package com.bisimplex.firebooru.danbooru;
public interface SearchTagTransactionAction {

    public abstract void failure(com.bisimplex.firebooru.danbooru.FailureType p0);

    public abstract void success(java.util.List p0);
}
