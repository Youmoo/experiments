package intro;

import retrofit.http.GET;
import retrofit.http.Path;

import java.util.List;

/**
 * @author youmoo
 * @since 2014-09-04 2:01 PM
 */
public interface GitHubService {
    @GET("/users/{user}/repos")
    List<Object> listRepos(@Path("user") String user);
}
