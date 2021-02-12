package de.ludetis.retrofitgithubtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;
import java.util.List;

import de.ludetis.retrofitgithubtest.retrofit.GithubApiInterface;
import de.ludetis.retrofitgithubtest.retrofit.models.Repo;
import de.ludetis.retrofitgithubtest.retrofit.models.SearchUserResult;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        GithubApiInterface githubApiInterface = retrofit.create(GithubApiInterface.class);

        Call<SearchUserResult> call = githubApiInterface.searchUsers("Android");

        call.enqueue(new Callback<SearchUserResult>() {
            @Override
            public void onResponse(Call<SearchUserResult> call, Response<SearchUserResult> response) {
                if (response.isSuccessful()) {
                    SearchUserResult body = response.body();
                    System.out.println("body = " + body);
                } else {
                    ResponseBody errorBody = response.errorBody();
                    System.err.println("errorBody = " + errorBody);
                }
            }

            @Override
            public void onFailure(Call<SearchUserResult> call, Throwable t) {
                System.err.println("t = " + t);
            }
        });

        githubApiInterface.listRepos("android10").enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    List<Repo> body = response.body();
                    System.out.println("body = " + body);
                } else {
                    ResponseBody errorBody = response.errorBody();
                    System.err.println("errorBody = " + errorBody);
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                System.err.println("t = " + t);
            }
        });



//        android10


//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Response<SearchUserResult> response = call.execute();
//
//                    if (response.isSuccessful()) {
//                        SearchUserResult body = response.body();
//                        System.out.println("body = " + body);
//                    } else {
//                        ResponseBody errorBody = response.errorBody();
//                        System.err.println("errorBody = " + errorBody);
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();

    }
}