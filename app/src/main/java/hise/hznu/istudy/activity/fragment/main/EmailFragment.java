package hise.hznu.istudy.activity.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import hise.hznu.istudy.R;
import hise.hznu.istudy.base.BaseFragment;

/**
 * Created by GuisenHan on 2016/7/25.
 */
public class EmailFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_exam,container,false);
        return rootview;
    }
}
