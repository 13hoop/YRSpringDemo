package BilibiliDemo;

public class User {
    private Integer uid;
    private String name;
    private Integer age;

    public User(Integer uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getUid() {
        return uid;
    }
}
