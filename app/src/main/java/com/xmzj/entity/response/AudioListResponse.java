package com.xmzj.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 音频列表Response
 */
public class AudioListResponse {

    /**
     * code : 0
     * data : [{"id":"Z3xZ1oC5oT7sG6n","createTime":1564381320000,"title":"身体虚弱可不可以入定？","info":"齐老师开示","cover":"https://www.xinmizj.com/res/audio/cover/1.png","src":"https://www.xinmizj.com/res/audio/src/Q8rS2zR8yQ3fS1sK0n01.mp3","playLength":0,"browseNum":52,"downloadUrl":"https://www.xinmizj.com/res/audio/src/Q8rS2zR8yQ3fS1sK0n01.mp3"},{"id":"F5uF9yO3xF3tI0j","createTime":1564381319000,"title":"法不会依照你而改变的。","info":"齐老师开示","cover":"https://www.xinmizj.com/res/audio/cover/1.png","src":"https://www.xinmizj.com/res/audio/src/P6yH3eF1iY0mW3oM7e62.mp3","playLength":0,"browseNum":30,"downloadUrl":"https://www.xinmizj.com/res/audio/src/P6yH3eF1iY0mW3oM7e62.mp3"},{"id":"P2cD2zA5oJ1nE2a","createTime":1564381319000,"title":"该设则设，该扔则扔！","info":"齐老师开示","cover":"https://www.xinmizj.com/res/audio/cover/1.png","src":"https://www.xinmizj.com/res/audio/src/Z2nB2pS8hH9aS0xB6t1b.mp3","playLength":0,"browseNum":34,"downloadUrl":"https://www.xinmizj.com/res/audio/src/Z2nB2pS8hH9aS0xB6t1b.mp3"},{"id":"G6aD0qL3aY0sX5y","createTime":1564381318000,"title":"接印应轻松自然，不要用力捏。","info":"齐老师开示","cover":"https://www.xinmizj.com/res/audio/cover/1.png","src":"https://www.xinmizj.com/res/audio/src/U8lQ6gJ9eK1eI9vY5oS9.mp3","playLength":0,"browseNum":16,"downloadUrl":"https://www.xinmizj.com/res/audio/src/U8lQ6gJ9eK1eI9vY5oS9.mp3"},{"id":"T3pG3mL5kX9qL2k","createTime":1564381317000,"title":"持往生广咒还可再持往生咒吗？","info":"齐老师开示","cover":"https://www.xinmizj.com/res/audio/cover/1.png","src":"https://www.xinmizj.com/res/audio/src/N9vW2hN9lR8hI5kF8z96.mp3","playLength":0,"browseNum":25,"downloadUrl":"https://www.xinmizj.com/res/audio/src/N9vW2hN9lR8hI5kF8z96.mp3"},{"id":"W5pZ5bO5kV2oR6u","createTime":1564381317000,"title":"持慈氏咒的时间","info":"齐老师开示","cover":"https://www.xinmizj.com/res/audio/cover/1.png","src":"https://www.xinmizj.com/res/audio/src/L7uG0yO7gD6zL7gJ3t42.mp3","playLength":0,"browseNum":22,"downloadUrl":"https://www.xinmizj.com/res/audio/src/L7uG0yO7gD6zL7gJ3t42.mp3"},{"id":"R5gF9kY7qW1jB6h","createTime":1564381316000,"title":"持咒跟呼吸不相干","info":"齐老师开示","cover":"https://www.xinmizj.com/res/audio/cover/1.png","src":"https://www.xinmizj.com/res/audio/src/S4fO3aC1iL5wL1hE1xQ3.mp3","playLength":0,"browseNum":12,"downloadUrl":"https://www.xinmizj.com/res/audio/src/S4fO3aC1iL5wL1hE1xQ3.mp3"},{"id":"Q3aV2xF3uI7mD9z","createTime":1564381315000,"title":"慈氏咒心密弟子回家咒","info":"齐老师开示","cover":"https://www.xinmizj.com/res/audio/cover/1.png","src":"https://www.xinmizj.com/res/audio/src/I6zZ8oR3cT8cQ1iK0aE8.mp3","playLength":0,"browseNum":17,"downloadUrl":"https://www.xinmizj.com/res/audio/src/I6zZ8oR3cT8cQ1iK0aE8.mp3"},{"id":"T8fU6kO6iV3aF7v","createTime":1564381315000,"title":"打完千座再念往生广咒吗？","info":"齐老师开示","cover":"https://www.xinmizj.com/res/audio/cover/1.png","src":"https://www.xinmizj.com/res/audio/src/P0lT1xM5hS0yZ6nE8q53.mp3","playLength":0,"browseNum":15,"downloadUrl":"https://www.xinmizj.com/res/audio/src/P0lT1xM5hS0yZ6nE8q53.mp3"},{"id":"H4sQ6xK6qO0uO6b","createTime":1564381314000,"title":"心密所传楞严咒心是需打坐修的","info":"齐老师开示","cover":"https://www.xinmizj.com/res/audio/cover/1.png","src":"https://www.xinmizj.com/res/audio/src/G9uB9iG6fE3rR0fW4h84.mp3","playLength":0,"browseNum":22,"downloadUrl":"https://www.xinmizj.com/res/audio/src/G9uB9iG6fE3rR0fW4h84.mp3"}]
     * count : 1636
     */

    private int code;
    private int count;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        public DataBean() {
        }

        /**
         * id : Z3xZ1oC5oT7sG6n
         * createTime : 1564381320000
         * title : 身体虚弱可不可以入定？
         * info : 齐老师开示
         * cover : https://www.xinmizj.com/res/audio/cover/1.png
         * src : https://www.xinmizj.com/res/audio/src/Q8rS2zR8yQ3fS1sK0n01.mp3
         * playLength : 0
         * browseNum : 52
         * downloadUrl : https://www.xinmizj.com/res/audio/src/Q8rS2zR8yQ3fS1sK0n01.mp3
         */

        private String id;
        private long createTime;
        private String title;
        private String info;
        private String cover;
        private String src;
        private int playLength;
        private int browseNum;
        private String downloadUrl;

        protected DataBean(Parcel in) {
            id = in.readString();
            createTime = in.readLong();
            title = in.readString();
            info = in.readString();
            cover = in.readString();
            src = in.readString();
            playLength = in.readInt();
            browseNum = in.readInt();
            downloadUrl = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public int getPlayLength() {
            return playLength;
        }

        public void setPlayLength(int playLength) {
            this.playLength = playLength;
        }

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeLong(createTime);
            dest.writeString(title);
            dest.writeString(info);
            dest.writeString(cover);
            dest.writeString(src);
            dest.writeInt(playLength);
            dest.writeInt(browseNum);
            dest.writeString(downloadUrl);
        }
    }
}
