/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bd.BdCarro;
import bd.BdCliente;
import bd.BdPagamento;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import object.Carro;
import object.Cliente;
import object.Pagamento;

/**
 *
 * @author Leto
 */
public class FormPagamento extends javax.swing.JFrame {

    /**
     * Creates new form FormPagamento
     */
    public FormPagamento() {
        initComponents();
        try {
            this.setLocationRelativeTo(null);
            model.setColumnIdentifiers(new String[]{"*", "Placa", "Cliente", "Data", "Hora"});
            jTable1.setModel(model);

            DefaultComboBoxModel cbCarro = new DefaultComboBoxModel();
            jComboPlaca.setModel(cbCarro);
            carregarCombobox(cbCarro);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    DefaultTableModel model = new DefaultTableModel();
    ArrayList<Pagamento> retorno = new ArrayList<Pagamento>();
    BdPagamento bdPag = new BdPagamento();
    BdCarro bcCarro = new BdCarro();

    ArrayList<Carro> arrayCarro = new ArrayList<Carro>();
    BdCarro bdCarro = new BdCarro();

    private void carregarCombobox(DefaultComboBoxModel cbPlaca) throws Exception {

        arrayCarro = bdCarro.select();
        String placa;
        cbPlaca.removeAllElements();
        for (int i = 0; i < arrayCarro.size(); i++) {
            placa = arrayCarro.get(i).getPlaca();
            cbPlaca.addElement(placa);

        }
    }

    public void carregartabela(DefaultTableModel modelo) throws Exception {

        try {
            modelo.setRowCount(0);
            retorno = bdPag.select();
            String codigo, placa, cliente, data, hora;
            cliente = "";
            for (int i = 0; i < retorno.size(); i++) {
                codigo = retorno.get(i).getCodigo();
                hora = retorno.get(i).getHora();
                data = retorno.get(i).getData();
                placa = retorno.get(i).getCarro().getPlaca();
                try {
                    ArrayList<Cliente> arraycli = new ArrayList<Cliente>();
                    BdCliente bCli = new BdCliente();
                    arraycli = bCli.select();
                    for (int j = 0; j < arraycli.size(); j++) {
                        if (arraycli.get(j).getCodigo().equals(retorno.get(i).getCarro().getCliente().getCodigo())) {
                            cliente = arraycli.get(j).getNome();
                        }
                    }
                } catch (Exception e) {
                    cliente = "Deletado: " + retorno.get(i).getCarro().getCliente().getCodigo();
                }

                model.addRow(new String[]{codigo, placa, cliente, data, hora});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERRO" + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboPlaca = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "*", "Cliente", "Placa", "Data", "Hora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
        }

        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Deletar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboPlaca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton4.setText("Fechar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jComboPlaca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jComboPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            carregartabela(model);
        } catch (Exception ex) {
            Logger.getLogger(FormCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "tem certeza que deseja excluir?") == 0) {
            Pagamento pagamento = new Pagamento();
            int selec = jTable1.getSelectedRow();
            pagamento.setCodigo(model.getValueAt(selec, 0).toString());
            try {
                bdPag.delete(pagamento);
                carregartabela(model);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            return;
        }
        JOptionPane.showMessageDialog(null, "Operação Cancelada");

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Date dataAtual = new Date();
        SimpleDateFormat dmy = new SimpleDateFormat("dd/MM/yyyy");
        String data = dmy.format(dataAtual);
        SimpleDateFormat hm = new SimpleDateFormat("HH:mm");
        String hora = hm.format(dataAtual);

        try {
            BdPagamento bd = new BdPagamento();
            Pagamento pagamento = new Pagamento();
            //Pegando o ultimo codigo do pagamento
            ArrayList<Pagamento> arr = new ArrayList<Pagamento>();
            arr = bd.select();
            int cod = Integer.parseInt(arr.get(arr.size() - 1).getCodigo()) + 1; //Ultimo Codigo + 1
            
            //Pegando o ultimo codigo do pagamento
            pagamento.setData(data);
            pagamento.setHora(hora);
            pagamento.setCarro(arrayCarro.get(jComboPlaca.getSelectedIndex()));
            String valida;
            valida = "";
            for (int i = 0; i < arr.size(); i++) {
                //Pegar o ultimo codigo do pagamento
                if (cod==Integer.parseInt(arr.get(i).getCodigo())){
                    cod++;
                }
                if (arr.get(i).getCarro().getPlaca().equals(pagamento.getCarro().getPlaca())) {
                    valida = arr.get(i).getCodigo();

                }

            }
            pagamento.setCodigo(cod + "");
            bd.insert(pagamento, valida);
            try {
                carregartabela(model);
            } catch (Exception ex) {
                Logger.getLogger(FormRevisao.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception ex) {
            Logger.getLogger(FormPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPagamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboPlaca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
