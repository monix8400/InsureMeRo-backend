package licenta.InsureMeRo.Services;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

@Slf4j
public class HtmlToPdf {
    private static final String HTML_INPUT_TEMPLATE = "src/main/resources/templates/insuranceTemplate.html";
    private static final String HTML_NEW_INPUT = "src/main/resources/insurance.html";
    private static final String PDF_OUTPUT = "src/main/resources/insurance.pdf";

    public byte[] generateHtmlToPdf() throws IOException {
        String htmlContent = readHtmlFile(HTML_INPUT_TEMPLATE);
        String newContent = replaceData(htmlContent);
        writeHtmlFile(HTML_NEW_INPUT, newContent);

        File inputHTML = new File(HTML_NEW_INPUT);
        Document doc = createWellFormedHtml(inputHTML);
        xhtmlToPdf(doc, PDF_OUTPUT);

        var ceva = readAndDelete(PDF_OUTPUT);
        Files.deleteIfExists(Path.of(HTML_NEW_INPUT));
        return ceva;
    }

    private Document createWellFormedHtml(File inputHTML) throws IOException {
        Document document = Jsoup.parse(inputHTML, "UTF-8");
        document.outputSettings()
                .syntax(Document.OutputSettings.Syntax.xml);
        return document;
    }

    private void xhtmlToPdf(Document doc, String outputPdf) throws IOException {
        try (OutputStream os = new FileOutputStream(outputPdf)) {
            String baseUri = FileSystems.getDefault()
                    .getPath("src/main/resources/")
                    .toUri()
                    .toString();
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withUri(outputPdf);
            builder.toStream(os);
            builder.withW3cDocument(new W3CDom().fromJsoup(doc), baseUri);
            builder.run();
        }
    }

    public byte[] readAndDelete(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        byte[] targetArray = new byte[fis.available()];
        fis.read(targetArray);
        fis.close();
        Files.deleteIfExists(Path.of(fileName));
        return targetArray;
    }

    public String readHtmlFile(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
            log.info(String.valueOf(e));
        }
        String content = contentBuilder.toString();
        log.info(content);
        return content;
    }

    public String replaceData(String content) {
//        insuranceDTO.getPersonalInfo().getName()

        content = content.replace("{{personalInfo.name}}", "BUNAAA");
        content = content.replace("{{personalInfo.identityCardSeries}}", "BUNAAA");
        content = content.replace("{{personalInfo.identityCardNr}}", "BUNAAA");
        content = content.replace("{{personalInfo.code}}", "6000408260051");
        content = content.replace("{{address}}", "BUNAAA");
        content = content.replace("{{vehicle.name}}", "BUNAAA");
        content = content.replace("{{vehicle.registrationNr}}", "SB77FNE");
        content = content.replace("{{vehicle.chassisSeries}}", "WVWZZZ1KZ7M034202");
        content = content.replace("{{vehicle.civSeries}}", "BUNAAA");
        content = content.replace("{{vehicle.cylindricalCapacity}}", "BUNAAA");
        content = content.replace("{{vehicle.maxNetPower}}", "BUNAAA");
        content = content.replace("{{vehicle.seatsNr}}", "BUNAAA");
        content = content.replace("{{vehicle.maxTotalMass}}", "BUNAAA");
        log.info(content);
        return content;
    }

    public void writeHtmlFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);

        } catch (IOException e) {
            log.info(String.valueOf(e));
        }
    }
}
