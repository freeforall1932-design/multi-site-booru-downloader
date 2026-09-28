package com.bisimplex.firebooru.dataadapter.search;
public class TagAutocompleteItem extends com.bisimplex.firebooru.dataadapter.search.TextItem {
    private String hint;
    private String serverID;

    public TagAutocompleteItem(String p1, String p2, String p3, boolean p4)
    {
        super(p1, p2, p3, p4);
        super.setEnterKeyAction(com.bisimplex.firebooru.dataadapter.search.ActionType.Search);
        super.setType(com.bisimplex.firebooru.dataadapter.search.ItemType.TagAutocomplete);
        return;
    }

    public String getHint()
    {
        return this.hint;
    }

    public String getServerID()
    {
        return this.serverID;
    }

    public void setHint(String p1)
    {
        this.hint = p1;
        return;
    }

    public void setServerID(String p1)
    {
        this.serverID = p1;
        return;
    }

    public bridge synthetic void setValue(Object p1)
    {
        this.setValue(((String) p1));
        return;
    }

    public void setValue(String p3)
    {
        android.util.Log.i("AUTOCOMPLETE", new StringBuilder("Value changed to: ").append(p3).toString());
        super.setValue(p3);
        return;
    }
}
