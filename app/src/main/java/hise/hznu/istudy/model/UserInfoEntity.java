package hise.hznu.istudy.model;

import java.io.Serializable;

/**
 * Created by PC on 2016/10/24.
 */
public class UserInfoEntity implements Serializable{
    /**
     * username ： 用户名
     name	:    姓名
     gender：用户性别
     institute ： 学院
     cls：班级
     phone：手机号
     email:邮箱
     avtarurl:头像路径
     qq:      qq号码
     addr:    地址
     zipcode:  邮编
     */
    private String username;
    private String name;
    private String gender;
    private String institute;
    private String cls;
    private String phone;
    private String email;
    private String avtarurl;
    private String qq;
    private String addr;
    private String zipcode;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

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

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
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

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
