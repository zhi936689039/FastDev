package com.util;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 创建人 ：ouyangzhibao
 * @version 1.0
 * @package 包名 ：com.renrenda.client.util
 * @createTime 创建时间 ：2019/9/3
 * @modifyBy 修改人 ：ouyangzhibao
 * @modifyTime 修改时间 ：2019/9/3
 * @modifyMemo 修改备注：
 */
public class ConnectionUtil {
    /**
     * 判断当前连接是否有效
     * @param address
     * @return
     */
    public static boolean isConnect(String address){
        try{
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setConnectTimeout(10*1000);
            conn.setReadTimeout(10*1000);

            //HTTP connect
            try {
                conn.connect();
            } catch(Exception e) {
                e.printStackTrace();
                return false;
            }

            int code = conn.getResponseCode();
            if ((code >= 100) && (code < 400)){
                return true;
            }

            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
