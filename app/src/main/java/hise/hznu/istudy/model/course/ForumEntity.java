package hise.hznu.istudy.model.course;

import java.io.Serializable;

/**
 * Created by PC on 2016/9/26.
 */
public class ForumEntity implements Serializable{
    /**
     * “top”			:,			1=置顶， 0=非
     “id”			:,			帖子Id
     ”title”		: 			标题
     ”content”		: 			内容html格式
     “date”			:			发布时间	 yyyyMMddHHmmss
     ”commentcount” :			回帖个数
     ”avatar_url”		:		头像路径, base64编码
     ”viewtimes”	:			点击次数
     ”author”		:			发布者
     "authorcomp"  ：         发布者单位
     */
    private int top;
    private String id;
    private String title;
    private String content;
    private String date;
    private String commentcount;
    private String avatar_url;
    private String viewtimes;
    private String author;
    private String authorcomp;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorcomp() {
        return authorcomp;
    }

    public void setAuthorcomp(String authorcomp) {
        this.authorcomp = authorcomp;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(String commentcount) {
        this.commentcount = commentcount;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public String getViewtimes() {
        return viewtimes;
    }

    public void setViewtimes(String viewtimes) {
        this.viewtimes = viewtimes;
    }
}
