package com.xmzj.entity.response;

import java.util.List;

/**
 * 视频列表Response
 */
public class VideoListResponse {
    /**
     * code : 0
     * data : [{"id":"35","title":"美国居士家座谈","info":"元音老人美国居士家座谈","cover":"https://www.xinmizj.com/res/video/cover/yylr-mgjsjzt.jpg"},{"id":"32","title":"修行六个要点","info":"元音老人开示修行六个要点","cover":"https://www.xinmizj.com/res/video/cover/yylr-xxdlgyd.jpg"},{"id":"30","title":"学佛第一要知见正","info":"学佛第一要知见正","cover":"https://www.xinmizj.com/res/video/cover/yylr-xfdyyzjz1.jpg"},{"id":"29","title":"元音老人圆寂纪实","info":"元音老人圆寂纪实","cover":"https://www.xinmizj.com/res/video/cover/yylr-yjjs1.jpg"},{"id":"28","title":"六字大明咒修法","info":"六字大明咒修法","cover":"https://www.xinmizj.com/res/video/cover/yylr-lzdmzxf.jpg"},{"id":"26","title":"略论明心见性","info":"我等众生，从无始旷劫以来，迷失自己本来面目，认妄为真，唤奴作郎，妄起贪嗔，造业受报，如春蚕作茧，自缠自缚，无解脱时。","cover":"https://www.xinmizj.com/res/video/cover/yylr-lvmxjx.jpg"},{"id":"18","title":"佛法修证心要","info":"讲到佛法,虽然有各大宗派,啊.但是克其目的，无不是明心见性，唉。","cover":"https://www.xinmizj.com/res/video/cover/yylr-ffxzxy.jpg"},{"id":"16","title":"法融禅净密","info":"讲到佛法 本来无话可说，因为一切众生，本具如来智慧德相，凡有言说，皆无实义。","cover":"https://www.xinmizj.com/res/video/cover/yylr-frcjm.jpg"},{"id":"11","title":"镇江随缘开示","info":"元音老人镇江随缘开示","cover":"https://www.xinmizj.com/res/video/cover/yylr-zjsyks.jpg"},{"id":"10","title":"随缘开示","info":"元音老人随缘开示","cover":"https://www.xinmizj.com/res/video/cover/yylr-syks.jpg"}]
     * count : 10
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

    public static class DataBean {
        /**
         * id : 35
         * title : 美国居士家座谈
         * info : 元音老人美国居士家座谈
         * cover : https://www.xinmizj.com/res/video/cover/yylr-mgjsjzt.jpg
         */

        private String id;
        private String title;
        private String info;
        private String cover;

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
    }
}
