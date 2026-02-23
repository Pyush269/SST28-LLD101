import java.util.LinkedHashMap;
import java.util.Map;

// Single responsibility: parse a semicolon-delimited key=value string into StudentData.
public class InputParser {
    public StudentData parse(String raw) {
        Map<String, String> kv = new LinkedHashMap<>();
        String[] parts = raw.split(";");
        for (String p : parts) {
            String[] t = p.split("=", 2);
            if (t.length == 2) kv.put(t[0].trim(), t[1].trim());
        }
        return new StudentData(
            kv.getOrDefault("name", ""),
            kv.getOrDefault("email", ""),
            kv.getOrDefault("phone", ""),
            kv.getOrDefault("program", "")
        );
    }
}
