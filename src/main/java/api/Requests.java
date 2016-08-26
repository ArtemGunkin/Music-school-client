package api;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Requests {
    private static HttpClient client = HttpClientBuilder.create().build();
    private static final String BASE_URL = "http://localhost:8080/api/";

    public static String getAllData(String entity) throws IOException {
        return basic(entity);
    }

    public static String getProfileData(String login, String password) {
        return basicAuth(login, password, "profile", false);
    }

    public static String addSchool(String login, String password, int schoolId) {
        return basicAuth(login, password, "schools/" + schoolId + "/add", true);
    }

    private static String basicAuth(String login, String password, String endpoint, boolean isPost) {
        try {

            String line;
            String url = BASE_URL + endpoint;
            HttpRequestBase request;
            if (isPost)
                request = new HttpPost(url);
            else
                request = new HttpGet(url);
            if (login != null && password != null)
                request.addHeader("Authorization", "Basic " + base64(login, password));

            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();

            while ((line = rd.readLine()) != null)
                result.append(line);
            if (response.getStatusLine().getStatusCode() == 401)
                return null;
            System.out.println(result.toString());
            return result.toString();


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String basic(String endpoint) throws IOException {
        return basicAuth(null, null, endpoint, false);
    }

    private static String base64(String login, String password) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString((login + ":" + password).getBytes("utf-8"));
    }
}
