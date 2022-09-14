package top.zshan.ggkt.vod.uitls;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author SansZhu
 * @create 2022/9/11 16:40
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {
    /**
     * tencent.cos.file.region=ap-beijing
     * tencent.cos.file.secretid=AKIDXu2ua091cx1oKaZHhsmPlqfDVWHlyuZx
     * tencent.cos.file.secretkey=90auxjETR7XhaCYdSzFExOYR5TZPyHJ3
     * #bucket可以在控制台创建，也可以使用java代码创建
     * tencent.cos.file.bucketname=ggkt-1251156075
     */
    @Value("${tencent.cos.file.region}")
    private String region;

    @Value("${tencent.cos.file.secretid}")
    private String secretId;

    @Value("${tencent.cos.file.secretkey}")
    private String secretKey;

    @Value("${tencent.cos.file.bucketname}")
    private String bucketName;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;



    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = region;
        ACCESS_KEY_ID = secretId;
        ACCESS_KEY_SECRET = secretKey;
        BUCKET_NAME = bucketName;
    }

}
