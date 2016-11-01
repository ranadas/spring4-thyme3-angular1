package com.rdas.testci;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.StartElement;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by x148128 on 05/02/2016.\
 * <p>
 * SAX: Is a stream-based processor. You only have a tiny part in memory at any time and you "sniff" the XML stream as it passes. It uses almost no memory, but you can't do "DOM" stuff, like use xpath or traverse trees.
 * <p>
 * DOM: You load the whole thing into memory - it's a massive memory hog. You can blow memory with even medium sized documents. But you can use xpath and traverse the tree etc.
 */
@Ignore
@Slf4j
public class JavaParsingTest {
    final String validXml = "<root atr1=\" someval\"> <head/> <body> </body> </root>";
    final String inValidXml = "<root> <head/> <body> </body> </root";
    final String nonWellFormedXml = "<root> <head/> <body> </body>";

    @Test(expected = SAXParseException.class)
    public void nonWellFormedThrowsSAXParseException() throws IOException, SAXException {
        saxValidate(inValidXml.getBytes());
    }

    @Test
    public void xmlWithPairedTageAreWellFormed() throws IOException, SAXException {
        saxValidate(validXml.getBytes());
    }

    private void saxValidate(byte[] xml) throws SAXException, IOException {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        parser.setContentHandler(new DefaultHandler2());
        InputSource source = new InputSource(new ByteArrayInputStream(xml));
        parser.parse(source);
    }

    /**
     * Assume the first tag is the root element
     * NOTE : <?xml version="1.0"?
     */
    @Test
    public void testStringParsing() {
        String rootElementName = validXml.substring(0, validXml.indexOf(">") + 1);
        log.debug(rootElementName);
    }

    @Test
    public void sTaXValidate() throws XMLStreamException {
        Stack<String> stackedTagNames = getNonWellformedElementsByStAx(validXml.getBytes(StandardCharsets.UTF_8));
        assertThat(stackedTagNames.capacity()).isEqualTo(0);
        stackedTagNames.empty();
    }

    @Test(expected = XMLStreamException.class)
    public void testThatNonWellFormedXmlThrowsXMLStreamException( )  throws XMLStreamException {
        Stack<String> stackedTagNames = getNonWellformedElementsByStAx(nonWellFormedXml.getBytes(StandardCharsets.UTF_8));
        assertThat(stackedTagNames.capacity()).isEqualTo(1);
        stackedTagNames.empty();
        log.debug(stackedTagNames.toString());

    }

    private Stack<String> getNonWellformedElementsByStAx(byte[] xml) throws XMLStreamException {
        Stack<String> stackOfTagNames = new Stack<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        InputStream stream = new ByteArrayInputStream(xml);
        XMLStreamReader reader = factory.createXMLStreamReader(stream);
        while (reader.hasNext()) {
            int event = reader.next();
            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String name = reader.getLocalName();
                    log.debug("start tag : {}", name);
                    stackOfTagNames.push(name);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    String name1 = reader.getLocalName();
                    log.debug("end tag : {}", name1);
                    if (stackOfTagNames.peek().equalsIgnoreCase(name1)) {
                        String popName = stackOfTagNames.pop();
                        log.debug("Removing {} ", popName);
                    }
                    break;
            }
        }

        return stackOfTagNames;
    }
}
