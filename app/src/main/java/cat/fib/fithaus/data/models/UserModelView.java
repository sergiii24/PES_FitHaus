package cat.fib.fithaus.data.models;

public class UserModelView {

    public static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserModelView.user = user;
    }

}
