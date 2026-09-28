package com.bisimplex.firebooru.fragment;
 class HomeFragment$1 implements android.view.View$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.HomeFragment this$0;

    HomeFragment$1(com.bisimplex.firebooru.fragment.HomeFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onClick(android.view.View p3)
    {
        com.bisimplex.firebooru.activity.MainActivity v3_1 = new android.os.Bundle();
        if (!android.text.TextUtils.isEmpty(this.this$0.selectedGroupID)) {
            v3_1.putString("GROUP_ID", this.this$0.selectedGroupID);
        }
        com.bisimplex.firebooru.fragment.SortPinFragment v0_1 = new com.bisimplex.firebooru.fragment.SortPinFragment();
        v0_1.setArguments(v3_1);
        ((com.bisimplex.firebooru.activity.MainActivity) this.this$0.requireActivity()).switchContent(v0_1);
        return;
    }
}
