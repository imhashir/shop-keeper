package com.hashirbaig.creator.shopkeeper.MainFragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hashirbaig.creator.shopkeeper.Dialogs.SimpleTextDialog;
import com.hashirbaig.creator.shopkeeper.Helper.BackupRestore;
import com.hashirbaig.creator.shopkeeper.R;

import java.io.IOException;

public class BackupRestoreFragment extends Fragment {

    private Button mLocalBackup;
    private Button mLocalRestore;
    private Button mOnlineBackup;
    private Button mOnlineRestore;
    private LinearLayout mRootLayout;

    private static final String TAG_ERROR_BACKUP = "errorBackup";
    private static final String TAG_ERROR_RESTORE = "errorRestore";

    private static final int REQUEST_STORAGE_PERMISSION = 0;

    public static BackupRestoreFragment newInstance() {
        return new BackupRestoreFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat
                    .requestPermissions(
                            getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_STORAGE_PERMISSION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_backup_restore, container, false);

        mLocalBackup = (Button) v.findViewById(R.id.local_backup_button);
        mLocalRestore = (Button) v.findViewById(R.id.local_restore_button);
        mOnlineBackup = (Button) v.findViewById(R.id.online_backup_button);
        mOnlineRestore = (Button) v.findViewById(R.id.online_restore_button);
        mRootLayout = (LinearLayout) v.findViewById(R.id.root_layout_backup_restore);

        mLocalBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    localBackup();
                } else {
                    Snackbar.make(mRootLayout, "Necessary Permissions not granted", Snackbar.LENGTH_LONG);
                }
            }
        });

        mLocalRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    localRestore();
                } else {
                    Snackbar.make(mRootLayout, "Necessary Permissions not granted", Snackbar.LENGTH_LONG);
                }
            }
        });

        return v;
    }

    public void localBackup() {
        try {
            BackupRestore.Local.backupDatabase();
            Snackbar.make(mRootLayout, "Successfully backed up data", Snackbar.LENGTH_LONG).show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            SimpleTextDialog
                    .newInstance("An error occurred while backing up data", "Error!")
                    .show(getFragmentManager(), TAG_ERROR_BACKUP);
        }
    }

    private void localRestore() {
        try {
            BackupRestore.Local.restoreDatabase();
            Snackbar.make(mRootLayout, "Successfully restored data", Snackbar.LENGTH_LONG).show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            SimpleTextDialog
                    .newInstance("An error occurred while restoring data", "Error!")
                    .show(getFragmentManager(), TAG_ERROR_BACKUP);
        }
    }

}
