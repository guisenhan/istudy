package hise.hznu.istudy.model.course;

import java.io.Serializable;

/**
 * Created by PC on 2016/9/26.
 */
public class CommentPaperListEntity implements Serializable{
    /**
     * “usertestid” ,              同学试卷id
     “title”	:					作业标题
     “testername”   ：           同学姓名（一半是匿名）
     “score”  : 分数
     “hupingtime”  ：互评时间yyyyMMddHHmmss， 没有评过为空
     */
    private String usertestid;
    private String title;
    private String testername;
    private String score;
    private String hupingtime;

    public String getHupingtime() {
        return hupingtime;
    }

    public void setHupingtime(String hupingtime) {
        this.hupingtime = hupingtime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTestername() {
        return testername;
    }

    public void setTestername(String testername) {
        this.testername = testername;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsertestid() {
        return usertestid;
    }

    public void setUsertestid(String usertestid) {
        this.usertestid = usertestid;
    }
}
