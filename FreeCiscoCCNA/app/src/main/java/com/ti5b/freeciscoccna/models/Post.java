package com.ti5b.freeciscoccna.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    private int id;
    private String image_main;
    private String heading_main;
    private String user_id;
    private String email;
    private String created_date;

    private String heading1;
    private String heading2;
    private String heading3;
    private String heading4;
    private String heading5;

    private String h1_paragraph1;
    private String h2_paragraph1;
    private String h3_paragraph1;
    private String h4_paragraph1;
    private String h5_paragraph1;

    private String h1_paragraph2;
    private String h2_paragraph2;
    private String h3_paragraph2;
    private String h4_paragraph2;
    private String h5_paragraph2;

    private String h1_image;
    private String h2_image;
    private String h3_image;
    private String h4_image;
    private String h5_image;

    private boolean expandable;

    public Post(boolean expandable) {
        this.expandable = expandable;
    }


    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public int getId() {
        return id;
    }

    public String getImage_main() {
        return image_main;
    }

    public String getHeading_main() {
        return heading_main;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getHeading1() {
        return heading1;
    }

    public String getHeading2() {
        return heading2;
    }

    public String getHeading3() {
        return heading3;
    }

    public String getHeading4() {
        return heading4;
    }

    public String getHeading5() {
        return heading5;
    }

    public String getH1_paragraph1() {
        return h1_paragraph1;
    }

    public String getH2_paragraph1() {
        return h2_paragraph1;
    }

    public String getH3_paragraph1() {
        return h3_paragraph1;
    }

    public String getH4_paragraph1() {
        return h4_paragraph1;
    }

    public String getH5_paragraph1() {
        return h5_paragraph1;
    }

    public String getH1_paragraph2() {
        return h1_paragraph2;
    }

    public String getH2_paragraph2() {
        return h2_paragraph2;
    }

    public String getH3_paragraph2() {
        return h3_paragraph2;
    }

    public String getH4_paragraph2() {
        return h4_paragraph2;
    }

    public String getH5_paragraph2() {
        return h5_paragraph2;
    }

    public String getH1_image() {
        return h1_image;
    }

    public String getH2_image() {
        return h2_image;
    }

    public String getH3_image() {
        return h3_image;
    }

    public String getH4_image() {
        return h4_image;
    }

    public String getH5_image() {
        return h5_image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.image_main);
        dest.writeString(this.heading_main);
        dest.writeString(this.user_id);
        dest.writeString(this.email);
        dest.writeString(this.created_date);
        dest.writeString(this.heading1);
        dest.writeString(this.heading2);
        dest.writeString(this.heading3);
        dest.writeString(this.heading4);
        dest.writeString(this.heading5);
        dest.writeString(this.h1_paragraph1);
        dest.writeString(this.h2_paragraph1);
        dest.writeString(this.h3_paragraph1);
        dest.writeString(this.h4_paragraph1);
        dest.writeString(this.h5_paragraph1);
        dest.writeString(this.h1_paragraph2);
        dest.writeString(this.h2_paragraph2);
        dest.writeString(this.h3_paragraph2);
        dest.writeString(this.h4_paragraph2);
        dest.writeString(this.h5_paragraph2);
        dest.writeString(this.h1_image);
        dest.writeString(this.h2_image);
        dest.writeString(this.h3_image);
        dest.writeString(this.h4_image);
        dest.writeString(this.h5_image);
        dest.writeByte(this.expandable ? (byte) 1 : (byte) 0);
    }

//    public void readFromParcel(Parcel source) {
//        super.readFromParcel(source);
//        this.id = source.readInt();
//        this.image_main = source.readString();
//        this.heading_main = source.readString();
//        this.date_main = source.readString();
//        this.user_id = source.readString();
//        this.email = source.readString();
//        this.created_date = source.readString();
//        this.heading1 = source.readString();
//        this.heading2 = source.readString();
//        this.heading3 = source.readString();
//        this.heading4 = source.readString();
//        this.heading5 = source.readString();
//        this.h1_paragraph1 = source.readString();
//        this.h2_paragraph1 = source.readString();
//        this.h3_paragraph1 = source.readString();
//        this.h4_paragraph1 = source.readString();
//        this.h5_paragraph1 = source.readString();
//        this.h1_paragraph2 = source.readString();
//        this.h2_paragraph2 = source.readString();
//        this.h3_paragraph2 = source.readString();
//        this.h4_paragraph2 = source.readString();
//        this.h5_paragraph2 = source.readString();
//        this.h1_image = source.readString();
//        this.h2_image = source.readString();
//        this.h3_image = source.readString();
//        this.h4_image = source.readString();
//        this.h5_image = source.readString();
//        this.expandable = source.readByte() != 0;
//    }

    protected Post(Parcel in) {
        this.id = in.readInt();
        this.image_main = in.readString();
        this.heading_main = in.readString();
        this.user_id = in.readString();
        this.email = in.readString();
        this.created_date = in.readString();
        this.heading1 = in.readString();
        this.heading2 = in.readString();
        this.heading3 = in.readString();
        this.heading4 = in.readString();
        this.heading5 = in.readString();
        this.h1_paragraph1 = in.readString();
        this.h2_paragraph1 = in.readString();
        this.h3_paragraph1 = in.readString();
        this.h4_paragraph1 = in.readString();
        this.h5_paragraph1 = in.readString();
        this.h1_paragraph2 = in.readString();
        this.h2_paragraph2 = in.readString();
        this.h3_paragraph2 = in.readString();
        this.h4_paragraph2 = in.readString();
        this.h5_paragraph2 = in.readString();
        this.h1_image = in.readString();
        this.h2_image = in.readString();
        this.h3_image = in.readString();
        this.h4_image = in.readString();
        this.h5_image = in.readString();
        this.expandable = in.readByte() != 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
