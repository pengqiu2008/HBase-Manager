/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package com.vg.hbase.manager.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import com.vg.hbase.comm.manager.TableRowObject;
import com.vg.hbase.operations.base.HbaseManagerTableGetter;

/**
 * 
 * @author skrishnanunni
 */
public class HbaseManagerColoumnVersionDetailView extends javax.swing.JFrame {
    
    /**
     * 
     */
    private List<String> coloumnQualifiers = new ArrayList<String>();
    private Map<String, List<KeyValue>> versionColoumns = new HashMap<String, List<KeyValue>>();
    
    private static final long serialVersionUID = -1145103491324638597L;
    
    /**
     * Creates new form HbaseManagerColoumnVersionDetailView
     */
    public HbaseManagerColoumnVersionDetailView(String rowKey, String tableName, String familyName) {
	initComponents();
	getVersionDataOfRow(rowKey, tableName, familyName);
	if (comboColQualifierForVersions.getSelectedIndex() < 0) {
	    
	    versionViewPanel.setEnabled(false);
	    buttonClickGetVersions.setEnabled(false);
	    
	}
    }
    
    private void getVersionDataOfRow(String rowKey, String tableName, String familyName) {
	
	HTable table = HbaseManagerTableGetter.getTable(tableName);
	Get rowGet = new Get(Bytes.toBytes(rowKey));
	rowGet.setMaxVersions();
	Result versionedData = null;
	try {
	    versionedData = table.get(rowGet);
	    TableRowObject rowVersionObject = new TableRowObject(versionedData, familyName, true);
	    Object[] dataObject = rowVersionObject.getAllRowInfo();
	    
	    this.coloumnQualifiers = (List<String>) dataObject[0];
	    this.versionColoumns = (Map<String, List<KeyValue>>) dataObject[1];
	    
	    Iterator<String> colQs = this.coloumnQualifiers.iterator();
	    while (colQs.hasNext()) {
		
		comboColQualifierForVersions.addItem(colQs.next());
	    }
	    
	}
	catch (IOException e) {
	    
	    e.printStackTrace();
	}
	
    }
    
    public HbaseManagerColoumnVersionDetailView() {
	initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    private void initComponents() {
	
	versionViewPanel = new javax.swing.JPanel();
	jScrollPane1 = new javax.swing.JScrollPane();
	versionDataList = new javax.swing.JList(new DefaultListModel());
	versionViewStatusMessage = new javax.swing.JLabel();
	jPanel1 = new javax.swing.JPanel();
	jLabel1 = new javax.swing.JLabel();
	buttonClickGetVersions = new javax.swing.JButton();
	comboColQualifierForVersions = new javax.swing.JComboBox();
	
	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	setTitle("Version Detail : ");
	Image image = Toolkit.getDefaultToolkit().getImage("hb.png");
	setIconImage(image);
	versionViewPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
	
	jScrollPane1.setViewportView(versionDataList);
	
	javax.swing.GroupLayout versionViewPanelLayout = new javax.swing.GroupLayout(versionViewPanel);
	versionViewPanel.setLayout(versionViewPanelLayout);
	versionViewPanelLayout.setHorizontalGroup(versionViewPanelLayout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addGroup(
		versionViewPanelLayout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1)
			.addContainerGap()));
	versionViewPanelLayout.setVerticalGroup(versionViewPanelLayout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addGroup(
		versionViewPanelLayout.createSequentialGroup().addContainerGap()
			.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
			.addContainerGap()));
	
	versionViewStatusMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	versionViewStatusMessage.setText("Status: -Waiting for User Action-");
	
	jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	
	jLabel1.setText("Versions for Coloumn: ");
	
	buttonClickGetVersions.setText("Get All Versions");
	
	comboColQualifierForVersions.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		comboColQualifierForVersionsActionPerformed(evt);
	    }
	});
	
	buttonClickGetVersions.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		buttonClickGetVersionsActionPerformed(evt);
	    }
	});
	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	jPanel1.setLayout(jPanel1Layout);
	jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(
			jPanel1Layout
				.createSequentialGroup()
				.addContainerGap()
				.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(comboColQualifierForVersions, javax.swing.GroupLayout.PREFERRED_SIZE, 87,
					javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)
				.addComponent(buttonClickGetVersions).addContainerGap()));
	jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(
			jPanel1Layout
				.createSequentialGroup()
				.addContainerGap()
				.addGroup(
					jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jLabel1)
						.addComponent(comboColQualifierForVersions,
							javax.swing.GroupLayout.PREFERRED_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonClickGetVersions)).addContainerGap()));
	
	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	getContentPane().setLayout(layout);
	layout.setHorizontalGroup(layout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(
			layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(
					layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(versionViewPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap())
		.addComponent(versionViewStatusMessage, javax.swing.GroupLayout.DEFAULT_SIZE,
			javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
		layout.createSequentialGroup()
			.addGap(24, 24, 24)
			.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addGap(18, 18, 18)
			.addComponent(versionViewPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addComponent(versionViewStatusMessage).addContainerGap()));
	
	pack();
    }// </editor-fold>
    
    private void comboColQualifierForVersionsActionPerformed(java.awt.event.ActionEvent evt) {
	if (comboColQualifierForVersions.getSelectedIndex() > -1) {
	    
	    versionViewPanel.setEnabled(true);
	    buttonClickGetVersions.setEnabled(true);
	    jLabel1.setText("Versions for Coloumn Qualifier: " + comboColQualifierForVersions.getSelectedItem());
	    versionViewStatusMessage.setText("Click Get Versions to view all Versions");
	    
	}
    }
    
    private void buttonClickGetVersionsActionPerformed(java.awt.event.ActionEvent evt) {
	
	List<KeyValue> keyValues = this.versionColoumns.get(comboColQualifierForVersions.getSelectedItem());
	Iterator<KeyValue> value = keyValues.iterator();
	KeyValue keyval;
	int i = 0;
	String entry;
	
	DefaultListModel lmodel = (DefaultListModel) versionDataList.getModel();
	lmodel.clear();
	
	while (value.hasNext()) {
	    i++;
	    entry = "Version(" + i + "): ";
	    keyval = value.next();
	    entry = entry.concat(Bytes.toString(keyval.getValue())).concat(" : ") + keyval.getTimestamp();
	    lmodel.addElement(entry);
	    
	}
	versionViewStatusMessage.setText("Displaying all Available versions for "
		+ comboColQualifierForVersions.getSelectedItem());
    }
    
    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {
	/*
	 * Set the Nimbus look and feel
	 */
	// <editor-fold defaultstate="collapsed"
	// desc=" Look and feel setting code (optional) ">
	/*
	 * If Nimbus (introduced in Java SE 6) is not available, stay with the
	 * default look and feel. For details see
	 * http://download.oracle.com/javase
	 * /tutorial/uiswing/lookandfeel/plaf.html
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	}
	catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(HbaseManagerColoumnVersionDetailView.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	}
	catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(HbaseManagerColoumnVersionDetailView.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	}
	catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(HbaseManagerColoumnVersionDetailView.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	}
	catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(HbaseManagerColoumnVersionDetailView.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	}
	// </editor-fold>
	
	/*
	 * Create and display the form
	 */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    
	    public void run() {
		new HbaseManagerColoumnVersionDetailView().setVisible(true);
	    }
	});
    }
    
    // Variables declaration - do not modify
    private javax.swing.JButton buttonClickGetVersions;
    private javax.swing.JComboBox comboColQualifierForVersions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList versionDataList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel versionViewPanel;
    private javax.swing.JLabel versionViewStatusMessage;
    // End of variables declaration
}
