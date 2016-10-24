package hise.hznu.istudy.model.course;

import java.io.Serializable;

/**
 * Created by PC on 2016/10/24.
 */
public class CommentBackEntity implements Serializable{
    /**
     * id”			:,			帖子Id
     ”content”		: 			内容html格式
     “date”			:			发布时间	 yyyyMMddHHmmss
     ”avatar_url”		:		头像路径,
     ”author”		:			发布者
     "authorcomp"  ：         发布者单位

     */
    private String id;
    private String content;
    private String date;
    private String avatar_url;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
