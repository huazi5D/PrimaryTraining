package com.li.primary;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21 0021.
 */

public class Gson {

    /**
     * status : true
     * message : success
     * data : {"sysareList":[{"CODE2":"00","TITLE2":"00"},{"CODE2":"01","TITLE2":"太原市"},{"CODE2":"02","TITLE2":"大同市"},{"CODE2":"03","TITLE2":"阳泉市"},{"CODE2":"04","TITLE2":"长治市"},{"CODE2":"05","TITLE2":"晋城市"},{"CODE2":"06","TITLE2":"朔州市"},{"CODE2":"07","TITLE2":"晋中市"},{"CODE2":"08","TITLE2":"运城市"},{"CODE2":"09","TITLE2":"忻州市"},{"CODE2":"10","TITLE2":"临汾市"},{"CODE2":"11","TITLE2":"吕梁市"}],"data":"{\"csny\":\"1967-07-01\",\"cyzg_clrq\":\"2002-06-28\",\"cyzglb\":\"105\",\"cyzgzh\":\"1408001050015000563\",\"jsz_clrq\":\"1988-05-24\",\"jszh\":\"142701196707012457\",\"jxjy_jssj\":null,\"jxjy_kssj\":null,\"lxdh\":\"\",\"mz\":\"00\",\"pxjgbh\":null,\"ryid\":\"142701000103\",\"sjhm\":\"13835879258\",\"sqrq\":null,\"ssjg\":\"140800\",\"whcd\":null,\"xb\":\"1\",\"xcyzg_fzrq\":\"2015-05-20\",\"xcyzg_yxqz\":\"2021-05-19\",\"xfzjg\":null,\"xm\":\"刘俊红\",\"xzqh\":null,\"ywzt\":null,\"zjcx\":null,\"zjh\":\"142701196707012457\",\"zjlx\":\"1\",\"zp\":\"\",\"zz\":\"山西省运城市盐湖区陶村镇苦池村\"}"}
     */

    private String status;
    private String message;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sysareList : [{"CODE2":"00","TITLE2":"00"},{"CODE2":"01","TITLE2":"太原市"},{"CODE2":"02","TITLE2":"大同市"},{"CODE2":"03","TITLE2":"阳泉市"},{"CODE2":"04","TITLE2":"长治市"},{"CODE2":"05","TITLE2":"晋城市"},{"CODE2":"06","TITLE2":"朔州市"},{"CODE2":"07","TITLE2":"晋中市"},{"CODE2":"08","TITLE2":"运城市"},{"CODE2":"09","TITLE2":"忻州市"},{"CODE2":"10","TITLE2":"临汾市"},{"CODE2":"11","TITLE2":"吕梁市"}]
         * data : {"csny":"1967-07-01","cyzg_clrq":"2002-06-28","cyzglb":"105","cyzgzh":"1408001050015000563","jsz_clrq":"1988-05-24","jszh":"142701196707012457","jxjy_jssj":null,"jxjy_kssj":null,"lxdh":"","mz":"00","pxjgbh":null,"ryid":"142701000103","sjhm":"13835879258","sqrq":null,"ssjg":"140800","whcd":null,"xb":"1","xcyzg_fzrq":"2015-05-20","xcyzg_yxqz":"2021-05-19","xfzjg":null,"xm":"刘俊红","xzqh":null,"ywzt":null,"zjcx":null,"zjh":"142701196707012457","zjlx":"1","zp":"","zz":"山西省运城市盐湖区陶村镇苦池村"}
         */

        private String data;
        private List<SysareListBean> sysareList;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public List<SysareListBean> getSysareList() {
            return sysareList;
        }

        public void setSysareList(List<SysareListBean> sysareList) {
            this.sysareList = sysareList;
        }

        public static class SysareListBean {
            /**
             * CODE2 : 00
             * TITLE2 : 00
             */

            private String CODE2;
            private String TITLE2;

            public String getCODE2() {
                return CODE2;
            }

            public void setCODE2(String CODE2) {
                this.CODE2 = CODE2;
            }

            public String getTITLE2() {
                return TITLE2;
            }

            public void setTITLE2(String TITLE2) {
                this.TITLE2 = TITLE2;
            }
        }
    }
}
