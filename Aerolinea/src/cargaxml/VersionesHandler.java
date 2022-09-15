package cargaxml;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class VersionesHandler extends DefaultHandler {

    private ArrayList<RegistroDeViajero> registrosDeViajeros = new ArrayList();
    private RegistroDeViajero registroDeViajero;
    private StringBuilder buffer = new StringBuilder();

    public ArrayList<RegistroDeViajero> getRegistros() {
        return registrosDeViajeros;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "ID":
                registroDeViajero.setID(buffer.toString());
                break;
            case "MONTO":
                registroDeViajero.setMonto(Float.parseFloat(buffer.toString()));
                break;
            case "LITRO":
                registroDeViajero.setLitro(Float.parseFloat(buffer.toString()));

        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {

            case "DNI":
                registroDeViajero = new RegistroDeViajero();
                registrosDeViajeros.add(registroDeViajero);
                registroDeViajero.setDNI(Long.parseLong(attributes.getValue("numero")));
                break;
            case "ID":
            case "MONTO":
            case "LITRO":
                buffer.delete(0, buffer.length());
                break;
        }
    }

}
