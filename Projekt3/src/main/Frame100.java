/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;

/**
 *
 * @author student
 */
public class Frame100 extends javax.swing.JFrame {

    public static ArrayList<JSlider> normalSliders = new ArrayList<JSlider>();
    public static ArrayList<JSlider> reverseSliders = new ArrayList<JSlider>();

    /**
     * Creates new form Main
     */
    public Frame100() {
    }

    private static void jSliderStateChanged(javax.swing.event.ChangeEvent evt) {
        JSlider that = (JSlider) evt.getSource();
        for (JSlider s : normalSliders) {
            if (!s.equals(that)) {
                ChangeListener[] temp = s.getChangeListeners();
                for (ChangeListener cl : temp) {
                    s.removeChangeListener(cl);
                }
                s.setValue(that.getValue());
                for (ChangeListener cl : temp) {
                    s.addChangeListener(cl);
                }
            }
        }
        for (JSlider s : reverseSliders) {
            ChangeListener[] temp = s.getChangeListeners();
            for (ChangeListener cl : temp) {
                s.removeChangeListener(cl);
            }
            s.setValue(100 - that.getValue());
            for (ChangeListener cl : temp) {
                s.addChangeListener(cl);
            }
        }
    }

    private static void jSliderRStateChanged(javax.swing.event.ChangeEvent evt) {
        JSlider that = (JSlider) evt.getSource();
        for (JSlider s : normalSliders) {
            ChangeListener[] temp = s.getChangeListeners();
            for (ChangeListener cl : temp) {
                s.removeChangeListener(cl);
            }
            s.setValue(100 - that.getValue());
            for (ChangeListener cl : temp) {
                s.addChangeListener(cl);
            }
        }
        for (JSlider s : reverseSliders) {
            if (!s.equals(that)) {
                ChangeListener[] temp = s.getChangeListeners();
                for (ChangeListener cl : temp) {
                    s.removeChangeListener(cl);
                }
                s.setValue(that.getValue());
                for (ChangeListener cl : temp) {
                    s.addChangeListener(cl);
                }
            }
        }
    }

    private static JSlider getNormalSlider() {
        JSlider slider = new javax.swing.JSlider();

        slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderStateChanged(evt);
            }
        });
        normalSliders.add(slider);
        return slider;
    }

    private static JSlider getReverseSlider() {
        JSlider slider = new javax.swing.JSlider();

        slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderRStateChanged(evt);
            }
        });
        reverseSliders.add(slider);
        return slider;
    }

    public static void main(String[] args) {

        final JFrame frame = new JFrame("Test");
        frame.setLayout(new GridLayout(0, 1));

        frame.add(new JButton(new AbstractAction("Normal") {
            @Override
            public void actionPerformed(ActionEvent e) {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        frame.add(getNormalSlider());
                        frame.validate();
                        frame.repaint();
                    }
                });
            }
        }));

        frame.add(new JButton(new AbstractAction("Reverse") {
            @Override
            public void actionPerformed(ActionEvent e) {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JSlider slider = new javax.swing.JSlider();

                        slider.addChangeListener(new javax.swing.event.ChangeListener() {
                            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                jSliderRStateChanged(evt);
                            }
                        });
                        reverseSliders.add(slider);
                        frame.add(getReverseSlider());
                        frame.validate();
                        frame.repaint();
                    }
                });
            }
        }));

        for (int i = 0; i < 40; i++) {
            frame.add(getNormalSlider());
        }
        for (int i = 0; i < 10; i++) {
            frame.add(getReverseSlider());
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 2000);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }
}
