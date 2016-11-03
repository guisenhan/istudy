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
import hise.hznu.istudy.model.exam.ExamEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.MiscUtils;

/**
 * Created by PC on 2016/9/21.
 */
public class ExamAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ExamEntity> _dataList = new ArrayList<ExamEntity>();

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

    public void UpdateView(List<ExamEntity> newList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_exam, null);
            view.tvExamName = (TextView) convertView.findViewById(R.id.tv_exam_name);
            view.tvTeacherName = (TextView) convertView.findViewById(R.id.tv_teacher_name);
            view.tvDate = (TextView) convertView.findViewById(R.id.tv_date);
            view.tvLocation = (TextView) convertView.findViewById(R.id.tv_location);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
       view.tvDate.setText("开始时间："+AppUtils.dateFormat(_dataList.get(position).getDatestart()));
        if(MiscUtils.isNotEmpty(_dataList.get(position).getKsdd() )&&MiscUtils.isNotEmpty( _dataList.get(position).getKszw()))
        view.tvLocation.setText("考试地点："+_dataList.get(position).getKsdd()+_dataList.get(position).getKszw());
        view.tvExamName.setText(_dataList.get(position).getTitle());
        view.tvTeacherName.setText(_dataList.get(position).getTeacher());
        return convertView;
    }


    final class ViewHolder {
        TextView tvExamName;
        TextView tvDate;
        TextView tvLocation;
        TextView tvTeacherName;
    }
}
