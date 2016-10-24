package hise.hznu.istudy.widget;

/**
 * Created by PC on 2016/10/24.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.Base64;

import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.mine.PersonInfoActivity;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;

/**
 * Created by GuisenHan on 2016/8/5.
 * @desctiption 选择性别的dialog
 */
public class SexDialog extends AlertDialog{
    private RadioGroup rg_sex;
    private RadioButton rb_man;
    private RadioButton rb_woman;
    private int sex;
    private PersonInfoActivity context;
    private OnChooseSex chooseSex;
    public interface OnChooseSex{
        void callBack(int sex);
    }
    JSONObject params = new JSONObject();
    public SexDialog(Context context, int sex, PersonInfoActivity activity,OnChooseSex chooseSex) {
        super(context);

        View view= View.inflate(context, R.layout.dialog_choose_sex,null);
        rb_man = (RadioButton)view.findViewById(R.id.rb_man);
        rb_woman = (RadioButton)view.findViewById(R.id.rb_woman);
        rg_sex = (RadioGroup)view.findViewById(R.id.rg_sex);
        this.setView(view);
        this.sex=sex;
        this.context = activity;
        this.chooseSex = chooseSex;
        init();
    }
    private  void init(){
        if(sex == 1){
            rb_man.setChecked(true);
            rb_woman.setChecked(false);
        }else if(sex == 0){
            rb_man.setChecked(false);
            rb_woman.setChecked(true);
        }
        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_man){
                    sex = 1;
                    params.put("gender","男");
                }else if(checkedId == R.id.rb_woman){
                    sex =0;
                    params.put("gender","女");
                }
                JSONObject temp = new JSONObject();
                temp.put("data",new String(Base64.encode(JSONObject.toJSONString(params).getBytes(),Base64.DEFAULT)));
                RequestManager.getmInstance().apiPostData(AppConstant.SAVE_PERSON_INFO,temp,context,AppConstant
                        .POST_SAVE_PERSON_INFO);
            }
        });
    }


}

