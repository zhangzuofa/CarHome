package lanou.carhome.findcarfragment.newcarfragment.hotadapter;

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
 * Created by dllo on 16/9/29.
 */
public class HotBrandAdapter extends RecyclerView.Adapter<HotBrandAdapter.ViewHolder> {
    private Context mContext;
    private HotBrandBean bean;

    public void setBean(HotBrandBean bean) {
        this.bean = bean;
    }

    public HotBrandAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hotbrand,null);
       ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).load(bean.getResult().getList().get(position).getImg()).into(holder.img);
        holder.tv.setText(bean.getResult().getList().get(position).getName());


    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 :bean.getResult().getList().size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img ;
        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_hotbrand_img);
            tv = (TextView) itemView.findViewById(R.id.item_hotbrand_tv);



        }
    }
}
