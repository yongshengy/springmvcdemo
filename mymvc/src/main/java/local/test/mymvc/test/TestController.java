package local.test.mymvc.test;

import local.test.mymvc.MyController;
import local.test.mymvc.MyRequestMapping;

@MyController
@MyRequestMapping("/testController")
public class TestController {

    @MyRequestMapping("/test")
    public String test() {
        System.out.println("this is the test...");
        return "test";
    }
}
