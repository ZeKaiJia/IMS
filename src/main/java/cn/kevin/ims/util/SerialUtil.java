package cn.kevin.ims.util;

import cn.kevin.ims.entity.Permission;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * serial
 *
 * @Author: Kevin
 * @Date: 2020/8/2 2:11 下午
 */
public class SerialUtil {
    public static byte[] serial(HashSet<Permission> permissions) throws IOException {
        byte[] std;
        ByteArrayOutputStream byt = new ByteArrayOutputStream();
        ObjectOutputStream obj = new ObjectOutputStream(byt);
        obj.writeObject(permissions);
        std = byt.toByteArray();
        return std;
    }
    public static HashSet<Permission> reSerial(byte[] std) throws IOException, ClassNotFoundException {
        HashSet<Permission> permissions;
        ByteArrayInputStream byteInt = new ByteArrayInputStream(std);
        ObjectInputStream objInt = new ObjectInputStream(byteInt);
        permissions = (HashSet<Permission>) objInt.readObject();
        return permissions;
    }
}
