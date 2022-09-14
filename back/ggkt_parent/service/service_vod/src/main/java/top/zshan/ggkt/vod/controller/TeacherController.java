package top.zshan.ggkt.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.zshan.ggkt.exception.GgktException;
import top.zshan.ggkt.model.vod.Teacher;
import top.zshan.ggkt.result.Result;
import top.zshan.ggkt.vo.vod.TeacherQueryVo;
import top.zshan.ggkt.vod.service.TeacherService;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Sans
 * @since 2022-08-08
 */
@RestController
@RequestMapping("/admin/vod/teacher")
@Api(tags = "讲师管理接口")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

//    //1.获取所有讲师信息
//    @GetMapping("findAll")
//    @ApiOperation(value = "获取所有讲师信息")
//    public List<Teacher> findAllTeacher(){
//        List<Teacher> list = teacherService.list();
//        return list;
//    }
//    1.获取所有讲师信息
    @GetMapping("findAll")
    @ApiOperation(value = "获取所有讲师信息")
    public Result findAllTeacher()  {

        try {
            int a = 10/0;
        }catch(Exception e) {
            throw new GgktException(20001,"出现自定义异常");
        }
        List<Teacher> list = teacherService.list();
        return Result.ok(list);
    }

//    @DeleteMapping("remove/{id}")
//    @ApiOperation(value = "删除讲师信息")
//    public boolean removeTeacher(@ApiParam(name = "id", value = "ID", required = true)
//                                     @PathVariable("id") String id){
//        boolean b = teacherService.removeById(id);
//        return b;
//    }
    @DeleteMapping("remove/{id}")
    @ApiOperation(value = "删除讲师信息")
    public Result removeTeacher(@ApiParam(name = "id", value = "ID", required = true)
                                     @PathVariable("id") String id){
        boolean b = teacherService.removeById(id);
        if (b){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    @PostMapping("findQueryPage/{current}/{limit}")
    @ApiOperation("条件分页查询")
    public Result findQueryPage(@PathVariable long current,
                                @PathVariable  long limit,
                                @RequestBody(required = false) TeacherQueryVo teacherQueryVo){
        Page<Teacher> pageParam = new Page<>(current,limit);
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if (teacherQueryVo == null){
            IPage<Teacher> page = teacherService.page(pageParam, null);
            return Result.ok(page);
        }else {
            String name = teacherQueryVo.getName();
            Integer level = teacherQueryVo.getLevel();
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();
            if (!StringUtils.isEmpty(name)){
                wrapper.like("name",name);
            }
            if (!StringUtils.isEmpty(level)){
                wrapper.eq("level",level);
            }
            if (!StringUtils.isEmpty(joinDateBegin)){
                wrapper.ge("join_date",joinDateBegin);
            }
            if (!StringUtils.isEmpty(joinDateEnd)){
                wrapper.le("join_date",joinDateEnd);
            }
            IPage<Teacher> page = teacherService.page(pageParam, wrapper);
            return Result.ok(page);
        }

    }

    @ApiOperation("添加讲师操作")
    @PostMapping("saveTeacher")
    public Result saveTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if (save){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }
    @ApiOperation("按ID查询")
    @GetMapping("findById/{id}")
    public Result findById(@PathVariable long id){
        Teacher byId = teacherService.getById(id);
        if (byId!=null){
            return Result.ok(byId);
        }else {
            return Result.fail(byId);
        }
    }
    @ApiOperation("修改讲师")
    @PutMapping("update")
    public Result updateById(@RequestBody Teacher teacher){
        boolean b = teacherService.updateById(teacher);
        if (b){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    @ApiOperation("批量删除讲师")
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestBody List<Long> ids){
        boolean b = teacherService.removeBatchByIds(ids);
        if (b){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }
}

