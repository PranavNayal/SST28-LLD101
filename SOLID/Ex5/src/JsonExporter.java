import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        // LSP fix: Handle null request consistently (as empty JSON object)
        if (req == null) {
            return new ExportResult("application/json", "{}".getBytes(StandardCharsets.UTF_8));
        }
        String json = "{\"title\":" + toJsonString(req.title) + ",\"body\":" + toJsonString(req.body) + "}";
        return new ExportResult("application/json", json.getBytes(StandardCharsets.UTF_8));
    }

    private String toJsonString(String s) {
        if (s == null) return "null";
        return "\"" + escape(s) + "\"";
    }

    private String escape(String s) {
        return s.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
    }
}
