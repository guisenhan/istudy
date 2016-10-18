package hise.hznu.istudy.model.course;

import java.io.Serializable;

/**
 * Created by PC on 2016/10/18.
 */
public class AnswerEntity implements Serializable {
    /**
     * {
     testid	：	考试id
     questionid:  题目id
     answer	:    答题内容
     }
     */
    private String testid;
    private String questionid;
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }
}
