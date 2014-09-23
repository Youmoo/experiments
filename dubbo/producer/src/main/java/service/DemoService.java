package service;

/**
 * 使用时，只需要将此接口暴露给服务的消费者。
 * 正式环境下应将接口与实现分包放置
 */
public interface DemoService {
    public String helloDubbo();
}
