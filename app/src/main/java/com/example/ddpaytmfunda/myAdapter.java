package com.example.ddpaytmfunda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myHolder> {

  String data[];

    public myAdapter(String[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.singlerow,parent,false);
            return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {

        holder.t.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    class myHolder extends RecyclerView.ViewHolder {
  ImageView img;
  TextView t;

        public myHolder(@NonNull View itemView) {
            super(itemView);
                img=(ImageView)itemView.findViewById(R.id.img1);
                t=(TextView)itemView.findViewById(R.id.t1);

        }
    }


}
