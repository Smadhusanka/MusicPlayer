package com.example.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{

    ArrayList<AudioModel> songList;
    Context context;

    public MusicListAdapter(ArrayList<AudioModel> songList, Context context) {
        this.songList = songList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new MusicListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicListAdapter.ViewHolder holder, int position) {
        AudioModel SongData = songList.get(position);
        holder.titleTextview.setText(SongData.getTitle());

        if(MyMediaPlayer.currentIndex == position){
            holder.titleTextview.setTextColor(Color.parseColor("#F1807E"));
        }else{
            holder.titleTextview.setTextColor(Color.parseColor("#000000"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate another activity
                MyMediaPlayer.getInstance().reset();
                MyMediaPlayer.currentIndex = position;
                Intent intent = new Intent(context, MusicPlayerActivity.class);
                intent.putExtra("LIST",songList);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextview;
        ImageView iconImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextview = itemView.findViewById(R.id.music_title_text);
            iconImageView = itemView.findViewById(R.id.icon_view);
        }
    }

}
