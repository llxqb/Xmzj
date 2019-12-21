package com.xmzj.entity.response;

import java.util.List;

public class BooksListResponse {

    /**
     * code : 0
     * data : [{"id":"3","name":"大愚阿阇黎略传","cover":"https://www.xinmizj.com/res/book/cover/dyasl-lz.jpg"},{"id":"4","name":"解脱歌","cover":"https://www.xinmizj.com/res/book/cover/dyqsl-jtg.jpg"},{"id":"5","name":"修行要诀","cover":"https://www.xinmizj.com/res/book/cover/dyasl-xxyj-.jpg"}]
     * count : 3
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
         * id : 3
         * name : 大愚阿阇黎略传
         * cover : https://www.xinmizj.com/res/book/cover/dyasl-lz.jpg
         */

        private String id;
        private String name;
        private String cover;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
