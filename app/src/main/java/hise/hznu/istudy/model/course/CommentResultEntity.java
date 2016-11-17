package hise.hznu.istudy.model.course;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC on 2016/11/15.
 */
public class CommentResultEntity implements Serializable{
    /**
     * questionid:题目id
     comments: 评论文本内容
     isauthorvisible:被评论一方是否可见 默认为false
     rules:
     [
     {
     ruleid:规则id
     score:评论分数
     }
     ]
     }
     */
    private String questionid;
    private String comments;
    private String isauthorvisible;
    private List<rules> rules;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getIsauthorvisible() {
        return isauthorvisible;
    }

    public void setIsauthorvisible(String isauthorvisible) {
        this.isauthorvisible = isauthorvisible;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public List<CommentResultEntity.rules> getRules() {
        return rules;
    }

    public void setRules(List<CommentResultEntity.rules> rules) {
        this.rules = rules;
    }

    public static class rules{
        private String ruleid;
        private String score;

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
}
