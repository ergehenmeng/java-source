package com.eghm;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import javax.swing.plaf.multi.MultiFileChooserUI;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ZxingTest {
	public static void main(String[] args) throws IOException, WriterException, NotFoundException {
		encode("http://www.baidu.com/", "/Users/wyb/qc.png");
		System.out.println(decode("/Users/wyb/qc.png"));
	}

	public static void encode(String content, String path) throws WriterException, IOException {
		int width = 100;
		int height = 100;
		Map<EncodeHintType, String> map = new HashMap<>();
		map.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width, height, map);
		Path file = FileSystems.getDefault().getPath(path);
		MatrixToImageWriter.writeToPath(matrix, "png",file);
	}

	public static String decode(String path ) throws IOException, NotFoundException {
		BufferedImage image = ImageIO.read(new File(path));
		BufferedImageLuminanceSource luminanceSource = new BufferedImageLuminanceSource(image);
		HybridBinarizer binarizer = new HybridBinarizer(luminanceSource);
		BinaryBitmap bitmap = new BinaryBitmap(binarizer);
		Map<DecodeHintType, Object> decodeHints = new HashMap<>();
		decodeHints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
		Result decode = new MultiFormatReader().decode(bitmap, decodeHints);
		return decode.getText();
	}
}
