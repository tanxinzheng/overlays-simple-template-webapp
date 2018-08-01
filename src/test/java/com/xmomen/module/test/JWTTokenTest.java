package com.xmomen.module.test;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tanxinzheng on 17/6/12.
 */
public class JWTTokenTest {

    @Test
    public void test() throws Exception{
        String username = "admin";
        Key key = MacProvider.generateKey();
        String compactJws = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 1000))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        System.out.println(compactJws);
        Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws);
    }

    @Test
    public void test2() throws Exception{
        System.out.println(UUID.randomUUID().toString());
        System.out.println(RandomStringUtils.randomNumeric(32));
        System.out.println(RandomStringUtils.randomAlphabetic(32));
        System.out.println(RandomStringUtils.randomAscii(32));
    }

    @Test
    public void test3() throws Exception{
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String password = bCryptPasswordEncoder.encode("111111");
//        System.out.println(password);
//        bCryptPasswordEncoder.
        parse();
//        download("https://fonts.googleapis.com/css?family="+ URLEncoder.encode("Roboto:400,500,700,400italic|Material+Icons", "utf-8"));
    }

    private void parse(){
        try {
            String file = FileUtils.readFileToString(new File("/Users/jeng/xmomen-repo/webapp/overlays-simple-template-webapp/website/src/assets/font.css"));
            String regex = "url\\((.*?)\\)";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(file);
            List<String> list = Lists.newArrayList();
            while (m.find()) {
                String url = m.group().replace("url(", "").replace(")", "");
                list.add(url);
                System.out.println(url);
                String[] strings = url.split("/");
                download(url, strings[strings.length - 1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean download(String url, String filename) {
        HttpClient httpClient1 = new DefaultHttpClient();
        HttpGet httpGet1 = new HttpGet(url);
        try {
            HttpResponse httpResponse1 = httpClient1.execute(httpGet1);
            StatusLine statusLine = httpResponse1.getStatusLine();
            if (statusLine.getStatusCode() == 200) {
                String filePath = "/Users/jeng/xmomen-repo/webapp/overlays-simple-template-webapp/src/test/resources/"+filename; // 文件路径
                File file = new File(filePath);
                FileOutputStream outputStream = new FileOutputStream(file);
                InputStream inputStream = httpResponse1.getEntity()
                        .getContent();
                byte b[] = new byte[1024];
                int j = 0;
                while ((j = inputStream.read(b)) != -1) {
                    outputStream.write(b, 0, j);
                }
                outputStream.flush();
                outputStream.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            httpClient1.getConnectionManager().shutdown();
        }
        return true;
    }
}
