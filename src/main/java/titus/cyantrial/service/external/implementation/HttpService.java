package titus.cyantrial.service.external.implementation;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import titus.cyantrial.service.external.IHttpService;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class HttpService implements IHttpService {

    private static HttpURLConnection createConnection(final String urlString) throws IOException {
        final URL url = new URL(urlString);
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        return con;
    }

    @SneakyThrows
    @Override
    public InputStream getUrlContent(final String url) {
        try {
            return createConnection(url).getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
