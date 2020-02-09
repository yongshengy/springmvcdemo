package local.test.springmvc.typeconverter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/demo")
public class DemoController {

//    http://localhost:8080/demo_typeconverter_war_exploded/demo/index?date=2019-12-1
    @RequestMapping("/index")
    @ResponseBody
    public String index(Date date) {
        return date.toString();
    }
    //http://localhost:8080/demo_typeconverter_war_exploded/demo/convertUser?user=1-yy-12
    // 或者是string和对象的转换 id-name-age -> User
    @RequestMapping("/convertUser")
    @ResponseBody
    public String userConvertTest(User user) {
        return user.toString();
    }
}
