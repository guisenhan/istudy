package hise.hznu.istudy.model.course;

import java.io.Serializable;

/**
 * Created by PC on 2016/9/26.
 */
public class CommentTaskEntity implements Serializable{
    /**
     * “testid”		:	,			考试id
     “title”	:					作业标题
     “memo”   ：                  作业说明
     “teacher” ：                 教师姓名
     “datestart”	:				互评开始日期yyyyMMddHHmmss
     “dateend”	:				互评截止日期yyyyMMddHHmmss
     “progress”     ：  进度（0-1）
     */
    private String testid;
    private String title;
    private String memo;
    private String teacher;
    private String datestart;
    private String dateend;
    private String progress;

    public String getDateend() {
        return dateend;
    }

    public void setDateend(String dateend) {
        this.dateend = dateend;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDatestart() {
        return datestart;
    }

    public void setDatestart(String datestart) {
        this.datestart = datestart;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
