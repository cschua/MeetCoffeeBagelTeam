package chua.cs.meetcoffeebagelteam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;

import chua.cs.meetcoffeebagelteam.model.LocalProfile;
import chua.cs.meetcoffeebagelteam.controller.ProfileDetailController;
import chua.cs.meetcoffeebagelteam.controller.ProfileListController;

public class MainActivity extends AppCompatActivity implements ProfileListController.OnClickProfileListener,
        ProfileDetailController.OnClickBioListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    private ProfileListController profileDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWelcomeText();
        profileDetailPresenter = new ProfileListController(this);
    }

    @Override
    public void onClickProfile(LocalProfile localProfile) {
        showFullProfileScreen(localProfile);
    }

    @Override
    public void onClickBioView(View view, LocalProfile localProfile) {
        showFullProfileScreen(localProfile);
    }

    @Override
    public void onDestroy() {
        if (profileDetailPresenter != null) {
            profileDetailPresenter.onDestroy();
        }
        profileDetailPresenter = null;

        super.onDestroy();
    }

    private void initWelcomeText() {
        final TextView textView = findViewById(R.id.welcome_textview);
        // set font typeface welcome textview
        textView.setTypeface(EasyFonts.captureIt(this));
    }

    private void showFullProfileScreen(final LocalProfile profile) {
        final Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.EXTRA_LOCALPROFILE, profile);
        startActivity(intent);
    }
}
