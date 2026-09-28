package com.bisimplex.firebooru.fragment;
public interface IBaseFragment {

    public abstract void HideLoading();

    public abstract void ShowLoading();

    public abstract com.bisimplex.firebooru.fragment.IBaseFragment getFramentCompanion();

    public abstract boolean getShouldResetStack();

    public abstract String getiOsFragmentName();

    public abstract void menuOpened();

    public abstract void setTitle(int p0);

    public abstract void setTitle(String p0);

    public abstract void switchFragment(androidx.fragment.app.Fragment p0);
}
