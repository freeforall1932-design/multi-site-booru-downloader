package com.bisimplex.firebooru.activity;
 class MenuBaseActivity$32 extends com.mikepenz.materialdrawer.model.PrimaryDrawerItem {
    final synthetic com.bisimplex.firebooru.activity.MenuBaseActivity this$0;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;

    MenuBaseActivity$32(com.bisimplex.firebooru.activity.MenuBaseActivity p3, com.bisimplex.firebooru.danbooru.DanbooruPost p4)
    {
        Object[] v4_4;
        this.this$0 = p3;
        this.val$post = p4;
        if (!p4.getHas_notes()) {
            v4_4 = 2131886982;
        } else {
            v4_4 = 2131887271;
        }
        this.setName(new com.mikepenz.materialdrawer.holder.StringHolder(p3.getString(2131886719, new Object[] {p3.getString(v4_4)}))));
        this.setSelectable(0);
        return;
    }
}
