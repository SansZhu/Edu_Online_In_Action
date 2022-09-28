package top.zshan.ggkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.zshan.ggkt.model.vod.Chapter;
import top.zshan.ggkt.model.vod.Video;
import top.zshan.ggkt.vo.vod.ChapterVo;
import top.zshan.ggkt.vo.vod.VideoVo;
import top.zshan.ggkt.vod.mapper.ChapterMapper;
import top.zshan.ggkt.vod.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zshan.ggkt.vod.service.VideoService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Sans
 * @since 2022-09-19
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    //章节小结列表封装
    @Override
    public List<ChapterVo> getNestedTreeList(Long courseId) {
        List<ChapterVo> chapterVoList = new ArrayList<>();

        //获取章信息
        LambdaQueryWrapper<Chapter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Chapter::getCourseId,courseId);
        wrapper.orderByAsc(Chapter::getSort,Chapter::getId);
        List<Chapter> chapters = baseMapper.selectList(wrapper);
        //获取课时信息
        LambdaQueryWrapper<Video> wrapperVideo = new LambdaQueryWrapper<>();
        wrapperVideo.eq(Video::getCourseId,courseId);
        wrapperVideo.orderByAsc(Video::getSort,Video::getId);
        List<Video> listVideo = videoService.list(wrapperVideo);

        //填充列表数据：Chapter列表
        for (int i = 0; i < chapters.size(); i++) {
            //创建ChapterVo对象
            ChapterVo chapterVo = new ChapterVo();
            Chapter chapter = chapters.get(i);
            BeanUtils.copyProperties(chapter,chapterVo);
            chapterVoList.add(chapterVo);
            //填充列表数据：Video列表
            List<VideoVo> videos = new ArrayList<>();
            for (Video v :
                    listVideo) {
                if (chapter.getId().equals(v.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(v,videoVo);
                    videos.add(videoVo);
                }
            }
            chapterVo.setChildren(videos);

        }

        return chapterVoList;
    }

    @Override
    public void removeChapterByCourseId(Long id) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }
}
