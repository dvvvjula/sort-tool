import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI {
    public static void Start(){
        // Main window
        ImageIcon image = new ImageIcon("icon.png");
        JLabel label = new JLabel("Choose your set:");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(Color.BLACK);
        label.setFont(new Font(Font.SERIF, Font.BOLD, 25));

        JFrame mainFrame = new JFrame("SortTool");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 580);
        mainFrame.setIconImage(image.getImage());
        mainFrame.getContentPane().setBackground(new Color(229, 226, 220));
        mainFrame.setLocationRelativeTo(null); // Center the main window on screen

        // Button: Add Existing Set (load from file)
        JButton addExistingSetButton = new JButton("Add Existing Set");
        addExistingSetButton.setBackground(Color.BLACK);
        addExistingSetButton.setForeground(Color.WHITE);
        addExistingSetButton.setFont(new Font("Arial", Font.PLAIN, 14));

        addExistingSetButton.addActionListener(e -> {
            mainFrame.setEnabled(false);
            JFrame existingSetFrame = new JFrame("Existing Set");
            existingSetFrame.setSize(500, 600);
            existingSetFrame.setLocationRelativeTo(null); // Center the child window on screen
            existingSetFrame.setVisible(true);
            // Use DISPOSE_ON_CLOSE so that closing this window does not exit the program
            existingSetFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            existingSetFrame.getContentPane().setBackground(new Color(229, 226, 220));
            existingSetFrame.setIconImage(image.getImage());

            // Re-enable the main window when the child window is closed
            existingSetFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                    mainFrame.setEnabled(true);
                }
                @Override
                public void windowClosed(WindowEvent we) {
                    mainFrame.setEnabled(true);
                }
            });

            // Label and text field for file path input
            JLabel pathLabel = new JLabel("Type file path:");
            pathLabel.setFont(new Font("Arial", Font.BOLD, 16));
            pathLabel.setForeground(Color.BLACK);

            JTextField pathField = new JTextField(20);
            pathField.setFont(new Font("Arial", Font.PLAIN, 14));

            // Label and combo box for choosing sorting algorithm
            JLabel algoLabel = new JLabel("Choose sorting algorithm:");
            algoLabel.setFont(new Font("Arial", Font.BOLD, 16));
            algoLabel.setForeground(Color.BLACK);
            String[] algorithms = {"QuickSort", "BubbleSort"};
            JComboBox<String> algoComboBox = new JComboBox<>(algorithms);
            algoComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

            JButton sortButton = new JButton("Sort");
            sortButton.setBackground(Color.BLACK);
            sortButton.setForeground(Color.WHITE);
            sortButton.setFont(new Font("Arial", Font.PLAIN, 14));

            // On click: load file, detect data type, sort the data, and save the result if desired
            sortButton.addActionListener(e2 -> {
                String filePath = pathField.getText();
                String algorithm = (String) algoComboBox.getSelectedItem();
                Sort.sortExistingSet(filePath, algorithm);
            });

            existingSetFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            ImageIcon dog1 = new ImageIcon("existingSetButton.png");
            JLabel dog1Label = new JLabel(dog1);

            existingSetFrame.add(pathLabel);
            existingSetFrame.add(pathField);
            existingSetFrame.add(algoLabel);
            existingSetFrame.add(algoComboBox);
            existingSetFrame.add(sortButton);
            existingSetFrame.add(dog1Label);
        });

        // Button: Generate New Set
        JButton generateNewSetButton = new JButton("Generate New Set");
        generateNewSetButton.setBackground(Color.BLACK);
        generateNewSetButton.setForeground(Color.WHITE);
        generateNewSetButton.setFont(new Font("Arial", Font.PLAIN, 14));

        generateNewSetButton.addActionListener(e -> {
            mainFrame.setEnabled(false);
            JFrame newSetFrame = new JFrame("Generate New Set");
            newSetFrame.setSize(500, 600);
            newSetFrame.setLocationRelativeTo(null); // Center the child window on screen
            newSetFrame.setVisible(true);
            newSetFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            newSetFrame.getContentPane().setBackground(new Color(229, 226, 220));
            newSetFrame.setIconImage(image.getImage());

            // Re-enable the main window when the child window is closed
            newSetFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                    mainFrame.setEnabled(true);
                }
                @Override
                public void windowClosed(WindowEvent we) {
                    mainFrame.setEnabled(true);
                }
            });

            // Label and combo box for set size selection
            JLabel sizeLabel = new JLabel("Choose set size:");
            sizeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            sizeLabel.setForeground(Color.BLACK);
            String[] sizes = {"100", "1000", "100000"};
            JComboBox<String> sizeComboBox = new JComboBox<>(sizes);
            sizeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

            // Label and combo box for set type selection
            JLabel typeLabel = new JLabel("Choose set type:");
            typeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            typeLabel.setForeground(Color.BLACK);
            String[] types = {"Integer", "Double", "String"};
            JComboBox<String> typeComboBox = new JComboBox<>(types);
            typeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

            // Label and combo box for choosing sorting algorithm
            JLabel algoLabel = new JLabel("Choose sorting algorithm:");
            algoLabel.setFont(new Font("Arial", Font.BOLD, 16));
            algoLabel.setForeground(Color.BLACK);
            String[] algorithms = {"QuickSort", "BubbleSort"};
            JComboBox<String> algoComboBox = new JComboBox<>(algorithms);
            algoComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

            JButton sortButton = new JButton("Sort");
            sortButton.setBackground(Color.BLACK);
            sortButton.setForeground(Color.WHITE);
            sortButton.setFont(new Font("Arial", Font.PLAIN, 14));

            // On click: generate the set, sort the data, and save the result if desired
            sortButton.addActionListener(e2 -> {
                String selectedSize = (String) sizeComboBox.getSelectedItem();
                int size = Integer.parseInt(selectedSize);
                String selectedType = (String) typeComboBox.getSelectedItem();
                String algorithm = (String) algoComboBox.getSelectedItem();
                Sort.sortGeneratedSet(selectedType, size, algorithm);
            });

            newSetFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            ImageIcon dog2 = new ImageIcon("newSetButton.png");
            JLabel dog2Label = new JLabel(dog2);

            newSetFrame.add(sizeLabel);
            newSetFrame.add(sizeComboBox);
            newSetFrame.add(typeLabel);
            newSetFrame.add(typeComboBox);
            newSetFrame.add(algoLabel);
            newSetFrame.add(algoComboBox);
            newSetFrame.add(sortButton);
            newSetFrame.add(dog2Label);
        });

        mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel imageLabel = new JLabel(image);
        mainFrame.add(label);
        mainFrame.add(addExistingSetButton);
        mainFrame.add(generateNewSetButton);
        mainFrame.add(imageLabel);
        mainFrame.setVisible(true);
    }
}
