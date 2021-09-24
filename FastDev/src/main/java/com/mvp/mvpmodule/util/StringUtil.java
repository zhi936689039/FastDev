package com.mvp.mvpmodule.util;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Pattern;

public class StringUtil {
    public static int[]StringToInt(String[] arrs){

        int[] ints=new int[arrs.length];

        for(int i=0;i<arrs.length;i++){

            ints[i]=Integer.parseInt(arrs[i]);

        }
        return ints;
    }
    public static String removeTrim(String str){
        if(str.indexOf(".") > 0){
            str = str.replaceAll("0+?$", "");//去掉多余的0
            str = str.replaceAll("[.]$", "");//如最后一位是.则去掉
        }else{
        str=str.replaceFirst("0*","");
    }
        return str;
    }
    // 采用Stringbuilder.append()的方式追加
    public static String listToString2(List<String> mList) {
        final String SEPARATOR = ",";
        // mList = Arrays.asList("AAA", "BBB", "CCC");
        StringBuilder sb = new StringBuilder();
        String convertedListStr = "";
        if (null != mList && mList.size() > 0) {
            for (String item : mList) {
                sb.append(item);
                sb.append(SEPARATOR);
            }
            convertedListStr = sb.toString();
            convertedListStr = convertedListStr.substring(0, convertedListStr.length()
                    - SEPARATOR.length());
            return convertedListStr;
        } else return "List is null!!!";
    }

    /**
     * 如果字符小数中包含.0和.00的字符的话，进行取整操作
     * @param str
     * @return
     */
    public static String toInteger(String str){
        String price = "";
        if (!TextUtils.isEmpty(str)&&str!=null) {
            if (str.contains(".00")) {
                price = str.replace(".00", "");
            } else if (str.contains(".0")) {
                price = str.replace(".0", "");
            }else{
                price = str;
            }
        }
        return price;
    }

    /**
     * 是否包含中文字符<br>
     * 包含中文标点符号<br>
     *
     * @param str
     * @return
     */
    public static boolean hasChinese(String str) {
        if (str == null) {
            return false;
        }
        char[] ch = str.toCharArray();
        for (char c : ch) {
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否全是中文字符<br>
     * 包含中文标点符号<br>
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        if (str == null) {
            return false;
        }
        char[] ch = str.toCharArray();
        for (char c : ch) {
            if (!isChinese(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否是中文字符<br>
     * 包含中文标点符号<br>
     *
     * @param c
     * @return
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
            return true;
        } else if (ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS) {
            return true;
        } else if (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) {
            return true;
        } else if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
            return true;
        } else if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B) {
            return true;
        } else if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C) {
            return true;
        } else if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D) {
            return true;
        } else if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        } else if (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 是否包含汉字<br>
     * 根据汉字编码范围进行判断<br>
     * CJK统一汉字（不包含中文的，。《》（）“‘'”、！￥等符号）<br>
     *
     * @param str
     * @return
     */
    public static boolean hasChineseByReg(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(str).find();
    }

    /**
     * 是否全是汉字<br>
     * 根据汉字编码范围进行判断<br>
     * CJK统一汉字（不包含中文的，。《》（）“‘'”、！￥等符号）<br>
     *
     * @param str
     * @return
     */
    public static boolean isChineseByReg(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(str).matches();
    }

    /**
     * 是否包含汉字<br>
     * 根据汉字编码范围进行判断<br>
     * CJK统一汉字（不包含中文的，。《》（）“‘'”、！￥等符号）<br>
     *
     * @param str
     * @return
     */
    public static boolean hasChineseByRange(String str) {
        if (str == null) {
            return false;
        }
        char[] ch = str.toCharArray();
        for (char c : ch) {
            if (c >= 0x4E00 && c <= 0x9FBF) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否全是汉字<br>
     * 根据汉字编码范围进行判断<br>
     * CJK统一汉字（不包含中文的，。《》（）“‘'”、！￥等符号）<br>
     *
     * @param str
     * @return
     */
    public static boolean isChineseByRange(String str) {
        if (str == null) {
            return false;
        }
        char[] ch = str.toCharArray();
        for (char c : ch) {
            if (c < 0x4E00 || c > 0x9FBF) {
                return false;
            }
        }
        return true;
    }

    /**
     * 功能：判断一个字符串是否包含特殊字符
     * 作者：Wave
     * @param string 要判断的字符串
     * @return true 提供的参数string不包含特殊字符
     * @return false 提供的参数string包含特殊字符
     */
    public static boolean isConSpeCharacters(String string) {
        // TODO Auto-generated method stub
        if(string.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*", "").length()==0){
            //如果不包含特殊字符
            return true;
        }
        return false;
    }

    public static void modifyTextViewDrawable(TextView v, Drawable drawable, int index) {
        if(null==drawable){
            return;
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        //index 0:左 1：上 2：右 3：下
        if (index == 0) {
            v.setCompoundDrawables(drawable, null, null, null);
        } else if (index == 1) {
            v.setCompoundDrawables(null, drawable, null, null);
        } else if (index == 2) {
            v.setCompoundDrawables(null, null, drawable, null);
        } else {
            v.setCompoundDrawables(null, null, null, drawable);
        }
    }
}
