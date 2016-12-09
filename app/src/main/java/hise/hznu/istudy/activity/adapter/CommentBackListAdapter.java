package hise.hznu.istudy.activity.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import hise.hznu.istudy.R;
import hise.hznu.istudy.model.course.CommentBackEntity;
import hise.hznu.istudy.model.course.CommentCardEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.util.MiscUtils;
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
        if(MiscUtils.isNotEmpty(_dataList.get(position).getAvatar_url()))
            ImageLoaderUtils.getImageLoader().displayImage(_dataList.get(position).getAvatar_url(),view.ivPhoto);
        view.tvName.setText(_dataList.get(position).getAuthor());
        view.tvDate.setText(_dataList.get(position).getAuthor() + " 于" + AppUtils.dateFormat(_dataList.get
                (position)
                .getDate()) +"发表");
        Html.ImageGetter imgGetter = new Html.ImageGetter() {
            public Drawable getDrawable(String source) {
                Drawable drawable = null;
                URL url;
                try {
                    url = new URL(source);
                    drawable =new BitmapDrawable(ImageLoaderUtils.getImageLoader().loadImageSync(url.toString())); // 获取网路图片
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                drawable.setBounds(0, 0, MiscUtils.getScreenWidth(),
                        (drawable.getIntrinsicHeight() * MiscUtils.getScreenWidth()) / drawable.getIntrinsicWidth());
                return drawable;
            }
        };
        view.tvContent.setText(Html.fromHtml(_dataList.get(position).getContent(),imgGetter,null));
        return convertView;
    }

    static class ViewHolder {
        TextView tvName;
        TextView tvDate;
        TextView tvContent;
        CircleImageView ivPhoto;
    }
}
