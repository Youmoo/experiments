package intro;

import retrofit.RestAdapter;

import java.util.List;

/**
 * @author youmoo
 * @since 2014-09-04 2:02 PM
 */
public class App {

    public static void main(String[] args) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .build();

        GitHubService service = restAdapter.create(GitHubService.class);

        List<Object> repos = service.listRepos("octocat");
    }
}
