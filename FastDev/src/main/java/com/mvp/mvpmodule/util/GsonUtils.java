package com.mvp.mvpmodule.util;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.mvp.mvpmodule.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * gson 解析工具
 *
 * @author oyzb 18.3.31
 */
public class GsonUtils {


    public static Gson gson = new Gson();

    public static <T> T deSerializedFromJson(String json, Class<T> clazz) throws JsonSyntaxException {
        return gson.fromJson(json, clazz);
    }

    public static <T> T deSerializedFromJson(String json, Type type) throws JsonSyntaxException {
        return gson.fromJson(json, type);
    }

    public static String serializedToJson(Object object) {
        if (object != null) {
            return gson.toJson(object);
        } else {
            return "";
        }
    }

    /**
     * 获取JsonObject
     *
     * @return JsonObject
     */
    public static JsonObject parseJson(String json) {
        JsonObject jsonObj = null;
        try {
            JsonParser parser = new JsonParser();
            jsonObj = parser.parse(json).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            LogUtil.e("frank", "parseJson Exception===" + e.toString(), BuildConfig.DEBUG);
        }
        return jsonObj;
    }

    /**
     * json字符串转成Bean对象
     *
     * @param str
     * @param type
     * @return
     */
    public static <T> T jsonToBean(String str, Class<T> type) {
        Gson gson = new Gson();
        try {
            T t = gson.fromJson(str, type);
            return t;
        } catch (JsonSyntaxException e) {
            LogUtil.e("frank", "jsonToBean Exception===" + e.toString()+"\n"+str, BuildConfig.DEBUG);

        }
        return null;
    }
    public static <T> T jsonToBeanFromData(String str, Class<T> type) {
        return jsonToBean(str,"datas",type);
    }

    public static <T> T jsonToBean(String str, String key, Class<T> type) {
        String data = getStringFromJSON(str,key);
        if(data==null){
            return null;
        }
        Gson gson = new Gson();
        try {
            T t = gson.fromJson(data, type);
            return t;
        } catch (JsonSyntaxException e) {
            LogUtil.e("frank", "jsonToBean Exception===" + e.toString()+"\n"+str, BuildConfig.DEBUG);

        }
        return null;
    }


    public static String getStringFromJSON(String json, String key1, String key2) {
        String data = "";
        try {
            JSONObject jsonObject = new JSONObject(json).getJSONObject(key1);
            data = jsonObject.getString(key2);

        } catch (JSONException e) {
            e.printStackTrace();
            LogUtil.e("frank", "getStringFromJSON Exception===" + e.toString(), BuildConfig.DEBUG);
        }
        return data;
    }


    public static long getLongFormJSON(String json, String key1, String key2) {
        long data = 0;
        try {
            JSONObject jsonObject = new JSONObject(json).getJSONObject(key1);
            data = jsonObject.getLong(key2);

        } catch (JSONException e) {
            LogUtil.e("frank", "getLongFormJSON Exception===" + e.toString(),BuildConfig.DEBUG);
        }
        return data;
    }

    /**
     * @param json
     * @param key1
     * @param key2
     * @return
     */
    // 现有逻辑有问题 ：更新包判断
    public static boolean getBooleanFormJSON(String json, String key1, String key2) {
        boolean data = true;
        try {
            JSONObject jsonObject = new JSONObject(json).getJSONObject(key1);
            data = jsonObject.getBoolean(key2);
        } catch (JSONException e) {
            LogUtil.e("frank", "getBooleanFormJSON Exception===" + e.toString(), BuildConfig.DEBUG);
        }
        return data;
    }
    public static Map<String, String> jsonToBeanMap(String json){
        Map<String, String> map;
        if (json == null|| TextUtils.isEmpty(json)){
            return new HashMap<String, String>();
        }else { //json data 字段不为空
            map = gson.fromJson(json,new TypeToken<Map<String, String>>() {}.getType());
            if (map!=null){
                return map;
            }
        }
        return new HashMap<String, String>();
    }

    public static <T, P> Map<T, P> jsonToBeanMap(String json, Type tClass) {
        Map<T, P> map;
        if (json == null || TextUtils.isEmpty(json)) {
            return new HashMap<T, P>();
        } else { //json data 字段不为空
            map = gson.fromJson(json, tClass);
            if (map != null) {
                return map;
            }
        }
        return new HashMap<T, P>();
    }

    /**
     * 根据key获取json object 的value
     *
     * @param json
     * @param key
     * @return
     */
    public static boolean getBooleanFormJSON(String json, String key) {
        boolean data = false;
        try {
            JSONObject jsonObject = new JSONObject(json);
            data = jsonObject.getBoolean(key);
        } catch (JSONException e) {
            LogUtil.e("frank", "getBooleanFormJSON Exception===" + e.toString()+"\njson="+json+"\nkey="+key, BuildConfig.DEBUG);
        }
        return data;
    }


