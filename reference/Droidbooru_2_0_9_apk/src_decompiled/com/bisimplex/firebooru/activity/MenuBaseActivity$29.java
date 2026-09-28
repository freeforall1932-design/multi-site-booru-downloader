package com.bisimplex.firebooru.activity;
 class MenuBaseActivity$29 extends com.mikepenz.materialdrawer.model.PrimaryDrawerItem {
    final synthetic com.bisimplex.firebooru.activity.MenuBaseActivity this$0;
    final synthetic java.text.DateFormat val$format;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;

    MenuBaseActivity$29(com.bisimplex.firebooru.activity.MenuBaseActivity p2, java.text.DateFormat p3, com.bisimplex.firebooru.danbooru.DanbooruPost p4)
    {
        this.this$0 = p2;
        this.val$format = p3;
        this.val$post = p4;
        this.setName(new com.mikepenz.materialdrawer.holder.StringHolder(p2.getString(2131886723, new Object[] {p3.format(p4.getCreated_at())}))));
        this.setSelectable(0);
        return;
    }
}
