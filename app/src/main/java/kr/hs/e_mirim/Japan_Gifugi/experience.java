package kr.hs.e_mirim.Japan_Gifugi;

public class experience {
    String address, explain, price, tel, time, image;

    public experience() {

    }

    public experience(String address, String explain, String price, String tel, String time, String image) {
        this.address = address;
        this.explain = explain;
        this.price = price;
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

    public String getPrice() {
        return price;
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