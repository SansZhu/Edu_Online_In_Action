package top.zshan.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.zshan.ggkt.model.vod.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import top.zshan.ggkt.vo.vod.CourseFormVo;
import top.zshan.ggkt.vo.vod.CoursePublishVo;
import top.zshan.ggkt.vo.vod.CourseQueryVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Sans
 * @since 2022-09-19
 */
public interface CourseService extends IService<Course> {

    Map findPage(Page<Course> pageParam, CourseQueryVo courseQueryVo);

    Long save(CourseFormVo courseFormVo);

    //根据id获取课程信息
    CourseFormVo getCourseFormVoById(Long id);

    //根据id修改课程信息
    void updateCourseById(CourseFormVo courseFormVo);

    CoursePublishVo getCoursePublishVo(Long id);

    boolean publishCourseById(Long id);


    void removeCourseById(Long id);
}
