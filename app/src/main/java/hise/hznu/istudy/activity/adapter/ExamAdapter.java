package hise.hznu.istudy.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.course.StudyActivity;
import hise.hznu.istudy.model.course.CourseEntity;

/**
 * Created by PC on 2016/9/21.
 */
public class ExamAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CourseEntity> _dataList = new ArrayList<CourseEntity>();

    public ExamAdapter(Context context) {
        this.context = context;
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

    public void UpdateView(List<CourseEntity> newList) {
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
        ViewHolder view = null;
        if (convertView == null) {
            view = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_course, null);
            view.rlIcon = (RelativeLayout) convertView.findViewById(R.id.rl_icon);
            view.tvCourseName = (TextView) convertView.findViewById(R.id.tv_course_name);
            view.tvTeacherName = (TextView) convertView.findViewById(R.id.tv_teacher_name);
            view.tvStudy = (TextView) convertView.findViewById(R.id.tv_study);
            view.rlCourse = (RelativeLayout) convertView.findViewById(R.id.rl_course);
            view.tvCourseDetail = (TextView) convertView.findViewById(R.id.tv_course_detail);
            view.ivArrow =(ImageView)convertView.findViewById(R.id.iv_arrow);
            view.tvPicTitle = (TextView)convertView.findViewById(R.id.tv_pic_title);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        final ViewHolder viewHolder = view;
       view.ivArrow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(viewHolder.tvCourseDetail.getVisibility()==View.VISIBLE){
                   viewHolder.tvCourseDetail.setVisibility(View.GONE);
                   viewHolder.ivArrow.setImageResource(R.mipmap.icon_arrow_down);
               }else{
                   viewHolder.tvCourseDetail.setVisibility(View.VISIBLE);
                   viewHolder.ivArrow.setImageResource(R.mipmap.icon_arrow_up);
               }
           }
       });
        view.tvCourseDetail.setText(_dataList.get(position).getMemo());
        view.tvCourseName.setText(_dataList.get(position).getTitle());
        view.tvTeacherName.setText(_dataList.get(position).getTeacher());
        view.tvPicTitle.setText(_dataList.get(position).getPictit());
        List<Integer> argb = _dataList.get(position).getPicbg();
        view.tvPicTitle.setBackgroundColor(Color.argb(255,argb.get(0),argb.get(1),argb.get(2)));
        view.tvStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent study = new Intent(context, StudyActivity.class);
                study.putExtra("course",_dataList.get(position));
                context.startActivity(study);
            }
        });
        return convertView;
    }

    final class ViewHolder {
        RelativeLayout rlIcon;
        TextView tvCourseName;
        TextView tvTeacherName;
        TextView tvStudy;
        RelativeLayout rlCourse;
        TextView tvCourseDetail;
        ImageView ivArrow;
        TextView tvPicTitle;
    }
}
