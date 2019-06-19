package kr.hs.e_mirim.Japan_Gifugi;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String userName;
    public String userPw;

    public User() {

    }

    public User(String userName, String userPw){
        this.userName = userName;
        this.userPw = userPw;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setUserPw(String userPw){
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPw() {
        return userPw;
    }
}
