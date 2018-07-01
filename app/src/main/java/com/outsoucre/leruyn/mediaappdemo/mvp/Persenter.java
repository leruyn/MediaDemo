package com.outsoucre.leruyn.mediaappdemo.mvp;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.felipecsl.gifimageview.library.GifImageView;
import com.outsoucre.leruyn.mediaappdemo.R;
import com.outsoucre.leruyn.mediaappdemo.autoplayvideo.MyApplication;
import com.outsoucre.leruyn.mediaappdemo.player.JCMediaManager;
import com.outsoucre.leruyn.mediaappdemo.player.JCVideoPlayer;
import com.outsoucre.leruyn.mediaappdemo.player.JCVideoPlayerStandard;


/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class Persenter {


    public void autoPlayVideo(RecyclerView view) {
        int visibleCount = view.getChildCount();
        for (int i = 0; i < visibleCount; i++) {
            if (view.getChildAt(i) != null) {
                if (view.getChildAt(i).findViewById(R.id.videoplayer) != null) {

                    Rect rect = new Rect();
                    view.getChildAt(i).findViewById(R.id.videoplayer).getLocalVisibleRect(rect);
                    int itemheight = view.getChildAt(i).findViewById(R.id.videoplayer).getHeight();

                    if (rect.top == 0 && rect.bottom == itemheight) {
                        JCVideoPlayerStandard videoPlayerStandard1 = (JCVideoPlayerStandard) view.getChildAt(i).findViewById(R.id.videoplayer);
                        if (videoPlayerStandard1.currentState == JCVideoPlayer.CURRENT_STATE_NORMAL || videoPlayerStandard1.currentState == JCVideoPlayer.CURRENT_STATE_ERROR) {
                            videoPlayerStandard1.startButton.performClick();
                            MyApplication.instance.VideoPlaying = videoPlayerStandard1;
                            if(!JCMediaManager.instance().mediaPlayer.isPlaying()){
                                JCMediaManager.instance().mediaPlayer.start();
                                videoPlayerStandard1.changeUiToPlayingShow();
                            }
                        }
                        return;
                    }else {
                        JCVideoPlayerStandard videoPlayerStandard1 = (JCVideoPlayerStandard) view.getChildAt(i).findViewById(R.id.videoplayer);
                        if (videoPlayerStandard1.currentState == JCVideoPlayer.CURRENT_STATE_NORMAL || videoPlayerStandard1.currentState == JCVideoPlayer.CURRENT_STATE_ERROR) {
                            videoPlayerStandard1.startButton.performClick();
                            MyApplication.instance.VideoPlaying = videoPlayerStandard1;
                            if(JCMediaManager.instance().mediaPlayer.isPlaying()){
                                JCMediaManager.instance().mediaPlayer.pause();
                                videoPlayerStandard1.changeUiToPauseShow();
                            }
                        }
                    }
                }

                if (view.getChildAt(i).findViewById(R.id.image_view) != null) {
                    Rect rect = new Rect();
                    view.getChildAt(i).findViewById(R.id.image_view).getLocalVisibleRect(rect);
                    int itemheight = view.getChildAt(i).findViewById(R.id.image_view).getHeight();
                    if (rect.top == 0 && rect.bottom == itemheight) {
                        GifImageView gifImageView = view.getChildAt(i).findViewById(R.id.image_view);
                        gifImageView.startAnimation();
                    } else {
                        GifImageView gifImageView = view.getChildAt(i).findViewById(R.id.image_view);
                        if (gifImageView.isAnimating())
                            gifImageView.stopAnimation();
                    }

                }
            }
        }
    }


    public void autoPlayGif(RecyclerView view) {
        int visibleCount = view.getChildCount();
        for (int i = 0; i < visibleCount; i++) {
            if (view.getChildAt(i) != null && view.getChildAt(i).findViewById(R.id.image_view) != null) {
                Rect rect = new Rect();
                view.getChildAt(i).findViewById(R.id.image_view).getLocalVisibleRect(rect);
                int itemheight = view.getChildAt(i).findViewById(R.id.image_view).getHeight();

                if (rect.top == 0 && rect.bottom == itemheight) {
                    ImageView imageView = (ImageView) view.getChildAt(i).findViewById(R.id.image_view);

                    return;
                }
            }
        }
    }


}
