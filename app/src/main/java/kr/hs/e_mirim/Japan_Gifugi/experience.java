package kr.hs.e_mirim.Japan_Gifugi;

public class experience {
    String address, explane, price, tel, time;

    public experience() {

    }

    public experience(String address, String explane, String price, String tel, String time) {
        this.address = address;
        this.explane = explane;
        this.price = price;
        this.tel = tel;
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public String getExplane() {
        return explane;
    }

    public String getPrice() {
        return price;
    }

    public String getTel() {
        return tel;
    }

    public String getTime() {
        return time;
    }
}