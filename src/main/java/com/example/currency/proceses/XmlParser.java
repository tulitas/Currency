package com.example.currency.proceses;

import com.example.currency.utils.FxRate;
import com.example.currency.utils.FxRates;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public static FxRates parseXml(String xmlResponse) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FxRates.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Создаем XMLInputFactory и устанавливаем флаг, чтобы он игнорировал префиксы пространства имен
            XMLInputFactory factory = XMLInputFactory.newFactory();
            factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);

            // Создаем XMLStreamReader из xmlResponse
            XMLStreamReader xmlStreamReader = factory.createXMLStreamReader(new StringReader(xmlResponse));

            // Пропускаем до элемента <FxRates>
            while (xmlStreamReader.hasNext() && !xmlStreamReader.isStartElement()) {
                xmlStreamReader.next();
            }

            Object object = unmarshaller.unmarshal(xmlStreamReader);
            return (FxRates) object;
        } catch (JAXBException | XMLStreamException e) {
            e.printStackTrace();
            return null;
        }
    }
}
