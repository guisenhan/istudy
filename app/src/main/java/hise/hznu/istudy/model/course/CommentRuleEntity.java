package hise.hznu.istudy.model.course;

import java.io.Serializable;

/**
 * Created by PC on 2016/9/26.
 */
public class CommentRuleEntity implements Serializable{
    /**
     *  ruleid : 规则id
     contents ： 指标内容
     score ：  最高可评分值
     */
    private String ruleid;
    private String contents;
    private String score;
    private String totalscore;

    public String getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(String totalscore) {
        this.totalscore = totalscore;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getRuleid() {
        return ruleid;
    }

    public void setRuleid(String ruleid) {
        this.ruleid = ruleid;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
