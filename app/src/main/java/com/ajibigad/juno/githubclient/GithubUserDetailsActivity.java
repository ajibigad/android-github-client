package com.ajibigad.juno.githubclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ajibigad.juno.githubclient.model.GithubUser;
import com.ajibigad.juno.githubclient.network.GithubResponse;
import com.ajibigad.juno.githubclient.network.GithubSearchService;
import com.ajibigad.juno.githubclient.utils.ProgressBarHelper;
import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubUserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_user_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView usernameTextView = (TextView) findViewById(R.id.username);
        final TextView profileUrlTextView = (TextView) findViewById(R.id.profile_url);
        final SimpleDraweeView profileImageView = (SimpleDraweeView) findViewById(R.id.profile_picture);
        ImageButton shareButton = (ImageButton) findViewById(R.id.share_btn);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareContent = String.format("Check out this awesome developer @%s, %s.", usernameTextView.getText().toString(), profileUrlTextView.getText().toString());
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent);
                startActivity(shareIntent);
            }
        });

        Intent intent = getIntent();

        final String selectedUsername = intent.getStringExtra("selectedUsername");

        final ProgressBarHelper progressBarHelper = new ProgressBarHelper(GithubUserDetailsActivity.this);
        progressBarHelper.showProgressDialog("Loading details...");

        GithubSearchService githubSearchService = new GithubSearchService();
        githubSearchService.getGithubUser(selectedUsername).enqueue(new Callback<GithubResponse>() {
            @Override
            public void onResponse(Call<GithubResponse> call, Response<GithubResponse> response) {
                if(response.isSuccessful()){
                    GithubUser githubUser = response.body().getGithubUserList().get(0);
                    if(githubUser != null){
                        usernameTextView.setText(githubUser.getUsername());
                        profileImageView.setImageURI(githubUser.getProfileImageUrl());
                        profileUrlTextView.setText(githubUser.getProfileURL());
                    }
                    else{
                        Toast.makeText(GithubUserDetailsActivity.this, selectedUsername + " not found", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(GithubUserDetailsActivity.this, "request failed", Toast.LENGTH_SHORT).show();
                }
                progressBarHelper.dismissProgressDialog();
            }

            @Override
            public void onFailure(Call<GithubResponse> call, Throwable t) {
                progressBarHelper.dismissProgressDialog();
                Toast.makeText(GithubUserDetailsActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
