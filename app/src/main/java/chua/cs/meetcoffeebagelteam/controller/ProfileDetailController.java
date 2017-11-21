package chua.cs.meetcoffeebagelteam.controller;

import android.content.Context;
import android.view.View;

import com.vstechlab.easyfonts.EasyFonts;

import chua.cs.meetcoffeebagelteam.model.LocalProfile;
import chua.cs.meetcoffeebagelteam.view.ProfileDetailView;

/**
 * Created by christopherchua on 11/19/17.
 */

public class ProfileDetailController implements View.OnClickListener {

    private final ProfileDetailView profileDetailView;
    private LocalProfile localProfile;
    private OnClickBioListener onClickBioListener;

    public ProfileDetailController(final Context context, ProfileDetailView profileDetailView) {
        this.profileDetailView = profileDetailView;
        init(context);
    }

    @Override
    public void onClick(final View view) {
        if (onClickBioListener != null) {
            onClickBioListener.onClickBioView(view, localProfile);
        }
    }

    public void setOnClickBioListener(final OnClickBioListener onClickBioListener) {
        this.onClickBioListener = onClickBioListener;
    }

    private void init(final Context context) {
        // set font typeface for textviews
        profileDetailView.setFullNameTypeface(EasyFonts.caviarDreamsBold(context));
        profileDetailView.setTitleTypeface(EasyFonts.caviarDreamsBoldItalic(context));
        profileDetailView.setBioTypeface(EasyFonts.robotoRegular(context));
        profileDetailView.setOnBioClickListener(this);
    }

    public void update(final LocalProfile localProfile) {
        this.localProfile = localProfile;
        profileDetailView.setFullName(localProfile.getFullName());
        profileDetailView.setTitle(localProfile.getTitle());
        profileDetailView.setBio(localProfile.getBio());
        profileDetailView.setImage(localProfile.getAvatar());
        profileDetailView.setVisibility(View.VISIBLE);
    }

    /**
     * @param viewVisibility View.VISIBLE, View.VISIBLE, View.GONE
     */
    public void setVisibility(final int viewVisibility) {
        profileDetailView.setVisibility(viewVisibility);
    }

    public void onDestroy() {
        localProfile = null;
        onClickBioListener = null;
    }

    public interface OnClickBioListener {
        void onClickBioView(View view, LocalProfile localProfile);
    }
}
