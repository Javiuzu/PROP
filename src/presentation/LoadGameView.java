package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import exceptions.AlreadyGameLoaded;
import exceptions.GameUnexistentForUser;
import exceptions.NoUserLoggedIn;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoadGameView extends JFrame {
    private CtrlPresentation ctrlPresentation;

    private JList gameList;
    private JButton loadButton;
    private JButton eraseButton;
    private JButton backButton;
    private JTextField selectedGame;
    private JPanel panel;
    private JLabel gameLabel;

    private void initializeComponents() {

        loadButton.setText("Load Game");
        eraseButton.setText("Erase Game");
        backButton.setText("Back");

        gameLabel.setText("Write a name of a Game or select it");

        setSize(400, 400);
        //The below function makes the screen appear in the center
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setTitle("Load a Game");
        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setTitle("Saved Games");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gameList.setListData(ctrlPresentation.getSavedGamesList());
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                loadButtonPressed();
            }
        });

        eraseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                eraseButtonPressed();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                backButtonPressed();
            }
        });
        gameList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent event) {
                clickOnListElement(event);
            }
        });
        setContentPane(panel);
    }

    LoadGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initializeComponents();
    }

    private void backButtonPressed() {
        setVisible(false);
        ctrlPresentation.loadMenuView();
    }

    private void clickOnListElement(ListSelectionEvent event) {
        String s = (String) gameList.getSelectedValue();
        selectedGame.setText(s);
    }

    private void loadButtonPressed() {
        boolean loadedSuccesfully = true;
        try {
            ctrlPresentation.loadGame(selectedGame.getText());
        } catch (ClassNotFoundException e) {
        } catch (NoUserLoggedIn noUserLoggedIn) {
        } catch (AlreadyGameLoaded alreadyGameLoaded) {
        } catch (GameUnexistentForUser gameUnexistentForUser) {
            loadedSuccesfully = false;
            JOptionPane.showMessageDialog(null, "The saved game name doesn't exist.");
        } catch (IOException e) {

        }
        if (loadedSuccesfully) {
            setVisible(false);
            ctrlPresentation.loadSavedGame();
        }
    }

    private void eraseButtonPressed() {
        ctrlPresentation.eraseGame(selectedGame.getText());
        //We reload the saved games list
        Object[] p = ctrlPresentation.getSavedGamesList();
        gameList.setListData(p);

    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        loadButton = new JButton();
        loadButton.setText("Button");
        panel.add(loadButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eraseButton = new JButton();
        eraseButton.setText("Button");
        panel.add(eraseButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backButton = new JButton();
        backButton.setText("Button");
        panel.add(backButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gameList = new JList();
        panel.add(gameList, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        selectedGame = new JTextField();
        panel.add(selectedGame, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        gameLabel = new JLabel();
        gameLabel.setText("Label");
        panel.add(gameLabel, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
