package com.git.crashlytics.crashlyticsapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.git.crashlytics.crashlyticsapp.R;
import com.git.crashlytics.crashlyticsapp.model.CraslyticsIssues;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by z018534 on 30-11-2018.
 */

public class CrashlyticsAdapter extends RecyclerView.Adapter<CrashlyticsAdapter.CrashlyticsViewHolder> {

    private List<CraslyticsIssues> issuesList;
    private Context context;

    public CrashlyticsAdapter(Context context, List<CraslyticsIssues> issuesList) {
        this.context = context;
        this.issuesList = issuesList;
    }

    @Override
    public CrashlyticsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_item_display, parent, false);
        return new CrashlyticsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CrashlyticsViewHolder holder, int position) {

        holder.txtTitle.setText(issuesList.get(position).getTitle());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(issuesList.get(position).getUser().getAvatar_url())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.userAvatar);
    }

    @Override
    public int getItemCount() {
        return issuesList.size();
    }

    class CrashlyticsViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;
        private ImageView userAvatar;

        public CrashlyticsViewHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txt_title);
            userAvatar = itemView.findViewById(R.id.img_user_avatar);
        }
    }

}