/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctg;

/**
 *
 * @author MaiDinh
 */
public class CTG_FL {
    
    /**
     * @param args the command line arguments
     * @param args[0] = -mode
     * @param args[1] = 0|1
     * @param args[2] = student.c
     * @param args[3] = solution.c
     */
    public static void main(String[] args) {
        CTG ctg = new CTG();

        ctg.studentC = args[2];     // path file student.c
        ctg.solutionC = args[3];    // path file solution.c    
        ctg.runCTG();

        // java -jar CTG.jar -mode = 1
        if (args[1].equals("1")) {
            ctg.runSimulate();
        }
    }
}
