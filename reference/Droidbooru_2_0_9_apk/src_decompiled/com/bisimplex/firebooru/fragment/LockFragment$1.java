package com.bisimplex.firebooru.fragment;
 class LockFragment$1 implements com.bisimplex.firebooru.lock.GestureLock$OnGestureEventListener {
    final synthetic com.bisimplex.firebooru.fragment.LockFragment this$0;

    LockFragment$1(com.bisimplex.firebooru.fragment.LockFragment p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onBlockSelected(int p3)
    {
        android.util.Log.d("position", new StringBuilder().append(p3).append("").toString());
        this.this$0.editLockInfo.add(String.valueOf(p3));
        return;
    }

    public void onGestureEvent(boolean p2)
    {
        this.this$0.lockInputFinished(p2);
        return;
    }

    public void onUnmatchedExceedBoundary()
    {
        this.this$0.showMessage("onUnmatchedExceedBoundary", com.bisimplex.firebooru.activity.MessageType.Minimal);
        return;
    }
}
