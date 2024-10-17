package com.example.vjezba2;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RepositoryAdapter adapter;
    private GitHubApiService gitHubApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Repository> mockRepositories = createMockRepositories();

        adapter = new RepositoryAdapter(mockRepositories);
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gitHubApiService = retrofit.create(GitHubApiService.class);

        fetchGitHubRepositories();
    }

    private List<Repository> createMockRepositories() {
        List<Repository> repositories = new ArrayList<>();

        repositories.add(new Repository("https://media.istockphoto.com/id/1300845620/vector/user-icon-flat-isolated-on-white-background-user-symbol-vector-illustration.jpg?s=612x612&w=0&k=20&c=yBeyba0hUkh14_jgv1OKqIH0CCSWU_4ckRkAoy2p73o=", "Repo 1", 150000));
        repositories.add(new Repository("https://media.istockphoto.com/id/1300845620/vector/user-icon-flat-isolated-on-white-background-user-symbol-vector-illustration.jpg?s=612x612&w=0&k=20&c=yBeyba0hUkh14_jgv1OKqIH0CCSWU_4ckRkAoy2p73o=", "Repo 2", 120000));
        repositories.add(new Repository("https://media.istockphoto.com/id/1300845620/vector/user-icon-flat-isolated-on-white-background-user-symbol-vector-illustration.jpg?s=612x612&w=0&k=20&c=yBeyba0hUkh14_jgv1OKqIH0CCSWU_4ckRkAoy2p73o=", "Repo 3", 110000));
        return repositories;
    }

    private void fetchGitHubRepositories() {

        Call<GitHubResponse> call = gitHubApiService.getTopRepositories("stars:>100000");
        call.enqueue(new Callback<GitHubResponse>() {
            @Override
            public void onResponse(Call<GitHubResponse> call, Response<GitHubResponse> response) {
                if (response.isSuccessful()) {
                    List<GitHubRepository> repositories = response.body().items;

                    List<Repository> myRepositories = new ArrayList<>();

                    for (GitHubRepository gitHubRepository : repositories) {
                        Repository repository = new Repository(
                                gitHubRepository.owner.avatar_url,
                                gitHubRepository.name,
                                gitHubRepository.stargazers_count
                        );
                        myRepositories.add(repository);
                    }

                    adapter.setRepositories(myRepositories);
                } else {
                    Log.e("GitHubApi", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<GitHubResponse> call, Throwable t) {
                Log.e("GitHubApi", "Failed: " + t.getMessage());
            }
        });
    }

}
