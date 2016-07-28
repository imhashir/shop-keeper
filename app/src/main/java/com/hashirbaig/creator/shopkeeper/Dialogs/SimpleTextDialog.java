package com.hashirbaig.creator.shopkeeper.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hashirbaig.creator.shopkeeper.R;

import java.util.zip.Inflater;

public class SimpleTextDialog extends DialogFragment {

    private static final String KEY_DESC = "com.hashirbaig.creator.shopkeeper.Dialogs.SimpleTextDialog.keyDesc";
    private static final String KEY_TITLE = "com.hashirbaig.creator.shopkeeper.Dialogs.SimpleTextDialog.keyTitle";
    private String mDesc;
    private String mTitle;
    private TextView mTextView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mDesc = new String();
        mDesc = getArguments().getString(KEY_DESC);
        mTitle = getArguments().getString(KEY_TITLE);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_text_layout, null);
        mTextView = (TextView) v.findViewById(R.id.simple_dialog_text);
        mTextView.setText(mDesc);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(mTitle)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

    public static SimpleTextDialog newInstance(String desc, String title) {
        Bundle args = new Bundle();
        args.putString(KEY_DESC, desc);
        args.putString(KEY_TITLE, title);
        SimpleTextDialog fragment = new SimpleTextDialog();
        fragment.setArguments(args);
        return fragment;
    }

}
