package com.bisimplex.firebooru.dataadapter;
public class SortPinItemHolder extends androidx.recyclerview.widget.RecyclerView$ViewHolder {
    com.mikepenz.iconics.view.IconicsImageView bottomButton;
    com.mikepenz.iconics.view.IconicsImageView deleteButton;
    android.widget.TextView descriptionTextView;
    com.mikepenz.iconics.view.IconicsImageView dragButton;
    com.mikepenz.iconics.view.IconicsImageView editButton;
    com.mikepenz.iconics.view.IconicsImageView folderButton;
    com.mikepenz.iconics.view.IconicsImageView iconImageView;
    ref.WeakReference listenerWeakReference;
    com.mikepenz.iconics.view.IconicsImageView moveButton;
    android.widget.TextView titleTextView;
    com.mikepenz.iconics.view.IconicsImageView topButton;

    public static synthetic void $r8$lambda$Qq2vipy6UFlXb4PZHHBdfbn5L5Y(com.bisimplex.firebooru.dataadapter.SortPinItemHolder p0, android.view.View p1)
    {
        p0.lambda$new$5(p1);
        return;
    }

    public static synthetic void $r8$lambda$UxSRs-xh5ZKjAmRNsmLZVBQQIqg(com.bisimplex.firebooru.dataadapter.SortPinItemHolder p0, android.view.View p1)
    {
        p0.lambda$new$4(p1);
        return;
    }

    public static synthetic boolean $r8$lambda$UyD1OgkrkhQoQqihxNFHg-r6OII(com.bisimplex.firebooru.dataadapter.SortPinItemHolder p0, android.view.View p1, android.view.MotionEvent p2)
    {
        return p0.lambda$new$0(p1, p2);
    }

    public static synthetic void $r8$lambda$VauKo85uinS1Pn6GAhi1laH1ok0(com.bisimplex.firebooru.dataadapter.SortPinItemHolder p0, android.view.View p1)
    {
        p0.lambda$new$1(p1);
        return;
    }

    public static synthetic void $r8$lambda$lgNH6ZyPy__2613vyLR5kmgLR5E(com.bisimplex.firebooru.dataadapter.SortPinItemHolder p0, android.view.View p1)
    {
        p0.lambda$new$6(p1);
        return;
    }

    public static synthetic void $r8$lambda$uj4ThhQgc-rTmZh2VV2d4LArNOg(com.bisimplex.firebooru.dataadapter.SortPinItemHolder p0, android.view.View p1)
    {
        p0.lambda$new$2(p1);
        return;
    }

    public static synthetic void $r8$lambda$vxVHP5ofpSq6w2EYWXQM4SrydUw(com.bisimplex.firebooru.dataadapter.SortPinItemHolder p0, android.view.View p1)
    {
        p0.lambda$new$3(p1);
        return;
    }

    public SortPinItemHolder(android.view.View p2, com.bisimplex.firebooru.dataadapter.SortPinItemHolder$SortPinItemListener p3)
    {
        super(p2);
        super.titleTextView = ((android.widget.TextView) p2.findViewById(2131362651));
        super.descriptionTextView = ((android.widget.TextView) p2.findViewById(2131361992));
        super.iconImageView = ((com.mikepenz.iconics.view.IconicsImageView) p2.findViewById(2131362169));
        super.dragButton = ((com.mikepenz.iconics.view.IconicsImageView) p2.findViewById(2131362012));
        super.deleteButton = ((com.mikepenz.iconics.view.IconicsImageView) p2.findViewById(2131361985));
        super.editButton = ((com.mikepenz.iconics.view.IconicsImageView) p2.findViewById(2131362028));
        super.topButton = ((com.mikepenz.iconics.view.IconicsImageView) p2.findViewById(2131362658));
        super.bottomButton = ((com.mikepenz.iconics.view.IconicsImageView) p2.findViewById(2131361916));
        super.folderButton = ((com.mikepenz.iconics.view.IconicsImageView) p2.findViewById(2131362126));
        super.moveButton = ((com.mikepenz.iconics.view.IconicsImageView) p2.findViewById(2131362315));
        super.listenerWeakReference = new ref.WeakReference(p3);
        super.dragButton.setOnTouchListener(new com.bisimplex.firebooru.dataadapter.SortPinItemHolder$$ExternalSyntheticLambda0(super));
        super.deleteButton.setOnClickListener(new com.bisimplex.firebooru.dataadapter.SortPinItemHolder$$ExternalSyntheticLambda1(super));
        super.editButton.setOnClickListener(new com.bisimplex.firebooru.dataadapter.SortPinItemHolder$$ExternalSyntheticLambda2(super));
        super.topButton.setOnClickListener(new com.bisimplex.firebooru.dataadapter.SortPinItemHolder$$ExternalSyntheticLambda3(super));
        super.bottomButton.setOnClickListener(new com.bisimplex.firebooru.dataadapter.SortPinItemHolder$$ExternalSyntheticLambda4(super));
        super.folderButton.setOnClickListener(new com.bisimplex.firebooru.dataadapter.SortPinItemHolder$$ExternalSyntheticLambda5(super));
        super.moveButton.setOnClickListener(new com.bisimplex.firebooru.dataadapter.SortPinItemHolder$$ExternalSyntheticLambda6(super));
        return;
    }

