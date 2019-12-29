package local.test.springmvc.modelandview;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/demo")
@SessionAttributes("name")
public class DemoController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * Model and View
     * Model - 域对象 request session
     * View
     * Map Model
     * ModelAndView - 包含视图
     * @SessionAttribute
     * @ModelAttribute - 可以包含视图 也可以不包含
     */

    @RequestMapping("/index1")
    public String index1(Map<String, Object> map) {
        map.put("name", "yyang");
        return "index";
    }

    @RequestMapping("/index2")
    public String index2(Model model) {
        model.addAttribute("name", "yyang2");
        return "index";
    }

    @RequestMapping("/index3")
    public ModelAndView index3() {
        // map, key=value
        // 有多种组织ModelAndView对象的形式，参照构造函数
        Map<String, Object> map = new HashMap<>();
        map.put("name", "yyang3");
        View view = new InternalResourceView("/index.jsp");
        return new ModelAndView(view, map);//new ModelAndView("index", map)
    }

    @RequestMapping("/index4")
    public String index4(HttpServletRequest request) {
        request.setAttribute("name", "yyang4");//?
        return "index";
    }

    /**
     * 可以是map，Model参数，或者无参，直接返回User类型，key=user
     * @param map
     */
    @ModelAttribute
    public void getName(Map<String, Object> map) {
        map.put("name1", "yys");
    }
}
