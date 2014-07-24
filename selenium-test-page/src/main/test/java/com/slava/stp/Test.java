package com.slava.stp;

import com.slava.stp.shell.SeleniumShell;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class Test {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        SeleniumTestPageRunner.main(new String[]{
                "-f", "./tests", "-t", "public_main\\.xml"
        });

        SeleniumShell.getInstance().run("", "arg0", "arg1", "arg2");
    }
}
