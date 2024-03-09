package com.android.macromate.view.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.macromate.R;

import java.util.function.Consumer;

public class CreateDialog extends AppCompatDialogFragment {

    private final String title;
    private final Consumer<String> callback;
    private final Runnable cancelCallback;

    public CreateDialog(String title, Consumer<String> callback) {
        this.title = title;
        this.callback = callback;
        this.cancelCallback = () -> {};
    }

    public CreateDialog(String title, Consumer<String> callback, Runnable cancelCallback) {
        this.title = title;
        this.callback = callback;
        this.cancelCallback = cancelCallback;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_list, null);

        builder.setView(view)
                .setTitle(title)
                .setNegativeButton("cancel", (dialogInterface, i) -> {
                    cancelCallback.run();
                })
                .setPositiveButton("ok", (dialogInterface, i) -> okButtonAction(view));

        return builder.create();
    }

    private void okButtonAction(View view) {
        var input = ((EditText) view.findViewById(R.id.edit_username))
                .getText().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            callback.accept(input);
        }
    }

}
