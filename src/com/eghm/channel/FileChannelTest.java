package com.eghm.channel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
	public static void main(String[] args) throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/wyb/channel.txt", "rw");
		FileChannel channel = randomAccessFile.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put("写入数据到channelxx".getBytes());
		buffer.flip();
		while (buffer.hasRemaining()) {
			channel.write(buffer);
		}
		//重新复位才能读到
		channel.position(0);
		buffer.compact();
		channel.read(buffer);
		buffer.flip();
		System.out.println(new String(buffer.array()));
	}
}
