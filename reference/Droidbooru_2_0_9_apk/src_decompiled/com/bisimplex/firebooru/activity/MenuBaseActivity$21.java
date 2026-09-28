package com.bisimplex.firebooru.activity;
 class MenuBaseActivity$21 extends com.mikepenz.materialdrawer.model.PrimaryDrawerItem {
    final synthetic com.bisimplex.firebooru.activity.MenuBaseActivity this$0;
    final synthetic boolean val$normalVisible;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;

    MenuBaseActivity$21(com.bisimplex.firebooru.activity.MenuBaseActivity p3, com.bisimplex.firebooru.danbooru.DanbooruPost p4, boolean p5)
    {
        com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon v5_1;
        this.this$0 = p3;
        this.val$post = p4;
        this.val$normalVisible = p5;
        this.setName(new com.mikepenz.materialdrawer.holder.StringHolder(2131887004));
        this.setDescription(new com.mikepenz.materialdrawer.holder.StringHolder(p4.getFile().renderResolution()));
        this.setSelectable(1);
        this.setIdentifier(((long) com.bisimplex.firebooru.custom.SecondaryMenuActionType.ShowOriginal.getValue()));
        if (p5 == null) {
            v5_1 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_check_circle;
        } else {
            v5_1 = com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome$Icon.faw_circle;
        }
        this.setIcon(new com.mikepenz.materialdrawer.iconics.IconicsImageHolder(v5_1));
        this.setBadge(new com.mikepenz.materialdrawer.holder.StringHolder(p4.getFile().getExtension()));
        return;
    }
}
