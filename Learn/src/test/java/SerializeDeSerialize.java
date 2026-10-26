import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

public class SerializeDeSerialize {

        @Test
        public void serialization() throws JsonProcessingException {
            POJO_StudentJson obj = new POJO_StudentJson();
            obj.setName("Raghul");
            obj.setAge(26);
            obj.setGrade("A");

            String[] courses = {"Maths", "Science"};
            obj.setSubjects(courses);

            ObjectMapper objectMapper = new ObjectMapper();
            String JSONData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            System.out.println(JSONData);
        }

        @Test
        public void deserialization() throws JsonProcessingException {
            String JSONData = "{\n  \"name\" : \"Raghul\",\n  \"age\" : 26,\n  \"grade\" : \"A\",\n  \"subjects\" : [ \"Maths\", \"Science\" ]\n}";
            ObjectMapper objectMapper = new ObjectMapper();
            POJO_StudentJson  Newobj= objectMapper.readValue(JSONData,POJO_StudentJson.class);
            System.out.println(Newobj.getName());
        }
}

