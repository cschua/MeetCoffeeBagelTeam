package chua.cs.meetcoffeebagelteam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import chua.cs.meetcoffeebagelteam.model.LocalProfile;
import chua.cs.meetcoffeebagelteam.model.json.Profile;
import chua.cs.meetcoffeebagelteam.controller.ProfileDetailController;
import chua.cs.meetcoffeebagelteam.view.ProfileDetailView;

/**
 * Created by christopherchua on 11/19/17.
 */

public class ProfileActivity extends AppCompatActivity {
    public final static String EXTRA_LOCALPROFILE = "extraImageURL";

    private ProfileDetailController profileDetailController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_detail);

        final LocalProfile profile = getIntent().getParcelableExtra(EXTRA_LOCALPROFILE);
        if (profile == null) {
            throw new ClassCastException(ProfileActivity.class.getSimpleName() +
                    " requires EXTRA_LOCALPROFILE " + Profile.class.getName() +
                    " in Intent of startActivity");
        }

        profileDetailController = new ProfileDetailController(this, new ProfileDetailView(this));
        profileDetailController.update(profile);
    }
}
