package lanou.carhome.recommedfragment.market;

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
public class MarketAdapter extends BaseAdapter {
    private Context mContext;
    private MarketBean bean;

    public void setBean(MarketBean bean) {
        this.bean = bean;
    }

    public MarketAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 :bean.getResult().getNewslist().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getResult().getNewslist().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_market,null);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);

        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        viewholder.titleTv.setText(bean.getResult().getNewslist().get(position).getTitle());
        viewholder.timeTv.setText(bean.getResult().getNewslist().get(position).getTime());
        Picasso.with(mContext).load(bean.getResult().getNewslist().get(position).getSmallpic()).into(viewholder.img);
        if (bean.getResult().getNewslist().get(position).getReplycount() != 0 ){
           viewholder.commentTv.setText("" + bean.getResult().getNewslist().get(position).getReplycount() + "评论");
        }
        return convertView;
    }
   class ViewHolder{
       TextView titleTv ;
       TextView commentTv;
       TextView timeTv;
       ImageView img;
       public ViewHolder(View view){
          titleTv = (TextView) view.findViewById(R.id.item_market_title_tv);
           commentTv = (TextView) view.findViewById(R.id.item_market_comment_tv);
           timeTv = (TextView) view.findViewById(R.id.item_market_time_tv);
           img = (ImageView) view.findViewById(R.id.item_market_img);
       }
   }
}
