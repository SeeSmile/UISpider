/*
 * TestPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package ui;

/**
 *
 * @author  __USER__
 */
public class TestPanel extends javax.swing.JPanel {

	/** Creates new form TestPanel */
	public TestPanel() {
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		button1 = new java.awt.Button();
		jButton1 = new javax.swing.JButton();

		button1.setLabel("button1");

		jButton1.setText("jButton1");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(286, Short.MAX_VALUE)
								.addComponent(button1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(57, 57, 57))
				.addGroup(
						layout.createSequentialGroup().addGap(123, 123, 123)
								.addComponent(jButton1)
								.addContainerGap(196, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addGap(40, 40, 40)
						.addComponent(button1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(30, 30, 30).addComponent(jButton1)
						.addContainerGap(179, Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private java.awt.Button button1;
	private javax.swing.JButton jButton1;
	// End of variables declaration//GEN-END:variables

}