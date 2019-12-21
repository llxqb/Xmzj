package com.xmzj.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BookTypeResponse {
    /**
     * code : 0
     * data : [{"id":"1","parentId":"","name":"心密文集","child":[{"id":"2","parentId":"1","name":"佛心经","child":[]},{"id":"3","parentId":"1","name":"大愚祖师","child":[]},{"id":"4","parentId":"1","name":"王骧陆上师","child":[]},{"id":"5","parentId":"1","name":"元音老人","child":[]},{"id":"6","parentId":"1","name":"齐老师","child":[]},{"id":"17","parentId":"1","name":"其它","child":[]}]},{"id":"7","parentId":"","name":"禅宗辑要","child":[{"id":"14","parentId":"7","name":"七佛","child":[]},{"id":"15","parentId":"7","name":"本师化迹","child":[]},{"id":"16","parentId":"7","name":"古尊宿集","child":[]}]}]
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
         * id : 1
         * parentId :
         * name : 心密文集
         * child : [{"id":"2","parentId":"1","name":"佛心经","child":[]},{"id":"3","parentId":"1","name":"大愚祖师","child":[]},{"id":"4","parentId":"1","name":"王骧陆上师","child":[]},{"id":"5","parentId":"1","name":"元音老人","child":[]},{"id":"6","parentId":"1","name":"齐老师","child":[]},{"id":"17","parentId":"1","name":"其它","child":[]}]
         */

        private String id;
        private String parentId;
        private String name;
        private List<ChildBean> child;

        protected DataBean(Parcel in) {
            id = in.readString();
            parentId = in.readString();
            name = in.readString();
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

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(parentId);
            dest.writeString(name);
        }

        public static class ChildBean implements Parcelable{
            /**
             * id : 2
             * parentId : 1
             * name : 佛心经
             * child : []
             */

            private String id;
            private String parentId;
            private String name;
            private List<?> child;

            protected ChildBean(Parcel in) {
                id = in.readString();
                parentId = in.readString();
                name = in.readString();
            }

            public static final Creator<ChildBean> CREATOR = new Creator<ChildBean>() {
                @Override
                public ChildBean createFromParcel(Parcel in) {
                    return new ChildBean(in);
                }

                @Override
                public ChildBean[] newArray(int size) {
                    return new ChildBean[size];
                }
            };

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<?> getChild() {
                return child;
            }

            public void setChild(List<?> child) {
                this.child = child;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeString(parentId);
                dest.writeString(name);
            }
        }
    }
}
