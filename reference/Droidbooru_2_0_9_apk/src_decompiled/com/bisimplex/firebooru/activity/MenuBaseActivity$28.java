package com.bisimplex.firebooru.activity;
 class MenuBaseActivity$28 extends com.mikepenz.materialdrawer.model.PrimaryDrawerItem {
    final synthetic com.bisimplex.firebooru.activity.MenuBaseActivity this$0;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;

    MenuBaseActivity$28(com.bisimplex.firebooru.activity.MenuBaseActivity p3, com.bisimplex.firebooru.danbooru.DanbooruPost p4)
    {
        this.this$0 = p3;
        this.val$post = p4;
        this.setName(new com.mikepenz.materialdrawer.holder.StringHolder(p3.getString(2131886717, new Object[] {p4.getAuthor()}))));
        this.setSelectable(1);
        this.setTag(com.bisimplex.firebooru.custom.SecondaryMenuActionType.Author);
        return;
    }
}
