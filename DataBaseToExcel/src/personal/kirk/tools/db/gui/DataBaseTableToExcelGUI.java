package personal.kirk.tools.db.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import personal.kirk.tools.db.DataBaseTableToExcel;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DataBaseTableToExcelGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JTextField jTextField2;
	private JLabel jLabel4;
	private JTextField jTextField0;
	private JLabel jLabel1;
	private JTextField jTextField1;
	private JTextField jTextField3;
	private JTextField jTextField4;
	private JButton jButton0;
	
	private DataBaseTableToExcel dbToExcel = new DataBaseTableToExcel();
	private JLabel jLabel5;
	private JTextField jTextField5;
	private JLabel jLabel6;
	private JTextField jTextField6;
	private JButton jButton1;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public DataBaseTableToExcelGUI() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Oracle Database Detail To Excel");
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(6, 10, 10), new Leading(6, 10, 10)));
		add(getJLabel2(), new Constraints(new Leading(6, 10, 10), new Leading(30, 10, 10)));
		add(getJTextField0(), new Constraints(new Leading(49, 97, 12, 12), new Leading(4, 10, 94)));
		add(getJLabel5(), new Constraints(new Leading(147, 10, 10), new Leading(6, 12, 12)));
		add(getJTextField5(), new Constraints(new Leading(160, 39, 10, 10), new Leading(4, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(202, 10, 10), new Leading(6, 12, 12)));
		add(getJTextField1(), new Constraints(new Leading(267, 97, 10, 10), new Leading(4, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(183, 10, 10), new Leading(30, 12, 12)));
		add(getJTextField3(), new Constraints(new Leading(253, 111, 12, 12), new Leading(28, 12, 12)));
		add(getJTextField2(), new Constraints(new Leading(69, 111, 10, 10), new Leading(28, 10, 70)));
		add(getJLabel6(), new Constraints(new Leading(6, 12, 12), new Leading(53, 12, 12)));
		add(getJTextField6(), new Constraints(new Leading(152, 212, 12, 12), new Leading(52, 12, 12)));
		add(getJLabel4(), new Constraints(new Leading(6, 12, 12), new Leading(78, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(6, 358, 12, 12), new Leading(100, 12, 12)));
		add(getJTextField4(), new Constraints(new Leading(88, 211, 10, 10), new Leading(76, 10, 64)));
		add(getJButton1(), new Constraints(new Leading(301, 63, 10, 10), new Leading(73, 12, 12)));
		setSize(374, 138);
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("瀏覽");
			jButton1.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton1MouseMouseClicked(event);
				}
			});
		}
		return jButton1;
	}

	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setText("jTextField6");
		}
		return jTextField6;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Output File Prefix Name：");
		}
		return jLabel6;
	}

	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
		}
		return jTextField5;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("：");
		}
		return jLabel5;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("輸出");
			jButton0.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton0MouseMouseClicked(event);
				}
			});
		}
		return jButton0;
	}

	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
		}
		return jTextField4;
	}

	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
		}
		return jTextField3;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
		}
		return jTextField1;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("DB Name：");
		}
		return jLabel1;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		}
		return jTextField0;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Output Path：");
		}
		return jLabel4;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
		}
		return jTextField2;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Password：");
		}
		return jLabel3;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Account：");
		}
		return jLabel2;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("DB IP：");
		}
		return jLabel0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DataBaseTableToExcelGUI frame = new DataBaseTableToExcelGUI();
				frame.setDefaultCloseOperation(DataBaseTableToExcelGUI.EXIT_ON_CLOSE);
				frame.setTitle("DataBaseTableToExcelGUI");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
				frame.getJTextField0().setText("10.76.134.3");
				frame.getJTextField5().setText("1533");
				frame.getJTextField1().setText("APPSS");
				frame.getJTextField2().setText("ebookstg");
				frame.getJTextField3().setText("ebookstg123");
				frame.getJTextField6().setText("FET_EBOOK_TABLES");
				frame.getJTextField4().setText("D:");
			}
		});
	}

	private void jButton0MouseMouseClicked(MouseEvent event) {
		this.dbToExcel.setOracleConnection(getJTextField0().getText(), getJTextField5().getText(), 
				getJTextField1().getText(), getJTextField2().getText(), getJTextField3().getText());
		this.dbToExcel.createOneFile(getJTextField4().getText()+"\\", getJTextField6().getText());
	}

	private void jButton1MouseMouseClicked(MouseEvent event) {
		JFileChooser jc = new JFileChooser(getJTextField4().getText());
		jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jc.setAcceptAllFileFilterUsed(false);
		jc.setDialogTitle("請選擇輸出檔案位置");
		int returnVal = jc.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			File file = jc.getSelectedFile();
			getJTextField4().setText(file.getAbsolutePath());
		}
	}

}
