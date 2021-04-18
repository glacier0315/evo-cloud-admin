package com.glacier.modules.gen.test;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author glacier
 * @version 1.0
 * date 2021-01-28 10:53
 */
public class FreemarkerTest {

    @Test
    public void test0() throws Exception {
        //1，创建FreeMarker的配置类
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        //2，指定模板加载器，将模板加入缓存中
        //文件路径加载器，获取到templates文件的路径
        String templates = Objects.requireNonNull(this.getClass()
                .getClassLoader()
                .getResource("templates"))
                .getPath();
        FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(templates, "ftl"));
        cfg.setTemplateLoader(fileTemplateLoader);
        //3，获取模板
        Template template = cfg.getTemplate("test.ftl");
        //4，构造数据模型
        Map<String, Object> map = new HashMap<>(16);
        map.put("username", "测试人员");
        map.put("password", 1234);

        List<String> list = new ArrayList<>(16);
        list.add("第一个");
        list.add("第二个");
        map.put("list", list);

        //5，文件输出
        /**
         * 处理模型
         *      参数一 数据模型
         *      参数二 writer对象（FileWriter（文件输出），printWriter（控制台输出））
         */
//        template.process(map,new FileWriter(new File("D:\\a.txt")));
        template.process(map, new PrintWriter(System.out));
    }
}
