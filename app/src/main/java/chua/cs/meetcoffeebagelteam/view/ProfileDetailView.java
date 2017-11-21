package chua.cs.meetcoffeebagelteam.view;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import chua.cs.meetcoffeebagelteam.R;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by christopherchua on 11/17/17.
 */

public class ProfileDetailView {
    private final TextView fullNameTextView;
    private final TextView titleTextView;
    private final TextView bioTextView;
    private final ImageView imageView;

    public ProfileDetailView(final Activity activity) {
        fullNameTextView = activity.findViewById(R.id.fullname_textview);
        titleTextView = activity.findViewById(R.id.title_textview);
        bioTextView = activity.findViewById(R.id.bio_textview);
        imageView = activity.findViewById(R.id.profile_image);
    }

    public void setFullName(final String fullName) {
        if (fullNameTextView != null) {
            fullNameTextView.setText(fullName);
        }
    }

    public void setTitle(final String title) {
        if (titleTextView != null) {
            titleTextView.setText(title);
        }
    }

    public void setBio(final String bio) {
        if (bioTextView != null) {
            bioTextView.setText(bio);
        }
    }

    public void setImage(String url) {
        if (imageView == null) {
            return;
        }
        Picasso.with(imageView.getContext()).load(url)
                .error(R.drawable.default_profile)
                .transform(new RoundedCornersTransformation(5, 5))
                .into(imageView);
    }

    public void setFullNameTypeface(Typeface typeface) {
        if (fullNameTextView != null) {
            fullNameTextView.setTypeface(typeface);
        }
    }

    public void setTitleTypeface(Typeface typeface) {
        if (titleTextView != null) {
            titleTextView.setTypeface(typeface);
        }
    }

    public void setBioTypeface(Typeface typeface) {
        if (bioTextView != null) {
            bioTextView.setTypeface(typeface);
        }
    }

    public void setOnBioClickListener(View.OnClickListener onClickListener) {
        if (bioTextView != null) {
            bioTextView.setOnClickListener(onClickListener);
        }
    }

    /**
     * @param viewVisibility View.VISIBLE, View.VISIBLE, View.GONE
     */
    public void setVisibility(final int viewVisibility) {
        if (fullNameTextView != null) {
            fullNameTextView.setVisibility(viewVisibility);
        }
        if (titleTextView != null) {
            titleTextView.setVisibility(viewVisibility);
        }
        if (bioTextView != null) {
            bioTextView.setVisibility(viewVisibility);
        }
    }
}
