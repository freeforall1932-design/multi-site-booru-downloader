package com.bisimplex.firebooru.view;
public interface IPageContentView {

    public abstract void cleanup();

    public abstract boolean isPlaying();

    public abstract void pause();

    public abstract void resume();

    public abstract void setFile(java.io.File p0, com.bisimplex.firebooru.danbooru.DanbooruPostContentType p1);

    public abstract void start();

    public abstract void stop();
}
