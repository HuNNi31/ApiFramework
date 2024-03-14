package ObjectData;

import java.util.HashMap;

public interface RequestPreparation {

    //serialize request body

    void prepareObject(HashMap<String, String> testData);
}
