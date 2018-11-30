package cn.et.yitao.util;

import com.google.zxing.WriterException;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class QRCodeUtilTest {

    @Test
    public void createQRCode() throws IOException, WriterException {
        FileOutputStream fos = new FileOutputStream(new File("F:/123.jpg"));
        QRCodeUtil.createQRCode(400,400,"aadsjasoassj",fos);
    }
}