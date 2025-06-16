package com.pds.curiousmind.view.home.course.components;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.view.common.RoundedLabel;
import com.pds.curiousmind.view.playview.question.components.QuestionStructure;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContentBlockRow {

    private static final Controller controller = Controller.INSTANCE;

    public static JPanel createContentColumnSection(JFrame parentFrame, RegisteredCourse course, java.util.List<RegisteredContentBlock> contentBlocks) {


        JPanel column = new JPanel();
        column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
        column.setOpaque(false);

        for ( RegisteredContentBlock block : contentBlocks ) {
            RoundedLabel label = new RoundedLabel(block.getName());

            if (block.isCompleted()) {
                label.setLabelBackground(new Color(214, 245, 214));
                label.setLabelBorderColor(new Color(180, 230, 180));
            } else {
                label.setLabelBackground(new Color(245, 245, 245));
                label.setLabelBorderColor(new Color(200, 200, 200));
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                Color original = label.getLabelBackground();

                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        label.setLabelBackground(new Color(230, 250, 255));
                    }
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        label.setLabelBackground(original);
                    }

                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        //TODO: Question question = controller.initializeGameManager(course, block);
                        Question question = block.getQuestions().get(1);
                        //pritn del tipo de pregunta
                        new QuestionStructure(course, question, block.getName());
                        parentFrame.dispose();
                    }
                });
            }

            column.add(label);
            column.add(Box.createVerticalStrut(12));
        }

        return column;
    }
}
