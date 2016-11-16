package hise.hznu.istudy.activity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hise.hznu.istudy.R;
import hise.hznu.istudy.model.course.CommentTaskEntity;
import hise.hznu.istudy.model.course.HomeWorkEntity;
import hise.hznu.istudy.model.email.ContacterEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.widget.MyListView;

/**
 * Created by PC on 2016/9/21.
 */
public class ContactorAdapter extends BaseAdapter implements StudentAdapter.StudentCallBack{

    private Context context;
    private ArrayList<ContacterEntity> _dataList = new ArrayList<ContacterEntity>();
    private StudentAdapter adapter;
    private List<String> chooseId;
    private List<String> names;
    public ContactorAdapter(Context context) {
        this.context = context;
        adapter = new StudentAdapter(context,this);
        chooseId = new ArrayList<String>();
        names = new ArrayList<String>();
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

    public void UpdateView(List<ContacterEntity> newList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_contact, null);
            view.tvClass = (TextView) convertView.findViewById(R.id.tv_class);
            view.ivArrow = (ImageView) convertView.findViewById(R.id.iv_arrow);
            view.lvStudent = (MyListView) convertView.findViewById(R.id.lv_student);
            view.divider = (View)convertView.findViewById(R.id.divider);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.tvClass.setText(_dataList.get(position).getLabel());

        view.lvStudent.setAdapter(adapter);
        adapter.UpdateView(_dataList.get(position).getContacterList());
        final MyListView listView = view.lvStudent;
        final ImageView img =view.ivArrow;
        final View di = view.divider;
        view.ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listView.getVisibility() ==View.VISIBLE){
                    listView.setVisibility(View.GONE);
                    img.setImageResource(R.mipmap.icon_down);
                    di.setVisibility(View.GONE);
                }else if(listView.getVisibility() ==View.GONE){
                    listView.setVisibility(View.VISIBLE);
                    img.setImageResource(R.mipmap.icon_right_arrow);
                    di.setVisibility(View.VISIBLE);
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView tvClass;
        ImageView ivArrow;
        MyListView lvStudent;
        View divider;
    }

    @Override
    public void CallBack(String name,String id, boolean add) {
        if(add){
            if(!names.contains(name))
                names.add(name);
            if(!chooseId.contains(id))
                chooseId.add(id);
        }else{
            if(names.contains(name))
                names.remove(name);
            if(chooseId.contains(id)){
                chooseId.remove(id);
            }
        }
    }

    public List<String> getChooseId() {
        return chooseId;
    }

    public void setChooseId(List<String> chooseId) {
        this.chooseId = chooseId;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
