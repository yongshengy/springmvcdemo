package local.test.mymvc;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class MyDispatcherServlet extends HttpServlet {

    // mock IOC container
    private Map<String, Object> iocController = new HashMap<>();

    // mock handler mapping
    private Map<String, Method> handlerMapping = new HashMap<>();

    // custom view resolver
    private MyViewResolver myViewResolver;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 根据配置文件收集Controller，保存到iocContainer
        // Controller的注解映射@MyController
        // 将Controller中的Method和注解@MyRequestMapping进行关联，保存到HandlerMapping
        // 根据bean中的class属性获取实例化的MyViewResolver
        //
        saveController(config);
        initHandlerMapping();
        loadViewResolver(config);

    }

    private void loadViewResolver(ServletConfig config) {
        try {
            SAXReader reader = new SAXReader();
            InputStream inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream(config.getInitParameter("contextConfigLocation"));
            Document document = reader.read(inputStream);
            Element rootElement = document.getRootElement();
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                if (element.getName().equals("bean")) {
                    String aClass = element.attributeValue("class");
                    Class<?> aClass1 = Class.forName(aClass);
                    Object instance = aClass1.newInstance();
                    Iterator<Element> iterator1 = element.elementIterator();
                    Map<String, String> propertyMap = new HashMap<>();
                    while (iterator1.hasNext()) {
                        Element element1 = iterator1.next();
                        propertyMap.put(element1.attributeValue("name"), element1.attributeValue("value"));
                    }

                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor("prefix", aClass1);
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    writeMethod.invoke(instance, propertyMap.get("prefix"));

                    PropertyDescriptor propertyDescriptor2 = new PropertyDescriptor("suffix", aClass1);
                    Method writeMethod2 = propertyDescriptor2.getWriteMethod();
                    writeMethod2.invoke(instance, propertyMap.get("suffix"));
                    myViewResolver = (MyViewResolver) instance;

                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * based the Controller map, then scan the methods for mappings
     */
    private void initHandlerMapping() {
        for (String key : iocController.keySet()) {
            Class<?> aClass = iocController.get(key).getClass();
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(MyRequestMapping.class)) {
                    MyRequestMapping annotation = method.getAnnotation(MyRequestMapping.class);
                    String value = annotation.value().substring(1);
                    handlerMapping.put(value, method);
                }
            }

        }
    }

    /**
     * scan controller and save the controller beans
     * @param config
     */
    private void saveController(ServletConfig config){
        SAXReader reader = new SAXReader();
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream(config.getInitParameter("contextConfigLocation"));

        try {
            Document document
                    = reader.read(inputStream);
            Element rootElement = document.getRootElement();
            Iterator<Element> elementIterator = rootElement.elementIterator();
            while(elementIterator.hasNext()) {
                Element element = elementIterator.next();
                if (element.getName().equals("component-scan")) {
                    String packageName = element.attributeValue("base-package");
                    List<String> clazz = getClassNames(packageName);
                    for (String className : clazz) {
                        Class<?> aClass = Class.forName(className);
                        if (aClass.isAnnotationPresent(MyController.class)) {
                            MyRequestMapping annotation = aClass.getAnnotation(MyRequestMapping.class);
                            String value = annotation.value().substring(1);
                            iocController.put(value, aClass.newInstance());
                        }

                    }

                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * get classes from scan folder
     * @param packageName
     * @return
     */
    private List<String> getClassNames(String packageName) {
        List<String> clazz = new ArrayList<>();
        String packagePath = packageName.replace(".", System.getProperty("file.separator"));
        URL url = Thread.currentThread().getContextClassLoader().getResource(packagePath);
        if (url != null) {
            File file = new File(url.getPath());
            File[] files = file.listFiles();
            for (File file1 : files) {
                String className = packageName + "." + file1.getName().replace(".class", "");
                clazz.add(className);
            }
        }
        return clazz;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if(req.getRequestURI().endsWith(".jsp")) {
                super.doPost(req, resp);
            }
            String handlerUri = req.getRequestURI().split("/")[2];
            Object o = iocController.get(handlerUri);
            String methodUri = req.getRequestURI().split("/")[3];
            Method method = handlerMapping.get(methodUri);
            String s = (String) method.invoke(o);
            String result = myViewResolver.jspMapping(s);
            req.getRequestDispatcher(result).forward(req, resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
