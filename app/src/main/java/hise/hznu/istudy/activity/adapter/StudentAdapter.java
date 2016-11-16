package hise.hznu.istudy.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hise.hznu.istudy.R;
import hise.hznu.istudy.model.email.ContacterEntity;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.widget.CircleImageView;
import hise.hznu.istudy.widget.MyListView;

/**
 * Created by PC on 2016/9/21.
 */
public class StudentAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ContacterEntity.ContacterList> _dataList = new ArrayList<ContacterEntity.ContacterList>();
    private StudentCallBack callBack;
    public StudentAdapter(Context context,StudentCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }
    interface StudentCallBack{
        void CallBack(String name,String id,boolean add);
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

    public void UpdateView(List<ContacterEntity.ContacterList> newList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_student, null);
            view.cbChoose = (CheckBox) convertView.findViewById(R.id.cb_choose);
            view.ivPhoto = (CircleImageView) convertView.findViewById(R.id.civ_photo);
            view.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            view.tvChar = (TextView)convertView.findViewById(R.id.tv_char);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.tvName.setText(_dataList.get(position).getName());
        ImageLoaderUtils.getImageLoader().displayImage(_dataList.get(position).getFace(),view.ivPhoto);
        view.cbChoose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    callBack.CallBack(_dataList.get(position).getName(),_dataList.get(position).getId(),true);
                }else{
                    callBack.CallBack(_dataList.get(position).getName(),_dataList.get(position).getId(),false);
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        CheckBox cbChoose;
        CircleImageView ivPhoto;
        TextView tvName;
        TextView tvChar;
    }
}
