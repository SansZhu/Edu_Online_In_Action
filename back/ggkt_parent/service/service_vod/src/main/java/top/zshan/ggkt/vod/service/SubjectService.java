package top.zshan.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import top.zshan.ggkt.model.vod.Subject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Sans
 * @since 2022-09-11
 */
public interface SubjectService extends IService<Subject> {

    List<Subject> selectList(Long id);

    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);
}
