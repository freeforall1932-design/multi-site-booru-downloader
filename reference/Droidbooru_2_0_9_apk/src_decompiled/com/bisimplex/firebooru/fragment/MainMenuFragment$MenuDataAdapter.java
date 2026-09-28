package com.bisimplex.firebooru.fragment;
public class MainMenuFragment$MenuDataAdapter extends android.widget.ArrayAdapter {
    final synthetic com.bisimplex.firebooru.fragment.MainMenuFragment this$0;

    public MainMenuFragment$MenuDataAdapter(com.bisimplex.firebooru.fragment.MainMenuFragment p1, android.content.Context p2)
    {
        this.this$0 = p1;
        super(p2, 0);
        return;
    }

    public android.view.View getView(int p6, android.view.View p7, android.view.ViewGroup p8)
    {
        if (p7 == null) {
            p7 = android.view.LayoutInflater.from(this.getContext()).inflate(2131558615, 0);
        }
        String v6_2 = ((com.bisimplex.firebooru.fragment.MainMenuFragment$CustomMenuItem) this.getItem(p6));
        com.bisimplex.firebooru.fragment.MainMenuFragment$MenuDataAdapter$1 v8_2 = ((android.widget.ImageView) p7.findViewById(2131362475));
        if (v6_2.iconRes > 0) {
            v8_2.setImageResource(v6_2.iconRes);
        }
        android.widget.TextView v0_4 = ((android.widget.TextView) p7.findViewById(2131362479));
        android.widget.Button v1_2 = ((android.widget.Button) p7.findViewById(2131362433));
        if (v6_2.oId < 0) {
            v8_2.setVisibility(8);
            v0_4.setVisibility(8);
            v1_2.setVisibility(0);
            v1_2.setOnClickListener(new com.bisimplex.firebooru.fragment.MainMenuFragment$MenuDataAdapter$1(this));
        } else {
            v0_4.setText(v6_2.tag);
            v8_2.setVisibility(0);
            v0_4.setVisibility(0);
            v1_2.setVisibility(8);
        }
        p7.setTag(String.valueOf(v6_2.oId));
        return p7;
    }
}
