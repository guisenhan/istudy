package hise.hznu.istudy.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hise.hznu.istudy.R;
import hise.hznu.istudy.model.course.CourseDatumEntity;
import hise.hznu.istudy.model.course.CourseNoticeEntity;

/**
 * Created by PC on 2016/9/21.
 */
public class CourseDatumAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CourseDatumEntity> _dataList = new ArrayList<CourseDatumEntity>();

    public CourseDatumAdapter(Context context) {
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

    public void UpdateView(List<CourseDatumEntity> newList) {
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
        CourseDatumEntity course = _dataList.get(position);
        if (convertView == null) {
            view = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_study_datum, null);
            view.icon = (ImageView)convertView.findViewById(R.id.iv_datum_type) ;
            view.title  = (TextView)convertView.findViewById(R.id.tv_datum_name);
            view.info = (TextView)convertView.findViewById(R.id.tv_datum_info);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }

        if(course.getExtensions().equals("doc") || course.getExtensions().equals("docx")){
            view.icon.setImageResource(R.mipmap.icon_word);
        } else if(course.getExtensions().equals("pptx")){
            view.icon.setImageResource(R.mipmap.icon_ppt);
        } else if(course.getExtensions().equals("pdf")) {
            view.icon.setImageResource(R.mipmap.icon_pdf);
        }else if(course.getExtensions().equals("xls")){
            view.icon.setImageResource(R.mipmap.icon_excel);}
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        view.info.setText("类型："+course.getExtensions()+"创建时间："+simpleDateFormat.format(new Date(course.getDatecreated()))+"文件大小："+course.getFilesize());

        view.title.setText(_dataList.get(position).getFilename());
        return convertView;
    }

    final class ViewHolder {
        ImageView icon;
        TextView info;
        TextView title;
    }
}
