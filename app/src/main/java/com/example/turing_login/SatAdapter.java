package com.example.turing_login;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SatAdapter extends RecyclerView.Adapter<SatAdapter.ViewHolder> {

    private Context context_sat;
    private List<Listitem_satfrag> listItems_satfrag;
    public SatAdapter(List<Listitem_satfrag> listItems_satfrag, Context context_sat) {
        this.listItems_satfrag = listItems_satfrag;
        this.context_sat = context_sat;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context_sat).inflate(R.layout.list_item_satfrag
                ,parent
                ,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Listitem_satfrag listitem_satfrag =listItems_satfrag.get(position);
        holder.textview_heading_satfragv.setText(listitem_satfrag.getHead());
        holder.textview_desc_satfragv.setText(listitem_satfrag.getDesc());
        holder.textview_lect_satfragv.setText(listitem_satfrag.getLect());
        int color= Integer.parseInt(listitem_satfrag.getCol());
        int colo= ContextCompat.getColor(context_sat, R.color.white);
        Log.d("Abhi",  "value of color is "+colo);
        if (color!=-1) {
            holder.textview_heading_satfragv.setTextColor(-1);
            holder.textview_desc_satfragv.setTextColor(-1);
            holder.textview_lect_satfragv.setTextColor(-1);
            holder.textview_heading_satfragv.setTypeface(Typeface.DEFAULT);
        }
        holder.cv.setCardBackgroundColor(color);
    }
    @Override
    public int getItemCount() {
        return listItems_satfrag.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_heading_satfragv;
        public TextView textview_desc_satfragv;
        public TextView textview_lect_satfragv;
        public RelativeLayout rv;
        public CardView cv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_satfragv=(TextView) itemView.findViewById(R.id.textview_heading_satfrag);
            textview_desc_satfragv=(TextView) itemView.findViewById(R.id.textview_desc_satfrag);
            textview_lect_satfragv=(TextView) itemView.findViewById(R.id.textview_lecturer_satfrag);
            rv=(RelativeLayout) itemView.findViewById(R.id.sat_rl);
            cv=itemView.findViewById(R.id.sat_card);
        }
    }
}