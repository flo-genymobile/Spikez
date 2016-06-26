package com.flo.spikez.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.view.WindowManager;
import com.flo.spikez.R;
import com.flo.spikez.animation.model.FrontPage;
import com.flo.spikez.animation.presenter.FrontPageFeedPresenter;

public class SpecialEditionActivity extends AppCompatActivity implements FrontFeedView, FrontPageFragment.FrontPagerCallback {

    private FrontPageFeedPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_edition);

        presenter = new FrontPageFeedPresenter(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        presenter.fetchFeed();
    }

    @Override
    public void showPage(FrontPage page) {
        FrontPageFragment fragment = FrontPageFragment.newInstance(page);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_content, fragment, "FRONT_PAGE_FRAGMENT")
                .commit();
    }

    @Override
    public void onNext() {
        presenter.loadNextPage();
    }
}

