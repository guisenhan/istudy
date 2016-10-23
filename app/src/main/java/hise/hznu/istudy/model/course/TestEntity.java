package hise.hznu.istudy.model.course;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC on 2016/10/18.
 */
public class TestEntity implements Serializable{
    /**
     * "id": 605,
     "title": "填空题",
     "type": "FILL_BLANK",
     "questions": [
     */
    private String id;
    private String title;
    private String type;
    private List<TestPaperEntity> questions;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TestPaperEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestPaperEntity> questions) {
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
