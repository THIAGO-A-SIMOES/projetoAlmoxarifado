
package Telas;

import Classes.Cidade;
import Classes.Fornecedor;
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
public class EfetivarOrdemDeCompra extends javax.swing.JFrame {

    /**
     * @return the listCodTipoProduto
     */
    public List<Integer> getListCodTipoProduto() {
        return listCodTipoProduto;
    }

    /**
     * @param listCodTipoProduto the listCodTipoProduto to set
     */
    public void setListCodTipoProduto(List<Integer> listCodTipoProduto) {
        this.listCodTipoProduto = listCodTipoProduto;
    }

    /**
     * @return the listCodFornecedor
     */
    public List<Integer> getListCodFornecedor() {
        return listCodFornecedor;
    }

    /**
     * @param listCodFornecedor the listCodFornecedor to set
     */
    public void setListCodFornecedor(List<Integer> listCodFornecedor) {
        this.listCodFornecedor = listCodFornecedor;
    }

    /**
     * @return the listCodCliente
     */
    public List<Integer> getListCodCliente() {
        return listCodCliente;
    }

    /**
     * @param listCodCliente the listCodCliente to set
     */
    public void setListCodCliente(List<Integer> listCodCliente) {
        this.listCodCliente = listCodCliente;
    }


    public List<String> getListProd() {
        return listProd;
    }

   
    public void setListProd(List<String> listProd) {
        this.listProd = listProd;
    }

    public List<String> getListFornecedor() {
        return listFornecedor;
    }

  
    public void setListFornecedor(List<String> listFornecedor) {
        this.listFornecedor = listFornecedor;
    }

  
    public List<String> getListClientes() {
        return listClientes;
    }

    
    public void setListClientes(List<String> listClientes) {
        this.listClientes = listClientes;
    }

 
    public List<OrdemCompra> getListOrdemC() {
        return listOrdemC;
    }

  
    public void setListOrdemC(List<OrdemCompra> listOrdemC) {
        this.listOrdemC = listOrdemC;
    }


    public EfetivarOrdemDeCompra() {
        initComponents();
        
        listOrdemC = new ArrayList<>();
        listClientes = new ArrayList<>();
        listFornecedor = new ArrayList<>();
        listProd = new ArrayList<>();
        listCodCliente = new ArrayList<>();
        listCodFornecedor = new ArrayList<>();
        listCodTipoProduto = new ArrayList<>();
        preencherdados();
    }

    private List<OrdemCompra> listOrdemC;
    private List<String> listClientes;
    private List<String> listFornecedor;
    private List<String> listProd;
    private List<Integer> listCodCliente;
    private List<Integer> listCodFornecedor;
    private List<Integer> listCodTipoProduto;
    
