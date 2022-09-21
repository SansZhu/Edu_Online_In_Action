package top.zshan.ggkt.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import top.zshan.ggkt.model.vod.Subject;
import top.zshan.ggkt.vo.vod.SubjectEeVo;
import top.zshan.ggkt.vod.listener.SubjectListener;
import top.zshan.ggkt.vod.mapper.SubjectMapper;
import top.zshan.ggkt.vod.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Sans
 * @since 2022-09-11
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectListener subjectListener;
    @Override
    public List<Subject> selectList(Long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",id);
        List<Subject> list = baseMapper.selectList(queryWrapper);
        for (Subject subject : list) {
            Long id1 = subject.getId();
           boolean isChildren =  this.isChildren(id1);
           subject.setHasChildren(isChildren);
        }

        return list;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
            List<Subject> dictList = baseMapper.selectList(null);
            ArrayList<SubjectEeVo> list = new ArrayList<>(dictList.size());
            for (Subject s :
                    dictList) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
                BeanUtils.copyProperties(s,subjectEeVo);
                list.add(subjectEeVo);
            }
            EasyExcel.write(response.getOutputStream(),SubjectEeVo.class).sheet("课程分类").doWrite(list);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),SubjectEeVo.class,subjectListener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isChildren(Long id1) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",id1);
        Long aLong = baseMapper.selectCount(queryWrapper);
        return aLong > 0;
    }
}
