package com.rmuhamed.sample.poketest.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class Pokemon implements Parcelable {
    private static final String FORMAT = "dd-MM-yy HH:mm";
    private Integer id;
    private String type;
    private String name;
    private String picture;
    private String weight;
    private String height;
    private String baseExperience;
    private String capturedAtStr;

    private Date capturedAt;

    private Pokemon(Pokemon.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.picture = builder.picture;
        this.weight = builder.weight;
        this.height = builder.height;
        this.baseExperience = builder.baseExperience;
    }

    protected Pokemon(Parcel in) {
        id = in.readInt();
        type = in.readString();
        name = in.readString();
        picture = in.readString();
        weight = in.readString();
        height = in.readString();
        baseExperience = in.readString();
        capturedAtStr = in.readString();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPicture() {
        return picture;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public String getBaseExperience() {
        return baseExperience;
    }

    public Date getCapturedAt() {
        return capturedAt;
    }

    public void setCapturedAt(Date capturedAt) {
        this.capturedAt = capturedAt;
        this.capturedAtStr = new SimpleDateFormat(FORMAT, Locale.getDefault()).format(capturedAt);
    }

    public String getCapturedAtStr() {
        return capturedAtStr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(type);
        dest.writeString(name);
        dest.writeString(picture);
        dest.writeString(weight);
        dest.writeString(height);
        dest.writeString(baseExperience);
        dest.writeString(capturedAtStr);
    }

    public static class Builder {
        private String name;
        private Integer id;
        private String type;
        private String picture;
        private String weight;
        private String height;
        private String baseExperience;

        public Builder setName(String name) {
            this.name = name;

            return this;
        }

        public Builder setId(Integer id) {
            this.id = id;

            return this;
        }

        public Builder setType(String type) {
            this.type = type;

            return this;
        }

        public Builder setPicture(String picture) {
            this.picture = picture;

            return this;
        }

        public Builder setWeight(String weight) {
            this.weight = weight;

            return this;
        }

        public Builder setHeight(String height) {
            this.height = height;

            return this;
        }

        public Builder setBaseExperience(String baseExperience) {
            this.baseExperience = baseExperience;

            return this;
        }

        public Pokemon build() {
            return new Pokemon(this);
        }
    }
}
