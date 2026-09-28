package com.bisimplex.firebooru.fragment;
 class ServerFormFragment$2 implements android.widget.AdapterView$OnItemSelectedListener {
    final synthetic com.bisimplex.firebooru.fragment.ServerFormFragment this$0;

    ServerFormFragment$2(com.bisimplex.firebooru.fragment.ServerFormFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onItemSelected(android.widget.AdapterView p1, android.view.View p2, int p3, long p4)
    {
        this.this$0.typeChanged();
        return;
    }

    public void onNothingSelected(android.widget.AdapterView p1)
    {
        return;
    }
}
