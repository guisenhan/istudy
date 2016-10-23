package hise.hznu.istudy.model;

import java.io.Serializable;

/**
 * Created by PC on 2016/10/19.
 */
public class UpLoadFileEntity implements Serializable{
    private String succ;
    private String uploadedfilename;
    private String uploadedurl;

    public String getSucc() {
        return succ;
    }

    public void setSucc(String succ) {
        this.succ = succ;
    }

    public String getUploadedfilename() {
        return uploadedfilename;
    }

    public void setUploadedfilename(String uploadedfilename) {
        this.uploadedfilename = uploadedfilename;
    }

    public String getUploadedurl() {
        return uploadedurl;
    }

    public void setUploadedurl(String uploadedurl) {
        this.uploadedurl = uploadedurl;
    }
}
