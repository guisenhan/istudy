package hise.hznu.istudy.activity.course;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
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

import com.alibaba.fastjson.JSONObject;

import jp.wasabeef.richeditor.RichEditor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.course.AnswerEntity;
import hise.hznu.istudy.model.course.TestEntity;
import hise.hznu.istudy.model.course.TestPaperEntity;
import hise.hznu.istudy.util.MiscUtils;

public class TestDetailActivity extends BaseActivity {
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
    @BindView(R.id.iv_center)
    ImageView ivCenter;
    @BindView(R.id.iv_right_arrow)
    ImageView ivRightArrow;


    private List<TestEntity> _dataList = new ArrayList<TestEntity>();
    private String testId;
    private int bProblem = 0;
    private int sProblem = -1;
    private List<AnswerEntity> _answerList = new ArrayList<AnswerEntity>();
    @Override
    protected void initData() {
        super.initData();
        testId = getIntent().getStringExtra("testId");
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

        rgAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.e("checkChange","exe");
                    AnswerEntity answerEntity = new AnswerEntity();
                    for(int j = 0 ; j < _answerList.size();j++){
                        if(_dataList.get(bProblem).getQuestions().get(sProblem).getId().equals(_answerList.get(j)
                                .getAnswer())){
                            answerEntity = _answerList.get(j);
                            break;
                        }
                    }
                if(MiscUtils.isEmpty(answerEntity.getQuestionid())){
                    answerEntity.setTestid(testId);
                    answerEntity.setQuestionid(_dataList.get(bProblem).getQuestions().get(sProblem).getId());
                }
                if(i ==rbChooseA.getId()){

                        answerEntity.setAnswer("a");
                        _answerList.add(answerEntity);
                    Log.e("rbChooseA","exe");
                    }else if(i == rbChooseB.getId()){
                        answerEntity.setAnswer("b");
                        _answerList.add(answerEntity);
                    Log.e("rbChooseB","exe");
                    }else if(i == rbChooseC.getId()){
                        answerEntity.setAnswer("c");
                        _answerList.add(answerEntity);
                    Log.e("rbChooseC","exe");
                    }else if(i == rbChooseD.getId()) {
                        answerEntity.setAnswer("d");
                        _answerList.add(answerEntity);
                    Log.e("rbChooseD","exe");
                    }
                }
        });
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        _dataList = response.getListData(TestEntity.class);
        if (_dataList.size() > 0) {
            initTestView();
        }
    }

    private void initTestView() {
        llTestType.setVisibility(View.VISIBLE);
        rlTestTitle.setVisibility(View.GONE);
        tvTestType.setText(_dataList.get(bProblem).getTitle());
        tvTestName.setText(_dataList.get(bProblem).getTitle());

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
        tvNumber.setText((sProblem+1)+"/"+_dataList.get(bProblem).getQuestions().size());
        tvTestContent.setText(Html.fromHtml(test.getContent()));
        if(MiscUtils.isNotEmpty(test.getOptiona())){
            rbChooseA.setVisibility(View.VISIBLE);
            rbChooseA.setText(Html.fromHtml(test.getOptiona()));
        }
        if(MiscUtils.isNotEmpty(test.getOptionb())){
            rbChooseB.setVisibility(View.VISIBLE);
            rbChooseB.setText(Html.fromHtml(test.getOptionb()));
        }
        if(MiscUtils.isNotEmpty(test.getOptionc())){
            rbChooseC.setVisibility(View.VISIBLE);
            rbChooseC.setText(Html.fromHtml(test.getOptionc()));
        }
        if(MiscUtils.isNotEmpty(test.getOptiond())){
            rbChooseD.setVisibility(View.VISIBLE);
            rbChooseD.setText(Html.fromHtml(test.getOptiond()));
        }
        if(MiscUtils.isNotEmpty(test.getAnswer())){
            tvChooseAnswer.setText("答案："+test.getAnswer());
        }
        if(MiscUtils.isNotEmpty(test.getKnowledge()))
            tvAnswerKnowledge.setText("知识点："+test.getKnowledge());
        if(MiscUtils.isNotEmpty(test.getScore()))
            tvChooseScore.setText("得分："+test.getScore()+"/"+test.getTotalscore());
        if(MiscUtils.isNotEmpty(test.getStrandanswer()))
            tvChooseStanderAnswer.setText("标准答案："+test.getStrandanswer());
        else
            tvChooseStanderAnswer.setText("标准答案未开放");

        rbChooseA.setChecked(false);
        rbChooseD.setChecked(false);
        rbChooseC.setChecked(false);
        rbChooseB.setChecked(false);
        if(_answerList.size()>0){
            for(int i = 0; i<_answerList.size();i++){
                if(MiscUtils.isNotEmpty(_answerList.get(i).getQuestionid())){
                    if(_answerList.get(i).getQuestionid().equals(test.getId())){
                        Log.e("questionid",_answerList.get(i).getQuestionid() +"testid:"+test.getId() +" " +_answerList.get(i)
                                .getAnswer() );
                        if(_answerList.get(i).getAnswer().equals("a")) {
                            Log.e("exe1", "exe");
                            rbChooseA.setChecked(true);
                        }else if(_answerList.get(i).getAnswer().equals("b")){
                            Log.e("exe2","exe");
                            rbChooseB.setChecked(true);
                        }else if(_answerList.get(i).getAnswer().equals("c")){
                            rbChooseC.setChecked(true);
                            Log.e("exe3","exe");
                        }else if(_answerList.get(i).getAnswer().equals("d")){
                            rbChooseD.setChecked(true);
                            Log.e("exe4","exe");
                        }
                        break;
                    }
                }

            }
        }

    }

    /**
     * 显示填空题
     */
    private void setFillBlank(TestPaperEntity test) {

    }

    /**
     * 显示问答题
     */
    private void setQA(TestPaperEntity test) {

    }

    @OnClick({R.id.iv_left_arrow, R.id.iv_center, R.id.iv_right_arrow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left_arrow:
                if(bProblem < _dataList.size() && bProblem>=0){
                    if(sProblem<_dataList.get(bProblem).getQuestions().size()){
                        sProblem =sProblem-1;
                        if(_dataList.get(bProblem).getType().equals("SINGLE_CHIOCE")){
                            setChooseTest(_dataList.get(bProblem).getQuestions().get(sProblem));
                        }
                    }else{
                        sProblem = 0;
                        bProblem = bProblem-1;
                    }
                }
                break;
            case R.id.iv_center:
                break;
            case R.id.iv_right_arrow:
                if(bProblem < _dataList.size()){
                    if(sProblem<_dataList.get(bProblem).getQuestions().size()){
                            sProblem =sProblem+1;
                        if(_dataList.get(bProblem).getType().equals("SINGLE_CHIOCE")){
                            setChooseTest(_dataList.get(bProblem).getQuestions().get(sProblem));
                        }
                    }else{
                        sProblem = 0;
                        bProblem = bProblem+1;
                    }
                }
                break;
        }
    }
}
