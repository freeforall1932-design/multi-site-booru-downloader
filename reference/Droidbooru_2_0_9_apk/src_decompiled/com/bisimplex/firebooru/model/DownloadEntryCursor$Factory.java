package com.bisimplex.firebooru.model;
final class DownloadEntryCursor$Factory implements io.objectbox.internal.CursorFactory {

    DownloadEntryCursor$Factory()
    {
        return;
    }

    public io.objectbox.Cursor createCursor(io.objectbox.Transaction p2, long p3, io.objectbox.BoxStore p5)
    {
        return new com.bisimplex.firebooru.model.DownloadEntryCursor(p2, p3, p5);
    }
}
