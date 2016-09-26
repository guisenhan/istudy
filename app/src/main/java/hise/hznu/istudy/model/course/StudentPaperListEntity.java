package hise.hznu.istudy.model.course;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC on 2016/9/26.
 */
public class StudentPaperListEntity implements Serializable{
    /**
     * “type”   ：         题型类别（基础题型）
     “id”: 题目id
     “answerext”:有可能评论一个文件 例如doc 这是文件路径
     “content” ： 题目内容
     “totalscore” ：分值
     “answer” ： 学生答案
     comments:评论
     score:分数
     isauthorvisible：被评论一方是否可见
     “rules” :    评分指标
     [
     {
     ruleid : 规则id
     contents ： 指标内容
     score ：  最高可评分值

     }
     ]
     */
    private int type;
    private String id;
    private String answerext;
    private String content;
    private String totalscore;
    private String answer;
    private String commnets;
    private String score;
    private String isauthorvisible;
    private List<CommentRuleEntity> rules;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerext() {
        return answerext;
    }

    public void setAnswerext(String answerext) {
        this.answerext = answerext;
    }

    public String getCommnets() {
        return commnets;
    }

    public void setCommnets(String commnets) {
        this.commnets = commnets;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsauthorvisible() {
        return isauthorvisible;
    }

    public void setIsauthorvisible(String isauthorvisible) {
        this.isauthorvisible = isauthorvisible;
    }

    public List<CommentRuleEntity> getRules() {
        return rules;
    }

    public void setRules(List<CommentRuleEntity> rules) {
        this.rules = rules;
    }

    public String getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(String totalscore) {
        this.totalscore = totalscore;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
