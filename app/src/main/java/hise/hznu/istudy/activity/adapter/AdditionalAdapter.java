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
import hise.hznu.istudy.model.FileEntity;
import hise.hznu.istudy.model.course.CommentTaskEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.MiscUtils;

/**
 * Created by PC on 2016/9/21.
 */
public class AdditionalAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<FileEntity> _dataList = new ArrayList<FileEntity>();

    public AdditionalAdapter(Context context) {
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

    public void UpdateView(List<FileEntity> newList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_addtional_file, null);
            view.tvFileName = (TextView) convertView.findViewById(R.id.tv_file_name);
            view.tvSize = (TextView) convertView.findViewById(R.id.tv_file_size);

            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        if(MiscUtils.isNotEmpty(_dataList.get(position).getName()))
            view.tvFileName.setText(_dataList.get(position).getName());
        if(MiscUtils.isNotEmpty(_dataList.get(position).getSize()));
            view.tvSize.setText(_dataList.get(position).getSize());
        return convertView;
    }

    static class ViewHolder {
        TextView tvFileName;
        TextView tvSize;
    }
}
