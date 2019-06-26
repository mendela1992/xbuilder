

import core.XBuilder;
import org.junit.jupiter.api.Test;

import static constants.Attribute.*;
import static constants.Function.*;
import static constants.Selector.*;

class ReadHTMLTest {

    @Test
    void xpath()  {

        XBuilder pageobject = new XBuilder();

        String xpath = pageobject.nodeWith(table, CLASS, "nelson").build();

        System.out.println(pageobject.nodeWith(div, CLASS, "core.XBuilder").
                with(span, Contains, TEXT, "Dick").
                with(any, not, HREF, "Login")
                .bracket(2).build());
    }
}