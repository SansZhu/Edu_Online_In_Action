package top.zshan.ggkt.vod.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zshan.ggkt.result.Result;
import top.zshan.ggkt.vod.service.FileService;

/**
 * @author SansZhu
 * @create 2022/9/11 16:55
 */
@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/admin/vod/file")
@CrossOrigin
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public Result upload(@ApiParam(name = "file",value = "文件",required = true)
                         @RequestParam("file") MultipartFile file){
        String uploadUrl = fileService.upload(file);
        return Result.ok(uploadUrl).message("文件上传成功");
    }

}
