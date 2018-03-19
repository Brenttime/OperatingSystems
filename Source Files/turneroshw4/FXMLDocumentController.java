/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turneroshw4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

/**
 *
 * @author Brenttime
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label lblNotepad;
    @FXML
    private Label lblCMD;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        lblNotepad.setText("Notepad has closed!");
        lblCMD.setText("CMD has closed!");
        ProcessBuilder notepad = new ProcessBuilder("Notepad.exe");
        ProcessBuilder cmd = new ProcessBuilder("cmd.exe", "/C", "start");
        
        //Declare to be able to use in finally block
        Process notepadProcess = null;
        Process cmdProcess = null; 
        
        //In case execution fails throw an alert box
        try
        {
            notepadProcess = notepad.start();
            cmdProcess = cmd.start();
           /*
           synchronized(notepadProcess)
           {
               notepadProcess.wait();
           }
           synchronized(cmdProcess)
           {
                cmdProcess.wait();
           }
            */
           notepadProcess.waitFor();
           cmdProcess.waitFor();
           
       
        }
        catch(Exception e)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
        finally
        {
            //Clean up
            if(notepadProcess.isAlive())
            {
                notepadProcess.destroy();
            }
            if(cmdProcess.isAlive()){
                cmdProcess.destroy();
            }
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
