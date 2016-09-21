package hise.hznu.istudy.model.course;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC on 2016/9/21.
 */
public class CourseEntity implements Serializable{
    private String id;
    private String memo;
    private String pic;
    private List<Integer> picbg;
    private String pictit;
    private String teacher;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<Integer> getPicbg() {
        return picbg;
    }

    public void setPicbg(List<Integer> picbg) {
        this.picbg = picbg;
    }

    public String getPictit() {
        return pictit;
    }

    public void setPictit(String pictit) {
        this.pictit = pictit;
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
}
