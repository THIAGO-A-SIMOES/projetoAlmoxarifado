package Telas;

import Classes.Categoria;
import DatabaseConnection.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VizualizarCategorias extends javax.swing.JFrame {

    public List<Integer> getSituacoes() {
        return situacoes;
    }

    public void setSituacoes(List<Integer> situacoes) {
        this.situacoes = situacoes;
    }

    public List< Integer> getCodigos() {
        return codigos;
    }

    public void setCodigos(List< Integer> codigos) {
        this.codigos = codigos;
    }

    public List<Categoria> getListcategoria() {
        return listcategoria;
    }

    public void setListcategoria(List<Categoria> listcategoria) {
        this.listcategoria = listcategoria;
    }

    private List<Categoria> listcategoria;
    private List< Integer> codigos;
    private List<Integer> situacoes;

    public VizualizarCategorias() {
        initComponents();
        listcategoria = new ArrayList<>();
        codigos = new ArrayList<>();
        situacoes = new ArrayList<>();
        preencherdados();

    }

    private void preencherdados() {

        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select * from categoria";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codc = rs.getInt("codCategoria");
                String desc = rs.getString("descricao");
                int sit = rs.getInt("situacao");
                Categoria categoria = new Categoria(codc, desc, sit);
                getListcategoria().add(categoria);

            }

            String sql2 = "select c.codCategoria, t.situacao from tipoProduto t inner join categoria  c on (c.codCategoria = t.codCategoria)";
            PreparedStatement ps2 = conexao.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {

                int codcat = rs2.getInt("codCategoria");
                int situacao = rs2.getInt("situacao");
                getSituacoes().add(situacao);
                getCodigos().add(codcat);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tbcategoria.getModel();
        for (Categoria categoria : listcategoria) {
            if (categoria.getSituacao() == 1) {

                model.addRow(new Object[]{categoria.getCodCategoria(), categoria.getDescricao(), "Ativada"});

            } else {
                model.addRow(new Object[]{categoria.getCodCategoria(), categoria.getDescricao(), "Desativada"});

            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ptabelacategoria = new javax.swing.JPanel();
        sctabelacategoria = new javax.swing.JScrollPane();
        tbcategoria = new javax.swing.JTable();
        pbotoes = new javax.swing.JPanel();
        jbcadastrar = new javax.swing.JButton();
        jbalterar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualizar Categorias");

        tbcategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codCategoria", "descricao", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbcategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbcategoriaMouseClicked(evt);
            }
        });
        sctabelacategoria.setViewportView(tbcategoria);
        if (tbcategoria.getColumnModel().getColumnCount() > 0) {
            tbcategoria.getColumnModel().getColumn(0).setPreferredWidth(75);
            tbcategoria.getColumnModel().getColumn(1).setPreferredWidth(250);
            tbcategoria.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        javax.swing.GroupLayout ptabelacategoriaLayout = new javax.swing.GroupLayout(ptabelacategoria);
        ptabelacategoria.setLayout(ptabelacategoriaLayout);
        ptabelacategoriaLayout.setHorizontalGroup(
            ptabelacategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sctabelacategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        ptabelacategoriaLayout.setVerticalGroup(
            ptabelacategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sctabelacategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jbcadastrar.setText("Cadastrar");
        jbcadastrar.setToolTipText("");
        jbcadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcadastrarActionPerformed(evt);
            }
        });

        jbalterar.setText("Alterar");
        jbalterar.setToolTipText("");
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
                .addContainerGap()
                .addGroup(pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbalterar)
                    .addComponent(jbcadastrar))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ptabelacategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pbotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ptabelacategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbalterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbalterarActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbcategoria.getModel();
        boolean verifica;
        boolean verificasituacao;
        verifica = codigos.contains((int) model.getValueAt(tbcategoria.getSelectedRow(), 0));
        verificasituacao = situacoes.contains(1);

        if (verifica == true && verificasituacao == true) {

            JOptionPane.showMessageDialog(this, "Não é possível alterar o item selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo alerar o item selecionado?", "Confirmação", JOptionPane.OK_CANCEL_OPTION);
            if (option == 0) {

                Categoria ax = new Categoria();

                ax.setCodCategoria((int) model.getValueAt(tbcategoria.getSelectedRow(), 0));
                ax.setDescricao((String) model.getValueAt(tbcategoria.getSelectedRow(), 1));

                if ("Ativada".equals((String) model.getValueAt(tbcategoria.getSelectedRow(), 2))) {
                    ax.setSituacao(1);
                } else {
                    ax.setSituacao(0);
                }

                Alterar_Categoria ac = new Alterar_Categoria();
                dispose();
                ac.ax(ax);
                ac.setVisible(true);

            }

        }


    }//GEN-LAST:event_jbalterarActionPerformed

    private void tbcategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbcategoriaMouseClicked
        if (tbcategoria.getSelectedRow() > -1) {
            jbalterar.setEnabled(true);

        }

    }//GEN-LAST:event_tbcategoriaMouseClicked

    private void jbcadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcadastrarActionPerformed

        Cadastro_Categoria cc = new Cadastro_Categoria();
        cc.setVisible(true);
        dispose();

    }//GEN-LAST:event_jbcadastrarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VizualizarCategorias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbalterar;
    private javax.swing.JButton jbcadastrar;
    private javax.swing.JPanel pbotoes;
    private javax.swing.JPanel ptabelacategoria;
    private javax.swing.JScrollPane sctabelacategoria;
    private javax.swing.JTable tbcategoria;
    // End of variables declaration//GEN-END:variables
}
