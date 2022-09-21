package top.zshan.ggkt.vod.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import top.zshan.ggkt.model.vod.Course;
import top.zshan.ggkt.result.Result;
import top.zshan.ggkt.vo.vod.CourseQueryVo;
import top.zshan.ggkt.vod.service.CourseService;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Sans
 * @since 2022-09-19
 */
@Api(tags = "课程管理接口")
@RestController
@RequestMapping("/admin/vod/course")
@CrossOrigin
public class CourseController {
    @Autowired
    CourseService courseService;

    @ApiOperation("获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        CourseQueryVo courseQueryVo){
        Page<Course> pageParam = new Page<>(page,limit);
        Map map = courseService.findPage(pageParam,courseQueryVo);
        return Result.ok(map);
    }
}

