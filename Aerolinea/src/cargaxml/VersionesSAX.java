package cargaxml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class VersionesSAX {

    VersionesHandler handler;

    public void CargaRegistrosDeViajeros() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        File file = new File("Registro.xml");
        handler = new VersionesHandler();
        saxParser.parse(file, handler);
    }

    public ArrayList<RegistroDeViajero> getRegistrosDeViajeros() {
        return handler.getRegistros();
    }
}
