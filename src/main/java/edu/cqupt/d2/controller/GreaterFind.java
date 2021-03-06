package edu.cqupt.d2.controller;


import edu.cqupt.d2.service.ArticleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 增强Post和Article的存取，实现文件的传输
 */
@Controller
public class GreaterFind {

    static final String postType = "E:\\Post";
    static final String articleType = "E:\\Article";
    static final String headType = "E:\\Head";
    static final String spliter = "\\";

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        ArticleService testService = (ArticleService) ctx.getBean("articleService");


        // convert file to byte[]
//        byte[] bFile = readBytesFromFile(articleType, "test.html");
//        Article article = testService.getAllByNo(1);
//        article.setArticle_Data(bFile);
//        String articleTime = article.getArticle_Time();
//        System.out.println(articleTime);
//        writeBytesToFile(article, articleType);


    }

    /**
     * 生成存取路径
     * @param article
     * @return
     */
    public static String getArticlePath(String article) {
        return articleType + spliter + convert2long(article) + ".html";
    }
    public static String getPostPath(String post) {
        return postType + spliter + convert2long(post) + ".html";
    }

    public static String getHeadPath(String headname,String uid) {
        return headType + spliter + uid + headname.substring(headname.lastIndexOf("."));
    }
    /**
     * 根据路径取得字节码文件
     * @param filePath
     * @return
     */
    public static byte[] readFile(String filePath) {
        byte[] bFile = null;
        try {
//            bFile = Files.readAllBytes(new File(filePath).toPath());
            bFile = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bFile;
    }


    /**
     * 传入路径和字节码文件，存储文件至指定位置
     * @param bFile
     * @param Spath
     */
    public static void writeBytesToFile(byte[] bFile, String Spath) {
        try {
            Path path = Paths.get(Spath);
            Files.write(path, bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //string转long，便于命名
    public static long convert2long(String date) {
        try {
            if (StringUtils.isNotBlank(date) && StringUtils.isNotBlank("yyyy-MM-dd HH:mm:ss")) {
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sf.parse(date).getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}



