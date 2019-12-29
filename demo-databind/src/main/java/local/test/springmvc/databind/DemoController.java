package local.test.springmvc.databind;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * 数据绑定
 * 基本类型，数组，POJO，List Set Map JSON
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    // @RequestParam, @PathVariable
    @RequestMapping("/index1")
    @ResponseBody
    public String index1(int id) {
        return id+"";
    }

    // http://localhost:8080/demo_databind_war_exploded/demo/index2?name=aa&name=bb
    @RequestMapping("/index2")
    @ResponseBody
    public String index2(@RequestParam("name") String[] names) {
        return names.length + "";
    }

    // http://localhost:8080/demo_databind_war_exploded/demo/index3?name=aa&address.name=cc
    @RequestMapping("/index3")
    @ResponseBody
    public User index3(User user) {
        return user;
    }
//    http://localhost:8080/demo_databind_war_exploded/demo/index4?users%5B0%5D.name=aaa&users%5B1%5D.name=bbb
    @RequestMapping("/index4")
    @ResponseBody
    public List<User> index4 (UserList users) {
        return users.getUsers();
    }

    // 因为新版本的Tomcat进行了安全限制，需要将[]符号进行UrlEncoder
//    http://localhost:8080/demo_databind_war_exploded/demo/index5?users%5B0%5D.name=aaa&users%5B1%5D.name=bbb
    @RequestMapping("/index5")
    @ResponseBody
    public Set<User> index5 (UserSet users) {
        return users.getUsers();
    }

//    http://localhost:8080/demo_databind_war_exploded/demo/index6?users%5B%27a%27%5D.name=aa&users%5B%27b%27%5D.name=bb
//    http://localhost:8080/demo_databind_war_exploded/demo/index6?users%5B%27a%27%5D.name%3Daa%26users%5B%27b%27%5D.name%3Dbb
//    users['a'] URLEncoder转换 或者后面的参数全部转换
    @RequestMapping("/index6")
    @ResponseBody
    public String index6 (UserMap users) {
        return users.getUsers().size() + "";
    }

    //===============================
    // @RequestBody @ResponseBody
    /**
     * 如果返回结果是JSON或XML，可以使用@ResponseBody
     * 这里使用FastJson作为JSON格式的转换
     * @param user
     * @return
     */
    @RequestMapping("/index7")
    @ResponseBody
    public User index7 (User user) {
        User user1 = new User();
        user1.setName(user.getName());
        return user1;
    }

    @RequestMapping("/index8")
    @ResponseBody
    public User index8 (@RequestBody User user) {
        user.setName(user.getName() + "123");
        return user;
    }
}
