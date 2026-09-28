package com.bisimplex.firebooru.view;
public interface SourceSpecsDialog$SourceSpecsDialogDialogListener {

    public abstract void onDialogNegativeClick(androidx.fragment.app.DialogFragment p0);

    public abstract void onDialogNewFolderClick(androidx.fragment.app.DialogFragment p0, String p1, com.bisimplex.firebooru.network.SourceType p2);

    public abstract void onDialogSpecsDuplicated(androidx.fragment.app.DialogFragment p0, com.bisimplex.firebooru.model.SourceSpecs p1, com.bisimplex.firebooru.model.SourceSpecs p2, com.bisimplex.firebooru.model.SourceSpecs p3);

    public abstract void onDialogSpecsSaved(androidx.fragment.app.DialogFragment p0, com.bisimplex.firebooru.model.SourceSpecs p1, boolean p2);
}
