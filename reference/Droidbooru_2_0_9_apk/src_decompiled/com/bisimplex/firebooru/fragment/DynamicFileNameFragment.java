package com.bisimplex.firebooru.fragment;
public class DynamicFileNameFragment extends com.bisimplex.firebooru.fragment.BaseFragment {
    com.bisimplex.firebooru.dataadapter.FileNameDataAdapter adapter;
    private final com.bisimplex.firebooru.dataadapter.search.DynamicFormAdapter$DynamicFormListener adapterListener;
    androidx.recyclerview.widget.RecyclerView recyclerView;

    static bridge synthetic void -$$Nest$msave(com.bisimplex.firebooru.fragment.DynamicFileNameFragment p0)
    {
        p0.save();
        return;
    }

    public DynamicFileNameFragment()
    {
        this.adapterListener = new com.bisimplex.firebooru.fragment.DynamicFileNameFragment$1(this);
        return;
    }

    private void save()
    {
        this.goBackInStack();
        return;
    }

    protected android.view.View getInsetContentView()
    {
        return this.recyclerView;
    }

    public boolean getShouldResetStack()
    {
        return 0;
    }

    public android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        return p2.inflate(2131558638, p3, 0);
    }

    public void onViewCreated(android.view.View p2, android.os.Bundle p3)
    {
        super.onViewCreated(p2, p3);
        androidx.appcompat.widget.Toolbar v2_6 = ((androidx.recyclerview.widget.RecyclerView) p2.findViewById(2131362449));
        this.recyclerView = v2_6;
        v2_6.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this.requireContext()));
        androidx.appcompat.widget.Toolbar v2_2 = new com.bisimplex.firebooru.dataadapter.FileNameDataAdapter(this.requireContext());
        this.adapter = v2_2;
        v2_2.setListener(this.adapterListener);
        this.recyclerView.setAdapter(this.adapter);
        this.getVisibleBar().setTitle(2131886469);
        return;
    }
}
