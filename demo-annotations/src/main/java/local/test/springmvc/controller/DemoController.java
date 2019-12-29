package local.test.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 测试所有注解
 * @RequestMapping @GetMapping @PostMapping
 * @RequestParam
 * @PathVariable
 * @CookieValue
 * POJO参数，自行匹配
 * 转发和重定向
 * @RequestBody
 * @ResponseBody
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    //===================================
    // @RequestMapping @GetMapping @PostMapping
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    // http://localhost:8080/demo_annotations_war_exploded/demo/index1?id=1 POST请求类型，GET会404
    @RequestMapping(value = "/index1", method = RequestMethod.POST, params ={"id=1"})
    public String index1(){
        return "index1";
    }

    @GetMapping("/index2")
    @ResponseBody
    public String index2(){
        return "index2";
    }

    @PostMapping(value = "/index3")
    @ResponseBody
    public String index3() {
        return "index3";
    }

    //=================================
    // @RequestParam @PathVariable @CookieValue
    @RequestMapping("/index01")
    @ResponseBody
    public String index01(@RequestParam(name = "name") String name) {
        System.out.println("##"+name);
        return name;
    }

    @RequestMapping("/index02")
    @ResponseBody
    public String index02(@RequestParam(value = "name", required = true, defaultValue = "yyang") String name) {
        System.out.println("##"+name);
        return name;
    }

    @RequestMapping("/index03/{name}")
    @ResponseBody
    public String index03 (@PathVariable("name") String name) {
        System.out.println("##"+name);
        return name;
    }

    @RequestMapping("/index04")
    @ResponseBody
    public String index04 (@CookieValue("name") String name) {
        System.out.println("###" + name);
        return name;
    }

    //==============================
    // POJO绑定参数

    @RequestMapping("/index001")
    @ResponseBody
    public String index001 (User user) {
        return user.getName();
    }

    // ======================================
    // @RequestBody @ResponseBody
    /**
     * 如果返回结果是JSON或XML，可以使用@ResponseBody
     * 这里使用FastJson作为JSON格式的转换
     * @param user
     * @return
     */
    @RequestMapping("/index002")
    @ResponseBody
    public User index002 (User user) {
        User user1 = new User();
        user1.setName(user.getName());
        return user1;
    }

    @RequestMapping("/index003")
    @ResponseBody
    public User index003 (@RequestBody User user) {
        user.setName(user.getName() + "123");
        return user;
    }

    // ==============================
    // redirect forward
    @RequestMapping("/index0001")
    public String index0001 () {
        return "redirect:index";
    }

    @RequestMapping("/index0002")
    public String index0002 () {
        return "forward:index";
    }

}
