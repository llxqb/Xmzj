package com.xmzj.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 视频分类Response
 */
public class VideoClassifyResponse {
    /**
     * code : 0
     * data : [{"id":"A","name":"元音老人视频","parentId":"","childs":[{"id":"A1","name":"随缘问答","parentId":"A","childs":[]},{"id":"A2","name":"各地开示","parentId":"A","childs":[]},{"id":"A3","name":"讲经","parentId":"A","childs":[]}]},{"id":"B","name":"齐老师视频","parentId":"","childs":[{"id":"B1","name":"灌顶答疑","parentId":"B","childs":[]},{"id":"B2","name":"打七答疑","parentId":"B","childs":[]},{"id":"B3","name":"讲经","parentId":"B","childs":[]}]},{"id":"C","name":"心密师兄","parentId":"","childs":[{"id":"C1","name":"郭老师视频","parentId":"C","childs":[]},{"id":"C2","name":"心中心法手印视频","parentId":"C","childs":[]}]}]
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
         * id : A
         * name : 元音老人视频
         * parentId :
         * childs : [{"id":"A1","name":"随缘问答","parentId":"A","childs":[]},{"id":"A2","name":"各地开示","parentId":"A","childs":[]},{"id":"A3","name":"讲经","parentId":"A","childs":[]}]
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

        public static class ChildsBean implements Parcelable{
            /**
             * id : A1
             * name : 随缘问答
             * parentId : A
             * childs : []
             */

            private String id;
            private String name;
            private String parentId;
            private List<?> childs;

            protected ChildsBean(Parcel in) {
                id = in.readString();
                name = in.readString();
                parentId = in.readString();
            }

            public static final Creator<ChildsBean> CREATOR = new Creator<ChildsBean>() {
                @Override
                public ChildsBean createFromParcel(Parcel in) {
                    return new ChildsBean(in);
                }

                @Override
                public ChildsBean[] newArray(int size) {
                    return new ChildsBean[size];
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

            public List<?> getChilds() {
                return childs;
            }

            public void setChilds(List<?> childs) {
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
        }
    }
}
