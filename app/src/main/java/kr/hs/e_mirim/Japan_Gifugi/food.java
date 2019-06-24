package kr.hs.e_mirim.Japan_Gifugi;

public class food {
    String address, closed, explane, site, tel, time;

    public food() {

    }

    public food(String address, String closed, String explane, String site, String tel, String time) {
        this.address = address;
        this.closed = closed;
        this.explane = explane;
        this.site = site;
        this.tel = tel;
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public String getClosed() {
        return closed;
    }

    public String getExplane() {
        return explane;
    }

    public String getSite() {
        return site;
    }

    public String getTel() {
        return tel;
    }

    public String getTime() {
        return time;
    }
}
