package com.bisimplex.firebooru.fragment;
 class RestoreFragment$3 implements androidx.activity.result.ActivityResultCallback {
    final synthetic com.bisimplex.firebooru.fragment.RestoreFragment this$0;

    RestoreFragment$3(com.bisimplex.firebooru.fragment.RestoreFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onActivityResult(android.net.Uri p3)
    {
        if (p3 != null) {
            com.bisimplex.firebooru.fragment.RestoreFragment v0_2 = this.this$0.getActivity();
            if (v0_2 != null) {
                com.bisimplex.firebooru.fragment.RestoreFragment v0_3 = v0_2.getContentResolver();
                if (v0_3 != null) {
                    v0_3.takePersistableUriPermission(p3, 1);
                    this.this$0.loadFile(p3);
                    return;
                }
            }
        }
        return;
    }

    public bridge synthetic void onActivityResult(Object p1)
    {
        this.onActivityResult(((android.net.Uri) p1));
        return;
    }
}
