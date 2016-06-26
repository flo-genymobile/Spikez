package com.flo.spikez.animation.presenter;

import com.flo.spikez.animation.FrontFeedView;
import com.flo.spikez.animation.model.FrontPageFeed;
import com.flo.spikez.animation.model.FrontPageFeedRepository;
import com.flo.spikez.animation.model.Repository;

public class FrontPageFeedPresenter implements FeedPresenter {

    private FrontFeedView view;
    private Repository repository;

    private FrontPageFeed feed;

    public FrontPageFeedPresenter(FrontFeedView view) {
        this.view = view;
        this.repository = new FrontPageFeedRepository();
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void fetchFeed() {
        feed = repository.fetchFeed();

        if (view != null) {
            view.showPage(feed.getPage(0));
        }
    }

    @Override
    public void loadNextPage() {
        if (view != null) {
            view.showPage(feed.getPage(0));
        }
    }
}
