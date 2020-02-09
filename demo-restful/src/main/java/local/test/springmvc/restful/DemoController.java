package local.test.springmvc.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/demo")
public class DemoController {

//    非 RESTful 的 URL：http://...../queryUserById?id=1
//    RESTful 的 URL：http://..../queryUserById/1


    // http://localhost:8080/demo_restful_war_exploded/demo/1  GET
    // Read
    @GetMapping("/{id}")
    @ResponseBody
    public String testGet(@PathVariable("id") int id){
        return "GET" + id;
    }

    // http://localhost:8080/demo_restful_war_exploded/demo POST
    // Create
    @PostMapping
    @ResponseBody
    public String testPost(){
        return "POST";
    }

    // http://localhost:8080/demo_restful_war_exploded/demo/1?_method=PUT  POST 用于form提交无法区分和POST的请求，借助HiddenHttpMethodFilter
    //    http://localhost:8080/demo_restful_war_exploded/demo/1   PUT
    // Update
    @PutMapping("/{id}")
    @ResponseBody
    public String testPut(@PathVariable("id") int id){
        return "PUT" + id;
    }

    // http://localhost:8080/demo_restful_war_exploded/demo/1?_method=DELETE  POST 用于form提交无法区分和POST的请求，借助HiddenHttpMethodFilter
    // http://localhost:8080/demo_restful_war_exploded/demo/1   DELETE
    // Delete
    @DeleteMapping("/{id}")
    @ResponseBody
    public String testDelete(@PathVariable("id") int id){

        return "Delete" + id;
    }

}
