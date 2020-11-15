package ZapThresholdInput;

import ZapModel.ZapRiskLevels;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ZapThresholdInput {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("OWASP Zed Attack Proxy Threshold Input Program");
        System.out.println("========================================================");

        int high, medium, low, informational;
        System.out.print("Maximum number of High Risk Level Alerts: ");
        high = input.nextInt();
        System.out.print("Maximum number of Medium Risk Level Alerts: ");
        medium = input.nextInt();
        System.out.print("Maximum number of Low Risk Level Alerts: ");
        low = input.nextInt();
        System.out.print("Maximum number of Informational Risk Level Alerts: ");
        informational = input.nextInt();

        ZapRiskLevels zapRiskLevels = new ZapRiskLevels(high, medium, low, informational);
        try {
            FileWriter fw = new FileWriter("/var/lib/jenkins/workspace/SpringPetclinic_DevSecOps_Pipeline/JavaZapPrograms/src/ZapThreshold.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.print(zapRiskLevels);
            pw.close();
        }
        catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
