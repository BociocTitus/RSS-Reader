package titus.cyantrial.service.external;

import java.io.InputStream;

public interface IHttpService {
    InputStream getUrlContent(final String url);
}
