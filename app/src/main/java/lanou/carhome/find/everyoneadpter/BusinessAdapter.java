package lanou.carhome.find.everyoneadpter;

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
import lanou.carhome.find.FindBean;
import lanou.carhome.recommedfragment.smallrecommedfrag.OnClickLisenerRecycleView;

/**
 * Created by dllo on 16/9/25.
 */
public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.ViewHolder> {
   private FindBean bean;
    OnClickLisenerRecycleView onClickLisenerRecycleView;

    public void setOnClickLisenerRecycleView(OnClickLisenerRecycleView onClickLisenerRecycleView) {
        this.onClickLisenerRecycleView = onClickLisenerRecycleView;
    }

    public void setBean(FindBean bean) {
        this.bean = bean;
    }

    private Context mContext;

    public BusinessAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_business,null);
       ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Picasso.with(mContext).load(bean.getResult().getCardlist().get(1).getData().get(position).getImageurl()).into(holder.img);
        holder.tv.setText(bean.getResult().getCardlist().get(1).getData().get(position).getTitle());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLisenerRecycleView.onClick(position,holder);
            }
        });

    }

    @Override
    public int getItemCount() {

        return bean == null ? 0 : bean.getResult().getCardlist().get(1).getData().size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img ;
        TextView tv ;
        LinearLayout ll;
        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_business_img);
            tv = (TextView) itemView.findViewById(R.id.item_business_tv);
            ll = (LinearLayout) itemView.findViewById(R.id.business_ll);

        }
    }
}
