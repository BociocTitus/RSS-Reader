package titus.cyantrial.service.external.implementation;

import org.springframework.stereotype.Service;
import titus.cyantrial.dto.external.RSSChannel;
import titus.cyantrial.dto.external.RSSItem;
import titus.cyantrial.dto.external.RSSWrapper;
import titus.cyantrial.service.external.IRSSReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.List;

@Service
public class JaxbXmlReader implements IRSSReader {

    @Override
    public RSSWrapper readFromInputStream(final InputStream inputStream) {
        final JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(RSSWrapper.class);
            final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (RSSWrapper) jaxbUnmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
