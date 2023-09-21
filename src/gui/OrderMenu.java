package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import java.awt.Color;


import javax.swing.border.Border;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.itextpdf.text.Document;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;

public class OrderMenu extends FoodMenu{
	
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					OrderMenu window = new OrderMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application.

	public OrderMenu() throws IOException {
		
		createAndShowGUI();
	}

	//Initialize the contents of the frame.
	 

	void createAndShowGUI() throws IOException {
		frame = new JFrame("Order Form");
		frame.setBounds(100, 100, 420, 300); // x,y,width,height
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblFillInDetails = new JLabel("Fill in Details");
		lblFillInDetails.setBounds(120, 11, 131, 26);
		lblFillInDetails.setFont(new Font("Serif", Font.PLAIN, 16));
		lblFillInDetails.setForeground(Color.BLUE);
		frame.getContentPane().add(lblFillInDetails);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 46, 46, 14);
		frame.getContentPane().add(lblName);

		textField = new JTextField();
		textField.setBounds(82, 43, 176, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblAddress = new JLabel("Tel No");
		lblAddress.setBounds(10, 92, 46, 14);
		frame.getContentPane().add(lblAddress);

		textField_1 = new JTextField();
		textField_1.setBounds(82, 89, 176, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblAddress_1 = new JLabel("Address");
		lblAddress_1.setBounds(10, 137, 62, 14);
		frame.getContentPane().add(lblAddress_1);

		Border border = BorderFactory.createLineBorder(Color.BLACK);
		textArea = new JTextArea();
		textArea.setBounds(82, 132, 236, 85);
		textArea.setMargin(new Insets(10,10,10,10)); // top,left,bottom,right
		textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		textArea.setLineWrap(true);
		frame.getContentPane().add(textArea);

		JButton btnCancel = new JButton("Cancel ");
		btnCancel.setBounds(130, 228,89, 23);
		frame.getContentPane().add(btnCancel);
		
		
		

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FoodMenu food = new FoodMenu();
				try {  
					food.createAndShowGUI();
					food.setVisible(true);
					setVisible(false);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}

				frame.dispose();
			}
		});

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(229, 228, 89, 23);
		frame.getContentPane().add(btnConfirm);

		btnConfirm.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || textField_1.getText().equals("")
						|| textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Field cannot be empty");
				} else {
					JOptionPane.showMessageDialog(null, "Your bill is generated to Downloads Folder");
					JOptionPane.showMessageDialog(null, "Your foods will be delivered soon, Thanks for ordering. ");
					
					MainMenu main = new MainMenu();
					try {
						
						
						Document document=new Document();
						PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\HP\\Downloads\\BILL.pdf"));
						
						document.open();
						document.add(new Paragraph("****************************************  YOUR FOOD ORDERS  *********************************"));
						document.add(new Paragraph("Customer Name : "+textField.getText()));
						document.add(new Paragraph("Customer Number : "+textField_1.getText()));
						document.add(new Paragraph("Customer Address : "+textArea.getText()));
						document.add(new Paragraph("\n\n"));

						PdfPTable tab=new PdfPTable(3);
						if(dtm!=null) {
						for(int i=0;i<dtm.getRowCount();i++) {
							for(int j=0;j<3;j++) {
								tab.addCell(dtm.getValueAt(i, j).toString());
							}
							
						}}
						else
							JOptionPane.showMessageDialog(null, "No Food Items are mentioned.Try Again!!");
						document.add(tab);
						document.add(new Paragraph("\n\n"));
						document.add(new Paragraph("                                                GRAND TOTAL		     :  "+total));
						document.add(new Paragraph("\n\n"));
						document.add(new Paragraph("For Further Details"));
						document.add(new Paragraph("Contact : "));
						document.add(new Paragraph("0422-2457858"));
						document.add(new Paragraph("\n\n"));
						document.add(new Paragraph("                                      *****  THANKS FOR ORDERING  *****                         "));
						document.close();
						main.main(null);
					} catch (IOException | DocumentException e1) {
						
						e1.printStackTrace();
					}
					main.setVisible(true);
					
					frame.dispose();
				}
			}
		});
		Image image = ImageIO.read(this.getClass().getResource("/order.png"));
		Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(imageScaled);
		JLabel lblNewLabel = new JLabel(imageIcon);
		lblNewLabel.setBounds(268, 19, 126, 90);
		frame.getContentPane().add(lblNewLabel);
		

	}

	public void setVisible(boolean b) {
		
	}
}
