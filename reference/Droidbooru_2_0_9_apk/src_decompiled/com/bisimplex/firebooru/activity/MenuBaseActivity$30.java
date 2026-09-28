package com.bisimplex.firebooru.activity;
 class MenuBaseActivity$30 extends com.mikepenz.materialdrawer.model.PrimaryDrawerItem {
    final synthetic com.bisimplex.firebooru.activity.MenuBaseActivity this$0;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;

    MenuBaseActivity$30(com.bisimplex.firebooru.activity.MenuBaseActivity p3, com.bisimplex.firebooru.danbooru.DanbooruPost p4)
    {
        this.this$0 = p3;
        this.val$post = p4;
        this.setName(new com.mikepenz.materialdrawer.holder.StringHolder(p3.getString(2131886725, new Object[] {Integer.valueOf(p4.getScore())}))));
        this.setSelectable(0);
        return;
    }
}
