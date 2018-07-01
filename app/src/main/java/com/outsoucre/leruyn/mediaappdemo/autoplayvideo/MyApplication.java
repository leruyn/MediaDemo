package com.outsoucre.leruyn.mediaappdemo.autoplayvideo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.outsoucre.leruyn.mediaappdemo.player.JCVideoPlayerStandard;


/**
 * @author : Wells
 * @time : 16/9/5
 * @notes :
 */
public class MyApplication extends Application {
    public static MyApplication instance;
    public JCVideoPlayerStandard VideoPlaying;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public Context getAppContext() {
        return getApplicationContext();
    }


    static {
        loadLibrarys();
    }


    public static void loadLibrarys() {
        try {
                System.loadLibrary("ijkffmpeg");
                System.loadLibrary("ijkplayer");
                System.loadLibrary("ijksdl");
        } catch(UnsatisfiedLinkError e) {
            Log.e("loadLibrarys()", e.getMessage());
        }
    }


}
