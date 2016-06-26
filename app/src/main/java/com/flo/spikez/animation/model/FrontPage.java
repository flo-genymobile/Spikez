package com.flo.spikez.animation.model;

public class FrontPage {
    private final String backgroundImageUrl;
    private final int blendAnimationType;
    private final String subTitle;
    private String subtitle;

    public FrontPage(String backgroundImageUrl, String subTitle, int blendAnimationType) {
        this.backgroundImageUrl = backgroundImageUrl;
        this.subTitle = subTitle;
        this.blendAnimationType = blendAnimationType;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getBlendAnimationType() {
        return blendAnimationType;
    }
}
