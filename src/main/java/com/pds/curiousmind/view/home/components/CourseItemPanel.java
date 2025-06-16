package com.pds.curiousmind.view.home.components;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static com.pds.curiousmind.view.common.GlobalConstants.*;
import static com.pds.curiousmind.view.common.LoadIcon.loadIcon;

import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.view.common.StyledButton;
import static com.pds.curiousmind.view.common.HoverEffect.addHoverEffect;

public class CourseItemPanel extends JPanel {

    private static final Controller controller = Controller.INSTANCE;


    public CourseItemPanel(Course course, Runnable onClick) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        setOpaque(false);

        // Create the main course button with icon and style
        StyledButton courseBtn = new StyledButton(course.getName(), new Color(230, 230, 230), Color.BLACK);
        courseBtn.setIcon(loadIcon(course.getImageURL(), 20, 20));
        courseBtn.setPreferredSize(new Dimension(150, 35));
        courseBtn.setMaximumSize(new Dimension(150, 35));
        courseBtn.addActionListener(e -> onClick.run());
        add(courseBtn);

        //SHARE BUTTON

        StyledButton shareBtn = new StyledButton("", Color.WHITE, Color.BLACK);
        shareBtn.setIcon(loadIcon(ICON_SHARE, 18, 18));
        shareBtn.setPreferredSize(new Dimension(35, 35));
        shareBtn.setFocusPainted(false);
        addHoverEffect(shareBtn);
        add(shareBtn);

        setBackground(Color.WHITE);

        //TODO: Check this functionality works correctly

        shareBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save file as JSON");

            // Proponer un nombre por defecto con extensión .json
            fileChooser.setSelectedFile(new java.io.File(course.getName() + ".json"));

            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File destinationFile = fileChooser.getSelectedFile();

                // Asegurarse de que tenga extensión .json
                if (!destinationFile.getName().toLowerCase().endsWith(".json")) {
                    destinationFile = new java.io.File(destinationFile.getAbsolutePath() + ".json");
                }

                // Obtener el fichero JSON generado por el controlador
                File jsonFile = controller.getJsonFromCourse(course);

                try {
                    // Copiar el contenido del fichero original al destino elegido por el usuario
                    Files.copy(jsonFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error al guardar el archivo: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE,
                            loadIcon(ICON_ANGRY, 60, 60)
                    );
                }
            }
        });

    }

}