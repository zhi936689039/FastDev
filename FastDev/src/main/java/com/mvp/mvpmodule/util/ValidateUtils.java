package com.mvp.mvpmodule.util;

import android.content.Context;
import android.text.TextUtils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 验证工具类 3.0
 *
 * @author cp
 */
public class ValidateUtils {
    //验证邮箱
    private static final String EMAIL_VALIDATION_REGEX = "^[\\w\\-\\+\\.]+@[\\w\\-]+\\.[A-Za-z]{2,}$";
    //验证密码
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$|/\\\\:*?<>|\\\"\\n\\t";
    //验证汉子
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(16[0-9])|(18[0-9]))\\d{8}$";

    public static boolean isValidate(String str) {
     /*   if(!(TextUtils.isEmpty(str)&&!str.trim().isEmpty())){
            return true;
        }else{
            return false;
        }*/
        return (!(TextUtils.isEmpty(str))&&str!=null&&!"".equals(str)&&!"null".equals(str)&&!str.isEmpty()&&str.trim().length()!=0);
    }


    public static <T> boolean isValidate(List<T> list) {
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }


    public static <T> boolean isValidate(T t) {
        return t == null ? false : true;
    }

    public static <T> boolean isValidate(int i) {
        return i == 0 ? false : true;
    }


    public static boolean isValidPhoneNumber(String phone) {
        if(TextUtils.isEmpty(phone)){
            return false;
        }
        boolean matches = Pattern.matches(REGEX_MOBILE, phone);
        return matches;
    }
    public static boolean isValidPwd(String pwd) {
        if(TextUtils.isEmpty(pwd)){
            return false;
        }
        boolean matches = Pattern.matches(REGEX_PASSWORD, pwd);
        return matches;
    }


    /**
     * 是否是邮箱
     * @param emailAddress
     * @return
     */
    public static boolean isValidEmail(String emailAddress) {
        return emailAddress.matches(EMAIL_VALIDATION_REGEX);
    }


    /**
     * 判断一个字符串是否含有数字
     * @param content
     * @return
     */
    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 判断是否含有特殊字符
     *
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!#$%^&*()+=|{}':;',\\[\\]<>/?~！#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 护照是否有效 香港 大陆
     * @param passport
     * @return
     */
    public static boolean isValidPassport(String passport){
        String regEx = "^1[45][0-9]{7}|([P|p|S|s]\\d{7})|([S|s|G|g]\\d{8})|([Gg|Tt|Ss|Ll|Qq|Dd|Aa|Ff]\\d{8})|([H|h|M|m]\\d{8,10})$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(passport);
        return matcher.matches();
    }


    public static boolean isWeixinAvilible(Context context) {
        return LauncherUtil.isExitApp(context,"com.tencent.mm");
    }
    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        return LauncherUtil.isExitApp(context,"com.tencent.mobileqq");
    }
    public static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && codePoint <= 0xD7FF))|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
    public static boolean checkStrIsNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
