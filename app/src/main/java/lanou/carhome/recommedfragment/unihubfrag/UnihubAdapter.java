package lanou.carhome.recommedfragment.unihubfrag;

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
public class UnihubAdapter extends BaseAdapter {
    private UnhubBean bean;

    public void setBean(UnhubBean bean) {
        this.bean = bean;
    }

    Context mContext;

    public UnihubAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getResult().getNewslist().size();
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
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_unihubfragment,null);
            viewHolder  = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(mContext).load(bean.getResult().getNewslist().get(position).getUserpic()).into(viewHolder.userImg);
        viewHolder.userNameTv.setText(bean.getResult().getNewslist().get(position).getUsername());
        viewHolder.titleTV.setText(bean.getResult().getNewslist().get(position).getTitle());
        viewHolder.timeTv.setText(bean.getResult().getNewslist().get(position).getPublishtime());
        viewHolder.zanTV.setText(bean.getResult().getNewslist().get(position).getPraisenum() + "赞");
        viewHolder.pinglunTV.setText(bean.getResult().getNewslist().get(position).getReplycount() + "评论");
    Picasso.with(mContext).load(bean.getResult().getNewslist().get(position).getThumbnailpics().get(0)).into(viewHolder.img);

        return convertView;
    }
    class  ViewHolder{

        private TextView userNameTv;
        private TextView titleTV;
        private TextView timeTv;
        private TextView pinglunTV;
        private TextView zanTV;
        ImageView userImg;
        ImageView img;

        public ViewHolder(View view){
            userNameTv = (TextView) view.findViewById(R.id.item_unihub_username);
            titleTV = (TextView) view.findViewById(R.id.item_unihub_title);
            timeTv = (TextView) view.findViewById(R.id.item_unihub_time_tv);
            pinglunTV = (TextView) view.findViewById(R.id.item_unihub_pinglun_tv);
            zanTV = (TextView) view.findViewById(R.id.item_unihub_zan_tv);
            userImg = (ImageView) view.findViewById(R.id.item_unihub_head_imag);
            img = (ImageView) view.findViewById(R.id.item_unihub_img);


        }
    }
}
