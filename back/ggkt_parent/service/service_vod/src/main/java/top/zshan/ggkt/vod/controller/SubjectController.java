package top.zshan.ggkt.vod.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import top.zshan.ggkt.model.vod.Subject;
import top.zshan.ggkt.result.Result;
import top.zshan.ggkt.vod.service.SubjectService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Sans
 * @since 2022-09-11
 */
@RestController
@RequestMapping("/admin/vod/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("查询下一层课程分类")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable("id")Long id){
       List<Subject> list =  subjectService.selectList(id);
       return Result.ok(list);
    }

    @ApiOperation("导出课程")
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response){
        subjectService.exportData(response);
    }

    @ApiOperation("导入课程")
    @PostMapping("importData")
    public Result importData(MultipartFile file){
        subjectService.importData(file);
        return Result.ok(null);
    }



}

