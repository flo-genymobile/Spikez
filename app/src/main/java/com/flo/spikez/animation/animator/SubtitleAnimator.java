package com.flo.spikez.animation.animator;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import com.flo.spikez.R;

public class SubtitleAnimator {

    private View subtiteView;
    private ObjectAnimator subtitleAnimator;

    public SubtitleAnimator(int blendAnimationType, Context context, View subtiteView) {
        this.subtiteView = subtiteView;
        this.subtitleAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.blend_a);
    }

    public void startSubtitleAnimation() {
        subtiteView.setVisibility(View.VISIBLE);
        subtitleAnimator.setTarget(subtiteView);
        subtitleAnimator.start();
    }
}
