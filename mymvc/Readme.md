# 实现Spring MVC
- 技术：XML解析+反射 
- 原理：前置控制器DispatcherServlet，作为核心，由它调用其他组件，共同完成业务。
- 主要组件：
    - Controller - 调用其业务方法Method，执行业务逻辑
    - ViewResolver - 将业务方法返回值解析为物理视图+模型数据
    


