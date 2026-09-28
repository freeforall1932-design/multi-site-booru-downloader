package com.bisimplex.firebooru.activity;
 class MainActivity$4 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.activity.MainActivity this$0;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;

    MainActivity$4(com.bisimplex.firebooru.activity.MainActivity p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        this.this$0 = p1;
        this.val$post = p2;
        return;
    }

    public void onClick(android.content.DialogInterface p4, int p5)
    {
        com.bisimplex.firebooru.activity.MainActivity v4_1 = this.this$0.findOnScreenSource();
        if (v4_1 != null) {
            int v5_2 = new java.util.ArrayList(1);
            v5_2.add(v4_1.getProvider().getServerDescription());
            com.bisimplex.firebooru.danbooru.DatabaseHelper.getInstance().addBannedTag(String.format("%s%s", new Object[] {"user:", this.val$post.getAuthor()})), v5_2);
            v4_1.getProvider().updateBannedTags();
            this.this$0.ShowMessage(2131886179, com.bisimplex.firebooru.activity.MessageType.Success);
        }
        return;
    }
}
