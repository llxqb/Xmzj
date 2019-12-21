package com.xmzj.entity.response;

import java.util.List;

public class ChapterListResponse {

    /**
     * code : 0
     * data : [{"id":"159","sectionName":"坛经述旨"},{"id":"160","sectionName":"自序品第一"},{"id":"161","sectionName":"般若品第二"},{"id":"162","sectionName":"决疑品第三"},{"id":"163","sectionName":"定慧品第四"},{"id":"164","sectionName":"妙行品第五"},{"id":"165","sectionName":"忏悔品第六"},{"id":"166","sectionName":"机缘品第七"},{"id":"167","sectionName":"顿渐品第八"},{"id":"168","sectionName":"护法品第九"},{"id":"169","sectionName":"付嘱品第十"},{"id":"170","sectionName":"附录"}]
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
         * id : 159
         * sectionName : 坛经述旨
         */

        private String id;
        private String sectionName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSectionName() {
            return sectionName;
        }

        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
        }
    }
}
