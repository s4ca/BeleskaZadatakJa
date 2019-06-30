package com.example.beleskazadatakja;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beleskazadatakja.db.Beleksa;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    List<Beleksa> beleske;
    private ItemClickListener listener;

    public MyAdapter(List<Beleksa> beleske, ItemClickListener listener) {
        this.beleske = beleske;
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_naslovRV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_naslovRV=itemView.findViewById(R.id.tv_naslovRV);
        }

        public void bind (final Beleksa beleksa, final ItemClickListener listener){
            tv_naslovRV.setText(beleksa.getNaslov());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(beleksa);
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(beleske.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return beleske.size();
    }

   public interface ItemClickListener {
        void onItemClick (Beleksa beleksa);
   }

}
