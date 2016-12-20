package hise.hznu.istudy.model.course;

import java.io.Serializable;
import java.util.List;

import hise.hznu.istudy.model.FileEntity;

/**
 * Created by PC on 2016/9/26.
 */
public class CommentPaperEntity implements Serializable{
    /**
     *  "type": "",
     "id": 50332,
     "content": "互评测试-文件",
     "totalscore": 0,
     "answer": "",
     "answerext": "http://dodo.hznu.edu.cn/Upload/test/1007/answer/50332/af4b6f4d-64a5-4fe3-ad75-a6b100615aff.jpg",
     "score": 9,
     "strandanswer": "",
     "isauthorvisible": false,
     "comments": "",
     "rules": [
     {
     "ruleid": 241,
     "contents": "命题",
     "totalscore": 4,
     "score": 3
     },
     */
    private String type;
    private String id;
    private String content;
    private String totalscore;
    private String answer;
    private String answerext;
    private List<FileEntity> answerfiles;

    private String score;
    private String strandanswer;
    private String isauthorvisible;
    private String comments;
    private List<CommentRuleEntity> rules;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<FileEntity> getAnswerfiles() {
        return answerfiles;
    }

    public void setAnswerfiles(List<FileEntity> answerfiles) {
        this.answerfiles = answerfiles;
    }

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStrandanswer() {
        return strandanswer;
    }

    public void setStrandanswer(String strandanswer) {
        this.strandanswer = strandanswer;
    }

    public String getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(String totalscore) {
        this.totalscore = totalscore;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
