package com.bisimplex.firebooru.activity;
 class MainActivity$3 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.activity.MainActivity this$0;
    final synthetic com.bisimplex.firebooru.danbooru.DanbooruPost val$post;

    MainActivity$3(com.bisimplex.firebooru.activity.MainActivity p1, com.bisimplex.firebooru.danbooru.DanbooruPost p2)
    {
        this.this$0 = p1;
        this.val$post = p2;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        this.this$0.copyToClipboard(this.val$post.getAuthor());
        this.this$0.ShowMessage(2131886280, com.bisimplex.firebooru.activity.MessageType.Success);
        return;
    }
}
