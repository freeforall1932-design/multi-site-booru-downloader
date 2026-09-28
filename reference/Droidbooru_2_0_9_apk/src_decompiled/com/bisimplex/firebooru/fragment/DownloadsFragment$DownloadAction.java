package com.bisimplex.firebooru.fragment;
public final enum class DownloadsFragment$DownloadAction extends java.lang.Enum {
    private static final synthetic com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction[] $VALUES;
    public static final enum com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction Copy;
    public static final enum com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction Retry;

    private static synthetic com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction[] $values()
    {
        return new com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction[] {com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction.Copy, com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction.Retry});
    }

    static DownloadsFragment$DownloadAction()
    {
        com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction.Copy = new com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction("Copy", 0);
        com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction.Retry = new com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction("Retry", 1);
        com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction.$VALUES = com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction.$values();
        return;
    }

    private DownloadsFragment$DownloadAction(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction valueOf(String p1)
    {
        return ((com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction) Enum.valueOf(com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction, p1));
    }

    public static com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction[] values()
    {
        return ((com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction[]) com.bisimplex.firebooru.fragment.DownloadsFragment$DownloadAction.$VALUES.clone());
    }
}
