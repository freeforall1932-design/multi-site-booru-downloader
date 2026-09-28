package com.bisimplex.firebooru.dataadapter.search;
public class ServerSelectorItem extends com.bisimplex.firebooru.dataadapter.search.CheckboxItem {
    private static final String SERVER_PREFIX = "server_";
    private com.bisimplex.firebooru.danbooru.ServerItem serverItem;

    public ServerSelectorItem(com.bisimplex.firebooru.danbooru.ServerItem p3, Boolean p4, boolean p5)
    {
        super(new StringBuilder("server_").append(String.valueOf(p3.getServerId())).toString(), 0, p4, p5);
        super.setLabel(String.format("%s\n%s", new Object[] {p3.getServerName(), p3.getExtraInfo()})));
        super.serverItem = p3;
        return;
    }

    public com.bisimplex.firebooru.danbooru.ServerItem getServerItem()
    {
        return this.serverItem;
    }
}
