package com.flo.spikez.animation;

import android.view.View;

public class BlendViewHolder {
    public View firstLayer;
    public View secondLayer;
    public View thirdLayer;

    public View titleView;
    public View subtitleView;
    public View detailsView;

    public BlendViewHolder(View firstLayer, View secondLayer, View thirdLayer, View titleView, View subtitleView,
                           View detailsView) {
        this.firstLayer = firstLayer;
        this.secondLayer = secondLayer;
        this.thirdLayer = thirdLayer;
        this.titleView = titleView;
        this.subtitleView = subtitleView;
        this.detailsView = detailsView;
    }
}
