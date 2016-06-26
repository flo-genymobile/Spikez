package com.flo.spikez.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.flo.spikez.R;
import com.flo.spikez.animation.animator.PageAnimator;
import com.flo.spikez.animation.model.FrontPage;
import com.flo.spikez.animation.presenter.FrontPagePresenter;
import com.flo.spikez.animation.presenter.PagePresenter;

public class FrontPageFragment extends Fragment implements FrontPageView {

    private PagePresenter presenter;
    private BlendViewHolder blendViewHolder;
    private View detailsView;

    public interface FrontPagerCallback {
        void onNext();
    }

    public static FrontPageFragment newInstance(FrontPage page) {
        Bundle arguments = new Bundle();
        arguments.putString("background_image_url", page.getBackgroundImageUrl());
        arguments.putString("subtitle_text", page.getSubtitle());
        arguments.putInt("blend_animation_type", page.getBlendAnimationType());

        FrontPageFragment fragment = new FrontPageFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.front_page_fragment, null);

        View layerOneView = root.findViewById(R.id.layer_one);
        View layerTwoView = root.findViewById(R.id.layer_two);
        View layerThreeView = root.findViewById(R.id.layer_three);
        View background = root.findViewById(R.id.background_image);

        detailsView = root.findViewById(R.id.details);
        View titleView = root.findViewById(R.id.image_title);
        View subtitleView = root.findViewById(R.id.image_subtitle);

        background.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        titleView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        subtitleView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        blendViewHolder = new BlendViewHolder(layerOneView, layerTwoView, layerThreeView, titleView, subtitleView, detailsView);

        FrontPage page = parseArguments();
        presenter = new FrontPagePresenter(this, page);

        return root;
    }

    private FrontPage parseArguments(){
        Bundle arguments = getArguments();
        String imageBackgroundUrl = arguments.getString("background_image_url");
        String subtitle = arguments.getString("subtitle_text");
        int blendAnimationType = arguments.getInt("blend_animation_type");

        return new FrontPage(imageBackgroundUrl, subtitle, blendAnimationType);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.initiatePageDisplay(getContext(), blendViewHolder);
    }

    @Override
    public void startPageDisplay(PageAnimator animator) {
        animator.startBlendAnimation();
    }

    @Override
    public void onPageDisplayed() {
        detailsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrontPagerCallback callback = (FrontPagerCallback) getActivity();
                callback.onNext();
            }
        });
    }
}

