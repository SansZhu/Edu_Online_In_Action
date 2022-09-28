package top.zshan.ggkt.vod.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.zshan.ggkt.model.vod.Video;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Sans
 * @since 2022-09-19
 */
public interface VideoService extends IService<Video> {

    void removeVideoByCourseId(Long id);
}
