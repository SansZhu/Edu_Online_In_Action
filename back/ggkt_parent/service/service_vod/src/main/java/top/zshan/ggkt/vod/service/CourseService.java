package top.zshan.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.zshan.ggkt.model.vod.Course;
import com.baomidou.mybatisplus.extension.service.IService;
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
}
