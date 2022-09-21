package top.zshan.ggkt.vod.service.impl;

import top.zshan.ggkt.model.vod.Video;
import top.zshan.ggkt.vod.mapper.VideoMapper;
import top.zshan.ggkt.vod.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Sans
 * @since 2022-09-19
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
