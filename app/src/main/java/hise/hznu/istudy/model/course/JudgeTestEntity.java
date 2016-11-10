package hise.hznu.istudy.model.course;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC on 2016/11/10.
 */
public class JudgeTestEntity implements Serializable {
    /**
     * info				:	{
     success		    :                     是否成功
     questioninfo				题目信息(提示学生第几套第几题)
     fullscore					总分
     gotscore					得分
     points : 					具体得分点
     [
     right			是否正确
     knowledge		知识点
     message			反馈信息
     key				参考答案
     comment			评语
     fullscore		总分
     gotscore		得分
     */
    private String success;
    private String questioninfo;
    private String fullscore;
    private String gotscore;
    private List<Points> points;
    public String getFullscore() {
        return fullscore;
    }

    public void setFullscore(String fullscore) {
        this.fullscore = fullscore;
    }

    public String getGotscore() {
        return gotscore;
    }

    public void setGotscore(String gotscore) {
        this.gotscore = gotscore;
    }

    public List<Points> getPoints() {
        return points;
    }

    public void setPoints(List<Points> points) {
        this.points = points;
    }

    public String getQuestioninfo() {
        return questioninfo;
    }

    public void setQuestioninfo(String questioninfo) {
        this.questioninfo = questioninfo;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public static class Points{
        private String right;
        private String knowledge;
        private String message;
        private String key;
        private String comment;
        private String fullscore;
        private String gotscore;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getFullscore() {
            return fullscore;
        }

        public void setFullscore(String fullscore) {
            this.fullscore = fullscore;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getGotscore() {
            return gotscore;
        }

        public void setGotscore(String gotscore) {
            this.gotscore = gotscore;
        }

        public String getKnowledge() {
            return knowledge;
        }

        public void setKnowledge(String knowledge) {
            this.knowledge = knowledge;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getRight() {
            return right;
        }

        public void setRight(String right) {
            this.right = right;
        }
    }
}
