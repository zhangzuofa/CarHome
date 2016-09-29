package lanou.carhome.findcarfragment.newcarfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lanou.carhome.R;

/**
 * Created by dllo on 16/9/27.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Person> list;
    private ViewHolder viewHolder;



//    ArrayList<Person>cars;
//
//    public void setCars(ArrayList<Person> cars) {
//        this.cars = cars;
//    }

    //    public void setCarP(Person carP) {
//        this.carP = carP;
//    }
    public ListViewAdapter(Context context, List<Person> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        // TODO Auto-generated method stub
        if (list.get(position).getName().length() == 1)// 如果是字母索引
            return false;// 表示不能点击
        return super.isEnabled(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = list.get(position).getName();
        viewHolder = new ViewHolder();
        if (item.length() == 1) {
            convertView = LayoutInflater.from(context).inflate(R.layout.index,
                    null);
            viewHolder.indexTv = (TextView) convertView
                    .findViewById(R.id.indexTv);
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item,
                    null);

            viewHolder.itemTv = (TextView) convertView
                    .findViewById(R.id.itemTv);
            viewHolder.itemImg = (ImageView) convertView.findViewById(R.id.item_suoyin_img);


        }
        if (item.length() == 1) {
            viewHolder.indexTv.setText(list.get(position).getName());

        } else {


            viewHolder.itemTv.setText(list.get(position).getName());
          //  Log.d("ListViewAdapter", list.get(position).getPic());

            Picasso.with(context).load(list.get(position).getPic()).into(viewHolder.itemImg);

        }
        return convertView;
    }

    private class ViewHolder {
        private TextView indexTv;
        private TextView itemTv;
        private ImageView itemImg;
    }

}

