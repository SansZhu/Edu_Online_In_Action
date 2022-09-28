package top.zshan.ggkt.vod.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import top.zshan.ggkt.model.vod.Course;
import top.zshan.ggkt.result.Result;
import top.zshan.ggkt.vo.vod.CourseFormVo;
import top.zshan.ggkt.vo.vod.CoursePublishVo;
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

    @ApiOperation("新增")
    @PostMapping("save")
    public Result save(@RequestBody CourseFormVo courseFormVo){
        Long id = courseService.save(courseFormVo);
        return Result.ok(id);
    }

    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        CourseFormVo courseFormVoById = courseService.getCourseFormVoById(id);
        return Result.ok(courseFormVoById);
    }

    @PostMapping("update")
    public Result update(@RequestBody CourseFormVo courseFormVo){
        courseService.updateCourseById(courseFormVo);
        return Result.ok(courseFormVo.getId());
    }

    @ApiOperation("根据id获取课程发布信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result getCoursePublishVoById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id){

        CoursePublishVo coursePublishVo = courseService.getCoursePublishVo(id);
        return Result.ok(coursePublishVo);
    }

    @ApiOperation("根据id发布课程")
    @PutMapping("publishCourseById/{id}")
    public Result publishCourseById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id){

        boolean result = courseService.publishCourseById(id);
        return Result.ok(null);
    }
    @ApiOperation("删除课程")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        courseService.removeCourseById(id);
        return Result.ok(null);
    }
}

