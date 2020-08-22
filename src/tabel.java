import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.istack.internal.logging.Logger;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JScrollBar;
public class tabel extends JFrame {
	private JTextField textCarModel;
	private JTextField textVIN;
	private JTextField textKm;
	private JTextField textPrice;
	private JTable table;
	private JTextField textYear;
	private JTextField textID;
	private JFrame frame;

	
	public void theQuery(String query) {
		Connection con=null;
		Statement st=null;
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3325/adi","root","");
			st=con.createStatement();
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null,"Query executed!");
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage());
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tabel frame = new tabel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	public tabel() {
		connection = sqliteConnection.dbConnector();
		setUndecorated(true);
		getContentPane().setBackground(new Color(175, 238, 238));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(20, 20, 901, 408);
		getContentPane().setLayout(null);
		setSize(1280,630);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(175, 238, 238));
		panel_1.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(0, 255, 255)));
		panel_1.setBounds(12, 51, 705, 547);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Car Model");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(140, 173, 124, 23);
		panel_1.add(lblNewLabel);
		
		textCarModel = new JTextField();
		textCarModel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textCarModel.setBounds(293, 163, 116, 42);
		panel_1.add(textCarModel);
		textCarModel.setColumns(10);
		
		JLabel lblVinNumber = new JLabel("VIN Number");
		lblVinNumber.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblVinNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblVinNumber.setBounds(94, 229, 188, 23);
		panel_1.add(lblVinNumber);
		
		textVIN = new JTextField();
		textVIN.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textVIN.setColumns(10);
		textVIN.setBounds(293, 218, 116, 42);
		panel_1.add(textVIN);
		
		JLabel lblKm = new JLabel("Km");
		lblKm.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblKm.setHorizontalAlignment(SwingConstants.CENTER);
		lblKm.setBounds(112, 280, 173, 23);
		panel_1.add(lblKm);
		
		textKm = new JTextField();
		textKm.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textKm.setColumns(10);
		textKm.setBounds(293, 273, 116, 42);
		panel_1.add(textKm);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setBounds(165, 337, 87, 23);
		panel_1.add(lblPrice);
		
		textPrice = new JTextField();
		textPrice.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textPrice.setColumns(10);
		textPrice.setBounds(293, 328, 116, 42);
		panel_1.add(textPrice);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setIcon(new ImageIcon(tabel.class.getResource("/plus.png")));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.MAGENTA));
		btnNewButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 21));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					theQuery("INSERT INTO registration(id,Car_Model,VIN_Number,Km,Price,Manufacturing_Year) VALUES ('"+textID.getText()+"','"+textCarModel.getText()+"','"+textVIN.getText()+"','"+textKm.getText()+"','"+textPrice.getText()+"','"+textYear.getText()+"')");
				}catch(Exception ex) {}
				
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						textID.getText(),
						textCarModel.getText(),
						textVIN.getText(),
						textKm.getText(),
						textPrice.getText(),
						textYear.getText(),
				});
				if(table.getSelectedRow()==-1) {
					if(table.getRowCount()==0) {
						JOptionPane.showMessageDialog(null,"Update confirmed","Car Registration System",JOptionPane.OK_OPTION);
					}
				}
		}
		});
		btnNewButton.setBounds(31, 460, 132, 42);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.setIcon(new ImageIcon(tabel.class.getResource("/bin.png")));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.MAGENTA));
		btnNewButton_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 21));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					theQuery("delete from registration where id = '"+textID.getText()+"'");
				}catch(Exception ex) {}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getSelectedRow()==-1) {
					if(table.getRowCount()==0) {
						JOptionPane.showMessageDialog(null,"No data to delete","Car Registration System",JOptionPane.OK_OPTION);}
						else {
							JOptionPane.showMessageDialog(null, "Select a row to delete","Car Registration System",JOptionPane.OK_OPTION);
						}
				}else	{
					model.removeRow(table.getSelectedRow());
				}
			}
		});
		btnNewButton_1.setBounds(196, 460, 132, 42);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Edit");
		btnNewButton_2.setIcon(new ImageIcon(tabel.class.getResource("/pencil.png")));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.MAGENTA));
		btnNewButton_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 21));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					theQuery("update registration set Manufacturing_Year = '"+textYear.getText()+"',VIN_Number = '"+textVIN.getText()+"',Km = '"+textKm.getText()+"',Price = '"+textPrice.getText()+"',Car_Model = '"+textCarModel.getText()+"' where id = '"+textID.getText()+"'");
				}catch(Exception ex) {}
			}
		});
		btnNewButton_2.setBounds(365, 460, 132, 43);
		panel_1.add(btnNewButton_2);
		
		JLabel lblManufacturingYear = new JLabel("Manufacturing Year");
		lblManufacturingYear.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblManufacturingYear.setHorizontalAlignment(SwingConstants.CENTER);
		lblManufacturingYear.setBounds(34, 387, 250, 33);
		panel_1.add(lblManufacturingYear);
		
		textYear = new JTextField();
		textYear.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textYear.setColumns(10);
		textYear.setBounds(293, 383, 116, 42);
		panel_1.add(textYear);
		
		JLabel lblId = new JLabel("id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(163, 117, 74, 23);
		panel_1.add(lblId);
		
		textID = new JTextField();
		textID.setFont(new Font("Tahoma", Font.PLAIN, 28));
		textID.setColumns(10);
		textID.setBounds(293, 108, 116, 42);
		panel_1.add(textID);
		
		JButton btnNewButton_4 = new JButton("RESET");
		btnNewButton_4.setIcon(new ImageIcon(tabel.class.getResource("/reset2.png")));
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.MAGENTA));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textID.setText("");
				textCarModel.setText("");
				textVIN.setText("");
				textKm.setText("");
				textPrice.setText("");
				textYear.setText("");
			}
		});
		btnNewButton_4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 16));
		btnNewButton_4.setBounds(532, 460, 132, 44);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("Load Data");
		btnNewButton_3.setIcon(new ImageIcon(tabel.class.getResource("/multimedia.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from registration";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.MAGENTA));
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setFont(new Font("Tw Cen MT", Font.BOLD, 19));
		btnNewButton_3.setBounds(532, 260, 132, 43);
		panel_1.add(btnNewButton_3);
		
		JButton excel = new JButton("Export");
		excel.setBackground(Color.WHITE);
		excel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.MAGENTA));
		excel.setIcon(new ImageIcon(tabel.class.getResource("/mac-os.png")));
		excel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileOutputStream excelFOU = null;    
				BufferedOutputStream excelBOU = null;
				XSSFWorkbook excelJTableExporter = null;
				
				JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\Adi-PC\\Desktop");
				excelFileChooser.setDialogTitle("Save as");
				FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES","xls","xlsx","xlsm");
				excelFileChooser.setFileFilter(fnef);
				int excelChooser = excelFileChooser.showSaveDialog(null);
				if(excelChooser == JFileChooser.APPROVE_OPTION) {
					
					try {
					excelJTableExporter = new XSSFWorkbook();
					XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet");
					
					for(int i=0;i<table.getRowCount();i++) {
						XSSFRow excelRow = excelSheet.createRow(i);
						for(int j=0;j<table.getColumnCount();j++) {
							XSSFCell excelCell = excelRow.createCell(j);
							
							excelCell.setCellValue(table.getValueAt(i, j).toString());
							
						}
					}
					
						
						
							excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile()+".xlsx");
						    excelBOU = new BufferedOutputStream(excelFOU);
							excelJTableExporter.write(excelBOU);
							JOptionPane.showMessageDialog(null,"Exported successfully !");
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}finally {
							try {
								if(excelBOU != null)
									excelBOU.close();
								if(excelFOU != null)
									excelFOU.close();
								if(excelJTableExporter != null)
									excelJTableExporter.close();
							}catch(IOException e1) {
								e1.printStackTrace();
							}
						}
						
						
					
					}
		
			}
		});
		excel.setFont(new Font("Tw Cen MT", Font.BOLD, 19));
		excel.setBounds(532, 163, 132, 40);
		panel_1.add(excel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.LIGHT_GRAY);
		scrollPane.setBounds(733, 51, 535, 545);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 255)));
		
		table.setBackground(new Color(175, 238, 238));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Car model", "VIN Number", "Km", "Price", "Year"
			}
		));
		
		JLabel lblCarRegistration = new JLabel("Car Registration");
		lblCarRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarRegistration.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 28));
		lblCarRegistration.setBounds(524, 5, 237, 38);
		getContentPane().add(lblCarRegistration);
		
		JLabel extitBTN = new JLabel("");
		extitBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = JOptionPane.showConfirmDialog(null, "Do you really want to exit this program ?","Message", JOptionPane.YES_NO_OPTION);
				if(x == 0)
				dispose();
			}
		});
		extitBTN.setHorizontalAlignment(SwingConstants.CENTER);
		extitBTN.setIcon(new ImageIcon(tabel.class.getResource("/reset.png")));
		extitBTN.setBounds(12, 10, 91, 33);
		getContentPane().add(extitBTN);
		
		
		
		
		
	}
}
