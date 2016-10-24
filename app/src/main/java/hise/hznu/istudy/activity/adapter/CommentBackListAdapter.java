package hise.hznu.istudy.activity.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hise.hznu.istudy.R;
import hise.hznu.istudy.model.course.CommentBackEntity;
import hise.hznu.istudy.model.course.CommentCardEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.widget.CircleImageView;

/**
 * Created by PC on 2016/9/21.
 */
public class CommentBackListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CommentBackEntity> _dataList = new ArrayList<CommentBackEntity>();

    public CommentBackListAdapter(Context context) {
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

    public void UpdateView(List<CommentBackEntity> newList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_comment_call_back, null);
            view.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            view.tvDate = (TextView) convertView.findViewById(R.id.tv_date);

            view.tvContent = (TextView)convertView.findViewById(R.id.tv_content);
            view.ivPhoto = (CircleImageView)convertView.findViewById(R.id.iv_photo);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        ImageLoaderUtils.getImageLoader().displayImage(_dataList.get(position).getAvatar_url(),view.ivPhoto);
        view.tvName.setText(_dataList.get(position).getAuthor());
        view.tvDate.setText(_dataList.get(position).getAuthor() + " 于" + AppUtils.dateFormat(_dataList.get
                (position)
                .getDate()) +"发表");
        view.tvContent.setText(Html.fromHtml(_dataList.get(position).getContent()));
        return convertView;
    }

    static class ViewHolder {
        TextView tvName;
        TextView tvDate;
        TextView tvContent;
        CircleImageView ivPhoto;
    }
}
