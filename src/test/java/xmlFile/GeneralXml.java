package xmlFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class GeneralXml {

    //clasa care reprezinta maparea fisierului xml cu constructia din xmlNode
    public static <T> T createConfig(Class<T> klass){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(klass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return klass.cast(unmarshaller.unmarshal(new File("src/test/resources/atfConfig.xml")));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
