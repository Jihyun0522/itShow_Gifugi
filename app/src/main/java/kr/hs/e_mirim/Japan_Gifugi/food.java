package kr.hs.e_mirim.Japan_Gifugi;

public class food {
    String address, closed, explain, site, tel, time, image;

    public food() {

    }

    public food(String address, String closed, String explain, String site, String tel, String time, String image) {
        this.address = address;
        this.closed = closed;
        this.explain = explain;
        this.site = site;
        this.tel = tel;
        this.time = time;
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public String getClosed() {
        return closed;
    }

    public String getExplain() {
        return explain;
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

    public String getImage() {
        return image;
    }
}