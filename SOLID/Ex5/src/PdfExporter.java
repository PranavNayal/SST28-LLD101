import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    private static final int MAX_LENGTH = 20;

    @Override
    public ExportResult export(ExportRequest req) {
        // LSP fix: Handle any length gracefully instead of throwing
        String title = req.title == null ? "" : req.title;
        String body = req.body == null ? "" : req.body;
        
        // If content is too long, truncate with ellipsis instead of throwing
        if (body.length() > MAX_LENGTH) {
            body = body.substring(0, MAX_LENGTH - 3) + "...";
        }
        if(body.length()>Max_length)
        String fakePdf = "PDF(" + title + "):" + body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}