    /**
     * 从JSON字符串提取出对应 Key的 字符串
     *
     * @param json
     * @param key
     * @return
     */
    public static String getStringFromJSON(String json, String key) {
        String data = null;
        if(json==null)return null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            data = jsonObject.getString(key);
        } catch (JSONException e) {
            LogUtil.e("frank", "getStringFromJSON Exception==="+"\n"+ e.toString()+"\n【key="+key+"】json="+json, BuildConfig.DEBUG);
        }
        return data;
    }


    public static int getIntFromJSON(JSONObject obj, String key) {
        int data = 0;
        try {
            if (obj == null) {
                return data;
            }
            data = obj.getInt(key);
        } catch (JSONException e) {
            LogUtil.e("frank", "getIntFromJSON Exception===" + e.toString(), BuildConfig.DEBUG);
        }
        return data;
    }


    public static int getIntFromJSON(String json, String key) {
        int data = 0;
        try {
            JSONObject jsonObject = new JSONObject(json);
            data = jsonObject.getInt(key);
        } catch (JSONException e) {
            LogUtil.e("frank", "getIntFromJSON Exception===" + e.toString(), BuildConfig.DEBUG);
        }
        return data;
    }
    public static double getDoubleFromJSON(String json, String key) {
        double data = 0;
        try {
            JSONObject jsonObject = new JSONObject(json);
            data = jsonObject.getDouble(key);
        } catch (JSONException e) {
            LogUtil.e("frank", "getIntFromJSON Exception===" + e.toString(), BuildConfig.DEBUG);
        }
        return data;
    }

    public static int getIntFromJSON(String json, String key1, String key2) {
        int data = 0;
        try {
            JSONObject jsonObject = new JSONObject(json);
            String dataJson = jsonObject.getString(key1);
            data = getIntFromJSON(dataJson, key2);
        } catch (JSONException e) {
            LogUtil.e("frank", "getIntFromJSON Exception===" + e.toString(), BuildConfig.DEBUG);
        }
        return data;
    }

    public static long getLongFromJSON(String json, String key) {
        long data = 0;
        try {
            JSONObject jsonObject = new JSONObject(json);
            data = jsonObject.getLong(key);
        } catch (JSONException e) {
            LogUtil.e("frank", "getLongFromJSON Exception===" + e.toString(), BuildConfig.DEBUG);
        }
        return data;
    }

    public static long getLongFromJSON(String json, String key1, String key2) {
        long data = 0;
        try {
            JSONObject jsonObject = new JSONObject(json);
            String dataJson = jsonObject.getString(key1);
            data = getLongFromJSON(dataJson, key2);
        } catch (JSONException e) {
            LogUtil.e("frank", "getLongFromJSON Exception===" + e.toString(), BuildConfig.DEBUG);
        }
        return data;
    }

    public static <T> List<T> jsonToBeanList(String json, Type tClass) {
        List<T> list;
        if (json == null || TextUtils.isEmpty(json)) { // json data 字段为空
            return new ArrayList<T>();
        } else { //json data 字段不为空
            try {
                list = gson.fromJson(json, tClass);
                if (list != null) {
                    return list;
                }
            } catch (JsonSyntaxException e) {
                LogUtil.e("frank", "jsonToBeanList_2 Exception ===" + e.toString(), BuildConfig.DEBUG);
            }
        }
        return new ArrayList<T>();
    }

    /**
     * 3.0
     * 从data里解析出对象数组  key1 一般为 : data  key2 ： your key
     *
     * @param json
     * @param tClass
     * @return list<T>  数据异常返回  list (size is 0)
     */
    public static <T> List<T> jsonToBeanList(String json, String key1, String key2, Type tClass) {
        List<T> list;
        String data = getStringFromJSON(json, key1, key2);
        if (data == null || TextUtils.isEmpty(data)) { // json data 字段为空
            return new ArrayList<T>();
        } else { //json data 字段不为空
            try {
                list = gson.fromJson(data, tClass);
                if (list != null) {
                    return list;
                }
            } catch (JsonSyntaxException je) {
                LogUtil.e("frank", "jsonToBeanList Exception===" + je.toString(), BuildConfig.DEBUG);
            }
        }
        return new ArrayList<T>();
    }
    /**
     * @param content json字符串
     * @return  如果转换失败返回null,
     */
    Map<String, Object> jsonToMap(String content) {
        content = content.trim();
        Map<String, Object> result = new HashMap<>();
        try {
            if (content.charAt(0) == '[') {
                JSONArray jsonArray = new JSONArray(content);
                for (int i = 0; i < jsonArray.length(); i++) {
                    Object value = jsonArray.get(i);
                    if (value instanceof JSONArray || value instanceof JSONObject) {
                        result.put(i + "", jsonToMap(value.toString().trim()));
                    } else {
                        result.put(i + "", jsonArray.getString(i));
                    }
                }
            } else if (content.charAt(0) == '{'){
                JSONObject jsonObject = new JSONObject(content);
                Iterator<String> iterator = jsonObject.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    Object value = jsonObject.get(key);
                    if (value instanceof JSONArray || value instanceof JSONObject) {
                        result.put(key, jsonToMap(value.toString().trim()));
                    } else {
                        result.put(key, value.toString().trim());
                    }
                }
            }else {
                LogUtil.e("Json_parase", "json2Map: 字符串格式错误", BuildConfig.DEBUG);

            }
        } catch (JSONException e) {
            LogUtil.e("Json_parase", "json2Map: "+ e, BuildConfig.DEBUG);
            result = null;
        }
        return result;
    }


}
