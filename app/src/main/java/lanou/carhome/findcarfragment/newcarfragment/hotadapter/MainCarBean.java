package lanou.carhome.findcarfragment.newcarfragment.hotadapter;

import java.util.List;

/**
 * Created by dllo on 16/9/29.
 */
public class MainCarBean {

    /**
     * returncode : 0
     * message :
     * result : {"list":[{"areaid":1775,"id":376379,"seriesid":3861,"seriesname":"沃尔沃S90","img":"http://adm3.autoimg.cn/admdfs/g5/M00/24/4F/wKgH21fGq5mAWEyoAABmpaqI26k189.png","url":"http://c.autohome.com.cn/adfront/click?ah_mark=0883DDFD1B971D4872F4E00BDD19DB55C9FAAED40414D38897EE431757F0820ACA034B938297FB6375FE708927D7DE441F25621738DBABCC3CD5292A473376E0EEFE8CCE4B793962EFB949D65031F1D71755300645442E8DD5917918A2B88F55&u=4312F46F984B7FFC8984209CBA3C7DDBC19BF1C7240B7759CB73E687B3840318629241FDD3DB646723A19CEC854C7145230F34131BC5DBD6D69DB3C36341D6BEB3E9D72D809C04CADBC81E0A960F46640A94826B2DE85A3E5EE2FEB8674B43646B477A3D3F0ECE08574AD2BA0C1B5833A14DA4B4FDEB99A4BBA24EBEC5D1C7F7E9EBBAA4942EA426845C1FDF25DC6115DAC53E38F4457CCC5FF637735F6C54B46861A24A88F19EB31F5B15B8FE99F7A92CC8A7552C299C819178D183AA7252836CD58CDF141B6A8F95C18CA80A47AA16FB9E725A88E7A0B2B29745D4CAF616708AE1F869AC0A9366DA8541EB4E8816C98AA8BCECC3F37237A41CEE7BB11E6477A8D304089A07AC279700527CEE3BEC551F9737EDBA7991FDEB0D5D4A64B941F2&isjump=0","thurl":"","jumptype":0,"jumpurl":"","areaid_cxzs":1781,"pvid":"bb554916-8504-4f9c-b05a-e774d64668790","ishavead":1,"thirdadurl":"","rdposturl":"http://rd.autohome.com.cn/adfront/realdeliver?"},{"areaid":1776,"id":315104,"seriesid":3781,"seriesname":"比亚迪元","img":"http://img2.autoimg.cn/admdfs/g21/M0A/2F/64/wKgFWlfguKWAbtKgAACZhgnVv_w322.png","url":"http://c.autohome.com.cn/adfront/click?ah_mark=7D9B6787F0DBA014F513454281212FC7C9FAAED40414D38897EE431757F0820AC78D735EED667B2439BA836D4351FCBDF5AB32E8ECE27CF258B1220B085DD1D9D1DF3D6FBE7AD25E99E67BDFFBC00C5AA1BE196553D916F20C2A1FDEF8F9C5D2&u=&isjump=0","thurl":"","jumptype":1,"jumpurl":"http://c.autohome.com.cn/adfront/click?ah_mark=7D9B6787F0DBA014F513454281212FC7C9FAAED40414D38897EE431757F0820AC78D735EED667B2439BA836D4351FCBDF5AB32E8ECE27CF258B1220B085DD1D9D1DF3D6FBE7AD25E99E67BDFFBC00C5AA1BE196553D916F20C2A1FDEF8F9C5D2&u=4497CA9F1B94D8C664BEBA35F434587CA56442F05794B642911B923AAA11851CC80414DAC479168094E53F11373F30A8F426F73D385A10E3197EA83FF883C2E5A64C4B7516D0F5F00A62D69C9ACD02E7E2DD3F74C9D5D2AFF6E38BFEC68410C96875103CA3559158A6DC52CEEE3D0C26C8E2D3D52370EA4B360A1205F25E497C2EDEC9AF24B7CDD54E37435EC2C3B48942FC47DFF4A627978F23136D18705C8C994B1A0572F40BD27EC17D936ED656C5C3690094A23AC70D97AF4A878A456DE1","areaid_cxzs":1782,"pvid":"bb554916-8504-4f9c-b05a-e774d64668791","ishavead":1,"thirdadurl":"","rdposturl":"http://rd.autohome.com.cn/adfront/realdeliver?"},{"areaid":1777,"id":376415,"seriesid":3777,"seriesname":"驭胜S330","img":"http://adm3.autoimg.cn/admdfs/g23/M02/04/4B/wKjBwFfGqnWAAN_JAAA2nLf4ccY698.png","url":"http://c.autohome.com.cn/adfront/click?ah_mark=2234A4E7270E73014A7334C0A91A2A7EC9FAAED40414D38897EE431757F0820A4A62C2FB4D4ADB474856D0F5949B35658F08D4CBA602EC474E35E973FD0B65BF30A0C721F774C666B4CC8A47C83F7FBD1755300645442E8DD5917918A2B88F55&u=&isjump=0","thurl":"","jumptype":0,"jumpurl":"","areaid_cxzs":1783,"pvid":"bb554916-8504-4f9c-b05a-e774d64668792","ishavead":1,"thirdadurl":"","rdposturl":"http://rd.autohome.com.cn/adfront/realdeliver?"}]}
     */

