package com.example.strangerfinder.strangerfinder.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String name;
    private String sex;
    private String preference;
    private int searchCode;
    private int id;
    private int lastUser;
    private String key;


    public User(String name, String sex, String preference, String key) {
        this.name = name;
        this.sex = sex;
        this.preference = preference;
        this.key = key;
        this.id = (int) (Math.random()*10000);
    }

    public User() {
        this.id = (int) (Math.random()*100);
    }

    protected User(Parcel in) {
        name = in.readString();
        sex = in.readString();
        preference = in.readString();
        searchCode = in.readInt();
        id = in.readInt();
        key = in.readString();
        lastUser = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public int getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(int searchCode) {
        this.searchCode = searchCode;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getLastUser() {
        return lastUser;
    }

    public void setLastUser(int lastUser) {
        this.lastUser = lastUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(sex);
        parcel.writeString(preference);
        parcel.writeInt(searchCode);
        parcel.writeInt(id);
        parcel.writeString(key);
        parcel.writeInt(lastUser);
    }
}
