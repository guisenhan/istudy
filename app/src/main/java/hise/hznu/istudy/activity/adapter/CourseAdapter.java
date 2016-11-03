package hise.hznu.istudy.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.course.StudyActivity;
import hise.hznu.istudy.model.course.CourseEntity;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.util.MiscUtils;

/**
 * Created by PC on 2016/9/21.
 */
public class CourseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CourseEntity> _dataList = new ArrayList<CourseEntity>();

    public CourseAdapter(Context context) {
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
            view.tvCourseName = (TextView) convertView.findViewById(R.id.tv_course_name);
            view.tvTeacherName = (TextView) convertView.findViewById(R.id.tv_teacher_name);
            view.tvCourseDetail = (TextView) convertView.findViewById(R.id.tv_course_detail);
            view.tvPicTitle = (TextView)convertView.findViewById(R.id.tv_pic_title);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        if(MiscUtils.isNotEmpty(_dataList.get(position).getMemo()))
            view.tvCourseDetail.setText(Html.fromHtml(_dataList.get(position).getMemo()));
        view.tvCourseName.setText(_dataList.get(position).getTitle());
        view.tvTeacherName.setText("讲师:"+_dataList.get(position).getTeacher());
        view.tvPicTitle.setText(_dataList.get(position).getPictit().substring(0,3));
        List<Integer> argb = _dataList.get(position).getPicbg();
        view.tvPicTitle.setTextColor(Color.argb(255,argb.get(0),argb.get(1),argb.get(2)));
        return convertView;
    }

    final class ViewHolder {
        TextView tvCourseName;
        TextView tvTeacherName;
        TextView tvCourseDetail;
        TextView tvPicTitle;
    }
}