    private int returncode;
    private String message;
    private ResultBean result;

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * areaid : 1775
         * id : 376379
         * seriesid : 3861
         * seriesname : 沃尔沃S90
         * img : http://adm3.autoimg.cn/admdfs/g5/M00/24/4F/wKgH21fGq5mAWEyoAABmpaqI26k189.png
         * url : http://c.autohome.com.cn/adfront/click?ah_mark=0883DDFD1B971D4872F4E00BDD19DB55C9FAAED40414D38897EE431757F0820ACA034B938297FB6375FE708927D7DE441F25621738DBABCC3CD5292A473376E0EEFE8CCE4B793962EFB949D65031F1D71755300645442E8DD5917918A2B88F55&u=4312F46F984B7FFC8984209CBA3C7DDBC19BF1C7240B7759CB73E687B3840318629241FDD3DB646723A19CEC854C7145230F34131BC5DBD6D69DB3C36341D6BEB3E9D72D809C04CADBC81E0A960F46640A94826B2DE85A3E5EE2FEB8674B43646B477A3D3F0ECE08574AD2BA0C1B5833A14DA4B4FDEB99A4BBA24EBEC5D1C7F7E9EBBAA4942EA426845C1FDF25DC6115DAC53E38F4457CCC5FF637735F6C54B46861A24A88F19EB31F5B15B8FE99F7A92CC8A7552C299C819178D183AA7252836CD58CDF141B6A8F95C18CA80A47AA16FB9E725A88E7A0B2B29745D4CAF616708AE1F869AC0A9366DA8541EB4E8816C98AA8BCECC3F37237A41CEE7BB11E6477A8D304089A07AC279700527CEE3BEC551F9737EDBA7991FDEB0D5D4A64B941F2&isjump=0
         * thurl :
         * jumptype : 0
         * jumpurl :
         * areaid_cxzs : 1781
         * pvid : bb554916-8504-4f9c-b05a-e774d64668790
         * ishavead : 1
         * thirdadurl :
         * rdposturl : http://rd.autohome.com.cn/adfront/realdeliver?
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int areaid;
            private int id;
            private int seriesid;
            private String seriesname;
            private String img;
            private String url;
            private String thurl;
            private int jumptype;
            private String jumpurl;
            private int areaid_cxzs;
            private String pvid;
            private int ishavead;
            private String thirdadurl;
            private String rdposturl;

            public int getAreaid() {
                return areaid;
            }

            public void setAreaid(int areaid) {
                this.areaid = areaid;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSeriesid() {
                return seriesid;
            }

            public void setSeriesid(int seriesid) {
                this.seriesid = seriesid;
            }

            public String getSeriesname() {
                return seriesname;
            }

            public void setSeriesname(String seriesname) {
                this.seriesname = seriesname;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThurl() {
                return thurl;
            }

            public void setThurl(String thurl) {
                this.thurl = thurl;
            }

            public int getJumptype() {
                return jumptype;
            }

            public void setJumptype(int jumptype) {
                this.jumptype = jumptype;
            }

            public String getJumpurl() {
                return jumpurl;
            }

            public void setJumpurl(String jumpurl) {
                this.jumpurl = jumpurl;
            }

            public int getAreaid_cxzs() {
                return areaid_cxzs;
            }

            public void setAreaid_cxzs(int areaid_cxzs) {
                this.areaid_cxzs = areaid_cxzs;
            }

            public String getPvid() {
                return pvid;
            }

            public void setPvid(String pvid) {
                this.pvid = pvid;
            }

            public int getIshavead() {
                return ishavead;
            }

            public void setIshavead(int ishavead) {
                this.ishavead = ishavead;
            }

            public String getThirdadurl() {
                return thirdadurl;
            }

            public void setThirdadurl(String thirdadurl) {
                this.thirdadurl = thirdadurl;
            }

            public String getRdposturl() {
                return rdposturl;
            }

            public void setRdposturl(String rdposturl) {
                this.rdposturl = rdposturl;
            }
        }
    }
}
