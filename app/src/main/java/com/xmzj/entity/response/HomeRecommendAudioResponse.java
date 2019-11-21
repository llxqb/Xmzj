package com.xmzj.entity.response;

import java.util.List;

public class HomeRecommendAudioResponse {
    private List<AudiosBean> audios;

    public List<AudiosBean> getAudios() {
        return audios;
    }

    public void setAudios(List<AudiosBean> audios) {
        this.audios = audios;
    }

    public static class AudiosBean {
        /**
         * id : 5
         * title : 为何心里老是纠结
         * src : https://www.xinmizj.com/res/audio/src/whxllsjj.mp3
         * playLengthStr : 00:00
         * downloadUrl : https://www.xinmizj.com/res/audio/src/whxllsjj.mp3
         */

        private String id;
        private String title;
        private String src;
        private String playLengthStr;
        private String downloadUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getPlayLengthStr() {
            return playLengthStr;
        }

        public void setPlayLengthStr(String playLengthStr) {
            this.playLengthStr = playLengthStr;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }
    }
}
