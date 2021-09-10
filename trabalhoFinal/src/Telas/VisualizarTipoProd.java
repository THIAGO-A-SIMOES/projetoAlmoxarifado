package Telas;

import Classes.Categoria;
import Classes.TipoProduto;
import DatabaseConnection.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VisualizarTipoProd extends javax.swing.JFrame {

    public List<Categoria> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Categoria> descriptions) {
        this.descriptions = descriptions;
    }

    public List<TipoProduto> getListtipoprod() {
        return listtipoprod;
    }

    public void setListtipoprod(List<TipoProduto> listtipoprod) {
        this.listtipoprod = listtipoprod;
    }

    private List<Categoria> descriptions;
    private List<TipoProduto> listtipoprod;

    public VisualizarTipoProd() {
        initComponents();
        listtipoprod = new ArrayList<>();
        descriptions = new ArrayList<>();
        preencherdados();
    }

    private void preencherdados() {

        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select * from tipoproduto";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codt = rs.getInt("codTipoProduto");
                String desc = rs.getString("descricao");
                String upc = rs.getString("UPC");
                int qtde = rs.getInt("qtdeMaxRecpt");
                int sit = rs.getInt("situacao");
                int codcat = rs.getInt("codCategoria");
                TipoProduto tipoprod = new TipoProduto(codt, desc, qtde, upc, codcat, sit);
                getListtipoprod().add(tipoprod);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql2 = "select * from categoria ";
            PreparedStatement ps2 = conexao.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                int codc = rs2.getInt("codCategoria");
                String desc = rs2.getString("descricao");
                int status = rs2.getInt("situacao");
                Categoria ca = new Categoria(codc, desc, status);
                getDescriptions().add(ca);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int cont = 0;
        DefaultTableModel model = (DefaultTableModel) tbtipoproduto.getModel();
        for (TipoProduto tipoprod : listtipoprod) {
            for (Categoria description : descriptions) {
                if (tipoprod.getCodCategoria() == description.getCodCategoria()) {

                    if (tipoprod.getSituacao() == 1) {
                        model.addRow(new Object[]{tipoprod.getCodTipoProduto(), tipoprod.getDescricao(), tipoprod.getQtdeMaxRecept(), tipoprod.getUPC(), description.getDescricao(), "Ativado"});
                    } else {
                        model.addRow(new Object[]{tipoprod.getCodTipoProduto(), tipoprod.getDescricao(), tipoprod.getQtdeMaxRecept(), tipoprod.getUPC(), description.getDescricao(), "Desativado"});
                    }
                }

            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pprincipal = new javax.swing.JPanel();
        sptabela = new javax.swing.JScrollPane();
        tbtipoproduto = new javax.swing.JTable();
        pbotoes = new javax.swing.JPanel();
        jbcadastrar = new javax.swing.JButton();
        jbalterar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualizar Tipo de Produto");

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
        if (tbtipoproduto.getColumnModel().getColumnCount() > 0) {
            tbtipoproduto.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbtipoproduto.getColumnModel().getColumn(1).setPreferredWidth(175);
            tbtipoproduto.getColumnModel().getColumn(2).setPreferredWidth(110);
            tbtipoproduto.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbtipoproduto.getColumnModel().getColumn(4).setPreferredWidth(100);
            tbtipoproduto.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pprincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pbotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pprincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        Cadastro_TipoProd ctp = new Cadastro_TipoProd();
        ctp.setVisible(true);
        dispose();

    }//GEN-LAST:event_jbcadastrarActionPerformed

    private void jbalterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbalterarActionPerformed
        
        
        
        int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo alterar o item selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option == 0) {

            TipoProduto ax = new TipoProduto();
            DefaultTableModel model = (DefaultTableModel) tbtipoproduto.getModel();
            ax.setCodTipoProduto((int) model.getValueAt(tbtipoproduto.getSelectedRow(), 0));
            ax.setDescricao((String) model.getValueAt(tbtipoproduto.getSelectedRow(), 1));
            ax.setQtdeMaxRecept((int) model.getValueAt(tbtipoproduto.getSelectedRow(), 2));
            ax.setUPC((String) model.getValueAt(tbtipoproduto.getSelectedRow(), 3));
            if ((String) model.getValueAt(tbtipoproduto.getSelectedRow(), 5) == "Ativado") {
                ax.setSituacao(1);

            } else {

                ax.setSituacao(0);
            }
            int cod = 0;
            String cate = (String) model.getValueAt(tbtipoproduto.getSelectedRow(), 4);
            for (Categoria description : descriptions) {
                if (cate == description.getDescricao()) {

                    cod = description.getCodCategoria();
                }
            }

            ax.setCodCategoria(cod);
            Alterar_Tipoprod atp = new Alterar_Tipoprod();
            dispose();
            atp.ax(ax);
            atp.setVisible(true);

        }


    }//GEN-LAST:event_jbalterarActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisualizarTipoProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizarTipoProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizarTipoProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizarTipoProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizarTipoProd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbalterar;
    private javax.swing.JButton jbcadastrar;
    private javax.swing.JPanel pbotoes;
    private javax.swing.JPanel pprincipal;
    private javax.swing.JScrollPane sptabela;
    private javax.swing.JTable tbtipoproduto;
    // End of variables declaration//GEN-END:variables
}