    private void preencherdados(){
        try {
            Connection conexao = ConexaoBanco.getConnection(); 
            String sql3 = "select * from ordemCompra where situacao = 1";
            PreparedStatement ps3 = conexao.prepareStatement(sql3);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                int codO = rs3.getInt("codOrdemCompra");
                int codF = rs3.getInt("codFornecedor");
                int codCl = rs3.getInt("codCliente");
                int codTip = rs3.getInt("codTipoProduto");
                int quant = rs3.getInt("quantidade");
                float valorU = rs3.getFloat("valUnitario");
                int quantest = rs3.getInt("quantEstocada");
                int sit = rs3.getInt("situacao");
                
                listCodCliente.add(codCl);
                listCodFornecedor.add(codF);
                listCodTipoProduto.add(codTip);
                
                OrdemCompra ordemcompra = new OrdemCompra(codO, valorU, quant, codCl, codF, codTip, sit, quantest);
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
                model.addRow(new Object[]{ordemCompra.getCodOrdemCompra(), listClientes.get(cont), listFornecedor.get(cont), listProd.get(cont), ordemCompra.getQuantidade(), ordemCompra.getQuantestocada() , ordemCompra.getValUnitario(), "Em Andamento"});
                cont ++;
            }else{
                model.addRow(new Object[]{ordemCompra.getCodOrdemCompra(), listClientes.get(cont), listFornecedor.get(cont), listProd.get(cont), ordemCompra.getQuantidade(), ordemCompra.getQuantestocada() , ordemCompra.getValUnitario(), "Finalizada"});
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
        setTitle("Visualizar ordens de compras em andamento");

        TBOrdemDeCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Cliente", "Fornecedor", "Produto", "Quantidade", "Estoque", "Valor por unidade", "Situacação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TBOrdemDeCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBOrdemDeCompraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBOrdemDeCompra);
        if (TBOrdemDeCompra.getColumnModel().getColumnCount() > 0) {
            TBOrdemDeCompra.getColumnModel().getColumn(0).setResizable(false);
            TBOrdemDeCompra.getColumnModel().getColumn(1).setResizable(false);
            TBOrdemDeCompra.getColumnModel().getColumn(2).setResizable(false);
            TBOrdemDeCompra.getColumnModel().getColumn(3).setResizable(false);
            TBOrdemDeCompra.getColumnModel().getColumn(4).setResizable(false);
            TBOrdemDeCompra.getColumnModel().getColumn(5).setResizable(false);
            TBOrdemDeCompra.getColumnModel().getColumn(6).setResizable(false);
            TBOrdemDeCompra.getColumnModel().getColumn(7).setResizable(false);
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

        cadastrarButton.setText("Efetivar");
        cadastrarButton.setActionCommand("");
        cadastrarButton.setEnabled(false);
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
         int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo efetivar esta Ordem de Compra ?", "Confirmação", JOptionPane.OK_CANCEL_OPTION);

        if (option == 0) {
            OrdemCompra axl = new OrdemCompra();
            DefaultTableModel model = (DefaultTableModel) TBOrdemDeCompra.getModel();
            
            int codC = 0;
            int codF = 0;
            int codP = 0;
            
            String nomeCliente = (String) model.getValueAt(TBOrdemDeCompra.getSelectedRow(), 1);
            String nomeFornecedor = (String) model.getValueAt(TBOrdemDeCompra.getSelectedRow(), 2);
            String nomeProduto = (String) model.getValueAt(TBOrdemDeCompra.getSelectedRow(), 3);
            
            int cont = 0;
            for (String listCliente : listClientes) {
                if(listCliente.equals(nomeCliente)){
                    codC = listCodCliente.get(cont);
                }
                cont ++;
            }
            cont = 0;
            
            for (String string : listFornecedor) {
                if(string.equals(nomeFornecedor)){
                    codF = listCodFornecedor.get(cont);
                }
                cont++;
            }
            cont = 0;
            
            for (String string : listProd) {
                if(string.equals(nomeProduto)){
                    codP = listCodTipoProduto.get(cont);
                }
                cont++;
            }
            
            axl.setCodOrdemCompra((Integer) model.getValueAt(TBOrdemDeCompra.getSelectedRow(), 0));
            axl.setCodCliente(codC);
            axl.setCodFornecedor(codF);
            axl.setCodTipoProduto(codP);
            axl.setQuantidade((Integer) model.getValueAt(TBOrdemDeCompra.getSelectedRow(), 4));
            axl.setQuantestocada((Integer) model.getValueAt(TBOrdemDeCompra.getSelectedRow(), 5));
            axl.setValUnitario((Float) model.getValueAt(TBOrdemDeCompra.getSelectedRow(), 6));
            
            if ((String) model.getValueAt(TBOrdemDeCompra.getSelectedRow(), 7) == "Em Andamento") {
                axl.setSituacao(1);
            } else {
                axl.setSituacao(0);
            }
            
            Efetivacao_OrdemCompra eo = new Efetivacao_OrdemCompra();
            dispose();
            eo.setVisible(true);
            eo.auxiliar(axl);
        }
    }//GEN-LAST:event_cadastrarButtonActionPerformed

    private void TBOrdemDeCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBOrdemDeCompraMouseClicked
        if (TBOrdemDeCompra.getSelectedRow() >  -1){
            cadastrarButton.setEnabled(true);
            
        }
    }//GEN-LAST:event_TBOrdemDeCompraMouseClicked

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
            java.util.logging.Logger.getLogger(EfetivarOrdemDeCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EfetivarOrdemDeCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EfetivarOrdemDeCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EfetivarOrdemDeCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EfetivarOrdemDeCompra().setVisible(true);
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
