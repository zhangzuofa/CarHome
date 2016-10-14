package lanou.carhome.forumfragment.slectedrecommedacitivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import lanou.carhome.R;

/**
 * Created by dllo on 16/9/22.
 */
public class SelectedRecAdapter extends BaseAdapter {
    private Context mCotext;

    private String[] arrayList ;

    public void setArrayList(String[] arrayList) {
        this.arrayList = arrayList;
    }

    public SelectedRecAdapter(Context mCotext) {
        this.mCotext = mCotext;
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0:arrayList.length;
    }

    @Override
    public Object getItem(int position) {
        return arrayList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView =LayoutInflater.from(mCotext).inflate(R.layout.item_selecrecomed,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(arrayList[position]);

        return convertView;
    }
   class ViewHolder{

       TextView tv;

       public ViewHolder(View view){
           tv = (TextView) view.findViewById(R.id.item_selectedre_tv);

       }

    }
}
