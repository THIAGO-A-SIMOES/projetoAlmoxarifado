/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Classes.OrdemCompra;
import DatabaseConnection.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MendeZ
 */
public class VisualizarOrdemDeCompra extends javax.swing.JFrame {

    /**
     * @return the listProd
     */
    public List<String> getListProd() {
        return listProd;
    }

    /**
     * @param listProd the listProd to set
     */
    public void setListProd(List<String> listProd) {
        this.listProd = listProd;
    }

    /**
     * @return the listFornecedor
     */
    public List<String> getListFornecedor() {
        return listFornecedor;
    }

    /**
     * @param listFornecedor the listFornecedor to set
     */
    public void setListFornecedor(List<String> listFornecedor) {
        this.listFornecedor = listFornecedor;
    }

    /**
     * @return the listClientes
     */
    public List<String> getListClientes() {
        return listClientes;
    }

    /**
     * @param listClientes the listClientes to set
     */
    public void setListClientes(List<String> listClientes) {
        this.listClientes = listClientes;
    }

    /**
     * @return the listOrdemC
     */
    public List<OrdemCompra> getListOrdemC() {
        return listOrdemC;
    }

    /**
     * @param listOrdemC the listOrdemC to set
     */
    public void setListOrdemC(List<OrdemCompra> listOrdemC) {
        this.listOrdemC = listOrdemC;
    }


    public VisualizarOrdemDeCompra() {
        initComponents();
        
        listOrdemC = new ArrayList<>();
        listClientes = new ArrayList<>();
        listFornecedor = new ArrayList<>();
        listProd = new ArrayList<>();
        preencherdados();
    }

    private List<OrdemCompra> listOrdemC;
    private List<String> listClientes;
    private List<String> listFornecedor;
    private List<String> listProd;
    
    private void preencherdados(){
        try {
            Connection conexao = ConexaoBanco.getConnection(); 
            String sql3 = "select * from ordemCompra";
            PreparedStatement ps3 = conexao.prepareStatement(sql3);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                int codO = rs3.getInt("codOrdemCompra");
                int codF = rs3.getInt("codFornecedor");
                int codCl = rs3.getInt("codCliente");
                int codTip = rs3.getInt("codTipoProduto");
                int quant = rs3.getInt("quantidade");
                float valorU = rs3.getFloat("valUnitario");
                int sit = rs3.getInt("situacao");
                int  quante = rs3.getInt("quantEstocada");
                OrdemCompra ordemcompra = new OrdemCompra(codO, valorU, quant, codCl, codF, codTip, sit, quante);
                getListOrdemC().add(ordemcompra);
                
                String sql4 = "select nome from cliente where codCliente = " + String.valueOf(codCl);
                PreparedStatement ps4 = conexao.prepareStatement(sql4);
                ResultSet rs4 = ps4.executeQuery();
                while (rs4.next()){
                    String nomeC = rs4.getString("nome");
                    getListClientes().add(nomeC);
                }
                
                String sql5 = "select nome from fornecedor where codFornecedor = " + String.valueOf(codF);
                PreparedStatement ps5 = conexao.prepareStatement(sql5);
                ResultSet rs5 = ps5.executeQuery();
                while (rs5.next()){
                    String nomeF = rs5.getString("nome");
                    getListFornecedor().add(nomeF);
                }
                
                String sql6 = "select descricao from tipoproduto where codTipoProduto = " + String.valueOf(codTip);
                PreparedStatement ps6 = conexao.prepareStatement(sql6);
                ResultSet rs6 = ps6.executeQuery();
                while (rs6.next()){
                    String nomeP = rs6.getString("descricao");
                    getListProd().add(nomeP);
                }
            }
                        
            
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
        DefaultTableModel model = (DefaultTableModel) TBOrdemDeCompra.getModel();

        int cont = 0;
        
        for (OrdemCompra ordemCompra : listOrdemC) {
            if(ordemCompra.getSituacao() == 1){
                model.addRow(new Object[]{ordemCompra.getCodOrdemCompra(), listClientes.get(cont), listFornecedor.get(cont), listProd.get(cont), ordemCompra.getQuantidade(), ordemCompra.getValUnitario(), "Em Andamento"});
                cont ++;
            }else{
                model.addRow(new Object[]{ordemCompra.getCodOrdemCompra(), listClientes.get(cont), listFornecedor.get(cont), listProd.get(cont), ordemCompra.getQuantidade(), ordemCompra.getValUnitario(), "Finalizada"});
                cont ++;
            }  
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OrndemDeCompra_pane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBOrdemDeCompra = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cadastrarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualizar Ordem de Compra");

        TBOrdemDeCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Cliente", "Fornecedor", "Produto", "Quantidade", "Valor por unidade", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TBOrdemDeCompra);
        if (TBOrdemDeCompra.getColumnModel().getColumnCount() > 0) {
            TBOrdemDeCompra.getColumnModel().getColumn(0).setResizable(false);
            TBOrdemDeCompra.getColumnModel().getColumn(1).setResizable(false);
            TBOrdemDeCompra.getColumnModel().getColumn(2).setResizable(false);
            TBOrdemDeCompra.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout OrndemDeCompra_paneLayout = new javax.swing.GroupLayout(OrndemDeCompra_pane);
        OrndemDeCompra_pane.setLayout(OrndemDeCompra_paneLayout);
        OrndemDeCompra_paneLayout.setHorizontalGroup(
            OrndemDeCompra_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        OrndemDeCompra_paneLayout.setVerticalGroup(
            OrndemDeCompra_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );

        cadastrarButton.setText("Registrar");
        cadastrarButton.setActionCommand("");
        cadastrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cadastrarButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(cadastrarButton)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(OrndemDeCompra_pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(OrndemDeCompra_pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cadastrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarButtonActionPerformed
        Cadastro_OrdemCompra co = new Cadastro_OrdemCompra();
        dispose();
        co.setVisible(true);
    }//GEN-LAST:event_cadastrarButtonActionPerformed

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
            java.util.logging.Logger.getLogger(VisualizarOrdemDeCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizarOrdemDeCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizarOrdemDeCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizarOrdemDeCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizarOrdemDeCompra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel OrndemDeCompra_pane;
    private javax.swing.JTable TBOrdemDeCompra;
    private javax.swing.JButton cadastrarButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
