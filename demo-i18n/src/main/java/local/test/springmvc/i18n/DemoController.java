package local.test.springmvc.i18n;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

    // http://localhost:8080/demo_i18n_war_exploded/index?lang=zh_CN
    @RequestMapping("/index")
    public String index(Model model) {
        User user = new User();
        user.setName("yyang");
        model.addAttribute("user", user);
        return "index";
    }
}
