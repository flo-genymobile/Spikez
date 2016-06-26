package com.flo.spikez.animation.model;

import java.util.List;

public class FrontPageFeed {

    private List<FrontPage> frontPageList;

    public FrontPageFeed(List<FrontPage> feed) {
        frontPageList = feed;
    }

    public FrontPage getPage(int position) {
        return frontPageList.get(position);
    }
}
