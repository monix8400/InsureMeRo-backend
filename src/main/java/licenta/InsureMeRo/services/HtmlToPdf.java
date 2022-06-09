package licenta.InsureMeRo.services;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import licenta.InsureMeRo.dto.InsuranceDTO;
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

    public byte[] generateHtmlToPdf(InsuranceDTO insuranceDTO) throws IOException {
        String htmlContent = readHtmlFile(HTML_INPUT_TEMPLATE);
        String newContent = replaceData(htmlContent, insuranceDTO);
        writeHtmlFile(HTML_NEW_INPUT, newContent);

        File inputHTML = new File(HTML_NEW_INPUT);
        Document doc = createWellFormedHtml(inputHTML);
        xhtmlToPdf(doc, PDF_OUTPUT);

        var pdfFile = readAndDelete(PDF_OUTPUT);
        Files.deleteIfExists(Path.of(HTML_NEW_INPUT));
        return pdfFile;
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

    public String replaceData(String content, InsuranceDTO insuranceDTO) {
        String issueDate = String.valueOf(insuranceDTO.getInsurance().getIssueDate());
        String policyNr = insuranceDTO.getInsurance().getId() + issueDate.substring(8)
                + issueDate.substring(5,7)+ issueDate.substring(0,3);

        content = content.replace("{{nr.polita}}", policyNr);
        String from = String.valueOf(insuranceDTO.getInsurance().getValidFrom());
        content = content.replace("{{from}}", from);
        String to = String.valueOf(insuranceDTO.getInsurance().getValidTo());
        content = content.replace("{{to}}", to);

        String name = insuranceDTO.getPersonalInfo().getName();
        content = content.replace("{{personalInfo.name}}", name);
        String identityCardSeries = insuranceDTO.getPersonalInfo().getIdentityCardSeries();
        content = content.replace("{{personalInfo.identityCardSeries}}", identityCardSeries);
        String identityCardNr = insuranceDTO.getPersonalInfo().getIdentityCardNr();
        content = content.replace("{{personalInfo.identityCardNr}}", identityCardNr);
        String code = insuranceDTO.getPersonalInfo().getCode();
        content = content.replace("{{personalInfo.code}}", code);
        String address = "str. " + insuranceDTO.getAddress().getStreet() + " nr. "
                + insuranceDTO.getAddress().getNumber() + ", "
                + insuranceDTO.getAddress().getCity() + ", "
                + insuranceDTO.getAddress().getCounty() + ", "
                + insuranceDTO.getAddress().getZipCode();
        content = content.replace("{{address}}", address);
        String vName = insuranceDTO.getVehicle().getMake() + " " + insuranceDTO.getVehicle().getModel();
        content = content.replace("{{vehicle.name}}", vName);
        String registrationNr = insuranceDTO.getVehicle().getRegistrationNr();
        content = content.replace("{{vehicle.registrationNr}}", registrationNr);
        content = content.replace("{{vehicle.chassisSeries}}", "WVWZZZ1KZ7M034202");
        String civSeries = insuranceDTO.getVehicle().getCIVSeries();
        content = content.replace("{{vehicle.civSeries}}", civSeries);
        String cylindricalCapacity = String.valueOf(insuranceDTO.getVehicle().getCylindricalCapacity());
        content = content.replace("{{vehicle.cylindricalCapacity}}", cylindricalCapacity);
        String maxNetPower = String.valueOf(insuranceDTO.getVehicle().getMaxNetPower());
        content = content.replace("{{vehicle.maxNetPower}}", maxNetPower);
        String seatsNr = String.valueOf(insuranceDTO.getVehicle().getSeatsNr());
        content = content.replace("{{vehicle.seatsNr}}", seatsNr);
        String maxTotalMass = String.valueOf(insuranceDTO.getVehicle().getMaxTotalMass());
        content = content.replace("{{vehicle.maxTotalMass}}", maxTotalMass);


        content = content.replace("{{issueDate}}", issueDate);
        String bm = insuranceDTO.getPersonalInfo().getBonusMalus();
        content = content.replace("{{bonusMalus}}", bm);
        String price = String.valueOf(insuranceDTO.getInsurance().getPrice());
        content = content.replace("{{price}}", price);

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
