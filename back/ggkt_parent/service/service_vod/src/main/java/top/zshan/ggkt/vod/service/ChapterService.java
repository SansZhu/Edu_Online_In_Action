package top.zshan.ggkt.vod.service;

import top.zshan.ggkt.model.vod.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import top.zshan.ggkt.vo.vod.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Sans
 * @since 2022-09-19
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getNestedTreeList(Long courseId);

    void removeChapterByCourseId(Long id);
}
