package cn.et.yitao.util;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class QQEmailUtilsTest {

    @Test
    public void sendEmail() {
       /* String s = QQEmailUtils.sendEmail("1921683476@qq.com", "测试邮箱");
        System.out.println(s);*/
    }

    @Test
    public void upload() throws IOException, MyException {
        ClientGlobal.init("F:\\woker\\chenqi\\work\\web\\yitao\\src\\main\\resources\\conf\\client.conf");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        String[] jpegs = storageClient.upload_appender_file("G:\\视频\\17-宜立方商城或淘淘商城（ssm统合项目二第80-93天）\\e3商城_day01\\黑马32期\\01.教案-3.0\\01.参考资料\\广告图片\\9a80e2d06170b6bb01046233ede701b3.jpg", "jpg", null);
        for (String str:jpegs) {
            System.out.println(str);
        }
    }

    @Test
    public void testUploadUtil() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("F:\\woker\\chenqi\\work\\web\\yitao\\src\\main\\resources\\conf\\client.conf");
        String s = fastDFSClient.uploadFile("F:\\woker\\黄杰荣\\java面试题.docx");
        System.out.println("http://chenqi.xyz:88/"+s);
    }


}