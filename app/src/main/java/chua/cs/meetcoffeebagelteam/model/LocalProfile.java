package chua.cs.meetcoffeebagelteam.model;

import android.os.Parcel;
import android.os.Parcelable;

import chua.cs.meetcoffeebagelteam.model.json.Profile;

/**
 * Created by christopherchua on 11/19/17.
 */

public class LocalProfile implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public LocalProfile createFromParcel(final Parcel in) {
            return new LocalProfile(in);
        }

        public LocalProfile[] newArray(final int size) {
            return new LocalProfile[size];
        }
    };

    private final Profile profile;
    private final String fullName;

    public LocalProfile(Parcel in) {
        profile = in.readParcelable(Profile.class.getClassLoader());
        fullName = in.readString();
    }

    public LocalProfile(final Profile profile) {
        this.profile = profile;
        fullName = profile.getFirstName() + " " + profile.getLastName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(profile, flags);
        dest.writeString(fullName);
    }

    public String getAvatar() {
        return profile.getAvatar();
    }

    public String getBio() {
        return profile.getBio();
    }

    public String getFullName() {
        return fullName;
    }

    public String getTitle() {
        return profile.getTitle();
    }
}
