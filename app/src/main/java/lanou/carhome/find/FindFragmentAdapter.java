package lanou.carhome.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lanou.carhome.R;

/**
 * Created by dllo on 16/9/23.
 */
public class FindFragmentAdapter extends BaseAdapter {

    FindBean bean;
    private int num;

    public void setBean(FindBean bean) {
        this.bean = bean;
    }

    Context mContext;

    public FindFragmentAdapter(Context mContext) {
        this.mContext = mContext;
    }



    @Override
    public int getCount() {
        num = bean.getResult().getCardlist().size();
        return bean == null ? 0: bean.getResult().getCardlist().get(num-1).getData().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder =null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_findfragment,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

       viewHolder.tvTitle.setText(bean.getResult().getCardlist().get(num-1).getData().get(position).getTitle());
        viewHolder.tvAdinfo.setText(bean.getResult().getCardlist().get(num-1).getData().get(position).getAdinfo());
        viewHolder.tvPrice.setText(bean.getResult().getCardlist().get(num-1).getData().get(position).getPrice());
        if (!bean.getResult().getCardlist().get(num-1).getData().get(position).getFctprice().equals("")){
            viewHolder.tvOldPrice.setText(bean.getResult().getCardlist().get(num-1).getData().get(position).getFctprice());

        }
        Picasso.with(mContext).load(bean.getResult().getCardlist().get(num-1).getData().get(position).getLogo()).into(viewHolder.img);



        return convertView;
    }
    class ViewHolder{
        TextView tvTitle;
        TextView tvAdinfo;
        TextView tvPrice;
        TextView tvOldPrice;
        ImageView img;


        public ViewHolder(View view){
            tvTitle = (TextView) view.findViewById(R.id.item_fragment_tv_title);
            tvAdinfo = (TextView) view.findViewById(R.id.item_fragment_adinfo);
            tvPrice = (TextView) view.findViewById(R.id.item_fragment_tv_price);
            tvOldPrice = (TextView) view.findViewById(R.id.item_fragment_tv_oldprice);
            img = (ImageView) view.findViewById(R.id.item_fragment_img);

        }
    }

}
