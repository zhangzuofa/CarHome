package lanou.carhome.forumfragment.threetyeight;

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
public class ThreeAdapter extends BaseAdapter {

    private Context mContext;
    private ThreeEightBean bean;

    public void setBean(ThreeEightBean bean) {
        this.bean = bean;
    }

    public ThreeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getResult().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getResult().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_threeeight,null);
           viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTitle.setText(bean.getResult().getList().get(position).getTitle());
        viewHolder.tvForum.setText(bean.getResult().getList().get(position).getBbsname());
        viewHolder.tvNum.setText(bean.getResult().getList().get(position).getReplycounts()+"回帖");
        Picasso.with(mContext).load(bean.getResult().getList().get(position).getSmallpic()).into(viewHolder.img);
        return convertView;
    }

    public void setBeanMore(ThreeEightBean response) {
        bean.getResult().getList().addAll(response.getResult().getList());
        notifyDataSetChanged();
    }

    class ViewHolder{

      TextView tvTitle;
       TextView tvForum;
       TextView tvNum;
       ImageView img;

       public ViewHolder(View view){
           tvTitle = (TextView) view.findViewById(R.id.threeNine_tv_title);
           tvForum = (TextView) view.findViewById(R.id.threeNine_tv_forum);
           tvNum = (TextView) view.findViewById(R.id.threeNine_tv_num);
           img = (ImageView) view.findViewById(R.id.threeNine_img);


       }
   }
}
