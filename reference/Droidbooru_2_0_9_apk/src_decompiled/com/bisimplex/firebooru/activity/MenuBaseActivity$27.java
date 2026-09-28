package com.bisimplex.firebooru.activity;
 class MenuBaseActivity$27 extends com.mikepenz.materialdrawer.model.PrimaryDrawerItem {
    final synthetic com.bisimplex.firebooru.activity.MenuBaseActivity this$0;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;

    MenuBaseActivity$27(com.bisimplex.firebooru.activity.MenuBaseActivity p3, com.bisimplex.firebooru.danbooru.DanbooruPost p4)
    {
        this.this$0 = p3;
        this.val$post = p4;
        this.setName(new com.mikepenz.materialdrawer.holder.StringHolder(p3.getString(2131886720, new Object[] {p4.getPostId()}))));
        this.setSelectable(1);
        this.setTag(com.bisimplex.firebooru.custom.SecondaryMenuActionType.ID);
        return;
    }
}
