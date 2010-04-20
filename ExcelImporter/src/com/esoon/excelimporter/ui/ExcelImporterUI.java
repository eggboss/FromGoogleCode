package com.esoon.excelimporter.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import com.esoon.excelimporter.exception.ImportException;
import com.esoon.excelimporter.poi.ExcelHandlerImpl;
import com.esoon.excelimporter.poi.ExcelTransform;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ExcelImporterUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JTextField jTextField0;
	private JButton jButton0;
	private JButton jButton2;
	private JButton jButton1;
	private JFileChooser jFileChooser;
	private String filePath;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public ExcelImporterUI() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(8, 10, 10), new Leading(8, 10, 10)));
		add(getJTextField0(), new Constraints(new Leading(76, 107, 10, 10), new Leading(6, 12, 12)));
		add(getJButton0(), new Constraints(new Bilateral(189, 12, 86), new Leading(3, 12, 12)));
		add(getJButton2(), new Constraints(new Trailing(12, 153, 153), new Leading(41, 12, 12)));
		add(getJButton1(), new Constraints(new Trailing(136, 12, 12), new Leading(41, 12, 12)));
		setSize(288, 79);
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("上傳客戶資料");
			jButton1.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButton1ActionActionPerformed(event);
				}
			});
		}
		return jButton1;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("上傳產品資料");
			jButton2.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButton2ActionActionPerformed(event);
				}
			});
		}
		return jButton2;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("選擇檔案");
			jButton0.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jButton0ActionActionPerformed(event);
				}
			});
		}
		return jButton0;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
			jTextField0.setText("");
		}
		return jTextField0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("檔案路徑：");
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
			@Override
			public void run() {
				ExcelImporterUI frame = new ExcelImporterUI();
				frame.setDefaultCloseOperation(ExcelImporterUI.EXIT_ON_CLOSE);
				frame.setTitle("ExcelImporterUI");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	// 選擇檔案
	private void jButton0ActionActionPerformed(ActionEvent event) {
		jFileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XLS & XLSX Images", "xls", "xlsx");
		jFileChooser.setFileFilter(filter);
	    int returnVal = jFileChooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	this.getJTextField0().setText(jFileChooser.getSelectedFile().getName());
	    	filePath = jFileChooser.getSelectedFile().getAbsolutePath();
	    	System.out.println(filePath);
	    }
	}

	// 上傳客戶資料
	private void jButton1ActionActionPerformed(ActionEvent event) {
		try {
			if(filePath!=null && !"".equals(filePath)){
				
				// 傳入Excel檔案路徑，及Excel處理實作。
				new ExcelTransform(filePath, new ExcelHandlerImpl()).transfer();
				
				JOptionPane.showMessageDialog(this, "上傳成功！");
				
				filePath = null;
				this.getJTextField0().setText("");
			}else{
				JOptionPane.showMessageDialog(this, "請選擇檔案！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "資料庫錯誤！" + e.getMessage());
		} catch (ImportException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "檔案有錯，請重新選擇！" + e.getMessage());
		}
	}

	// 上傳產品資料
	private void jButton2ActionActionPerformed(ActionEvent event) {
		try {
			if(filePath!=null && !"".equals(filePath)){
				
				// 傳入Excel檔案路徑，及Excel處理實作。
				new ExcelTransform(filePath, new ExcelHandlerImpl()).transfer();
				
				JOptionPane.showMessageDialog(this, "上傳成功！");
				
				filePath = null;
				this.getJTextField0().setText("");
			}else{
				JOptionPane.showMessageDialog(this, "請選擇檔案！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "資料庫錯誤！" + e.getMessage());
		} catch (ImportException e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "檔案有錯，請重新選擇！" + e.getMessage());
		}
	}

}
