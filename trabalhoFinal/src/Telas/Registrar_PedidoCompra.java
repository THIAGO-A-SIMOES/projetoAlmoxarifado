/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Classes.OrdemCompra;
import Classes.PedidoCompra;
import Classes.RelOrdemPedido;
import DatabaseConnection.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Registrar_PedidoCompra extends javax.swing.JFrame {

    public List<RelOrdemPedido> getListordemped() {
        return listordemped;
    }

   
    public void setListordemped(List<RelOrdemPedido> listordemped) {
        this.listordemped = listordemped;
    }

    
    public int getCount() {
        return count;
    }

  
    public void setCount(int count) {
        this.count = count;
    }

    
    public List<PedidoCompra> getListped() {
        return listped;
    }

    
    public void setListped(List<PedidoCompra> listped) {
        this.listped = listped;
    }

    public List<OrdemCompra> getListOrdemC() {
        return listOrdemC;
    }

    public void setListOrdemC(List<OrdemCompra> listOrdemC) {
        this.listOrdemC = listOrdemC;
    }

    public List<String> getListClientes() {
        return listClientes;
    }

    public void setListClientes(List<String> listClientes) {
        this.listClientes = listClientes;
    }

    public List<String> getListFornecedor() {
        return listFornecedor;
    }

    public void setListFornecedor(List<String> listFornecedor) {
        this.listFornecedor = listFornecedor;
    }

    public List<String> getListProd() {
        return listProd;
    }

    public void setListProd(List<String> listProd) {
        this.listProd = listProd;
    }

    public Registrar_PedidoCompra() {
        initComponents();
        listOrdemC = new ArrayList<>();
        listClientes = new ArrayList<>();
        listFornecedor = new ArrayList<>();
        listProd = new ArrayList<>();
        listped = new ArrayList<>();
        listordemped = new ArrayList<>();
        preencherordemcompra();

    }
    private List<OrdemCompra> listOrdemC;
    private List<String> listClientes;
    private List<String> listFornecedor;
    private List<String> listProd;
    private List<PedidoCompra> listped;
    private int count;
    private List<RelOrdemPedido> listordemped;

    private boolean validardados() {
        boolean validar = true;
        if (TBOrdemDeCompra.getRowCount() == 0) {
            validar = false;

        }

        if (!validar) {
            JOptionPane.showMessageDialog(this, "Favor adicionar uma ordem de compra!", "Erro", JOptionPane.ERROR_MESSAGE);

        }

        return validar;

    }
    
    private void update (){
        
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql3 = "select * from PedidoCompra where situacao = 1";
            PreparedStatement ps3 = conexao.prepareStatement(sql3);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                int codp = rs3.getInt("codPedidoCompra");
                float val = rs3.getFloat("valTotal");
                int status = rs3.getInt("situacao");
                PedidoCompra pc = new PedidoCompra (codp, val, status);
                getListped().add(pc);
            
            }

        } catch (ClassNotFoundException | SQLException ex) {
           JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
         try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql4 = "select count(*) as quantidade from PedidoCompra where situacao = 1";
            PreparedStatement ps4 = conexao.prepareStatement(sql4);
            ResultSet rs4 = ps4.executeQuery();
            while (rs4.next()) {
                setCount(rs4.getInt("quantidade"));
       
              
            
            }

        } catch (ClassNotFoundException | SQLException ex) {
            //JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        
    }
    
    private void preencherordemcompra() {
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql3 = "select * from ordemCompra where codOrdemCompra not in (select codOrdemCompra from relOrdemPedido) and situacao = 1";
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
                int quante = rs3.getInt("quantEstocada");
                OrdemCompra ordemcompra = new OrdemCompra(codO, valorU, quant, codCl, codF, codTip, sit, quante);
                getListOrdemC().add(ordemcompra);

                String sql4 = "select nome from cliente where codCliente = " + String.valueOf(codCl);
                PreparedStatement ps4 = conexao.prepareStatement(sql4);
                ResultSet rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    String nomeC = rs4.getString("nome");
                    getListClientes().add(nomeC);
                }

                String sql5 = "select nome from fornecedor where codFornecedor = " + String.valueOf(codF);
                PreparedStatement ps5 = conexao.prepareStatement(sql5);
                ResultSet rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    String nomeF = rs5.getString("nome");
                    getListFornecedor().add(nomeF);
                }

                String sql6 = "select descricao from tipoproduto where codTipoProduto = " + String.valueOf(codTip);
                PreparedStatement ps6 = conexao.prepareStatement(sql6);
                ResultSet rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    String nomeP = rs6.getString("descricao");
                    getListProd().add(nomeP);
                }
            }
            
         
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (OrdemCompra ordemCompra : listOrdemC) {
            cbcodordemcompra.addItem((ordemCompra.getCodOrdemCompra()));
        }
    }
    
    
    

    private void registro() {
        float valtotal = 0;
        DefaultTableModel model = (DefaultTableModel) TBOrdemDeCompra.getModel();

        for (int i = 0; i < TBOrdemDeCompra.getRowCount(); i++) {

            valtotal += (float) model.getValueAt(i, 5) * (int) model.getValueAt(i, 4);

        }

        String sql = "insert into pedidoCompra (valTotal, situacao)";
        sql += "values (?, ?)";

        try {
            Connection conn = ConexaoBanco.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, valtotal);
            ps.setInt(2, 1);
            ps.execute();

            JOptionPane.showMessageDialog(this, "Pedido de compra registrado com sucesso!", "Confirmação de cadastro", JOptionPane.INFORMATION_MESSAGE);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Registrar_PedidoCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        update();
        for (int i = 0; i < TBOrdemDeCompra.getRowCount(); i++) {
            String sql1 = "insert into relOrdemPedido (codOrdemCompra, codPedidoCompra)";
            sql1 += "values (?, ?)";

            try {
                
                Connection conn = ConexaoBanco.getConnection();
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setInt(1, (int) model.getValueAt(i, 0));
                ps1.setInt(2, getListped().get(getCount()-1).getCodPedidoCompra());
                ps1.execute();

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Registrar_PedidoCompra.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pprincipal = new javax.swing.JPanel();
        lcodordemcompra = new javax.swing.JLabel();
        cbcodordemcompra = new javax.swing.JComboBox<>();
        jbadicionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBOrdemDeCompra = new javax.swing.JTable();
        jbremover = new javax.swing.JButton();
        jbcadastrar = new javax.swing.JButton();
        jbcancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro Pedido de Compra");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pprincipal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Pedido de Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        lcodordemcompra.setText("Codigo da Ordem de Compra:  ");

        jbadicionar.setText("Adicionar");
        jbadicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbadicionarActionPerformed(evt);
            }
        });

        TBOrdemDeCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Cliente", "Fornecedor", "Produto", "Quantidade", "Valor por unidade", "Situacação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
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

        jbremover.setText("Remover");
        jbremover.setEnabled(false);
        jbremover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbremoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pprincipalLayout = new javax.swing.GroupLayout(pprincipal);
        pprincipal.setLayout(pprincipalLayout);
        pprincipalLayout.setHorizontalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
            .addGroup(pprincipalLayout.createSequentialGroup()
                .addComponent(lcodordemcompra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbcodordemcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbadicionar)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pprincipalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbremover))
        );
        pprincipalLayout.setVerticalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pprincipalLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcodordemcompra)
                    .addComponent(cbcodordemcompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbadicionar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbremover, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbcadastrar.setText("Registrar");
        jbcadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcadastrarActionPerformed(evt);
            }
        });

        jbcancelar.setText("Cancelar");
        jbcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pprincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbcadastrar)
                .addGap(19, 19, 19)
                .addComponent(jbcancelar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pprincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbcadastrar)
                    .addComponent(jbcancelar))
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        VisualizarPedidoCompra vpc = new VisualizarPedidoCompra();
        vpc.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void jbadicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbadicionarActionPerformed

        if (cbcodordemcompra.getSelectedIndex() > -1) {
            DefaultTableModel model = (DefaultTableModel) TBOrdemDeCompra.getModel();

            int codigo = (int) cbcodordemcompra.getSelectedItem();
            boolean verifica = false;
            for (int i = 0; i < TBOrdemDeCompra.getRowCount(); i++) {
                if (codigo == (int) model.getValueAt(i, 0)) {
                    verifica = true;

                }
            }
            if (!verifica) {

                int cont = 0;
                for (OrdemCompra ordemCompra : listOrdemC) {
                    if (ordemCompra.getCodOrdemCompra() == codigo) {

                        if (ordemCompra.getSituacao() == 1) {
                            model.addRow(new Object[]{ordemCompra.getCodOrdemCompra(), getListClientes().get(cont), getListFornecedor().get(cont), getListProd().get(cont), ordemCompra.getQuantidade(), ordemCompra.getValUnitario(), "Em Andamento"});

                        } else {
                            model.addRow(new Object[]{ordemCompra.getCodOrdemCompra(), getListClientes().get(cont), getListFornecedor().get(cont), getListProd().get(cont), ordemCompra.getQuantidade(), ordemCompra.getValUnitario(), "Finalizada"});

                        }

                    }

                    cont++;
                }

            } else {
                JOptionPane.showMessageDialog(this, "Esta ordem já foi adicionada ao pedido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }


    }//GEN-LAST:event_jbadicionarActionPerformed

    private void TBOrdemDeCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBOrdemDeCompraMouseClicked
        if (TBOrdemDeCompra.getSelectedRow() > -1) {

            jbremover.setEnabled(true);
        }
    }//GEN-LAST:event_TBOrdemDeCompraMouseClicked

    private void jbcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcancelarActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair da tela de cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            dispose();

        }
    }//GEN-LAST:event_jbcancelarActionPerformed

    private void jbremoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbremoverActionPerformed

        if (TBOrdemDeCompra.getSelectedRow() > -1) {
            int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo remover o item selecionado da tabela?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                DefaultTableModel model = (DefaultTableModel) TBOrdemDeCompra.getModel();
                model.removeRow(TBOrdemDeCompra.getSelectedRow());
                jbremover.setEnabled(false);

            }

        }


    }//GEN-LAST:event_jbremoverActionPerformed

    private void jbcadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcadastrarActionPerformed
        if (validardados()){
            
            registro();
            dispose();
            
        }
    }//GEN-LAST:event_jbcadastrarActionPerformed

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
            java.util.logging.Logger.getLogger(Registrar_PedidoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar_PedidoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar_PedidoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar_PedidoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrar_PedidoCompra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TBOrdemDeCompra;
    private javax.swing.JComboBox<Integer> cbcodordemcompra;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbadicionar;
    private javax.swing.JButton jbcadastrar;
    private javax.swing.JButton jbcancelar;
    private javax.swing.JButton jbremover;
    private javax.swing.JLabel lcodordemcompra;
    private javax.swing.JPanel pprincipal;
    // End of variables declaration//GEN-END:variables
}
