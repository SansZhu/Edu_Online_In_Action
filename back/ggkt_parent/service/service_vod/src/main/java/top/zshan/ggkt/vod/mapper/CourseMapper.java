package top.zshan.ggkt.vod.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.zshan.ggkt.model.vod.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.zshan.ggkt.vo.vod.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Sans
 * @since 2022-09-19
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    CoursePublishVo selectCoursePublishVoById(Long id);
}
