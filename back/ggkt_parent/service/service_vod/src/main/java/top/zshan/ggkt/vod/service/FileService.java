package top.zshan.ggkt.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author SansZhu
 * @create 2022/9/11 16:54
 */
public interface FileService {
    String upload(MultipartFile file);
}
