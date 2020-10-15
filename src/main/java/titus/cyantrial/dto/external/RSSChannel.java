package titus.cyantrial.dto.external;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

@Getter
public class RSSChannel {
    @XmlElement(name="title")
    private String title;

    @XmlElement(name="item")
    private ArrayList<RSSItem> items;
}
