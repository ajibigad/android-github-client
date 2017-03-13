package com.ajibigad.juno.githubclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ajibigad.juno.githubclient.GithubUserDetailsActivity;
import com.ajibigad.juno.githubclient.R;
import com.ajibigad.juno.githubclient.model.GithubUser;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Julius on 08/03/2017.
 */

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.ViewHolder> {

    private List<GithubUser> githubUsers;

    public GithubUserAdapter(List<GithubUser> githubUsers) {
        this.githubUsers = githubUsers;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public SimpleDraweeView userProfilePicture;
        public ViewHolder(View itemView) {
            super(itemView);
            final Context itemContext = itemView.getContext();
            username = (TextView) itemView.findViewById(R.id.username);
            userProfilePicture = (SimpleDraweeView) itemView.findViewById(R.id.profile_picture);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = ((TextView) v.findViewById(R.id.username)).getText().toString();
                    Intent githubUserDetailsIntent = new Intent(itemContext, GithubUserDetailsActivity.class);
                    githubUserDetailsIntent.putExtra("selectedUsername", username);
                    itemContext.startActivity(githubUserDetailsIntent);
                }
            });
        }
    }

    @Override
    public GithubUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GithubUser githubUser = githubUsers.get(position);
        holder.username.setText(githubUser.getUsername());
        holder.userProfilePicture.setImageURI(githubUser.getProfileImageUrl());
    }

    @Override
    public int getItemCount() {
        return githubUsers.size();
    }
}
