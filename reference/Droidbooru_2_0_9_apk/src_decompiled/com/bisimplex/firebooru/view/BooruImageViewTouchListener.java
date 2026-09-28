package com.bisimplex.firebooru.view;
public interface BooruImageViewTouchListener {

    public abstract boolean executeGesture(com.bisimplex.firebooru.view.GestureType p0);

    public abstract void tapOnNote(com.bisimplex.firebooru.danbooru.NoteItem p0);
}
