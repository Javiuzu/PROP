package presentation;

import domain.CtrlDomain;
import domain.CtrlDomainRecords;
import domini.Tuple.Tuple;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import persistence.CtrlPersistence;
import persistence.CtrlPersistenceRecords;
import presentation.*;

public class RecordsGUI {

    //View Objects
    private JFrame frame;
    private JButton newButton;
    private JTable table;

    private CtrlDomainRecords records;
    private CtrlDomain ctrlDomain;
    CtrlPresentationRecords cpr;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RecordsGUI g = new RecordsGUI(new CtrlDomainRecords(null));
            g.initialize();
        } catch (IOException ex) {
            Logger.getLogger(RecordsGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecordsGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RecordsGUI(CtrlDomainRecords recordsControl) {
        this.records = recordsControl;
    }

    public RecordsGUI(Tuple player, CtrlDomain domain) {

        try {
            this.ctrlDomain = domain;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        
        //Frame dimensions
        Dimension dimension = new Dimension(360, 120);
        frame.setPreferredSize(dimension);
        
        frame.setResizable(true);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        ArrayList<model.Tuple> records = this.records.getGlobalRecords().getGlobalRecords();

        Object[] columnNames = new Object[2];
        columnNames[0] = "Name";
        columnNames[1] = "Value";

        table = new JTable();

        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setColumnIdentifiers(columnNames);
        for (int i = 0; i < records.size(); i++) {
            Object[] row = new Object[2];
            row[0] = records.get(i).getPlayerName();
            String value = "";
            if (records.get(i).getValue() != null) {
                row[1] = records.get(i).getValue().toString();
            } else {
                row[1] = "N/A";
            }

            dtm.addRow(row);
        }
        JScrollPane pane = new JScrollPane(table);
        panel.add(table.getTableHeader(), BorderLayout.NORTH);

        panel.add(pane);

        
    }

}
