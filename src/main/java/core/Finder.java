package core;

import constants.Selector;
import nodes.MyElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Finder {
    public static void main(String[] args) throws IOException {
        new Finder();
    }

    public Finder() throws IOException {
        Document doc = Jsoup.connect("https://www.google.com").get();
        System.out.println(doc.title());

        Elements elements = doc.body().children();
        List<XBuilder> xbuilders = new ArrayList<>();

        int count = 0;
        List<Object> myElements = new ArrayList<>();

        for (int i = 0; i < elements.size(); i++) {
            myElements.add(find(elements.get(i)));
        }
//        for (Element element : elements) {
//            find(element, myElements);
//        }
        for (Element element : elements) {
            Tag tag = element.tag();
            System.out.println("tag: " + tag.getName());
            Attributes attributes = element.attributes();
            for (Attribute attribute : attributes) {
                XBuilder pageobject = new XBuilder();
                xbuilders.add(pageobject.nodeWith(Selector.valueOf(tag.getName()), constants.Attribute.valueOf(attribute.getKey().toUpperCase()), attribute.getValue()));
                System.out.println("Xpath: " + pageobject.build());
            }

            if (count == 3) {
                break;
            }
            count++;

        }
        System.out.println(elements.toString());
    }

    public Element find(Element element){
        if (element.children().size() > 0){
            Element newElement = null;
            for (Element childElement : element.children()) {
                newElement = find(childElement);
            }
            return newElement;
        }
        return element;
    }


    public List<Object> find(Element element, List<Object> myMyElements) {


        if (element.children().size() > 0){
            final List<Object> newElement = new ArrayList<>();
            for (Element childElement : element.children()) {
                find(childElement, newElement);
            }
            myMyElements.add(newElement);
//            element.children().forEach(currentElement -> myMyElements.add(find(currentElement, new ArrayList<>())));


        }
        else{
            myMyElements.add(element);

        }
        System.out.println(myMyElements.toString());
        return myMyElements;
    }
}
