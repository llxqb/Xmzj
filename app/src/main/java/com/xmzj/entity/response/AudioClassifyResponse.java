package com.xmzj.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 音频分类Response
 */
public class AudioClassifyResponse {

    /**
     * code : 0
     * data : [{"childs":[{"childs":[],"id":"4","name":"因果与业力","parentId":"1"},{"childs":[],"id":"5","name":"出离与解脱","parentId":"1"},{"childs":[],"id":"6","name":"佛法在世间不离世间觉","parentId":"1"}],"id":"1","name":"佛教常识","parentId":""},{"childs":[{"childs":[],"id":"7","name":"基本教理","parentId":"2"},{"childs":[],"id":"8","name":"心经","parentId":"2"},{"childs":[],"id":"9","name":"金刚经","parentId":"2"},{"childs":[],"id":"25","name":"楞严经","parentId":"2"},{"childs":[],"id":"10","name":"佛心经","parentId":"2"},{"childs":[],"id":"11","name":"其他","parentId":"2"}],"id":"2","name":"佛经学习","parentId":""},{"childs":[{"childs":[],"id":"12","name":"认识心中心法","parentId":"3"},{"childs":[],"id":"13","name":"六字大明咒","parentId":"3"},{"childs":[{"id":"15","name":"座上用功","parentId":"14","childs":[{"id":"26","name":"百座","parentId":"15","childs":[]},{"id":"18","name":"千坐","parentId":"15","childs":[]},{"id":"19","name":"打七与打九","parentId":"15","childs":[]},{"id":"20","name":"方便与其他","parentId":"15","childs":[]}]},{"id":"16","name":"坐下观照","parentId":"14","childs":[]},{"id":"17","name":"宗趣归宿","parentId":"14","childs":[{"id":"21","name":"明心见性","parentId":"17","childs":[]},{"id":"22","name":"除习与妙用","parentId":"17","childs":[]},{"id":"23","name":"以禅为体","parentId":"17","childs":[]},{"id":"24","name":"关于净土","parentId":"17","childs":[]}]},{"id":"27","name":"方便与其他","parentId":"14","childs":[]}],"id":"14","name":"心中心法","parentId":"3"}],"id":"3","name":"心中心","parentId":""}]
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

    public static class DataBean implements Parcelable {
        /**
         * childs : [{"childs":[],"id":"4","name":"因果与业力","parentId":"1"},{"childs":[],"id":"5","name":"出离与解脱","parentId":"1"},{"childs":[],"id":"6","name":"佛法在世间不离世间觉","parentId":"1"}]
         * id : 1
         * name : 佛教常识
         * parentId :
         */

        private String id;
        private String name;
        private String parentId;
        private List<ChildsBean> childs;

        protected DataBean(Parcel in) {
            id = in.readString();
            name = in.readString();
            parentId = in.readString();
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public List<ChildsBean> getChilds() {
            return childs;
        }

        public void setChilds(List<ChildsBean> childs) {
            this.childs = childs;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeString(parentId);
        }

        public static class ChildsBean {
            /**
             * childs : []
             * id : 4
             * name : 因果与业力
             * parentId : 1
             */

            private String id;
            private String name;
            private String parentId;
            private List<?> childs;

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

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public List<?> getChilds() {
                return childs;
            }

            public void setChilds(List<?> childs) {
                this.childs = childs;
            }
        }
    }
}
