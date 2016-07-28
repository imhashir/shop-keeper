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

    private static final String KEY_STRING = "com.hashirbaig.creator.shopkeeper.Dialogs.SimpleTextDialog";
    private String mText;
    private TextView mTextView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mText = new String();
        mText = getArguments().getString(KEY_STRING);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_text_layout, null);
        mTextView = (TextView) v.findViewById(R.id.simple_dialog_text);
        mTextView.setText(mText);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

    public static SimpleTextDialog newInstance(String text) {
        Bundle args = new Bundle();
        args.putString(KEY_STRING, text);
        SimpleTextDialog fragment = new SimpleTextDialog();
        fragment.setArguments(args);
        return fragment;
    }

}
