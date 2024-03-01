package com.example.currency.proceses;

import com.example.currency.utils.FxRates;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;

public class XmlParser {

    public static FxRates parseXml(String xmlResponse) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FxRates.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            XMLInputFactory factory = XMLInputFactory.newFactory();
            factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);

            XMLStreamReader xmlStreamReader = factory.createXMLStreamReader(new StringReader(xmlResponse));

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
