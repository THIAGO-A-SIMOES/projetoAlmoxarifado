package Telas;

import Classes.TipoProduto;
import Classes.Cliente;
import Classes.PedidoCliente;
import DatabaseConnection.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EfetivarPedidoCliente extends javax.swing.JFrame {

    public List<Cliente> getListcliente() {
        return listcliente;
    }

    public void setListcliente(List<Cliente> listcliente) {
        this.listcliente = listcliente;
    }

    public List<PedidoCliente> getListpedidocliente() {
        return listpedidocliente;
    }

    public void setListpedidocliente(List<PedidoCliente> listpedidocliente) {
        this.listpedidocliente = listpedidocliente;
    }

    public List<TipoProduto> getListtipoproduto() {
        return listtipoproduto;
    }

    public void setListtipoproduto(List<TipoProduto> listtipoproduto) {
        this.listtipoproduto = listtipoproduto;
    }

    public EfetivarPedidoCliente() {
        initComponents();
        listtipoproduto = new ArrayList<>();
        listcliente = new ArrayList<>();
        listpedidocliente = new ArrayList<>();
        preencherdados();
    }

    protected List<TipoProduto> listtipoproduto;
    protected List<Cliente> listcliente;
    protected List<PedidoCliente> listpedidocliente;

    private void preencherdados() {
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select * from cliente";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codc = rs.getInt("codCliente");
                int codcid = rs.getInt("codCidade");
                String cnpj = rs.getString("cnpj");
                String nome = rs.getString("nome");
                String contato = rs.getString("contato");
                String endereco = rs.getString("endereco");
                int sit = rs.getInt("situacao");
                Cliente cliente = new Cliente(codc, codcid, cnpj, nome, contato, endereco, sit);
                getListcliente().add(cliente);
            }

            String sql1 = "select * from tipoProduto";
            PreparedStatement ps1 = conexao.prepareStatement(sql1);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                int codTipoProduto = rs1.getInt("codTipoProduto");
                String descricao = rs1.getString("descricao");
                int qtdeMaxRecept = rs1.getInt("qtdeMaxRecpt");
                String UPC = rs1.getString("UPC");
                int codCategoria = rs1.getInt("codCategoria");
                int situacao = rs1.getInt("situacao");
                TipoProduto tipoprod = new TipoProduto(codTipoProduto, descricao, qtdeMaxRecept, UPC, codCategoria, situacao);
                getListtipoproduto().add(tipoprod);
            }

            String sql2 = "select * from pedidoCliente  where situacao = 1";
            PreparedStatement ps2 = conexao.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                int codPedidoCliente = rs2.getInt("codPedidoCliente");
                int codTipoProduto = rs2.getInt("codTipoProduto");
                int codCliente = rs2.getInt("codCliente");
                int quantidade = rs2.getInt("quantidade");
                int situacao = rs2.getInt("situacao");
                PedidoCliente pedCli = new PedidoCliente(codPedidoCliente, codTipoProduto, codCliente, quantidade, situacao);
                getListpedidocliente().add(pedCli);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tbpedidocliente.getModel();
        for (PedidoCliente pedCliente : listpedidocliente) {
            for (Cliente cliente : listcliente) {
                for (TipoProduto tipoProduto : listtipoproduto) {
                    if (cliente.getCodCliente() == pedCliente.getCodCliente() && tipoProduto.getCodTipoProduto() == pedCliente.getCodTipoProduto()) {
                        if (pedCliente.getSituacao() == 1) {
                            model.addRow(new Object[]{pedCliente.getCodPedidoCliente(), cliente.getNome(), cliente.getCnpj(), tipoProduto.getDescricao(), pedCliente.getQuantidade(), "Em Andamento"});

                        } else {
                            model.addRow(new Object[]{pedCliente.getCodPedidoCliente(), cliente.getNome(), cliente.getCnpj(), tipoProduto.getDescricao(), pedCliente.getQuantidade(), "Finalizado"});

                        }
                    }
                }
            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        pprincipal = new javax.swing.JPanel();
        sptabela = new javax.swing.JScrollPane();
        tbtipoproduto = new javax.swing.JTable();
        pbotoes = new javax.swing.JPanel();
        jbcadastrar = new javax.swing.JButton();
        jbalterar = new javax.swing.JButton();
        jbcadastrar1 = new javax.swing.JButton();
        sptabela1 = new javax.swing.JScrollPane();
        tbtipoproduto1 = new javax.swing.JTable();
        jbcadastrar2 = new javax.swing.JButton();
        sptabela2 = new javax.swing.JScrollPane();
        tbpedidocliente = new javax.swing.JTable();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tbtipoproduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codTipoProduto", "descricao", "qtdeMaxRecpt", "UPC", "Categoria", "situacao"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbtipoproduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtipoprodutoMouseClicked(evt);
            }
        });
        sptabela.setViewportView(tbtipoproduto);

        javax.swing.GroupLayout pprincipalLayout = new javax.swing.GroupLayout(pprincipal);
        pprincipal.setLayout(pprincipalLayout);
        pprincipalLayout.setHorizontalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sptabela, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        pprincipalLayout.setVerticalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sptabela, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jbcadastrar.setText("Cadastrar");
        jbcadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcadastrarActionPerformed(evt);
            }
        });

        jbalterar.setText("Alterar");
        jbalterar.setEnabled(false);
        jbalterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbalterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pbotoesLayout = new javax.swing.GroupLayout(pbotoes);
        pbotoes.setLayout(pbotoesLayout);
        pbotoesLayout.setHorizontalGroup(
            pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pbotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbcadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbalterar)
                .addContainerGap())
        );
        pbotoesLayout.setVerticalGroup(
            pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pbotoesLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbcadastrar)
                    .addComponent(jbalterar))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pprincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pbotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addComponent(pprincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jbcadastrar1.setText("Cadastrar");
        jbcadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcadastrar1ActionPerformed(evt);
            }
        });

        tbtipoproduto1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codTipoProduto", "descricao", "qtdeMaxRecpt", "UPC", "Categoria", "situacao"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbtipoproduto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtipoproduto1MouseClicked(evt);
            }
        });
        sptabela1.setViewportView(tbtipoproduto1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualizar Pedido do Cliente");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jbcadastrar2.setText("Efetivar");
        jbcadastrar2.setEnabled(false);
        jbcadastrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcadastrar2ActionPerformed(evt);
            }
        });

        tbpedidocliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cliente", "CNPJ", "Produto", "Quantidade", "Situação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbpedidocliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpedidoclienteMouseClicked(evt);
            }
        });
        sptabela2.setViewportView(tbpedidocliente);
        if (tbpedidocliente.getColumnModel().getColumnCount() > 0) {
            tbpedidocliente.getColumnModel().getColumn(0).setPreferredWidth(130);
            tbpedidocliente.getColumnModel().getColumn(1).setPreferredWidth(240);
            tbpedidocliente.getColumnModel().getColumn(2).setPreferredWidth(240);
            tbpedidocliente.getColumnModel().getColumn(3).setPreferredWidth(240);
            tbpedidocliente.getColumnModel().getColumn(4).setPreferredWidth(150);
            tbpedidocliente.getColumnModel().getColumn(5).setPreferredWidth(200);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sptabela2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbcadastrar2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sptabela2, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jbcadastrar2)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbtipoprodutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbtipoprodutoMouseClicked
        if (tbtipoproduto.getSelectedRow() > -1) {
            jbalterar.setEnabled(true);

        }
    }//GEN-LAST:event_tbtipoprodutoMouseClicked

    private void jbcadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcadastrarActionPerformed

    }//GEN-LAST:event_jbcadastrarActionPerformed

    private void jbalterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbalterarActionPerformed

    }//GEN-LAST:event_jbalterarActionPerformed

    private void tbtipoproduto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbtipoproduto1MouseClicked

    }//GEN-LAST:event_tbtipoproduto1MouseClicked

    private void jbcadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcadastrar1ActionPerformed

    }//GEN-LAST:event_jbcadastrar1ActionPerformed

    private void jbcadastrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcadastrar2ActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo efetivar o pedido selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option == 0) {

            DefaultTableModel model = (DefaultTableModel) tbpedidocliente.getModel();
            PedidoCliente pedc = new PedidoCliente();
            pedc.setCodPedidoCliente((int) model.getValueAt(tbpedidocliente.getSelectedRow(), 0));

            int codigo = 0;

            for (Cliente cliente : listcliente) {
                if ((String) model.getValueAt(tbpedidocliente.getSelectedRow(), 1) == cliente.getNome()) {

                    codigo = cliente.getCodCliente();

                }
            }

            pedc.setCodCliente(codigo);

            int codigop = 0;
            for (TipoProduto tipoProduto : listtipoproduto) {
                if ((String) model.getValueAt(tbpedidocliente.getSelectedRow(), 3) == tipoProduto.getDescricao()) {

                    codigop = tipoProduto.getCodTipoProduto();

                }
            }
            
            pedc.setCodTipoProduto(codigop);
            pedc.setQuantidade((int) model.getValueAt(tbpedidocliente.getSelectedRow(), 4));
            pedc.setSituacao(1);
            Efetivacao_PedidoCliente epc = new Efetivacao_PedidoCliente ();
            epc.ax(pedc);
            epc.setVisible(true);
            dispose();
        }

    }//GEN-LAST:event_jbcadastrar2ActionPerformed

    private void tbpedidoclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpedidoclienteMouseClicked
        if (tbpedidocliente.getSelectedRow() > -1){
            
            jbcadastrar2.setEnabled(true);
        }
    }//GEN-LAST:event_tbpedidoclienteMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

        if (tbpedidocliente.getSelectedRow() > -1) {

            jbcadastrar2.setEnabled(true);
        }

    }//GEN-LAST:event_formMouseClicked

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
            java.util.logging.Logger.getLogger(EfetivarPedidoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EfetivarPedidoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EfetivarPedidoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EfetivarPedidoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EfetivarPedidoCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame jFrame1;
    private javax.swing.JButton jbalterar;
    private javax.swing.JButton jbcadastrar;
    private javax.swing.JButton jbcadastrar1;
    private javax.swing.JButton jbcadastrar2;
    private javax.swing.JPanel pbotoes;
    private javax.swing.JPanel pprincipal;
    private javax.swing.JScrollPane sptabela;
    private javax.swing.JScrollPane sptabela1;
    private javax.swing.JScrollPane sptabela2;
    private javax.swing.JTable tbpedidocliente;
    private javax.swing.JTable tbtipoproduto;
    private javax.swing.JTable tbtipoproduto1;
    // End of variables declaration//GEN-END:variables
}
