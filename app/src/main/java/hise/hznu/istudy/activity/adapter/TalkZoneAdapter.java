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
import hise.hznu.istudy.app.AppConstant;
import hise.hznu.istudy.model.course.CommentCardEntity;
import hise.hznu.istudy.model.course.CommentTaskEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.util.MiscUtils;
import hise.hznu.istudy.widget.CircleImageView;

/**
 * Created by PC on 2016/9/21.
 */
public class TalkZoneAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CommentCardEntity> _dataList = new ArrayList<CommentCardEntity>();

    public TalkZoneAdapter(Context context) {
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

    public void UpdateView(List<CommentCardEntity> newList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_taik_zone, null);
            view.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            view.tvDetail = (TextView) convertView.findViewById(R.id.tv_detail);
            view.isTop = (TextView) convertView.findViewById(R.id.tv_is_top);
            view.tvPrise = (TextView)convertView.findViewById(R.id.tv_prise_number);
            view.tvComment = (TextView)convertView.findViewById(R.id.tv_comment_number);
            view.ivPhoto = (CircleImageView)convertView.findViewById(R.id.iv_photo);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        if(_dataList.get(position).isTop()){
            view.isTop.setVisibility(View.VISIBLE);
            view.tvComment.setVisibility(View.GONE);
            view.tvPrise.setVisibility(View.GONE);
            view.ivPhoto.setVisibility(View.GONE);
        }else{
            view.isTop.setVisibility(View.GONE);
            view.tvComment.setVisibility(View.VISIBLE);
            view.tvPrise.setVisibility(View.GONE);
            view.ivPhoto.setVisibility(View.VISIBLE);
        }
        if(MiscUtils.isNotEmpty(_dataList.get(position).getAvatar_url()))
            ImageLoaderUtils.getImageLoader().displayImage(_dataList.get(position).getAvatar_url(),view.ivPhoto);
        view.tvName.setText(_dataList.get(position).getTitle());
        view.tvDetail.setText(_dataList.get(position).getAuthor() + " 于" + AppUtils.dateFormat(_dataList.get
                (position)
                .getDate()) +"发表");
        view.tvComment.setText(_dataList.get(position).getCommentcount()+"");
        view.tvPrise.setText(_dataList.get(position).getViewtimes()+"");
        return convertView;
    }

    static class ViewHolder {
        TextView tvName;
        TextView tvDetail;
        TextView isTop;
        TextView tvPrise;
        TextView tvComment;
        CircleImageView ivPhoto;
    }
}
