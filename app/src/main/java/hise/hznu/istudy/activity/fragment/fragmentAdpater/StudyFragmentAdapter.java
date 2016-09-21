package hise.hznu.istudy.activity.fragment.fragmentAdpater;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.fragment.main.CourseFragment;
import hise.hznu.istudy.activity.fragment.main.EmailFragment;
import hise.hznu.istudy.activity.fragment.main.ExamFragment;
import hise.hznu.istudy.activity.fragment.main.MineFragment;
import hise.hznu.istudy.activity.fragment.study.CourseInfoFragment;
import hise.hznu.istudy.activity.fragment.study.CourseMessageFragment;
import hise.hznu.istudy.activity.fragment.study.MyStudyFragment;
import hise.hznu.istudy.model.course.CourseEntity;
import hise.hznu.istudy.shizhefei.view.indicator.IndicatorViewPager;

/**
 * Created by GuisenHan on 2016/7/25.
 */
public class StudyFragmentAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter{
    private String[] names = {"我的学习","课程信息","消息通知"};
    private CourseEntity courseEntity;
    private LayoutInflater inflater;
    public StudyFragmentAdapter(Context context, FragmentManager fragmentManager, CourseEntity courseEntity) {
        super(fragmentManager);
        inflater = LayoutInflater.from(context);
        this.courseEntity = courseEntity;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if(convertView ==null){
            convertView = inflater.inflate(R.layout.tab_bottom,container,false);
        }
        TextView tab = (TextView)convertView;
        tab.setText(names[position]);
        return convertView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        Fragment fragment = new Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("course",courseEntity);
        switch (position){
            case 0:
                fragment = new MyStudyFragment();
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new CourseInfoFragment();
                fragment.setArguments(bundle);
                break;
            case 2:
                fragment = new CourseMessageFragment();
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return names.length;
    }
}
