package local.test.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC simple demo:
 * 名词：DispatcherServlet - Handler(Controller) - HandlerMapping - HandlerInterceptor -
 * HandlerExecutionChain - HandlerAdapter - ModelAndView - ViewResolver
 * 工作流程...
 * 开发：Handler(Controller,处理业务逻辑) - View(展示)
 */
@Controller
public class HelloController {

    @RequestMapping("/index")
    public String index(){
        System.out.println("Hello - Index");
        return "index";
    }

}
