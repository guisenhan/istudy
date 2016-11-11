package hise.hznu.istudy.activity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hise.hznu.istudy.R;
import hise.hznu.istudy.model.course.CommentTaskEntity;
import hise.hznu.istudy.model.course.CommetListEntity;

/**
 * Created by PC on 2016/9/21.
 */
public class CommentListAdapter extends BaseAdapter {

    private Context context;
    private List<CommetListEntity> _dataList = new ArrayList<CommetListEntity>();

    public CommentListAdapter(Context context) {
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

    public void UpdateView(List<CommetListEntity> newList) {
        if (_dataList != null) {
            _dataList.clear();
            this.notifyDataSetChanged();
        }
        _dataList.addAll(newList);
        Log.e("update",_dataList.size()+"");
        this.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view ;
        if (convertView == null) {
            view = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comment_list, null);
            view.tvNumber = (TextView)convertView.findViewById(R.id.tv_number);
            view.tvScore = (TextView)convertView.findViewById(R.id.tv_score);
            view.tvTester = (TextView)convertView.findViewById(R.id.tv_tester);
            view.tvTitle = (TextView)convertView.findViewById(R.id.tv_title);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        Log.e("String GetView", "getView");
        view.tvNumber.setText(""+position);
        view.tvTester.setText(_dataList.get(position).getTestername());
        view.tvScore.setText(_dataList.get(position).getScore());
        view.tvTitle.setText(_dataList.get(position).getTitle());
        //view.tvHomeworkInfo.setText(_dataList.get(position).getMemo() + _dataList.get(position).getTeacher());
        return convertView;
    }

    static class ViewHolder {
        TextView tvNumber;
        TextView tvTitle;
        TextView tvScore;
        TextView tvTester;
    }
}
