package hise.hznu.istudy.model.course;

import java.io.Serializable;

/**
 * Created by PC on 2016/9/26.
 */
public class HomeWorkEntity implements Serializable{
    /**
     * “id”		:	,				Record Id
     “title”	:					作业标题
     “memo”   ：                  作业说明
     “teacher” ：                 教师姓名
     “datestart”	:				开始日期yyyyMMddHHmmss
     “dateend”	:				截止日期yyyyMMddHHmmss
     “score”     ：               总分
     enableClientJudge			是否开启客户端阅卷
     keyVisible					阅卷时参考答案是否可见
     viewOneWithAnswerKey		查卷时参考答案是否可见
     myscore                       在截止后查看的自己的得分
     */
    private String id;
    private String title;
    private String memo;
    private String teacher;
    private String datestart;
    private String dateend;
    private String score;
    private String enableClientJudge;
    private String keyVisible;
    private String viewOneWithAnswerKey;
    private String myscore;

    public String getDateend() {
        return dateend;
    }

    public void setDateend(String dateend) {
        this.dateend = dateend;
    }

    public String getDatestart() {
        return datestart;
    }

    public void setDatestart(String datestart) {
        this.datestart = datestart;
    }

    public String getEnableClientJudge() {
        return enableClientJudge;
    }

    public void setEnableClientJudge(String enableClientJudge) {
        this.enableClientJudge = enableClientJudge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyVisible() {
        return keyVisible;
    }

    public void setKeyVisible(String keyVisible) {
        this.keyVisible = keyVisible;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMyscore() {
        return myscore;
    }

    public void setMyscore(String myscore) {
        this.myscore = myscore;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewOneWithAnswerKey() {
        return viewOneWithAnswerKey;
    }

    public void setViewOneWithAnswerKey(String viewOneWithAnswerKey) {
        this.viewOneWithAnswerKey = viewOneWithAnswerKey;
    }
}
