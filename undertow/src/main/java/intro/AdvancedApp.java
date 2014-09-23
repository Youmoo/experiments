package intro;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.resource.ClassPathResourceManager;

/**
 * @author youmoo
 * @since 2014-09-16 11:27 AM
 */
public class AdvancedApp {
    public static void main(String[] args) {
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(
                        Handlers.path()
                                .addPrefixPath("/", new HttpHandler() {
                                    @Override
                                    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
                                        httpServerExchange.getResponseSender().send("Hello world !");
                                    }
                                })
                                .addPrefixPath("/api/rest", new HttpHandler() {
                                    @Override
                                    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
                                        httpServerExchange.getResponseSender().send("Hello restful !");
                                    }
                                })
                                        //访问静态文件，/res/img/460.gif
                                .addPrefixPath("/res", Handlers.resource(new ClassPathResourceManager(AdvancedApp.class.getClassLoader(), "")))
                ).build();
        server.start();
    }

}
