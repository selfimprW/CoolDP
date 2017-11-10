package com.yaerin.cooldp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by yaerin on 7/28/17.
 */

public class DownloadService extends IntentService {

    public static final String EXTRA_URL = ".DownloadService.EXTRA_URL";
    public static final String EXTRA_NAME = ".DownloadService.EXTRA_NAME";

    public DownloadService() {
        super("DownloadService");
    }

    public static void startAction(Context context, String url, String name) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_NAME, name);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) return;
        String url = intent.getStringExtra(EXTRA_URL);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
        String name = intent.getStringExtra(EXTRA_NAME);
        try {
            InputStream is = new URL(url).openStream();
            FileOutputStream fos = new FileOutputStream(path + File.separator + name);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            is.close();
            fos.close();
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                    .setData(Uri.fromFile(new File(path + File.separator + name))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
