package lanou.carhome.recommedfragment.smallrecommedfrag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lanou.carhome.R;

/**
 * Created by dllo on 16/10/8.
 */
public class SmallRecommedAdapter  extends RecyclerView.Adapter<SmallRecommedAdapter.ViewHolder>{
    Context mContext;
    ReconmmedBean bean;

    public void setBean(ReconmmedBean bean) {
        this.bean = bean;
    }

    public SmallRecommedAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recommed,null);
       ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_titcle.setText(bean.getResult().getNewslist().get(position).getTitle());
        if (bean.getResult().getNewslist().get(position).getMediatype() == 3){
            holder.tv_comment.setText("" + bean.getResult().getNewslist().get(position).getReplycount()+ "播放");
        }else if (bean.getResult().getNewslist().get(position).getMediatype() == 6){
            holder.tv_comment.setText( "图说"+ " " + bean.getResult().getNewslist().get(position).getReplycount()+ "评论");

        }else {
            holder.tv_comment.setText("" + bean.getResult().getNewslist().get(position).getReplycount()+ "评论");
        }

        holder.tv_time.setText(bean.getResult().getNewslist().get(position).getTime());

        if (!bean.getResult().getNewslist().get(position).getSmallpic().isEmpty()){

            Picasso.with(mContext).load(bean.getResult().getNewslist().get(position).getSmallpic()).into(holder.img);
        }

    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 :bean.getResult().getNewslist().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_titcle;
        TextView tv_comment;
        TextView tv_time;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_titcle = (TextView) itemView.findViewById(R.id.recommed_item_title_tv);
            tv_comment = (TextView) itemView.findViewById(R.id.recommed_item_pinglun_tv);
            tv_time = (TextView) itemView.findViewById(R.id.recommed_item_time_tv);
            img = (ImageView) itemView.findViewById(R.id.recommed_item_pic_img);

        }
    }
}
