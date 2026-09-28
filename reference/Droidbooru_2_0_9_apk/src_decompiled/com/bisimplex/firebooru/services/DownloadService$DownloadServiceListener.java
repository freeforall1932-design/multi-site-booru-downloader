package com.bisimplex.firebooru.services;
public interface DownloadService$DownloadServiceListener {

    public abstract void changedEntryStatus(com.bisimplex.firebooru.model.DownloadEntry p0);

    public abstract void downloadsFinished();
}
