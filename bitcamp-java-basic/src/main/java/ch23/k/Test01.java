package ch23.k;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test01 {
  public static void main(String[] args) throws Exception {
    String url = "/aaa/okok/a%21%22%23.gif";
    System.out.println(URLDecoder.decode(url,"UTF-8"));
    System.out.println(URLEncoder.encode(url,"UTF-8"));
    
    File f = new File("./webroot", URLDecoder.decode(url,"UTF-8"));
    System.out.println(f.getCanonicalPath());
  }
}
