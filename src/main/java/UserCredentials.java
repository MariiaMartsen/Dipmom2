public class UserCredentials {
    private String email;
    private String password;
    private String accessToken;
    private String token;


    public UserCredentials(String email, String password, String accessToken, String token) {
        this.email = email;
        this.password = password;
        this.accessToken = accessToken;
        this.token = token;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAccessToken() {
        return accessToken;
    }
    public String getToken() {
        return token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
