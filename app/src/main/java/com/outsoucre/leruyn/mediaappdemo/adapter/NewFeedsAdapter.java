package com.outsoucre.leruyn.mediaappdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.felipecsl.gifimageview.library.GifImageView;
import com.outsoucre.leruyn.mediaappdemo.R;
import com.outsoucre.leruyn.mediaappdemo.gifplayer.GifDataDownloader;
import com.outsoucre.leruyn.mediaappdemo.gifplayer.ProgressListener;
import com.outsoucre.leruyn.mediaappdemo.mvp.ProgressResponseBody;
import com.outsoucre.leruyn.mediaappdemo.player.JCVideoPlayer;
import com.outsoucre.leruyn.mediaappdemo.player.JCVideoPlayerStandard;
import com.outsoucre.leruyn.mediaappdemo.utils.UiUtil;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import static android.support.constraint.Constraints.TAG;


/**
 * yangchao
 * 2016/8/30
 */
public class NewFeedsAdapter extends RecyclerView.Adapter<NewFeedsAdapter.MyHodler> {

    private static final int TYPE_PIC = 1;//图片
    private static final int TYPE_VIDEO = 2;//视频

    private int[] data;
    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public NewFeedsAdapter(Context context, Object tag, int[] data) {
        this.data = data;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemViewType(int position) {
        if (data[position] == 0) {
            return TYPE_PIC;
        } else {
            return TYPE_VIDEO;
        }
    }

    @Override
    public MyHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        MyHodler holder;
        if (viewType == TYPE_PIC) {
            view = mLayoutInflater.inflate(R.layout.item_image, parent, false);
            holder = new ImageHolder(view);
        } else {
            view = mLayoutInflater.inflate(R.layout.item_video, parent, false);
            holder = new VideoHolder(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHodler holder, int position) {

        holder.update();
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class MyHodler extends RecyclerView.ViewHolder {

        public MyHodler(View itemView) {
            super(itemView);

        }

        void update() {

        }

        ;
    }


    class VideoHolder extends MyHodler {
        JCVideoPlayerStandard mVideoPlayer;

        public VideoHolder(View itemView) {
            super(itemView);

            mVideoPlayer = (JCVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
            ViewGroup.LayoutParams para = mVideoPlayer.getLayoutParams();
            para.width = UiUtil.getScreenWidth(false);
            para.height = para.width * 9 / 12;
            mVideoPlayer.setLayoutParams(para);
            mVideoPlayer.setMute(true);
        }

        @Override
        void update() {
            super.update();

//            mVideoPlayer.setUp(
//                    "http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4",
//                    JCVideoPlayer.SCREEN_LAYOUT_LIST,
//                    "");
//            Glide.with(itemView.getContext()).load(
//                    "http://a4.att.hudong.com/05/71/01300000057455120185716259013.jpg").into(mVideoPlayer.thumbImageView);
//            https://videodown.zerotech.com/out480/A40ba819eca57c15b4175d7da1fe2d203.mp4?OSSAccessKeyId=9NcSgCCVfxXAbsr6&Expires=1522896893&Signature=yqRUCS%2FJEw1T%2BE4b%2FfYsrJJr3RA%3D
//            http://video.gedoushijie.com/XvLp9nkAirHvoVf0lEKdvlIdaQI=/lqH7C1Unckgfwj4WuvtM15DMItEA
//            https://hgskylinetest.oss-cn-shenzhen.aliyuncs.com/shares/videos/628/I2059696277cef3fa90d7b1667ec67daf.mp4?Expires=1522837159&OSSAccessKeyId=LTAIfgMs2rTpuZgY&Signature=TVth2doY%2Bf86NB4ogBUgEaNmn7I%3D
            mVideoPlayer.setUp(
                    "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
                    JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    "");
            Glide.with(itemView.getContext()).load(
                    "https://videodown.zerotech.com/outimg/A40ba819eca57c15b4175d7da1fe2d203.jpg%21Avatar_600?OSSAccessKeyId=9NcSgCCVfxXAbsr6&Expires=1522891554&Signature=GWVAMVt604A1L6J5XA6PtKT13us%3D").into(mVideoPlayer.thumbImageView);


        }
    }

    class ImageHolder extends MyHodler {

        private GifImageView image_view;
        private ImageView no_image;
        private ProgressListener progressListener;
        //        private CircleProgressBar mLineProgressBar;
        private OkHttpClient mOkHttpClient;

        //        private Handler handler;
        public ImageHolder(View itemView) {
            super(itemView);

            image_view = (GifImageView) itemView.findViewById(R.id.image_view);
            no_image = (ImageView) itemView.findViewById(R.id.no_image);
//            mLineProgressBar = (CircleProgressBar) itemView.findViewById(R.id.line_progress);
        }

        @Override
        void update() {
            super.update();

            mOkHttpClient = new OkHttpClient();
//            handler = new Handler();

//            progressListener = new ProgressListener() {
//                @Override
//                public void update(long bytesRead, long contentLength, boolean done) {
//                    int progress = (int) ((100 * bytesRead) / contentLength);
//                    mLineProgressBar.setVisibility(View.VISIBLE);
//                    // Enable if you want to see the progress with logcat
//                    // Log.v(LOG_TAG, "Progress: " + progress + "%");
//                    mLineProgressBar.setProgress(progress);
//                    if (done) {
//                        Log.i("GifActivity", "Done loading");
////                    mLineProgressBar.setVisibility(View.GONE);
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                //Do something after 100ms
//                                mLineProgressBar.setVisibility(View.GONE);
//                            }
//                        }, 500);
//                    }
//                }
//
//
//            };

            mOkHttpClient.networkInterceptors().add(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                            .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                            .build();
                }
            });

//            Glide.get(mContext)
//                    .register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(mOkHttpClient));
//            Glide.with(mContext)
//                    .load("https://media.giphy.com/media/gw3IWyGkC0rsazTi/giphy.gif")
//                    // Disabling cache to see download progress with every app load
//                    // You may want to enable caching again in production
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .into(image_view);

            new GifDataDownloader() {
                @Override
                protected void onPostExecute(final byte[] bytes) {
                    image_view.setBytes(bytes);
                    image_view.startAnimation();
                    no_image.setVisibility(View.GONE);
                    Log.d(TAG, "GIF width is " + image_view.getGifWidth());
                    Log.d(TAG, "GIF height is " + image_view.getGifHeight());
                }


            }.execute("http://katemobile.ru/tmp/sample3.gif");
        }
    }


}
