package Telas;

import DatabaseConnection.ConexaoBanco;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Tela_Valor_Gasto extends javax.swing.JFrame {

    public Tela_Valor_Gasto() {
        initComponents();
    }

    private void preencherDadosNome() throws SQLException {
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select e.sigla as Estado, cd.descricao as Cidade, c.codCliente as CódCliente, c.nome as Cliente, c.cnpj as CNPJ, (o.valUnitario) as Valor"
                    + " from cliente c"
                    + " join ordemcompra o on o.codCliente = c.codCliente"
                    + " join cidade cd on cd.codCidade = c.codCidade"
                    + " join estado e on e.codEstado = cd.codEstado"
                    + " join tipoproduto tp on tp.codTipoProduto = o.codTipoProduto"
                    + " join categoria ct on ct.codCategoria = tp.codCategoria where c.nome = '" + text_box.getText() + "' group by c.codCliente;";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String sigla = rs.getString("Estado");
                String cidade = rs.getString("Cidade");
                int codCidade = rs.getInt("CódCliente");
                String nome = rs.getString("Cliente");
                String cnpj = rs.getString("CNPJ");
                float valor = rs.getFloat("Valor");
                DefaultTableModel model = (DefaultTableModel) TB_Cliente.getModel();
                model.addRow(new Object[]{sigla, cidade, codCidade, nome, cnpj, valor});

            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private void preencherDadosCNPJ() {
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select e.sigla as Estado, cd.descricao as Cidade, c.codCliente as CódCliente, c.nome as Cliente, c.cnpj as CNPJ, (o.valUnitario) as Valor"
                    + " from cliente c"
                    + " join ordemcompra o on o.codCliente = c.codCliente"
                    + " join cidade cd on cd.codCidade = c.codCidade"
                    + " join estado e on e.codEstado = cd.codEstado"
                    + " join tipoproduto tp on tp.codTipoProduto = o.codTipoProduto"
                    + " join categoria ct on ct.codCategoria = tp.codCategoria  where (REPLACE(REPLACE(REPLACE( c.cnpj, '.', '' ), '-', '' ),'/','') like '%" + text_box.getText() + "%')  group by c.codCliente";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String sigla = rs.getString("Estado");
                String cidade = rs.getString("Cidade");
                int codCidade = rs.getInt("CódCliente");
                String nome = rs.getString("Cliente");
                String cnpj = rs.getString("CNPJ");
                float valor = rs.getFloat("Valor");
                DefaultTableModel model = (DefaultTableModel) TB_Cliente.getModel();
                model.addRow(new Object[]{sigla, cidade, codCidade, nome, cnpj, valor});

            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private void preencherDadosCodigo() {
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select e.sigla as Estado, cd.descricao as Cidade, c.codCliente as CódCliente, c.nome as Cliente, c.cnpj as CNPJ, (o.valUnitario) as Valor"
                    + " from cliente c"
                    + " join ordemcompra o on o.codCliente = c.codCliente"
                    + " join cidade cd on cd.codCidade = c.codCidade"
                    + " join estado e on e.codEstado = cd.codEstado"
                    + " join tipoproduto tp on tp.codTipoProduto = o.codTipoProduto"
                    + " join categoria ct on ct.codCategoria = tp.codCategoria where c.codCliente = " + text_box.getText() + " group by c.codCliente;";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String sigla = rs.getString("Estado");
                String cidade = rs.getString("Cidade");
                int codCidade = rs.getInt("CódCliente");
                String nome = rs.getString("Cliente");
                String cnpj = rs.getString("CNPJ");
                float valor = rs.getFloat("Valor");
                DefaultTableModel model = (DefaultTableModel) TB_Cliente.getModel();
                model.addRow(new Object[]{sigla, cidade, codCidade, nome, cnpj, valor});

            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pesquisa = new javax.swing.JPanel();
        combo_box_geral = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        select_label = new javax.swing.JLabel();
        text_box = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TB_Cliente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Valor gasto");

        pesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatorio de Cliente"));

        combo_box_geral.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "CNPJ", "Código do Cliente" }));
        combo_box_geral.setSelectedIndex(-1);
        combo_box_geral.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_box_geralItemStateChanged(evt);
            }
        });

        jLabel1.setText("Filtro:");

        select_label.setText("Pesquisa:");

        text_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_boxActionPerformed(evt);
            }
        });
        text_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                text_boxKeyPressed(evt);
            }
        });

        TB_Cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Estado", "Cidade", "Código", "Nome", "CNPJ", "Valor"
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
        jScrollPane1.setViewportView(TB_Cliente);
        if (TB_Cliente.getColumnModel().getColumnCount() > 0) {
            TB_Cliente.getColumnModel().getColumn(0).setResizable(false);
            TB_Cliente.getColumnModel().getColumn(1).setResizable(false);
            TB_Cliente.getColumnModel().getColumn(2).setResizable(false);
            TB_Cliente.getColumnModel().getColumn(3).setResizable(false);
            TB_Cliente.getColumnModel().getColumn(4).setResizable(false);
            TB_Cliente.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout pesquisaLayout = new javax.swing.GroupLayout(pesquisa);
        pesquisa.setLayout(pesquisaLayout);
        pesquisaLayout.setHorizontalGroup(
            pesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pesquisaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combo_box_geral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(select_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text_box, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(753, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        pesquisaLayout.setVerticalGroup(
            pesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pesquisaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_box_geral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(select_label)
                    .addComponent(text_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void combo_box_geralItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_box_geralItemStateChanged
        select_label.setText((String) combo_box_geral.getSelectedItem());
    }//GEN-LAST:event_combo_box_geralItemStateChanged

    private void text_boxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_boxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            DefaultTableModel model = (DefaultTableModel) TB_Cliente.getModel();
            model.setRowCount(0);

            if (combo_box_geral.getSelectedItem() == "Nome") {
                try {
                    preencherDadosNome();
                    if (TB_Cliente.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(this, "Não foram encontrados Clientes com esse nome!", "Confirmação", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Tela_Valor_Gasto.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (combo_box_geral.getSelectedItem() == "CNPJ") {
                preencherDadosCNPJ();
                if (TB_Cliente.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Não foram encontrados Clientes com esse CNPJ!", "Confirmação", JOptionPane.ERROR_MESSAGE);
                }
            } else if (combo_box_geral.getSelectedItem() == "Código do Cliente") {
                preencherDadosCodigo();
                if (TB_Cliente.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Não foram encontrados Clientes com esse Código de Cliente!", "Confirmação", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }//GEN-LAST:event_text_boxKeyPressed

    private void text_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_boxActionPerformed

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
            java.util.logging.Logger.getLogger(Tela_Valor_Gasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela_Valor_Gasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela_Valor_Gasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela_Valor_Gasto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela_Valor_Gasto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TB_Cliente;
    private javax.swing.JComboBox<String> combo_box_geral;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pesquisa;
    private javax.swing.JLabel select_label;
    private javax.swing.JTextField text_box;
    // End of variables declaration//GEN-END:variables
}
