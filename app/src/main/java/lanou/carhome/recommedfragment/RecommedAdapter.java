package lanou.carhome.recommedfragment;

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
 * Created by dllo on 16/9/20.
 */
public class RecommedAdapter extends BaseAdapter {
  ReconmmedBean bean;

    public void setBean(ReconmmedBean bean) {
        this.bean = bean;
    }



    Context mContext;

    public RecommedAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return bean== null?0:bean.getResult().getNewslist().size();

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
         convertView = LayoutInflater.from(mContext).inflate(R.layout.item_recommed,null);
         viewHolder = new ViewHolder(convertView);
         convertView.setTag(viewHolder);


     }else {
         viewHolder = (ViewHolder) convertView.getTag();
     }





       viewHolder.tv_titcle.setText(bean.getResult().getNewslist().get(position).getTitle());
        if (bean.getResult().getNewslist().get(position).getMediatype() == 3){
            viewHolder.tv_comment.setText("" + bean.getResult().getNewslist().get(position).getReplycount()+ "播放");
        }else if (bean.getResult().getNewslist().get(position).getMediatype() == 6){
            viewHolder.tv_comment.setText( "图说"+ " " + bean.getResult().getNewslist().get(position).getReplycount()+ "评论");

        }else {
            viewHolder.tv_comment.setText("" + bean.getResult().getNewslist().get(position).getReplycount()+ "评论");
        }

        viewHolder.tv_time.setText(bean.getResult().getNewslist().get(position).getTime());

        if (!bean.getResult().getNewslist().get(position).getSmallpic().isEmpty()){

            Picasso.with(mContext).load(bean.getResult().getNewslist().get(position).getSmallpic()).into(viewHolder.img);
        }






        return convertView;
    }
    class ViewHolder{


        TextView tv_titcle;
        TextView tv_comment;
        TextView tv_time;
        ImageView img;

        public ViewHolder(View view){
            tv_titcle = (TextView) view.findViewById(R.id.recommed_item_title_tv);
            tv_comment = (TextView) view.findViewById(R.id.recommed_item_pinglun_tv);
            tv_time = (TextView) view.findViewById(R.id.recommed_item_time_tv);
            img = (ImageView) view.findViewById(R.id.recommed_item_pic_img);


        }

    }
}
