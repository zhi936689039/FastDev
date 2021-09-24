package com.mvp.mvpmodule.util;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author 创建人 ：ouyangzhibao
 * @version 1.0
 * @package 包名 ：com.renrenda.client.util
 * @createTime 创建时间 ：2019/8/2
 * @modifyBy 修改人 ：ouyangzhibao
 * @modifyTime 修改时间 ：2019/8/2
 * @modifyMemo 修改备注：
 */
public class NumUtil {
    public static String formatNum(String num) {
        BigDecimal bk = new BigDecimal("1000");//千
        BigDecimal bm = new BigDecimal("1000000");//百万
        BigDecimal b10B = new BigDecimal("1000000000");//10亿
        BigDecimal b3 = new BigDecimal(num);
        if (b3.compareTo(b10B) == 0 || b3.compareTo(b10B) == 1) {
            return b3.divide(b10B).setScale(2, RoundingMode.HALF_UP) + "b";
        } else if (b3.compareTo(bm) == 0 || b3.compareTo(bm) == 1) {
            return b3.divide(bm).setScale(2, RoundingMode.HALF_UP) + "m";
        } else if (b3.compareTo(bk) == 0 || b3.compareTo(bk) == 1) {
            return b3.divide(bk).setScale(2, RoundingMode.HALF_UP) + "k";
        }else{
            return num;
        }
    }


}