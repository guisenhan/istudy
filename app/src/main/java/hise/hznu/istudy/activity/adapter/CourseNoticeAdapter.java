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

import java.io.PipedOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hise.hznu.istudy.R;
import hise.hznu.istudy.activity.course.StudyActivity;
import hise.hznu.istudy.model.course.CourseEntity;
import hise.hznu.istudy.model.course.CourseNoticeEntity;

/**
 * Created by PC on 2016/9/21.
 */
public class CourseNoticeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CourseNoticeEntity> _dataList = new ArrayList<CourseNoticeEntity>();

    public CourseNoticeAdapter(Context context) {
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

    public void UpdateView(List<CourseNoticeEntity> newList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_course_notice, null);
            view.title  = (TextView)convertView.findViewById(R.id.tv_title);
            view.date = (TextView)convertView.findViewById(R.id.tv_date);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }

        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sim.parse(_dataList.get(position).getDate());

            view.date.setText("发布日期："+sim1.format(date));
        }catch (ParseException e){
            e.getMessage();
        }

        view.title.setText(_dataList.get(position).getTitle());
        return convertView;
    }

    final class ViewHolder {
        TextView date;
        TextView title;
    }
}
