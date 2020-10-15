package titus.cyantrial.dto.external;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="rss")
public class RSSWrapper {

    @XmlElement(name="channel")
    private RSSChannel rssChannel;

    @XmlAttribute(name="version")
    private String version;
}
