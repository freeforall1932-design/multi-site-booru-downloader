package com.bisimplex.firebooru.network;
public interface SourceListener {

    public abstract void failure(com.bisimplex.firebooru.network.Source p0, com.bisimplex.firebooru.data.FailureType p1);

    public abstract void reloadVisible();

    public abstract void success(com.bisimplex.firebooru.network.Source p0, java.util.List p1);
}
