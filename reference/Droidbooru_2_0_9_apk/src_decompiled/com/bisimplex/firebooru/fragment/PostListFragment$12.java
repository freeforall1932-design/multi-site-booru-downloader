package com.bisimplex.firebooru.fragment;
 class PostListFragment$12 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.PostListFragment this$0;
    final synthetic android.widget.CheckBox val$animatedCheckbox;
    final synthetic android.widget.CheckBox val$duplicateCheckbox;
    final synthetic java.util.List val$list;
    final synthetic com.bisimplex.firebooru.services.DownloadOptions val$options;
    final synthetic android.widget.CheckBox val$originalCheckBox;
    final synthetic com.bisimplex.firebooru.network.SourceQuery val$query;

    PostListFragment$12(com.bisimplex.firebooru.fragment.PostListFragment p1, com.bisimplex.firebooru.services.DownloadOptions p2, android.widget.CheckBox p3, android.widget.CheckBox p4, android.widget.CheckBox p5, com.bisimplex.firebooru.network.SourceQuery p6, java.util.List p7)
    {
        this.this$0 = p1;
        this.val$options = p2;
        this.val$animatedCheckbox = p3;
        this.val$duplicateCheckbox = p4;
        this.val$originalCheckBox = p5;
        this.val$query = p6;
        this.val$list = p7;
        return;
    }

    public void onClick(android.content.DialogInterface p4, int p5)
    {
        this.val$options.setExcludeAnimated(this.val$animatedCheckbox.isChecked());
        this.val$options.setAvoidDuplicates(this.val$duplicateCheckbox.isChecked());
        this.val$options.setDownloadOriginal(this.val$originalCheckBox.isChecked());
        if (this.this$0.getSource().getType() != com.bisimplex.firebooru.network.SourceType.Favorites) {
            com.bisimplex.firebooru.services.DownloadService.getInstance().enqueueWork(this.this$0.getContext(), this.val$list, this.val$options, this.val$query);
        } else {
            com.bisimplex.firebooru.fragment.PostListFragment.-$$Nest$mdownloadAllFavoritesWithQuery(this.this$0, this.val$options, this.val$query);
        }
        this.this$0.showMessage(2131886165, com.bisimplex.firebooru.activity.MessageType.Minimal);
        com.bisimplex.firebooru.fragment.PostListFragment.-$$Nest$fputbatchDownloadOptions(this.this$0, 0);
        return;
    }
}
