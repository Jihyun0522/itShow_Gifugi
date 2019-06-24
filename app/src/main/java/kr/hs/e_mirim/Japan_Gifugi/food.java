package kr.hs.e_mirim.Japan_Gifugi;

public class food {
    String address, closed, explane, site, tel, time, image;

    public food() {

    }

    public food(String address, String closed, String explane, String site, String tel, String time, String image) {
        this.address = address;
        this.closed = closed;
        this.explane = explane;
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

    public String getImage() {
        return image;
    }
}
