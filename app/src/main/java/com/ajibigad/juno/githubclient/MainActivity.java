package com.ajibigad.juno.githubclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ajibigad.juno.githubclient.adapter.GithubUserAdapter;
import com.ajibigad.juno.githubclient.model.GithubUser;
import com.ajibigad.juno.githubclient.network.GithubResponse;
import com.ajibigad.juno.githubclient.network.GithubSearchService;
import com.ajibigad.juno.githubclient.utils.ProgressBarHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    GithubSearchService githubSearchService;

    //int currentPage = 0;

    private RecyclerView.Adapter adapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    final List<GithubUser> githubUsers = new ArrayList<>();
    private boolean isThereNextPage = true;
    private ProgressBarHelper progressBarHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView githubUserView = (RecyclerView) findViewById(R.id.user_list);
        githubUserView.setHasFixedSize(false);

        adapter = new GithubUserAdapter(githubUsers);
        githubUserView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        githubUserView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(githubUserView.getContext(),
                DividerItemDecoration.VERTICAL);
        githubUserView.addItemDecoration(dividerItemDecoration);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadPage(page + 1);
            }
        };
        githubUserView.addOnScrollListener(scrollListener);

        progressBarHelper = new ProgressBarHelper(MainActivity.this);
        githubSearchService = new GithubSearchService();
        loadPage(1); //load first page
    }

    private void updateGithubUserList(List<GithubUser> newGithubUsers){
        githubUsers.addAll(newGithubUsers);
        adapter.notifyDataSetChanged();
    }

    private void loadPage(int page){
        String dialogMessage = page == 1 ? "Loading developers..." : "Loading more developers...";
        if(isThereNextPage){
            progressBarHelper.showProgressDialog(dialogMessage);
            githubSearchService.getLagosJavaDevs(page).enqueue(new Callback<GithubResponse>() {
                @Override
                public void onResponse(Call<GithubResponse> call, Response<GithubResponse> response) {
                    if(response.isSuccessful()){
                        Log.i(TAG, "Total github result " + response.body().getTotalCount());
                        if(response.body().getTotalCount() > 0){
                            updateGithubUserList(response.body().getGithubUserList());
                        }
                        isThereNextPage = response.headers().get("Link").contains("next");
                    }else {
                        Toast.makeText(MainActivity.this, "Request to github failed : " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                    progressBarHelper.dismissProgressDialog();
                }

                @Override
                public void onFailure(Call<GithubResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Request to github failed at network level",  Toast.LENGTH_LONG).show();
                    progressBarHelper.dismissProgressDialog();
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
