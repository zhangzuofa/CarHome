package lanou.carhome.find.everyoneadpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import lanou.carhome.R;
import lanou.carhome.find.FindBean;

/**
 * Created by dllo on 16/9/23.
 */
public class MyLoveAdapter extends RecyclerView.Adapter<MyLoveAdapter.ViewHolder> {

    private Context mContext;
    private FindBean bean;
    private int num;

    public void setBean(FindBean bean) {
        this.bean = bean;

    }

    public MyLoveAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_myloveadapter,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvTitle.setText(bean.getResult().getCardlist().get(num - 3).getData().get(position).getTitle());
        holder.tvCheap.setText(bean.getResult().getCardlist().get(num - 3).getData().get(position).getSubtitle());
        holder.tvPrice.setText(bean.getResult().getCardlist().get(num - 3).getData().get(position).getCurrentprice());
        holder.tvOldPrice.setText(bean.getResult().getCardlist().get(num - 3).getData().get(position).getPrice());
        Picasso.with(mContext).load(bean.getResult().getCardlist().get(num - 3).getData().get(position).getImageurl()).into(holder.img);



    }

    @Override
    public int getItemCount() {
        num = bean.getResult().getCardlist().size();
        return bean == null? 0:bean.getResult().getCardlist().get(num - 3).getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvCheap ;
        TextView tvPrice;
        TextView tvOldPrice;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.item_mylove_tv_titlce);
            tvCheap = (TextView) itemView.findViewById(R.id.item_mylove_tv_cheap);
            tvPrice = (TextView) itemView.findViewById(R.id.item_mylove_tv_price);
            tvOldPrice = (TextView) itemView.findViewById(R.id.item_mylove_tv_oldprice);
            img = (ImageView) itemView.findViewById(R.id.item_mylove_img);


        }
    }
}
