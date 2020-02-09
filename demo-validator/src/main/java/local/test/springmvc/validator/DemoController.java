package local.test.springmvc.validator;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class DemoController {

    // http://localhost:8080/demo_validator_war_exploded/login?id=1&age=10
    @GetMapping("/login")
    @ResponseBody
    public String login(@Validated User user, BindingResult br) {
        if (br.hasErrors()) {
            return br.getFieldError("name").toString();
        } else {
            return user.toString();
        }
    }

    // http://localhost:8080/demo_validator_war_exploded/login1?id=1&age=40
    @GetMapping("/login1")
    @ResponseBody
    public String loginWithJsr(@Valid User user, BindingResult br) {
        if (br.hasErrors()) {
            return br.getFieldError("age").toString();
        } else {
            return user.toString();
        }
    }


    // 页面上借助form标签  form:input form:errors path=name

}
