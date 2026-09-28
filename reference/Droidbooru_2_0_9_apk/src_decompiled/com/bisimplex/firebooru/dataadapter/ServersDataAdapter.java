package com.bisimplex.firebooru.dataadapter;
public class ServersDataAdapter extends android.widget.ArrayAdapter {
    private boolean enableDelete;
    private boolean highlightSelected;
    com.bisimplex.firebooru.dataadapter.ServersDataAdapter$ServersDataAdapterListener mListener;
    private int selectedServerID;

    public ServersDataAdapter(android.content.Context p2)
    {
        super(p2, 0);
        super.selectedServerID = -1;
        super.enableDelete = 1;
        super.highlightSelected = 1;
        return;
    }

    protected void deleteServerAt(int p2)
    {
        com.bisimplex.firebooru.dataadapter.ServersDataAdapter$ServersDataAdapterListener v0 = this.mListener;
        if (v0 != null) {
            v0.onDeleteServer(this, p2);
        }
        return;
    }

    public android.view.View getView(int p2, android.view.View p3, android.view.ViewGroup p4)
    {
        int v2_2 = ((com.bisimplex.firebooru.danbooru.ServerItem) this.getItem(p2));
        if (p3 == null) {
            p3 = android.view.LayoutInflater.from(this.getContext()).inflate(2131558635, 0);
        }
        int v2_3;
        ((android.widget.TextView) p3.findViewById(2131362621)).setText(v2_2.getServerName());
        ((android.widget.TextView) p3.findViewById(2131362620)).setText(v2_2.getExtraInfo());
        if (!this.highlightSelected) {
            v2_3 = 0;
        } else {
            if (this.selectedServerID <= 0) {
                v2_3 = v2_2.isSelected();
            } else {
                if (v2_2.getServerId() != this.selectedServerID) {
                } else {
                    v2_3 = 1;
                }
            }
        }
        if (v2_3 == 0) {
            p3.setBackgroundColor(0);
            return p3;
        } else {
            p3.setBackgroundColor(androidx.core.content.ContextCompat.getColor(this.getContext(), 2131099701));
            return p3;
        }
    }

    public boolean isHighlightSelected()
    {
        return this.highlightSelected;
    }

    public void setEnableDelete(boolean p1)
    {
        this.enableDelete = p1;
        return;
    }

    public void setHighlightSelected(boolean p1)
    {
        this.highlightSelected = p1;
        return;
    }

    public void setSelectedServerID(int p1)
    {
        this.selectedServerID = p1;
        return;
    }

    public void setServerAdapterListner(com.bisimplex.firebooru.dataadapter.ServersDataAdapter$ServersDataAdapterListener p1)
    {
        this.mListener = p1;
        return;
    }
}
