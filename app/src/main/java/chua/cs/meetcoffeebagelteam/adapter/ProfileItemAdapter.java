package chua.cs.meetcoffeebagelteam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import chua.cs.meetcoffeebagelteam.R;
import chua.cs.meetcoffeebagelteam.model.LocalProfile;
import chua.cs.meetcoffeebagelteam.view.ProfileItemViewHolder;
import chua.cs.meetcoffeebagelteam.view.RecyclerViewHolder;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by christopherchua on 11/17/17.
 */

public class ProfileItemAdapter extends RecyclerView.Adapter<ProfileItemViewHolder> implements RecyclerViewHolder.OnClickItemView {
    private LocalProfile[] profiles;
    private LayoutInflater inflater;
    private OnClickProfileListener onClickProfileListener;

    public ProfileItemAdapter(final Context context) {
        profiles = new LocalProfile[0];
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ProfileItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.profile_item, parent, false);
        return new ProfileItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileItemViewHolder holder, int position) {
        holder.setOnClickItemView(this);

        final LocalProfile profile = profiles[position];
        Picasso.with(holder.itemView.getContext()).load(profile.getAvatar())
                .error(R.drawable.default_profile)
                .transform(new RoundedCornersTransformation(5, 5))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return profiles.length;
    }

    @Override
    public void onClickItemView(final View view, int position, boolean isLongClick) {
        if (onClickProfileListener != null) {
            onClickProfileListener.onClickAdapterProfileItem(view, profiles[position], position);
        }
    }

    public void setOnClickProfileListener(final OnClickProfileListener onClickProfileListener) {
        this.onClickProfileListener = onClickProfileListener;
    }

    public void setProfiles(final LocalProfile[] profiles) {
        this.profiles = profiles;
    }

    public void onDestroy() {
        onClickProfileListener = null;
        inflater = null;
        profiles = null;
    }

    public LocalProfile getProfile(final int position) {
        return profiles[position];
    }

    public interface OnClickProfileListener {
        void onClickAdapterProfileItem(final View view, final LocalProfile profile, final int position);
    }
}
