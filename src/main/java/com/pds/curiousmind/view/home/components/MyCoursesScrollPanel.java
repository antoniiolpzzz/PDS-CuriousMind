
package com.pds.curiousmind.view.home.components;

import javax.swing.*;
import java.awt.*;

public class MyCoursesScrollPanel extends JScrollPane {
    public MyCoursesScrollPanel(JPanel content) {
        super(content);
        setPreferredSize(new Dimension(880, 200)); // ajusta alto si hay varias filas
        setBorder(null);
        setOpaque(false);
        getViewport().setOpaque(false);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
    }
}
