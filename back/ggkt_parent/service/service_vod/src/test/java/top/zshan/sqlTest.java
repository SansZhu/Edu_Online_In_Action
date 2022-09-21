package top.zshan;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author SansZhu
 * @create 2022/8/8 17:52
 * A:\InAction\Edu_Online_In_Action\back\ggkt_parent
 */
public class sqlTest {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/glkt_vod?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC", "root", "123abc")
                .globalConfig(builder -> {
                    builder.author("Sans") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("A:\\InAction\\Edu_Online_In_Action\\back\\ggkt_parent\\service\\service_vod\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("top.zshan.ggkt") // 设置父包名
                            .moduleName("vod") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "A:\\InAction\\Edu_Online_In_Action\\back\\ggkt_parent\\service\\service_vod\\src\\main\\java\\top\\zshan\\ggkt\\vod\\mapper\\xml")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("course","course_description","chapter","video"); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
