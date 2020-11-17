package ZapReview;

import ZapModel.ZapRiskLevels;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ZapSpringPetclinicReview {
    public static void main(String[] args) {
        int[] riskCodeTextContent;
        boolean isPass = false;
        String thresholdInput = "";
        int high, medium, low, informational;
        Scanner lineTokenizer;
        ZapRiskLevels zapRiskLevels = null;
        int noOfInformational = 0;
        int noOfLow = 0;
        int noOfMedium = 0;
        int noOfHigh = 0;
        try {
            FileReader fr = new FileReader("/var/lib/jenkins/workspace/SpringPetclinic_InsecureDocker_Pipeline/JavaZapPrograms/src/ZapThreshold.txt");
            Scanner scanner = new Scanner(fr);
            while (scanner.hasNext()) {
                thresholdInput = scanner.next();
                lineTokenizer = new Scanner(thresholdInput);
                lineTokenizer.useDelimiter(";");
                high = lineTokenizer.nextInt();
                medium = lineTokenizer.nextInt();
                low = lineTokenizer.nextInt();
                informational = lineTokenizer.nextInt();
                zapRiskLevels = new ZapRiskLevels(high, medium, low, informational);
            }
        }
        catch (FileNotFoundException e) {
            isPass = false;
        }

        try {
            File file = new File("/var/lib/jenkins/workspace/SpringPetclinic_InsecureDocker_ZapScan/reports/SPRINGPETCLINIC_ZAP_VULNERABILITY_REPORT.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("alertitem");
            riskCodeTextContent = new int[nodeList.getLength()];

            for (int count=0; count<nodeList.getLength(); count++) {
                Node node = nodeList.item(count);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element)node;
                    riskCodeTextContent[count] = Integer.parseInt(element.getElementsByTagName("riskcode").item(0).getTextContent());
                }
            }

            for (int index=0; index<riskCodeTextContent.length; index++) {
                if (riskCodeTextContent[index] == 0)
                    noOfInformational++;
                else if (riskCodeTextContent[index] == 1)
                    noOfLow++;
                else if (riskCodeTextContent[index] == 2)
                    noOfMedium++;
                else if (riskCodeTextContent[index] == 3)
                    noOfHigh++;
            }

            isPass = getResult(zapRiskLevels, noOfInformational, noOfLow, noOfMedium, noOfHigh);
        }
        catch (Exception exception) {
            isPass = false;
        }

        if (isPass)
            System.out.println("Pass");
        else if (!isPass)
            System.out.println("Fail");
    }

    private static boolean getResult(ZapRiskLevels zapRiskLevels, int noOfInformational, int noOfLow, int noOfMedium, int noOfHigh) {
        if (noOfHigh > zapRiskLevels.getHigh())
            return false;
        else if (noOfMedium > zapRiskLevels.getMedium())
            return false;
        else if (noOfLow > zapRiskLevels.getLow())
            return false;
        else if (noOfInformational > zapRiskLevels.getInformational())
            return false;
        else
            return true;
    }
}
