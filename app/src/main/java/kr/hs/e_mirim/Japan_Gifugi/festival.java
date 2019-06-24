package kr.hs.e_mirim.Japan_Gifugi;

public class festival {
    String address, explane, season, tel, time;

    public festival() {

    }

    public festival(String address, String explane, String season, String tel, String time) {
        this.address = address;
        this.explane = explane;
        this.season = season;
        this.tel = tel;
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public String getExplane() {
        return explane;
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
}
