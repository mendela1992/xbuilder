
import org.junit.Test;

import java.io.IOException;

import static constants.Attribute.*;
import static constants.Function.*;
import static constants.Selector.*;

public class ReadHTMLTest {

    @Test
    public void xpath() throws IOException {

        XBuilder pageobject = new XBuilder();

        String xpath = pageobject.nodeWith(table, CLASS, "nelson").build();

        System.out.println(pageobject.nodeWith(div, CLASS, "XBuilder").
                with(span, Contains, TEXT, "Dick").
                with(any, not, HREF, "Login")
                .bracket(2).build());
    }
}