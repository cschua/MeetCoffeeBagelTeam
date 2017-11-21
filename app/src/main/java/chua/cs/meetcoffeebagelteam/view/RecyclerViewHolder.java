package chua.cs.meetcoffeebagelteam.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by christopherchua on 11/17/17.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
        View.OnLongClickListener {

    private OnClickItemView onClickItemView;

    public RecyclerViewHolder(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public void setOnClickItemView(final OnClickItemView onClickItemView) {
        this.onClickItemView = onClickItemView;
    }

    @Override
    public void onClick(final View view) {
        if (onClickItemView != null) {
            onClickItemView.onClickItemView(view, getAdapterPosition(), false);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (onClickItemView != null) {
            onClickItemView.onClickItemView(view, getAdapterPosition(), true);
        }
        return true;
    }

    public interface OnClickItemView {
        void onClickItemView(final View view, final int position, final boolean isLongClick);
    }
}
