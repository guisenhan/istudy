package hise.hznu.istudy.model.course;

import java.io.Serializable;

/**
 * Created by PC on 2016/11/17.
 */
public class NotifyEntity implements Serializable{
    /**
     * “title”			:			文章标题
     ”content”			: 			内容html格式
     “date”				:			发布时间	 yyyyMMddHHmmss
     ”viewtimes”		:			点击次数
     ”author”			:			发布者
     */
    private String title;
    private String content;
    private String date;
    private String viewtimes;
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewtimes() {
        return viewtimes;
    }

    public void setViewtimes(String viewtimes) {
        this.viewtimes = viewtimes;
    }
}
