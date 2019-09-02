package com.xmzj.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 视频详情Response
 */
public class VideoInfoResponse {
    /**
     * isMana : null
     * param : {"id":"40"}
     * needWatchPwd : false
     * episode : {"id":"249","createTime":1528594764000,"title":"入楞伽心探玄第一集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=o0384ubibhx&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=o0384ubibhx&tiny=0&auto=0","videoId":"40","num":1,"srcType":2}
     * episodes : [{"id":"249","createTime":1528594764000,"title":"入楞伽心探玄第一集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=o0384ubibhx&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=o0384ubibhx&tiny=0&auto=0","videoId":"40","num":1,"srcType":2},{"id":"250","createTime":1528594764000,"title":"入楞伽心探玄第二集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=v0384ul6vxf&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=v0384ul6vxf&tiny=0&auto=0","videoId":"40","num":2,"srcType":2},{"id":"251","createTime":1528594764000,"title":"入楞伽心探玄第三集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=b0384abrk9t&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=b0384abrk9t&tiny=0&auto=0","videoId":"40","num":3,"srcType":2},{"id":"252","createTime":1528594764000,"title":"入楞伽心探玄第四集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=f0384725f2j&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=f0384725f2j&tiny=0&auto=0","videoId":"40","num":4,"srcType":2},{"id":"253","createTime":1528594764000,"title":"入楞伽心探玄第五集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=f0384725f2j&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=f0384725f2j&tiny=0&auto=0","videoId":"40","num":5,"srcType":2},{"id":"254","createTime":1528594764000,"title":"入楞伽心探玄第六集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=o0384tehm4j&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=o0384tehm4j&tiny=0&auto=0","videoId":"40","num":6,"srcType":2},{"id":"255","createTime":1528594764000,"title":"入楞伽心探玄第七集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=o0384y0yhr6&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=o0384y0yhr6&tiny=0&auto=0","videoId":"40","num":7,"srcType":2},{"id":"256","createTime":1528594764000,"title":"入楞伽心探玄第八集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=x038422vi20&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=x038422vi20&tiny=0&auto=0","videoId":"40","num":8,"srcType":2},{"id":"257","createTime":1528594764000,"title":"入楞伽心探玄第九集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=q0384jq41q5&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=q0384jq41q5&tiny=0&auto=0","videoId":"40","num":9,"srcType":2},{"id":"258","createTime":1528594764000,"title":"入楞伽心探玄第十集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=q0384midevs&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=q0384midevs&tiny=0&auto=0","videoId":"40","num":10,"srcType":2},{"id":"259","createTime":1528594764000,"title":"入楞伽心探玄第十一集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=q0384f5owxx&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=q0384f5owxx&tiny=0&auto=0","videoId":"40","num":11,"srcType":2},{"id":"260","createTime":1528594764000,"title":"入楞伽心探玄第十二集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=q0384i89vtt&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=q0384i89vtt&tiny=0&auto=0","videoId":"40","num":12,"srcType":2},{"id":"261","createTime":1528594764000,"title":"入楞伽心探玄第十三集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=l0384o4aldi&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=l0384o4aldi&tiny=0&auto=0","videoId":"40","num":13,"srcType":2},{"id":"262","createTime":1528594764000,"title":"入楞伽心探玄第十四集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=p0384oz1t3r&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=p0384oz1t3r&tiny=0&auto=0","videoId":"40","num":14,"srcType":2},{"id":"263","createTime":1528594764000,"title":"入楞伽心探玄第十五集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=a0384fg8cx2&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=a0384fg8cx2&tiny=0&auto=0","videoId":"40","num":15,"srcType":2},{"id":"264","createTime":1528594764000,"title":"入楞伽心探玄第十六集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=t03840ute6k&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=t03840ute6k&tiny=0&auto=0","videoId":"40","num":16,"srcType":2},{"id":"265","createTime":1528594764000,"title":"入楞伽心探玄第十七集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=w0384my0gt9&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=w0384my0gt9&tiny=0&auto=0","videoId":"40","num":17,"srcType":2},{"id":"266","createTime":1528594764000,"title":"入楞伽心探玄第十八集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=k0384kc33ok&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=k0384kc33ok&tiny=0&auto=0","videoId":"40","num":18,"srcType":2},{"id":"267","createTime":1528594764000,"title":"入楞伽心探玄第十九集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=e03845zk45s&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=e03845zk45s&tiny=0&auto=0","videoId":"40","num":19,"srcType":2},{"id":"268","createTime":1528594764000,"title":"入楞伽心探玄第二十集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=w0384fgbbqf&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=w0384fgbbqf&tiny=0&auto=0","videoId":"40","num":20,"srcType":2},{"id":"269","createTime":1528594764000,"title":"入楞伽心探玄第二十一集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=g0384efmnwd&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=g0384efmnwd&tiny=0&auto=0","videoId":"40","num":21,"srcType":2},{"id":"270","createTime":1528594764000,"title":"入楞伽心探玄第二十二集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=e0384lpf0ch&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=e0384lpf0ch&tiny=0&auto=0","videoId":"40","num":22,"srcType":2},{"id":"271","createTime":1528594764000,"title":"入楞伽心探玄第二十三集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=e0384lpf0ch&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=e0384lpf0ch&tiny=0&auto=0","videoId":"40","num":23,"srcType":2},{"id":"272","createTime":1528594764000,"title":"入楞伽心探玄第二十四集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=e0385fgnr8s&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=e0385fgnr8s&tiny=0&auto=0","videoId":"40","num":24,"srcType":2},{"id":"273","createTime":1528594764000,"title":"入楞伽心探玄第二十五集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=e0385ima3ib&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=e0385ima3ib&tiny=0&auto=0","videoId":"40","num":25,"srcType":2},{"id":"274","createTime":1528594764000,"title":"入楞伽心探玄第二十六集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=y03853adagp&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=y03853adagp&tiny=0&auto=0","videoId":"40","num":26,"srcType":2},{"id":"275","createTime":1528594764000,"title":"入楞伽心探玄第二十七集","info":"齐老师讲解","cover":"https://www.xinmizj.com/res/video/episodecover/null","src":"https://v.qq.com/iframe/player.html?vid=m03854wmpjg&tiny=0&auto=0","downloadUrl":"https://v.qq.com/iframe/player.html?vid=m03854wmpjg&tiny=0&auto=0","videoId":"40","num":27,"srcType":2}]
     */

