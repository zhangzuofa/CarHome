package lanou.carhome.recommedfragment.moreactivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lanou.carhome.R;

/**
 * Created by dllo on 16/10/12.
 */
public class MoreReAdaper  extends RecyclerView.Adapter<MoreReAdaper.ViewHolder>{
    private Context mContext;
    private MoreBean bean;
   private OnClickLisenerRcycleMoreActy onClickLisenerRcycleMoreActy;

    public void setOnClickLisenerRcycleMoreActy(OnClickLisenerRcycleMoreActy onClickLisenerRcycleMoreActy) {
        this.onClickLisenerRcycleMoreActy = onClickLisenerRcycleMoreActy;
    }

    public void setBean(MoreBean bean) {
        this.bean = bean;
    }

    public MoreReAdaper(Context mContext) {
        this.mContext = mContext;
    }
    public void move(int from, int to) {
        //  Log.d("MoreActivityAdapter", "entityList.size():" + entityList.size());
        MoreBean.ResultBean.MetalistBean.ListBean fromStr = bean.getResult().getMetalist().get(0).getList().remove(from);
        // MoreEntity fromStr = entityList.remove(from);
        int position = from >= to ? to : to - 1;
        bean.getResult().getMetalist().get(0).getList().add(position, fromStr);
        notifyItemMoved(from, to);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_moreactivity,null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(mContext).load(bean.getResult().getMetalist().get(0).getList().get(position).getIconurl()).into(holder.img);
        holder.tv.setText(bean.getResult().getMetalist().get(0).getList().get(position).getName());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLisenerRcycleMoreActy.onClick(position,holder);


            }
        });
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 :bean.getResult().getMetalist().get(0).getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv;
        private LinearLayout ll;
        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_moreactivity_img);
            tv = (TextView) itemView.findViewById(R.id.item_moreactivity_tv);
            ll = (LinearLayout) itemView.findViewById(R.id.more_ll);


        }
    }
}
