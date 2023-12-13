
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

    public List<HashMap<String, String>> getJsonDataToMap() {

        String jsonContent = FileUtils
                .readFileToString(new File(System.getProperty("user.dir") + "\\src\\jsonFiles\\OrderItems.json"));

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {

                });
        return data; 
    }
}
