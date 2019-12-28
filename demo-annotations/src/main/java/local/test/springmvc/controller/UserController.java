package local.test.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    // @RequestMapping
    // @RequestParam
    // @PathVariable
    // @RequestBody
    // ResponseBody

    // request methods
    // CRUD PUT GET POST DELETE
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        return "Index";
    }

    @GetMapping("/get")
    @ResponseBody
    public String get(){
        return "GET!!";
    }
    @PostMapping("/post")
    @ResponseBody
    public String post(){
        return "POST!!";
    }

    @GetMapping(value = "/get", params = {"name", "id=0"})
    @ResponseBody
    public String getByName(){
        return "GET-ID=0";
    }

    // params

    @GetMapping(value = "/get1")
    @ResponseBody
    public String getByName1(@RequestParam(value="name", required = true, defaultValue = "yang") String name) {
        return name;
    }

    @GetMapping(value = "/get2/{name}")
    @ResponseBody
    public String getByName2(@PathVariable("name") String name) {
        return name;
    }

    // cookie

    @RequestMapping("/cookie")
    @ResponseBody
    public String cookietest(@CookieValue("JSESSIONID") String sessionid){
        return sessionid;
    }

    // bind pojo as param

    @GetMapping("/add")
    public String add(){
        return "add";

    }

    @PostMapping("/addUser")
    @ResponseBody
    public String addUser(User user){
        return user.getAddress().getName();
        // encode problem - filter: CharacterEncodingFilter
    }

    // redirect/forward
    @GetMapping("/add1")
    public String add1() {
//        return "redirect:/user/add";
        return "redirect:add";
    }

    @GetMapping("/add2")
    public String add2(){
        return "forward:add";
    }



}
