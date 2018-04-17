/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulesoftware;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Brenttime
 */
public class UserInterface extends javax.swing.JFrame {

    private List<String> listOfProcesses;
    private int[] burstTime;
    private DefaultListModel readyQueue;
    private DefaultListModel doneQueue;
    
    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            
            //Clean UP before run
            doneQueue.removeAllElements();
            jList3.setModel(doneQueue);
            
            jProgressBar1.setValue(0);

            String processID = "";
            for(int index = 0; index < listOfProcesses.size(); index++)
            {
                jProgressBar2.setValue(0);
                for(int i =0; i <= 100; i++)
                {
                    if(i == 0)
                    {
                        jList2.setSelectedIndex(0);
                        processID = jList2.getSelectedValue();
                        jLabel9.setText(processID);

                        readyQueue.remove(0);
                        jList2.setModel(readyQueue);
                    }

                    if(i < 100)
                    {
                        jProgressBar2.setValue(i);
                        try {
                            //Get the waiting time into seconds
                            Thread.sleep(burstTime[index] * 10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    else if(i == 100){
                        jProgressBar2.setValue(100);
                        jProgressBar1.setValue(jProgressBar1.getValue() + (100 / listOfProcesses.size()));
                        doneQueue.addElement(processID);
                        jList3.setModel(doneQueue);
                    }
                }
            }
            
            return null;
        }
        
 
        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            jProgressBar1.setValue(100);
            jProgressBar2.setValue(0);
            jLabel9.setText("");
        }
    }

    /**
     * Creates new form UserInteface
     */
    public UserInterface() {
        initComponents();
        pBar = jProgressBar2;
    }
    
    private JProgressBar pBar;
    
    public void viewBar(int[] burstTime, DefaultListModel doneQueue, DefaultListModel readyQueue) throws InterruptedException {

        for(int i =0; i < jProgressBar1.getMaximum(); i++)
        {
            jProgressBar1.setValue(i);
        }
        Thread.sleep(100);
        
        jProgressBar1.setStringPainted(true);
        jProgressBar1.setValue(0);
        jProgressBar2.setStringPainted(true);
        jProgressBar2.setValue(0);
        
        //jProgressBar2.setIndeterminate(true);
        //jProgressBar1.setIndeterminate(true);
        
        //Now to start the jobs            
        int timerDelay = 0;
        for(int i = 0; i < listOfProcesses.size(); i++)
        {
            timerDelay += burstTime[i];
        }
        
        int index = 0;
            

        String processID = "";

        if(index == 0)
        {
            doneQueue.removeAllElements();
            jList3.setModel(doneQueue);
        }

        //Is the progress bar progressing?
        if (index  < listOfProcesses.size()) {        
            for(int i =0; i <= 100; i++)
            {
                if(i == 0)
                {
                    jProgressBar2.setValue(0);

                    jList2.setSelectedIndex(0);
                    processID = jList2.getSelectedValue();
                    jLabel9.setText(processID);

                    readyQueue.remove(0);
                    jList2.setModel(readyQueue);
                }

                if(i < 100)
                {
                    jProgressBar2.setValue(i);

                }
                else if(i == 100){
                    jProgressBar2.setValue(100);
                    jProgressBar1.setValue( 100 - (100 / (index + 1)));
                    index++;
                    doneQueue.addElement(processID);
                    jList3.setModel(doneQueue);
                }
            }
        }

        //Main progress bar done?
        else {
            jProgressBar1.setValue(100);
            jLabel9.setText("");
       }                  
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        btn1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        jTextField2.setText("jTextField2");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("How many processes?");

        jScrollPane1.setViewportView(jList1);

        btn1.setText("Generate");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Process IDs");

        jLabel3.setText("Current Processes Burst Time");

        jButton1.setText("Start Simulation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Current Job Status");

        jScrollPane2.setViewportView(jList2);

        jLabel5.setText("Ready Queue");

        jScrollPane3.setViewportView(jList3);

        jLabel6.setText("Jobs Completed");

        jLabel7.setText("Process:");

        jButton2.setText("Apply");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel9.setText("None");

        jLabel10.setText("Total Progress:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton2)))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(89, 89, 89)
                                                .addComponent(jLabel8)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel7)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel9)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))))
                        .addContainerGap(49, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btn1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        //When the user decides how many processes to build
        
        listOfProcesses = new ArrayList<String>();
        DefaultListModel model = new DefaultListModel();

        
        //Ensure the text the user entered was an integer
        try{
            int numberOfProcesses = Integer.parseInt(jTextField1.getText());
            burstTime = new int[numberOfProcesses];
            
            for(int i = 0; i < numberOfProcesses; i++)
            {
                listOfProcesses.add("P" + (i + 1));
                model.addElement("P" + (i + 1));
            }
            
            jList1.setModel(model);
            jList1.updateUI();
            
            
            jList1.addListSelectionListener(new ListSelectionListener()
            {
              public void valueChanged(ListSelectionEvent ev)
              {
                int currentIndx = listOfProcesses.indexOf(jList1.getSelectedValue());
                if(burstTime[currentIndx] > 0)
                {
                    jTextField3.setText(Integer.toString(burstTime[currentIndx]));
                }
                else
                {
                    jTextField3.setText("");
                }
              } 
            });

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Please Enter an Integer", "Error",  JOptionPane.WARNING_MESSAGE); 
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jTextField3.getText() != "")
        {
            try
            {
                int burstValue = Integer.parseInt(jTextField3.getText());
                burstTime[listOfProcesses.indexOf(jList1.getSelectedValue())] = burstValue;        
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Please Enter an Integer", "Error",  JOptionPane.WARNING_MESSAGE);         
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        boolean valuesEntered = true;
        if(listOfProcesses.size() > 0)
            for(int i=0; i < listOfProcesses.size(); i++)
            {
                if(burstTime[i] <= 0)
                {
                    valuesEntered = false;
                }
            }
        else
            valuesEntered = false;
        
        if(valuesEntered)
        {
            //Empty Lists
            jList2.removeAll();
            jList3.removeAll();
            
            //sorting burst time in ascending order using selection sort
            int temp;
            String tempID;
            readyQueue = new DefaultListModel();
            doneQueue = new DefaultListModel();
            
            for(int i=0; i < listOfProcesses.size(); i++)
            {
                int pos = i;
                for(int j = i+1; j< listOfProcesses.size(); j++)
                {
                    if(burstTime[j]<burstTime[pos])
                        pos=j;
                }

                //reorder bursts
                temp=burstTime[i];
                burstTime[i]=burstTime[pos];
                burstTime[pos]=temp;

                //Reorder process ID
                tempID=listOfProcesses.get(i);
                listOfProcesses.set(i, listOfProcesses.get(pos));
                listOfProcesses.set(pos, tempID);
            }
            for(int i = 0; i < listOfProcesses.size(); i++)
            {
                readyQueue.addElement(listOfProcesses.get(i));
            }
            jList2.setModel(readyQueue);
            //viewBar(burstTime, doneQueue, readyQueue);
            Task task = new Task();
            task.execute();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Please Enter all Burst Times", "Error",  JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JProgressBar jProgressBar1;
    private final javax.swing.JProgressBar jProgressBar2 = new javax.swing.JProgressBar();
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
