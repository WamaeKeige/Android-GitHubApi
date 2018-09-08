package models;

public class GitHubUser {
    private String login;
    private String name;
    private String followers;
    private String following;
    private String avatar;
    private String email;

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getFollowers() {
        return followers;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getFollowing() {
        return following;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public void setName(String name) {
        this.name = name;
    }
 public GitHubUser(String email, String avatar, String followers, String following, String login, String name){
        this.setEmail(email);
        this.setLogin(login);
        this.setAvatar(avatar);
        this.setFollowing(following);
        this.setFollowers(followers);
 }
}
