package com.bisimplex.firebooru.custom;
public class VideoPrepareListener implements android.media.MediaPlayer$OnPreparedListener {
    private com.bisimplex.firebooru.custom.VideoPrepareListener$OnVideoPreparedListener mListener;
    private int videoIndex;

    public VideoPrepareListener(int p1, com.bisimplex.firebooru.custom.VideoPrepareListener$OnVideoPreparedListener p2)
    {
        this.videoIndex = p1;
        this.mListener = p2;
        return;
    }

    public void onPrepared(android.media.MediaPlayer p3)
    {
        if (p3 != null) {
            com.bisimplex.firebooru.custom.VideoPrepareListener$OnVideoPreparedListener v0 = this.mListener;
            if (v0 != null) {
                v0.onPrepared(p3, this.videoIndex);
            }
        }
        return;
    }
}
