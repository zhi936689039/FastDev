package com.util;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;


/**
 * Created by Administrator on 2017/4/16.
 */

public class FileUtils {
    public static String fileRootPath(Application application){
        return getSDPath(application) != null ? (getSDPath(application) + "/RenRenDa/") : null;
    }
    /**若存在SD 则获取SD卡的路径 不存在则返回null*/
    public static String getSDPath(Application application){
        File sdDir = null;
        String path = null;
        //判断sd卡是否存在
        boolean sdCardExist = hasSDCard();
        if (sdCardExist) {
            //获取跟目录
            sdDir = Environment.getExternalStorageDirectory();
            path = sdDir.toString();
        }else{
            path= application.getFilesDir().getPath();
        }
        return path;
    }

    private static void init(Application application){
        if(isNotBlank(fileRootPath(application))){
            File file=new File(fileRootPath(application));
            file.mkdirs();
        }
    }
    /**
     * 是否为空字符串
     * @param value
     * @return
     */
    public static boolean isNotBlank(String value){
        if(null==value||"".equals(value)){
            return false;
        }
        return true;
    }

    /**判断是否有SD卡*/
    public static boolean hasSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 保存图片
     * @param image
     */
    public static void saveImage(Bitmap image,String fileName,Application application){

        File f = new File(fileRootPath(application)+fileName);
        String suffix=fileName.substring(fileName.lastIndexOf(".") + 1);
        try {
            f.createNewFile();
            FileOutputStream fOut = new FileOutputStream(f);

            Bitmap.CompressFormat compressFormat=Bitmap.CompressFormat.JPEG;
            if("jpg".equalsIgnoreCase(suffix)||"jpeg".equalsIgnoreCase(suffix)){
                compressFormat=Bitmap.CompressFormat.JPEG;
            }else if("png".equalsIgnoreCase(suffix)){
                compressFormat=Bitmap.CompressFormat.PNG;
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
     * @param fileName
     * @return
     */
    public static String existFile(String fileName,Application application){
        init(application);
        File f = new File(fileRootPath(application)+fileName);
        if(f.exists()){
            return f.getAbsolutePath();
        }
        return null;
    }

    /**
     * 文件下载
     * @param url          下载地址
     * @param context
     * @param path          文件保存路径
     * @param fileName       文件名.后缀
     */
    public static void downloadFile(String url, Context context, String path, String fileName, FileDownloadCallBack fileDownloadCallBack){
        OkGo.<File>get(url).tag(context).execute(new FileCallback(
                path,  fileName) {
            @Override
            public void onSuccess(Response<File> response) {
                fileDownloadCallBack.onSuccess();
            }

            @Override
            public void downloadProgress(Progress progress) {
                fileDownloadCallBack.onProgress(progress);
            }

            @Override
            public void onError(Response<File> response) {
                super.onError(response);
                fileDownloadCallBack.onFail();

            }
        });
    }
    public interface FileDownloadCallBack{
        void onSuccess();
        void onFail();
        void onProgress(Progress progress);
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
        if(file.exists()) {//判断路径是否存在
            if(file.isFile()){//boolean isFile():测试此抽象路径名表示的文件是否是一个标准文件。
                file.delete();
            }else{//不是文件，对于文件夹的操作
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
        }else {
            System.out.println("该file路径不存在！！");
        }
    }
}
