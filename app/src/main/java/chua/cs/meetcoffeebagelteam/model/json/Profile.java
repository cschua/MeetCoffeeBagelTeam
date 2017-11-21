package chua.cs.meetcoffeebagelteam.model.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by christopherchua on 11/17/17.
 */

public class Profile implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Profile createFromParcel(final Parcel in) {
            return new Profile(in);
        }

        public Profile[] newArray(final int size) {
            return new Profile[size];
        }
    };
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("bio")
    @Expose
    private String bio;

    public Profile() {
    }

    public Profile(Parcel in) {
        id = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        title = in.readString();
        avatar = in.readString();
        bio = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(title);
        dest.writeString(avatar);
        dest.writeString(bio);
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
