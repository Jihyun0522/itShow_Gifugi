package kr.hs.e_mirim.Japan_Gifugi;

public class hotel {
    String address, explain, link, name, price, tel, image;

    public hotel() {

    }

    public hotel(String address, String explain, String link, String name, String price, String tel, String image){
        this.address = address;
        this.explain = explain;
        this.link = link;
        this.name = name;
        this.price = price;
        this.tel = tel;
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public String getExplain() {
        return explain;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getTel() {
        return tel;
    }

    public String getImage() {
        return image;
    }
}
