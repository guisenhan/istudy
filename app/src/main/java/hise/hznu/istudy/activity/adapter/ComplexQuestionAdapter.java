package hise.hznu.istudy.activity.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import jp.wasabeef.richeditor.RichEditor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.course.TestDetailActivity;
import hise.hznu.istudy.model.course.AnswerEntity;
import hise.hznu.istudy.model.course.CommentTaskEntity;
import hise.hznu.istudy.model.course.HomeWorkEntity;
import hise.hznu.istudy.model.course.SubQuestionEntity;
import hise.hznu.istudy.util.MiscUtils;

/**
 * Created by PC on 2016/9/21.
 */
public class ComplexQuestionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SubQuestionEntity> _dataList = new ArrayList<SubQuestionEntity>();
    private Html.ImageGetter imgGetter;
    private View.OnClickListener clickListener;

    private List<String> complexAnswer = new ArrayList<String>();
    private List<String> multiAnswer = new ArrayList<String>();
    private String[] fillBlankAnswer ;
    private String answer = "";
    public ComplexQuestionAdapter(Context context,Html.ImageGetter imageGetter,View.OnClickListener clickListener,
            String answer) {
        this.context = context;
        this.imgGetter = imageGetter;
        this.clickListener = clickListener;
        this.answer = answer;
    }

    @Override
    public int getCount() {
        return _dataList == null ? 0 : _dataList.size();
    }

    @Override
    public Object getItem(int position) {
        int topCount = _dataList == null ? 0 : _dataList.size();
        if (position < topCount) {
            return _dataList.get(position);
        } else {
            return null;
        }
    }

    public void UpdateView(List<SubQuestionEntity> newList) {
        if (_dataList != null) {
            _dataList.clear();
            this.notifyDataSetChanged();
        }
        _dataList.addAll(newList);
        for(int i = 0 ; i < _dataList.size(); i ++){
            complexAnswer.add(" ");
        }
        if(MiscUtils.isNotEmpty(answer)){
            complexAnswer = new ArrayList<String>();
            complexAnswer = getAnswer(answer);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String  viewType = _dataList.get(position).getType();
        SubQuestionEntity test = _dataList.get(position);
        if(viewType.equals("JUDGE")||viewType.equals("SINGLE_CHIOCE")){
            Choice view ;
            view = new Choice();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_choice_question,null);
            view.rbChooseA = (RadioButton)convertView.findViewById(R.id.rb_choose_a);
            view.rbChooseB = (RadioButton)convertView.findViewById(R.id.rb_choose_b);
            view.rbChooseC = (RadioButton)convertView.findViewById(R.id.rb_choose_c);
            view.rbChooseD = (RadioButton)convertView.findViewById(R.id.rb_choose_d);
            view.rbChooseE = (RadioButton)convertView.findViewById(R.id.rb_choose_e);
            view.tvTestContent = (TextView)convertView.findViewById(R.id.tv_test_content);
            view.rgAnswer = (RadioGroup)convertView.findViewById(R.id.rg_answer);
            convertView.setTag(view);
            view.tvTestContent.setText(Html.fromHtml(test.getContent()));
            if (MiscUtils.isNotEmpty(test.getOptiona())) {
                view.rbChooseA.setVisibility(View.VISIBLE);
                view.rbChooseA.setText(Html.fromHtml(test.getOptiona()));
            }
            if (MiscUtils.isNotEmpty(test.getOptionb())) {
                view.rbChooseB.setVisibility(View.VISIBLE);
                view.rbChooseB.setText(Html.fromHtml(test.getOptionb()));
            }
            if (MiscUtils.isNotEmpty(test.getOptionc())) {
                view.rbChooseC.setVisibility(View.VISIBLE);
                view.rbChooseC.setText(Html.fromHtml(test.getOptionc()));
            }
            if (MiscUtils.isNotEmpty(test.getOptiond())) {
                view.rbChooseD.setVisibility(View.VISIBLE);
                view.rbChooseD.setText(Html.fromHtml(test.getOptiond()));
            }
            if(MiscUtils.isNotEmpty(answer)){
                if(complexAnswer.get(position).equals("A")){
                    view.rbChooseA.setChecked(true);
                }else if(complexAnswer.get(position).equals("B")){
                    view.rbChooseB.setChecked(true);
                }else if(complexAnswer.get(position).equals("C")){
                    view.rbChooseC.setChecked(true);
                }else if(complexAnswer.get(position).equals("D")){
                    view.rbChooseD.setChecked(true);
                }
            }
            final Choice temp = view;
            view.rgAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (radioGroup.getCheckedRadioButtonId() == temp.rbChooseA.getId() && temp.rbChooseA.isPressed()) {
                        complexAnswer.set(position,"A");
                    } else if (radioGroup.getCheckedRadioButtonId() == temp.rbChooseB.getId() && temp.rbChooseB.isPressed()) {
                        complexAnswer.set(position,"B");
                    } else if (radioGroup.getCheckedRadioButtonId() == temp.rbChooseC.getId() && temp.rbChooseC.isPressed()) {
                        complexAnswer.set(position,"C");
                    } else if (radioGroup.getCheckedRadioButtonId() == temp.rbChooseD.getId() && temp.rbChooseD.isPressed()) {
                        complexAnswer.set(position,"D");
                    }
                }
            });

            return convertView;
        }else if(viewType.equals("FILL_BLANK")||viewType.equals("PROGRAM_FILL_BLANK")){
                final FillBlank fillBlank ;
                convertView = LayoutInflater.from(context).inflate(R.layout.item_fill_blank,null);
                fillBlank = new FillBlank();
                fillBlank.tvFillBlankAnswerA = (EditText)convertView.findViewById(R.id.tv_fill_blank_answer_a);
                fillBlank.tvFillBlankAnswerB = (EditText)convertView.findViewById(R.id.tv_fill_blank_answer_b);
                fillBlank.tvFillBlankAnswerC = (EditText)convertView.findViewById(R.id.tv_fill_blank_answer_c);
                fillBlank.tvFillBlankAnswerD = (EditText)convertView.findViewById(R.id.tv_fill_blank_answer_d);
                fillBlank.tvFillBlankTitle = (TextView)convertView.findViewById(R.id.tv_fill_blank_title);
                convertView.setTag(fillBlank);

            String[] fillBlankAnswera = complexAnswer.get(position).split("&&&");
            switch (test.getStrandanswer().split("&&&").length) {
                case 1:
                    fillBlank.tvFillBlankAnswerA.setVisibility(View.VISIBLE);
                    fillBlank.tvFillBlankAnswerB.setVisibility(View.GONE);
                    fillBlank.tvFillBlankAnswerC.setVisibility(View.GONE);
                    fillBlank.tvFillBlankAnswerD.setVisibility(View.GONE);
                    if(MiscUtils.isNotEmpty(answer)){
                        if(MiscUtils.isNotEmpty(fillBlankAnswera[0] ))
                        fillBlank.tvFillBlankAnswerA.setText(fillBlankAnswera[0]);
                    }

                    fillBlankAnswer = new String[1];
                    break;
                case 2:
                    fillBlank.tvFillBlankAnswerA.setVisibility(View.VISIBLE);
                    fillBlank.tvFillBlankAnswerB.setVisibility(View.VISIBLE);
                    fillBlank.tvFillBlankAnswerC.setVisibility(View.GONE);
                    fillBlank.tvFillBlankAnswerD.setVisibility(View.GONE);
                    if(MiscUtils.isNotEmpty(answer)){
                        if(MiscUtils.isNotEmpty(fillBlankAnswera[0] ))
                        fillBlank.tvFillBlankAnswerA.setText(fillBlankAnswera[0]);
                        if(MiscUtils.isNotEmpty(fillBlankAnswera[1] ))
                        fillBlank.tvFillBlankAnswerB.setText(fillBlankAnswera[1]);
                    }

                    fillBlankAnswer = new String[2];
                    break;
                case 3:
                    fillBlank.tvFillBlankAnswerA.setVisibility(View.VISIBLE);
                    fillBlank.tvFillBlankAnswerB.setVisibility(View.VISIBLE);
                    fillBlank.tvFillBlankAnswerC.setVisibility(View.VISIBLE);
                    fillBlank.tvFillBlankAnswerD.setVisibility(View.GONE);
                    if(MiscUtils.isNotEmpty(answer)){
                        if(MiscUtils.isNotEmpty(fillBlankAnswera[0] ))
                        fillBlank.tvFillBlankAnswerA.setText(fillBlankAnswera[0]);
                        if(MiscUtils.isNotEmpty(fillBlankAnswera[1] ))
                        fillBlank.tvFillBlankAnswerB.setText(fillBlankAnswera[1]);
                        if(MiscUtils.isNotEmpty(fillBlankAnswera[2] ))
                        fillBlank.tvFillBlankAnswerC.setText(fillBlankAnswera[2]);
                    }

                    fillBlankAnswer = new String[3];
                    break;
                case 4:
                    fillBlank.tvFillBlankAnswerA.setVisibility(View.VISIBLE);
                    fillBlank.tvFillBlankAnswerB.setVisibility(View.VISIBLE);
                    fillBlank.tvFillBlankAnswerC.setVisibility(View.VISIBLE);
                    fillBlank.tvFillBlankAnswerD.setVisibility(View.VISIBLE);
                    if(MiscUtils.isNotEmpty(answer)){
                        if(MiscUtils.isNotEmpty(fillBlankAnswera[0] ))
                        fillBlank.tvFillBlankAnswerA.setText(fillBlankAnswera[0]);
                        if(MiscUtils.isNotEmpty(fillBlankAnswera[1] ))
                        fillBlank.tvFillBlankAnswerB.setText(fillBlankAnswera[1]);
                        if(MiscUtils.isNotEmpty(fillBlankAnswera[2] ))
                        fillBlank.tvFillBlankAnswerC.setText(fillBlankAnswera[2]);
                        if(MiscUtils.isNotEmpty(fillBlankAnswera[3] ))
                        fillBlank.tvFillBlankAnswerD.setText(fillBlankAnswera[3]);
                    }
                    fillBlankAnswer = new String[4];
                    break;
            }

            fillBlank.tvFillBlankTitle.setText(Html.fromHtml(test.getContent(), imgGetter, null));
            fillBlank.tvFillBlankAnswerA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    fillBlankAnswer[0] = editable.toString();
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0 ; i < fillBlankAnswer.length ; i ++){
                        sb.append(fillBlankAnswer[i]);
                        if(i != (fillBlankAnswer.length-1)){
                            sb.append("&&&");
                        }
                    }
                    complexAnswer.set(position,sb.toString());
                }
            });
            fillBlank.tvFillBlankAnswerB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    fillBlankAnswer[1] = editable.toString();
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0 ; i < fillBlankAnswer.length ; i ++){
                        sb.append(fillBlankAnswer[i]);
                        if(i != (fillBlankAnswer.length-1)){
                            sb.append("&&&");
                        }
                    }
                    complexAnswer.set(position,sb.toString());
                }
            });
            fillBlank.tvFillBlankAnswerC.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    fillBlankAnswer[2] = editable.toString();
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0 ; i < fillBlankAnswer.length ; i ++){
                        sb.append(fillBlankAnswer[i]);
                        if(i != (fillBlankAnswer.length-1)){
                            sb.append("&&&");
                        }
                    }
                    complexAnswer.set(position,sb.toString());
                }
            });
            fillBlank.tvFillBlankAnswerD.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    fillBlankAnswer[3] = editable.toString();
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0 ; i < fillBlankAnswer.length ; i ++){
                        sb.append(fillBlankAnswer[i]);
                        if(i != (fillBlankAnswer.length-1)){
                            sb.append("&&&");
                        }
                    }
                    complexAnswer.set(position,sb.toString());
                }
            });
            return  convertView;
        }else if(viewType.equals("DESIGN")||viewType.equals("PROGRAM_CORRECT")||viewType.equals("PROGRAM_DESIGN")
                ||viewType.equals("DOC_DESIGN")){
            QA qa ;
            qa = new QA();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_other_question,null);
            qa.actionInsertImage = (ImageButton)convertView.findViewById(R.id.action_insert_image);
            qa.actionInsertLink = (ImageButton)convertView.findViewById(R.id.action_insert_link);
            qa.actionRedo = (ImageButton)convertView.findViewById(R.id.action_redo);
            qa.actionUndo = (ImageButton)convertView.findViewById(R.id.action_undo);
            qa.reEditor = (RichEditor)convertView.findViewById(R.id.re_editor);
            qa.tvQuestionAnswerTitle = (TextView)convertView.findViewById(R.id.tv_question_answer_title);
            convertView.setTag(qa);
            qa.tvQuestionAnswerTitle.setText(Html.fromHtml(test.getContent(), imgGetter, null));
            qa.actionUndo.setOnClickListener(clickListener);
            qa.actionRedo.setOnClickListener(clickListener);
            qa.actionInsertImage.setOnClickListener(clickListener);
            if(MiscUtils.isNotEmpty(answer)){
                qa.reEditor.setHtml(complexAnswer.get(position));
            }
            qa.reEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
                @Override
                public void onTextChange(String s) {
                    complexAnswer.set(position,s);
                }
            });
            return convertView;
        }else if(viewType.equals("MULIT_CHIOCE")){
            MultiChoice view ;
            view = new MultiChoice();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_multi_choice,null);
            view.rbChooseA = (CheckBox)convertView.findViewById(R.id.rb_choose_a);
            view.rbChooseB = (CheckBox)convertView.findViewById(R.id.rb_choose_b);
            view.rbChooseC = (CheckBox)convertView.findViewById(R.id.rb_choose_c);
            view.rbChooseD = (CheckBox)convertView.findViewById(R.id.rb_choose_d);
            view.rbChooseE = (CheckBox)convertView.findViewById(R.id.rb_choose_e);
            view.tvTestContent = (TextView)convertView.findViewById(R.id.tv_test_content);
            convertView.setTag(view);
            view.tvTestContent.setText(Html.fromHtml(test.getContent()));

            if(MiscUtils.isNotEmpty(answer)){
                Log.e("answer"," "+ answer);
                List<String> mans = new ArrayList<String>();
                for (int i= 0 ; i < complexAnswer.get(position).split("&&&").length;i++){
                    if(MiscUtils.isNotEmpty(complexAnswer.get(position).split("&&&")[i])){
                        mans.add(complexAnswer.get(position).split("&&&")[i]);
                    }
                }
                if(mans.contains("A")){
                    view.rbChooseA.setChecked(true);
                }
                if(mans.contains("B")){
                    view.rbChooseB.setChecked(true);
                }
                if(mans.contains("C")){
                    view.rbChooseC.setChecked(true);
                }
                if(mans.contains("D")){
                    view.rbChooseD.setChecked(true);
                }
            }


            if (MiscUtils.isNotEmpty(test.getOptiona())) {
                view.rbChooseA.setVisibility(View.VISIBLE);
                view.rbChooseA.setText(Html.fromHtml(test.getOptiona()));
            }
            if (MiscUtils.isNotEmpty(test.getOptionb())) {
                view.rbChooseB.setVisibility(View.VISIBLE);
                view.rbChooseB.setText(Html.fromHtml(test.getOptionb()));
            }
            if (MiscUtils.isNotEmpty(test.getOptionc())) {
                view.rbChooseC.setVisibility(View.VISIBLE);
                view.rbChooseC.setText(Html.fromHtml(test.getOptionc()));
            }
            if (MiscUtils.isNotEmpty(test.getOptiond())) {
                view.rbChooseD.setVisibility(View.VISIBLE);
                view.rbChooseD.setText(Html.fromHtml(test.getOptiond()));
            }

            view.rbChooseA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        multiAnswer.add("A");
                    }else{
                        if(multiAnswer.contains("A"))
                            multiAnswer.remove("A");
                    }
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0 ; i < multiAnswer.size(); i++){
                        sb.append(multiAnswer.get(i));
                        if(i != (multiAnswer.size()-1))
                            sb.append("&&&");
                    }
                    complexAnswer.set(position,sb.toString());
                }
            });
            view.rbChooseB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        multiAnswer.add("B");
                    }else{
                        if(multiAnswer.contains("B"))
                            multiAnswer.remove("B");
                    }
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0 ; i < multiAnswer.size(); i++){
                        sb.append(multiAnswer.get(i));
                        if(i != (multiAnswer.size()-1))
                            sb.append("&&&");
                    }

                    complexAnswer.set(position,sb.toString());
                }
            });
            view.rbChooseC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        multiAnswer.add("C");
                    }else{
                        if(multiAnswer.contains("C"))
                            multiAnswer.remove("C");
                    }
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0 ; i < multiAnswer.size(); i++){
                        sb.append(multiAnswer.get(i));
                        if(i != (multiAnswer.size()-1))
                            sb.append("&&&");
                    }
                    complexAnswer.set(position,sb.toString());
                }
            });
            view.rbChooseD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        multiAnswer.add("D");
                    }else{
                        if(multiAnswer.contains("D"))
                            multiAnswer.remove("D");
                    }
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0 ; i < multiAnswer.size(); i++){
                        sb.append(multiAnswer.get(i));
                        if(i != (multiAnswer.size()-1))
                            sb.append("&&&");
                    }
                    complexAnswer.set(position,sb.toString());
                }
            });
            return convertView;
        }else{
            throw  new RuntimeException();
        }
    }
    final class Choice{
        TextView tvTestContent;
        RadioButton rbChooseA;
        RadioButton rbChooseB;
        RadioButton rbChooseC;
        RadioButton rbChooseD;
        RadioButton rbChooseE;
        RadioGroup rgAnswer;
    }
    final class FillBlank{
        TextView tvFillBlankTitle;
        EditText tvFillBlankAnswerA;
        EditText tvFillBlankAnswerB;
        EditText tvFillBlankAnswerC;
        EditText tvFillBlankAnswerD;
    }
    final class QA{
        TextView tvQuestionAnswerTitle;
        ImageButton actionUndo;
        ImageButton actionRedo;
        ImageButton actionInsertImage;
        ImageButton actionInsertLink;
        RichEditor reEditor;
    }
    final class MultiChoice{
        TextView tvTestContent;
        CheckBox rbChooseA;
        CheckBox rbChooseB;
        CheckBox rbChooseC;
        CheckBox rbChooseD;
        CheckBox rbChooseE;
    }

    public List<String> getComplexAnswer() {
        return complexAnswer;
    }

    public void setComplexAnswer(List<String> complexAnswer) {
        this.complexAnswer = complexAnswer;
    }
    private List<String> getAnswer(String answer){
        List<String> ans = new ArrayList<String>();
        String[] array = answer.split("~~~");
        for(int i = 0 ; i < array.length;i++){
                ans.add(array[i]);
        }
        return  ans;
    }
}
