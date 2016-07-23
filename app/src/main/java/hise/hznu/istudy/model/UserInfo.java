package hise.hznu.istudy.model;

import java.io.Serializable;

/**
 * Created by PC on 2016/7/22.
 */
public class UserInfo implements Serializable{
    private String name;
    private String gender;
    private String cls;
    private String phone;
    private String email;
    private String avtarurl;

    public String getAvtarurl() {
        return avtarurl;
    }

    public void setAvtarurl(String avtarurl) {
        this.avtarurl = avtarurl;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
