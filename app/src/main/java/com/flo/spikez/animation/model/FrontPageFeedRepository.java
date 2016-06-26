package com.flo.spikez.animation.model;

import java.util.Collections;

public class FrontPageFeedRepository implements Repository {
    @Override
    public FrontPageFeed fetchFeed() {
        FrontPage page = new FrontPage("", "voir le film de la nuit", 0);
        return new FrontPageFeed(Collections.singletonList(page));
    }
}
