package com.trankimphu.rssreader;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RSSFeedListAdapter extends RecyclerView.Adapter<RSSFeedListAdapter.FeedModelViewHolder> {

    private List<RSSFeedModel> mRSSFeedModels;
    Context context;


    public static class FeedModelViewHolder extends RecyclerView.ViewHolder {
        private View rssFeedView;

        public FeedModelViewHolder(View v) {
            super(v);
            rssFeedView = v;

        }
    }

    public RSSFeedListAdapter(List<RSSFeedModel> mRSSFeedModels, Context context) {
        this.mRSSFeedModels = mRSSFeedModels;
        this.context = context;
    }

    @Override
    public FeedModelViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rss_feed, parent, false);
        FeedModelViewHolder holder = new FeedModelViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(FeedModelViewHolder holder, int position) {
        final RSSFeedModel rssFeedModel = mRSSFeedModels.get(position);
        ((TextView)holder.rssFeedView.findViewById(R.id.titleText)).setText(rssFeedModel.title);
        ((TextView)holder.rssFeedView.findViewById(R.id.descriptionText))
                .setText(rssFeedModel.description);
        ((TextView)holder.rssFeedView.findViewById(R.id.linkText)).setText(rssFeedModel.link);
        ((TextView)holder.rssFeedView.findViewById(R.id.linkText)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lnk = ((TextView)v).getText().toString();
                Uri uri = Uri.parse(lnk);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                if (i.resolveActivity(context.getPackageManager())!=null) {
                    try {
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    }
                    catch (Exception e) {
                        Log.i("Err", e.getMessage().toString());
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
