import com.ti.et.education.commproxy.INodeID;
import com.ti.et.education.commproxy.INodeInfo;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.util.LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Levak
 */
public class DeviceSelectionFrame extends javax.swing.JFrame {
    private INodeID[] selectedItems;

    /**
     * Creates new form DeviceSelectionFrame
     */
    public DeviceSelectionFrame(final NspireKeyboard nk) {
        initComponents();
        TableColumn col = this.DeviceList.getColumnModel().getColumn(0);
        col.setCellEditor(this.DeviceList.getDefaultEditor(Boolean.class));
        col.setCellRenderer(this.DeviceList.getDefaultRenderer(Boolean.class));
        this.DeviceList.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getColumn() == 0) {
                    updateSelectedItems();
                } else if (e.getColumn() == 1) {
                    System.out.println(DeviceList.getValueAt(e.getFirstRow(), e.getColumn()));
                }
                nk.updateFields();
            }
        });
        ImageIcon icn = new ImageIcon(getClass().getResource("nremote.png"));
        this.setIconImage(icn.getImage());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        devices = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DeviceList = new javax.swing.JTable();
        tools = new javax.swing.JPanel();
        left = new javax.swing.JPanel();
        ALL = new javax.swing.JButton();
        NONE = new javax.swing.JButton();
        right = new javax.swing.JPanel();
        OK = new javax.swing.JButton();

        setTitle("Device Selection");

        Panel.setLayout(new javax.swing.BoxLayout(Panel, javax.swing.BoxLayout.Y_AXIS));

        DeviceList.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "", "Device Name", "SID", "Version"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.Boolean.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        DeviceList.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        jScrollPane1.setViewportView(DeviceList);

        javax.swing.GroupLayout devicesLayout = new javax.swing.GroupLayout(devices);
        devices.setLayout(devicesLayout);
        devicesLayout.setHorizontalGroup(
                devicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
        );
        devicesLayout.setVerticalGroup(
                devicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
        );

        Panel.add(devices);

        tools.setMaximumSize(new java.awt.Dimension(32767, 48));

        left.setLayout(new java.awt.GridLayout(1, 0));

        ALL.setText("All");
        ALL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ALLActionPerformed(evt);
            }
        });
        left.add(ALL);

        NONE.setText("None");
        NONE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NONEActionPerformed(evt);
            }
        });
        left.add(NONE);

        right.setLayout(new java.awt.GridLayout(1, 0));

        OK.setText("Ok");
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });
        right.add(OK);

        javax.swing.GroupLayout toolsLayout = new javax.swing.GroupLayout(tools);
        tools.setLayout(toolsLayout);
        toolsLayout.setHorizontalGroup(
                toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(toolsLayout.createSequentialGroup()
                                .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                                .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        toolsLayout.setVerticalGroup(
                toolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(left, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(right, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Panel.add(tools);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_OKActionPerformed

    private void ALLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ALLActionPerformed
        DefaultTableModel model = (DefaultTableModel) this.DeviceList.getModel();
        int n = model.getRowCount();
        for (int i = 0; i < n; i++) {
            model.setValueAt(true, i, 0);
        }
    }//GEN-LAST:event_ALLActionPerformed

    private void NONEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NONEActionPerformed
        DefaultTableModel model = (DefaultTableModel) this.DeviceList.getModel();
        int n = model.getRowCount();
        for (int i = 0; i < n; i++) {
            model.setValueAt(false, i, 0);
        }
    }//GEN-LAST:event_NONEActionPerformed

    public void removeAllItems() {
        DefaultTableModel model = (DefaultTableModel) this.DeviceList.getModel();
        int n = model.getRowCount();
        for (int i = 0; i < n; i++) {
            model.removeRow(0);
        }
        this.selectedItems = new INodeID[]{};
    }

    public TableModel getModel() {
        return this.DeviceList.getModel();
    }

    public INodeID[] getSelectedItems() {
        return this.selectedItems;
    }

    public void updateSelectedItems() {
        LinkedList<INodeID> list = new LinkedList<INodeID>();
        TableModel model = this.DeviceList.getModel();
        int n = model.getRowCount();
        for (int i = 0; i < n; i++) {
            if (((Boolean) model.getValueAt(i, 0))) {
                list.add(((CalcItem) model.getValueAt(i, 2)).nodeID);
            }
        }
        this.selectedItems = new INodeID[list.size()];
        list.toArray(this.selectedItems);
    }

    public void updateItems() {
        INodeID[] tab = Remote.theCalcs;
        DefaultTableModel model = (DefaultTableModel) this.DeviceList.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            boolean isPresent = false;
            for (int j = 0; j < tab.length; j++) {
                if (((CalcItem) model.getValueAt(i, 2)).nodeID.equals(tab[j])) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                model.removeRow(i);
            }
        }
        for (int j = 0; j < tab.length; j++) {
            boolean isPresent = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                if (((CalcItem) model.getValueAt(i, 2)).nodeID.equals(tab[j])) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                INodeInfo info = Remote.getDeviceInfo(tab[j]);
                model.addRow(new Object[]{
                        true,
                        info.getName(),
                        new CalcItem(tab[j]),
                        info.getVersion()
                });
            }
        }
        updateSelectedItems();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ALL;
    private javax.swing.JTable DeviceList;
    private javax.swing.JButton NONE;
    private javax.swing.JButton OK;
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel devices;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel left;
    private javax.swing.JPanel right;
    private javax.swing.JPanel tools;
    // End of variables declaration//GEN-END:variables

}
