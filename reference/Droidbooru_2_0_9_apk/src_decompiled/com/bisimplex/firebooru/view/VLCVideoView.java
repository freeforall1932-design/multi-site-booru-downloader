package com.bisimplex.firebooru.view;
public class VLCVideoView extends android.widget.FrameLayout implements com.bisimplex.firebooru.view.IVideoView {
    private org.videolan.libvlc.Media currentMediaItem;
    private org.videolan.libvlc.MediaPlayer$EventListener currentPlayerListener;
    private org.videolan.libvlc.LibVLC mLibVLC;
    private org.videolan.libvlc.MediaPlayer mMediaPlayer;
    private org.videolan.libvlc.util.VLCVideoLayout mVideoLayout;

    public VLCVideoView(android.content.Context p2)
    {
        super(p2);
        super.mVideoLayout = 0;
        super.mLibVLC = 0;
        super.mMediaPlayer = 0;
        super.initializePlayer(p2);
        return;
    }

    public VLCVideoView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3, 0);
        super.mVideoLayout = 0;
        super.mLibVLC = 0;
        super.mMediaPlayer = 0;
        super.initializePlayer(p2);
        return;
    }

    public VLCVideoView(android.content.Context p2, android.util.AttributeSet p3, int p4)
    {
        super(p2, p3, p4, 0);
        super.mVideoLayout = 0;
        super.mLibVLC = 0;
        super.mMediaPlayer = 0;
        super.initializePlayer(p2);
        return;
    }

    public VLCVideoView(android.content.Context p1, android.util.AttributeSet p2, int p3, int p4)
    {
        super(p1, p2, p3, p4);
        super.mVideoLayout = 0;
        super.mLibVLC = 0;
        super.mMediaPlayer = 0;
        super.initializePlayer(p1);
        return;
    }

    private void initializePlayer(android.content.Context p1)
    {
        if (p1 != null) {
            this.prepareView(p1);
            return;
        } else {
            return;
        }
    }

    private void prepareView(android.content.Context p4)
    {
        if (this.mLibVLC == null) {
            org.videolan.libvlc.Media v0_8 = new java.util.ArrayList();
            v0_8.add("--input-repeat=500");
            v0_8.add("--avcodec-skip-frame");
            v0_8.add("2");
            v0_8.add("--avcodec-skip-idct");
            v0_8.add("2");
            v0_8.add("-vvv");
            this.mLibVLC = new org.videolan.libvlc.LibVLC(p4, v0_8);
        }
        if (this.mMediaPlayer == null) {
            this.mMediaPlayer = new org.videolan.libvlc.MediaPlayer(this.mLibVLC);
        }
        if (this.mVideoLayout == null) {
            this.mVideoLayout = new org.videolan.libvlc.util.VLCVideoLayout(p4);
            this.addView(this.mVideoLayout, new android.widget.FrameLayout$LayoutParams(-1, -1));
        }
        this.setKeepScreenOn(1);
        this.setMuted(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isVideoMuted());
        int v4_6 = this.currentMediaItem;
        if ((v4_6 != 0) && (!v4_6.isReleased())) {
            this.mMediaPlayer.setMedia(this.currentMediaItem);
            this.currentMediaItem.release();
            this.currentMediaItem = 0;
        }
        return;
    }

    private void setMediaItem(org.videolan.libvlc.Media p1)
    {
        this.currentMediaItem = p1;
        return;
    }

    public void addListener(org.videolan.libvlc.MediaPlayer$EventListener p2)
    {
        org.videolan.libvlc.MediaPlayer v0 = this.mMediaPlayer;
        if (v0 != null) {
            v0.setEventListener(p2);
            this.currentPlayerListener = p2;
        }
        return;
    }

    public void cleanup()
    {
        this.stop();
        this.release();
        return;
    }

    public android.widget.FrameLayout getAsLayout()
    {
        return this;
    }

    public long getCurrentPosition()
    {
        long v0_0 = this.mMediaPlayer;
        if (v0_0 != 0) {
            return v0_0.getTime();
        } else {
            return 0;
        }
    }

    public long getDuration()
    {
        long v0_0 = this.mMediaPlayer;
        if (v0_0 != 0) {
            return v0_0.getLength();
        } else {
            return 0;
        }
    }

    public boolean hasControls()
    {
        return 0;
    }

    public boolean isPlaying()
    {
        boolean v0_0 = this.mMediaPlayer;
        if (v0_0) {
            return v0_0.isPlaying();
        } else {
            return 0;
        }
    }

    public boolean isReleased()
    {
        boolean v0_0 = this.mMediaPlayer;
        if (v0_0) {
            return v0_0.isReleased();
        } else {
            return 1;
        }
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        android.util.Log.e("VLCPlayer", "onDetachedFromWindow reset video");
        this.reset();
        this.stop();
        return;
    }

    public void pause()
    {
        org.videolan.libvlc.MediaPlayer v0_0 = this.mMediaPlayer;
        if ((v0_0 != null) && (v0_0.isPlaying())) {
            this.mMediaPlayer.pause();
        }
        return;
    }

    public void release()
    {
        org.videolan.libvlc.Media v0_0 = this.mMediaPlayer;
        if ((v0_0 != null) && (!v0_0.isReleased())) {
            this.mMediaPlayer.detachViews();
            this.mMediaPlayer.release();
            this.mMediaPlayer = 0;
        }
        org.videolan.libvlc.Media v0_1 = this.mLibVLC;
        if ((v0_1 != null) && (!v0_1.isReleased())) {
            this.mLibVLC.release();
            this.mLibVLC = 0;
        }
        org.videolan.libvlc.Media v0_4 = this.currentMediaItem;
        if ((v0_4 != null) && (!v0_4.isReleased())) {
            this.currentMediaItem.release();
            this.currentMediaItem = 0;
        }
        return;
    }

    public void removeListener()
    {
        org.videolan.libvlc.MediaPlayer v0 = this.mMediaPlayer;
        if (v0 != null) {
            v0.setEventListener(0);
            this.currentPlayerListener = 0;
        }
        return;
    }

    public void reset()
    {
        this.removeListener();
        return;
    }

    public void resume()
    {
        org.videolan.libvlc.MediaPlayer v0_0 = this.mMediaPlayer;
        if ((v0_0 != null) && (!v0_0.isPlaying())) {
            this.mMediaPlayer.play();
        }
        return;
    }

    public void seekTo(long p3)
    {
        org.videolan.libvlc.MediaPlayer v0_0 = this.mMediaPlayer;
        if ((v0_0 != null) && (v0_0.isSeekable())) {
            this.mMediaPlayer.setTime(p3, 1);
        }
        return;
    }

    public void setFile(java.io.File p2, com.bisimplex.firebooru.danbooru.DanbooruPostContentType p3)
    {
        org.videolan.libvlc.Media v3_0 = this.mLibVLC;
        if ((v3_0 != null) && (!v3_0.isReleased())) {
            this.setMediaItem(new org.videolan.libvlc.Media(this.mLibVLC, android.net.Uri.fromFile(p2)));
        }
        return;
    }

    public void setMuted(boolean p2)
    {
        org.videolan.libvlc.MediaPlayer v0 = this.mMediaPlayer;
        if (v0 != null) {
            int v2_1;
            if (p2 == 0) {
                v2_1 = 100;
            } else {
                v2_1 = 0;
            }
            v0.setVolume(v2_1);
            return;
        } else {
            return;
        }
    }

    public void setURL(String p1, String p2, com.bisimplex.firebooru.danbooru.DanbooruPostContentType p3)
    {
        org.videolan.libvlc.Media v2_0 = this.mLibVLC;
        if ((v2_0 != null) && (!v2_0.isReleased())) {
            this.setMediaItem(new org.videolan.libvlc.Media(this.mLibVLC, android.net.Uri.parse(p1)));
        }
        return;
    }

    public void start()
    {
        if ((!this.mMediaPlayer.isPlaying()) && (this.currentMediaItem != null)) {
            this.mMediaPlayer.detachViews();
            this.mMediaPlayer.attachViews(this.mVideoLayout, 0, 1, 0);
            this.currentMediaItem.setHWDecoderEnabled(1, 0);
            this.mMediaPlayer.setMedia(this.currentMediaItem);
            this.currentMediaItem.release();
            this.currentMediaItem = 0;
            this.mMediaPlayer.play();
            this.setMuted(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isVideoMuted());
            return;
        } else {
            return;
        }
    }

    public void stop()
    {
        org.videolan.libvlc.MediaPlayer v0 = this.mMediaPlayer;
        if (v0 != null) {
            v0.stop();
        }
        return;
    }
}
