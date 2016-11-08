package hise.hznu.istudy.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.course.TestDetailActivity;
import hise.hznu.istudy.model.course.CommentTaskEntity;
import hise.hznu.istudy.model.course.HomeWorkEntity;
import hise.hznu.istudy.model.course.SubQuestionEntity;

/**
 * Created by PC on 2016/9/21.
 */
public class ComplexQuestionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SubQuestionEntity> _dataList = new ArrayList<SubQuestionEntity>();

    public static final int JUDGE = 1;//判断题
    public static final int SINGLE_CHOICE=2; //单选题
    public static final int  MULIT_CHIOCE = 3;//多选题
    public static final int FILL_BLANK = 4;//填空题
    public static final int DESIGN = 5;//设计题
    public static final int COMPLEX = 6;//组合题
    public static final int PROGRAM_FILL_BLANK = 7;//程序填空题
    public static final int PROGRAM_CORRECT = 8;//程序改错题
    public static final int PROGRAM_DESIGN = 9;//程序设计题
    public static final int DOC_DESIGN = 10;//文档设计题

    public ComplexQuestionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
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

    @Override
    public int getItemViewType(int position) {
        return TestDetailActivity.type.get(_dataList.get(position).getType());
    }

    public void UpdateView(List<SubQuestionEntity> newList) {

        if (_dataList != null) {
            _dataList.clear();
            this.notifyDataSetChanged();
        }
        _dataList.addAll(newList);
        this.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case JUDGE:
            case SINGLE_CHOICE:
            case MULIT_CHIOCE:

                return convertView;
            case FILL_BLANK:
                return  convertView;
            case DESIGN:
                return  convertView;
            case COMPLEX:
                return  convertView;
            case PROGRAM_FILL_BLANK:
                return  convertView;
            case PROGRAM_CORRECT:
                return  convertView;
            case PROGRAM_DESIGN:
                return  convertView;
            case DOC_DESIGN:
                return convertView;
        }

        return convertView;
    }

}
