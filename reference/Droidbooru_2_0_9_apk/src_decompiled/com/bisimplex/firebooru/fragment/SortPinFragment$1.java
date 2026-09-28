package com.bisimplex.firebooru.fragment;
 class SortPinFragment$1 implements com.bisimplex.firebooru.dataadapter.SortPinItemHolder$SortPinItemListener {
    final synthetic com.bisimplex.firebooru.fragment.SortPinFragment this$0;

    SortPinFragment$1(com.bisimplex.firebooru.fragment.SortPinFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void deleteItem(int p2)
    {
        com.bisimplex.firebooru.fragment.SortPinFragment.-$$Nest$maskDeleteItem(this.this$0, p2);
        return;
    }

    public void editItem(int p2)
    {
        com.bisimplex.firebooru.fragment.SortPinFragment.-$$Nest$maskEditItem(this.this$0, p2);
        return;
    }

    public void moveBottom(int p2)
    {
        com.bisimplex.firebooru.fragment.SortPinFragment.-$$Nest$fgetadapter(this.this$0).moveBottom(p2);
        this.this$0.setEdited(1);
        return;
    }

    public void moveToFolder(int p2)
    {
        com.bisimplex.firebooru.fragment.SortPinFragment.-$$Nest$maskMoveToFolder(this.this$0, p2);
        return;
    }

    public void moveTop(int p2)
    {
        com.bisimplex.firebooru.fragment.SortPinFragment.-$$Nest$fgetadapter(this.this$0).moveTop(p2);
        this.this$0.setEdited(1);
        return;
    }

    public void openFolder(int p2)
    {
        com.bisimplex.firebooru.fragment.SortPinFragment.-$$Nest$mselectFolder(this.this$0, p2);
        return;
    }

    public void startDrag(com.bisimplex.firebooru.dataadapter.SortPinItemHolder p2)
    {
        com.bisimplex.firebooru.fragment.SortPinFragment.-$$Nest$fgetitemTouchHelper(this.this$0).startDrag(p2);
        return;
    }
}