    private synthetic boolean lambda$new$0(android.view.View p1, android.view.MotionEvent p2)
    {
        if (p2.getActionMasked() == 0) {
            com.bisimplex.firebooru.dataadapter.SortPinItemHolder$SortPinItemListener v1_4 = this.listenerWeakReference;
            if ((v1_4 != null) && (v1_4.get() != null)) {
                ((com.bisimplex.firebooru.dataadapter.SortPinItemHolder$SortPinItemListener) this.listenerWeakReference.get()).startDrag(this);
            }
        }
        return 0;
    }

    private synthetic void lambda$new$1(android.view.View p2)
    {
        int v2_0 = this.listenerWeakReference;
        if ((v2_0 != 0) && (v2_0.get() != null)) {
            int v2_2 = this.getBindingAdapterPosition();
            if (v2_2 != -1) {
                ((com.bisimplex.firebooru.dataadapter.SortPinItemHolder$SortPinItemListener) this.listenerWeakReference.get()).deleteItem(v2_2);
            }
        }
        return;
    }

    private synthetic void lambda$new$2(android.view.View p2)
    {
        int v2_0 = this.listenerWeakReference;
        if ((v2_0 != 0) && (v2_0.get() != null)) {
            int v2_2 = this.getBindingAdapterPosition();
            if (v2_2 != -1) {
                ((com.bisimplex.firebooru.dataadapter.SortPinItemHolder$SortPinItemListener) this.listenerWeakReference.get()).editItem(v2_2);
            }
        }
        return;
    }

    private synthetic void lambda$new$3(android.view.View p2)
    {
        int v2_0 = this.listenerWeakReference;
        if ((v2_0 != 0) && (v2_0.get() != null)) {
            int v2_2 = this.getBindingAdapterPosition();
            if (v2_2 != -1) {
                ((com.bisimplex.firebooru.dataadapter.SortPinItemHolder$SortPinItemListener) this.listenerWeakReference.get()).moveTop(v2_2);
            }
        }
        return;
    }

    private synthetic void lambda$new$4(android.view.View p2)
    {
        int v2_0 = this.listenerWeakReference;
        if ((v2_0 != 0) && (v2_0.get() != null)) {
            int v2_2 = this.getBindingAdapterPosition();
            if (v2_2 != -1) {
                ((com.bisimplex.firebooru.dataadapter.SortPinItemHolder$SortPinItemListener) this.listenerWeakReference.get()).moveBottom(v2_2);
            }
        }
        return;
    }

    private synthetic void lambda$new$5(android.view.View p2)
    {
        int v2_0 = this.listenerWeakReference;
        if ((v2_0 != 0) && (v2_0.get() != null)) {
            int v2_2 = this.getBindingAdapterPosition();
            if (v2_2 != -1) {
                ((com.bisimplex.firebooru.dataadapter.SortPinItemHolder$SortPinItemListener) this.listenerWeakReference.get()).openFolder(v2_2);
            }
        }
        return;
    }

    private synthetic void lambda$new$6(android.view.View p2)
    {
        int v2_0 = this.listenerWeakReference;
        if ((v2_0 != 0) && (v2_0.get() != null)) {
            int v2_2 = this.getBindingAdapterPosition();
            if (v2_2 != -1) {
                ((com.bisimplex.firebooru.dataadapter.SortPinItemHolder$SortPinItemListener) this.listenerWeakReference.get()).moveToFolder(v2_2);
            }
        }
        return;
    }
}
