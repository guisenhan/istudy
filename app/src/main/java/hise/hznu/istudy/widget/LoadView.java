package hise.hznu.istudy.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import hise.hznu.istudy.R;


/**
 * Created by Jacky on 15/10/29.
 */
public class LoadView extends FrameLayout {

    private FrameLayout loadingLayout;
    private FrameLayout netErrorLayout;
    private FrameLayout noDataLayout;

    private ImageView loadingImage;
    private TextView loadingViewText;
    private TextView noDataText;
    private LoadViewStatus currentLoadViewStatus = LoadViewStatus.NONE;

    private OnLoadViewListener onLoadViewListener;

    public LoadView(Context context) {
        super(context);
        init();
    }

    public LoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_load, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        loadingLayout = (FrameLayout) findViewById(R.id.ll_load_loading);
        loadingLayout.setVisibility(GONE);
        netErrorLayout = (FrameLayout) findViewById(R.id.ll_load_net_error);
        netErrorLayout.setVisibility(GONE);
        noDataLayout = (FrameLayout) findViewById(R.id.ll_load_no_data);
        noDataLayout.setVisibility(GONE);

//        loadingImage = (ImageView)findViewById(R.id.loading_image);
        loadingViewText = (TextView) findViewById(R.id.loading_view_text);
        noDataText = (TextView)findViewById(R.id.loading_view_no_data_text);
    }

    public void showLoading() {
        if (loadingLayout != null) {
            loadingLayout.setVisibility(VISIBLE);
//            AnimationDrawable animationDrawable = (AnimationDrawable)loadingImage.getDrawable();
//            animationDrawable.start();
        }
        if (netErrorLayout != null) {
            netErrorLayout.setVisibility(GONE);
            netErrorLayout.setOnClickListener(null);
        }
        if (noDataLayout != null) {
            noDataLayout.setVisibility(GONE);
        }
        currentLoadViewStatus = LoadViewStatus.LOADING;
    }

    public void showNetError() {
        if (loadingLayout != null) {
            loadingLayout.setVisibility(GONE);
//            AnimationDrawable animationDrawable = (AnimationDrawable)loadingImage.getDrawable();
//            animationDrawable.stop();
        }
        if (netErrorLayout != null) {
            netErrorLayout.setVisibility(VISIBLE);
            netErrorLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentLoadViewStatus.equals(LoadViewStatus.NET_ERROR) && onLoadViewListener != null) {
                        onLoadViewListener.onNetErrorReload();
                    }
                }
            });
        }
        if (noDataLayout != null) {
            noDataLayout.setVisibility(GONE);
        }
        currentLoadViewStatus = LoadViewStatus.NET_ERROR;
    }
    public void setNoDataText(String str){
        noDataText.setText(str);
    }
    public void showNoData() {
        if (loadingLayout != null) {
            loadingLayout.setVisibility(GONE);
//            AnimationDrawable animationDrawable = (AnimationDrawable)loadingImage.getDrawable();
//            animationDrawable.stop();
        }
        if (netErrorLayout != null) {
            netErrorLayout.setVisibility(GONE);
            netErrorLayout.setOnClickListener(null);
        }
        if (noDataLayout != null) {
            noDataLayout.setVisibility(VISIBLE);
        }
        currentLoadViewStatus = LoadViewStatus.NO_DATA;
    }

    public void clearLoad() {
        if (loadingLayout != null) {
            loadingLayout.setVisibility(GONE);
//            AnimationDrawable animationDrawable = (AnimationDrawable)loadingImage.getDrawable();
//            animationDrawable.stop();
        }
        if (netErrorLayout != null) {
            netErrorLayout.setVisibility(GONE);
        }
        if (noDataLayout != null) {
            noDataLayout.setVisibility(GONE);
        }
    }
    public void showTransparentLoading() {
        if (loadingLayout != null) {
            loadingLayout.setClickable(false);
            loadingLayout.setVisibility(VISIBLE);
            loadingLayout.setBackgroundColor(ContextCompat.getColor(getContext(),android.R.color.transparent));
//            AnimationDrawable animationDrawable = (AnimationDrawable)loadingImage.getDrawable();
//            animationDrawable.start();
        }
        if (netErrorLayout != null) {
            netErrorLayout.setVisibility(GONE);
            netErrorLayout.setOnClickListener(null);
        }
        if (noDataLayout != null) {
            noDataLayout.setVisibility(GONE);
        }
        currentLoadViewStatus = LoadViewStatus.LOADING;
    }

    public void setLoadingViewText(String text) {
        if (loadingViewText != null) {
            loadingViewText.setText(text);
        }
    }

    public void setLoadingViewText(int resid) {
        if (loadingViewText != null) {
            loadingViewText.setText(resid);
        }
    }

    public void setLoadingViewText(CharSequence text) {
        if (loadingViewText != null) {
            loadingViewText.setText(text);
        }
    }

    public void setOnLoadViewListener(OnLoadViewListener onLoadViewListener) {
        this.onLoadViewListener = onLoadViewListener;
    }

    private enum LoadViewStatus {
        NONE, LOADING, NET_ERROR, NO_DATA
    }

    public interface OnLoadViewListener {
        void onNetErrorReload();
    }
}
