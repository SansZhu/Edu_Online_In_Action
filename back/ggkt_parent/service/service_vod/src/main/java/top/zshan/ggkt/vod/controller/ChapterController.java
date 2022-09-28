package top.zshan.ggkt.vod.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import top.zshan.ggkt.model.vod.Chapter;
import top.zshan.ggkt.result.Result;
import top.zshan.ggkt.vo.vod.ChapterVo;
import top.zshan.ggkt.vod.service.ChapterService;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Sans
 * @since 2022-09-19
 */
@RestController
@RequestMapping("/admin/vod/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    ChapterService chapterService;
    //获取章节小结列表
    @ApiOperation("嵌套章节数据列表")
    @GetMapping("getNestedTreeList/{courseId}")
    public Result getNestedTreeList(@ApiParam(value = "课程ID", required = true)
                                        @PathVariable Long courseId){
        List<ChapterVo> chapterList = chapterService.getNestedTreeList(courseId);
        return Result.ok(chapterList);
    }
    //2 添加章节
    @PostMapping("save")
    public Result save(@RequestBody Chapter chapter) {
        boolean save = chapterService.save(chapter);
        return Result.ok(null);
    }
    //3 修改-根据id查询
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Chapter byId = chapterService.getById(id);
        return Result.ok(byId);
    }
    //4 修改-最终实现
    @PostMapping("update")
    public Result update(@RequestBody Chapter chapter) {
        boolean b = chapterService.updateById(chapter);
        return Result.ok(null);
    }
    //5 删除章节
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean b = chapterService.removeById(id);
        return Result.ok(null);
    }
}

