package com.bisimplex.firebooru.fragment;
 class FormBaseFragment$1 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.fragment.FormBaseFragment this$0;

    FormBaseFragment$1(com.bisimplex.firebooru.fragment.FormBaseFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void run()
    {
        this.this$0.HideLoading();
        this.this$0.successFinished();
        return;
    }
}
