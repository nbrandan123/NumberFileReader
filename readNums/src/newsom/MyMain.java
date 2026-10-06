package newsom;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileReader;

public class MyMain {

	private JFrame frame;
	private JTextField textMean;
	private JTextField textStdDev;
	private LinkedList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyMain window = new MyMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 248);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textMean = new JTextField();
		textMean.setEditable(false);
		textMean.setBounds(138, 124, 86, 20);
		frame.getContentPane().add(textMean);
		textMean.setColumns(10);
		
		textStdDev = new JTextField();
		textStdDev.setEditable(false);
		textStdDev.setColumns(10);
		textStdDev.setBounds(138, 155, 86, 20);
		frame.getContentPane().add(textStdDev);
		
		JLabel lblNewLabel = new JLabel("Mean:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(16, 127, 107, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblStandardDeviation = new JLabel("Standard Deviation:");
		lblStandardDeviation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStandardDeviation.setBounds(10, 158, 113, 14);
		frame.getContentPane().add(lblStandardDeviation);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(234, 11, 190, 88);
		frame.getContentPane().add(scrollPane);
		
		JTextArea numDisplay = new JTextArea();
		numDisplay.setEditable(false);
		numDisplay.setWrapStyleWord(true);
		numDisplay.setLineWrap(true);
		numDisplay.setText("Your selected text file will appear here.");
		scrollPane.setViewportView(numDisplay);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(234, 110, 190, 88);
		frame.getContentPane().add(scrollPane2);
		
		JTextArea errDisplay = new JTextArea();
		errDisplay.setWrapStyleWord(true);
		errDisplay.setText("Any invalid numbers from your file will appear here.");
		errDisplay.setLineWrap(true);
		errDisplay.setEditable(false);
		scrollPane2.setViewportView(errDisplay);
		
		JButton btnLoad = new JButton("Choose File");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a file chooser
				JFileChooser fileChooser = new JFileChooser();
				
				// Filter to only allow text files
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
				fileChooser.setFileFilter(filter);
				
				// Display the file chooser dialog
				int returnValue = fileChooser.showOpenDialog(null);
				
				// Verify that the user selected a file
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String filePath = selectedFile.getAbsolutePath();
					
					// Display contents in numDisplay area
					String fileContents = ReadFile.readFileContents(filePath);
					numDisplay.setText(fileContents);
					
					// Create a linked list from the file contents, weeding out non-integer values
					list = LinkedList.toList(fileContents);
					
					// Display the error array in the errDisplay area
					errDisplay.setText("Invalid Numbers:" + LinkedList.getErrors(fileContents));
				}
				else {
					numDisplay.setText("No file selected.");
				}

				
			}
		});
		btnLoad.setBounds(44, 29, 113, 23);
		frame.getContentPane().add(btnLoad);
		
		
		JButton btnCalc = new JButton("Calculate");
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double listMean = 0.0;
				double listStdDev = 0.0;
				for (int i = 0; i <= list.getSize(); i++) {
					
					// Calculate the mean and standard deviation of the linked list
					listMean = list.calcMean();
					listStdDev = list.calcStdDev();
					
					// Display results
					textMean.setText(String.format("%.2f", listMean));
					textStdDev.setText(String.format("%.2f", listStdDev));
				}
			}
		});
		btnCalc.setBounds(44, 63, 113, 23);
		frame.getContentPane().add(btnCalc);
	}
}
