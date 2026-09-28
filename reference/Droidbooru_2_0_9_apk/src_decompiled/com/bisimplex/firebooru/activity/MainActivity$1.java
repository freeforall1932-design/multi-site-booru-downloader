package com.bisimplex.firebooru.activity;
 class MainActivity$1 implements androidx.fragment.app.FragmentManager$OnBackStackChangedListener {
    final synthetic com.bisimplex.firebooru.activity.MainActivity this$0;

    MainActivity$1(com.bisimplex.firebooru.activity.MainActivity p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onBackStackChanged()
    {
        com.bisimplex.firebooru.activity.MainActivity v0 = this.this$0;
        v0.mContent = v0.getSupportFragmentManager().findFragmentById(2131361964);
        return;
    }
}
