package lanou.carhome.recommedfragment.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.carhome.R;
import lanou.carhome.find.dbtool.SearchCarNameBean;

/**
 * Created by dllo on 16/10/10.
 */
public class HistoryAdapter extends BaseAdapter {
    Context mContext;
    public int i = 2;

    public HistoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    ArrayList<SearchCarNameBean>arrayList ;

    public void setArrayList(ArrayList<SearchCarNameBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();


    }

    @Override
    public int getCount() {
        if (arrayList.size()>10){
            return  arrayList  == null ? 0:10;
        }else {
            return arrayList == null? 0:arrayList.size();
        }

    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search_history,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(arrayList.get(position).getName());
        return convertView;
    }
    class ViewHolder{
        private TextView tv;
        public ViewHolder(View view){
            tv = (TextView) view.findViewById(R.id.searchhistory_item_tv);

        }
    }
}
