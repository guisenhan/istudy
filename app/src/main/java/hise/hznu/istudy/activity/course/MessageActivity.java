package hise.hznu.istudy.activity.course;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.api.ApiResponse;
import hise.hznu.istudy.api.RequestManager;
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.base.BaseActivity;
import hise.hznu.istudy.model.course.NotifyEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.util.MiscUtils;

public class MessageActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_date)
    TextView tvDate;
    private String messageId;
    private NotifyEntity notify;
    @Override
    protected void initData() {
        super.initData();
        notify = new NotifyEntity();
        JSONObject params = new JSONObject();
        params.put("id",messageId);
        RequestManager.getmInstance().apiPostData(AppConstant.GET_NOTIFY_INFO_ACTION,params,this,AppConstant
                .POST_GET_NOTIFY_INFO);
    }

    @Override
    protected void initExtras(Bundle extras) {
        super.initExtras(extras);
        messageId = extras.getString("messageId");
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

    @Override
    public void onApiresponseSuccess(ApiResponse response, int actionId) {
        super.onApiresponseSuccess(response, actionId);
        notify = response.getInfo(NotifyEntity.class);
        setContent();
    }
    private void setContent(){
        if(MiscUtils.isNotEmpty(notify.getAuthor()))
            tvAuthor.setText(notify.getAuthor());
        if(MiscUtils.isNotEmpty(notify.getContent()))
            tvContent.setText(Html.fromHtml(notify.getContent(),imgGetter,null));
        if(MiscUtils.isNotEmpty(notify.getTitle()))
            tvTitle.setText(notify.getTitle());
        if(MiscUtils.isNotEmpty(notify.getDate()))
            tvDate.setText(AppUtils.dateFormat(notify.getDate()));
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
                    (drawable.getIntrinsicHeight() * MiscUtils.getScreenWidth()) / drawable.getIntrinsicWidth());
            return drawable;
        }
    };
    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
