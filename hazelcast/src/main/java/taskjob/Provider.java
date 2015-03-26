package taskjob;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 提供任务,可以开启多个实例
 *
 * 假如开启a/b两个实例，a挂了后，a所生成的任务仍然在队列中，其他消费者仍然可以消费到
 *
 * @author youmoo
 * @since 2015-03-25 11:20 AM
 */
public class Provider {
    public static HazelcastInstance instance = null;

    static {
        Config cfg = new Config();
        instance = Hazelcast.newHazelcastInstance(cfg);
    }

    public static void main(String[] args) {

        Queue<String> queueCustomers = instance.getQueue("tasks");
        generateTasks(queueCustomers);
    }

    //定时生成任务
    public static void generateTasks(final Queue<String> queue) {
        Executors.newScheduledThreadPool(4).scheduleWithFixedDelay(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                String task = "[" + Thread.currentThread().getName() + "]" + (i++);
                System.out.println(task);
                queue.offer(task);
            }
        }, 0, 1500, TimeUnit.MILLISECONDS);
    }
}
