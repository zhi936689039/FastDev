package com.mvp.mvpmodule.util;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.mvp.mvpmodule.BuildConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/4/16.
 */

public class FileUtils {
    private static final String logPath = "log/";
    private static final String httpCache="/cache";

    public static String fileRootPath(Application application,String rootPath) {
        return getSDPath(application) != null ? (getSDPath(application) + rootPath) : null;
    }

    public static String getLogPath(Application context,String path) {
        return fileRootPath(context,path) + logPath;
    }
    public static String getCachePath(Application context,String path) {
        return fileRootPath(context,path) + httpCache;
    }
    /**
     * 若存在SD 则获取SD卡的路径 不存在则返回null
     */
    public static String getSDPath(Application application) {
        File sdDir = null;
        String path = null;
        //判断sd卡是否存在
        boolean sdCardExist = hasSDCard();
        if (sdCardExist) {
            //获取跟目录
            sdDir = Environment.getExternalStorageDirectory();
            path = sdDir.toString();
        } else {
            path = application.getFilesDir().getPath();
        }
        return path;
    }

    private static void init(Application application,String path) {
        if (isNotBlank(fileRootPath(application,path))) {
            File file = new File(fileRootPath(application,path));
            file.mkdirs();
        }
    }

    /**
     * 是否为空字符串
     *
     * @param value
     * @return
     */
    public static boolean isNotBlank(String value) {
        if (null == value || "".equals(value)) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否有SD卡
     */
    public static boolean hasSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 保存图片
     *
     * @param image
     */
    public static void saveImage(Bitmap image, String fileName, Application application,String path) {

        File f = new File(fileRootPath(application,path) + fileName);
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        try {
            f.createNewFile();
            FileOutputStream fOut = new FileOutputStream(f);

            Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
            if ("jpg".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix)) {
                compressFormat = Bitmap.CompressFormat.JPEG;
            } else if ("png".equalsIgnoreCase(suffix)) {
                compressFormat = Bitmap.CompressFormat.PNG;
            }

            image.compress(compressFormat, 100, fOut);

            fOut.flush();
            fOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断文件是否存在，存在则返回实际地址
     *
     * @param fileName
     * @return
     */
    public static String existFile(String fileName, Application application,String path) {
        init(application,path);
        File f = new File(fileRootPath(application,path) + fileName);
        if (f.exists()) {
            return f.getAbsolutePath();
        }
        return null;
    }

    /**
     * 随机生产文件名
     *
     * @return
     */
    public static String generateFileName() {
        return UUID.randomUUID().toString();
    }

    /*
     * 如果是文件 ==》直接删除
     * 如果是目录 ==》必须先删除里面每一层目录里的所有文件，最后才能删除外层的目录
     *              原因：不为空的话 删不了
     */
    public static void deleteFile(File file) {
        if (file.exists()) {//判断路径是否存在
            if (file.isFile()) {//boolean isFile():测试此抽象路径名表示的文件是否是一个标准文件。
                file.delete();
            } else {//不是文件，对于文件夹的操作
                //保存 路径D:/1/新建文件夹2  下的所有的文件和文件夹到listFiles数组中
                File[] listFiles = file.listFiles();//listFiles方法：返回file路径下所有文件和文件夹的绝对路径
                for (File file2 : listFiles) {
                    /*
                     * 递归作用：由外到内先一层一层删除里面的文件 再从最内层 反过来删除文件夹
                     *    注意：此时的文件夹在上一步的操作之后，里面的文件内容已全部删除
                     *         所以每一层的文件夹都是空的  ==》最后就可以直接删除了
                     */
                    deleteFile(file2);
                }
            }
            file.delete();
        } else {
            System.out.println("该file路径不存在！！");
        }
    }

    /**
     * 保存到文件中
     */
    public static void saveLogFile(String text, String date, Application application,String path) {
        try {
            String fileName = "app-log-" + date + ".txt";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File dir = new File(getLogPath(application,path));
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(getLogPath(application,path) + fileName, true);
                fos.write(date.getBytes());
                fos.write(" ".getBytes());
                fos.write(text.getBytes());
                fos.write("\r\n".getBytes());
                fos.close();
            }
        } catch (IOException e) {
            LogUtil.e("异常", "an error occured while writing file..." + e.toString(), BuildConfig.DEBUG);
        }
    }

    //获取文件夹下所有文件名
    public static List<String> getFileName(String path) {
        List<String> fileNameList = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) {//判断路径是否存在
            return null;
        }
        File[] files = f.listFiles();

        if (files == null) {//判断权限
            return null;
        }
        for (File _file : files) {//遍历目录
            if (_file.isFile() && _file.getName().endsWith(".txt")) {
                String _name = _file.getName();
                String fileName = _file.getName().substring(8, _name.indexOf(".txt"));//获取文件名
                fileNameList.add(fileName);
            }
        }
        return fileNameList;
    }

    //获取文件夹下所有文件内容
    public static List<String> getFileContent(String path, Context context) {
        List<String> fileContentList = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) {//判断路径是否存在
            return null;
        }
        File[] files = f.listFiles();

        if (files == null) {//判断权限
            return null;
        }
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try {
                    InputStreamReader read = new InputStreamReader(
                            new FileInputStream(file), "UTF-8");//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    StringBuilder sb=new StringBuilder();
                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        LogUtil.e("文件读取", "内容！！！！！！！！！！！！:" + lineTxt, BuildConfig.DEBUG);
                        sb.append(lineTxt+"/n");
                    }
                    fileContentList.add(sb.toString());
                    deleteFile(file);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileContentList;
    }
}
