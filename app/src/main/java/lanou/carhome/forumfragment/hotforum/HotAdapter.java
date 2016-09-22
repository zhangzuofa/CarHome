package lanou.carhome.forumfragment.hotforum;

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
 * Created by dllo on 16/9/22.
 */
public class HotAdapter extends BaseAdapter {
   HotBean bean;

    public void setBean(HotBean bean) {
        this.bean = bean;
    }

    Context mContext;

    public HotAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return bean == null? 0: bean.getResult().getList().size();
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
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.hot_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }




        if (bean.getResult().getList().get(position).getHeadimg().equals("")){
            viewHolder.img.setImageResource(R.mipmap.ahlib_userpic_default);

        }else {
            Picasso.with(mContext).load(bean.getResult().getList().get(position).getHeadimg()).into(viewHolder.img);
        }

        if (bean.getResult().getList().get(position).getAuthseries().equals("")){
            viewHolder.tvName.setText(bean.getResult().getList().get(position).getPostusername());
        }else {

            viewHolder.tvName.setText(bean.getResult().getList().get(position).getPostusername() + "  " +
            bean.getResult().getList().get(position).getAuthseries() );
        }

        viewHolder.tvTitle.setText(bean.getResult().getList().get(position).getTitle());
        viewHolder.tvForum.setText(bean.getResult().getList().get(position).getBbsname());
        viewHolder.tvTime.setText(bean.getResult().getList().get(position).getPostdate());
        viewHolder.tvNum.setText(bean.getResult().getList().get(position).getReplycounts()+" " + "回帖");
        return convertView;
    }

    class ViewHolder {

        TextView tvName;
        TextView tvTitle;
        TextView tvForum;
        TextView tvTime;
        TextView tvNum;
        ImageView img;

        public ViewHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.hotActy_tv_name);
            tvTitle = (TextView) view.findViewById(R.id.tv_title_hotActy);
            tvForum = (TextView) view.findViewById(R.id.tv_forumed_hotActy);
            tvTime = (TextView) view.findViewById(R.id.time_tv_hotAvty);
            tvNum = (TextView) view.findViewById(R.id.tv_num_hotActy);
            img = (ImageView) view.findViewById(R.id.img_hotActy);

        }
    }

}
