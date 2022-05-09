import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    static String userEmail;
    static String userPassword;
    static String userName;

    @Step("Generating random user with Email, Password and Name")
    public static User getRandomEmailPasswordName() {
        userEmail = RandomStringUtils.randomAlphabetic(9) + "@yandex.ru";
        userPassword = RandomStringUtils.randomAlphabetic(9);
        userName = RandomStringUtils.randomAlphabetic(9);

        Allure.addAttachment("email", userEmail);
        Allure.addAttachment("password", userPassword);
        Allure.addAttachment("name", userName);
        return new User(userEmail, userPassword, userName);
    }

    @Step("Generating random user with Email, Password and without Name")
    public static User getRandomEmailPasswordWithoutName() {
        userEmail = RandomStringUtils.randomAlphabetic(9) + "@yandex.ru";
        userPassword = RandomStringUtils.randomAlphabetic(9);

        Allure.addAttachment("email", userEmail);
        Allure.addAttachment("password", userPassword);

        return new User(userEmail, userPassword);
    }
}
