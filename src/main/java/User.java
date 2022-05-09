import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"email", "password", "name"})
public class User {
    public static String email;
    public static String password;
    public static String name;

    @JsonCreator
    public User(
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("name") String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @JsonCreator
    public User(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

    @JsonGetter("email")
    public String getEmail() {
        return email;
    }

    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }


    public void setEmail(String email) {
        User.email = email;
    }

    public void setPassword(String password) {
        User.password = password;
    }

    public void setName(String name) {
        User.name = name;
    }
}
