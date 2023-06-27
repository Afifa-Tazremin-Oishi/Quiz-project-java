import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONWrite {
    public static void main(String[] args) throws IOException, ParseException {
        String LfileName="./src/main/resources/userLogin.json";
        JSONParser jsonParser= new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(LfileName));
        JSONObject userObject= new JSONObject();
        userObject.put("username","oishi");
        userObject.put("password","1234");
        userObject.put("role","student");

        jsonArray.add(userObject);
        System.out.println(jsonArray);

        FileWriter fileWriter=new FileWriter(LfileName);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }
}
