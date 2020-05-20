package com.sdacn.yinpin.bean.home;

import java.util.List;

/**
 * @author 创建人 ：ouyangSuperHandsome
 * @version 1.0
 * @package 包名 ：com.sdacn.yinpin.bean.home
 * @createTime 创建时间 ：2020/5/20 22:51
 * @modifyBy 修改人 ：ouyangSuperHandsome
 * @modifyTime 修改时间 ：2020/5/20 22:51
 * @modifyMemo 修改备注：
 */
public class AudioTypeBean {

    /**
     * total : 75
     * rows : [{"id":1,"status":1,"cate_name":"圣经","cate_desc":"","cate_image":"","cate_content":"","pid":0,"update_time":1587106113,"weigh":1,"fname":"圣经","childs":[{"id":5,"status":1,"cate_name":"旧约","cate_desc":"","cate_image":"","cate_content":"","pid":1,"update_time":1587106659,"weigh":0,"fname":"圣经"},{"id":6,"status":1,"cate_name":"新约","cate_desc":"","cate_image":"","cate_content":"","pid":1,"update_time":1587106663,"weigh":0,"fname":"圣经"}]},{"id":2,"status":1,"cate_name":"怀著","cate_desc":"","cate_image":"","cate_content":"","pid":0,"update_time":1587106150,"weigh":2,"fname":"怀著"},{"id":3,"status":1,"cate_name":"教育","cate_desc":"","cate_image":"","cate_content":"","pid":0,"update_time":1587106160,"weigh":3,"fname":"教育"},{"id":4,"status":1,"cate_name":"专题","cate_desc":"","cate_image":"","cate_content":"","pid":0,"update_time":1587106185,"weigh":4,"fname":"专题","childs":[{"id":73,"status":1,"cate_name":"称义","cate_desc":"","cate_image":"","cate_content":"","pid":4,"update_time":1587733755,"weigh":0,"fname":"专题"},{"id":74,"status":1,"cate_name":"成圣","cate_desc":"","cate_image":"","cate_content":"","pid":4,"update_time":1587733766,"weigh":0,"fname":"专题"},{"id":75,"status":1,"cate_name":"1888年","cate_desc":"","cate_image":"","cate_content":"","pid":4,"update_time":1587733779,"weigh":0,"fname":"专题"}]}]
     * pagess : 1
     */

    private int total;
    private int pagess;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPagess() {
        return pagess;
    }

    public void setPagess(int pagess) {
        this.pagess = pagess;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 1
         * status : 1
         * cate_name : 圣经
         * cate_desc :
         * cate_image :
         * cate_content :
         * pid : 0
         * update_time : 1587106113
         * weigh : 1
         * fname : 圣经
         * childs : [{"id":5,"status":1,"cate_name":"旧约","cate_desc":"","cate_image":"","cate_content":"","pid":1,"update_time":1587106659,"weigh":0,"fname":"圣经"},{"id":6,"status":1,"cate_name":"新约","cate_desc":"","cate_image":"","cate_content":"","pid":1,"update_time":1587106663,"weigh":0,"fname":"圣经"}]
         */

        private int id;
        private int status;
        private String cate_name;//一级目录名
        private String cate_desc;
        private String cate_image;
        private String cate_content;
        private int pid;
        private int update_time;
        private int weigh;
        private String fname;
        private List<ChildsBean> childs;//二级目录列表

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public String getCate_desc() {
            return cate_desc;
        }

        public void setCate_desc(String cate_desc) {
            this.cate_desc = cate_desc;
        }

        public String getCate_image() {
            return cate_image;
        }

        public void setCate_image(String cate_image) {
            this.cate_image = cate_image;
        }

        public String getCate_content() {
            return cate_content;
        }

        public void setCate_content(String cate_content) {
            this.cate_content = cate_content;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public int getWeigh() {
            return weigh;
        }

        public void setWeigh(int weigh) {
            this.weigh = weigh;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public List<ChildsBean> getChilds() {
            return childs;
        }

        public void setChilds(List<ChildsBean> childs) {
            this.childs = childs;
        }

        public static class ChildsBean {
            /**
             * id : 5
             * status : 1
             * cate_name : 旧约
             * cate_desc :
             * cate_image :
             * cate_content :
             * pid : 1
             * update_time : 1587106659
             * weigh : 0
             * fname : 圣经
             */

            private int id;
            private int status;
            private String cate_name;//一级目录下对应的二级目录
            private String cate_desc;
            private String cate_image;
            private String cate_content;
            private int pid;
            private int update_time;
            private int weigh;
            private String fname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }

            public String getCate_desc() {
                return cate_desc;
            }

            public void setCate_desc(String cate_desc) {
                this.cate_desc = cate_desc;
            }

            public String getCate_image() {
                return cate_image;
            }

            public void setCate_image(String cate_image) {
                this.cate_image = cate_image;
            }

            public String getCate_content() {
                return cate_content;
            }

            public void setCate_content(String cate_content) {
                this.cate_content = cate_content;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public int getWeigh() {
                return weigh;
            }

            public void setWeigh(int weigh) {
                this.weigh = weigh;
            }

            public String getFname() {
                return fname;
            }

            public void setFname(String fname) {
                this.fname = fname;
            }
        }
    }

    @Override
    public String toString() {
        return "AudioTypeBean{" +
                "total=" + total +
                ", pagess=" + pagess +
                ", rows=" + rows +
                '}';
    }
}
