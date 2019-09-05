package com.xmzj.entity.response;

/**
 */
public class AudioDetailInfoResponse {
    /**
     * id : 34
     * createTime : 1533052828000
     * singer : 齐老师
     * title : 一关也无方破初关
     * info : 齐老师开示
     * cover : https://www.xinmizj.com/res/audio/cover/1.png
     * src : https://www.xinmizj.com/res/audio/src/ygywfpcg.mp3
     * playLength : 0
     * browseNum : 117
     * downloadUrl : ygywfpcg.mp3
     * prev : {"id":"33","createTime":1533052827000,"singer":"齐老师","title":"心性禅亦不能建立","info":"齐老师开示","cover":"https://www.xinmizj.com/res/audio/cover/1.png","src":"https://www.xinmizj.com/res/audio/src/xxcybnjl.mp3","playLength":0,"browseNum":126,"downloadUrl":"xxcybnjl.mp3","createTimeStr":"2018-08-01"}
     * next : {"id":"35","createTime":1533052829000,"singer":"齐老师","title":"印证是心心相应","info":"齐老师开示","cover":"https://www.xinmizj.com/res/audio/cover/1.png","src":"https://www.xinmizj.com/res/audio/src/yzsxxxy.mp3","playLength":0,"browseNum":114,"downloadUrl":"yzsxxxy.mp3","createTimeStr":"2018-08-01"}
     * isCollect : false
     * createTimeStr : 2018-08-01
     */

    private String id;
    private long createTime;
    private String singer;
    private String title;
    private String info;
    private String cover;
    private String src;
    private int playLength;
    private int browseNum;
    private String downloadUrl;
    private PrevBean prev;
    private NextBean next;
    private boolean isCollect;
    private String createTimeStr;

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

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
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

    public PrevBean getPrev() {
        return prev;
    }

    public void setPrev(PrevBean prev) {
        this.prev = prev;
    }

    public NextBean getNext() {
        return next;
    }

    public void setNext(NextBean next) {
        this.next = next;
    }

    public boolean isIsCollect() {
        return isCollect;
    }

    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public static class PrevBean {
        /**
         * id : 33
         * createTime : 1533052827000
         * singer : 齐老师
         * title : 心性禅亦不能建立
         * info : 齐老师开示
         * cover : https://www.xinmizj.com/res/audio/cover/1.png
         * src : https://www.xinmizj.com/res/audio/src/xxcybnjl.mp3
         * playLength : 0
         * browseNum : 126
         * downloadUrl : xxcybnjl.mp3
         * createTimeStr : 2018-08-01
         */

        private String id;
        private long createTime;
        private String singer;
        private String title;
        private String info;
        private String cover;
        private String src;
        private int playLength;
        private int browseNum;
        private String downloadUrl;
        private String createTimeStr;

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

        public String getSinger() {
            return singer;
        }

        public void setSinger(String singer) {
            this.singer = singer;
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

        public String getCreateTimeStr() {
            return createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }
    }

    public static class NextBean {
        /**
         * id : 35
         * createTime : 1533052829000
         * singer : 齐老师
         * title : 印证是心心相应
         * info : 齐老师开示
         * cover : https://www.xinmizj.com/res/audio/cover/1.png
         * src : https://www.xinmizj.com/res/audio/src/yzsxxxy.mp3
         * playLength : 0
         * browseNum : 114
         * downloadUrl : yzsxxxy.mp3
         * createTimeStr : 2018-08-01
         */

        private String id;
        private long createTime;
        private String singer;
        private String title;
        private String info;
        private String cover;
        private String src;
        private int playLength;
        private int browseNum;
        private String downloadUrl;
        private String createTimeStr;

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

        public String getSinger() {
            return singer;
        }

        public void setSinger(String singer) {
            this.singer = singer;
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

        public String getCreateTimeStr() {
            return createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }
    }
}
