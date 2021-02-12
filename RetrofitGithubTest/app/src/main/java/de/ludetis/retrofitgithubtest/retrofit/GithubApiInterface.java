package de.ludetis.retrofitgithubtest.retrofit;

import java.util.List;

import de.ludetis.retrofitgithubtest.retrofit.models.Repo;
import de.ludetis.retrofitgithubtest.retrofit.models.SearchUserResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApiInterface {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("search/users")
    Call<SearchUserResult> searchUsers(@Query("q") String query);
}
