package taskjob;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;

import java.io.InputStream;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 执行一项任务,可以开启多个实例
 *
 * @author youmoo
 * @since 2015-03-25 11:22 AM
 */
public class Consumer {
    static HazelcastInstance client = null;

    static {
        try {
            Properties properties = loadProps();
            ClientConfig clientConfig = new ClientConfig();
            ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
            String ip = properties.getProperty("provider.ip", "");
            System.out.println("ip: " + ip);
            networkConfig.addAddress(ip.split(","));
            client = HazelcastClient.newHazelcastClient(clientConfig);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public static void main(String[] args) {

        Queue<String> queue = client.getQueue("tasks");
        consumer(queue);
    }

    public static void consumer(final Queue<String> queue) {
        Executors.newScheduledThreadPool(4).scheduleWithFixedDelay(new Runnable() {

            @Override
            public void run() {
                String task = queue.poll();
                if (task == null) {
                    System.out.println("休息10秒");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return;
                }
                System.out.println(task);
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    public static Properties loadProps() throws Exception {
        InputStream stream = Consumer.class.getClassLoader().getResourceAsStream("app.properties");
        Properties properties = new Properties();
        properties.load(stream);
        stream.close();
        return properties;
    }
}
