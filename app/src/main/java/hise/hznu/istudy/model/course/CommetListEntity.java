package hise.hznu.istudy.model.course;

import java.io.Serializable;

/**
 * Created by PC on 2016/11/10.
 */
public class CommetListEntity implements Serializable{
    /**
     * {"taskid":1321,"usertestid":124638,"title":"互评测试-文件","testername":"2015001003","score":"4","hupingtime":"20161102133338"}
     */
    private String taskid;
    private String usertestid;
    private String title;
    private String testername;
    private String score;
    private String hupingtime;

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getHupingtime() {
        return hupingtime;
    }

    public void setHupingtime(String hupingtime) {
        this.hupingtime = hupingtime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTestername() {
        return testername;
    }

    public void setTestername(String testername) {
        this.testername = testername;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsertestid() {
        return usertestid;
    }

    public void setUsertestid(String usertestid) {
        this.usertestid = usertestid;
    }
}
