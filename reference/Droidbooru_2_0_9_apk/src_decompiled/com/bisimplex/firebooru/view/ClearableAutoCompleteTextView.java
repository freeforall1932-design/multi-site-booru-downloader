package com.bisimplex.firebooru.view;
public class ClearableAutoCompleteTextView extends androidx.appcompat.widget.AppCompatAutoCompleteTextView {
    private com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$OnBackPressedListener backPressedListener;
    private com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$OnClearListener defaultClearListener;
    public android.graphics.drawable.Drawable imgClearButton;
    boolean justCleared;
    private com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$OnClearListener onClearListener;

    static bridge synthetic com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$OnClearListener -$$Nest$fgetonClearListener(com.bisimplex.firebooru.view.ClearableAutoCompleteTextView p0)
    {
        return p0.onClearListener;
    }

    public ClearableAutoCompleteTextView(android.content.Context p1)
    {
        super(p1);
        super.justCleared = 0;
        com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$1 v1_3 = new com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$1(super);
        super.defaultClearListener = v1_3;
        super.onClearListener = v1_3;
        super.init();
        return;
    }

    public ClearableAutoCompleteTextView(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.justCleared = 0;
        com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$1 v1_3 = new com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$1(super);
        super.defaultClearListener = v1_3;
        super.onClearListener = v1_3;
        super.init();
        return;
    }

    public ClearableAutoCompleteTextView(android.content.Context p1, android.util.AttributeSet p2, int p3)
    {
        super(p1, p2, p3);
        super.justCleared = 0;
        com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$1 v1_3 = new com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$1(super);
        super.defaultClearListener = v1_3;
        super.onClearListener = v1_3;
        super.init();
        return;
    }

    public void hideClearButton()
    {
        this.setCompoundDrawables(0, 0, 0, 0);
        return;
    }

    void init()
    {
        com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$2 v0_4 = com.bisimplex.firebooru.fragment.BaseFragment.iconWithColor(this.getContext(), com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_times, com.bisimplex.firebooru.skin.SkinManager.getInstance().getActionBarIconColorRes());
        android.graphics.drawable.Drawable v1_2 = this.getContext().getResources().getDimensionPixelSize(2131166098);
        this.imgClearButton = v0_4;
        v0_4.setSizeXPx(v1_2);
        v0_4.setSizeYPx(v1_2);
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, this.imgClearButton, 0);
        this.setOnTouchListener(new com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$2(this));
        return;
    }

    public boolean onKeyPreIme(int p2, android.view.KeyEvent p3)
    {
        if ((p2 == 4) && (p3.getAction() == 1)) {
            com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$OnBackPressedListener v2_2 = this.backPressedListener;
            if (v2_2 != null) {
                v2_2.onBackPressed();
            }
        }
        return 0;
    }

    public void setBackPressedListener(com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$OnBackPressedListener p1)
    {
        this.backPressedListener = p1;
        return;
    }

    public void setImgClearButton(android.graphics.drawable.Drawable p1)
    {
        this.imgClearButton = p1;
        return;
    }

    public void setOnClearListener(com.bisimplex.firebooru.view.ClearableAutoCompleteTextView$OnClearListener p1)
    {
        this.onClearListener = p1;
        return;
    }

    public void showClearButton()
    {
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, this.imgClearButton, 0);
        return;
    }
}
