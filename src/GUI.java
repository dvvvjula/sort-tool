import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void Start(){
        // main window
        ImageIcon image = new ImageIcon("icon.png");

        JLabel label = new JLabel();
        label.setText("Choose your set:");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(Color.BLACK);
        label.setFont(new Font(Font.SERIF, Font.BOLD, 25));

        JFrame frame = new JFrame("SortTool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 580);

        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(new Color(229, 226, 220));

        // ADD EXISTING SET button
        JButton addExistingSetButton = new JButton("Add Existing Set");
        addExistingSetButton.setBackground(Color.BLACK);
        addExistingSetButton.setForeground(Color.WHITE);
        addExistingSetButton.setFont(new Font("Arial", Font.PLAIN, 14));

        // action after clicking
        addExistingSetButton.addActionListener(e -> {
            frame.dispose();

            JFrame newFrame = new JFrame("Existing Set");
            newFrame.setSize(500, 600);
            newFrame.setVisible(true);
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.getContentPane().setBackground(new Color(229, 226, 220));
            newFrame.setIconImage(image.getImage());

            JLabel text = new JLabel("Type file path:");
            text.setFont(new Font("Arial", Font.BOLD, 16));
            text.setForeground(Color.BLACK);

            JTextField path = new JTextField(20);
            path.setFont(new Font("Arial", Font.PLAIN, 14));

            JButton sortButton = new JButton("Sort");
            sortButton.setBackground(Color.BLACK);
            sortButton.setForeground(Color.WHITE);
            sortButton.setFont(new Font("Arial", Font.PLAIN, 14));

            // action after clicking SORT
            sortButton.addActionListener(e2 -> {
                String filePath = path.getText();
                Sort.SortX(filePath);// SORT HERE
            });

            newFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            ImageIcon dog1 = new ImageIcon("existingSetButton.png");
            JLabel dog1Label = new JLabel(dog1);

            newFrame.add(text);
            newFrame.add(path);
            newFrame.add(sortButton);
            newFrame.add(dog1Label);
        });

        // GENERATE NEW SET button
        JButton generateNewSetButton = new JButton("Generate New Set");
        generateNewSetButton.setBackground(Color.BLACK);
        generateNewSetButton.setForeground(Color.WHITE);
        generateNewSetButton.setFont(new Font("Arial", Font.PLAIN, 14));

        // action after clicking
        generateNewSetButton.addActionListener(e -> {
            frame.dispose();

            JFrame newFrame = new JFrame("Generate New Set");
            newFrame.setSize(500, 600);
            newFrame.setVisible(true);
            newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newFrame.getContentPane().setBackground(new Color(229, 226, 220));
            newFrame.setIconImage(image.getImage());

            JLabel text = new JLabel("Choose set size:");
            text.setFont(new Font("Arial", Font.BOLD, 16));
            text.setForeground(Color.BLACK);

            // combo box with 3 options of set sizes: 10, 1000 and 100000
            String[] sizes = {"10", "1000", "100000"};
            JComboBox<String> comboBox = new JComboBox<>(sizes);
            comboBox.setFont(new Font("Arial", Font.PLAIN, 14));

            JButton sortButton = new JButton("Sort");
            sortButton.setBackground(Color.BLACK);
            sortButton.setForeground(Color.WHITE);
            sortButton.setFont(new Font("Arial", Font.PLAIN, 14));

            // action after clicking SORT
            sortButton.addActionListener(e2 -> {
                String selectedSize = (String) comboBox.getSelectedItem();
                Sort.SortX(selectedSize); // SORT HERE
            });

            newFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            ImageIcon dog2 = new ImageIcon("newSetButton.png");
            JLabel dog2Label = new JLabel(dog2);

            newFrame.add(text);
            newFrame.add(comboBox);
            newFrame.add(sortButton);
            newFrame.add(dog2Label);
        });

        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel imageLabel = new JLabel(image);
        // add all components
        frame.add(label);
        frame.add(addExistingSetButton);
        frame.add(generateNewSetButton);
        frame.add(imageLabel);

        frame.setVisible(true);
    }
}
