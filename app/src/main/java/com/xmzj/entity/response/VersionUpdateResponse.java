package com.xmzj.entity.response;

public class VersionUpdateResponse {

    /**
     * code : 0
     * data : {"id":1,"versionNumber":"V1.0","versionName":"版本1","versionUrl":"https://fir.im/xmzjdev","releaseNotes":"","createTime":1576252800000}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * versionNumber : V1.0
         * versionName : 版本1
         * versionUrl : https://fir.im/xmzjdev
         * releaseNotes :
         * createTime : 1576252800000
         */

        private int id;
        private String versionNumber;
        private String versionName;
        private String versionUrl;
        private String releaseNotes;
        private long createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersionNumber() {
            return versionNumber;
        }

        public void setVersionNumber(String versionNumber) {
            this.versionNumber = versionNumber;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersionUrl() {
            return versionUrl;
        }

        public void setVersionUrl(String versionUrl) {
            this.versionUrl = versionUrl;
        }

        public String getReleaseNotes() {
            return releaseNotes;
        }

        public void setReleaseNotes(String releaseNotes) {
            this.releaseNotes = releaseNotes;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
