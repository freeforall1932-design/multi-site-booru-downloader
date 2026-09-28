package com.bisimplex.firebooru.fragment;
 class DetailFragment$4 implements com.bisimplex.firebooru.fragment.DetailPagerAdapter$DetailPageViewListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;

    public static synthetic void $r8$lambda$E_pQHGq62WIsNpLEUxgWsQTv86E(com.bisimplex.firebooru.fragment.DetailFragment$4 p0, android.view.View p1)
    {
        p0.lambda$loadFailed$0(p1);
        return;
    }

    DetailFragment$4(com.bisimplex.firebooru.fragment.DetailFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    private synthetic void lambda$loadFailed$0(android.view.View p1)
    {
        this.this$0.reloadSelectedImage();
        return;
    }

    public boolean executeGesture(com.bisimplex.firebooru.view.GestureType p3)
    {
        if (p3 != com.bisimplex.firebooru.view.GestureType.None) {
            com.bisimplex.firebooru.model.ViewerCommandType v3_2;
            com.bisimplex.firebooru.model.ViewerCommandType v3_7 = com.bisimplex.firebooru.fragment.DetailFragment$23.$SwitchMap$com$bisimplex$firebooru$view$GestureType[p3.ordinal()];
            if (v3_7 == 1) {
                v3_2 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getDoubleTapCommand();
            } else {
                if (v3_7 == 2) {
                    v3_2 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSwipeUpCommand();
                } else {
                    if (v3_7 == 3) {
                        v3_2 = com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getSwipeDownCommand();
                    } else {
                        return 0;
                    }
                }
            }
            return com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$mexecuteCommand(this.this$0, v3_2);
        } else {
            return 0;
        }
    }

    public void loadFailed(int p2, String p3)
    {
        if ((this.this$0.getContext() != null) && (this.this$0.pager.getCurrentItem() == p2)) {
            com.google.android.material.snackbar.Snackbar v2_1 = this.this$0.adapter.getItem(p2);
            if (v2_1 != null) {
                com.google.android.material.snackbar.Snackbar v2_2 = v2_1.getPost();
                if ((v2_2 == null) || (v2_2.getFile() == v2_2.getVisibleVersion())) {
                    com.google.android.material.snackbar.Snackbar v2_4 = this.this$0.getView();
                    if (v2_4 != null) {
                        com.google.android.material.snackbar.Snackbar v2_5 = com.google.android.material.snackbar.Snackbar.make(v2_4, 2131886493, 0);
                        v2_5.setAction(2131887093, new com.bisimplex.firebooru.fragment.DetailFragment$4$$ExternalSyntheticLambda0(this));
                        android.view.View v3_8 = this.this$0.getSnackBarAnchorView();
                        if (v3_8 != null) {
                            v2_5.setAnchorView(v3_8);
                        }
                        v2_5.show();
                    }
                } else {
                    v2_2.setEnforceOriginalImage(1);
                    this.this$0.reloadSelectedImage();
                    return;
                }
            }
        }
        return;
    }

    public boolean onLongClick(android.view.View p3)
    {
        if (this.this$0.getContext() != null) {
            this.this$0.toggleFullScreen();
            if (!(p3 instanceof com.bisimplex.firebooru.view.IVideoView)) {
                p3.performHapticFeedback(0);
            }
            return 1;
        } else {
            return 0;
        }
    }

    public void pageViewLoaded(int p6, com.bisimplex.firebooru.view.BooruPhotoView p7, com.bisimplex.firebooru.danbooru.DanbooruPost p8, long p9)
    {
        if ((this.this$0.getContext() != null) && (p8 != null)) {
            com.bisimplex.firebooru.network.SourcePostBasic v0_3 = this.this$0.getSource();
            if (v0_3 != null) {
                com.bisimplex.firebooru.network.SourceType v1_1 = this.this$0.adapter.getItem(p6);
                int v2_1 = v0_3.getProvider(v1_1.getPost());
                com.bisimplex.firebooru.network.SourceType v3_1 = v2_1.getServerDescription().getType();
                if ((v1_1 == null) || ((!v1_1.isShowNotes()) || ((!p8.getHas_notes()) || ((v3_1 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeDanbooru) || (v3_1 == com.bisimplex.firebooru.danbooru.ServerItemType.ServerItemTypeE621))))) {
                    p7.setNotesVisible(0);
                } else {
                    if ((v0_3.getType() != com.bisimplex.firebooru.network.SourceType.Post) && (v0_3.getType() != com.bisimplex.firebooru.network.SourceType.MultiPost)) {
                        v2_1 = 0;
                    }
                    p7.showNotes(p8, v2_1);
                }
                if ((p8.isFavorite()) && (p8.getVisibleVersion().getErrorLoadCount() > 0)) {
                    p8.getVisibleVersion().setErrorLoadCount(0);
                    com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().updateFavoriteItem(p8);
                }
                if ((v0_3.getVisiblePostIndex() == p6) && (!p8.getVisibleVersion().isVideo())) {
                    com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$mtriggerSlideshowTick(this.this$0, p9);
                }
            }
        }
        return;
    }

    public void postFinishedDownload(int p1, java.io.File p2)
    {
        return;
    }

    public void seekingChange(boolean p1)
    {
        return;
    }

    public void showBlacklisted(int p2)
    {
        com.bisimplex.firebooru.fragment.DetailFragment v2_2 = this.this$0.adapter.getItem(p2);
        if ((v2_2 != null) && (v2_2.getPost() != null)) {
            v2_2.getPost().setBlacklisted(0);
            this.this$0.reloadSelectedImage();
            return;
        } else {
            return;
        }
    }

    public void tapOnNote(com.bisimplex.firebooru.danbooru.NoteItem p3)
    {
        if ((this.this$0.getContext() != null) && (p3 != null)) {
            this.this$0.showMessage(p3.getBody(), com.bisimplex.firebooru.activity.MessageType.Info);
        }
        return;
    }

    public void togglePlayVideo(int p1, boolean p2)
    {
        return;
    }

    public void videoChangeTime(int p1, com.bisimplex.firebooru.view.IVideoView p2, long p3, long p5)
    {
        if ((this.this$0.getContext() != null) && ((this.this$0.pager.getCurrentItem() == p1) && (p2.isPlaying()))) {
            com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fputvideoTime(this.this$0, p3);
        }
        return;
    }

    public void videoError(int p4, com.bisimplex.firebooru.view.IVideoView p5, String p6, com.bisimplex.firebooru.view.VideoErrorType p7)
    {
        if (this.this$0.getContext() != null) {
            com.bisimplex.firebooru.data.DanbooruPostPage v0_4 = this.this$0.adapter.getItem(p4);
            if (v0_4 != null) {
                com.bisimplex.firebooru.danbooru.DanbooruPostImage v1_1 = v0_4.getPost().getVisibleVersion();
                if ((com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().streamVideo()) && ((p4 == this.this$0.pager.getCurrentItem()) && (p7 != com.bisimplex.firebooru.view.VideoErrorType.Renderer))) {
                    if (!v1_1.isMP4()) {
                        if (v1_1.isWebM()) {
                            v1_1.setUrlExtension("gif");
                            this.this$0.reloadSelectedImage();
                            return;
                        }
                    } else {
                        v1_1.setUrlExtension("webm");
                        p5.setURL(v1_1.getUrl(), v0_4.getPost().getPostUrl(), v1_1.getContentType());
                        return;
                    }
                }
                if (!android.text.TextUtils.isEmpty(p6)) {
                    this.this$0.showMessage(p6, com.bisimplex.firebooru.activity.MessageType.Error);
                    return;
                } else {
                    com.bisimplex.firebooru.fragment.DetailFragment v4_6 = this.this$0;
                    v4_6.showMessage(v4_6.getString(2131886509, new Object[] {v1_1.getUrl()})), com.bisimplex.firebooru.activity.MessageType.Error);
                    return;
                }
            }
        }
        return;
    }

    public void videoIsPreparedToPlay(int p5, com.bisimplex.firebooru.view.IVideoView p6)
    {
        if ((this.this$0.getContext() != null) && (this.this$0.pager.getCurrentItem() == p5)) {
            if ((p5 == com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fgetvideoIndex(this.this$0)) && (com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fgetvideoTime(this.this$0) > 0)) {
                p6.seekTo(com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fgetvideoTime(this.this$0));
                com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fputvideoIndex(this.this$0, -1);
                com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fputvideoTime(this.this$0, 0);
            }
            p6.resume();
        }
        return;
    }

    public void videoStartedPlaying(int p3, com.bisimplex.firebooru.view.IVideoView p4, android.view.ViewGroup p5)
    {
        if ((this.this$0.getContext() != null) && (this.this$0.pager.getCurrentItem() == p3)) {
            com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fputvideoIndex(this.this$0, p3);
            if (!p4.hasControls()) {
                if (!com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$misToolBarVisible(this.this$0)) {
                    p5.setVisibility(8);
                } else {
                    p5.setVisibility(0);
                }
            }
            if (com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$fgetslideshowEnabled(this.this$0)) {
                com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$mtriggerSlideshowTick(this.this$0, (p4.getDuration() + 1000));
            }
        }
        return;
    }
}
