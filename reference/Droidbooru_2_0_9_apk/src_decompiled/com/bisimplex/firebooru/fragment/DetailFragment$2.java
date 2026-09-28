package com.bisimplex.firebooru.fragment;
 class DetailFragment$2 implements androidx.appcompat.widget.Toolbar$OnMenuItemClickListener {
    final synthetic com.bisimplex.firebooru.fragment.DetailFragment this$0;

    DetailFragment$2(com.bisimplex.firebooru.fragment.DetailFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public boolean onMenuItemClick(android.view.MenuItem p3)
    {
        if (p3.getItemId() != 2131362105) {
            if (p3.getItemId() != 2131362483) {
                if (p3.getItemId() != 2131362184) {
                    if (p3.getItemId() != 2131362539) {
                        if (p3.getItemId() != 2131362010) {
                            if (p3.getItemId() != 2131362453) {
                                if (p3.getItemId() != 2131362372) {
                                    if (p3.getItemId() != 2131362525) {
                                        if (p3.getItemId() != 2131361921) {
                                            if (p3.getItemId() != 2131362712) {
                                                if (p3.getItemId() != 2131362167) {
                                                    if (p3.getItemId() == 2131361970) {
                                                        this.this$0.copyImageToClipBoard();
                                                    }
                                                } else {
                                                    com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$msendPostToHydrus(this.this$0);
                                                }
                                            } else {
                                                this.this$0.wallpaperIt();
                                            }
                                        } else {
                                            this.this$0.browseIt();
                                        }
                                    } else {
                                        this.this$0.shareIt();
                                    }
                                } else {
                                    this.this$0.toggleNotes();
                                    this.this$0.stopSlideshow();
                                }
                            } else {
                                com.bisimplex.firebooru.fragment.DetailFragment.-$$Nest$mreloadIt(this.this$0);
                            }
                        } else {
                            this.this$0.addToDownload();
                        }
                    } else {
                        this.this$0.toggleSlideshow();
                    }
                } else {
                    com.bisimplex.firebooru.fragment.DetailFragment v3_12 = this.this$0;
                    v3_12.showInfo(v3_12.getSource().getVisiblePost());
                }
            } else {
                this.this$0.saveIt();
            }
        } else {
            this.this$0.toogleFavorite();
        }
        return 1;
    }
}
