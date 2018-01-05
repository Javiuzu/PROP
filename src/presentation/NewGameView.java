package presentation;

import domain.CtrlDomain;
import model.Diff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class NewGameView extends JFrame {
    private CtrlPresentation ctrlPresentation;

    private JRadioButton makerButton;
    private JRadioButton breakerButton;
    private JLabel playAsText;

    private JRadioButton easyButton;
    private JRadioButton mediumButton;
    private JLabel difficultyText;
    private JRadioButton hardButton;

    private JLabel aiText;
    private JRadioButton geneticButton;
    private JRadioButton fiveGuessButton;
    private JButton createButton;
    private JButton backButton;
    private JPanel panel;

    private ButtonGroup roleGroup;
    private ButtonGroup difficultyGroup;
    private ButtonGroup aiGroup;


    private void initializeComponents() {
        playAsText.setText("Choose a side to play as this game");

        roleGroup = new ButtonGroup();
        makerButton.setText("Code Maker");
        breakerButton.setText("Code Breaker");
        roleGroup.add(makerButton);
        roleGroup.add(breakerButton);

        difficultyText.setText("Choose a difficulty for the game");
        setSize(500, 500);
        difficultyGroup = new ButtonGroup();
        easyButton.setText("Easy");
        mediumButton.setText("Medium");
        hardButton.setText("Hard");

        difficultyGroup.add(easyButton);
        difficultyGroup.add(mediumButton);
        difficultyGroup.add(hardButton);

        aiText.setVisible(false);
        aiText.setText("Choose an AI to play against");

        aiGroup = new ButtonGroup();
        geneticButton.setVisible(false);
        fiveGuessButton.setVisible(false);
        aiGroup.add(geneticButton);
        aiGroup.add(fiveGuessButton);

        //default parameters are a medium game as maker with fiveguess when breakerSelected
        fiveGuessButton.setSelected(true);
        mediumButton.setSelected(true);
        breakerButton.setSelected(true);


        createButton.setText("Create Game");
        backButton.setText("Back");

        //ItemListener activates only if state changes not on every button press
        makerButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                int state = event.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    geneticButton.setVisible(true);
                    fiveGuessButton.setVisible(true);
                    aiText.setVisible(true);
                }
            }
        });

        breakerButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                int state = event.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    geneticButton.setVisible(false);
                    fiveGuessButton.setVisible(false);
                    aiText.setVisible(false);
                }
            }
        });

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                createGameButtonPressed();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                backButtonPressed();
            }
        });

        setContentPane(panel);
        //The below function makes the screen appear in the center
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    private void backButtonPressed() {
        setVisible(false);
        ctrlPresentation.loadMenuView();
    }

    public NewGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initializeComponents();
        setVisible(true);
    }

    private void createGameButtonPressed() {
        Diff diff;
        if (easyButton.isSelected()) diff = Diff.EASY;
        else if (mediumButton.isSelected()) diff = Diff.NORMAL;
        else diff = Diff.HARD;
        setVisible(false);
        ctrlPresentation.createNewGame(diff, breakerButton.isSelected(), fiveGuessButton.isSelected());
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
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 5, new Insets(0, 0, 0, 0), -1, -1));
        easyButton = new JRadioButton();
        easyButton.setText("Easy");
        panel.add(easyButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mediumButton = new JRadioButton();
        mediumButton.setText("Hard");
        panel.add(mediumButton, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        hardButton = new JRadioButton();
        hardButton.setText("Medium");
        panel.add(hardButton, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        breakerButton = new JRadioButton();
        breakerButton.setText("CodeBreaker");
        panel.add(breakerButton, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        makerButton = new JRadioButton();
        makerButton.setText("CodeMaker");
        panel.add(makerButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fiveGuessButton = new JRadioButton();
        fiveGuessButton.setText("FiveGuess AI");
        panel.add(fiveGuessButton, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        geneticButton = new JRadioButton();
        geneticButton.setText("Genetic AI");
        panel.add(geneticButton, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playAsText = new JLabel();
        playAsText.setText("Label");
        panel.add(playAsText, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        difficultyText = new JLabel();
        difficultyText.setText("Label");
        panel.add(difficultyText, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        aiText = new JLabel();
        aiText.setText("Label");
        panel.add(aiText, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createButton = new JButton();
        createButton.setText("Button");
        panel.add(createButton, new com.intellij.uiDesigner.core.GridConstraints(6, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backButton = new JButton();
        backButton.setText("Button");
        panel.add(backButton, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}