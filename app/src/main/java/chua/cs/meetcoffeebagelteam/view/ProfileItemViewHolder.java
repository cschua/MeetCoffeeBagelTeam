package chua.cs.meetcoffeebagelteam.view;

import android.view.View;
import android.widget.ImageView;

import chua.cs.meetcoffeebagelteam.R;

/**
 * Created by christopherchua on 11/17/17.
 */

public class ProfileItemViewHolder extends RecyclerViewHolder {
    public final ImageView imageView;
    //public final TextView nameTextView;

    public ProfileItemViewHolder(final View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.profile_image);
        //nameTextView = itemView.findViewById(R.id.name_textview);
    }
}
