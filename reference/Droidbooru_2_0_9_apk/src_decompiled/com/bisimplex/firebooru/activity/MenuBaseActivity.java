package com.bisimplex.firebooru.activity;
public abstract class MenuBaseActivity extends com.bisimplex.firebooru.activity.SkeletonActivity implements com.bisimplex.firebooru.view.TagMenuDialog$TagMenuDialogListener, com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagMenuDialogListener, com.bisimplex.firebooru.services.BooruTagListener {
    private androidx.appcompat.app.ActionBarDrawerToggle actionBarDrawerToggle;
    private boolean drawerEnabled;
    android.view.animation.Animation fadeOut;
    private int mTitleRes;
    protected com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView menuDrawer;
    private androidx.drawerlayout.widget.DrawerLayout root;
    protected com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView secondaryDrawer;
    private Object secondaryMenuData;
    private com.bisimplex.firebooru.custom.SecondaryMenuType secondaryMenuType;

    public static synthetic Boolean $r8$lambda$CzfYfy5riXw9HfyKGb1EVIjZvq8(com.bisimplex.firebooru.activity.MenuBaseActivity p0, android.view.View p1, com.mikepenz.materialdrawer.model.interfaces.IDrawerItem p2, Integer p3)
    {
        return p0.lambda$onCreate$2(p1, p2, p3);
    }

    public static synthetic Boolean $r8$lambda$HPgksniA2YRaugrjZf360sKoX1I(com.bisimplex.firebooru.activity.MenuBaseActivity p0, android.view.View p1, com.mikepenz.materialdrawer.model.interfaces.IDrawerItem p2, Integer p3)
    {
        return p0.lambda$onCreate$0(p1, p2, p3);
    }

    public static synthetic Boolean $r8$lambda$ZYEreDrYbAq4aZlpZXk5eJW3x2w(com.bisimplex.firebooru.activity.MenuBaseActivity p0, android.view.View p1, com.mikepenz.materialdrawer.model.interfaces.IDrawerItem p2, Integer p3)
    {
        return p0.lambda$onCreate$1(p1, p2, p3);
    }

    public static synthetic void $r8$lambda$ssffw6g52IHrMxdAaIITp07jqkI(com.bisimplex.firebooru.activity.MenuBaseActivity p0, com.bisimplex.firebooru.danbooru.DanbooruPost p1)
    {
        p0.lambda$finishedLoading$3(p1);
        return;
    }

    static bridge synthetic void -$$Nest$mMainShowMessage(com.bisimplex.firebooru.activity.MenuBaseActivity p0, String p1, com.bisimplex.firebooru.activity.MessageType p2)
    {
        p0.MainShowMessage(p1, p2);
        return;
    }

    static bridge synthetic androidx.drawerlayout.widget.DrawerLayout -$$Nest$mgetDrawerLayout(com.bisimplex.firebooru.activity.MenuBaseActivity p0)
    {
        return p0.getDrawerLayout();
    }

    public MenuBaseActivity(int p2)
    {
        this.actionBarDrawerToggle = 0;
        this.mTitleRes = p2;
        return;
    }

    private void MainShowMessage(String p4, com.bisimplex.firebooru.activity.MessageType p5)
    {
        this.HideLoading();
        com.bisimplex.firebooru.view.CustomDialog v0_4 = com.bisimplex.firebooru.activity.MenuBaseActivity$41.$SwitchMap$com$bisimplex$firebooru$activity$MessageType[p5.ordinal()];
        if ((v0_4 == 1) || (v0_4 == 2)) {
            if (!this.isPaused()) {
                com.bisimplex.firebooru.view.CustomDialog v0_3 = new com.bisimplex.firebooru.view.CustomDialog();
                int v1_1 = new android.os.Bundle();
                v1_1.putInt(com.bisimplex.firebooru.view.CustomDialog.TYPE_KEY, com.bisimplex.firebooru.activity.MessageType.TypeToInt(p5));
                v1_1.putString(com.bisimplex.firebooru.view.CustomDialog.MESSAGE_KEY, p4);
                v0_3.setArguments(v1_1);
                v0_3.show(this.getSupportFragmentManager(), "AlertDialog");
                return;
            } else {
                return;
            }
        } else {
            if (v0_4 == 3) {
                this.initLoading(p4);
                return;
            } else {
                if (v0_4 == 4) {
                    this.initSnackbar(p4, 0, com.bisimplex.firebooru.skin.SkinManager.getInstance().getSuccessTextColorRes());
                    return;
                } else {
                    if (v0_4 == 5) {
                        this.initSnackbar(p4, 0, 0);
                        return;
                    } else {
                        this.initSnackbar(p4, 0, 0);
                        return;
                    }
                }
            }
        }
    }

