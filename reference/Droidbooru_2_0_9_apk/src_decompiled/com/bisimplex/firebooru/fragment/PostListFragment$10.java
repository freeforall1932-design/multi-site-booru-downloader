package com.bisimplex.firebooru.fragment;
 class PostListFragment$10 implements android.content.DialogInterface$OnDismissListener {
    final synthetic com.bisimplex.firebooru.fragment.PostListFragment this$0;

    PostListFragment$10(com.bisimplex.firebooru.fragment.PostListFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onDismiss(android.content.DialogInterface p1)
    {
        this.this$0.checkNotificationPermission();
        return;
    }
}
