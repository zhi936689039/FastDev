package com.mvp.mvpmodule.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 创建人 ：ouyangzhibao
 * @version 1.0
 * @package 包名 ：com.renrenda.client.util
 * @createTime 创建时间 ：2019/8/19
 * @modifyBy 修改人 ：ouyangzhibao
 * @modifyTime 修改时间 ：2019/8/19
 * @modifyMemo 修改备注：
 */
public class MD5Util {
    public static String getMD5(String url) {
        String result="";
        try {
            MessageDigest md= MessageDigest.getInstance("md5");
            md.update(url.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb=new StringBuilder();
            for(byte b:bytes){
                String str=Integer.toHexString(b&0xFF);
                if(str.length()==1){
                    sb.append("0");
                }
                sb.append(str);
            }
            result=sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
