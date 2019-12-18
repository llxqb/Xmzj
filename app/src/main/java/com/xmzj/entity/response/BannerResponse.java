package com.xmzj.entity.response;

import java.util.List;

public class BannerResponse {
    /**
     * code : 0
     * data : [{"id":1,"url":"https://www.xinmizj.com/res/bunner/pic/1.png","order":1},{"id":2,"url":"https://www.xinmizj.com/res/bunner/pic/2.png","order":2},{"id":3,"url":"https://www.xinmizj.com/res/bunner/pic/3.png","order":3}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * url : https://www.xinmizj.com/res/bunner/pic/1.png
         * order : 1
         */

        private int id;
        private String url;
        private int order;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }
    }
}
