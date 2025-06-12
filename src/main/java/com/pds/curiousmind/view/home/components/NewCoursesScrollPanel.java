package com.pds.curiousmind.view.home.components;

import javax.swing.*;
import java.awt.*;

/**
 * This class provides a styled scrollable panel for displaying new courses.
 */
public class NewCoursesScrollPanel extends JScrollPane {
    public NewCoursesScrollPanel(JPanel content) {
        super(content);
        setPreferredSize(new Dimension(880, 200));
        setBorder(null);
        setOpaque(false);
        getViewport().setOpaque(false);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
    }
}
