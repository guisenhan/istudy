package hise.hznu.istudy.model.course;

import java.io.Serializable;

/**
 * Created by PC on 2016/9/26.
 */
public class ExerciseEntity implements Serializable {
    /**
     * “id”		:	,				Record Id
     “title”	:					作业标题
     “memo”   ：                  作业说明
     “teacher” ：                 教师姓名
     “datestart”	:				开始日期yyyyMMddHHmmss
     “dateend”	:				截止日期yyyyMMddHHmmss
     “score”     ： 				总分
     enableClientJudge			是否开启客户端阅卷
     keyVisible					阅卷时参考答案是否可见
     viewOneWithAnswerKey		查卷时参考答案是否可见
     */
    private String id;
    private String title;
    private String mome;
    private String teacher;
    private String datestart;
    private String dateend;
    private String score;
    private String enableClientJudge;
    private String keyVisible;
    private String viewOneWithAnswerKey;
}
