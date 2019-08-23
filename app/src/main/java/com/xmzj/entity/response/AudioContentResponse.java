package com.xmzj.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 音频内容bean
 */
public class AudioContentResponse implements Parcelable {
    public String title;
    /**
     * 播放次数
     */
    public int playNum;
    /**
     * 观看次数
     */
    public int lookNum;
    /**
     * 上传日期
     */
    public String uploadDate;
    /**
     * 播放时长
     */
    public String playDuration;
    /**
     * 播放地址
     */
    public String url;

    protected AudioContentResponse(Parcel in) {
        title = in.readString();
        playNum = in.readInt();
        lookNum = in.readInt();
        uploadDate = in.readString();
        playDuration = in.readString();
        url = in.readString();
    }

    public static final Creator<AudioContentResponse> CREATOR = new Creator<AudioContentResponse>() {
        @Override
        public AudioContentResponse createFromParcel(Parcel in) {
            return new AudioContentResponse(in);
        }

        @Override
        public AudioContentResponse[] newArray(int size) {
            return new AudioContentResponse[size];
        }
    };

    public AudioContentResponse() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(playNum);
        dest.writeInt(lookNum);
        dest.writeString(uploadDate);
        dest.writeString(playDuration);
        dest.writeString(url);
    }
}
