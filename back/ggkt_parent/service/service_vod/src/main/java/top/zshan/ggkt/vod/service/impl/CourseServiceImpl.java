package top.zshan.ggkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import top.zshan.ggkt.model.vod.Course;
import top.zshan.ggkt.model.vod.CourseDescription;
import top.zshan.ggkt.model.vod.Subject;
import top.zshan.ggkt.model.vod.Teacher;
import top.zshan.ggkt.vo.vod.CourseFormVo;
import top.zshan.ggkt.vo.vod.CoursePublishVo;
import top.zshan.ggkt.vo.vod.CourseQueryVo;
import top.zshan.ggkt.vod.mapper.CourseMapper;
import top.zshan.ggkt.vod.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Sans
 * @since 2022-09-19
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    TeacherService teacherService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    CourseDescriptionService descriptionService;
    @Autowired
    VideoService videoService;
    @Autowired
    ChapterService chapterService;
    @Autowired
    CourseMapper courseMapper;

    @Override
    public Map findPage(Page<Course> pageParam, CourseQueryVo courseQueryVo) {
        //获取条件值
        Long subjectId = courseQueryVo.getSubjectId();
        Long subjectParentId = courseQueryVo.getSubjectParentId();
        Long teacherId = courseQueryVo.getTeacherId();
        String title = courseQueryVo.getTitle();

//        添加查询条件
        QueryWrapper wrapper = new QueryWrapper();
        if(!StringUtils.isEmpty(title)) {
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id",subjectId);
        }
        if(!StringUtils.isEmpty(subjectParentId)) {
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(teacherId)) {
            wrapper.eq("teacher_id",teacherId);
        }
//        调用方法查询
        Page<Course> pages = baseMapper.selectPage(pageParam, wrapper);
        List<Course> records = pages.getRecords();
        long current = pages.getCurrent();
        long totalCount = pages.getTotal();
        long totalPage = pages.getPages();
        long size = pages.getSize();

        records.stream().forEach(item -> {
            this.getTeacherOrSubkectName(item);
        });
        Map<String,Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);
        map.put("records",records);
        return map;
    }
    //实现方法
    //添加课程基本信息
    @Override
    public Long save(CourseFormVo courseFormVo) {
        //保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        baseMapper.insert(course);

        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setId(course.getId());
        descriptionService.save(courseDescription);
        return course.getId();
    }

    //根据id获取课程信息
    @Override
    public CourseFormVo getCourseFormVoById(Long id) {
        //从course表中取数据
        Course course = baseMapper.selectById(id);
        if(course == null){
            return null;
        }
        //从course_description表中取数据
        CourseDescription courseDescription = descriptionService.getById(id);
        //创建courseInfoForm对象
        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course,courseFormVo);
        if (courseDescription != null){
            courseFormVo.setDescription(courseDescription.getDescription());
        }
        return courseFormVo;
    }

    //根据id修改课程信息
    @Override
    public void updateCourseById(CourseFormVo courseFormVo) {
        //修改课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        baseMapper.updateById(course);
        //修改课程详情信息
        CourseDescription courseDescription = descriptionService.getById(course.getId());
        System.out.println(courseDescription);
        System.out.println(courseFormVo.getDescription());
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setId(course.getId());
        descriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo getCoursePublishVo(Long id) {
        return courseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(Long id) {
        Course course = new Course();
        course.setId(id);
        course.setPublishTime(new Date());
        course.setStatus(1);
        return this.updateById(course);
    }

    private Course getTeacherOrSubkectName(Course item) {
        Long teacherId = item.getTeacherId();
        Teacher teacher = teacherService.getById(teacherId);
        if (teacher != null){
            item.getParam().put("teacherName",teacher.getName());
        }
        Long subjectId = item.getSubjectId();
        Subject subject = subjectService.getById(subjectId);
        if (subject != null){
            item.getParam().put("subjectTitle",subject.getTitle());
        }
        Long subjectParentId = item.getSubjectParentId();
        Subject parentSubject = subjectService.getById(subjectParentId);
        if (parentSubject != null){
            item.getParam().put("subjectParentTitle",parentSubject.getTitle());
        }
        return item;
    }

    //删除课程
    @Override
    public void removeCourseById(Long id) {
        //根据课程id删除小节
        videoService.removeVideoByCourseId(id);
        //根据课程id删除章节
        chapterService.removeChapterByCourseId(id);
        //根据课程id删除描述
        descriptionService.removeById(id);
        //根据课程id删除课程
        baseMapper.deleteById(id);
    }
}
