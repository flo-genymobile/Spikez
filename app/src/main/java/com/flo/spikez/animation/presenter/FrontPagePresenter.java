package com.flo.spikez.animation.presenter;

import android.content.Context;
import com.flo.spikez.animation.BlendViewHolder;
import com.flo.spikez.animation.FrontPageView;
import com.flo.spikez.animation.animator.PageAnimator;
import com.flo.spikez.animation.animator.PageAnimatorCallback;
import com.flo.spikez.animation.model.FrontPage;

public class FrontPagePresenter implements PagePresenter, PageAnimatorCallback {

    private final FrontPage page;
    private FrontPageView view;

    public FrontPagePresenter(FrontPageView view, FrontPage page) {
        this.view = view;
        this.page = page;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void initiatePageDisplay(Context context, BlendViewHolder viewHolder) {
        if (view != null) {
            view.startPageDisplay(new PageAnimator(page.getBlendAnimationType(), context, this, viewHolder));
        }
    }

    @Override
    public void onBlendAnimationOver() {
        if (view != null) {
            view.onPageDisplayed();
        }
    }
}
