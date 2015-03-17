/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author MaiDinh
 */
class CTG {

    public String studentC;
    public String solutionC;

    public CTG() {
    }

    public void runCTG() {
        String src = Control.s_GoldInstance.standardizeSource(studentC);
        WriteFile("src.cpp", src);

        String dst = Control.s_EvoInstance.standardizeSource(solutionC);
        WriteFile("dst.cpp", dst);

        Control.s_GoldInstance.InitCTG();
        Control.s_EvoInstance.InitCTG();
        Control.RunCTG();

        // print test cases
        String testcases = "";
        for (ArrayList<String> testcase : Control.T) {
            for (String var : testcase) {
                testcases += var + ";";
            }
            testcases += "\n";
        }
        WriteFile("testcases.txt", testcases);
        
        // mrbubu
        // print test cases for DSFL
        testcases = "Begin test cases.\n";
        for (ArrayList<String> testcase : Control.T) {
            for (String var : testcase) {
                testcases += var + ";";
            }
            testcases += "\n";
        }
        testcases += "End test cases.\n";
        WriteFile("concolicSE.txt", testcases);
    }

    public void runSimulate() {
        if (Control.T != null) {
            int rowNum = Control.T.size();
            int colNum = Control.T.size() + 3;
            String[][] data = new String[rowNum][colNum];
            for (int i = 0; i < rowNum; i++) {
                for (int j = 0; j < colNum; j++) {
                    data[i][j] = "";
                }
            }
            Control.testResult = new ArrayList<>();
            Control.totalPassed = 0;
            Control.totalFailed = 0;
            for (int i = 0; i < Control.T.size(); i++) {
                ArrayList<String> testcase = Control.T.get(i);
                for (int j = 0; j < testcase.size(); j++) {
                    data[i][j] = testcase.get(j);
                }
                data[i][testcase.size()] = Control.s_GoldInstance.getOutput(i) + "";
                data[i][testcase.size() + 1] = Control.s_EvoInstance.getOutput(i) + "";
                if (data[i][testcase.size()].equals(data[i][testcase.size() + 1])) {
                    data[i][testcase.size() + 2] = "Passed";
                    Control.testResult.add(true);
                    Control.totalPassed++;
                } else {
                    data[i][testcase.size() + 2] = "Failed";
                    Control.testResult.add(false);
                    Control.totalFailed++;
                }
            }

            int paramNum = Control.s_GoldInstance.getParaList().size();
            Object[] colName = new String[paramNum + 3];
            for (int i = 0; i < paramNum; i++) {
                colName[i] = Control.s_GoldInstance.getParaList().get(i);
            }

            colName[paramNum++] = "Original output";
            colName[paramNum++] = "Evolution output";
            colName[paramNum++] = "Test result";

            // print result
            String result = "";
            for (String[] testcase : data) {
                for (String s : testcase) {
                    if (s.length() > 0) {
                        result += s + ",";
                    }
                }
                result = result.substring(0, result.length() - 1) + "\n";
            }
            result += "Total passed: " + Control.totalPassed + "\n";
            result += "Total failed: " + Control.totalFailed + "\n";
            WriteFile("simulate.txt", result);
        }
    }

    public void WriteFile(String fileName, String content) {
        try {
            File file = new File(fileName);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
