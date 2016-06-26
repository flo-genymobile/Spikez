package com.flo.spikez.animation;

import com.flo.spikez.animation.animator.PageAnimator;

public interface FrontPageView {
    void startPageDisplay(PageAnimator animator);
    void onPageDisplayed();
}