package Telas;

import Classes.Alojamento;
import Classes.Cliente;
import DatabaseConnection.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VisualizarAlojamento extends javax.swing.JFrame {

    public List<Cliente> getListclientes() {
        return listclientes;
    }

    public void setListclientes(List<Cliente> listclientes) {
        this.listclientes = listclientes;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public List<Alojamento> getListalojamento() {
        return listalojamento;
    }

    public void setListalojamento(List<Alojamento> listalojamento) {
        this.listalojamento = listalojamento;
    }

    public VisualizarAlojamento() {
        initComponents();
        listalojamento = new ArrayList<>();
        descriptions = new ArrayList<>();
        listclientes = new ArrayList<>();
        preencherdados();

    }

    private List<Alojamento> listalojamento;
    private List<String> descriptions;
    private List<Cliente> listclientes;

    private void preencherdados() {

        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select * from alojamento order by codAlojamento";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int coda = rs.getInt("codAlojamento");
                int codc = rs.getInt("codCliente");
                Alojamento alojamento = new Alojamento(coda, codc);
                getListalojamento().add(alojamento);

                String sql1 = "select nome from cliente where codCliente = " + codc;
                PreparedStatement ps1 = conexao.prepareStatement(sql1);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    String desc = rs1.getString("nome");
                    getDescriptions().add(desc);
                }

            }

            String sql2 = "select * from cliente";
            PreparedStatement ps2 = conexao.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                int codc = rs2.getInt("codCliente");
                int codcl = rs2.getInt("codCidade");
                String cnpj = rs2.getString("cnpj");
                String nome = rs2.getString("nome");
                String contato = rs2.getString("contato");
                String endereco = rs2.getString("endereco");
                int status = rs2.getInt("situacao");
                Cliente cliente = new Cliente(codc, codcl, cnpj, nome, contato, endereco, status);
                getListclientes().add(cliente);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int cont = 0;
        DefaultTableModel model = (DefaultTableModel) tbalojamento.getModel();
        for (Alojamento alojamento : listalojamento) {
            if (alojamento.getCodCliente() > 0) {
                model.addRow(new Object[]{alojamento.getCodAlojamento(), descriptions.get(cont)});
                cont++;
            } else {
                model.addRow(new Object[]{alojamento.getCodAlojamento(), null});

            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pprincipal = new javax.swing.JPanel();
        sptabela = new javax.swing.JScrollPane();
        tbalojamento = new javax.swing.JTable();
        pbotoes = new javax.swing.JPanel();
        jbvinculacao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualizar Alojamento");

        tbalojamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Cliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbalojamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbalojamentoMouseClicked(evt);
            }
        });
        sptabela.setViewportView(tbalojamento);
        if (tbalojamento.getColumnModel().getColumnCount() > 0) {
            tbalojamento.getColumnModel().getColumn(0).setPreferredWidth(110);
            tbalojamento.getColumnModel().getColumn(1).setPreferredWidth(170);
        }

        jbvinculacao.setText("Vincular");
        jbvinculacao.setToolTipText("");
        jbvinculacao.setEnabled(false);
        jbvinculacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbvinculacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pbotoesLayout = new javax.swing.GroupLayout(pbotoes);
        pbotoes.setLayout(pbotoesLayout);
        pbotoesLayout.setHorizontalGroup(
            pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pbotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbvinculacao)
                .addContainerGap())
        );
        pbotoesLayout.setVerticalGroup(
            pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pbotoesLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jbvinculacao)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout pprincipalLayout = new javax.swing.GroupLayout(pprincipal);
        pprincipal.setLayout(pprincipalLayout);
        pprincipalLayout.setHorizontalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sptabela, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addComponent(pbotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pprincipalLayout.setVerticalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pprincipalLayout.createSequentialGroup()
                .addComponent(sptabela, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pprincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pprincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbalojamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbalojamentoMouseClicked
        if (tbalojamento.getSelectedRow() > -1) {
            jbvinculacao.setEnabled(true);

        }
    }//GEN-LAST:event_tbalojamentoMouseClicked

    private void jbvinculacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbvinculacaoActionPerformed
        int verifica = 0;
        DefaultTableModel model = (DefaultTableModel) tbalojamento.getModel();

        if (model.getValueAt(tbalojamento.getSelectedRow(), 1) == null) {

        } else {
            String nome = (String) model.getValueAt(tbalojamento.getSelectedRow(), 1);
            for (Cliente listcliente : listclientes) {

                if (nome.equals(listcliente.getNome())) {
                    verifica = listcliente.getSituacao();

                }
            }

        }

        if (verifica == 1) {

            JOptionPane.showMessageDialog(this, "Este alojamento está vinculado a um cliente ativo!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {

            int ax = (int) model.getValueAt(tbalojamento.getSelectedRow(), 0);
            VinculacaoClienteAlojamento vca = new VinculacaoClienteAlojamento();
            vca.ax(ax);
            vca.setVisible(true);
            dispose();
        }


    }//GEN-LAST:event_jbvinculacaoActionPerformed

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
            java.util.logging.Logger.getLogger(VisualizarAlojamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizarAlojamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizarAlojamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizarAlojamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizarAlojamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbvinculacao;
    private javax.swing.JPanel pbotoes;
    private javax.swing.JPanel pprincipal;
    private javax.swing.JScrollPane sptabela;
    private javax.swing.JTable tbalojamento;
    // End of variables declaration//GEN-END:variables
}
