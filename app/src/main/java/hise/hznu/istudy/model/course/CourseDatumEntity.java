package hise.hznu.istudy.model.course;

import java.io.Serializable;

/**
 * Created by PC on 2016/9/23.
 */
public class CourseDatumEntity implements Serializable {
    /**
     * “filesize”			:,		文件大小
     “extensions”		:		文件类型
     “filename”		    :		文件名字
     ”url”	    :	    文件url路径
     “datecreated”       :     “文件创建日期”
     */
    private String filesize;
    private String extensions;
    private String filename;
    private String url;
    private long datecreated;

    public CourseDatumEntity() {
    }

    public long getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(long datecreated) {
        this.datecreated = datecreated;
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
