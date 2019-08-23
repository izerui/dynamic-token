package com.github.token;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by leon on 2018/8/2.
 */
public class TokenHelper {

    /**
     * 生成token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public String getToken(DynamicToken token) {
        byte[] data;
        data = sha1(token.getSecKey(), System.currentTimeMillis() / (token.getInterval().getValue() * 1000));//sha1生成 20字节（160位）的数据摘要
        int o = data[19] & 0xf;//通过对最后一个字节的低4位二进制位建立索引，索引范围为  （0-15）+4  ，正好20个字节。
        int number = hashToInt(data, o) & 0x7fffffff;  //然后计算索引指向的连续4字节空间生成int整型数据。
        return output(String.valueOf(number % 1000000));//对获取到的整型数据进行模运算，再对结果进行补全（长度不够6位，在首位补零）得到长度为6的字符串
    }

    /**
     * 取数据摘要
     *
     * @param secKey
     * @param msg
     * @return 加密后的字节数字
     * @throws Exception
     */
    private byte[] sha1(String secKey, long msg) {
        try {
            SecretKey secretKey = new SecretKeySpec(secKey.getBytes(), "");//创建秘钥
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKey);//初始化秘钥
            byte[] value = ByteBuffer.allocate(8).putLong(msg).array();//将long类型的数据转换为byte数组
            return mac.doFinal(value);//计算数据摘要
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    /**
     * 将byte数组转化为整数
     *
     * @param bytes
     * @param start
     * @return int
     */
    private int hashToInt(byte[] bytes, int start) {
        DataInput input = new DataInputStream(
                new ByteArrayInputStream(bytes, start, bytes.length - start));
        int val;
        try {
            val = input.readInt();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return val;
    }

    /**
     * 格式化输出结果
     *
     * @param s
     */
    private String output(String s) {
        if (s.length() < 6) {
            s = "0" + s;
            return output(s);
        }
        return s;
    }
}
