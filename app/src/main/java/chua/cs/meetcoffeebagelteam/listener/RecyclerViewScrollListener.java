package chua.cs.meetcoffeebagelteam.listener;

import android.support.v7.widget.RecyclerView;

/**
 * Created by christopherchua on 11/19/17.
 */

public class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private OnScrollUpdateListener onScrollUpdateListener;

    public RecyclerViewScrollListener(final OnScrollUpdateListener onScrollUpdateListener) {
        this.onScrollUpdateListener = onScrollUpdateListener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            onScrollUpdateListener.onIdleState();
        } else {
            onScrollUpdateListener.onScroll();
        }
    }

    public void onDestroy() {
        onScrollUpdateListener = null;
    }

    public interface OnScrollUpdateListener {
        void onScroll();

        void onIdleState();
    }
}
