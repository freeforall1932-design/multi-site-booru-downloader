package com.bisimplex.firebooru.danbooru;
public interface TransactionAction {

    public abstract void error(com.bisimplex.firebooru.danbooru.FailureType p0);

    public abstract void success();
}
