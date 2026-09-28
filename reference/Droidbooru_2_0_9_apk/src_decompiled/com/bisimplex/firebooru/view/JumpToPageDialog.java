package com.bisimplex.firebooru.view;
public class JumpToPageDialog extends androidx.fragment.app.DialogFragment {
    com.bisimplex.firebooru.view.JumpToPageDialog$JumpToPageDialogListener mListener;
    private android.widget.EditText numberEditText;

    static bridge synthetic android.widget.EditText -$$Nest$fgetnumberEditText(com.bisimplex.firebooru.view.JumpToPageDialog p0)
    {
        return p0.numberEditText;
    }

    public JumpToPageDialog()
    {
        return;
    }

    public void onAttach(android.content.Context p3)
    {
        super.onAttach(p3);
        try {
            this.mListener = ((com.bisimplex.firebooru.view.JumpToPageDialog$JumpToPageDialogListener) p3);
            return;
        } catch (ClassCastException) {
            throw new ClassCastException(new StringBuilder().append(p3.toString()).append(" must implement NoticeDialogListener").toString());
        }
    }

    public android.app.Dialog onCreateDialog(android.os.Bundle p4)
    {
        androidx.appcompat.app.AlertDialog v4_1 = new com.google.android.material.dialog.MaterialAlertDialogBuilder(this.getActivity());
        android.widget.EditText v0_9 = this.getActivity().getLayoutInflater();
        v4_1.setTitle(2131886733);
        android.widget.EditText v0_0 = v0_9.inflate(2131558514, 0);
        this.numberEditText = ((android.widget.EditText) v0_0.findViewById(2131362377));
        v4_1.setView(v0_0);
        v4_1.setPositiveButton(2131886997, new com.bisimplex.firebooru.view.JumpToPageDialog$1(this));
        v4_1.setNegativeButton(2131886205, new com.bisimplex.firebooru.view.JumpToPageDialog$2(this));
        this.numberEditText.setOnFocusChangeListener(new com.bisimplex.firebooru.view.JumpToPageDialog$3(this));
        this.numberEditText.setOnEditorActionListener(new com.bisimplex.firebooru.view.JumpToPageDialog$4(this));
        return v4_1.create();
    }

    public void onDestroyView()
    {
        android.app.Dialog v0 = this.getDialog();
        if ((v0 != null) && (this.getRetainInstance())) {
            v0.setDismissMessage(0);
        }
        super.onDestroyView();
        return;
    }

    public void onStart()
    {
        super.onStart();
        this.numberEditText.requestFocus();
        return;
    }
}
