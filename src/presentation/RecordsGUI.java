package presentation;

import static com.sun.glass.ui.Cursor.setVisible;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import domain.CtrlDomainRecords;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
//Author:Luis
public class RecordsGUI {

    //View Objects
    private JFrame frame;
    private JTable grTable;

    private CtrlDomainRecords cdr;
    private CtrlPresentationRecords cpr;
    private JButton backButton;
    private CtrlPresentation ctrlPresentation;


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
                        ctrlPresentation = new CtrlPresentation();

        frame = new JFrame();

        //Frame dimensions
        Dimension dimension = new Dimension(500,400);
        frame.setPreferredSize(dimension);
        frame.setSize(dimension);
        frame.setResizable(true);
        frame.setLocation(dimension.width/2-frame.getSize().width/2, dimension.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //Back button handling
        backButton = new JButton();
        backButton.setText("Back");
        backButton.setVisible(true);
        
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
        panel.add(backButton);
        

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //backButtonPressed();
                setVisible(false);
                ctrlPresentation.loadMenuView();
            }
        });
    }
    
    private void backButtonPressed() {
        setVisible(false);
        ctrlPresentation.loadMenuView();
    }
}