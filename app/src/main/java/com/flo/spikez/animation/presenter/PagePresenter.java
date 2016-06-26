package com.flo.spikez.animation.presenter;

import android.content.Context;
import com.flo.spikez.animation.BlendViewHolder;

public interface PagePresenter {
    void onDestroy();
    void initiatePageDisplay(Context context, BlendViewHolder viewHolder);
}
