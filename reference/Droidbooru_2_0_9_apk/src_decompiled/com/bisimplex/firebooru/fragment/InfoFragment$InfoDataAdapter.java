package com.bisimplex.firebooru.fragment;
public class InfoFragment$InfoDataAdapter extends android.widget.ArrayAdapter {
    final synthetic com.bisimplex.firebooru.fragment.InfoFragment this$0;

    public InfoFragment$InfoDataAdapter(com.bisimplex.firebooru.fragment.InfoFragment p1, android.content.Context p2)
    {
        this.this$0 = p1;
        super(p2, 0);
        return;
    }

    public android.view.View getView(int p5, android.view.View p6, android.view.ViewGroup p7)
    {
        android.widget.TextView v0_0;
        com.bisimplex.firebooru.fragment.InfoFragment$InfoItem v6_1 = ((com.bisimplex.firebooru.fragment.InfoFragment$InfoItem) this.getItem(p5));
        android.view.View v7_2 = android.view.LayoutInflater.from(this.getContext());
        if (!v6_1.isSeparator) {
            v0_0 = 2131558661;
        } else {
            v0_0 = 2131558502;
        }
        android.view.View v7_0 = v7_2.inflate(v0_0, 0);
        android.widget.TextView v0_3 = ((android.widget.TextView) v7_0.findViewById(2131362479));
        if (!v6_1.isSeparator) {
            int v1_5 = ((android.widget.ImageView) v7_0.findViewById(2131362178));
            if ((com.bisimplex.firebooru.fragment.InfoFragment.-$$Nest$fgetpost(this.this$0) == null) || (v1_5 == 0)) {
                v1_5.setVisibility(8);
            } else {
                v1_5.setVisibility(8);
                if (p5 != 1) {
                    if ((p5 == 4) && ((!com.bisimplex.firebooru.fragment.InfoFragment.-$$Nest$fgetpost(this.this$0).getSample().renderResolution().equalsIgnoreCase(com.bisimplex.firebooru.fragment.InfoFragment.-$$Nest$fgetpost(this.this$0).getFile().renderResolution())) && (com.bisimplex.firebooru.fragment.InfoFragment.-$$Nest$fgetpost(this.this$0).getFile() != com.bisimplex.firebooru.fragment.InfoFragment.-$$Nest$fgetpost(this.this$0).getVisibleVersion()))) {
                        v1_5.setVisibility(0);
                    }
                } else {
                    if ((!com.bisimplex.firebooru.fragment.InfoFragment.-$$Nest$fgetpost(this.this$0).getSample().renderResolution().equalsIgnoreCase(com.bisimplex.firebooru.fragment.InfoFragment.-$$Nest$fgetpost(this.this$0).getFile().renderResolution())) && (com.bisimplex.firebooru.fragment.InfoFragment.-$$Nest$fgetpost(this.this$0).getSample() != com.bisimplex.firebooru.fragment.InfoFragment.-$$Nest$fgetpost(this.this$0).getVisibleVersion())) {
                        v1_5.setVisibility(0);
                    }
                }
            }
            v0_3.setTextColor(this.this$0.getResources().getColor(com.bisimplex.firebooru.danbooru.TagItem.getColorIdByType(v6_1.colorId)));
        }
        v0_3.setText(v6_1.tag);
        return v7_0;
    }
}
