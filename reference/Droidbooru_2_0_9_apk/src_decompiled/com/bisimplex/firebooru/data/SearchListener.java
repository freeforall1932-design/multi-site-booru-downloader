package com.bisimplex.firebooru.data;
public interface SearchListener {

    public abstract void failure(com.bisimplex.firebooru.data.Source p0, com.bisimplex.firebooru.data.FailureType p1);

    public abstract void success(com.bisimplex.firebooru.data.Source p0);
}
