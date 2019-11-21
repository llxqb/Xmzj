package com.xmzj.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 视频详情Response
 */
public class VideoInfoResponse implements Parcelable{

    /**
     * isMana : null
     * param : {"id":"23"}
     * needWatchPwd : false
     * episode : {"id":"223","createTime":1528594764000,"title":"解脱歌第一集","info":"元音老人讲解","cover":"https://www.xinmizj.com/res/video/cover/yylr-jtgqs.jpg","src":"https://v.qq.com/iframe/player.html?vid=t0670xv8e05&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=t0670xv8e05&tiny=0&auto=0","isCollect":false,"videoId":"23","num":1,"srcType":2}
     * episodes : [{"id":"223","createTime":1528594764000,"title":"解脱歌第一集","info":"元音老人讲解","cover":"https://www.xinmizj.com/res/video/cover/yylr-jtgqs.jpg","src":"https://v.qq.com/iframe/player.html?vid=t0670xv8e05&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=t0670xv8e05&tiny=0&auto=0","isCollect":false,"videoId":"23","num":1,"srcType":2},{"id":"224","createTime":1528594764000,"title":"解脱歌第二集","info":"元音老人讲解","cover":"https://www.xinmizj.com/res/video/cover/yylr-jtgqs.jpg","src":"https://v.qq.com/iframe/player.html?vid=k0670rr7nks&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=k0670rr7nks&tiny=0&auto=0","videoId":"23","num":2,"srcType":2},{"id":"225","createTime":1528594764000,"title":"解脱歌第三集","info":"元音老人讲解","cover":"https://www.xinmizj.com/res/video/cover/yylr-jtgqs.jpg","src":"https://v.qq.com/iframe/player.html?vid=d0670b3gjjm&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=d0670b3gjjm&tiny=0&auto=0","videoId":"23","num":3,"srcType":2},{"id":"226","createTime":1528594764000,"title":"解脱歌第四集","info":"元音老人讲解","cover":"https://www.xinmizj.com/res/video/cover/yylr-jtgqs.jpg","src":"https://v.qq.com/iframe/player.html?vid=m0670p8feru&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=m0670p8feru&tiny=0&auto=0","videoId":"23","num":4,"srcType":2}]
     */

    private Object isMana;
    private ParamBean param;
    private boolean needWatchPwd;
    private EpisodeBean episode;
    private List<EpisodesBean> episodes;

    protected VideoInfoResponse(Parcel in) {
        needWatchPwd = in.readByte() != 0;
    }

    public static final Creator<VideoInfoResponse> CREATOR = new Creator<VideoInfoResponse>() {
        @Override
        public VideoInfoResponse createFromParcel(Parcel in) {
            return new VideoInfoResponse(in);
        }

        @Override
        public VideoInfoResponse[] newArray(int size) {
            return new VideoInfoResponse[size];
        }
    };

    public Object getIsMana() {
        return isMana;
    }

    public void setIsMana(Object isMana) {
        this.isMana = isMana;
    }

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public boolean isNeedWatchPwd() {
        return needWatchPwd;
    }

    public void setNeedWatchPwd(boolean needWatchPwd) {
        this.needWatchPwd = needWatchPwd;
    }

    public EpisodeBean getEpisode() {
        return episode;
    }

    public void setEpisode(EpisodeBean episode) {
        this.episode = episode;
    }

    public List<EpisodesBean> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodesBean> episodes) {
        this.episodes = episodes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (needWatchPwd ? 1 : 0));
    }

    public static class ParamBean {
        /**
         * id : 23
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class EpisodeBean implements Parcelable{
        public EpisodeBean() {
        }

        /**
         * id : 223
         * createTime : 1528594764000
         * title : 解脱歌第一集
         * info : 元音老人讲解
         * cover : https://www.xinmizj.com/res/video/cover/yylr-jtgqs.jpg
         * src : https://v.qq.com/iframe/player.html?vid=t0670xv8e05&tiny=0&auto=0
         * downloadUrl : https://v.qq.com/iframe/player.html?vid=t0670xv8e05&tiny=0&auto=0
         * isCollect : false
         * videoId : 23
         * num : 1
         * srcType : 2
         */

        private String id;
        private long createTime;
        private String title;
        private String info;
        private String cover;
        private String src;
        private String downloadUrl;
        private boolean isCollect;
        private String videoId;
        private int num;
        private int srcType;

        protected EpisodeBean(Parcel in) {
            id = in.readString();
            createTime = in.readLong();
            title = in.readString();
            info = in.readString();
            cover = in.readString();
            src = in.readString();
            downloadUrl = in.readString();
            isCollect = in.readByte() != 0;
            videoId = in.readString();
            num = in.readInt();
            srcType = in.readInt();
        }

        public static final Creator<EpisodeBean> CREATOR = new Creator<EpisodeBean>() {
            @Override
            public EpisodeBean createFromParcel(Parcel in) {
                return new EpisodeBean(in);
            }

            @Override
            public EpisodeBean[] newArray(int size) {
                return new EpisodeBean[size];
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

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getSrcType() {
            return srcType;
        }

        public void setSrcType(int srcType) {
            this.srcType = srcType;
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
            dest.writeString(downloadUrl);
            dest.writeByte((byte) (isCollect ? 1 : 0));
            dest.writeString(videoId);
            dest.writeInt(num);
            dest.writeInt(srcType);
        }
    }

    public static class EpisodesBean implements Parcelable{
        /**
         * id : 223
         * createTime : 1528594764000
         * title : 解脱歌第一集
         * info : 元音老人讲解
         * cover : https://www.xinmizj.com/res/video/cover/yylr-jtgqs.jpg
         * src : https://v.qq.com/iframe/player.html?vid=t0670xv8e05&tiny=0&auto=0
         * downloadUrl : https://v.qq.com/iframe/player.html?vid=t0670xv8e05&tiny=0&auto=0
         * isCollect : false
         * videoId : 23
         * num : 1
         * srcType : 2
         */

        private String id;
        private long createTime;
        private String title;
        private String info;
        private String cover;
        private String src;
        private String downloadUrl;
        private boolean isCollect;
        private String videoId;
        private int num;
        private int srcType;

        protected EpisodesBean(Parcel in) {
            id = in.readString();
            createTime = in.readLong();
            title = in.readString();
            info = in.readString();
            cover = in.readString();
            src = in.readString();
            downloadUrl = in.readString();
            isCollect = in.readByte() != 0;
            videoId = in.readString();
            num = in.readInt();
            srcType = in.readInt();
        }

        public static final Creator<EpisodesBean> CREATOR = new Creator<EpisodesBean>() {
            @Override
            public EpisodesBean createFromParcel(Parcel in) {
                return new EpisodesBean(in);
            }

            @Override
            public EpisodesBean[] newArray(int size) {
                return new EpisodesBean[size];
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

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getSrcType() {
            return srcType;
        }

        public void setSrcType(int srcType) {
            this.srcType = srcType;
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
            dest.writeString(downloadUrl);
            dest.writeByte((byte) (isCollect ? 1 : 0));
            dest.writeString(videoId);
            dest.writeInt(num);
            dest.writeInt(srcType);
        }
    }
}
