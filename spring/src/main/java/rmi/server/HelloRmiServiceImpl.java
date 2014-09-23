package rmi.server;

public class HelloRmiServiceImpl implements HelloRmiService {
    @Override
    public String getGreeting() {
        return "hello rmi!";
    }

    @Override
    public User getUser() {
        return new User((int) Math.round(Math.random() * 100), "youmoo" + System.currentTimeMillis());
    }
}
