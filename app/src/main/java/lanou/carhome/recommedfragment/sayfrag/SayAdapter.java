package lanou.carhome.recommedfragment.sayfrag;

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
 * Created by dllo on 16/10/8.
 */
public class SayAdapter extends BaseAdapter {
    Context mContext;
    SayBean bean;

    public void setBean(SayBean bean) {
        this.bean = bean;
    }

    public SayAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getResult().getList().size();
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
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sayfragment,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.titleTv.setText(bean.getResult().getList().get(position).getTitle());
        viewHolder.timeTv.setText(bean.getResult().getList().get(position).getTime());
        viewHolder.commentTv.setText(bean.getResult().getList().get(position).getReplycount() + "评论");
        Picasso.with(mContext).load(bean.getResult().getList().get(position).getSmallpic()).into(viewHolder.img);


        return convertView;
    }
    class ViewHolder{
        TextView titleTv ;
        TextView commentTv;
        TextView timeTv;
        ImageView img;

        public ViewHolder(View view){
            titleTv = (TextView) view.findViewById(R.id.item_say_title_tv);
            commentTv = (TextView) view.findViewById(R.id.item_say_comment_tv);
            timeTv = (TextView) view.findViewById(R.id.item_say_time_tv);
            img = (ImageView) view.findViewById(R.id.item_say_img);


        }
    }
}
