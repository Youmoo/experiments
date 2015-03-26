package app;

import static spark.Spark.get;
import static spark.Spark.stop;

/**
 * @author youmoo
 * @since 2014-08-25 上午10:01
 */
public class HelloWorld {
    public static void main(String[] args) {
        //简单的路径匹配
        get("/", (req, res) -> {
            System.out.println("get / ");
            return "hello root path";
        });
        //简单的路径匹配
        get("/hello", (req, res) -> "Hello World");
        //命名参数
        get("/named/:name", (req, res) -> {
            System.out.println("name:" + req.params(":name"));
            return "params->" + req.params(":name");
        });
        //通配符
        get("/wildcard/*/a/*", (req, res) -> {
            res.header("charset", "utf-8");
            return "[0]->" + req.splat()[0] + "\n[1]->" + req.splat()[1];
        });

        //关闭server
        get("/stop", (req, res) -> {
            stop();
            return null;
        });

    }
}
