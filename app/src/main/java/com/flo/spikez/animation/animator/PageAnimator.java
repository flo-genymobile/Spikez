package com.flo.spikez.animation.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import com.flo.spikez.R;
import com.flo.spikez.animation.BlendViewHolder;

public class PageAnimator {

    private ObjectAnimator layerOneAnimator;
    private ObjectAnimator layerTwoAnimator;
    private ObjectAnimator layerThreeAnimator;

    private ObjectAnimator titleAnimator;
    private ObjectAnimator subtitleAnimator;
    private ObjectAnimator detailsAnimator;

    private BlendViewHolder blendViewHolder;

    private PageAnimatorCallback callback;

    public PageAnimator(int blendAnimationType, Context context, PageAnimatorCallback callback, BlendViewHolder blendViewHolder) {
        this.layerOneAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.blend_a);
        this.layerTwoAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.blend_a);
        this.layerThreeAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.blend_a);

        this.titleAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.blend_a_fade_in);
        this.subtitleAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.blend_a_subtitle);
        this.detailsAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.blend_a);

        this.callback = callback;
        this.blendViewHolder = blendViewHolder;
    }

    public void startBlendAnimation() {
        blendViewHolder.subtitleView.setVisibility(View.VISIBLE);
        subtitleAnimator.addListener(subtitleListener);
        subtitleAnimator.setTarget(blendViewHolder.subtitleView);
        subtitleAnimator.start();
    }

    private Animator.AnimatorListener subtitleListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            blendViewHolder.titleView.setVisibility(View.VISIBLE);
            titleAnimator.setTarget(blendViewHolder.titleView);
            titleAnimator.start();

            blendViewHolder.firstLayer.setVisibility(View.VISIBLE);
            layerOneAnimator.addListener(firstLayerListener);
            layerOneAnimator.setTarget(blendViewHolder.firstLayer);
            layerOneAnimator.start();
        }

        @Override
        public void onAnimationCancel(Animator animator) {
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
        }
    };

    private Animator.AnimatorListener firstLayerListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            blendViewHolder.secondLayer.setVisibility(View.VISIBLE);
            layerTwoAnimator.setTarget(blendViewHolder.secondLayer);
            layerTwoAnimator.addListener(secondLayerListener);
            layerTwoAnimator.start();
        }

        @Override
        public void onAnimationCancel(Animator animator) {
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
        }
    };

    private Animator.AnimatorListener secondLayerListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            blendViewHolder.thirdLayer.setVisibility(View.VISIBLE);
            layerThreeAnimator.setTarget(blendViewHolder.thirdLayer);
            layerThreeAnimator.removeAllListeners();
            layerThreeAnimator.addListener(thirdLayerListener);
            layerThreeAnimator.start();
        }

        @Override
        public void onAnimationCancel(Animator animator) {
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
        }
    };

    private Animator.AnimatorListener thirdLayerListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            blendViewHolder.detailsView.setVisibility(View.VISIBLE);
            detailsAnimator.setTarget(blendViewHolder.detailsView);
            detailsAnimator.removeAllListeners();
            detailsAnimator.addListener(detailsListener);
            detailsAnimator.start();
        }

        @Override
        public void onAnimationCancel(Animator animator) {
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
        }
    };

    private Animator.AnimatorListener detailsListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            callback.onBlendAnimationOver();
        }

        @Override
        public void onAnimationCancel(Animator animator) {
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
        }
    };
}
