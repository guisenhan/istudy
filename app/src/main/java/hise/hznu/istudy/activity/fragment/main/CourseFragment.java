package hise.hznu.istudy.activity.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hise.hznu.istudy.R;
import hise.hznu.istudy.base.BaseFragment;

/**
 * Created by Guisen Han on 2016/7/25.
 */
public class CourseFragment extends BaseFragment {
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.lv_course)
    ListView lvCourse;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = inflater.inflate(R.layout.fragment_course,container,false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.iv_search)
    public void onClick() {

    }
}
