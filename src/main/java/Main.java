import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    private static String readString(String jsonFile) {
        StringBuffer buffer = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader("jsonData.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return buffer.toString();
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> employees = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray array = (JsonArray) parser.parse(json);
        for (JsonElement object : array) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            employees.add(gson.fromJson(object, Employee.class));
        }
        return employees;
    }
}
