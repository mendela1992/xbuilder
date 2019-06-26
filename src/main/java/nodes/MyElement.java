package nodes;

import constants.Selector;
import core.XBuilder;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.util.ArrayList;
import java.util.List;

public class MyElement {
    private Element element;

    public MyElement(Element element) {
        this.element = element;
    }

    public List<XBuilder> buildXpath(){
        List<XBuilder> possibilities = new ArrayList<>();
        Tag tag = element.tag();
        System.out.println("tag: " + tag.getName());
        Attributes attributes = element.attributes();
        for (Attribute attribute : attributes) {
            possibilities.add( new XBuilder().nodeWith(Selector.valueOf(tag.getName()), constants.Attribute.valueOf(attribute.getKey().toUpperCase()), attribute.getValue()));
        }
        return possibilities;
    }
}