    private Object isMana;
    private ParamBean param;
    private boolean needWatchPwd;
    private EpisodeBean episode;
    private List<EpisodesBean> episodes;

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

    public static class ParamBean {
        /**
         * id : 40
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class EpisodeBean implements Parcelable {
        /**
         * id : 249
         * createTime : 1528594764000
         * title : 入楞伽心探玄第一集
         * info : 齐老师讲解
         * cover : https://www.xinmizj.com/res/video/episodecover/null
         * src : https://v.qq.com/iframe/player.html?vid=o0384ubibhx&tiny=0&auto=0
         * downloadUrl : https://v.qq.com/iframe/player.html?vid=o0384ubibhx&tiny=0&auto=0
         * videoId : 40
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
        private String videoId;
        private int num;
        private int srcType;

        public EpisodeBean() {
        }

        public EpisodeBean(Parcel in) {
            id = in.readString();
            createTime = in.readLong();
            title = in.readString();
            info = in.readString();
            cover = in.readString();
            src = in.readString();
            downloadUrl = in.readString();
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
            dest.writeString(videoId);
            dest.writeInt(num);
            dest.writeInt(srcType);
        }
    }

    public static class EpisodesBean {
        /**
         * id : 249
         * createTime : 1528594764000
         * title : 入楞伽心探玄第一集
         * info : 齐老师讲解
         * cover : https://www.xinmizj.com/res/video/episodecover/null
         * src : https://v.qq.com/iframe/player.html?vid=o0384ubibhx&tiny=0&auto=0
         * downloadUrl : https://v.qq.com/iframe/player.html?vid=o0384ubibhx&tiny=0&auto=0
         * videoId : 40
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
        private String videoId;
        private int num;
        private int srcType;

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
    }
}
