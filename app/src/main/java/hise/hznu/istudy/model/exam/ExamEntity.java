package hise.hznu.istudy.model.exam;

import java.io.Serializable;

/**
 * Created by PC on 2016/9/26.
 */
public class ExamEntity implements Serializable{
    /**
     * “id”			:,		考试Id
     “myscore”		:		我的得分
     “memo” 	    :		考试描述
     ”teacher”	    :	    任课老师
     “kscc”       :        “考试场次”
     “ksdd” :                考试地点
     “kszw”                 考试座位
     “xq”：                  校区
     xy                    学院
     “datestart”        考试开始时间 yyyyMMddHHmmss
     dateend           考试截止时间 yyyyMMddHHmmss
     score             总分
     title             考试名称
     */
    private String id;
    private String myscore;
    private String memo;
    private String teacher;
    private String kscc;
    private String kszw;
    private String ksdd;

    private String xq;
    private String xy;
    private String datestart;
    private String dateend;
    private String score;
    private String title;

    public String getKsdd() {
        return ksdd;
    }

    public void setKsdd(String ksdd) {
        this.ksdd = ksdd;
    }

    public String getDateend() {
        return dateend;
    }

    public void setDateend(String dateend) {
        this.dateend = dateend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatestart() {
        return datestart;
    }

    public void setDatestart(String datestart) {
        this.datestart = datestart;
    }

    public String getKscc() {
        return kscc;
    }

    public void setKscc(String kscc) {
        this.kscc = kscc;
    }

    public String getKszw() {
        return kszw;
    }

    public void setKszw(String kszw) {
        this.kszw = kszw;
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

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getXy() {
        return xy;
    }

    public void setXy(String xy) {
        this.xy = xy;
    }
}
