package com.bisimplex.firebooru.custom;
public interface DownloadTargetListener {

    public abstract void onLoadFailed(String p0);

    public abstract void onProgress(long p0, long p1);

    public abstract void onResourceReady(Object p0, com.bumptech.glide.request.transition.Transition p1, String p2, com.bisimplex.firebooru.danbooru.DanbooruPost p3, boolean p4);
}
