package service.impl;

import service.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String helloDubbo() {
        System.out.println("hello dubbo?");
        return "dubbo";
    }
}
