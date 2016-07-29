import org.w3c.css.sac.InputSource;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by ASUS on 2016/7/27.
 */
public class JDKXmlParser {

    public static String doc2String(Document doc){
        TransformerFactory tf  =  TransformerFactory.newInstance();
        String xmlStr = null;
        try{
        Transformer t = tf.newTransformer();

        t.setOutputProperty("encoding","UTF-8");//解决中文问题，试过用GBK不行

        //ByteArrayOutputStream bos  =  new  ByteArrayOutputStream();
            StringWriter sw = new StringWriter();
        t.transform(new DOMSource(doc), new StreamResult(sw));
            xmlStr = sw.toString();
        }catch(Exception e){

        }

        return xmlStr;
    }

    public static Document string2Doc(String xmlStr){
        Document doc = null;
        try{
            StringReader sr = new StringReader(xmlStr);

            //InputSource is = new InputSource(sr);
            InputStream is = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
            //InputStream is  = new ByteArrayInputStream();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            doc = builder.parse(is);
        } catch(Exception e){

        }
        return doc;
    }
}
