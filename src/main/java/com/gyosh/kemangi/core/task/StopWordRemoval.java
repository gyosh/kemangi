package com.gyosh.kemangi.core.task;

import com.gyosh.kemangi.core.utility.TcpStyleTimer;
import com.gyosh.kemangi.core.utility.Util;
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

public class StopWordRemoval implements Task {
    public static final String TASK_NAME = "Stop words removal";

    private static final String WSDL_URI = "http://fws.cs.ui.ac.id/StopwordRemover/StopwordRemover?wsdl";
    private static final String END_POINT_URI = "http://fws.cs.ui.ac.id:80/StopwordRemover/StopwordRemover";
    private static final String PORT_BINDING = "StopwordRemoverPortBinding";
    private static final String SOAP_ACTION = "";
    private static final String SOAP_ACTION_NAME = "removeStopword";
    private static final String REQUEST_FIELD = "sentence";
    private static final String RESPONSE_FIELD = "return";
    private static final Logger logger = Logger.getLogger(StopWordRemoval.class);

    private Document requestXml;
    private SoapClient client;
    private Transformer transformer;
    private DocumentBuilder docBuilder;
    private TcpStyleTimer tcpStyleTimer;
    private int stringProcessed;
    private int totalString;

    public List<List<String>> exec(List<List<String>> doc) {
        stringProcessed = 0;
        totalString = doc.size();
        tcpStyleTimer = new TcpStyleTimer();

        initSoapClient();
        initRequestBuilder();
        initRequestEditor();

        for (int i = 0; i < doc.size(); i++) {
            doc.set(i, removeStopWord(doc.get(i)));
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

    private List<String> removeStopWord(List<String> tokens) {
        Node word = requestXml.getElementsByTagName(REQUEST_FIELD).item(0);
        word.setTextContent(Util.join(tokens, " "));

        String request = "";
        try {
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(requestXml), new StreamResult(writer));
            request = writer.getBuffer().toString();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        String response = "";
        boolean success = false;
        tcpStyleTimer.init();
        while (!success) {
            try {
                response = client.post(request);
                tcpStyleTimer.blockingWait();
                success = true;
            } catch (Exception e) {
                logger.info("Stop word removal timeout, retrying with " + tcpStyleTimer.getCurrentWaitMilis() + " ms delay");
            }
        }

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
        if (stringProcessed == 0) {
            return TASK_NAME + " (connecting to server)";
        }
        return TASK_NAME + " (" + stringProcessed + "/" + totalString + ")";
    }

    public String toString() {
        return TASK_NAME;
    }
}