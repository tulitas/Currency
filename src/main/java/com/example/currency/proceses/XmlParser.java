package com.example.currency.proceses;

import com.example.currency.utils.CcyAmt;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public static List<CcyAmt>  parseXml(String xmlResponse) {
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);

            XMLStreamReader xmlStreamReader = factory.createXMLStreamReader(new StringReader(xmlResponse));

            List<CcyAmt> ccyAmtList = new ArrayList<>();
            CcyAmt ccyAmt = null;
            boolean isSecondCcyAmt = false;

            while (xmlStreamReader.hasNext()) {
                int event = xmlStreamReader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = xmlStreamReader.getLocalName();
                    if ("CcyAmt".equals(elementName)) {
                        if (isSecondCcyAmt) {
                            ccyAmt = new CcyAmt();
                        }
                        isSecondCcyAmt = !isSecondCcyAmt;
                    } else if (ccyAmt != null && "Ccy".equals(elementName)) {
                        ccyAmt.setCcy(xmlStreamReader.getElementText());
                    } else if (ccyAmt != null && "Amt".equals(elementName)) {
                        ccyAmt.setAmt(xmlStreamReader.getElementText());
                    }
                } else if (event == XMLStreamConstants.END_ELEMENT) {
                    String elementName = xmlStreamReader.getLocalName();
                    if ("CcyAmt".equals(elementName)) {
                        if (ccyAmt != null) {
                            ccyAmtList.add(ccyAmt);
                            ccyAmt = null;
                        }
                    }
                }
            }
            return ccyAmtList;
        } catch ( XMLStreamException e) {
            e.printStackTrace();
            return null;
        }
    }
}
