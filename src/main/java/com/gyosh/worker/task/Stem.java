package com.gyosh.worker.task;

import com.gyosh.worker.Utility;
import org.apache.log4j.Logger;
import org.reficio.ws.builder.*;
import org.reficio.ws.builder.core.Wsdl;
import org.reficio.ws.client.core.SoapClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Stem implements Task {
    public static final String TASK_NAME = "Stem";

    private static final String WSDL_URI = "http://fws.cs.ui.ac.id/Stemmer/Stemmer?wsdl";
    private static final String END_POINT_URI = "http://fws.cs.ui.ac.id:80/Stemmer/Stemmer";
    private static final String PORT_BINDING = "StemmerPortBinding";
    private static final String SOAP_ACTION = "";
    private static final String SOAP_ACTION_NAME = "StemSentence";
    private static final String REQUEST_FIELD = "sentence";
    private static final String RESPONSE_FIELD = "return";
    private static final Logger logger = Logger.getLogger(Stem.class);

    private Document requestXml;
    private SoapClient client;
    private Transformer transformer;
    private DocumentBuilder docBuilder;
    private int stringProcessed;
    private int totalString;

    public List<List<String>> exec(List<List<String>> doc) {
        stringProcessed = 0;
        totalString = doc.size();

        initSoapClient();
        initRequestBuilder();
        initRequestEditor();

        for (int i = 0; i < doc.size(); i++) {
            doc.set(i, stemSentence(doc.get(i)));
            stringProcessed++;
        }

        return doc;
    }

    private void initSoapClient() {
        client = SoapClient.builder()
                .endpointUri(END_POINT_URI)
                .build();
    }

    private void initRequestBuilder() {
        Wsdl wsdl = Wsdl.parse(WSDL_URI);
        SoapBuilder builder = wsdl.binding()
                .localPart(PORT_BINDING)
                .find();

        SoapOperation operation = builder.operation()
                .soapAction(SOAP_ACTION)
                .name(SOAP_ACTION_NAME)
                .find();

        String request = builder.buildInputMessage(operation);
        InputStream is = new ByteArrayInputStream(request.getBytes());

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            requestXml = docBuilder.parse(is);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private void initRequestEditor() {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            transformer = transformerFactory.newTransformer();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private List<String> stemSentence(List<String> tokens) {
        Node word = requestXml.getElementsByTagName(REQUEST_FIELD).item(0);
        word.setTextContent(Utility.join(tokens, " "));

        String request = "";
        try {
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(requestXml), new StreamResult(writer));
            request = writer.getBuffer().toString();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        String response = client.post(request);
        return parseResponse(response);
    }

    private List<String> parseResponse(String response) {
        InputStream is = new ByteArrayInputStream(response.getBytes());
        Document responseXml = null;
        String ret = "";
        try {
            responseXml = docBuilder.parse(is);
            Node word = responseXml.getElementsByTagName(RESPONSE_FIELD).item(0);
            ret = word.getTextContent();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return Arrays.asList(ret.trim().split(" "));
    }

    public int getProgressPercentage() {
        return 100 * stringProcessed / totalString;
    }

    public String getCurrentActivity() {
        return TASK_NAME + " (" + stringProcessed + "/" + totalString + ")";
    }

    public String toString() {
        return TASK_NAME;
    }
}
