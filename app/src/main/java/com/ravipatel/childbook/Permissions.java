package com.ravipatel.childbook;

import android.Manifest;

/**
 * Created by User on 7/10/2017.
 */

public class Permissions {

    public static final String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,

    };



    public static final String[] WRITE_STORAGE_PERMISSION = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final String[] READ_STORAGE_PERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static final String[] RECORD_AUDIO = {
            Manifest.permission.RECORD_AUDIO
    };
}
