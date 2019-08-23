package com.xmzj.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 视频 response
 */
public class VideoResponse implements Parcelable {
    /**
     * 视频作者
     */
    public String author;
    /**
     * 视频标题
     */
    public String title;
    /**
     * 封面图片
     * 后面改成String
     */
    public int coverPic;
    /**
     * 视频路径
     */
    public String url;

    public VideoResponse() {
    }

    protected VideoResponse(Parcel in) {
        author = in.readString();
        title = in.readString();
        coverPic = in.readInt();
        url = in.readString();
    }

    public static final Creator<VideoResponse> CREATOR = new Creator<VideoResponse>() {
        @Override
        public VideoResponse createFromParcel(Parcel in) {
            return new VideoResponse(in);
        }

        @Override
        public VideoResponse[] newArray(int size) {
            return new VideoResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(title);
        dest.writeInt(coverPic);
        dest.writeString(url);
    }
}
