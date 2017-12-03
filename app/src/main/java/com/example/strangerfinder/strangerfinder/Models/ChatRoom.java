package com.example.strangerfinder.strangerfinder.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ChatRoom implements Parcelable {

    private String name;
    private String user;
    private String strange;

    public ChatRoom(){}
    public ChatRoom(String name, String user, String strange) {
        this.name = name;
        this.user = user;
        this.strange = strange;
    }

    protected ChatRoom(Parcel in) {
        name = in.readString();
        user = in.readString();
        strange = in.readString();
    }

    public static final Creator<ChatRoom> CREATOR = new Creator<ChatRoom>() {
        @Override
        public ChatRoom createFromParcel(Parcel in) {
            return new ChatRoom(in);
        }

        @Override
        public ChatRoom[] newArray(int size) {
            return new ChatRoom[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStrange() {
        return strange;
    }

    public void setStrange(String strange) {
        this.strange = strange;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(user);
        parcel.writeString(strange);
    }
}

