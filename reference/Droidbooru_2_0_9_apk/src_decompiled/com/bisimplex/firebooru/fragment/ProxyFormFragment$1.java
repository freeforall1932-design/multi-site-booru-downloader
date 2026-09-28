package com.bisimplex.firebooru.fragment;
 class ProxyFormFragment$1 implements android.widget.RadioGroup$OnCheckedChangeListener {
    final synthetic com.bisimplex.firebooru.fragment.ProxyFormFragment this$0;

    ProxyFormFragment$1(com.bisimplex.firebooru.fragment.ProxyFormFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onCheckedChanged(android.widget.RadioGroup p2, int p3)
    {
        int v3_2;
        p2 = this.this$0;
        if (com.bisimplex.firebooru.fragment.ProxyFormFragment.-$$Nest$fgettypeGroup(p2).getCheckedRadioButtonId() == 2131362005) {
            v3_2 = 0;
        } else {
            v3_2 = 1;
        }
        com.bisimplex.firebooru.fragment.ProxyFormFragment.-$$Nest$menableControllers(p2, v3_2);
        return;
    }
}
