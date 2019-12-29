1. 为什么单独说明
    - 如何标准实现CRUD，POST - GET - PUT - DELETE
    - 借助Spring MVC的过滤器：传统的WEB开发中，form表单只支持GET/POST请求，不支持PUT，DELETE，通过添加HiddenHttpMethodFilter过滤器，即可将POST请求转为PUT、DELETE

2. Restful
    - 为不同中断的数据访问提供统一的交互规范
    - Representational State Transfer,资源表现层状态转化
    - Resource资源，统一资源定位符URI，网络中每个资源都有特定的URI，来锁定具体的资源
    - Representational 资源表现层，比如是文字，txt，html，xml，json等
    - State Transfer: 状态转换，指客户端和服务端之间的数据交互，比如数据改变了，删除了，
    - 特点：URL更清晰，易读，CRUD对应不同的请求类型
    - HiddenHttpMethodFilter: 检测请求参数中是否包含_method这个参数，如果包含则获取其值，判断何种操作并完成请求类型的转换。
    - form: name="_method" value="PUT/DELETE"
        ```xml
        <filter>
            <filter-name>HiddenHttpMethodFilter</filter-name>
            <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
        </filter>
        
        <filter-mapping>
            <filter-name>HiddenHttpMethodFilter</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>
        ```
    
