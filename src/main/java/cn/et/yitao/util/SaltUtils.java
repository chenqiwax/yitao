package cn.et.yitao.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;

import java.util.UUID;

/**
 * @描述: shiro盐的工具类
 * @Author: pyj
 * @DateTime: 2018/10/9--上午 10:26
 */
public class SaltUtils {

    /**
     * 功能描述 获取盐值
     * @author pyj
     * @date 2018/10/9 0009
      * @param
     * @return java.lang.String
     */
    public static String getSaltValue(){
        String salt = UUID.randomUUID().toString();
        salt = salt.replaceAll("-","")+System.currentTimeMillis();
        return salt;
    }
    /**
     * 功能描述 获取uuid
     * @author chenqi
     *
     * @param
     * @return java.lang.String
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 功能描述 对用户密码进行md5加密
     * @author pyj
     * @date 2018/10/11 0011
      * @param password // 用户密码
     * @param saltValue  // 盐值
     * @return java.lang.String
     */
    public static String getMatcher(String password,String saltValue){
        Sha256Hash sha256Hash = new Sha256Hash(password, saltValue);
        String newpassword = sha256Hash.toHex();
        return newpassword;
    }
}
