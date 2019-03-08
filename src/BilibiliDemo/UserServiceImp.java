package BilibiliDemo;

public class UserServiceImp implements UserService{

    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    public void addUser() {
        System.out.println(" -> addUser methord used");
    }

    public void updateUser() {
        System.out.println(" -> updateUser methord used");
    }
    public void deleteUser() {
        System.out.println(" -> deleteUser methord used");
    }

    public void descripUser() {
        System.out.println(user.getUid() + " | " + user.getName() + " | " + user.getAge());
    }
}
