package com.eghm.image;

import cn.hutool.core.io.FileUtil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 殿小二
 * @date 2021/2/3
 */
public class ImageUtil {

    static {
        System.load("C:\\Java\\jdk1.8.0_181\\bin\\opencv_java451.dll");
    }

    /**
     * 从视频中提取图片信息并返回图片名称
     * @param videoPath 视频路径
     * @param num 提取多少张图片
     * @param imagePath 图片保存的路径
     * @return 图片路径
     * @throws FrameGrabber.Exception Exception
     */
    public static List<String> getImage(String videoPath, int num, String imagePath) throws IOException {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(FileUtil.file(videoPath));
        grabber.start();
        int lengthInFrames = grabber.getLengthInFrames();
        int interval = lengthInFrames / num;
        int index = 0;
        List<String> imageList = new ArrayList<>();
        Frame frame = null;
        while (index <= lengthInFrames) {
            int subIndex = interval;
            while (subIndex-- > 0) { frame = grabber.grabImage();}
            if (frame != null) {
                imageList.add(frameToImage(frame, imagePath));
            }
            index += interval;
        }
        return imageList;
    }

    private static String frameToImage(Frame frame, String imagePath) throws IOException {
        String imageName = UUID.randomUUID().toString() + ".jpg";
        File file = FileUtil.newFile(imagePath + File.separator + imageName);
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage image = converter.convert(frame);
        ImageIO.write(image, "jpg", file);
        return imageName;
    }

    public static void main(String[] args) throws IOException {
        ImageUtil.getImage("C:\\Users\\DXE\\Desktop\\test.mp4", 10, "C:\\Users\\DXE\\Desktop");
    }
}
