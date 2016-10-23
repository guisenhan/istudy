package hise.hznu.istudy.activity.course;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.Html;
import android.util.Log;
import android.view.View;
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
import com.loopj.android.http.Base64;
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
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.AsyHttpClient;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.UpLoadFileEntity;
import hise.hznu.istudy.model.course.AnswerEntity;
import hise.hznu.istudy.model.course.TestEntity;
import hise.hznu.istudy.model.course.TestPaperEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.MiscUtils;
import hise.hznu.istudy.util.UIUtils;
import me.nereo.multi_image_selector.MultiImageSelector;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class TestDetailActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
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
    @BindView(R.id.action_bold)
    ImageButton actionBold;
    @BindView(R.id.action_italic)
    ImageButton actionItalic;
    @BindView(R.id.action_subscript)
    ImageButton actionSubscript;
    @BindView(R.id.action_superscript)
    ImageButton actionSuperscript;
    @BindView(R.id.action_strikethrough)
    ImageButton actionStrikethrough;
    @BindView(R.id.action_underline)
    ImageButton actionUnderline;
    @BindView(R.id.action_heading1)
    ImageButton actionHeading1;
    @BindView(R.id.action_heading2)
    ImageButton actionHeading2;
    @BindView(R.id.action_heading3)
    ImageButton actionHeading3;
    @BindView(R.id.action_heading4)
    ImageButton actionHeading4;
    @BindView(R.id.action_heading5)
    ImageButton actionHeading5;
    @BindView(R.id.action_heading6)
    ImageButton actionHeading6;
    @BindView(R.id.action_txt_color)
    ImageButton actionTxtColor;
    @BindView(R.id.action_bg_color)
    ImageButton actionBgColor;
    @BindView(R.id.action_indent)
    ImageButton actionIndent;
    @BindView(R.id.action_outdent)
    ImageButton actionOutdent;
    @BindView(R.id.action_align_left)
    ImageButton actionAlignLeft;
    @BindView(R.id.action_align_center)
    ImageButton actionAlignCenter;
    @BindView(R.id.action_align_right)
    ImageButton actionAlignRight;
    @BindView(R.id.action_insert_bullets)
    ImageButton actionInsertBullets;
    @BindView(R.id.action_insert_numbers)
    ImageButton actionInsertNumbers;
    @BindView(R.id.action_blockquote)
    ImageButton actionBlockquote;
    @BindView(R.id.action_insert_image)
    ImageButton actionInsertImage;
    @BindView(R.id.action_insert_link)
    ImageButton actionInsertLink;
    @BindView(R.id.action_insert_checkbox)
    ImageButton actionInsertCheckbox;
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

    private List<TestEntity> _dataList = new ArrayList<TestEntity>();
    private String testId;
    private int bProblem = 0;
    private List<AnswerEntity> _answerList = new ArrayList<AnswerEntity>();
    boolean isChange = true;
    boolean isBackChange = true;
    private ArrayList<String> selectPath;
    private List<TestPaperEntity> _questionList = new ArrayList<TestPaperEntity>(); //重新处理数据
    private JSONObject submitResult; // 提交的答案
    /**
     * enableClientJudge			是否开启客户端阅卷
     * keyVisible					阅卷时参考答案是否可见
     * viewOneWithAnswerKey		查卷时参考答案是否可见
     */
    private boolean enableClientJudge;
    private boolean keyVisible;
    private boolean viewOneWithAnswerKey;
    private int paperModel = 1; //1表示查卷模式，2表示答题模式，3 表示阅卷模式

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
        Log.e("paperModel",""+paperModel);
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
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_test_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        rlTestTitle.setVisibility(View.GONE);
        slFillBlank.setVisibility(View.GONE);
        slTestChoose.setVisibility(View.GONE);
        slTestChoose.setVisibility(View.GONE);
        llTestType.setVisibility(View.GONE);

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
                llFillBlanks.setVisibility(View.GONE);
                if (viewOneWithAnswerKey) { //查卷时参考答案是否可见
                    tvChooseStanderAnswer.setVisibility(View.VISIBLE);
                    tvFillBlankStanderAnswer.setVisibility(View.VISIBLE);
                    tvPreview.setVisibility(View.VISIBLE);
                } else {
                    tvChooseStanderAnswer.setVisibility(View.GONE);
                    tvFillBlankStanderAnswer.setVisibility(View.GONE);
                    tvPreview.setVisibility(View.GONE);
                }
                break;
            case 2: //答题模式
                llFillBlankAnswer.setVisibility(View.GONE);
                llChoiceAnswer.setVisibility(View.GONE);
                tvPreview.setVisibility(View.GONE);
                ivSave.setVisibility(View.VISIBLE);
                break;
            case 3: //阅卷模式
                ivSave.setVisibility(View.GONE);
                if (keyVisible) {
                    tvChooseStanderAnswer.setVisibility(View.VISIBLE);
                    tvFillBlankStanderAnswer.setVisibility(View.VISIBLE);
                    tvPreview.setVisibility(View.VISIBLE);
                } else {
                    tvPreview.setVisibility(View.GONE);
                    tvFillBlankStanderAnswer.setVisibility(View.GONE);
                    tvChooseStanderAnswer.setVisibility(View.GONE);
                }
                break;
        }

        rgAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == rbChooseA.getId() && rbChooseA.isPressed()) {
                    AnswerEntity answerEntity = new AnswerEntity();
                    for (int j = 0; j < _answerList.size(); j++) {
                        if (_questionList.get(bProblem).getId().equals(_answerList.get(j).getAnswer())) {
                            answerEntity = _answerList.get(j);
                            break;
                        }
                    }
                    if (MiscUtils.isEmpty(answerEntity.getQuestionid())) {
                        answerEntity.setTestid(testId);
                        answerEntity.setQuestionid(_questionList.get(bProblem).getId());
                    }
                    answerEntity.setAnswer("A");
                    _answerList.add(answerEntity);
                } else if (radioGroup.getCheckedRadioButtonId() == rbChooseB.getId() && rbChooseB.isPressed()) {
                    AnswerEntity answerEntity = new AnswerEntity();
                    for (int j = 0; j < _answerList.size(); j++) {
                        if (_questionList.get(bProblem).getId().equals(_answerList.get(j).getAnswer())) {
                            answerEntity = _answerList.get(j);
                            break;
                        }
                    }
                    if (MiscUtils.isEmpty(answerEntity.getQuestionid())) {
                        answerEntity.setTestid(testId);
                        answerEntity.setQuestionid(_questionList.get(bProblem).getId());
                    }
                    answerEntity.setAnswer("B");
                    _answerList.add(answerEntity);
                } else if (radioGroup.getCheckedRadioButtonId() == rbChooseC.getId() && rbChooseC.isPressed()) {
                    AnswerEntity answerEntity = new AnswerEntity();
                    for (int j = 0; j < _answerList.size(); j++) {
                        if (_questionList.get(bProblem).getId().equals(_answerList.get(j).getAnswer())) {
                            answerEntity = _answerList.get(j);
                            break;
                        }
                    }
                    if (MiscUtils.isEmpty(answerEntity.getQuestionid())) {
                        answerEntity.setTestid(testId);
                        answerEntity.setQuestionid(_questionList.get(bProblem).getId());
                    }
                    answerEntity.setAnswer("C");
                    _answerList.add(answerEntity);
                } else if (radioGroup.getCheckedRadioButtonId() == rbChooseD.getId() && rbChooseD.isPressed()) {
                    AnswerEntity answerEntity = new AnswerEntity();
                    for (int j = 0; j < _answerList.size(); j++) {
                        if (_questionList.get(bProblem).getId().equals(_answerList.get(j).getAnswer())) {
                            answerEntity = _answerList.get(j);
                            break;
                        }
                    }
                    if (MiscUtils.isEmpty(answerEntity.getQuestionid())) {
                        answerEntity.setTestid(testId);
                        answerEntity.setQuestionid(_questionList.get(bProblem).getId());
                    }
                    answerEntity.setAnswer("D");
                    _answerList.add(answerEntity);
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
                    initTestView();
                }
                break;
            case AppConstant.POST_COMMIT_TEST_RESULT:
                MiscUtils.showMessageToast("保存成功，请继续答题");
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
                _questionList.add(question);
            }
        }
    }

    private void initTestView() {
        llTestType.setVisibility(View.VISIBLE);
        rlTestTitle.setVisibility(View.GONE);
        slQuestionAnswer.setVisibility(View.GONE);
        slTestChoose.setVisibility(View.GONE);
        slFillBlank.setVisibility(View.GONE);
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
        tvNumber.setText(test.getNumber() + "/" + test.getTotalNumber());
        tvTestContent.setText(Html.fromHtml(test.getContent()));
        if (MiscUtils.isNotEmpty(test.getOptiona())) {
            rbChooseA.setVisibility(View.VISIBLE);
            rbChooseA.setText(Html.fromHtml(test.getOptiona()));
        }
        if (MiscUtils.isNotEmpty(test.getOptionb())) {
            rbChooseB.setVisibility(View.VISIBLE);
            rbChooseB.setText(Html.fromHtml(test.getOptionb()));
        }
        if (MiscUtils.isNotEmpty(test.getOptionc())) {
            rbChooseC.setVisibility(View.VISIBLE);
            rbChooseC.setText(Html.fromHtml(test.getOptionc()));
        }
        if (MiscUtils.isNotEmpty(test.getOptiond())) {
            rbChooseD.setVisibility(View.VISIBLE);
            rbChooseD.setText(Html.fromHtml(test.getOptiond()));
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
            if (MiscUtils.isNotEmpty(_answerList.get(i).getQuestionid())) {
                if (_answerList.get(i).getQuestionid().equals(test.getId())) {
                    if (_answerList.get(i).getAnswer().equals("A")) {
                        rgAnswer.check(rbChooseA.getId());
                    } else if (_answerList.get(i).getAnswer().equals("B")) {
                        rbChooseB.setChecked(true);
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
        slFillBlank.setVisibility(View.VISIBLE);
        slQuestionAnswer.setVisibility(View.GONE);
        if (test.isDesc()) {
            llTestType.setVisibility(View.VISIBLE);
            rlTestTitle.setVisibility(View.GONE);
            slQuestionAnswer.setVisibility(View.GONE);
            slTestChoose.setVisibility(View.GONE);
            slFillBlank.setVisibility(View.GONE);
            tvTestType.setText(test.getQuestionTitle());
            tvTestTypeInfo.setText(test.getQuestionDesc());
            return;
        }
        switch (getBlankNumber(test.getStrandanswer())) {
            case 1:
                tvFillBlankAnswerA.setVisibility(View.VISIBLE);
                tvFillBlankAnswerB.setVisibility(View.GONE);
                tvFillBlankAnswerC.setVisibility(View.GONE);
                tvFillBlankAnswerD.setVisibility(View.GONE);
                break;
            case 2:
                tvFillBlankAnswerA.setVisibility(View.VISIBLE);
                tvFillBlankAnswerB.setVisibility(View.VISIBLE);
                tvFillBlankAnswerC.setVisibility(View.GONE);
                tvFillBlankAnswerD.setVisibility(View.GONE);
                break;
            case 3:
                tvFillBlankAnswerA.setVisibility(View.VISIBLE);
                tvFillBlankAnswerB.setVisibility(View.VISIBLE);
                tvFillBlankAnswerC.setVisibility(View.VISIBLE);
                tvFillBlankAnswerD.setVisibility(View.GONE);
                break;
            case 4:
                tvFillBlankAnswerA.setVisibility(View.VISIBLE);
                tvFillBlankAnswerB.setVisibility(View.VISIBLE);
                tvFillBlankAnswerC.setVisibility(View.VISIBLE);
                tvFillBlankAnswerD.setVisibility(View.VISIBLE);
                break;
        }
        //getFillBlankResult(test.getAnswer());
        tvTestName.setText(test.getQuestionTitle());
        tvNumber.setText(test.getNumber() + "/" + test.getTotalNumber());
        tvFillBlankTitle.setText(Html.fromHtml(test.getContent(), imgGetter, null));
        tvFillBlankAnswer.setText("答案:" + Html.fromHtml(test.getAnswer()));
        tvFillBlankKnowledge.setText("知识点：" + Html.fromHtml(test.getKnowledge()));
        tvFillBlankScore.setText("得分：" + test.getScore() + "/" + test.getTotalscore());
        tvFillBlankStanderAnswer.setText("标准答案：" + Html.fromHtml(test.getStrandanswer()));
    }

    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            URL url;
            try {
                url = new URL(source);
                drawable = Drawable.createFromStream(url.openStream(), ""); // 获取网路图片
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            drawable.setBounds(0, 0, MiscUtils.getScreenWidth(),
                    (drawable.getIntrinsicHeight() * MiscUtils.getScreenWidth()) / drawable.getIntrinsicWidth());
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
        if (test.isDesc()) {
            llTestType.setVisibility(View.VISIBLE);
            rlTestTitle.setVisibility(View.GONE);
            slQuestionAnswer.setVisibility(View.GONE);
            slTestChoose.setVisibility(View.GONE);
            slFillBlank.setVisibility(View.GONE);
            tvTestType.setText(test.getQuestionTitle());
            tvTestTypeInfo.setText(test.getQuestionDesc());
            return;
        }
        tvTestName.setText(test.getQuestionTitle());
        tvNumber.setText(test.getNumber() + "/" + test.getTotalNumber());
        tvQuestionAnswerTitle.setText(Html.fromHtml(test.getContent(), imgGetter, null));
        reEditor.setHtml(test.getAnswer());
        tvPreview.setText("标准答案：" + Html.fromHtml(test.getStrandanswer()));
    }

    @OnClick({R.id.iv_left_arrow, R.id.iv_right_arrow, R.id.action_undo, R.id.action_redo, R.id.action_bold,
            R.id.action_italic, R.id.action_subscript, R.id.action_superscript, R.id.action_strikethrough,
            R.id.action_underline, R.id.action_heading1, R.id.action_heading2, R.id.action_heading3,
            R.id.action_heading4, R.id.action_heading5, R.id.action_heading6, R.id.action_txt_color,
            R.id.action_bg_color, R.id.action_indent, R.id.action_outdent, R.id.action_align_left,
            R.id.action_align_center, R.id.action_align_right, R.id.action_insert_bullets, R.id.action_insert_numbers,
            R.id.action_blockquote, R.id.action_insert_image, R.id.action_insert_link, R.id.action_insert_checkbox,
            R.id.iv_save, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left_arrow:
                if ((bProblem - 1) > 0) {
                    bProblem = bProblem - 1;
                    if (_questionList.get(bProblem).getQuestionType().equals("SINGLE_CHIOCE")) {
                        setChooseTest(_questionList.get(bProblem));
                    } else if (_questionList.get(bProblem).getQuestionType().equals("FILL_BLANK")) {
                        setFillBlank(_questionList.get(bProblem));
                    } else {
                        setQA(_questionList.get(bProblem));
                    }
                    if (bProblem == _questionList.size()) {
                        return;
                    }
                } else {
                    MiscUtils.showMessageToast("没有更多了！");
                }
                break;
            case R.id.iv_right_arrow:
                try {
                    if (bProblem + 1 < _questionList.size()) {
                        bProblem = bProblem + 1;
                        if (_questionList.get(bProblem).getQuestionType().equals("SINGLE_CHIOCE")) {
                            setChooseTest(_questionList.get(bProblem));
                        } else if (_questionList.get(bProblem).getQuestionType().equals("FILL_BLANK")) {
                            setFillBlank(_questionList.get(bProblem));
                        } else {
                            setQA(_questionList.get(bProblem));
                        }
                        if (bProblem == _questionList.size()) {
                            return;
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    MiscUtils.showMessageToast("没有更多了！");
                }
                break;
            case R.id.action_undo:
                reEditor.undo();
                break;
            case R.id.action_redo:
                reEditor.redo();
                break;
            case R.id.action_bold:
                reEditor.setBold();
                break;
            case R.id.action_italic:
                reEditor.setItalic();
                break;
            case R.id.action_subscript:
                reEditor.setSubscript();
                break;
            case R.id.action_superscript:
                reEditor.setSuperscript();
                break;
            case R.id.action_strikethrough:
                reEditor.setStrikeThrough();
                break;
            case R.id.action_underline:
                reEditor.setUnderline();
                break;
            case R.id.action_heading1:
                reEditor.setHeading(1);
                break;
            case R.id.action_heading2:
                reEditor.setHeading(2);
                break;
            case R.id.action_heading3:
                reEditor.setHeading(3);
                break;
            case R.id.action_heading4:
                reEditor.setHeading(4);
                break;
            case R.id.action_heading5:
                reEditor.setHeading(5);
                break;
            case R.id.action_heading6:
                reEditor.setHeading(6);
                break;
            case R.id.action_txt_color:
                reEditor.setTextColor(isChange ? Color.BLACK : Color.RED);
                isChange = !isChange;
                break;
            case R.id.action_bg_color:
                reEditor.setTextBackgroundColor(isBackChange ? Color.TRANSPARENT : Color.YELLOW);
                isBackChange = !isBackChange;
                break;
            case R.id.action_indent:
                reEditor.setIndent();
                break;
            case R.id.action_outdent:
                reEditor.setOutdent();
                break;
            case R.id.action_align_left:
                reEditor.setAlignLeft();
                break;
            case R.id.action_align_center:
                reEditor.setAlignCenter();
                break;
            case R.id.action_align_right:
                reEditor.setAlignRight();
                break;
            case R.id.action_insert_bullets:
                reEditor.setBullets();
                break;
            case R.id.action_insert_numbers:
                reEditor.setNumbers();
                break;
            case R.id.action_blockquote:
                reEditor.setBlockquote();
                break;
            case R.id.action_insert_image:
                pickImage();
                //插入图片到富文本框
                break;
            case R.id.action_insert_link:
                break;
            case R.id.action_insert_checkbox:
                reEditor.insertTodo();
                break;
            case R.id.iv_save:
                saveAnswer();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void saveAnswer() {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setTestid(testId);
        answerEntity.setQuestionid(_questionList.get(bProblem).getId());
        if (_questionList.get(bProblem).getQuestionType().equals("SINGLE_CHIOCE")) {

        } else if (_questionList.get(bProblem).getQuestionType().equals("FILL_BLANK")) {
            StringBuilder stringBuilder = new StringBuilder();
            if (MiscUtils.isNotEmpty(tvFillBlankAnswerA.getText().toString().trim())) {
                stringBuilder.append(tvFillBlankAnswerA.getText().toString().trim());
            }
            if (MiscUtils.isNotEmpty(tvFillBlankAnswerB.getText().toString().trim())) {
                stringBuilder.append("&&&");
                stringBuilder.append(tvFillBlankAnswerB.getText().toString().trim());
            }
            if (MiscUtils.isNotEmpty(tvFillBlankAnswerC.getText().toString().trim())) {
                stringBuilder.append("&&&");
                stringBuilder.append(tvFillBlankAnswerC.getText().toString().trim());
            }
            if (MiscUtils.isNotEmpty(tvFillBlankAnswerD.getText().toString().trim())) {
                stringBuilder.append("&&&");
                stringBuilder.append(tvFillBlankAnswerD.getText().toString().trim());
            }
            answerEntity.setAnswer(stringBuilder.toString());
        } else {
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
        temp.put("questionid", _answerList.get(bProblem).getQuestionid());
        temp.put("answer", _answerList.get(bProblem).getAnswer());
        submitResult.put("data", new String(Base64.encode(JSONObject.toJSONString(temp).getBytes(), Base64.DEFAULT)));
        RequestManager.getmInstance()
                .apiPostData(AppConstant.COMMIT_TEST_RESULE, submitResult, this, AppConstant.POST_COMMIT_TEST_RESULT);
    }

    public static void struct() {
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork() // or
                        // .detectAll()
                        // for
                        // all
                        // detectable
                        // problems
                        .penaltyLog().build());
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
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE) {
                selectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                if (MiscUtils.isNotEmpty(selectPath)) {
                    AppUtils.startClipAvatarActivity(this, new File(selectPath.get(0)));
                }
            } else if (requestCode == AppUtils.REQUEST_CODE_CLIP_PHOTO) {
                if (data != null) {
                    Uri uri = data.getData();
                    if (uri == null) {
                        UIUtils.showToast("选取失败");
                    } else {
                        doUpLoad();
                    }
                }
            }
        }
    }

    private void doUpLoad() {
        RequestParams params = new RequestParams();
        try {
            params.put("name", new File(selectPath.get(0)));
        } catch (IOException e) {

        }
        AsyHttpClient.get("upfile", params, handler);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            org.json.JSONObject response = (org.json.JSONObject) msg.obj;
            UpLoadFileEntity upLoadFileEntity = new UpLoadFileEntity();
            upLoadFileEntity = new ApiResponse(JSON.parseObject(response.toString())).getInfo(UpLoadFileEntity.class);
            reEditor.insertImage(upLoadFileEntity.getUploadedurl(), upLoadFileEntity.getUploadedfilename());
        }
    };

    private void getFillBlankResult(String result) {
        String[] temp = result.split("&&&");
        for (int i = 0; i < temp.length; i++) {
            Log.e("fill blank", temp[i]);
        }
    }

    private int getBlankNumber(String standerAnswer) {
        return standerAnswer.split("&&&").length;
    }
}
