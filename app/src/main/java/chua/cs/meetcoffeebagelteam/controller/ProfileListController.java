package chua.cs.meetcoffeebagelteam.controller;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import java.io.IOException;
import java.io.InputStreamReader;

import chua.cs.meetcoffeebagelteam.MainActivity;
import chua.cs.meetcoffeebagelteam.R;
import chua.cs.meetcoffeebagelteam.adapter.ProfileItemAdapter;
import chua.cs.meetcoffeebagelteam.listener.RecyclerViewScrollListener;
import chua.cs.meetcoffeebagelteam.model.LocalProfile;
import chua.cs.meetcoffeebagelteam.model.json.Profile;
import chua.cs.meetcoffeebagelteam.view.ProfileDetailView;

/**
 * Created by christopherchua on 11/19/17.
 */

public class ProfileListController implements ProfileItemAdapter.OnClickProfileListener,
        RecyclerViewScrollListener.OnScrollUpdateListener {

    private final static String TAG = ProfileListController.class.getSimpleName();

    private RecyclerView recyclerView;
    private ProfileItemAdapter profileItemAdapter;
    private CardSliderLayoutManager layoutManger;
    private RecyclerViewScrollListener recyclerViewScrollListener;

    private JsonReader jsonReader;

    private OnClickProfileListener onClickProfileListener;

    private ProfileDetailController profileDetailController;

    public ProfileListController(final MainActivity activity) {
        if (activity instanceof OnClickProfileListener) {
            onClickProfileListener = (OnClickProfileListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemenet ProfileListController.OnClickProfileListener");
        }

        jsonReader = new JsonReader(
                new InputStreamReader(activity.getResources().openRawResource(R.raw.team)));

        recyclerView = (RecyclerView) activity.findViewById(R.id.profiles_recycler_view);
        profileItemAdapter = new ProfileItemAdapter(activity);

        profileDetailController = new ProfileDetailController(activity, new ProfileDetailView(activity));
        profileDetailController.setOnClickBioListener(activity);

        initProfileList();
        updateProfileList();
    }

    @Override
    public void onClickAdapterProfileItem(final View view, final LocalProfile profile, final int position) {
        if (layoutManger == null) {
            return;
        }

        // prevent clicks for auto scrolling or while scrolling
        if (layoutManger.isSmoothScrolling()) {
            return;
        }

        // only allow clicks for active view (the view that is snapped to)
        final int activeCardPosition = layoutManger.getActiveCardPosition();
        if (activeCardPosition == RecyclerView.NO_POSITION) {
            return;
        }

        // show full profile pic if active view is clicked
        if (activeCardPosition == position) {
            onClickProfileListener.onClickProfile(profile);
            return;
        }

        // otherwise auto scroll to clicked position
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(position);
        }
    }

    @Override
    public void onScroll() {
        // hide detail information when recyclerview is scrolling
        profileDetailController.setVisibility(View.GONE);
    }

    @Override
    public void onIdleState() {
        // only show detail when recyclerview is idle (not scrolling)
        if (layoutManger != null && profileItemAdapter != null) {
            int position = layoutManger.getActiveCardPosition();
            LocalProfile profile = profileItemAdapter.getProfile(position);
            profileDetailController.update(profile);
        }
    }

    private void initProfileList() {
        recyclerView.setHasFixedSize(true);

        // recyclerview's layoutmanager
        layoutManger = (CardSliderLayoutManager) recyclerView.getLayoutManager();
        recyclerView.setLayoutManager(layoutManger);

        // recyclerview's adapter
        profileItemAdapter.setOnClickProfileListener(this);
        recyclerView.setAdapter(profileItemAdapter);

        // recyclerview's scrolllistener
        recyclerViewScrollListener = new RecyclerViewScrollListener(this);
        recyclerView.addOnScrollListener(recyclerViewScrollListener);

        // reyclerview SnapHelper
        new CardSnapHelper().attachToRecyclerView(recyclerView);
    }

    private void updateProfileList() {
        final LocalProfile[] localProfiles = getProfiles();
        if (localProfiles != null && localProfiles.length > 0) {
            // update adapter with profiles
            profileItemAdapter.setProfiles(localProfiles);
            // also update the first item's detail information
            profileDetailController.update(localProfiles[0]);
        }
    }

    private LocalProfile[] getProfiles() {
        if (jsonReader == null) {
            return null;
        }

        // retrieve profiles from a JSON file
        Profile[] profiles = null;
        try {
            final Gson gson = new Gson();
            profiles = gson.fromJson(jsonReader, Profile[].class);
        } catch (Exception e) {
            Log.w(TAG, e);
        }

        // convert json object to local model
        LocalProfile[] localProfiles = null;
        if (profiles != null) {
            localProfiles = new LocalProfile[profiles.length];
            for (int i = 0; i < profiles.length; i++) {
                localProfiles[i] = new LocalProfile(profiles[i]);
            }
        }
        return localProfiles;
    }

    public void onDestroy() {
        if (profileItemAdapter != null) {
            profileItemAdapter.onDestroy();
        }
        profileItemAdapter = null;

        if (recyclerViewScrollListener != null) {
            recyclerViewScrollListener.onDestroy();
        }
        recyclerViewScrollListener = null;

        if (profileDetailController != null) {
            profileDetailController.onDestroy();
        }
        profileDetailController = null;

        if (jsonReader != null) {
            try {
                jsonReader.close();
            } catch (IOException e) {
                Log.d(TAG, e.getMessage());
            }
        }
        jsonReader = null;

        recyclerView = null;
        layoutManger = null;
        onClickProfileListener = null;
    }

    public interface OnClickProfileListener {
        void onClickProfile(LocalProfile localProfile);
    }
}