    private void addTagsToMenu(java.util.List p6)
    {
        java.util.Iterator v6_1 = p6.iterator();
        while (v6_1.hasNext()) {
            android.content.res.ColorStateList v0_6 = ((com.bisimplex.firebooru.danbooru.TagItem) v6_1.next());
            com.mikepenz.materialdrawer.model.PrimaryDrawerItem v1_0 = new com.mikepenz.materialdrawer.model.PrimaryDrawerItem();
            v1_0.setName(new com.mikepenz.materialdrawer.holder.StringHolder(v0_6.getName()));
            v1_0.setTag(com.bisimplex.firebooru.custom.SecondaryMenuActionType.SearchTag);
            v1_0.setSelectable(1);
            if ((v0_6.getType() > 0) && (v0_6.getType() < 7)) {
                v1_0.setTextColor(android.content.res.ColorStateList.valueOf(androidx.core.content.ContextCompat.getColor(this, v0_6.getTypeColorId())));
            }
            android.content.res.ColorStateList v0_4 = this.secondaryDrawer;
            com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v2_4 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[1];
            v2_4[0] = v1_0;
            com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v0_4, v2_4);
        }
        return;
    }

    private void deleteHistoryTagItem(int p3)
    {
        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().deleteHistoryItemById(((com.bisimplex.firebooru.model.TagHistory) ((java.util.List) this.secondaryMenuData).remove(p3)).getId());
        com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.removeItemByPosition(this.secondaryDrawer, p3);
        this.ShowMessage(2131886396, com.bisimplex.firebooru.activity.MessageType.Success);
        return;
    }

    private androidx.recyclerview.widget.DefaultItemAnimator generateAnimator()
    {
        androidx.recyclerview.widget.DefaultItemAnimator v0_1 = new androidx.recyclerview.widget.DefaultItemAnimator();
        v0_1.setSupportsChangeAnimations(0);
        v0_1.setChangeDuration(0);
        v0_1.setAddDuration(0);
        v0_1.setMoveDuration(0);
        v0_1.setRemoveDuration(0);
        return v0_1;
    }

    private androidx.drawerlayout.widget.DrawerLayout getDrawerLayout()
    {
        return this.root;
    }

    private void initLoading(String p5)
    {
        android.widget.ProgressBar v0_2 = ((android.widget.ProgressBar) this.findViewById(2131362436));
        if (v0_2 != null) {
            v0_2.clearAnimation();
            v0_2.setVisibility(0);
            android.widget.TextView v2_1 = ((android.widget.TextView) this.findViewById(2131362214));
            if ((p5 != null) && (!p5.isEmpty())) {
                v2_1.setText(p5);
                v2_1.setVisibility(0);
            } else {
                v2_1.setVisibility(8);
            }
            v0_2.startAnimation(android.view.animation.AnimationUtils.loadAnimation(this, 2130771996));
            return;
        } else {
            return;
        }
    }

    private void initSecondaryDrawer()
    {
        return;
    }

    private void initSnackbar(String p2, int p3, int p4)
    {
        com.google.android.material.snackbar.Snackbar v2_1 = com.google.android.material.snackbar.Snackbar.make(this.root, p2, p3);
        if (p4 != 0) {
            this.setSnackbarColor(v2_1, p4);
        }
        v2_1.show();
        return;
    }

    private synthetic void lambda$finishedLoading$3(com.bisimplex.firebooru.danbooru.DanbooruPost p3)
    {
        com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView v0 = this.secondaryDrawer;
        com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.removeItemByPosition(v0, (v0.getItemAdapter().getAdapterItemCount() - 1));
        this.addTagsToMenu(p3.getTagList());
        return;
    }

    private synthetic Boolean lambda$onCreate$0(android.view.View p1, com.mikepenz.materialdrawer.model.interfaces.IDrawerItem p2, Integer p3)
    {
        return Boolean.valueOf(this.onDrawerItemClick(p1, p3.intValue(), p2));
    }

    private synthetic Boolean lambda$onCreate$1(android.view.View p1, com.mikepenz.materialdrawer.model.interfaces.IDrawerItem p2, Integer p3)
    {
        return Boolean.valueOf(this.onSecondaryDrawerItemClick(p1, p3.intValue(), p2));
    }

    private synthetic Boolean lambda$onCreate$2(android.view.View p1, com.mikepenz.materialdrawer.model.interfaces.IDrawerItem p2, Integer p3)
    {
        return Boolean.valueOf(this.onSecondaryDrawerItemLongClick(p1, p3.intValue(), p2));
    }

    private boolean onSecondaryDrawerItemClick(android.view.View p3, int p4, com.mikepenz.materialdrawer.model.interfaces.IDrawerItem p5)
    {
        if ((this.secondaryMenuData != null) && (this.secondaryMenuType != null)) {
            com.bisimplex.firebooru.model.TagHistory v3_9 = com.bisimplex.firebooru.activity.MenuBaseActivity$41.$SwitchMap$com$bisimplex$firebooru$custom$SecondaryMenuType[this.secondaryMenuType.ordinal()];
            if (v3_9 == 2) {
                this.infoPanelTap(p5, ((com.bisimplex.firebooru.danbooru.DanbooruPost) this.secondaryMenuData));
            } else {
                if (v3_9 == 3) {
                    this.historyPanelTap(p5, ((com.bisimplex.firebooru.model.TagHistory) ((java.util.List) this.secondaryMenuData).get(p4)));
                }
            }
        }
        return 1;
    }

    private void renderInfoPanel(com.bisimplex.firebooru.danbooru.DanbooruPost p10)
    {
        com.bisimplex.firebooru.danbooru.BooruProvider v0_11;
        if (p10.getVisibleVersion() != p10.getSample()) {
            v0_11 = 0;
        } else {
            v0_11 = 1;
        }
        com.bisimplex.firebooru.services.BooruTagHelper v1_19 = this.secondaryDrawer;
        String v5_0 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[3];
        v5_0[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$19(this);
        v5_0[1] = new com.bisimplex.firebooru.activity.MenuBaseActivity$20(this, p10, v0_11);
        v5_0[2] = new com.bisimplex.firebooru.activity.MenuBaseActivity$21(this, p10, v0_11);
        com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v1_19, v5_0);
        com.bisimplex.firebooru.services.BooruTagHelper v1_9 = com.bisimplex.firebooru.danbooru.BooruProvider.fixSourcePost(p10.getSource());
        if (!v1_9.isEmpty()) {
            com.bisimplex.firebooru.services.BooruTagHelper v1_11 = v1_9.iterator();
            while (v1_11.hasNext()) {
                String v5_2 = ((String) v1_11.next());
                com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v6_2 = this.secondaryDrawer;
                com.bisimplex.firebooru.activity.MenuBaseActivity$32 v7_0 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[2];
                v7_0[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$22(this);
                v7_0[1] = new com.bisimplex.firebooru.activity.MenuBaseActivity$23(this, v5_2);
                com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v6_2, v7_0);
            }
        }
        com.bisimplex.firebooru.services.BooruTagHelper v1_12 = this.secondaryDrawer;
        com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v6_7 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[4];
        v6_7[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$24(this);
        v6_7[1] = new com.bisimplex.firebooru.activity.MenuBaseActivity$25(this, p10);
        v6_7[2] = new com.bisimplex.firebooru.activity.MenuBaseActivity$26(this);
        v6_7[3] = new com.bisimplex.firebooru.activity.MenuBaseActivity$27(this, p10);
        com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v1_12, v6_7);
        if (!android.text.TextUtils.isEmpty(p10.getAuthor())) {
            com.bisimplex.firebooru.services.BooruTagHelper v1_15 = this.secondaryDrawer;
            com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v6_8 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[1];
            v6_8[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$28(this, p10);
            com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v1_15, v6_8);
        }
        if (p10.getCreated_at() != null) {
            com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v6_9 = this.secondaryDrawer;
            com.bisimplex.firebooru.activity.MenuBaseActivity$32 v7_11 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[1];
            v7_11[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$29(this, java.text.DateFormat.getDateTimeInstance(2, 3), p10);
            com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v6_9, v7_11);
        }
        com.bisimplex.firebooru.services.BooruTagHelper v1_18 = this.secondaryDrawer;
        com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v6_11 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[5];
        v6_11[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$30(this, p10);
        v6_11[1] = new com.bisimplex.firebooru.activity.MenuBaseActivity$31(this, p10);
        v6_11[2] = new com.bisimplex.firebooru.activity.MenuBaseActivity$32(this, p10);
        v6_11[3] = new com.bisimplex.firebooru.activity.MenuBaseActivity$33(this, p10);
        v6_11[4] = new com.bisimplex.firebooru.activity.MenuBaseActivity$34(this, p10);
        com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v1_18, v6_11);
        if (!android.text.TextUtils.isEmpty(p10.getMd5())) {
            com.bisimplex.firebooru.danbooru.BooruProvider v0_18 = this.secondaryDrawer;
            com.bisimplex.firebooru.services.BooruTagHelper v1_20 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[1];
            v1_20[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$35(this, p10);
            com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v0_18, v1_20);
        }
        com.bisimplex.firebooru.danbooru.BooruProvider v0_1 = this.secondaryDrawer;
        com.bisimplex.firebooru.services.BooruTagHelper v1_0 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[1];
        v1_0[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$36(this);
        com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v0_1, v1_0);
        if (!p10.hasCalculatedTags()) {
            com.bisimplex.firebooru.danbooru.BooruProvider v0_4 = new com.mikepenz.materialdrawer.model.PrimaryDrawerItem();
            v0_4.setName(new com.mikepenz.materialdrawer.holder.StringHolder(2131886800));
            v0_4.setTag(com.bisimplex.firebooru.custom.SecondaryMenuActionType.None);
            com.bisimplex.firebooru.services.BooruTagHelper v1_4 = this.secondaryDrawer;
            com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v2_0 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[1];
            v2_0[0] = v0_4;
            com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v1_4, v2_0);
            com.bisimplex.firebooru.danbooru.BooruProvider v0_7 = ((com.bisimplex.firebooru.activity.MainActivity) this).findOnScreenSource();
            if ((v0_7 == null) || ((v0_7 instanceof com.bisimplex.firebooru.network.SourceFavorites))) {
                com.bisimplex.firebooru.services.BooruTagHelper.getInstance().processTagsAsync(p10, 0, this);
                return;
            } else {
                com.bisimplex.firebooru.services.BooruTagHelper.getInstance().processTagsAsync(p10, v0_7.getProvider(), this);
                return;
            }
        } else {
            this.addTagsToMenu(p10.getTagList());
            return;
        }
    }

    private void renderTagHistory(java.util.List p5)
    {
        java.util.Iterator v5_1 = p5.iterator();
        while (v5_1.hasNext()) {
            com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView v0_8 = ((com.bisimplex.firebooru.model.TagHistory) v5_1.next());
            com.mikepenz.materialdrawer.model.PrimaryDrawerItem v1_0 = new com.mikepenz.materialdrawer.model.PrimaryDrawerItem();
            v1_0.setName(new com.mikepenz.materialdrawer.holder.StringHolder(v0_8.search));
            if (v0_8.isFavoritedHistoryItem != 1) {
                v1_0.setIcon(new com.mikepenz.materialdrawer.holder.ImageHolder(2131230881));
            } else {
                v1_0.setIcon(new com.mikepenz.materialdrawer.iconics.IconicsImageHolder(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_heart1));
            }
            com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView v0_5 = this.secondaryDrawer;
            com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v2_3 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[1];
            v2_3[0] = v1_0;
            com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v0_5, v2_3);
        }
        return;
    }

    private void setSnackbarColor(com.google.android.material.snackbar.Snackbar p1, int p2)
    {
        return;
    }

    private void toggleFavoriteHistoryTag(int p7)
    {
        com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView v0_8 = ((com.bisimplex.firebooru.model.TagHistory) ((java.util.List) this.secondaryMenuData).get(p7));
        com.mikepenz.materialdrawer.model.PrimaryDrawerItem v1_0 = ((com.mikepenz.materialdrawer.model.PrimaryDrawerItem) this.secondaryDrawer.getAdapter().getItem(p7));
        if (v0_8.isFavoritedHistoryItem != 1) {
            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().starHistoryItem(v0_8.getId().longValue(), 1);
            v0_8.isFavoritedHistoryItem = 1;
            v1_0.setIcon(new com.mikepenz.materialdrawer.iconics.IconicsImageHolder(com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_heart1));
        } else {
            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().starHistoryItem(v0_8.getId().longValue(), 0);
            v0_8.isFavoritedHistoryItem = 0;
            v1_0.setIcon(new com.mikepenz.materialdrawer.holder.ImageHolder(2131230881));
        }
        com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.updateItemAtPosition(this.secondaryDrawer, v1_0, p7);
        return;
    }

    public void HideLoading()
    {
        android.widget.ProgressBar v0_2 = ((android.widget.ProgressBar) this.findViewById(2131362436));
        if ((v0_2 != null) && (v0_2.getVisibility() != 8)) {
            android.view.animation.Animation v1_5 = this.fadeOut;
            if (v1_5 != null) {
                v1_5.cancel();
            }
            this.fadeOut = android.view.animation.AnimationUtils.loadAnimation(this, 2130771997);
            v0_2.clearAnimation();
            this.fadeOut.setAnimationListener(new com.bisimplex.firebooru.activity.MenuBaseActivity$39(this, v0_2));
            v0_2.startAnimation(this.fadeOut);
            return;
        } else {
            return;
        }
    }

    public void ShowLoading()
    {
        this.ShowMessage("", com.bisimplex.firebooru.activity.MessageType.Loading);
        return;
    }

    public void ShowMessage(int p1, com.bisimplex.firebooru.activity.MessageType p2)
    {
        this.ShowMessage(this.getString(p1), p2);
        return;
    }

    public void ShowMessage(String p2, com.bisimplex.firebooru.activity.MessageType p3)
    {
        this.runOnUiThread(new com.bisimplex.firebooru.activity.MenuBaseActivity$38(this, p2, p3));
        return;
    }

    protected void backPressed()
    {
        this.HideLoading();
        androidx.fragment.app.FragmentManager v0 = this.getSupportFragmentManager();
        if (v0.getBackStackEntryCount() <= 0) {
            this.finish();
            return;
        } else {
            v0.popBackStack();
            return;
        }
    }

    protected com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView buildMainMenu()
    {
        com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.removeAllItems(this.menuDrawer);
        com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView v0_2 = this.menuDrawer;
        com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v2_4 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[3];
        v2_4[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$3(this);
        v2_4[1] = new com.bisimplex.firebooru.activity.MenuBaseActivity$4(this);
        v2_4[2] = new com.bisimplex.firebooru.activity.MenuBaseActivity$5(this);
        com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v0_2, v2_4);
        com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView v0_1 = this.getFrontProvider();
        if (!v0_1.getTopString().isEmpty()) {
            com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v2_2 = this.menuDrawer;
            int v3_3 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[1];
            v3_3[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$6(this);
            com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v2_2, v3_3);
        }
        if (!v0_1.getBestString().isEmpty()) {
            com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView v0_5 = this.menuDrawer;
            com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v2_3 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[1];
            v2_3[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$7(this);
            com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v0_5, v2_3);
        }
        com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView v0_6 = this.menuDrawer;
        com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v2_5 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[3];
        v2_5[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$8(this);
        v2_5[1] = new com.bisimplex.firebooru.activity.MenuBaseActivity$9(this);
        v2_5[2] = new com.bisimplex.firebooru.activity.MenuBaseActivity$10(this);
        com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v0_6, v2_5);
        com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView v0_7 = this.menuDrawer;
        com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v2_6 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[1];
        v2_6[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$11(this);
        com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v0_7, v2_6);
        com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView v0_8 = this.menuDrawer;
        com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] v2_8 = new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[7];
        v2_8[0] = new com.bisimplex.firebooru.activity.MenuBaseActivity$12(this);
        v2_8[1] = new com.bisimplex.firebooru.activity.MenuBaseActivity$13(this);
        v2_8[2] = new com.bisimplex.firebooru.activity.MenuBaseActivity$14(this);
        v2_8[3] = new com.bisimplex.firebooru.activity.MenuBaseActivity$15(this);
        v2_8[4] = new com.bisimplex.firebooru.activity.MenuBaseActivity$16(this);
        v2_8[5] = new com.bisimplex.firebooru.activity.MenuBaseActivity$17(this);
        v2_8[6] = new com.bisimplex.firebooru.activity.MenuBaseActivity$18(this);
        com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.addItems(v0_8, v2_8);
        this.initSecondaryDrawer();
        return this.menuDrawer;
    }

    public void clearFocusFromDrawer()
    {
        if (this.root.isFocused()) {
            this.root.clearFocus();
        }
        return;
    }

    public void closeDrawer()
    {
        if ((this.menuDrawer != null) && (this.getDrawerLayout().isDrawerOpen(this.menuDrawer))) {
            this.getDrawerLayout().closeDrawer(this.menuDrawer);
        }
        return;
    }

    public boolean closeMenuIfOpen()
    {
        if ((this.menuDrawer == null) || (!this.getDrawerLayout().isDrawerOpen(this.menuDrawer))) {
            if ((this.secondaryDrawer == null) || (!this.getDrawerLayout().isDrawerOpen(this.secondaryDrawer))) {
                return 0;
            } else {
                this.getDrawerLayout().closeDrawer(this.secondaryDrawer);
                return 1;
            }
        } else {
            this.getDrawerLayout().closeDrawer(this.menuDrawer);
            return 1;
        }
    }

    public void closeSecondaryMenu()
    {
        if ((this.secondaryDrawer != null) && (this.getDrawerLayout().isDrawerOpen(this.secondaryDrawer))) {
            this.getDrawerLayout().closeDrawer(this.secondaryDrawer);
        }
        return;
    }

    public void copyToClipboard(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            ((android.content.ClipboardManager) this.getSystemService("clipboard")).setPrimaryClip(android.content.ClipData.newPlainText("Copied Text", p3));
            return;
        } else {
            return;
        }
    }

    protected void drawerClosed(android.view.View p1)
    {
        return;
    }

    protected void drawerOpened(android.view.View p1)
    {
        return;
    }

    public void finishedLoading(com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        if ((this.secondaryDrawer != null) && (p2 == this.secondaryMenuData)) {
            this.runOnUiThread(new com.bisimplex.firebooru.activity.MenuBaseActivity$$ExternalSyntheticLambda0(this, p2));
        }
        return;
    }

    protected com.bisimplex.firebooru.danbooru.BooruProvider getFrontProvider()
    {
        return com.bisimplex.firebooru.danbooru.BooruProvider.getInstance();
    }

    public com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView getMenuDrawer()
    {
        return this.menuDrawer;
    }

    protected int getOpenDrawerIndex()
    {
        if (!this.getDrawerLayout().isDrawerVisible(this.menuDrawer)) {
            if (!this.getDrawerLayout().isDrawerVisible(this.secondaryDrawer)) {
                return 0;
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    protected void historyPanelTap(com.mikepenz.materialdrawer.model.interfaces.IDrawerItem p1, com.bisimplex.firebooru.model.TagHistory p2)
    {
        return;
    }

    protected void infoPanelTap(com.mikepenz.materialdrawer.model.interfaces.IDrawerItem p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        if ((p2 != null) && (p1 != 0)) {
            com.bisimplex.firebooru.custom.SecondaryMenuActionType.fromInteger(((int) p1.getIdentifier())).ordinal();
        }
        return;
    }

    public boolean isAllowedBilling()
    {
        return this.isIncludedPermission("com.android.vending.BILLING");
    }

    public boolean isDrawerEnabled()
    {
        return this.drawerEnabled;
    }

    public boolean isIncludedPermission(String p2)
    {
        if (this.getApplicationContext().checkCallingOrSelfPermission(p2) != 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean isLoadingSomething()
    {
        int v0_3 = ((android.widget.ProgressBar) this.findViewById(2131362436));
        if (v0_3 != 0) {
            if (v0_3.getVisibility() != 0) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public void onConfigurationChanged(android.content.res.Configuration p2)
    {
        super.onConfigurationChanged(p2);
        this.actionBarDrawerToggle.onConfigurationChanged(p2);
        return;
    }

    public void onCreate(android.os.Bundle p8)
    {
        super.onCreate(p8);
        this.setContentView(2131558452);
        this.root = ((androidx.drawerlayout.widget.DrawerLayout) this.findViewById(2131362471));
        this.menuDrawer = ((com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView) this.findViewById(2131362537));
        this.secondaryDrawer = ((com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView) this.findViewById(2131362538));
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(this.root, new com.bisimplex.firebooru.activity.MenuBaseActivity$1(this));
        int v1_1 = new androidx.appcompat.app.ActionBarDrawerToggle(this, this.root, 0, 2131886824, 2131886823);
        this.actionBarDrawerToggle = v1_1;
        this.root.addDrawerListener(v1_1);
        this.setStatusBarVisible(com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().isStatusBarVisible());
        com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().setContext(this);
        this.setTitle(this.mTitleRes);
        this.setupActionbar();
        this.root.addDrawerListener(new com.bisimplex.firebooru.activity.MenuBaseActivity$2(this));
        this.menuDrawer.setOnDrawerItemClickListener(new com.bisimplex.firebooru.activity.MenuBaseActivity$$ExternalSyntheticLambda1(this));
        this.secondaryDrawer.setOnDrawerItemClickListener(new com.bisimplex.firebooru.activity.MenuBaseActivity$$ExternalSyntheticLambda2(this));
        this.getDrawerLayout().setDrawerLockMode(1, 8388613);
        this.secondaryDrawer.setOnDrawerItemLongClickListener(new com.bisimplex.firebooru.activity.MenuBaseActivity$$ExternalSyntheticLambda3(this));
        this.menuDrawer.setItemAnimator(this.generateAnimator());
        this.secondaryDrawer.setItemAnimator(this.generateAnimator());
        return;
    }

    protected boolean onDrawerItemClick(android.view.View p4, int p5, com.mikepenz.materialdrawer.model.interfaces.IDrawerItem p6)
    {
        if (this.isDrawerEnabled()) {
            int v5_6;
            switch (((int) p6.getIdentifier())) {
                case 0:
                    v5_6 = new com.bisimplex.firebooru.fragment.HomeFragment();
                    break;
                case 1:
                    com.bisimplex.firebooru.network.SourceFactory.getInstance().removeAllSources();
                    int v5_18 = new com.bisimplex.firebooru.network.SourceQuery();
                    v5_18.setDisableAutoLoad(1);
                    ((com.bisimplex.firebooru.activity.MainActivity) this).searchQuery(v5_18, com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance(), 1);
                    v5_6 = 0;
                    break;
                case 2:
                    com.bisimplex.firebooru.network.SourceFactory.getInstance().removeAllSources();
                    ((com.bisimplex.firebooru.activity.MainActivity) this).searchQuery(new com.bisimplex.firebooru.network.SourceQuery(this.getString(2131886144)), com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance(), 1);
                    break;
                case 3:
                    com.bisimplex.firebooru.network.SourceFactory.getInstance().removeAllSources();
                    v5_6 = new com.bisimplex.firebooru.fragment.FavoriteListFragment();
                    android.content.Intent v6_2 = com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.Favorites);
                    String v2_2 = new android.os.Bundle(1);
                    v2_2.putString("SOURCE_KEY", v6_2.getKey());
                    v5_6.setArguments(v2_2);
                    break;
                case 4:
                    com.bisimplex.firebooru.network.SourceFactory.getInstance().removeAllSources();
                    v5_6 = new com.bisimplex.firebooru.fragment.ServersFragment();
                    break;
                case 5:
                    com.bisimplex.firebooru.network.SourceFactory.getInstance().removeAllSources();
                    v5_6 = new com.bisimplex.firebooru.fragment.TagsBlackListFragment();
                    break;
                case 6:
                default:
                    break;
                case 7:
                    ((com.bisimplex.firebooru.activity.MainActivity) this).launchBrowser(this.getString(2131886265), 1);
                    break;
                case 8:
                    ((com.bisimplex.firebooru.activity.MainActivity) this).launchBrowser(this.getString(2131887223), 1);
                    break;
                case 9:
                    ((com.bisimplex.firebooru.activity.MainActivity) this).launchBrowser(this.getString(2131886651), 1);
                    break;
                case 10:
                    com.bisimplex.firebooru.network.SourceFactory.getInstance().removeAllSources();
                    ((com.bisimplex.firebooru.activity.MainActivity) this).searchQuery(new com.bisimplex.firebooru.network.SourceQuery(com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().getTopString()), com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance(), 1);
                    break;
                case 11:
                    com.bisimplex.firebooru.network.SourceFactory.getInstance().removeAllSources();
                    ((com.bisimplex.firebooru.activity.MainActivity) this).searchQuery(new com.bisimplex.firebooru.network.SourceQuery(com.bisimplex.firebooru.danbooru.BooruProvider.getInstance().getBestString()), com.bisimplex.firebooru.danbooru.BooruProvider.createSelectedInstance(), 1);
                    break;
                case 12:
                    com.bisimplex.firebooru.network.SourceFactory.getInstance().removeAllSources();
                    v5_6 = new com.bisimplex.firebooru.fragment.HistoryFragment();
                    android.content.Intent v6_11 = com.bisimplex.firebooru.network.SourceFactory.getInstance().createSource(com.bisimplex.firebooru.network.SourceType.History);
                    String v2_7 = new android.os.Bundle(1);
                    v2_7.putString("SOURCE_KEY", v6_11.getKey());
                    v5_6.setArguments(v2_7);
                    break;
                case 13:
                    com.bisimplex.firebooru.network.SourceFactory.getInstance().removeAllSources();
                    v5_6 = new com.bisimplex.firebooru.fragment.PoolServersFragment();
                    break;
                case 14:
                    ((com.bisimplex.firebooru.activity.MainActivity) this).launchBrowser(this.getString(2131886240), 1);
                    break;
                case 15:
                    android.content.Intent v6_9 = new android.content.Intent("android.intent.action.SEND");
                    v6_9.setDataAndType(android.net.Uri.parse("mailto"), "message/rfc822");
                    String v0_3 = new String[1];
                    v0_3[0] = "support@bisimplex.com";
                    v6_9.putExtra("android.intent.extra.EMAIL", v0_3);
                    v6_9.putExtra("android.intent.extra.SUBJECT", this.getString(2131886583));
                    v6_9.putExtra("android.intent.extra.TEXT", this.getString(2131886582));
                    ((com.bisimplex.firebooru.activity.MainActivity) this).startActivity(android.content.Intent.createChooser(v6_9, this.getString(2131887019)));
                    break;
                case 16:
                    v5_6 = new com.bisimplex.firebooru.fragment.DownloadsFragment();
                    break;
                case 17:
                    com.bisimplex.firebooru.network.SourceFactory.getInstance().removeAllSources();
                    v5_6 = new com.bisimplex.firebooru.fragment.DynamicSettingsFragment();
                    break;
            }
            if (v5_6 != 0) {
                ((com.bisimplex.firebooru.activity.MainActivity) this).switchContent(v5_6);
            }
            return 1;
        } else {
            return 0;
        }
    }

    public void onHistoryTagDialogClick(androidx.fragment.app.DialogFragment p2, String p3, int p4, com.bisimplex.firebooru.view.HistoryTagMenuDialog$HistoryTagActionType p5)
    {
        if (this.secondaryMenuType == com.bisimplex.firebooru.custom.SecondaryMenuType.History) {
            int v2_3 = com.bisimplex.firebooru.activity.MenuBaseActivity$41.$SwitchMap$com$bisimplex$firebooru$view$HistoryTagMenuDialog$HistoryTagActionType[p5.ordinal()];
            if (v2_3 == 1) {
                this.toggleFavoriteHistoryTag(p4);
                return;
            } else {
                if (v2_3 == 2) {
                    this.deleteHistoryTagItem(p4);
                    return;
                } else {
                    if (v2_3 == 3) {
                        this.copyToClipboard(p3);
                        this.ShowMessage(2131886280, com.bisimplex.firebooru.activity.MessageType.Success);
                        return;
                    }
                }
            }
        }
        return;
    }

    public boolean onOptionsItemSelected(android.view.MenuItem p2)
    {
        if (!this.actionBarDrawerToggle.onOptionsItemSelected(p2)) {
            return super.onOptionsItemSelected(p2);
        } else {
            return 1;
        }
    }

    protected void onPostCreate(android.os.Bundle p3)
    {
        super.onPostCreate(p3);
        this.getOnBackPressedDispatcher().addCallback(this, new com.bisimplex.firebooru.activity.MenuBaseActivity$40(this, 1));
        return;
    }

    protected void onRestoreInstanceState(android.os.Bundle p2)
    {
        super.onRestoreInstanceState(p2);
        this.drawerEnabled = p2.getBoolean("drawerEnabled");
        return;
    }

    public void onResume()
    {
        super.onResume();
        this.actionBarDrawerToggle.syncState();
        return;
    }

    protected void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        p3.putBoolean("drawerEnabled", this.drawerEnabled);
        return;
    }

    protected boolean onSecondaryDrawerItemLongClick(android.view.View p6, int p7, com.mikepenz.materialdrawer.model.interfaces.IDrawerItem p8)
    {
        String v0_0 = 0;
        if (this.isDrawerEnabled()) {
            if (this.secondaryMenuType != com.bisimplex.firebooru.custom.SecondaryMenuType.History) {
                com.bisimplex.firebooru.activity.MessageType v6_16;
                com.bisimplex.firebooru.activity.MessageType v6_4 = p8.getTag();
                if (v6_4 == null) {
                    v6_16 = com.bisimplex.firebooru.custom.SecondaryMenuActionType.fromInteger(((int) p8.getIdentifier()));
                } else {
                    v6_16 = ((com.bisimplex.firebooru.custom.SecondaryMenuActionType) v6_4);
                }
                switch (com.bisimplex.firebooru.activity.MenuBaseActivity$41.$SwitchMap$com$bisimplex$firebooru$custom$SecondaryMenuActionType[v6_16.ordinal()]) {
                    case 2:
                        com.bisimplex.firebooru.activity.MessageType v6_1 = this.secondaryMenuData;
                        if (!(v6_1 instanceof com.bisimplex.firebooru.danbooru.DanbooruPost)) {
                        } else {
                            com.bisimplex.firebooru.activity.MessageType v6_2 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v6_1);
                            if (v6_2.getSample() == null) {
                            } else {
                                this.shareURL(v6_2.getSample().getUrl());
                            }
                        }
                        break;
                    case 3:
                        com.bisimplex.firebooru.activity.MessageType v6_40 = this.secondaryMenuData;
                        if (!(v6_40 instanceof com.bisimplex.firebooru.danbooru.DanbooruPost)) {
                        } else {
                            com.bisimplex.firebooru.activity.MessageType v6_41 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v6_40);
                            if (v6_41.getFile() == null) {
                            } else {
                                this.shareURL(v6_41.getFile().getUrl());
                            }
                        }
                        break;
                    case 4:
                        if (!(p8 instanceof com.mikepenz.materialdrawer.model.PrimaryDrawerItem)) {
                        } else {
                            this.shareURL(((com.mikepenz.materialdrawer.model.PrimaryDrawerItem) p8).getName().getText(this));
                        }
                        break;
                    case 5:
                        if (!(p8 instanceof com.mikepenz.materialdrawer.model.PrimaryDrawerItem)) {
                            if (!(p8 instanceof com.mikepenz.materialdrawer.model.PrimaryDrawerItem)) {
                            } else {
                                com.bisimplex.firebooru.activity.MessageType v6_31 = ((com.mikepenz.materialdrawer.model.PrimaryDrawerItem) p8).getName().getText(this);
                                if (!android.text.TextUtils.isEmpty(v6_31)) {
                                    this.copyToClipboard(v6_31.substring(5));
                                    this.ShowMessage(2131886280, com.bisimplex.firebooru.activity.MessageType.Success);
                                } else {
                                }
                            }
                        } else {
                            com.bisimplex.firebooru.activity.MessageType v6_36 = new com.bisimplex.firebooru.view.TagMenuDialog();
                            androidx.fragment.app.FragmentManager v7_7 = new android.os.Bundle();
                            v7_7.putString(com.bisimplex.firebooru.view.TagMenuDialog.TITLE_KEY, ((com.mikepenz.materialdrawer.model.PrimaryDrawerItem) p8).getName().getText(this));
                            v6_36.setArguments(v7_7);
                            v6_36.show(this.getSupportFragmentManager(), "tagMenuDialog");
                        }
                        break;
                    case 7:
                        com.bisimplex.firebooru.activity.MessageType v6_23 = this.secondaryMenuData;
                        if (!(v6_23 instanceof com.bisimplex.firebooru.danbooru.DanbooruPost)) {
                        } else {
                            this.copyToClipboard(((com.bisimplex.firebooru.danbooru.DanbooruPost) v6_23).getPostId());
                            this.ShowMessage(2131886280, com.bisimplex.firebooru.activity.MessageType.Success);
                        }
                        break;
                    case 8:
                        com.bisimplex.firebooru.activity.MessageType v6_19 = this.secondaryMenuData;
                        if (!(v6_19 instanceof com.bisimplex.firebooru.danbooru.DanbooruPost)) {
                        } else {
                            com.bisimplex.firebooru.activity.MessageType v6_20 = ((com.bisimplex.firebooru.danbooru.DanbooruPost) v6_19);
                            if (!v6_20.hasParent()) {
                            } else {
                                this.copyToClipboard(v6_20.getParent_id());
                                this.ShowMessage(2131886280, com.bisimplex.firebooru.activity.MessageType.Success);
                            }
                        }
                        break;
                    default:
                }
                return 1;
            } else {
                if ((p8 instanceof com.mikepenz.materialdrawer.model.PrimaryDrawerItem)) {
                    com.bisimplex.firebooru.activity.MessageType v6_10 = ((com.bisimplex.firebooru.model.TagHistory) ((java.util.List) this.secondaryMenuData).get(p7));
                    int v8_2 = new com.bisimplex.firebooru.view.HistoryTagMenuDialog();
                    android.os.Bundle v1_1 = new android.os.Bundle();
                    v1_1.putString(com.bisimplex.firebooru.view.HistoryTagMenuDialog.TITLE_KEY, v6_10.search);
                    if (v6_10.isFavoritedHistoryItem == 1) {
                        v0_0 = 1;
                    }
                    v1_1.putBoolean(com.bisimplex.firebooru.view.HistoryTagMenuDialog.FAVORITE_KEY, v0_0);
                    v1_1.putInt(com.bisimplex.firebooru.view.HistoryTagMenuDialog.POSITION_KEY, p7);
                    v8_2.setArguments(v1_1);
                    v8_2.show(this.getSupportFragmentManager(), "historyTagMenuDialog");
                }
                return 1;
            }
        } else {
            return 0;
        }
    }

    public void onTagDialogClick(androidx.fragment.app.DialogFragment p3, String p4, com.bisimplex.firebooru.view.TagMenuDialog$TagActionType p5)
    {
        if (!android.text.TextUtils.isEmpty(p4)) {
            com.bisimplex.firebooru.danbooru.BooruProvider v0_1 = ((com.bisimplex.firebooru.activity.MainActivity) this).findOnScreenSource();
            if (v0_1 != null) {
                switch (com.bisimplex.firebooru.activity.MenuBaseActivity$41.$SwitchMap$com$bisimplex$firebooru$view$TagMenuDialog$TagActionType[p5.ordinal()]) {
                    case 1:
                        ((com.bisimplex.firebooru.activity.MainActivity) this).searchQuery(new com.bisimplex.firebooru.network.SourceQuery(p4), v0_1.getProvider());
                        this.closeSecondaryMenu();
                        break;
                    case 2:
                        com.bisimplex.firebooru.network.SourceQuery v5_12 = v0_1.getQuery();
                        com.bisimplex.firebooru.danbooru.BooruProvider v0_2 = v0_1.getProvider();
                        if (v5_12 != null) {
                            ((com.bisimplex.firebooru.activity.MainActivity) this).searchQuery(v5_12.plus(p4, v0_2.getTagSeparator()), v0_2);
                        } else {
                            ((com.bisimplex.firebooru.activity.MainActivity) this).searchQuery(new com.bisimplex.firebooru.network.SourceQuery(p4), v0_2);
                        }
                        this.closeSecondaryMenu();
                        return;
                    case 3:
                        com.bisimplex.firebooru.network.SourceQuery v5_9 = v0_1.getQuery();
                        com.bisimplex.firebooru.danbooru.BooruProvider v0_0 = v0_1.getProvider();
                        if (v5_9 != null) {
                            ((com.bisimplex.firebooru.activity.MainActivity) this).searchQuery(v5_9.minus(p4, v0_0.getTagSeparator()), v0_0);
                        } else {
                            ((com.bisimplex.firebooru.activity.MainActivity) this).searchQuery(new com.bisimplex.firebooru.network.SourceQuery(p4), v0_0);
                        }
                        this.closeSecondaryMenu();
                        return;
                    case 4:
                        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().addHistoryItem(p4);
                        this.ShowMessage(2131887103, com.bisimplex.firebooru.activity.MessageType.Success);
                        return;
                    case 5:
                        this.copyToClipboard(p4);
                        this.ShowMessage(2131886280, com.bisimplex.firebooru.activity.MessageType.Success);
                        return;
                    case 6:
                        com.bisimplex.firebooru.network.Source v3_4 = new java.util.ArrayList(1);
                        v3_4.add(v0_1.getProvider().getServerDescription());
                        com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().addBannedTag(p4, v3_4);
                        v0_1.getProvider().updateBannedTags();
                        this.ShowMessage(2131886179, com.bisimplex.firebooru.activity.MessageType.Success);
                        return;
                    case 7:
                        com.bisimplex.firebooru.network.Source v3_2 = com.bisimplex.firebooru.network.SourceFactory.getInstance().copySource(v0_1);
                        v3_2.setQuery(new com.bisimplex.firebooru.network.SourceQuery(p4));
                        this.pinToHome(v3_2);
                        return;
                    default:
                }
            }
        }
        return;
    }

    public void openDrawer()
    {
        if ((this.menuDrawer != null) && (!this.getDrawerLayout().isOpen())) {
            this.getDrawerLayout().openDrawer(this.menuDrawer);
            this.closeSecondaryMenu();
        }
        return;
    }

    public void openURL(String p3)
    {
        if ((!android.text.TextUtils.isEmpty(p3)) && (android.util.Patterns.WEB_URL.matcher(p3).matches())) {
            this.startActivity(new android.content.Intent("android.intent.action.VIEW", android.net.Uri.parse(p3)));
            return;
        } else {
            return;
        }
    }

    protected void pinToHome(com.bisimplex.firebooru.network.Source p5)
    {
        if ((p5 != null) && (p5.getProvider() != null)) {
            com.bisimplex.firebooru.view.PinDialog v0_2 = new com.bisimplex.firebooru.view.PinDialog();
            String v1_2 = new android.os.Bundle(1);
            v1_2.putString("SOURCE_ID", p5.getKey());
            v1_2.putInt("SOURCE_TYPE", p5.getType().getValue());
            v0_2.setArguments(v1_2);
            v0_2.show(this.getSupportFragmentManager(), "PinDialog_TAG");
        }
        return;
    }

    public void reloadMenuOptions()
    {
        this.buildMainMenu();
        return;
    }

    public void setDrawerEnabled(boolean p3)
    {
        if (this.menuDrawer != null) {
            this.drawerEnabled = p3;
            this.getDrawerLayout().setDrawerLockMode((p3 ^ 1), 8388611);
            return;
        } else {
            return;
        }
    }

    public void setIsSmokeScreenVisible(boolean p3)
    {
        android.widget.ProgressBar v0_2 = ((android.widget.ProgressBar) this.findViewById(2131362436));
        if (v0_2 != null) {
            android.view.View v3_3;
            if (p3 == null) {
                v3_3 = 8;
            } else {
                v3_3 = 0;
            }
            v0_2.setVisibility(v3_3);
            this.findViewById(2131362214).setVisibility(8);
            return;
        } else {
            return;
        }
    }

    public void setStatusBarVisible(boolean p3)
    {
        android.view.Window v0 = this.getWindow();
        if (p3 == 0) {
            if ((v0.getAttributes().flags & 1024) != 1024) {
                v0.setFlags(1024, 1024);
            }
        } else {
            if ((v0.getAttributes().flags & 1024) == 1024) {
                v0.clearFlags(1024);
                return;
            }
        }
        return;
    }

    protected void setupActionbar()
    {
        androidx.appcompat.app.ActionBar v0 = this.getSupportActionBar();
        if (v0 != null) {
            v0.setDisplayHomeAsUpEnabled(1);
            v0.setDisplayUseLogoEnabled(0);
            v0.setHomeButtonEnabled(0);
            v0.setDisplayShowHomeEnabled(0);
        }
        return;
    }

    public void shareURL(String p6)
    {
        if (!android.text.TextUtils.isEmpty(p6)) {
            try {
                android.net.Uri v0_5 = android.net.Uri.parse(p6);
                int v1_5 = v0_5.getQueryParameterNames();
                android.net.Uri$Builder v2 = v0_5.buildUpon();
                v2.clearQuery();
                int v1_0 = v1_5.iterator();
            } catch (Exception) {
                String v6_1 = new com.bumptech.glide.load.model.GlideUrl(p6).toStringUrl();
                if (!android.text.TextUtils.isEmpty(v6_1)) {
                    android.net.Uri v0_7 = new android.content.Intent("android.intent.action.SEND");
                    v0_7.setType("text/plain");
                    v0_7.putExtra("android.intent.extra.TEXT", v6_1);
                    this.startActivity(android.content.Intent.createChooser(v0_7, this.getResources().getText(2131887133)));
                    return;
                }
            }
            while (v1_0.hasNext()) {
                String v3_2 = ((String) v1_0.next());
                if (!v3_2.equalsIgnoreCase("api_key")) {
                    if (!v3_2.equalsIgnoreCase("login")) {
                        v2.appendQueryParameter(v3_2, v0_5.getQueryParameter(v3_2));
                    } else {
                    }
                }
            }
            p6 = v2.build().toString();
        }
        return;
    }

    public void showSecondaryMenu(com.bisimplex.firebooru.custom.SecondaryMenuType p3, Object p4)
    {
        this.closeDrawer();
        this.initSecondaryDrawer();
        if ((p4 != null) && (p3 != null)) {
            this.secondaryMenuType = p3;
            this.secondaryMenuData = p4;
            com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.removeAllItems(this.secondaryDrawer);
            android.os.Handler v3_1 = com.bisimplex.firebooru.activity.MenuBaseActivity$41.$SwitchMap$com$bisimplex$firebooru$custom$SecondaryMenuType[this.secondaryMenuType.ordinal()];
            if (v3_1 == 2) {
                if ((p4 instanceof com.bisimplex.firebooru.danbooru.DanbooruPost)) {
                    this.renderInfoPanel(((com.bisimplex.firebooru.danbooru.DanbooruPost) p4));
                }
            } else {
                if (v3_1 == 3) {
                    this.renderTagHistory(((java.util.List) p4));
                }
            }
            if (!this.getDrawerLayout().isDrawerOpen(this.secondaryDrawer)) {
                new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new com.bisimplex.firebooru.activity.MenuBaseActivity$37(this), 150);
            }
            return;
        } else {
            com.mikepenz.materialdrawer.util.MaterialDrawerSliderViewExtensionsKt.removeAllItems(this.secondaryDrawer);
            this.secondaryMenuType = com.bisimplex.firebooru.custom.SecondaryMenuType.None;
            this.secondaryMenuData = 0;
            return;
        }
    }
}
