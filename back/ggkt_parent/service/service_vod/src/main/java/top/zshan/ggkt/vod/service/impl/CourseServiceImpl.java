package top.zshan.ggkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import top.zshan.ggkt.model.vod.Course;
import top.zshan.ggkt.model.vod.Subject;
import top.zshan.ggkt.model.vod.Teacher;
import top.zshan.ggkt.vo.vod.CourseQueryVo;
import top.zshan.ggkt.vod.mapper.CourseMapper;
import top.zshan.ggkt.vod.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zshan.ggkt.vod.service.SubjectService;
import top.zshan.ggkt.vod.service.TeacherService;

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
}
