package com.eghm;

/**
 * @author 殿小二
 * @date 2021/3/8
 */
public class ByteTest {
    public static void main(String[] args) {
        int max = 1242243422;
        System.out.println((byte)max);
        System.out.println((char) 0x09 );
        System.out.println((char) 0x0C);
        System.out.println((char) 0x0B );
        System.out.println( (char) 0x0D);
        // 相当于 >>> ( -6 + 64 ) Integer#rotateRight说明 int long右移只关注低6位的数 -6的低六位与58的低六位是一样的
        System.out.println(-1L >>> -6);
        System.out.println(-1L >>> 58);
        System.out.println(2<<3);
        System.out.println(2<<67);
        System.out.println(Integer.toBinaryString(-6));
        System.out.println(Integer.toBinaryString(58));
    }
}
