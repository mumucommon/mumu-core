package com.lovecws.mumu.common.core.utils;

import java.io.*;

/**
 * 它的作用就是把对象转化为byte数组，或把byte数组转化为对象。
 */
public class SerializeUtils {

    /**
     * 将对象序列化成字节数组
     *
     * @param o
     * @return
     */
    public static byte[] serialize(Object o) {
        if(o==null){
            return null;
        }
        ObjectOutputStream outo = null;
        ByteArrayOutputStream out = null;
        try {
            outo = new ObjectOutputStream(out);
            out = new ByteArrayOutputStream();
            outo.writeObject(o);
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将序列化的字节数据反序列化为对象
     *
     * @param b
     * @return
     */
    public static Object deserialize(byte[] b) {
        if(b==null){
            return null;
        }
        ObjectInputStream oin = null;
        try {
            oin = new ObjectInputStream(new ByteArrayInputStream(b));
            return oin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                oin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
