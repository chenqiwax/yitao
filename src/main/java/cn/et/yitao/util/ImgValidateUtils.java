package cn.et.yitao.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @描述: 创建图片验证码的工具类
 * @Author: pyj
 * @DateTime: 2018/10/11 0011--下午 1:13
 */
public class ImgValidateUtils {
    private int w = 70;
    private int h = 35;

    private Random r = new Random();

    // 将字体归集到一起  然后便于随机获取一个字体
    private String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
    // 随机产生字符(可选字符)
    private String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQSTUVWXYZ";
    // 背景颜色
    private Color bgColor = new Color(255,255,255);
    // 验证码上的文本
    private String text;

    // create random color
    private Color randomColor() {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color (red , green , blue);
    }

    // create random font
    private Font randdomFont() {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index]; // create random fontname
        int style = r.nextInt(4); // create random fontStyle 0(no style),1(overStriking), 2(italic), 3(overstriking+italic)
        int size = r.nextInt(5)+24; // create random fontsize number

        return new Font(fontName,style,size);
    }

    // paint disturb line
    private void drawLine(BufferedImage image) {
        int num = 3; // set line's count
        Graphics2D g2 = (Graphics2D)image.getGraphics();
        for(int i=0;i<num;i++) { // two points are sure a line
            int x1 = r.nextInt(w);
            int y1 = r.nextInt(h);
            int x2 = r.nextInt(w);
            int y2 = r.nextInt(h);
            g2.setStroke(new BasicStroke(1.5F));
            g2.setColor(Color.BLUE); // set disturb line's color
            g2.drawLine(x1, y1, x2, y2); // paint the line
        }
    }

    // create random a character
    private char randomChar() {
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }

    // create bufferedImage
    private BufferedImage createImage() {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D)image.getGraphics();
        g2.setColor(this.bgColor);
        g2.fillRect(0, 0, w, h);
        return image;
    }

    // get identifying code
    public BufferedImage getImage(){
        BufferedImage image = createImage(); // create picture buffered area
        Graphics2D g2 = (Graphics2D)image.getGraphics(); // get draw environment
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<4;i++) {
            String s = randomChar() + "";
            sb.append(s);
            float x = i * 1.0F * w / 4; // set charecter's coordinate on X Axes;
            g2.setFont(randdomFont()); // set font
            g2.setColor(randomColor()); // set fontcolor
            g2.drawString(s, x, h-5); // painting
        }
        this.text = sb.toString();
        drawLine(image);
        return image;
    }

    public String getText() {
        return text;
    }

    // store picture
    public static void output(BufferedImage image , OutputStream out) throws IOException {
        ImageIO.write(image, "JPEG", out);
    }
}
