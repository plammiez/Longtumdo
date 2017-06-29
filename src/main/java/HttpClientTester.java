import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Waraporn on 8/19/2016.
 */
public class HttpClientTester {

    public static void main(String... args) throws Exception{

//        URL url = new URL("http://www.dictionary.com/browse/responsibility");
//        URLConnection connection = url.openConnection();
//        System.out.println(connection.getClass().getName());
//
//        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
//
//        if (httpURLConnection.getResponseCode() !=
//                java.net.HttpURLConnection.HTTP_OK) {
//            throw new IOException("Cannot connect");
//        }
        InputStream inputStream = getInputStreamFromUrl("http://www.dictionary.com/browse/responsibility");

//        HttpClient httpClient = new HttpClient();
//        GetMethod getMethod = new GetMethod("http://www.pantip.com");
//        httpClient.executeMethod(getMethod);
//        InputStream inputStream = getMethod.getResponseBodyAsStream();

        FileOutputStream outputStream = new FileOutputStream("pantip-index.html");

        int readChar;
        byte[] buffer = new byte[1024];

        while ((readChar = inputStream.read(buffer, 0, buffer.length)) > 0 ){
            outputStream.write(buffer, 0, readChar);
        }

        inputStream.close();
        outputStream.close();
    }

    public static InputStream getInputStreamFromUrl(String urlspec) throws IOException {

        URL url = new URL("http://www.dictionary.com/browse/responsibility");
        URLConnection connection = url.openConnection();
        System.out.println(connection.getClass().getName());

        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        if (httpURLConnection.getResponseCode() !=
                java.net.HttpURLConnection.HTTP_OK) {
            throw new IOException("Cannot connect");
        }
        return connection.getInputStream();
    }

    public static InputStream getInputStreamFromUrlV2(String urlspec) throws IOException {

        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(urlspec);

        int result = httpClient.executeMethod(getMethod);
        System.out.println(result);

        if (result != HttpStatus.SC_OK) {
            throw new IOException("Cannot connect");
        }
        return getMethod.getResponseBodyAsStream();
    }
}
