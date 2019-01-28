package com.design.maven;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author admin
 * @since 2019-01-28 16:38
 */
class JsoupTest {

    /**
     * 得到 document 对象的几种方式
     */
    @Test
    void getDocument() {
        // 1. 直接从字符串中输入 HTML 文档
        String htmlStr = "<html><head><title>学习使我快乐</title></head><body><p>好好学习，天天向上</p></body></html>";
        Document document1 = Jsoup.parse(htmlStr);

        try {
            // 2. 从文件中加载 HTML 文档
            File file = new File("src/main/resources/index.html");
            Document document2 = Jsoup.parse(file, "UTF-8", null);

            // 3. 从 URL 使用 GET 方法直接加载 HTML 文档
            Document document3 = Jsoup.connect("http://www.baidu.com/").get();

            // 4. 从 URL 使用 POST 方法直接加载 HTML 文档
            Document document4 = Jsoup.connect("http://www.baidu.com/").post();

            // 5. 从 URL 使用 execute 方法获取响应, 从响应流中解析出 HTML 文档
            Connection.Response response = Jsoup.connect("http://www.baidu.com/").execute();
            Document document5 = response.parse();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String title = document1.title();
        System.out.println(title);
    }

    @Test
    void parseUrl() {
        try {
            URL url = new URL("https://xingzhengquhua.51240.com/");

            Document document = Jsoup.parse(url, 300);

            // 解析出的 document 的对象, 可以使用像 jquery 的选择器语法去筛选想要的节点
            // 例如： cssQuery: "table.kuang_biaoge tbody table"
            //      筛选 类名为 kuang_biaoge的 table 标签下的 tbody 标签下的所有 table 标签节点
            Elements items = document.select("table.kuang_biaoge tbody table");

            // 注意 select() 方法返回的 Elements 类型, 是多个节点
            // document.getElementById() 返回的 是唯一的 Element 对象
            // 其他 document.getElementByXXXXX 返回的基本都是 Elements 类型

            Element element = items.get(0);
            Elements tr = element.select("tr");
            for (Element e : tr) {
                System.out.println(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parseString() {
        String string = "<table width=\"100%\" border=\"0\" cellpadding=\"8\" cellspacing=\"1\">\n" +
                " <tbody>\n" +
                "  <tr>\n" +
                "   <td colspan=\"3\" bgcolor=\"#FFFFFF\"><a href=\"/\">全国</a></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "   <td bgcolor=\"#F5F5F5\">所辖行政区</td>\n" +
                "   <td bgcolor=\"#F5F5F5\">行政区划代码</td>\n" +
                "   <td bgcolor=\"#F5F5F5\">城乡分类</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "   <td bgcolor=\"#FFFFFF\"><a href=\"/110000000000__xingzhengquhua/\">北京市</a></td>\n" +
                "   <td bgcolor=\"#FFFFFF\"><a href=\"/110000000000__xingzhengquhua/\">110000000000</a></td>\n" +
                "   <td bgcolor=\"#FFFFFF\"></td>\n" +
                "  </tr>\n" +
                " </tbody>\n" +
                "</table>\n" +
                "\n" +
                "Process finished with exit code 0\n";

        Document document = Jsoup.parse(string);

        Elements items = document.select("table tbody");
        Element element = items.get(0);
        Elements tr = element.select("tr");
        for (Element e : tr) {
            System.out.println(e);
        }
    }

    @Test
    void Test() {
        try {
            // Document doc = Jsoup.connect("https://search.jd.com/Search?keyword=手机&enc=utf-8&wq=手机&pvid=6e71aa1c0d654157add4cb5f08cd1f28")
            //        .header("Accept", "*/*")
            //        .header("Accept-Encoding", "gzip, deflate")
            //        .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            //        .header("Referer", "https://www.baidu.com/")
            //        .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
            //        .timeout(5000)
            //        .get();

            Document document = Jsoup.connect("https://search.jd.com/Search?keyword=手机&enc=utf-8&wq=手机&pvid=6e71aa1c0d654157add4cb5f08cd1f28").get();
            System.out.println(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Successfully created project 'jsoupdemo' on GitHub,
     * but initial commit failed: *** Please tell me who you are.
     * Run
     * git config --global user.email "you@example.com"
     * git config --global user.name "Your Name"
     * to set your account's default identity.
     * Omit
     * --global to set the identity only in this repository.
     * unable to auto-detect email address (got 'admin@mi-liushuai.(none)')
     */
}
