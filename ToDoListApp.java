import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import javax.swing.event.*;
import java.io.*;
//import java.util.*;

public class ToDoListApp{
    private static DefaultListModel<String> listModel;
    public static void main(String[] args) {
         // Create the main window (JFrame)
    JFrame frame = new JFrame("To-Do List");
    frame.setSize(300,300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   // Initialize list model and load tasks from file
    listModel = new DefaultListModel<>();
    loadTasks();                                    //24BSAI036 & 24BSAI043

 // Create JList to display tasks
    JList<String> todolist = new JList<>(listModel);
    todolist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scrollPane = new JScrollPane(todolist);
    frame.add(scrollPane,BorderLayout.CENTER);
    
    // Create a panel for user input
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

     // Text field for entering new tasks
    JTextField textField = new JTextField();
    panel.add(textField, BorderLayout.CENTER);

        // Button to add tasks
    JButton addButton = new JButton("Add Task");
    panel.add(addButton,BorderLayout.EAST);

       // Button to delete selected task
    JButton deleteButton = new JButton("Delete Task");
    panel.add(deleteButton, BorderLayout.SOUTH);
    
        // Add the input panel to the frame
    frame.add(panel, BorderLayout.SOUTH);

        // Action to add tasks
    addButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            String task = textField.getText().trim();
            if (!task.isEmpty()) {
                listModel.addElement(task);  // Add the task to the list model
                textField.setText(""); // Clear the text field after adding
                saveTasks(); // Save the tasks to file
            }
        }
    });
        // Action to delete selected task
    deleteButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            int selectedIndex = todolist.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);// Remove the selected task
                saveTasks();  // Save the tasks to file
            }
        }
    });

    //decorating
    addButton.setFocusPainted(false);
    deleteButton.setFocusPainted(false);

    deleteButton.setBackground(new Color(220, 20, 60));
    deleteButton.setForeground(Color.WHITE);
    addButton.setBackground(new Color(76, 175, 80));
    //addButton.setBackground(new Color(0, 123, 255));
    addButton.setForeground(Color.WHITE);
    frame.setBackground(new Color(173, 216, 230));      //24BSAI036 & 24BSAI043


   // textField.setBackground(new Color(173, 216, 230));// Name: Light Blue
    textField.setBackground(new Color(245, 245, 220));
    textField.setFont(new Font("Roboto", Font.BOLD, 15));


     // Show the frame
    frame.setVisible(true);
}
       // Method to save tasks to a file
    private static void saveTasks(){
    try(BufferedWriter writer = new BufferedWriter(new FileWriter("Tasks.txt"))){
        for(int i = 0 ; i<listModel.size(); i++){
            writer.write(listModel.getElementAt(i));
            writer.newLine();
        }
    } catch(IOException e){
        e.printStackTrace();
    }

}
    // Method to load tasks from a file
    private static void loadTasks(){
        try(BufferedReader reader = new BufferedReader(new FileReader("Tasks.txt")) ){
            String line;
            while((line = reader.readLine())!=null){
                listModel.addElement(line);
            }
        } catch(IOException e){
             // No tasks to load if file doesn't exist or error

        }
    }
}