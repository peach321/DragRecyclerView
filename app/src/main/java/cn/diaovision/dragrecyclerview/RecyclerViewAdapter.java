package cn.diaovision.dragrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Tao Yimin on 2017/4/18.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Bean> list;

    public RecyclerViewAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sliding,
                parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getId() + "");
        holder.slidingItemView.setOnHideViewClickListener(new SlidingItemView.OnHideViewClickListener() {
            @Override
            public void onClick1(View view, int pos) {
                Toast.makeText(context,"position="+pos+"操作1",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick2(View view, int pos) {
                Toast.makeText(context,"position="+pos+"操作2",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick3(View view, int pos) {
                Toast.makeText(context,"position="+pos+"操作3",Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"id+"+list.get(position).getId()+"position="+position,Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,"长按了",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        holder.slidingItemView.bindViewAndData(holder.itemView,
                SlidingItemView.HideViewMode.MODE_HIDE_BOTTOM, list,
                holder.getLayoutPosition());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        SlidingItemView slidingItemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
            slidingItemView= (SlidingItemView) itemView.findViewById(R.id.item_view);
        }
    }

    public List<Bean> getList() {
        return list;
    }

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(MyViewHolder holder,int position);
        void onItemLongClick(MyViewHolder holder,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
