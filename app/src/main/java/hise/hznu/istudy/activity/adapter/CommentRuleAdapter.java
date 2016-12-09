package hise.hznu.istudy.activity.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hise.hznu.istudy.R;
import hise.hznu.istudy.model.course.CommentBackEntity;
import hise.hznu.istudy.model.course.CommentRuleEntity;
import hise.hznu.istudy.util.AppUtils;
import hise.hznu.istudy.util.ImageLoaderUtils;
import hise.hznu.istudy.util.MiscUtils;
import hise.hznu.istudy.widget.CircleImageView;

/**
 * Created by PC on 2016/9/21.
 */
public class CommentRuleAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<CommentRuleEntity> _dataList = new ArrayList<CommentRuleEntity>();

    public CommentRuleAdapter(Context context) {
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

    public void UpdateView(List<CommentRuleEntity> newList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comment_rule, null);
            view.tvTitle = (TextView) convertView.findViewById(R.id.tv_rule);
            view.ed_score = (EditText) convertView.findViewById(R.id.ed_score);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.tvTitle.setText("("+position+")."+_dataList.get(position).getContents());
        if(MiscUtils.isNotEmpty(_dataList.get(position).getScore())){
            view.ed_score.setText(_dataList.get(position).getScore());
        }
        view.ed_score.setHint("输入的分数不能超过"+_dataList.get(position).getTotalscore());
        final EditText editText = view.ed_score;
        view.ed_score.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(MiscUtils.isNotEmpty(editable.toString())){
                    if(Integer.valueOf(_dataList.get(position).getTotalscore())<Integer.valueOf(editable.toString())){
                        editText.setText(_dataList.get(position).getTotalscore());
                        _dataList.get(position).setScore(editText.getText().toString());
                    }
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView tvTitle;
        EditText ed_score;
    }

    public ArrayList<CommentRuleEntity> get_dataList() {
        return _dataList;
    }

    public void set_dataList(ArrayList<CommentRuleEntity> _dataList) {
        this._dataList = _dataList;
    }
}
