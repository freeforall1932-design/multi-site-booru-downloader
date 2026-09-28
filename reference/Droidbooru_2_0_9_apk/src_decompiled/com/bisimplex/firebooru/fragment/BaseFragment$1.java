package com.bisimplex.firebooru.fragment;
 class BaseFragment$1 implements androidx.core.view.OnApplyWindowInsetsListener {
    final synthetic com.bisimplex.firebooru.fragment.BaseFragment this$0;

    BaseFragment$1(com.bisimplex.firebooru.fragment.BaseFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public androidx.core.view.WindowInsetsCompat onApplyWindowInsets(android.view.View p8, androidx.core.view.WindowInsetsCompat p9)
    {
        androidx.core.graphics.Insets v8_1 = p9.getInsets(androidx.core.view.WindowInsetsCompat$Type.displayCutout());
        androidx.core.graphics.Insets v0_1 = p9.getInsets(androidx.core.view.WindowInsetsCompat$Type.systemBars());
        if (com.bisimplex.firebooru.fragment.BaseFragment.-$$Nest$fgettopAppBar(this.this$0) != null) {
            com.bisimplex.firebooru.fragment.BaseFragment.-$$Nest$fgettopAppBar(this.this$0).setPadding((v8_1.left + v0_1.left), Math.max(v8_1.top, v0_1.top), (v8_1.right + v0_1.right), 0);
        }
        if (com.bisimplex.firebooru.fragment.BaseFragment.-$$Nest$fgetbottomAppBar(this.this$0) != null) {
            com.bisimplex.firebooru.fragment.BaseFragment.-$$Nest$fgetbottomAppBar(this.this$0).setPadding((v8_1.left + v0_1.left), 0, (v8_1.right + v0_1.right), v0_1.bottom);
        }
        this.this$0.configureInsets(v8_1, v0_1);
        return p9;
    }
}
