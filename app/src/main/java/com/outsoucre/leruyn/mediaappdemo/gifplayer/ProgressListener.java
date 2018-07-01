package com.outsoucre.leruyn.mediaappdemo.gifplayer;

/**
 * Created by LeRuyn on 6/28/2018.
 */
public interface ProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
