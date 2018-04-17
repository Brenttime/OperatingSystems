/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulesoftware;

/**
 *
 * @author Brenttime
 */
public class ScheduleSoftware {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserInterface GUI = new UserInterface();
        GUI.setVisible(true);
        GUI.setResizable(false);
        GUI.setTitle("SJF Simulator");
    }
    
}
