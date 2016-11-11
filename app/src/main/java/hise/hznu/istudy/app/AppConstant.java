package hise.hznu.istudy.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by PC on 2016/7/21.
 */
public class AppConstant {
    public static final int SERVER_PARAMS_ERROR = 11; //参数错误
    public static final int SERVER_ERROR_ERROR =12; //服务器内部错误
    public static final int SERVER_TOKEN_OOD = 13;// 服务

    public static final String FILE_STORED = Environment.getExternalStorageDirectory()+ File.separator+"istudy";
    public static final String SERVER_URL="http://dodo.hznu.edu.cn/api/"; //服务器地址

    public static final String LOGIN_ACTION = "login"; //登录
    public static final int POST_LOGIN_ACTION = 1;//登录ActionId
    public static final String GET_COURSE_ACTION = "coursequery" ; //获取首页课程
    public static final int POST_GET_COURSE_ACTION = 2; //获取课程的ActionId;
    public static final String GET_NOTICE_ACTION = "notifyquery" ;//获取公告信息
    public static final int POST_GET_NOTICE_ACTION = 3; //获取公告信息ID
    public static final String GET_NOTIFY_INFO_ACTION = "notifyinfo"; //获取单个公告信息
    public static final int POST_GET_NOTIFY_INFO = 4 ;//获取单个公告信息
    public static final String GET_COURSE_NOTICE_ACTION = "courseinfo"; //获取课程的公告信息
    public static final int POST_GET_CORSE_NOTICE_ACTION = 5 ;
    public static final String GET_STUDY_DATUM_ACTION = "courseresoure"; //获取学习资料
    public static final int POST_GET_STUDY_DATUM = 6;
    public static final String GET_EXAM_LIST_ACTION = "testquery"; //获取学生的考试信息
    public static final int POST_EXAM_LIST_ACTION = 7 ;
    public static final String GET_EMAIL_ACTION = "messagereceivequery"; //获取站内信
    public static final int POST_GET_EMAIL_ACTION = 9;
    public static final String GET_HOMEWORK_ACTION = "homeworkquery"; //查询课程作业
    public static final int POST_HOMEWORK_ACTION = 10;
    public static final String GET_COMMENT_EACH = "hupingquery" ;//查询互评任务
    public static final int POST_COMMENT_EACH = 11 ;
    public static final String GET_MY_EXPERIMENT = "exprementquery"; //查询实验
    public static final int POST_GET_MY_EXPERIMENT = 12 ;
    public static final String GET_MY_EXERCISE = "exercisequery"; //查询练习
    public static final int POST_GET_MY_EXERCISE = 13;
    public static final String GET_TEST_DETAIL = "testinfo"; //获取作业，试卷，练习详细试题
    public static final int POST_GET_TEST_DETAIL = 14;
    public static final String COMMIT_TEST_RESULE = "submitquestion" ;//提交练习数据
    public static final int POST_COMMIT_TEST_RESULT = 15;
    public static final String UPLOAD_FILE = "upfile"; //上传文件
    public static final int POST_UPLOAD_FILE = 16;
    public static final String AUTO_COMMIT_RESULT = "judgequestion";//自动阅卷
    public static final int POST_AUTO_COMMIT_RESULT = 17;
    public static final String GET_FORUM = "forumquery"; //查询论坛主题
    public static final int POST_GET_FORUM = 18;
    public static final String FORUM_POST = "forumpost" ;// 发帖子或者回复帖子
    public static final int POST_FORUM_POST = 19;
    public static final String FORUM_COMMENT = "forumcommentquery";//查询帖子回复
    public static final int POST_FORUM_COMMENT = 20;
    public static final String SAVE_PERSON_INFO = "saveprofile";//保存个人信息
    public static final int POST_SAVE_PERSON_INFO = 21;
    public static final String GET_USERINFO = "queryprofile";//查询个人信息
    public static final int POST_GET_USERINFO = 22;
    public static final String CHANGE_PASSWORD = "changepassword";//修啊密码
    public static final int POST_CHANGE_PASSWORD = 23;
    public static final String QUERY_EMAIL_SEND = "messagesendquery";// 发信查询
    public static final int POST_QUERY_EMAIL_SEND = 24;
    public static final String QUERY_COMMENT_LIST = "hupinginfo" ; // 查询互评列表
    public static final int POST_QUERY_COMMENT_LIST = 25;
    public static final String QUERY_USER_TESTER_COMMENT = "hupingusertest" ; //互评试卷查询同学试卷题目
    public static final int POST_QUERY_USER_TESTER_COMMENT = 26;
    public static final String QUERY_SUBMIT_HUPING = "submithuping" ; //提交互评数据
    public static final int POST_SUBMIT_HUPING = 27;
}
