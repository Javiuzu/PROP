package presentation;

import domain.CtrlDomain;
import domain.CtrlDomainRecords;
import domini.Tuple.Tuple;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import persistence.CtrlPersistence;
import persistence.CtrlPersistenceRecords;
import presentation.*;

//Author:Luis
public class RecordsGUI {

    //View Objects
    private JFrame frame;
    private JTable grTable;

    private CtrlDomainRecords cdr;
    private CtrlPresentationRecords cpr;


    public RecordsGUI(CtrlDomainRecords recordsControl) {
        try{
            this.cdr = recordsControl;
        } catch (Exception e){
            e.printStackTrace();
        }
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();

        //Frame dimensions
        Dimension dimension = new Dimension(500,400);
        frame.setPreferredSize(dimension);
        frame.setSize(dimension);
        frame.setResizable(true);
        frame.setLocation(dimension.width/2-frame.getSize().width/2, dimension.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);


        ArrayList<model.Tuple> records = this.cdr.getGlobalRecords().getGlobalRecords();

        List<String> recordTypes = Arrays.asList("Finished Games", "Games Lost", "Games Won", "Max Score","Min Guesses","Total Score");


        Object[] columnNames = new Object[3];
        columnNames[0] = "Record type";
        columnNames[1] = "Player";
        columnNames[2] = "Value";

        grTable = new JTable();


        DefaultTableModel dtm = (DefaultTableModel) grTable.getModel();
        dtm.setColumnIdentifiers(columnNames);
        for (int i = 0; i < records.size(); i++) {
            Object[] row = new Object[3];
            row[0] = recordTypes.get(i);
            row[1] = records.get(i).getPlayerName();
            String value = "";
            if (records.get(i).getValue() != null) {
                row[2] = records.get(i).getValue().toString();
            } else {
                row[2] = "N/A";
            }

            dtm.addRow(row);
        }


        JScrollPane pane = new JScrollPane(grTable);
        panel.add(grTable.getTableHeader(), BorderLayout.CENTER);
        panel.add(pane);

    }

}