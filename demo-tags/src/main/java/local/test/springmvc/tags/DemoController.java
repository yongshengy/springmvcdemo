package local.test.springmvc.tags;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {


    // http://localhost:8080/demo_tags_war_exploded/get
    @RequestMapping("/get")
    public String get(Model model) {
        User user = new User();
        user.setId(1);
        user.setAge(20);
        user.setName("yyang");
        user.setGender("Man");
        model.addAttribute("user", user);
        return "index";
    }
}
