package com.bisimplex.firebooru.activity;
 class MenuBaseActivity$25 extends com.mikepenz.materialdrawer.model.PrimaryDrawerItem {
    final synthetic com.bisimplex.firebooru.activity.MenuBaseActivity this$0;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;

    MenuBaseActivity$25(com.bisimplex.firebooru.activity.MenuBaseActivity p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        this.this$0 = p1;
        this.val$post = p2;
        this.setName(new com.mikepenz.materialdrawer.holder.StringHolder(p2.getPostUrl()));
        this.setSelectable(1);
        this.setTag(com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowUrl);
        return;
    }
}
