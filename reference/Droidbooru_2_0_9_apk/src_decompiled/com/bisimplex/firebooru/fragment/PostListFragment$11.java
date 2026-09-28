package com.bisimplex.firebooru.fragment;
 class PostListFragment$11 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.bisimplex.firebooru.fragment.PostListFragment this$0;
    final synthetic android.widget.CheckBox val$animatedCheckbox;
    final synthetic android.widget.CheckBox val$duplicateCheckbox;
    final synthetic com.bisimplex.firebooru.services.DownloadOptions val$options;
    final synthetic android.widget.CheckBox val$originalCheckBox;

    PostListFragment$11(com.bisimplex.firebooru.fragment.PostListFragment p1, com.bisimplex.firebooru.services.DownloadOptions p2, android.widget.CheckBox p3, android.widget.CheckBox p4, android.widget.CheckBox p5)
    {
        this.this$0 = p1;
        this.val$options = p2;
        this.val$animatedCheckbox = p3;
        this.val$duplicateCheckbox = p4;
        this.val$originalCheckBox = p5;
        return;
    }

    public void onClick(android.content.DialogInterface p1, int p2)
    {
        this.val$options.setExcludeAnimated(this.val$animatedCheckbox.isChecked());
        this.val$options.setAvoidDuplicates(this.val$duplicateCheckbox.isChecked());
        this.val$options.setDownloadOriginal(this.val$originalCheckBox.isChecked());
        com.bisimplex.firebooru.fragment.PostListFragment.-$$Nest$mtriggerStorageAccessFramework(this.this$0, 1);
        return;
    }
}
