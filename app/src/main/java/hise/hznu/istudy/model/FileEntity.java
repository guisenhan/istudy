package hise.hznu.istudy.model;

import java.io.Serializable;

/**
 * Created by PC on 2016/11/28.
 */
public class FileEntity implements Serializable{
    /**
     * "name":"5.jpg","url":"http://dodo.hznu.edu.cn/UploadFile/Download/8345db09-3917-48dd-9da5-a6b100621b75","size":"1.19
     */
    private String name;
    private String url;
    private String size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
