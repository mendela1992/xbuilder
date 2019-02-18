import constants.Attribute;
import constants.Function;
import constants.Selector;

import java.util.LinkedList;

import static constants.Character.*;

/**
 *
 */
public class XBuilder {

    private final LinkedList<Object> xpathBuilder;
    private boolean history = false;
    private final LinkedList<String> xpathBuilderHistory;

    XBuilder() {
        this.xpathBuilder = new LinkedList();
        xpathBuilderHistory = null;
    }

    XBuilder(boolean history) {
        this.xpathBuilder = new LinkedList();
        this.xpathBuilderHistory = new LinkedList();
        this.history = history;
    }

    /**
     * Generate new xpathBuilder
     *
     * @param selector  Selector value
     * @param function  function value
     * @param attribute attribute value
     * @param value     value
     * @return Instance to XBuilder
     */
    public XBuilder nodeWith(Selector selector, Function function, Attribute attribute, String value) {
        this.xpathBuilder.clear();
        return this.with(selector, function, attribute, value);
    }

    /**
     * Generate new Xpath without function
     *
     * @param selector  Selector value
     * @param attribute attribute value
     * @param value     value
     * @return Instance to XBuilder
     */
    public XBuilder nodeWith(Selector selector, Attribute attribute, String value) {
        this.xpathBuilder.clear();
        return this.with(selector, attribute, value);
    }

    /**
     * Generate or add xpathBuilder
     *
     * @param selector  Selector value
     * @param function  function value
     * @param attribute attribute value
     * @param value     value
     * @return Instance to XBuilder
     */
    public XBuilder with(Selector selector, Function function, Attribute attribute, String value) {
        this.xpathBuilder.add(DoubleSlash.getValue());

        if (selector == null) {
            throw new NullPointerException("Selector can not be null");
        }
        if (selector == Selector.any)
            this.xpathBuilder.add(all.getValue());
        else
            this.xpathBuilder.add(selector);


        this.xpathBuilder.add(OpenSquareBracket.getValue());

        if (function != null) {
            this.xpathBuilder.add(function);
            this.xpathBuilder.add(OpenBracket.getValue());
        }

        if (attribute == null) {
            throw new NullPointerException("Attribute can not be null");
        }
        if (attribute == Attribute.TEXT) {
            this.xpathBuilder.add(attribute.name().toLowerCase());
            this.xpathBuilder.add(OpenBracket.getValue());
            this.xpathBuilder.add(CloseBracket.getValue());
        } else {
            this.xpathBuilder.add(At.getValue());
            this.xpathBuilder.add(attribute.name().toLowerCase());
        }

        if (value != null) {
            if (function != null) {
                this.xpathBuilder.add(Comma.getValue());
            } else {
                this.xpathBuilder.add(Equal.getValue());
            }


            this.xpathBuilder.add(Quote.getValue());
            this.xpathBuilder.add(value);
            this.xpathBuilder.add(Quote.getValue());
        }

        if (function != null) {
            this.xpathBuilder.add(CloseBracket.getValue());
        }
        this.xpathBuilder.add(CloseSquareBracket.getValue());

        return this;
    }

    /**
     * Generate or add Xpath without function
     *
     * @param selector  Selector value
     * @param attribute attribute value
     * @param value     value
     * @return Instance to XBuilder
     */
    public XBuilder with(Selector selector, Attribute attribute, String value) {
        this.with(selector, null, attribute, value);
        return this;
    }

    /**
     * Include previous expression between bracket
     *
     * @param index index value
     * @return Instance to XBuilder
     */
    public XBuilder bracket(int index) {
        this.xpathBuilder.add(0, OpenBracket.getValue());
        this.xpathBuilder.add(this.xpathBuilder.size(), CloseBracket.getValue());
        return this.index(index);
    }

    /**
     * Index your xpathBuilder
     *
     * @param index index value
     * @return Instance to XBuilder
     */
    public XBuilder index(int index) {
        this.xpathBuilder.add(OpenSquareBracket.getValue());
        this.xpathBuilder.add(index);
        this.xpathBuilder.add(CloseSquareBracket.getValue());
        return this;
    }

    /**
     * Output Xpath value
     *
     * @return value of xpathBuilder
     */
    public String build() {
        if (this.xpathBuilder.isEmpty()) {
            throw new NullPointerException("XBuilder builder is empty");
        }

        StringBuilder path = new StringBuilder();
        for (Object element :
                this.xpathBuilder) {
            path.append(element);
        }

        if (history){
            xpathBuilderHistory.add(path.toString());
        }

        return path.toString();
    }
}
