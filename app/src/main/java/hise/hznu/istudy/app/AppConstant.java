package hise.hznu.istudy.app;

/**
 * Created by PC on 2016/7/21.
 */
public class AppConstant {
    public static final int SERVER_PARAMS_ERROR = 11; //参数错误
    public static final int SERVER_ERROR_ERROR =12; //服务器内部错误
    public static final int SERVER_TOKEN_OOD = 13;// 服务


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
}
