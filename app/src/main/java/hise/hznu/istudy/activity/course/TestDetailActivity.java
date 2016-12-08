package hise.hznu.istudy.activity.course;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.loopj.android.http.Base64;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import jp.wasabeef.richeditor.RichEditor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.adapter.ComplexQuestionAdapter;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.AsyHttpClient;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.UpLoadFileEntity;
import hise.hznu.istudy.model.course.AnswerEntity;
import hise.hznu.istudy.model.course.JudgeTestEntity;
import hise.hznu.istudy.model.course.TestEntity;
import hise.hznu.istudy.model.course.TestPaperEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.util.MiscUtils;
import hise.hznu.istudy.util.UIUtils;
import hise.hznu.istudy.util.clip.MCrop;
import hise.hznu.istudy.widget.MyListView;
import me.nereo.multi_image_selector.MultiImageSelector;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class TestDetailActivity extends BaseActivity
        implements EasyPermissions.PermissionCallbacks, View.OnClickListener {
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 102;
    public static final int REQUEST_IMAGE = 103;

    // topbar
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    //顶部用于展示题目类型和题目数量
    @BindView(R.id.tv_test_name)
    TextView tvTestName;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.rl_test_title)
    RelativeLayout rlTestTitle;
    //用于展示题目类型的，
    @BindView(R.id.tv_test_type)
    TextView tvTestType;
    @BindView(R.id.tv_test_type_info)
    TextView tvTestTypeInfo;
    @BindView(R.id.ll_test_type)
    LinearLayout llTestType;
    //这是判断题
    @BindView(R.id.tv_test_content)
    TextView tvTestContent;
    @BindView(R.id.rb_choose_a)
    RadioButton rbChooseA;
    @BindView(R.id.rb_choose_b)
    RadioButton rbChooseB;
    @BindView(R.id.rb_choose_c)
    RadioButton rbChooseC;
    @BindView(R.id.rb_choose_d)
    RadioButton rbChooseD;
    @BindView(R.id.rb_choose_e)
    RadioButton rbChooseE;
    @BindView(R.id.rg_answer)
    RadioGroup rgAnswer;
    @BindView(R.id.tv_choose_answer)
    TextView tvChooseAnswer;
    @BindView(R.id.tv_answer_knowledge)
    TextView tvAnswerKnowledge;
    @BindView(R.id.tv_choose_score)
    TextView tvChooseScore;
    @BindView(R.id.tv_choose_stander_answer)
    TextView tvChooseStanderAnswer;
    @BindView(R.id.sl_test_choose)
    ScrollView slTestChoose;

    //这是填空题的
    @BindView(R.id.tv_fill_blank_title)
    TextView tvFillBlankTitle;
    @BindView(R.id.tv_fill_blank_answer_a)
    EditText tvFillBlankAnswerA;
    @BindView(R.id.tv_fill_blank_answer_b)
    EditText tvFillBlankAnswerB;
    @BindView(R.id.tv_fill_blank_answer_c)
    EditText tvFillBlankAnswerC;
    @BindView(R.id.tv_fill_blank_answer_d)
    EditText tvFillBlankAnswerD;
    @BindView(R.id.tv_fill_blank_answer)
    TextView tvFillBlankAnswer;
    @BindView(R.id.tv_fill_blank_knowledge)
    TextView tvFillBlankKnowledge;
    @BindView(R.id.tv_fill_blank_score)
    TextView tvFillBlankScore;
    @BindView(R.id.tv_fill_blank_stander_answer)
    TextView tvFillBlankStanderAnswer;
    @BindView(R.id.sl_fill_blank)
    ScrollView slFillBlank;


    //这是问答题。计算题，绘图提
    @BindView(R.id.tv_question_answer_title)
    TextView tvQuestionAnswerTitle;
    //这是富文本编辑的
    @BindView(R.id.action_undo)
    ImageButton actionUndo;
    @BindView(R.id.action_redo)
    ImageButton actionRedo;
    @BindView(R.id.action_insert_image)
    ImageButton actionInsertImage;
    @BindView(R.id.action_insert_link)
    ImageButton actionInsertLink;
    @BindView(R.id.re_editor)
    RichEditor reEditor;
    @BindView(R.id.tv_preview)
    TextView tvPreview;
    @BindView(R.id.sl_question_answer)
    ScrollView slQuestionAnswer;

    //最底部用于控制题目的
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.iv_left_arrow)
    ImageView ivLeftArrow;
    @BindView(R.id.iv_save)
    ImageView ivSave;
    @BindView(R.id.iv_right_arrow)
    ImageView ivRightArrow;
    @BindView(R.id.ll_choice_answer)
    LinearLayout llChoiceAnswer;
    @BindView(R.id.ll_fill_blank_answer)
    LinearLayout llFillBlankAnswer;
    @BindView(R.id.ll_fill_blanks)
    LinearLayout llFillBlanks;
    @BindView(R.id.tv_auto_commit)
    TextView tvAutoCommit;


    @BindView(R.id.tv_complex_answer)
    TextView tvComplexAnswer;
    @BindView(R.id.tv_complex_knowledge)
    TextView tvComplexKnowledge;
    @BindView(R.id.tv_complex_score)
    TextView tvComplexScore;
    @BindView(R.id.tv_complex_stander_answer)
    TextView tvComplexStanderAnswer;
    @BindView(R.id.ll_complex_answer)
    LinearLayout llComplexAnswer;
    @BindView(R.id.tv_complex_content)
    TextView tvComplexContent;
    @BindView(R.id.lv_complex)
    MyListView lvComplex;
    @BindView(R.id.sl_complex)
    ScrollView slComplex;
    //多选题
    @BindView(R.id.tv_multi_choice_content)
    TextView tvMultiChoiceContent;
    @BindView(R.id.rb_multi_choice_a)
    CheckBox rbMultiChoiceA;
    @BindView(R.id.rb_multi_choice_b)
    CheckBox rbMultiChoiceB;
    @BindView(R.id.rb_multi_choice_c)
    CheckBox rbMultiChoiceC;
    @BindView(R.id.rb_multi_choice_d)
    CheckBox rbMultiChoiceD;
    @BindView(R.id.rb_multi_choice_e)
    CheckBox rbMultiChoiceE;
    @BindView(R.id.tv_multi_choice_answer)
    TextView tvMultiChoiceAnswer;
    @BindView(R.id.tv_multi_choice_answer_knowledge)
    TextView tvMultiChoiceAnswerKnowledge;
    @BindView(R.id.tv_multi_choice_score)
    TextView tvMultiChoiceScore;
    @BindView(R.id.tv_multi_choice_stander_answer)
    TextView tvMultiChoiceStanderAnswer;
    @BindView(R.id.ll_multi_choice_answer)
    LinearLayout llMultiChoiceAnswer;
    @BindView(R.id.sl_multi_choice)
    ScrollView slMultiChoice;
    @BindView(R.id.acl_load_view)
    AnimatedCircleLoadingView aclLoadView;
    @BindView(R.id.rl_animation)
    RelativeLayout rlAnimation;
    private List<TestEntity> _dataList = new ArrayList<TestEntity>();
    private String testId;
    private int bProblem = 0;
    private List<AnswerEntity> _answerList = new ArrayList<AnswerEntity>();
    private ArrayList<String> selectPath;
    private List<TestPaperEntity> _questionList = new ArrayList<TestPaperEntity>(); //重新处理数据
    private JSONObject submitResult; // 提交的答案
    ComplexQuestionAdapter comlex;
    /**
     * enableClientJudge			是否开启客户端阅卷
     * keyVisible					阅卷时参考答案是否可见
     * viewOneWithAnswerKey		查卷时参考答案是否可见
     */
    private boolean enableClientJudge;
    private boolean keyVisible;
    private boolean viewOneWithAnswerKey;
    private int paperModel = 1; //1表示查卷模式，2表示答题模式，3 表示阅卷模式
    private List<String> multiAnswer = new ArrayList<String>();
    private JudgeTestEntity judge = new JudgeTestEntity(); // 阅卷的结果
    private String title = "答题";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        struct();
    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        testId = extras.getString("testId");
        enableClientJudge = extras.getBoolean("enableClientJudge");
        keyVisible = extras.getBoolean("keyVisible");
        viewOneWithAnswerKey = extras.getBoolean("viewOneWithAnswerKey");
        paperModel = extras.getInt("paperModel", 2); // 默认为答题模式
        title = extras.getString("title");
        tvTitle.setText(title);
    }

    @Override
    protected void initData() {
        super.initData();

        submitResult = new JSONObject();
        selectPath = new ArrayList<String>();
        JSONObject params = new JSONObject();
        params.put("testid", testId);
        RequestManager.getmInstance()
                .apiPostData(AppConstant.GET_TEST_DETAIL, params, this, AppConstant.POST_GET_TEST_DETAIL);
        aclLoadView.startDeterminate();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_test_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        slMultiChoice.setVisibility(View.GONE);
        rlTestTitle.setVisibility(View.GONE);
        slFillBlank.setVisibility(View.GONE);
        slTestChoose.setVisibility(View.GONE);
        slTestChoose.setVisibility(View.GONE);
        llTestType.setVisibility(View.GONE);
        aclLoadView.setAnimationListener(new AnimatedCircleLoadingView.AnimationListener() {

            @Override
            public void onAnimationEnd(boolean success) {
                rlAnimation.setVisibility(View.GONE);
                initTestView();
            }
        });
        //判断是答题模式还是阅卷模式
        switch (paperModel) {
            case 1: //查卷模式
                llChoiceAnswer.setVisibility(View.VISIBLE);
                llFillBlankAnswer.setVisibility(View.VISIBLE);
                tvPreview.setVisibility(View.VISIBLE);
                ivSave.setVisibility(View.GONE);
                rbChooseA.setEnabled(false);
                rbChooseD.setEnabled(false);
                rbChooseC.setEnabled(false);
                rbChooseB.setEnabled(false);
                rbMultiChoiceA.setEnabled(false);
                rbMultiChoiceB.setEnabled(false);
                rbMultiChoiceC.setEnabled(false);
                rbMultiChoiceD.setEnabled(false);
                rbMultiChoiceE.setEnabled(false);
                tvAutoCommit.setVisibility(View.GONE);
                llFillBlanks.setVisibility(View.GONE);
                if (viewOneWithAnswerKey) { //查卷时参考答案是否可见
                    tvChooseStanderAnswer.setVisibility(View.VISIBLE);
                    tvFillBlankStanderAnswer.setVisibility(View.VISIBLE);
                    tvPreview.setVisibility(View.VISIBLE);
                    tvMultiChoiceStanderAnswer.setVisibility(View.VISIBLE);
                    tvComplexStanderAnswer.setVisibility(View.VISIBLE);
                } else {
                    tvChooseStanderAnswer.setVisibility(View.GONE);
                    tvFillBlankStanderAnswer.setVisibility(View.GONE);
                    tvPreview.setVisibility(View.GONE);
                    tvMultiChoiceStanderAnswer.setVisibility(View.GONE);
                    tvComplexStanderAnswer.setVisibility(View.GONE);
                }
                break;
            case 2: //答题模式
                llFillBlankAnswer.setVisibility(View.GONE);
                llChoiceAnswer.setVisibility(View.GONE);
                llMultiChoiceAnswer.setVisibility(View.GONE);
                tvPreview.setVisibility(View.GONE);
                llComplexAnswer.setVisibility(View.GONE);

                ivSave.setVisibility(View.VISIBLE);
                if (enableClientJudge) {
                    tvAutoCommit.setVisibility(View.VISIBLE);
                } else {
                    tvAutoCommit.setVisibility(View.GONE);
                }
                break;
            case 3: //阅卷模式
                ivSave.setVisibility(View.GONE);
                if (keyVisible) {
                    tvChooseStanderAnswer.setVisibility(View.VISIBLE);
                    tvFillBlankStanderAnswer.setVisibility(View.VISIBLE);
                    tvPreview.setVisibility(View.VISIBLE);
                    tvMultiChoiceStanderAnswer.setVisibility(View.VISIBLE);
                    tvComplexStanderAnswer.setVisibility(View.VISIBLE);
                } else {
                    tvPreview.setVisibility(View.GONE);
                    tvFillBlankStanderAnswer.setVisibility(View.GONE);
                    tvChooseStanderAnswer.setVisibility(View.GONE);
                    tvComplexStanderAnswer.setVisibility(View.GONE);
                    tvMultiChoiceStanderAnswer.setVisibility(View.GONE);
                }
                break;
        }

        rgAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == rbChooseA.getId() && rbChooseA.isPressed()) {
                    AnswerEntity answerEntity = new AnswerEntity();
                    int temp = -1;
                    for (int j = 0; j < _answerList.size(); j++) {
                        if (_questionList.get(bProblem).getId().equals(_answerList.get(j).getQuestionid())) {
                           // answerEntity = _answerList.get(j);
                            temp = j;
                            break;
                        }
                    }
                    answerEntity.setTestid(testId);
                    answerEntity.setQuestionid(_questionList.get(bProblem).getId());
                    answerEntity.setAnswer("A");
                    Log.e("temp",""+temp);
                    if(temp != -1){
                        _answerList.set(temp,answerEntity);
                    }else{
                        _answerList.add(answerEntity);
                    }

                } else if (radioGroup.getCheckedRadioButtonId() == rbChooseB.getId() && rbChooseB.isPressed()) {
                    AnswerEntity answerEntity = new AnswerEntity();
                    int temp = -1 ;
                    for (int j = 0; j < _answerList.size(); j++) {
                        if (_questionList.get(bProblem).getId().equals(_answerList.get(j).getQuestionid())) {
                            answerEntity = _answerList.get(j);
                            temp = j;
                            break;
                        }
                    }
                    if (MiscUtils.isEmpty(answerEntity.getQuestionid())) {
                        answerEntity.setTestid(testId);
                        answerEntity.setQuestionid(_questionList.get(bProblem).getId());
                    }
                    answerEntity.setAnswer("B");
                    if(temp != -1){
                        _answerList.set(temp,answerEntity);
                    }else{
                        _answerList.add(answerEntity);
                    }
                } else if (radioGroup.getCheckedRadioButtonId() == rbChooseC.getId() && rbChooseC.isPressed()) {
                    AnswerEntity answerEntity = new AnswerEntity();
                    int temp = -1;
                    for (int j = 0; j < _answerList.size(); j++) {
                        if (_questionList.get(bProblem).getId().equals(_answerList.get(j).getQuestionid())) {
                            answerEntity = _answerList.get(j);
                            temp = j;
                            break;
                        }
                    }
                    if (MiscUtils.isEmpty(answerEntity.getQuestionid())) {
                        answerEntity.setTestid(testId);
                        answerEntity.setQuestionid(_questionList.get(bProblem).getId());
                    }
                    answerEntity.setAnswer("C");
                    if(temp != -1){
                        _answerList.set(temp,answerEntity);
                    }else{
                        _answerList.add(answerEntity);
                    }
                } else if (radioGroup.getCheckedRadioButtonId() == rbChooseD.getId() && rbChooseD.isPressed()) {
                    int temp = -1;
                    AnswerEntity answerEntity = new AnswerEntity();
                    for (int j = 0; j < _answerList.size(); j++) {
                        if (_questionList.get(bProblem).getId().equals(_answerList.get(j).getQuestionid())) {
                            answerEntity = _answerList.get(j);
                            temp = j;
                            break;
                        }
                    }
                    if (MiscUtils.isEmpty(answerEntity.getQuestionid())) {
                        answerEntity.setTestid(testId);
                        answerEntity.setQuestionid(_questionList.get(bProblem).getId());
                    }
                    answerEntity.setAnswer("D");
                    if(temp != -1){
                        _answerList.set(temp,answerEntity);
                    }else{
                        _answerList.add(answerEntity);
                    }
                }
            }
        });

        rbMultiChoiceA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    multiAnswer.add("A");
                }else{
                    if(multiAnswer.contains("A")){
                        multiAnswer.remove("A");
                    }
                }
            }
        });
        rbMultiChoiceB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    multiAnswer.add("B");
                }else{
                    if(multiAnswer.contains("B")){
                        multiAnswer.remove("B");
                    }
                }
            }
        });
        rbMultiChoiceC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    multiAnswer.add("C");
                }else{
                    if(multiAnswer.contains("C")){
                        multiAnswer.remove("C");
                    }
                }
            }
        });
        rbMultiChoiceD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    multiAnswer.add("D");
                }else{
                    if(multiAnswer.contains("D")){
                        multiAnswer.remove("D");
                    }
                }
            }
        });
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        switch (actionId) {
            case AppConstant.POST_GET_TEST_DETAIL:
                _dataList = response.getListData(TestEntity.class);
                if (_dataList.size() > 0) {
                    restData();
                    aclLoadView.stopOk();
                }
                break;
            case AppConstant.POST_COMMIT_TEST_RESULT:
                Log.e("result", "" + JSONObject.toJSONString(response));
                if (response.getRetcode() == 0) {
                    MiscUtils.showMessageToast(response.getMessage());
                } else {
                    MiscUtils.showMessageToast(response.getMessage());
                }
                break;
            case AppConstant.POST_AUTO_COMMIT_RESULT:
                //阅卷的地方 代码优化空间很大，XML文件挖得坑 只能这么写了 等改版的时候在优化
                if (response.getRetcode() == 0) {
                    Log.e("response",JSONObject.toJSONString(response));
                    judge = response.getInfo(JudgeTestEntity.class);
                    if(_questionList.get(bProblem).getQuestionType().equals("SINGLE_CHIOCE")||_questionList.get(bProblem).getQuestionType().equals("JUDGE")){
                        llChoiceAnswer.setVisibility(View.VISIBLE);
                        tvChooseAnswer.setText("答案："+judge.getPoints().get(0).getComment());
                        tvChooseScore.setText("得分："+judge.getPoints().get(0).getGotscore()+"/"+judge.getPoints().get(0).getFullscore());
                        tvAnswerKnowledge.setText("知识点："+judge.getPoints().get(0).getKnowledge());
                        tvChooseStanderAnswer.setText("标准答案："+judge.getPoints().get(0).getKey());

                    }else if(_questionList.get(bProblem).getQuestionType().equals("FILL_BLANK")||_questionList.get(bProblem).getQuestionType().equals("PROGRAM_FILL_BLANK")){
                        llFillBlankAnswer.setVisibility(View.VISIBLE);
                        StringBuilder score = new StringBuilder();
                        StringBuilder answer = new StringBuilder();
                        StringBuilder knowledge = new StringBuilder();
                        StringBuilder stander = new StringBuilder();
                        for(JudgeTestEntity.Points points:judge.getPoints()){
                            score.append(points.getGotscore()+"/"+points.getFullscore()+"  ");
                            answer.append(points.getComment()+"  ");
                            knowledge.append(points.getKnowledge()+" ");
                            stander.append(points.getKey());
                        }
                        tvFillBlankAnswer.setText("答案"+answer.toString());
                        tvFillBlankKnowledge.setText("知识点："+knowledge.toString());
                        tvFillBlankScore.setText("得分："+score.toString());
                        tvFillBlankStanderAnswer.setText("标准答案："+stander.toString());
                    }else if(_questionList.get(bProblem).getQuestionType().equals("COMPLEX")){
                        llComplexAnswer.setVisibility(View.VISIBLE);
                        StringBuilder score = new StringBuilder();
                        StringBuilder answer = new StringBuilder();
                        StringBuilder knowledge = new StringBuilder();
                        StringBuilder stander = new StringBuilder();
                        for(JudgeTestEntity.Points points:judge.getPoints()){
                            score.append(points.getGotscore()+"/"+points.getFullscore()+"  ");
                            answer.append(points.getComment()+"  ");
                            knowledge.append(points.getKnowledge()+" ");
                            stander.append(points.getKey());
                        }
                        tvComplexAnswer.setText("答案："+answer.toString());
                        tvComplexKnowledge.setText("知识点："+knowledge.toString());
                        tvComplexScore.setText("得分："+score.toString());
                        tvComplexStanderAnswer.setText("标准答案："+stander.toString());
                    }else if(_questionList.get(bProblem).getQuestionType().equals("MULIT_CHIOCE")){
                        llMultiChoiceAnswer.setVisibility(View.VISIBLE);
                        tvMultiChoiceStanderAnswer.setText("标准答案："+judge.getPoints().get(0).getKey());
                        tvMultiChoiceAnswerKnowledge.setText("知识点："+judge.getPoints().get(0).getKnowledge());
                        tvMultiChoiceScore.setText("得分："+judge.getPoints().get(0).getGotscore()+"/"+judge.getPoints().get(0).getFullscore());
                        tvMultiChoiceAnswer.setText("答案："+judge.getPoints().get(0).getComment());
                    }else{
                        tvPreview.setVisibility(View.VISIBLE);
                        tvPreview.setText("答案："+Html.fromHtml(judge.getPoints().get(0).getKey()));
                    }
                } else {
                    MiscUtils.showMessageToast(response.getMessage());
                }
                break;
        }
    }

    private void restData() {
        for (int i = 0; i < _dataList.size(); i++) {
            TestPaperEntity questionType = new TestPaperEntity();
            questionType.setDesc(true);
            questionType.setQuestionTitle(_dataList.get(i).getTitle());
            questionType.setQuestionType(_dataList.get(i).getType());
            questionType.setTotalNumber("" + _dataList.get(i).getQuestions().size());
            questionType.setNumber("-1");
            questionType.setQuestionDesc(_dataList.get(i).getDesc());
            _questionList.add(questionType);
            for (int j = 0; j < _dataList.get(i).getQuestions().size(); j++) {
                TestPaperEntity question = _dataList.get(i).getQuestions().get(j);
                question.setDesc(false);
                question.setQuestionTitle(_dataList.get(i).getTitle());
                question.setQuestionType(_dataList.get(i).getType());
                question.setTotalNumber("" + _dataList.get(i).getQuestions().size());
                question.setNumber("" + (j + 1));
                if(MiscUtils.isNotEmpty(question.getAnswer())){
                    AnswerEntity answerEntity = new AnswerEntity();
                    answerEntity.setAnswer(question.getAnswer());
                    answerEntity.setQuestionid(question.getId());
                    answerEntity.setTestid(testId);
                    _answerList.add(answerEntity);
                }
                _questionList.add(question);
            }
        }
        Log.e("answer",JSONObject.toJSONString(_answerList));
    }

    private void initTestView() {
        llTestType.setVisibility(View.VISIBLE);
        rlTestTitle.setVisibility(View.GONE);
        slQuestionAnswer.setVisibility(View.GONE);
        slTestChoose.setVisibility(View.GONE);
        slFillBlank.setVisibility(View.GONE);
        slComplex.setVisibility(View.GONE);
        slMultiChoice.setVisibility(View.GONE);
        tvTestType.setText(_dataList.get(bProblem).getTitle());
        tvTestTypeInfo.setText(_dataList.get(bProblem).getDesc());
    }

    /**
     * 显示选择题
     */
    private void setChooseTest(TestPaperEntity test) {
        slTestChoose.setVisibility(View.VISIBLE);
        rlTestTitle.setVisibility(View.VISIBLE);
        llTestType.setVisibility(View.GONE);
        slFillBlank.setVisibility(View.GONE);
        slQuestionAnswer.setVisibility(View.GONE);
        slMultiChoice.setVisibility(View.GONE);
        slComplex.setVisibility(View.GONE);
        if (test.isDesc()) {
            llTestType.setVisibility(View.VISIBLE);
            rlTestTitle.setVisibility(View.GONE);
            slQuestionAnswer.setVisibility(View.GONE);
            slTestChoose.setVisibility(View.GONE);
            slFillBlank.setVisibility(View.GONE);
            slComplex.setVisibility(View.GONE);
            tvTestType.setText(test.getQuestionTitle());
            tvTestTypeInfo.setText(test.getQuestionDesc());
            return;
        }
        tvNumber.setText(test.getNumber() + "/" + test.getTotalNumber());
        tvTestContent.setText(Html.fromHtml(test.getContent()));
        if (MiscUtils.isNotEmpty(test.getOptiona())) {
            rbChooseA.setVisibility(View.VISIBLE);
            rbChooseA.setText(Html.fromHtml(test.getOptiona()));
        }else{
            rbChooseA.setVisibility(View.GONE);
        }
        if (MiscUtils.isNotEmpty(test.getOptionb())) {
            rbChooseB.setVisibility(View.VISIBLE);
            rbChooseB.setText(Html.fromHtml(test.getOptionb()));
        }else{
            rbChooseB.setVisibility(View.GONE);
        }
        if (MiscUtils.isNotEmpty(test.getOptionc())) {
            rbChooseC.setVisibility(View.VISIBLE);
            rbChooseC.setText(Html.fromHtml(test.getOptionc()));
        }else{
            rbChooseC.setVisibility(View.GONE);
        }
        if (MiscUtils.isNotEmpty(test.getOptiond())) {
            rbChooseD.setVisibility(View.VISIBLE);
            rbChooseD.setText(Html.fromHtml(test.getOptiond()));
        }else{
            rbChooseD.setVisibility(View.GONE);
        }
        if (MiscUtils.isNotEmpty(test.getAnswer())) {
            tvChooseAnswer.setText("答案：" + test.getAnswer());
        }
        if (MiscUtils.isNotEmpty(test.getKnowledge())) { tvAnswerKnowledge.setText("知识点：" + test.getKnowledge()); }
        if (MiscUtils.isNotEmpty(test.getScore())) {
            tvChooseScore.setText("得分：" + test.getScore() + "/" + test.getTotalscore());
        }
        if (MiscUtils.isNotEmpty(test.getStrandanswer())) {
            tvChooseStanderAnswer.setText("标准答案：" + test.getStrandanswer());
        } else { tvChooseStanderAnswer.setText("标准答案未开放"); }
        tvTestName.setText(test.getQuestionTitle());
        rgAnswer.clearCheck();

        for (int i = 0; i < _answerList.size(); i++) {
            if (MiscUtils.isNotEmpty(_answerList.get(i).getQuestionid())&& MiscUtils.isNotEmpty(_answerList.get(i)
                    .getAnswer())) {
                if (_answerList.get(i).getQuestionid().equals(test.getId())) {
                    if (_answerList.get(i).getAnswer().equals("A")) {
                        rgAnswer.check(rbChooseA.getId());
                    } else if (_answerList.get(i).getAnswer().equals("B")) {
                        rgAnswer.check(rbChooseB.getId());
                    } else if (_answerList.get(i).getAnswer().equals("C")) {
                        rgAnswer.check(rbChooseC.getId());
                    } else if (_answerList.get(i).getAnswer().equals("D")) {
                        // rbChooseD.setChecked(true);
                        rgAnswer.check(rbChooseD.getId());
                    }
                    break;
                }
            }
        }
    }

    /**
     * 显示填空题
     */
    private void setFillBlank(TestPaperEntity test) {
        slTestChoose.setVisibility(View.GONE);
        rlTestTitle.setVisibility(View.VISIBLE);
        llTestType.setVisibility(View.GONE);
        slComplex.setVisibility(View.GONE);
        slFillBlank.setVisibility(View.VISIBLE);
        slQuestionAnswer.setVisibility(View.GONE);
        slMultiChoice.setVisibility(View.GONE);
        slComplex.setVisibility(View.GONE);
        if (test.isDesc()) {
            llTestType.setVisibility(View.VISIBLE);
            rlTestTitle.setVisibility(View.GONE);
            slQuestionAnswer.setVisibility(View.GONE);
            slTestChoose.setVisibility(View.GONE);
            slFillBlank.setVisibility(View.GONE);
            slComplex.setVisibility(View.GONE);
            tvTestType.setText(test.getQuestionTitle());
            tvTestTypeInfo.setText(test.getQuestionDesc());
            return;
        }
        tvFillBlankAnswerA.setText("");
        tvFillBlankAnswerB.setText("");
        tvFillBlankAnswerC.setText("");
        tvFillBlankAnswerD.setText("");
        String fb  = "";
        for(AnswerEntity an :_answerList){
            if(an.getQuestionid().equals(test.getId())){
                fb = an.getAnswer();
                break;
            }
        }
        Log.e("fb",fb);

        switch (getBlankNumber(test.getStrandanswer())) {
            case 1:
                if(fb.split("&&&").length >0 && MiscUtils.isNotEmpty(fb)){
                    if(MiscUtils.isNotEmpty(fb.split("&&&")[0])){
                        tvFillBlankAnswerA.setText(fb.split("&&&")[0]);
                    }
                }
                tvFillBlankAnswerA.setVisibility(View.VISIBLE);
                tvFillBlankAnswerB.setVisibility(View.GONE);
                tvFillBlankAnswerC.setVisibility(View.GONE);
                tvFillBlankAnswerD.setVisibility(View.GONE);
                break;
            case 2:
                if(MiscUtils.isNotEmpty(fb)){
                    if(fb.split("&&&").length >0 && MiscUtils.isNotEmpty(fb.split("&&&")[0])){
                        tvFillBlankAnswerA.setText(fb.split("&&&")[0]);
                    }
                    if(fb.split("&&&").length>1 && MiscUtils.isNotEmpty(fb.split("&&&")[1])){
                        tvFillBlankAnswerB.setText(fb.split("&&&")[1]);
                    }
                }
                tvFillBlankAnswerA.setVisibility(View.VISIBLE);
                tvFillBlankAnswerB.setVisibility(View.VISIBLE);
                tvFillBlankAnswerC.setVisibility(View.GONE);
                tvFillBlankAnswerD.setVisibility(View.GONE);
                break;
            case 3:
                if(MiscUtils.isNotEmpty(fb)){
                    if(fb.split("&&&").length >0 && MiscUtils.isNotEmpty(fb.split("&&&")[0])){
                        tvFillBlankAnswerA.setText(fb.split("&&&")[0]);
                    }
                    if(fb.split("&&&").length>1 && MiscUtils.isNotEmpty(fb.split("&&&")[1])){
                        tvFillBlankAnswerB.setText(fb.split("&&&")[1]);
                    }
                    if(fb.split("&&&").length>2 && MiscUtils.isNotEmpty(fb.split("&&&")[2]) ){
                        tvFillBlankAnswerC.setText(fb.split("&&&")[2]);
                    }
                }
                tvFillBlankAnswerA.setVisibility(View.VISIBLE);
                tvFillBlankAnswerB.setVisibility(View.VISIBLE);
                tvFillBlankAnswerC.setVisibility(View.VISIBLE);
                tvFillBlankAnswerD.setVisibility(View.GONE);
                break;
            case 4:
                if(MiscUtils.isNotEmpty(fb)){
                    if(fb.split("&&&").length >0 && MiscUtils.isNotEmpty(fb.split("&&&")[0])){
                        tvFillBlankAnswerA.setText(fb.split("&&&")[0]);
                    }
                    if(fb.split("&&&").length>1 &&MiscUtils.isNotEmpty(fb.split("&&&")[1])&& fb.split("&&&").length>1){
                        tvFillBlankAnswerB.setText(fb.split("&&&")[1]);
                    }
                    if(fb.split("&&&").length>2 &&MiscUtils.isNotEmpty(fb.split("&&&")[2])){
                        tvFillBlankAnswerC.setText(fb.split("&&&")[2]);
                    }
                    if(fb.split("&&&").length>3 && MiscUtils.isNotEmpty(fb.split("&&&")[3])&&fb.split("&&&").length>3){
                        tvFillBlankAnswerD.setText(fb.split("&&&")[3]);
                    }
                }
                tvFillBlankAnswerA.setVisibility(View.VISIBLE);
                tvFillBlankAnswerB.setVisibility(View.VISIBLE);
                tvFillBlankAnswerC.setVisibility(View.VISIBLE);
                tvFillBlankAnswerD.setVisibility(View.VISIBLE);
                break;
        }

        if (MiscUtils.isNotEmpty(test.getQuestionTitle())) { tvTestName.setText(test.getQuestionTitle()); }
        tvNumber.setText(test.getNumber() + "/" + test.getTotalNumber());
        tvFillBlankTitle.setText(Html.fromHtml(test.getContent(), imgGetter, null));
        if (MiscUtils.isNotEmpty(test.getAnswer())) {
            tvFillBlankAnswer.setText("答案:" + Html.fromHtml(test.getAnswer()));
        }
        if (MiscUtils.isNotEmpty(test.getKnowledge())) {
            tvFillBlankKnowledge.setText("知识点：" + Html.fromHtml(test.getKnowledge()));
        }
        tvFillBlankScore.setText("得分：" + test.getScore() + "/" + test.getTotalscore());
        if (MiscUtils.isNotEmpty(test.getStrandanswer())) {
            tvFillBlankStanderAnswer.setText("标准答案：" + Html.fromHtml(test.getStrandanswer()));
        }
    }
    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            URL url;
            try {
                url = new URL(source);
                drawable =new BitmapDrawable(ImageLoaderUtils.getImageLoader().loadImageSync(url.toString())); // 获取网路图片
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            drawable.setBounds(0, 0, MiscUtils.getScreenWidth(),
                    (drawable.getIntrinsicHeight() * (MiscUtils.getScreenWidth() - MiscUtils.dpToPx(20,getResources()))) / drawable
                            .getIntrinsicWidth
                            ());
            return drawable;
        }
    };

    /**
     * 显示问答题
     */
    private void setQA(TestPaperEntity test) {
        slTestChoose.setVisibility(View.GONE);
        rlTestTitle.setVisibility(View.VISIBLE);
        llTestType.setVisibility(View.GONE);
        slFillBlank.setVisibility(View.GONE);
        slQuestionAnswer.setVisibility(View.VISIBLE);
        slComplex.setVisibility(View.GONE);
        slMultiChoice.setVisibility(View.GONE);

        if (test.isDesc()) {
            llTestType.setVisibility(View.VISIBLE);
            rlTestTitle.setVisibility(View.GONE);
            slQuestionAnswer.setVisibility(View.GONE);
            slTestChoose.setVisibility(View.GONE);
            slFillBlank.setVisibility(View.GONE);
            slComplex.setVisibility(View.GONE);
            tvTestType.setText(test.getQuestionTitle());
            tvTestTypeInfo.setText(test.getQuestionDesc());
            return;
        }
        reEditor.setHtml("");
        for(AnswerEntity an :_answerList){
            if(an.getQuestionid().equals(test.getId())){
                reEditor.setHtml(an.getAnswer());
                break;
            }
        }
        tvTestName.setText(test.getQuestionTitle());
        tvNumber.setText(test.getNumber() + "/" + test.getTotalNumber());
        tvQuestionAnswerTitle.setText(Html.fromHtml(test.getContent(), imgGetter, null));
        tvPreview.setText("标准答案：" + Html.fromHtml(test.getStrandanswer()));
    }

    /**
     * 阅读题
     * @param test
     */
    private void setComplex(TestPaperEntity test) {
        slTestChoose.setVisibility(View.GONE);
        rlTestTitle.setVisibility(View.VISIBLE);
        llTestType.setVisibility(View.GONE);
        slFillBlank.setVisibility(View.GONE);
        slQuestionAnswer.setVisibility(View.GONE);
        slComplex.setVisibility(View.VISIBLE);
        slMultiChoice.setVisibility(View.GONE);
        if (test.isDesc()) {
            llTestType.setVisibility(View.VISIBLE);
            rlTestTitle.setVisibility(View.GONE);
            slQuestionAnswer.setVisibility(View.GONE);
            slTestChoose.setVisibility(View.GONE);
            slFillBlank.setVisibility(View.GONE);
            slComplex.setVisibility(View.GONE);
            tvTestType.setText(test.getQuestionTitle());
            tvTestTypeInfo.setText(test.getQuestionDesc());
            return;
        }
        String ans = "";
        for(AnswerEntity answer:_answerList){
            if(answer.getQuestionid().equals(test.getId())){
                ans = answer.getAnswer();
                break;
            }
        }
        comlex = new ComplexQuestionAdapter(this, imgGetter, this,ans);
        lvComplex.setAdapter(comlex);
        comlex.UpdateView(test.getSubquestions());
        tvTestName.setText(test.getQuestionTitle());
        tvNumber.setText(test.getNumber() + "/" + test.getTotalNumber());
        tvComplexContent.setText(Html.fromHtml(test.getContent(), imgGetter, null));

    }

    private void setMulti(TestPaperEntity test){
        slTestChoose.setVisibility(View.GONE);
        rlTestTitle.setVisibility(View.VISIBLE);
        llTestType.setVisibility(View.GONE);
        slFillBlank.setVisibility(View.GONE);
        slQuestionAnswer.setVisibility(View.GONE);
        slMultiChoice.setVisibility(View.VISIBLE);
        slComplex.setVisibility(View.GONE);

        if (test.isDesc()) {
            llTestType.setVisibility(View.VISIBLE);
            rlTestTitle.setVisibility(View.GONE);
            slQuestionAnswer.setVisibility(View.GONE);
            slTestChoose.setVisibility(View.GONE);
            slFillBlank.setVisibility(View.GONE);
            slComplex.setVisibility(View.GONE);
            tvTestType.setText(test.getQuestionTitle());
            tvTestTypeInfo.setText(test.getQuestionDesc());
            return;
        }
        rbMultiChoiceA.setChecked(false);
        rbMultiChoiceB.setChecked(false);
        rbMultiChoiceC.setChecked(false);
        rbMultiChoiceD.setChecked(false);
        tvNumber.setText(test.getNumber() + "/" + test.getTotalNumber());
        tvMultiChoiceContent.setText(Html.fromHtml(test.getContent()));
        if (MiscUtils.isNotEmpty(test.getOptiona())) {
            rbMultiChoiceA.setVisibility(View.VISIBLE);
            rbMultiChoiceA.setText(Html.fromHtml(test.getOptiona()));
        }
        if (MiscUtils.isNotEmpty(test.getOptionb())) {
            rbMultiChoiceB.setVisibility(View.VISIBLE);
            rbMultiChoiceB.setText(Html.fromHtml(test.getOptionb()));
        }
        if (MiscUtils.isNotEmpty(test.getOptionc())) {
            rbMultiChoiceC.setVisibility(View.VISIBLE);
            rbMultiChoiceC.setText(Html.fromHtml(test.getOptionc()));
        }
        if (MiscUtils.isNotEmpty(test.getOptiond())) {
            rbMultiChoiceD.setVisibility(View.VISIBLE);
            rbMultiChoiceD.setText(Html.fromHtml(test.getOptiond()));
        }
        if (MiscUtils.isNotEmpty(test.getAnswer())) {
            tvMultiChoiceAnswer.setText("答案：" + test.getAnswer());
        }
        if (MiscUtils.isNotEmpty(test.getKnowledge())) { tvMultiChoiceAnswerKnowledge.setText("知识点：" + test.getKnowledge()); }
        if (MiscUtils.isNotEmpty(test.getScore())) {
            tvMultiChoiceScore.setText("得分：" + test.getScore() + "/" + test.getTotalscore());
        }
        if (MiscUtils.isNotEmpty(test.getStrandanswer())) {
            tvMultiChoiceStanderAnswer.setText("标准答案：" + test.getStrandanswer());
        } else { tvMultiChoiceStanderAnswer.setText("标准答案未开放"); }
        tvTestName.setText(test.getQuestionTitle());
        for (int i = 0; i < _answerList.size(); i++) {
            if (MiscUtils.isNotEmpty(_answerList.get(i).getQuestionid())) {
                if (_answerList.get(i).getQuestionid().equals(test.getId())) {
                    if (_answerList.get(i).getAnswer().contains("A")) {
                        rbMultiChoiceA.setChecked(true);
                    } else if (_answerList.get(i).getAnswer().contains("B")) {
                        rbMultiChoiceB.setChecked(true);
                    } else if (_answerList.get(i).getAnswer().contains("C")) {
                        rbMultiChoiceC.setChecked(true);
                    } else if (_answerList.get(i).getAnswer().contains("D")) {
                        rbMultiChoiceD.setChecked(true);
                    }
                    break;
                }
            }
        }
    }
    @OnClick({R.id.iv_left_arrow, R.id.iv_right_arrow, R.id.action_undo, R.id.action_redo, R.id.action_insert_image,
            R.id.action_insert_link, R.id.iv_save, R.id.iv_back, R.id.tv_auto_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left_arrow:
                try {
                    if ((bProblem) > 0) {
                        if(bProblem <= _questionList.size()){
                            saveAnswerNotCommit();
                        }
                        bProblem = bProblem - 1;
                        if(paperModel == 2){
                            llFillBlankAnswer.setVisibility(View.GONE);
                            llChoiceAnswer.setVisibility(View.GONE);
                            llComplexAnswer.setVisibility(View.GONE);
                            llMultiChoiceAnswer.setVisibility(View.GONE);
                            tvPreview.setVisibility(View.GONE);
                        }
                        if (_questionList.get(bProblem).getQuestionType().equals("SINGLE_CHIOCE") || _questionList
                                .get(bProblem).getQuestionType().equals("JUDGE")) {
                            setChooseTest(_questionList.get(bProblem));
                        } else if (_questionList.get(bProblem).getQuestionType().equals("FILL_BLANK")||_questionList.get(bProblem).getQuestionType().equals("PROGRAM_FILL_BLANK")) {
                            setFillBlank(_questionList.get(bProblem));
                        } else if (_questionList.get(bProblem).getQuestionType().equals("COMPLEX")) {
                            setComplex(_questionList.get(bProblem));
                        } else if(_questionList.get(bProblem).getQuestionType().equals("MULIT_CHIOCE")){
                            setMulti(_questionList.get(bProblem));
                        }else{
                            setQA(_questionList.get(bProblem));
                        }
                        if (bProblem == _questionList.size()) {
                            return;
                        }
                    } else {
                        MiscUtils.showMessageToast("已经是第一个了！");
                    }
                } catch (IndexOutOfBoundsException e) {
                    MiscUtils.showMessageToast("已经是第一个了！");
                }
                break;
            case R.id.iv_right_arrow:
                    if (bProblem + 1 < _questionList.size()) {
                        if(paperModel == 2){
                            llFillBlankAnswer.setVisibility(View.GONE);
                            llChoiceAnswer.setVisibility(View.GONE);
                            llComplexAnswer.setVisibility(View.GONE);
                            llMultiChoiceAnswer.setVisibility(View.GONE);
                            tvPreview.setVisibility(View.GONE);
                        }
                        if(bProblem >=0){
                            saveAnswerNotCommit();
                        }
                        bProblem = bProblem + 1;
                        if (_questionList.get(bProblem).getQuestionType().equals("SINGLE_CHIOCE") || _questionList
                                .get(bProblem).getQuestionType().equals("JUDGE")) {
                            setChooseTest(_questionList.get(bProblem));
                        } else if (_questionList.get(bProblem).getQuestionType().equals("FILL_BLANK")) {
                            setFillBlank(_questionList.get(bProblem));
                        } else if (_questionList.get(bProblem).getQuestionType().equals("COMPLEX")) {
                            setComplex(_questionList.get(bProblem));
                        } else if(_questionList.get(bProblem).getQuestionType().equals("MULIT_CHIOCE")){
                            setMulti(_questionList.get(bProblem));
                        }else {
                            setQA(_questionList.get(bProblem));
                        }
                        if (bProblem == _questionList.size()) {
                            return;
                        }
                    } else if (enableClientJudge && paperModel == 2) {
                        // autoCommit();
                    } else {
                        MiscUtils.showMessageToast("没有更多了！");
                    }
                break;
            case R.id.action_undo:
                reEditor.undo();
                break;
            case R.id.action_redo:
                reEditor.redo();
                break;
            case R.id.action_insert_image:
                pickImage();
                //插入图片到富文本框
                break;
            case R.id.action_insert_link:
                break;
            case R.id.iv_save:
                saveAnswerAndCommit();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_auto_commit:
                try {
                    auto_commit();
                } catch (Exception e) {
                    return;
                }
                break;
        }
    }
    private void saveAnswerNotCommit(){
        if(MiscUtils.isEmpty(_questionList.get(bProblem).getId())){
            return;
        }
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setTestid(testId);
        answerEntity.setQuestionid(_questionList.get(bProblem).getId());

        for(int i = 0 ; i < _answerList.size();i++){
            if(_answerList.get(i).getQuestionid().equals(_questionList.get(bProblem).getId())){
                answerEntity  = _answerList.get(i);
                answerEntity.setTestid(testId);
                answerEntity.setQuestionid(_questionList.get(bProblem).getId());
            }
        }
        if (_questionList.get(bProblem).getQuestionType().equals("SINGLE_CHIOCE") ||_questionList.get(bProblem).getQuestionType().equals("JUDGE") ) {

        } else if (_questionList.get(bProblem).getQuestionType().equals("FILL_BLANK")) {
            StringBuilder stringBuilder = new StringBuilder();
            if (MiscUtils.isNotEmpty(tvFillBlankAnswerA.getText().toString().trim())) {
                stringBuilder.append(tvFillBlankAnswerA.getText().toString().trim());
            }else{
                stringBuilder.append("");
            }
            if (MiscUtils.isNotEmpty(tvFillBlankAnswerB.getText().toString().trim())) {
                stringBuilder.append("&&&");
                stringBuilder.append(tvFillBlankAnswerB.getText().toString().trim());
            }else{
                stringBuilder.append("&&&");
            }
            if (MiscUtils.isNotEmpty(tvFillBlankAnswerC.getText().toString().trim())) {
                stringBuilder.append("&&&");
                stringBuilder.append(tvFillBlankAnswerC.getText().toString().trim());
            }else{
                stringBuilder.append("&&&");
            }
            if (MiscUtils.isNotEmpty(tvFillBlankAnswerD.getText().toString().trim())) {
                stringBuilder.append("&&&");
                stringBuilder.append(tvFillBlankAnswerD.getText().toString().trim());
            }else{
                stringBuilder.append("&&&");
            }
            answerEntity.setAnswer(stringBuilder.toString());
        } else if (_questionList.get(bProblem).getQuestionType().equals("COMPLEX")) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i< comlex.getComplexAnswer().size() ; i++){
                sb.append(comlex.getComplexAnswer().get(i));
                if(i != (comlex.getComplexAnswer().size()-1))
                    sb.append("~~~");
            }
            answerEntity.setAnswer(sb.toString());
        } else if(_questionList.get(bProblem).getQuestionType().equals("MULIT_CHIOCE")){
            if(multiAnswer.size()>0){
                StringBuilder multi = new StringBuilder();
                for(int i = 0; i<multiAnswer.size();i++){
                    multi.append(multiAnswer.get(i));
                    if(i != (multiAnswer.size()-1))
                        multi.append("&&&");
                }
                answerEntity.setAnswer(multi.toString());
            }else{
                MiscUtils.showMessageToast("请选择答案");
            }
        }else{
            answerEntity.setAnswer(reEditor.getHtml());
        }
        _answerList.add(answerEntity);
    }
    //提交答案
    private void saveAnswerAndCommit() {
        if(MiscUtils.isEmpty(_questionList.get(bProblem).getId())){
            return;
        }
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setTestid(testId);
        answerEntity.setQuestionid(_questionList.get(bProblem).getId());
        for(int i = 0 ; i < _answerList.size();i++){
            if(_answerList.get(i).getQuestionid().equals(_questionList.get(bProblem).getId())){
                answerEntity  = _answerList.get(i);
                answerEntity.setTestid(testId);
                answerEntity.setQuestionid(_questionList.get(bProblem).getId());
            }
        }
        if (_questionList.get(bProblem).getQuestionType().equals("SINGLE_CHIOCE") ||_questionList.get(bProblem).getQuestionType().equals("JUDGE") ) {

        } else if (_questionList.get(bProblem).getQuestionType().equals("FILL_BLANK")) {
            StringBuilder stringBuilder = new StringBuilder();
            if (MiscUtils.isNotEmpty(tvFillBlankAnswerA.getText().toString().trim())) {
                stringBuilder.append(tvFillBlankAnswerA.getText().toString().trim());
            }else{
                stringBuilder.append("");
            }
            if (MiscUtils.isNotEmpty(tvFillBlankAnswerB.getText().toString().trim())) {
                stringBuilder.append("&&&");
                stringBuilder.append(tvFillBlankAnswerB.getText().toString().trim());
            }else{
                stringBuilder.append("&&&");
            }
            if (MiscUtils.isNotEmpty(tvFillBlankAnswerC.getText().toString().trim())) {
                stringBuilder.append("&&&");
                stringBuilder.append(tvFillBlankAnswerC.getText().toString().trim());
            }else{
                stringBuilder.append("&&&");
            }
            if (MiscUtils.isNotEmpty(tvFillBlankAnswerD.getText().toString().trim())) {
                stringBuilder.append("&&&");
                stringBuilder.append(tvFillBlankAnswerD.getText().toString().trim());
            }else{
                stringBuilder.append("&&&");
            }
            answerEntity.setAnswer(stringBuilder.toString());
        } else if (_questionList.get(bProblem).getQuestionType().equals("COMPLEX")) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i< comlex.getComplexAnswer().size() ; i++){
                sb.append(comlex.getComplexAnswer().get(i));
                if(i != (comlex.getComplexAnswer().size()-1))
                    sb.append("~~~");
            }
            answerEntity.setAnswer(sb.toString());
        } else if(_questionList.get(bProblem).getQuestionType().equals("MULIT_CHIOCE")){
            if(multiAnswer.size()>0){
                StringBuilder multi = new StringBuilder();
                for(int i = 0; i<multiAnswer.size();i++){
                    multi.append(multiAnswer.get(i));
                    if(i != (multiAnswer.size()-1))
                        multi.append("&&&");
                }
                answerEntity.setAnswer(multi.toString());
            }else{
                MiscUtils.showMessageToast("请选择答案");
            }
        }else{
            answerEntity.setAnswer(reEditor.getHtml());
        }
        _answerList.add(answerEntity);
        JSONObject temp = new JSONObject();
        /**
         * testid	：	考试id
         questionid:  题目id
         answer	:    答题内容
         */
        temp.put("testid", testId);
        temp.put("questionid", _questionList.get(bProblem).getId());
        temp.put("answer", answerEntity.getAnswer());
        submitResult.put("data", new String(Base64.encode(JSONObject.toJSONString(temp).getBytes(), Base64.DEFAULT)));
        RequestManager.getmInstance()
                .apiPostData(AppConstant.COMMIT_TEST_RESULE, submitResult, this, AppConstant.POST_COMMIT_TEST_RESULT);
    }

    //自动阅卷
    private void auto_commit() {
        /**
         * testid	：	考试id
         questionid:  题目id
         */
        submitResult.put("testid", testId);
        submitResult.put("questionid", _questionList.get(bProblem).getId());
        RequestManager.getmInstance()
                .apiPostData(AppConstant.AUTO_COMMIT_RESULT, submitResult, this, AppConstant.POST_AUTO_COMMIT_RESULT);
    }

    public static void struct() {
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog()
                        .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects() // 探测SQLite数据库操作
                .penaltyLog() // 打印logcat
                .penaltyDeath().build());
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION) {
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(REQUEST_STORAGE_READ_ACCESS_PERMISSION)
    private void pickImage() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION, perms);
        } else {
            MultiImageSelector selector = MultiImageSelector.create();
            selector.showCamera(true);
            selector.single();
            selector.origin(selectPath);
            selector.start(TestDetailActivity.this, REQUEST_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE) {
            selectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
            if (MiscUtils.isNotEmpty(selectPath)) {
                File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                if (!outDir.exists()) {
                    outDir.mkdirs();
                }
                File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
                //裁剪后图片的绝对路径
                //  String cameraScalePath = outFile.getAbsolutePath();
                Uri destinationUri = Uri.fromFile(outFile);
                MCrop.of(Uri.fromFile(new File(selectPath.get(0))),destinationUri).withAspectRatio(1,1)
                        .withMaxResultSize
                                (200,200).start(this);
            }
        } else if ( resultCode == RESULT_OK && requestCode == MCrop.REQUEST_CROP) {
            Log.e("path"," exe");
            if (data != null) {
                Uri uri = MCrop.getOutput(data);
                if (uri == null) {
                    UIUtils.showToast("选取失败");
                } else {
                    Log.e("path"," " +uri.getPath());
                    String path = uri.getPath();
                    //上传图像
                    doUpLoad(path);
                }
            }
        }
    }

    private void doUpLoad(String uri) {
        RequestParams params = new RequestParams();
        try {
            params.put("name", new File(uri));
            params.put("type","2");
        } catch (IOException e) {
        }
        AsyHttpClient.get("upfile", params, handler);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            UpLoadFileEntity upLoadFileEntity =(UpLoadFileEntity) msg.obj;
            Log.e("uploadUrk",JSONObject.toJSONString(upLoadFileEntity));
            reEditor.insertImage(upLoadFileEntity.getUploadedurl(), upLoadFileEntity.getUploadedfilename());
        }
    };


    private int getBlankNumber(String standerAnswer) {
        return standerAnswer.split("&&&").length;
    }
}
