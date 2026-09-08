package com.fongmi.android.tv.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.DialogFragment;

import com.fongmi.android.tv.R;
import com.fongmi.android.tv.event.RefreshEvent;
import com.fongmi.android.tv.setting.Setting;

public class ThemeDialog extends DialogFragment {

    public interface Listener {
        void setTheme(int color);
    }

    private Listener listener;

    public static void show(FragmentActivity activity) {
        ThemeDialog dialog = new ThemeDialog();
        dialog.show(activity.getSupportFragmentManager(), "theme");
    }

    public static void show(FragmentActivity activity, Listener listener) {
        ThemeDialog dialog = new ThemeDialog();
        dialog.listener = listener;
        dialog.show(activity.getSupportFragmentManager(), "theme");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String[] themes = {"系统默认", "自定义颜色", "关闭"};
        return new AlertDialog.Builder(requireActivity())
                .setTitle(R.string.setting_theme_color)
                .setSingleChoiceItems(themes, 0, (dialog, which) -> {
                    Setting.putThemeColor(which == 0 ? -1 : 0);
                    if (listener != null) listener.setTheme(Setting.getThemeColor());
                    RefreshEvent.theme();
                    dialog.dismiss();
                }).create();
    }
}
