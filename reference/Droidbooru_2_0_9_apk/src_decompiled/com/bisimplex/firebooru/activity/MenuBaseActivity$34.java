package com.bisimplex.firebooru.activity;
 class MenuBaseActivity$34 extends com.mikepenz.materialdrawer.model.PrimaryDrawerItem {
    final synthetic com.bisimplex.firebooru.activity.MenuBaseActivity this$0;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;

    MenuBaseActivity$34(com.bisimplex.firebooru.activity.MenuBaseActivity p3, com.bisimplex.firebooru.danbooru.DanbooruPost p4)
    {
        Object[] v4_1;
        this.this$0 = p3;
        this.val$post = p4;
        if (!android.text.TextUtils.isEmpty(p4.getParent_id())) {
            v4_1 = p4.getParent_id();
        } else {
            v4_1 = "";
        }
        this.setName(new com.mikepenz.materialdrawer.holder.StringHolder(p3.getString(2131886722, new Object[] {v4_1}))));
        this.setSelectable(1);
        this.setTag(com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowParent);
        return;
    }
}
