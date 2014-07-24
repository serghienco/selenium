package com.slava.stp.sax.helpers;

import com.slava.stp.exception.NotImplementedTagException;
import com.slava.stp.tagbuilder.SeleniumTagBuilder;
import com.slava.stp.tagbuilder.Tag;
import com.slava.stp.util.FileUtil;
import com.slava.stp.util.StringUtil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TagBuilderHandler extends DefaultHandler {

    private static final XMLOutputFactory factory = XMLOutputFactory.newInstance();

    private static final String TAG_CLASS_PATERN = SeleniumTagBuilder.class.getPackage().getName() + ".tag.{0}Tag";

    private LinkedList<Tag> tagsStack;
    private XMLStreamWriter writerXml;
    private BufferedWriter writerGroovy;

    private String absoluteClassName;
    private String lastModified;
    private File outputXml;
    private File outputGroovy;
    private int level;

    public TagBuilderHandler(String lastModified, String absoluteClassName, File outXml, File outGroovy) throws IOException {
        this.lastModified = lastModified;
        this.absoluteClassName = absoluteClassName;
        this.outputXml = outXml;
        this.outputGroovy = outGroovy;
        tagsStack = new LinkedList<Tag>();
    }

    private Tag getTagClass(String name) {
        String classPath = MessageFormat.format(TAG_CLASS_PATERN, StringUtil.toUpperCaseFirst(name));
        try {
            return ((Tag) Class.forName(classPath).newInstance());
        } catch (Exception e) {
            throw new NotImplementedTagException(classPath);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        try {
            FileUtil.newFile(outputGroovy);
            FileUtil.newFile(outputXml);
            writerGroovy = FileUtil.getWriterUTF8(outputGroovy);
            writerXml = factory.createXMLStreamWriter(new FileOutputStream(outputXml), "UTF-8");
            writerXml.writeStartDocument("UTF-8", "1.0");
        } catch (Exception e) {
            closeStreams();
            throw new SAXException(e.getMessage(), e);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        try {
            writerXml.writeEndDocument();
        } catch (XMLStreamException e) {
            throw new SAXException(e.getMessage(), e);
        } finally {
            closeStreams();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        level++;
        Tag tag = getTagClass(qName);
        tagsStack.add(tag);
        tag.setStack(tagsStack);
        tag.setName(qName);
        tag.setAttributes(attributes);
        tag.setAbsoluteClassName(absoluteClassName);
        try {
            tag.init();
            doBeginChildElement();
            tag.doBeginTag(writerGroovy);
            writerXml.writeStartElement(qName);
            for (int i = 0, len = attributes.getLength(); i < len; i++) {
                writerXml.writeAttribute(attributes.getQName(i), attributes.getValue(i));
            }
            if (level == 1) {
                writerXml.writeAttribute("lastModified", lastModified);
            }
        } catch (Exception e) {
            closeStreams();
            throw new SAXException(e.getMessage(), e);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            tagsStack.getLast().doEndTag(writerGroovy);
            doEndChildElement();
            writerXml.writeEndElement();
        } catch (Exception e) {
            closeStreams();
            throw new SAXException(e.getMessage(), e);
        }
        tagsStack.removeLast();
        level--;
    }

    private void doEndChildElement() throws Exception {
        try {
            Iterator<Tag> it = tagsStack.descendingIterator();
            it.next();
            it.next().doEndChildElement(writerGroovy);
        } catch (NoSuchElementException e) {
        }
    }

    private void doBeginChildElement() throws Exception {
        try {
            Iterator<Tag> it = tagsStack.descendingIterator();
            it.next();
            it.next().doBeginChildElement(writerGroovy);
        } catch (NoSuchElementException e) {
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch, start, length);
        try {
            if (!StringUtil.isEmptyOrNull(text)) {
                tagsStack.getLast().doText(writerGroovy, text);
                writerXml.writeCharacters(ch, start, length);
            }
        } catch (Exception e) {
            closeStreams();
            throw new SAXException(e.getMessage(), e);
        }
    }

    private void closeStreams() {
        if (writerGroovy != null) {
            try {
                writerGroovy.close();
            } catch (IOException e1) {
            }
        }
        if (writerXml != null) {
            try {
                writerXml.close();
            } catch (XMLStreamException e1) {
            }
        }
    }
}
