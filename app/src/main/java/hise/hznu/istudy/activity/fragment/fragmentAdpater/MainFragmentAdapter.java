package hise.hznu.istudy.activity.fragment.fragmentAdpater;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.zip.Inflater;

import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.fragment.main.CourseFragment;
import hise.hznu.istudy.activity.fragment.main.EmailFragment;
import hise.hznu.istudy.activity.fragment.main.ExamFragment;
import hise.hznu.istudy.activity.fragment.main.MineFragment;


/**
 * Created by GuisenHan on 2016/7/25.
 */
public class MainFragmentAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter{
    private String[] names = {"我的课程","我的考试","站内信","个人信息"};
    private int[] icons ={R.drawable.tab_my_course,R.drawable.tab_my_exam,R.drawable.tab_indoor_email,R.drawable.tab_indoor_email};
    private LayoutInflater inflater;
    public MainFragmentAdapter(Context context,FragmentManager fragmentManager) {
        super(fragmentManager);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if(convertView ==null){
            convertView = inflater.inflate(R.layout.tab_bottom,container,false);
        }
        TextView tab = (TextView)convertView;
        tab.setText(names[position]);
        tab.setCompoundDrawablesWithIntrinsicBounds(0,icons[position],0,0);
        return convertView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        Fragment fragment = new Fragment();
        switch (position){
            case 0:
               CourseFragment course = new CourseFragment();
                fragment = course;
                break;
            case 1:
                fragment = new ExamFragment();
                break;
            case 2:
                fragment = new EmailFragment();
                break;
            case 3:
                fragment = new MineFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return names.length;
    }
}
