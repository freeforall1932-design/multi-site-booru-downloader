package com.bisimplex.firebooru.activity;
 class MenuBaseActivity$38 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.activity.MenuBaseActivity this$0;
    final synthetic String val$message;
    final synthetic com.bisimplex.firebooru.activity.MessageType val$type;

    MenuBaseActivity$38(com.bisimplex.firebooru.activity.MenuBaseActivity p1, String p2, com.bisimplex.firebooru.activity.MessageType p3)
    {
        this.this$0 = p1;
        this.val$message = p2;
        this.val$type = p3;
        return;
    }

    public void run()
    {
        com.bisimplex.firebooru.activity.MenuBaseActivity.-$$Nest$mMainShowMessage(this.this$0, this.val$message, this.val$type);
        return;
    }
}
