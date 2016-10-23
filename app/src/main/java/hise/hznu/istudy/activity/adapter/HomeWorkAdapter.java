package hise.hznu.istudy.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.model.course.HomeWorkEntity;
import hise.hznu.istudy.util.AppUtils;

/**
 * Created by PC on 2016/9/21.
 */
public class HomeWorkAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<HomeWorkEntity> _dataList = new ArrayList<HomeWorkEntity>();

    public HomeWorkAdapter(Context context) {
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

    public void UpdateView(List<HomeWorkEntity> newList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_home_work, null);
            view.tvHomeworkName = (TextView) convertView.findViewById(R.id.tv_homework_name);
            view.tvHomeworkInfo = (TextView) convertView.findViewById(R.id.tv_homework_info);
            view.tvSeeDetail = (TextView) convertView.findViewById(R.id.tv_see_detail);
            view.tvScore = (TextView) convertView.findViewById(R.id.tv_score);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.tvHomeworkName.setText(_dataList.get(position).getTitle());
        view.tvHomeworkInfo.setText("老师："+_dataList.get(position).getTeacher()+"  开始时间："+ AppUtils.dateFormat(_dataList.get(position).getDatestart()) +"结束时间："+AppUtils.dateFormat(_dataList.get(position).getDateend()));
        if(System.currentTimeMillis()>AppUtils.DateFormat(_dataList.get(position).getDateend())){
            view.tvScore.setVisibility(View.VISIBLE);
            view.tvScore.setText("成绩："+_dataList.get(position).getMyscore());
        }else{
            view.tvScore.setVisibility(View.GONE);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tvHomeworkName;
        TextView tvHomeworkInfo;
        TextView tvSeeDetail;
        TextView tvScore;
     }
}
