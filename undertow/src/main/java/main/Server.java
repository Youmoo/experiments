package main;

import com.alibaba.fastjson.JSON;
import dao.BannerDao;
import dao.impl.BannerDaoImpl;
import domain.Banner;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.util.Headers;
import util.Datty;

/**
 * @author youmoo
 * @since 2014-09-16 11:27 AM
 */
public class Server {
    public static void main(String[] args) {
        final BannerDao bannerDao = new BannerDaoImpl();
        Undertow server = Undertow.builder()
                .addHttpListener(8060, "localhost")
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
                                        httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json;charset=utf-8");
                                        Banner banner = bannerDao.get(Datty.toDate("2014-09-01 00:00:00"));
                                        httpServerExchange.getResponseSender().send(JSON.toJSONString(banner));
                                    }
                                })
                                        //访问静态文件，/res/img/460.gif
                                .addPrefixPath("/res", Handlers.resource(new ClassPathResourceManager(Server.class.getClassLoader(), "")))
                ).build();
        server.start();
    }

}
