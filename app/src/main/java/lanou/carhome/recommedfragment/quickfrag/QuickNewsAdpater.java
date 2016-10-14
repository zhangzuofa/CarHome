package lanou.carhome.recommedfragment.quickfrag;

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
public class QuickNewsAdpater extends BaseAdapter {
    private Context mContext;
    private QuickNewsBean bean;

    public void setBean(QuickNewsBean bean) {
        this.bean = bean;
    }

    public QuickNewsAdpater(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return bean == null ? 0: bean.getResult().getList().size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_quicknews,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.titleTv.setText(bean.getResult().getList().get(position).getTitle());
        viewHolder.timeTv.setText(bean.getResult().getList().get(position).getCreatetime());
        viewHolder.listenTv.setText((bean.getResult().getList().get(position).getReviewcount())/10000 + "万位听众");
        Picasso.with(mContext).load(bean.getResult().getList().get(position).getBgimage()).into(viewHolder.img);

        return convertView;
    }
    class ViewHolder{
        TextView titleTv;
        TextView playTv;
        TextView listenTv;
       TextView timeTv;
        ImageView img;
        public ViewHolder(View view){
            titleTv = (TextView) view.findViewById(R.id.item_quicknews_title_tv);
            listenTv = (TextView) view.findViewById(R.id.item_quicknews_listsener_tv);
            timeTv = (TextView) view.findViewById(R.id.item_quicknews_time_tv);
            img = (ImageView) view.findViewById(R.id.item_quicknews_img);

        }
    }
}
