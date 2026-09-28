package com.bisimplex.firebooru.view;
 class JumpToPageDialog$3$1 implements java.lang.Runnable {
    final synthetic com.bisimplex.firebooru.view.JumpToPageDialog$3 this$1;

    JumpToPageDialog$3$1(com.bisimplex.firebooru.view.JumpToPageDialog$3 p1)
    {
        this.this$1 = p1;
        return;
    }

    public void run()
    {
        ((android.view.inputmethod.InputMethodManager) this.this$1.this$0.requireActivity().getSystemService("input_method")).showSoftInput(com.bisimplex.firebooru.view.JumpToPageDialog.-$$Nest$fgetnumberEditText(this.this$1.this$0), 1);
        return;
    }
}
