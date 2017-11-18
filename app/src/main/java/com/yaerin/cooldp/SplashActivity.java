package com.yaerin.cooldp;

import android.app.Activity;
import android.app.WallpaperManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.github.florent37.glidepalette.GlidePalette;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SplashActivity extends Activity {

    private static final String TAG = "SplashActivity";

    private ImageView mWallpaper;
    private ProgressBar mProgress;
    private RelativeLayout mBottomSheet;
    private LinearLayout mSummary;
    private TextView mTitle;
    private TextView mDate;
    private ImageButton mBack;
    private Button mSet;
    private ImageButton mMore;
    private FrameLayout mSettings;

    private BottomSheetBehavior mBehavior;
    private DisplayMetrics mDisplay = new DisplayMetrics();
    private String baseUrl = "https://cn.bing.com";
    private String api = "https://cn.bing.com/HPImageArchive.aspx?format=js&n=1&idx=";
    private String mUrl = "http://cn.bing.com/ImageResolution.aspx";
    private int idx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindowManager().getDefaultDisplay().getRealMetrics(mDisplay);

        mWallpaper = findViewById(R.id.mWallpaper);
        mProgress = findViewById(R.id.mProgress);
        mBottomSheet = findViewById(R.id.mBottomSheet);
        mSummary = findViewById(R.id.mSummary);
        mTitle = findViewById(R.id.mTitle);
        mDate = findViewById(R.id.mDate);
        mBack = findViewById(R.id.mBack);
        mSet = findViewById(R.id.mSet);
        mMore = findViewById(R.id.mMore);
        mSettings = findViewById(R.id.mSettings);

        mBehavior = BottomSheetBehavior.from(mBottomSheet);
        mBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mSettings.setVisibility(View.GONE);
                    mMore.setImageResource(R.drawable.ic_expand_less_white_24dp);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        mWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mBehavior.getState()) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        break;
                }
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idx += 1;
                if (idx < 8) {
                    Toast.makeText(getString(R.string.loading, idx + ""), Toast.LENGTH_SHORT);
                    onRequest();
                } else {
                    Toast.makeText(R.string.none, Toast.LENGTH_SHORT);
                }
            }
        });
        mSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(R.string.setting, Toast.LENGTH_SHORT);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            WallpaperManager wm = WallpaperManager.getInstance(SplashActivity.this);
                            mWallpaper.getDrawable();
                            wm.setStream(new URL(mUrl).openStream());
                            Toast.makeText(R.string.set_ok, Toast.LENGTH_SHORT);
                        } catch (final Exception e) {
                            e.printStackTrace();
                            Toast.makeText(e.getMessage() + "", Toast.LENGTH_SHORT);
                        }
                    }
                }).start();
            }
        });
        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mSettings.getVisibility()) {
                    case View.GONE:
                        mSettings.setVisibility(View.VISIBLE);
                        mMore.setImageResource(R.drawable.ic_expand_more_white_24dp);
                        break;
                    case View.VISIBLE:
                        mSettings.setVisibility(View.GONE);
                        mMore.setImageResource(R.drawable.ic_expand_less_white_24dp);
                        break;
                    case View.INVISIBLE:
                        break;
                }
            }
        });

        onRequest();
    }

    private void onRequest() {
        OkHttpClient mClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(api + idx)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                ResponseBody body = response.body();
                if (body != null) {
                    Gson gson = new Gson();
                    BingApi bingApi = gson.fromJson(body.charStream(), BingApi.class);
                    BingApi.ImagesBean imagesBean = bingApi.getImages().get(0);
                    final String url = baseUrl + imagesBean.getUrlbase() +
                            "_" + mDisplay.widthPixels + "x" + mDisplay.heightPixels + ".jpg";
                    final String copyright = imagesBean.getCopyright();
                    final String date = imagesBean.getEnddate();
                    mUrl = url;
                    DownloadService.startAction(SplashActivity.this, url, url.split("/")[url.split("/").length - 1]);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(SplashActivity.this)
                                    .load(url)
                                    .apply(new RequestOptions()
                                            .placeholder(mWallpaper.getDrawable()))
                                    .transition(new DrawableTransitionOptions()
                                            .crossFade())
                                    .listener(GlidePalette.with(url)
                                            .use(GlidePalette.Profile.MUTED_LIGHT)
                                            .intoBackground(mSummary)
                                            .intoTextColor(mTitle, GlidePalette.Swatch.TITLE_TEXT_COLOR)
                                            .intoTextColor(mDate, GlidePalette.Swatch.BODY_TEXT_COLOR)
                                            .crossfade(true))
                                    .into(mWallpaper);
                            mTitle.setText(copyright);
                            mDate.setText(date);
                            mWallpaper.post(new Runnable() {
                                @Override
                                public void run() {
                                    mBehavior.setPeekHeight(mSummary.getMeasuredHeight());
                                    mProgress.setVisibility(View.GONE);
                                }
                            });
                        }
                    });
                }
            }
        });
    }

}
