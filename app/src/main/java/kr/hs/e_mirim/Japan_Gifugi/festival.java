package kr.hs.e_mirim.Japan_Gifugi;

public class festival {
    String address, explain, season, tel, time, image;

    public festival() {

    }

    public festival(String address, String explain, String season, String tel, String time, String imgae) {
        this.address = address;
        this.explain = explain;
        this.season = season;
        this.tel = tel;
        this.time = time;
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public String getExplain() {
        return explain;
    }

    public String getSeason() {
        return season;
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