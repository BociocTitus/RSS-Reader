package titus.cyantrial.dto.external;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "description")
public class RSSDescription {
    @XmlElementWrapper(name = "ol")
    @XmlElement(name = "li")
    private List<RSSListItem> rssOrderedList;
}
