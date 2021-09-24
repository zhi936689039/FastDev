package com.mvp.mvpmodule.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import java.io.IOException;


/**
 * Created by intexh on 2016/9/1.
 * 播放声音
 */
public enum PlayerUtil {
    INSTANCE;


    /**
     * 播放震动500毫秒
     */
    public void playerVibrator(Context context){
        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
//                long [] pattern = {100,400,100,400}; // 停止 开启 停止 开启
        vibrator.vibrate(-1); //重复两次上面的pattern 如果只想震动一次，index设为-1
    }


    /**
     * 播放assets 中声音文件
     * @param assetsMusicFileName
     */
    public void playMusic(String assetsMusicFileName,Context context) {
        try {
            AssetManager assetManager =context.getAssets();
            AssetFileDescriptor afd = assetManager.openFd(assetsMusicFileName);
            AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            //听筒模式下设置为false
            am.setSpeakerphoneOn(true);
            //设置成听筒模式
            am.setMode(AudioManager.MODE_NORMAL);
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(afd.getFileDescriptor(),
                    afd.getStartOffset(), afd.getLength());
            mediaPlayer.setLooping(false);//循环播放

            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 播放系统声音
     */
    public void playSystemMusic(Context context){
        MediaPlayer mediaPlayer = MediaPlayer.create(context, getSystemDefultRingtoneUri(context));
        mediaPlayer.setLooping(false);
        try {
            mediaPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    //获取系统默认铃声的Uri
    private Uri getSystemDefultRingtoneUri(Context context) {
        return RingtoneManager.getActualDefaultRingtoneUri(context,
                RingtoneManager.TYPE_NOTIFICATION);
    }
}
