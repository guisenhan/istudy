package hise.hznu.istudy.activity.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hise.hznu.istudy.R;
import hise.hznu.istudy.model.email.EmailEntity;

/**
 * Created by PC on 2016/9/21.
 */
public class EmailAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<EmailEntity> _dataList = new ArrayList<EmailEntity>();

    public EmailAdapter(Context context) {
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

    public void UpdateView(List<EmailEntity> newList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_email, null);
            view.tvRedLabel = (TextView) convertView.findViewById(R.id.tv_red_label);
            view.ivEmail = (ImageView)convertView.findViewById(R.id.iv_email);

            view.tvEmailSender = (TextView) convertView.findViewById(R.id.tv_email_sender);
            view.tvDate = (TextView) convertView.findViewById(R.id.tv_date);
            view.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.tvDate.setText(_dataList.get(position).getDate());
        view.tvContent.setText(_dataList.get(position).getSubject());
        view.tvEmailSender.setText(_dataList.get(position).getSendername());
        if(_dataList.get(position).isread()){
            view.ivEmail.setImageResource(R.mipmap.icon_email_readed);
        }else{
            view.ivEmail.setImageResource(R.mipmap.icon_emali_unread);
        }
        view.tvRedLabel.setVisibility(View.GONE);
        return convertView;
    }


    final class ViewHolder {
        TextView tvRedLabel;
        ImageView ivEmail;
        TextView tvEmailSender;
        TextView tvContent;
        TextView tvDate;

    }
}
