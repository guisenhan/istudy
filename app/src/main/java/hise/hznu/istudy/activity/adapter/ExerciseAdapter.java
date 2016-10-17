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
import hise.hznu.istudy.model.course.ExerciseEntity;
import hise.hznu.istudy.model.course.ExprementEntity;
import hise.hznu.istudy.util.AppUtils;

/**
 * Created by PC on 2016/9/21.
 */
public class ExerciseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ExerciseEntity> _dataList = new ArrayList<ExerciseEntity>();

    public ExerciseAdapter(Context context) {
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

    public void UpdateView(List<ExerciseEntity> newList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_exercise, null);
            view.tvHomeworkName = (TextView) convertView.findViewById(R.id.tv_homework_name);
            view.tvHomeworkInfo = (TextView) convertView.findViewById(R.id.tv_homework_info);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.tvHomeworkName.setText(_dataList.get(position).getTitle());
        view.tvHomeworkInfo.setText("老师："+_dataList.get(position).getTeacher()+"  开始时间："+ AppUtils.dateFormat(_dataList.get(position).getDatestart()) +" \n 结束时间："+AppUtils.dateFormat(_dataList.get(position).getDateend()));
        return convertView;
    }

    static class ViewHolder {
        TextView tvHomeworkName;
        TextView tvHomeworkInfo;
        TextView tvSeeDetail;

    }
}
