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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class VinculacaoClienteAlojamento extends javax.swing.JFrame {

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<Cliente> getListcliente() {
        return listcliente;
    }

    public void setListcliente(List<Cliente> listcliente) {
        this.listcliente = listcliente;
    }

    public VinculacaoClienteAlojamento() {
        initComponents();
        listcliente = new ArrayList<>();

        preencherclientes();
    }

    private List<Cliente> listcliente;
    private int codigo;

    private void preencherclientes() {

        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select nome, codCliente from cliente where situacao = 1";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                int codc = rs.getInt("codCliente");
                Cliente cliente = new Cliente();
                cliente.setCodCliente(codc);
                cliente.setNome(nome);
                getListcliente().add(cliente);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Cliente cliente : listcliente) {
            cbcliente.addItem(cliente.getNome());
        }

    }

    private boolean validardados() {
        boolean validar = true;
        if (cbcliente.getSelectedIndex() < 0) {
            validar = false;

        }

        if (!validar) {

            JOptionPane.showMessageDialog(this, "Favor associar um cliente a este alojamento!", "Erro", JOptionPane.ERROR_MESSAGE);

        }

        return validar;
    }

    public void ax(int codalojamento) {

        setCodigo(codalojamento);

    }

    private void vincular() {

        int codc = 0;
        for (Cliente cliente : listcliente) {
            if (cliente.getNome().equals((String) cbcliente.getSelectedItem())) {
                {
                    codc = cliente.getCodCliente();

                }
            }
            String sql = "update alojamento set codCliente = ? where codAlojamento = " + getCodigo();

            try {

                Connection conn = ConexaoBanco.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, codc);
                ps.execute();

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(VinculacaoClienteAlojamento.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pprincipal = new javax.swing.JPanel();
        lcliente = new javax.swing.JLabel();
        cbcliente = new javax.swing.JComboBox<>();
        pbotoes = new javax.swing.JPanel();
        jbvincular = new javax.swing.JButton();
        jbcancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vinculação Cliente/Alojamento");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pprincipal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vinculação Cliente/Alojamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        lcliente.setText("Cliente: ");

        javax.swing.GroupLayout pprincipalLayout = new javax.swing.GroupLayout(pprincipal);
        pprincipal.setLayout(pprincipalLayout);
        pprincipalLayout.setHorizontalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pprincipalLayout.createSequentialGroup()
                .addComponent(lcliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 264, Short.MAX_VALUE))
        );
        pprincipalLayout.setVerticalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pprincipalLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcliente)
                    .addComponent(cbcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        jbvincular.setText("Vincular");
        jbvincular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbvincularActionPerformed(evt);
            }
        });

        jbcancelar.setText("Cancelar");
        jbcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pbotoesLayout = new javax.swing.GroupLayout(pbotoes);
        pbotoes.setLayout(pbotoesLayout);
        pbotoesLayout.setHorizontalGroup(
            pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pbotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbvincular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbcancelar)
                .addContainerGap())
        );
        pbotoesLayout.setVerticalGroup(
            pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pbotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbvincular)
                    .addComponent(jbcancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(pbotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        VisualizarAlojamento va = new VisualizarAlojamento();
        va.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void jbvincularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbvincularActionPerformed
        if (validardados()) {

            vincular();
            JOptionPane.showMessageDialog(this, "Vinculação realizada com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);

            dispose();

        }
    }//GEN-LAST:event_jbvincularActionPerformed

    private void jbcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcancelarActionPerformed
        int option2 = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair da tela de vinculação?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option2 == 0) {

            dispose();
        }

    }//GEN-LAST:event_jbcancelarActionPerformed

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
            java.util.logging.Logger.getLogger(VinculacaoClienteAlojamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VinculacaoClienteAlojamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VinculacaoClienteAlojamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VinculacaoClienteAlojamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VinculacaoClienteAlojamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbcliente;
    private javax.swing.JButton jbcancelar;
    private javax.swing.JButton jbvincular;
    private javax.swing.JLabel lcliente;
    private javax.swing.JPanel pbotoes;
    private javax.swing.JPanel pprincipal;
    // End of variables declaration//GEN-END:variables
}
