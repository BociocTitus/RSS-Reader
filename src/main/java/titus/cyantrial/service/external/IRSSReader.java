package titus.cyantrial.service.external;

import titus.cyantrial.dto.external.RSSChannel;
import titus.cyantrial.dto.external.RSSWrapper;

import java.io.InputStream;
import java.util.List;

public interface IRSSReader {
    RSSWrapper readFromInputStream(final InputStream inputStream);
}
