package com.bisimplex.firebooru.view;
public class ExoVideoView extends android.widget.FrameLayout implements com.bisimplex.firebooru.view.IVideoView {
    private androidx.media3.common.MediaItem currentMediaItem;
    private androidx.media3.common.Player$Listener currentPlayerListener;
    private androidx.media3.exoplayer.ExoPlayer player;
    private androidx.media3.ui.PlayerView playerView;
    private boolean released;

    public ExoVideoView(android.content.Context p2)
    {
        super(p2);
        super.released = 0;
        super.initializePlayer(p2);
        return;
    }

    public ExoVideoView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3, 0);
        super.released = 0;
        super.initializePlayer(p2);
        return;
    }

    public ExoVideoView(android.content.Context p2, android.util.AttributeSet p3, int p4)
    {
        super(p2, p3, p4, 0);
        super.released = 0;
        super.initializePlayer(p2);
        return;
    }

    public ExoVideoView(android.content.Context p1, android.util.AttributeSet p2, int p3, int p4)
    {
        super(p1, p2, p3, p4);
        super.released = 0;
        super.initializePlayer(p1);
        return;
    }

    private void initializePlayer(android.content.Context p4)
    {
        if (p4 != null) {
            if (this.player == null) {
                this.player = new androidx.media3.exoplayer.ExoPlayer$Builder(p4).build();
            }
            if (this.playerView == null) {
                this.playerView = new androidx.media3.ui.PlayerView(p4);
                this.player.addAnalyticsListener(new androidx.media3.exoplayer.util.EventLogger());
                this.addView(this.playerView, new android.widget.FrameLayout$LayoutParams(-1, -1));
            }
            this.setKeepScreenOn(1);
            this.player.setRepeatMode(1);
            this.player.setPlayWhenReady(0);
            this.playerView.setPlayer(this.player);
            this.playerView.setUseController(0);
            this.playerView.setShowPreviousButton(0);
            this.playerView.setShowRewindButton(0);
            this.playerView.setShowFastForwardButton(0);
            this.playerView.setShowBuffering(2);
            this.playerView.setArtworkDisplayMode(0);
            this.setMuted(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isVideoMuted());
            this.setKeepScreenOn(1);
            androidx.media3.exoplayer.ExoPlayer v4_5 = this.currentMediaItem;
            if (v4_5 != null) {
                this.player.setMediaItem(v4_5);
                this.player.prepare();
            }
        }
        return;
    }

    private void setMediaItem(androidx.media3.common.MediaItem p1)
    {
        this.currentMediaItem = p1;
        return;
    }

    public void addListener(androidx.media3.common.Player$Listener p2)
    {
        androidx.media3.exoplayer.ExoPlayer v0 = this.player;
        if (v0 != null) {
            v0.addListener(p2);
            this.currentPlayerListener = p2;
        }
        return;
    }

    public void cleanup()
    {
        if (!this.released) {
            int v0_2 = this.player;
            if (v0_2 != 0) {
                v0_2.stop();
                this.player.release();
                this.player = 0;
            }
            int v0_4 = this.playerView;
            if (v0_4 != 0) {
                this.removeView(v0_4);
                this.playerView = 0;
            }
            this.currentMediaItem = 0;
            this.released = 1;
        }
        return;
    }

    public android.widget.FrameLayout getAsLayout()
    {
        return this;
    }

    public long getCurrentPosition()
    {
        long v0_0 = this.player;
        if (v0_0 != 0) {
            return v0_0.getCurrentPosition();
        } else {
            return 0;
        }
    }

    public long getDuration()
    {
        long v0_0 = this.player;
        if (v0_0 != 0) {
            return v0_0.getDuration();
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
        boolean v0_0 = this.player;
        if (v0_0) {
            return v0_0.isPlaying();
        } else {
            return 0;
        }
    }

    public boolean isReleased()
    {
        return this.released;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        android.util.Log.e("ExoPlayer", "onDetachedFromWindow reset video");
        this.reset();
        return;
    }

    public void pause()
    {
        androidx.media3.exoplayer.ExoPlayer v0_0 = this.player;
        if ((v0_0 != null) && (v0_0.isPlaying())) {
            this.player.pause();
        }
        return;
    }

    public void release()
    {
        int v0_0 = this.player;
        if (v0_0 != 0) {
            v0_0.release();
            this.player = 0;
            this.released = 1;
            return;
        } else {
            return;
        }
    }

    public void removeListener()
    {
        androidx.media3.exoplayer.ExoPlayer v0 = this.player;
        if (v0 != null) {
            androidx.media3.common.Player$Listener v1 = this.currentPlayerListener;
            if (v1 != null) {
                v0.removeListener(v1);
            }
        }
        return;
    }

    public void reset()
    {
        int v0_0 = this.player;
        if (v0_0 != 0) {
            v0_0.stop();
            this.removeListener();
            this.player.clearMediaItems();
            this.player.prepare();
        }
        this.currentMediaItem = 0;
        this.setVisibility(4);
        return;
    }

    public void resume()
    {
        this.start();
        return;
    }

    public void seekTo(long p2)
    {
        androidx.media3.exoplayer.ExoPlayer v0 = this.player;
        if (v0 != null) {
            v0.seekTo(p2);
            return;
        } else {
            return;
        }
    }

    public void setFile(java.io.File p1, com.bisimplex.firebooru.danbooru.DanbooruPostContentType p2)
    {
        this.setMediaItem(androidx.media3.common.MediaItem.fromUri(android.net.Uri.fromFile(p1)));
        return;
    }

    public void setMuted(boolean p2)
    {
        androidx.media3.exoplayer.ExoPlayer v0 = this.player;
        if (v0 != null) {
            int v2_1;
            if (p2 == 0) {
                v2_1 = 1065353216;
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
        this.setMediaItem(androidx.media3.common.MediaItem.fromUri(p1));
        return;
    }

    public void start()
    {
        androidx.media3.exoplayer.ExoPlayer v0_0 = this.player;
        if ((v0_0 != null) && ((!v0_0.isPlaying()) && (this.currentMediaItem != null))) {
            if (this.playerView != null) {
                androidx.media3.exoplayer.ExoPlayer v0_16 = this.player;
                if (v0_16 != null) {
                    if (v0_16.getMediaItemCount() <= 0) {
                        this.player.setMediaItem(this.currentMediaItem, 1);
                        this.player.setPlayWhenReady(1);
                        this.player.prepare();
                        return;
                    } else {
                        androidx.media3.common.MediaItem v2_2 = this.currentMediaItem;
                        if (this.player.getMediaItemAt(0) != v2_2) {
                            this.player.setMediaItem(v2_2, 1);
                            this.player.setPlayWhenReady(1);
                            this.player.prepare();
                            return;
                        } else {
                            this.player.play();
                            return;
                        }
                    }
                }
            }
            this.initializePlayer(this.getContext());
            this.player.play();
            return;
        } else {
            return;
        }
    }

    public void stop()
    {
        androidx.media3.exoplayer.ExoPlayer v0 = this.player;
        if (v0 != null) {
            v0.stop();
        }
        return;
    }
}
