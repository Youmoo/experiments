package perfomance;

import java.util.Date;

/**
 * @author youmoo
 * @since 2014-04-17 下午4:43
 */
public class ValueHolder {
    String username;
    Integer age;
    Double height;
    Date birthday;

    public ValueHolder() {
    }

    public ValueHolder(Integer age, Double height) {
        this.age = age;
        this.height = height;
    }

    public ValueHolder(String username, Integer age, Double height, Date birthday) {
        this.username = username;
        this.age = age;
        this.height = height;
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "ValueHolder{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", birthday=" + birthday +
                '}';
    }
}
