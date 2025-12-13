import java.io.File;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XMLAccuracyLoader {
    public static Map<Integer, Integer> loadXML(File xmlFile){
        Map<Integer, Integer> mapping = new HashMap<>();

        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); //creating document builders to parse xml
            DocumentBuilder db = dbf.newDocumentBuilder();  

            Document doc = db.parse(xmlFile);

            doc.getDocumentElement().normalize();  //normalize xml

            for (int i = 0; i < doc.getElementsByTagName("LOCATION").getLength(); i++) {  //loop through all location elemends
                Node node = doc.getElementsByTagName("LOCATION").item(i);

                
                if (node.getNodeType() == Node.ELEMENT_NODE) { //check for element nodes
                    Element e = (Element) node;


                    int ogLine = Integer.parseInt(e.getAttribute("ORIG")) - 1;
                    int newLine = Integer.parseInt(e.getAttribute("NEW")) - 1;  //finds mappings stored on xml and stores in a map

                    mapping.put(ogLine, newLine);
                }
            }

        } catch(Exception e){
            System.out.println("Could not parse accuracy file");
        }

        return mapping;
    }
}
