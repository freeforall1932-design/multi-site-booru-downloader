package com.bisimplex.firebooru.skin;
public class SkinManager {
    private static com.bisimplex.firebooru.skin.SkinManager sharedInstance;
    private int accentColorRes;
    private int actionBarIconColorRes;
    private int activityBackgroudColorRes;
    private int primaryColorRes;
    private int primaryDarkColorRes;
    private int semiWindowBackgroundColorRes;
    private int successTextColorRes;
    private int textColorRes;
    private int windowBackgroundColorRes;

    protected SkinManager()
    {
        this.reloadTheme();
        return;
    }

    public static com.bisimplex.firebooru.skin.SkinManager getInstance()
    {
        if (com.bisimplex.firebooru.skin.SkinManager.sharedInstance == null) {
            com.bisimplex.firebooru.skin.SkinManager.sharedInstance = new com.bisimplex.firebooru.skin.SkinManager();
        }
        return com.bisimplex.firebooru.skin.SkinManager.sharedInstance;
    }

    public int getAccentColorRes()
    {
        return this.accentColorRes;
    }

    public int getActionBarIconColorRes()
    {
        return this.actionBarIconColorRes;
    }

    public int getActivityBackgroudColorRes()
    {
        return this.activityBackgroudColorRes;
    }

    public int getPrimaryColorRes()
    {
        return this.primaryColorRes;
    }

    public int getPrimaryDarkColorRes()
    {
        return this.primaryDarkColorRes;
    }

    public android.graphics.drawable.ShapeDrawable$ShaderFactory getScrimShaderFactory()
    {
        return new com.bisimplex.firebooru.skin.SkinManager$1(this);
    }

    public int getSemiWindowBackgroundColorRes()
    {
        return this.semiWindowBackgroundColorRes;
    }

    public int getSuccessTextColorRes()
    {
        return this.successTextColorRes;
    }

    public int getTextColorRes()
    {
        return this.textColorRes;
    }

    public int getWindowBackgroundColorRes()
    {
        return this.windowBackgroundColorRes;
    }

    public void reloadTheme()
    {
        int v0_19 = com.bisimplex.firebooru.skin.SkinManager$2.$SwitchMap$com$bisimplex$firebooru$custom$ThemeType[com.bisimplex.firebooru.danbooru.UserConfiguration.getInstance().getThemeSelected().ordinal()];
        if ((v0_19 == 1) || ((v0_19 == 2) || (v0_19 == 3))) {
            this.setAccentColorRes(2131099701);
            this.setPrimaryColorRes(2131099708);
            this.setPrimaryDarkColorRes(2131099709);
            this.setActionBarIconColorRes(2131099784);
            this.setActivityBackgroudColorRes(2131099675);
            this.setTextColorRes(2131099745);
            this.setWindowBackgroundColorRes(2131100468);
            this.setSemiWindowBackgroundColorRes(2131100467);
        } else {
            if (v0_19 == 4) {
                this.setAccentColorRes(2131099703);
                this.setPrimaryColorRes(2131099711);
                this.setPrimaryDarkColorRes(2131099710);
                this.setActionBarIconColorRes(2131099785);
                this.setActivityBackgroudColorRes(2131099732);
                this.setTextColorRes(2131099732);
                this.setWindowBackgroundColorRes(2131100471);
                this.setSemiWindowBackgroundColorRes(2131100470);
            }
        }
        this.successTextColorRes = this.getAccentColorRes();
        return;
    }

    public void setAccentColorRes(int p1)
    {
        this.accentColorRes = p1;
        return;
    }

    public void setActionBarIconColorRes(int p1)
    {
        this.actionBarIconColorRes = p1;
        return;
    }

    public void setActivityBackgroudColorRes(int p1)
    {
        this.activityBackgroudColorRes = p1;
        return;
    }

    public void setPrimaryColorRes(int p1)
    {
        this.primaryColorRes = p1;
        return;
    }

    public void setPrimaryDarkColorRes(int p1)
    {
        this.primaryDarkColorRes = p1;
        return;
    }

    public void setSemiWindowBackgroundColorRes(int p1)
    {
        this.semiWindowBackgroundColorRes = p1;
        return;
    }

    public void setTextColorRes(int p1)
    {
        this.textColorRes = p1;
        return;
    }

    public void setWindowBackgroundColorRes(int p1)
    {
        this.windowBackgroundColorRes = p1;
        return;
    }
}
