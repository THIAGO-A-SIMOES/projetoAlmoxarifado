package Telas;

import Classes.Categoria;
import Classes.Cidade;
import Classes.TipoProduto;
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

public class Cadastro_Cliente extends javax.swing.JFrame {

    public List<Cidade> getListcidade() {
        return listcidade;
    }

    public void setListcidade(List<Cidade> listcidade) {
        this.listcidade = listcidade;
    }

    public Cadastro_Cliente() {
        initComponents();
        listcidade = new ArrayList<>();
        preenchercidade();
    }

    private List<Cidade> listcidade;

    private boolean validardados() {
        boolean validar = true;
        if (tfnome.getText().isEmpty()) {
            validar = false;
        } else if (tfendereco.getText().isEmpty()) {
            validar = false;
        } else if (cbcidade.getSelectedIndex() < 0) {
            validar = false;
        } else if (ftfcontato.getText().equals("(  )    -    ")) {
            validar = false;
        } else if (tftcnpj.getText().equals("  .   .   /    -  ")) {
            validar = false;

        }

        if (!validar) {
            JOptionPane.showConfirmDialog(this, "Favor preencher todos os dados!", "Erro", JOptionPane.ERROR_MESSAGE);

        }

        return validar;
    }

    private void preenchercidade() {
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select * from cidade ";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codci = rs.getInt("codCidade");
                int code = rs.getInt("codEstado");
                int sit = rs.getInt("situacao");
                String desc = rs.getString("descricao");
                Cidade cidade = new Cidade(codci, desc, code, sit);
                getListcidade().add(cidade);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Cidade cidade : listcidade) {

            if (cidade.getSituacao() == 1) {
                cbcidade.addItem(cidade.getDescricao());

            }

        }

    }

    private void cadastro() {

     
        String sql = "insert into cliente (codCidade,cnpj, nome,contato, endereco, situacao)";
        sql += "values (?,? ,? ,?, ?, ?)";
        int codigo = 0;
        try {
            Connection conn = ConexaoBanco.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            for (Cidade cidade : listcidade) {
                if (cidade.getDescricao() == cbcidade.getSelectedItem()){
                    codigo = cidade.getCodCidade();
                }
            }
            ps.setInt(1, codigo);
            ps.setString(2, tftcnpj.getText());
            ps.setString(3, tfnome.getText());
            ps.setString(4, ftfcontato.getText());
            ps.setString(5, tfendereco.getText());
            ps.setInt(6, 1);
            ps.execute();

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!", "Confirmação de cadastro", JOptionPane.INFORMATION_MESSAGE);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Cadastro_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pprincipal = new javax.swing.JPanel();
        lnome = new javax.swing.JLabel();
        lendereco = new javax.swing.JLabel();
        lcontato = new javax.swing.JLabel();
        tfnome = new javax.swing.JTextField();
        tfendereco = new javax.swing.JTextField();
        ftfcontato = new javax.swing.JFormattedTextField();
        lcnpj = new javax.swing.JLabel();
        tftcnpj = new javax.swing.JFormattedTextField();
        lcidade = new javax.swing.JLabel();
        cbcidade = new javax.swing.JComboBox<>();
        pbotoes = new javax.swing.JPanel();
        jbconfirmar = new javax.swing.JButton();
        jbcancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Cliente");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pprincipal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        lnome.setText("Nome: ");

        lendereco.setText("Endereço: ");

        lcontato.setText("Contato: ");

        try {
            ftfcontato.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lcnpj.setText("CNPJ: ");

        try {
            tftcnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tftcnpj.setToolTipText("");

        lcidade.setText("Cidade: ");

        cbcidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcidadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pprincipalLayout = new javax.swing.GroupLayout(pprincipal);
        pprincipal.setLayout(pprincipalLayout);
        pprincipalLayout.setHorizontalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pprincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pprincipalLayout.createSequentialGroup()
                        .addComponent(lendereco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfendereco, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pprincipalLayout.createSequentialGroup()
                        .addComponent(lnome)
                        .addGap(30, 30, 30)
                        .addComponent(tfnome, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pprincipalLayout.createSequentialGroup()
                        .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lcontato)
                            .addComponent(lcnpj)
                            .addComponent(lcidade))
                        .addGap(18, 18, 18)
                        .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tftcnpj, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(ftfcontato, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbcidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(256, Short.MAX_VALUE))
        );
        pprincipalLayout.setVerticalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pprincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lnome)
                    .addComponent(tfnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lendereco)
                    .addComponent(tfendereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcontato)
                    .addComponent(ftfcontato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcnpj)
                    .addComponent(tftcnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcidade)
                    .addComponent(cbcidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jbconfirmar.setText("Confirmar");
        jbconfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbconfirmarActionPerformed(evt);
            }
        });

        jbcancelar.setText("Cancelar");
        jbcancelar.setToolTipText("");
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
                .addComponent(jbconfirmar)
                .addGap(18, 18, 18)
                .addComponent(jbcancelar)
                .addGap(29, 29, 29))
        );
        pbotoesLayout.setVerticalGroup(
            pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pbotoesLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbconfirmar)
                    .addComponent(jbcancelar))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pbotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pprincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pprincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbcidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcidadeActionPerformed

    }//GEN-LAST:event_cbcidadeActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        VisualizarClientes vc = new VisualizarClientes();
        vc.setVisible(true);

    }//GEN-LAST:event_formWindowClosed

    private void jbcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcancelarActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair da tela de cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option == 0) {

            dispose();

        }

    }//GEN-LAST:event_jbcancelarActionPerformed

    private void jbconfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbconfirmarActionPerformed

        if(validardados()){
            
            cadastro();
            dispose();
            
        }

    }//GEN-LAST:event_jbconfirmarActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cadastro_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadastro_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadastro_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadastro_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cadastro_Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbcidade;
    private javax.swing.JFormattedTextField ftfcontato;
    private javax.swing.JButton jbcancelar;
    private javax.swing.JButton jbconfirmar;
    private javax.swing.JLabel lcidade;
    private javax.swing.JLabel lcnpj;
    private javax.swing.JLabel lcontato;
    private javax.swing.JLabel lendereco;
    private javax.swing.JLabel lnome;
    private javax.swing.JPanel pbotoes;
    private javax.swing.JPanel pprincipal;
    private javax.swing.JTextField tfendereco;
    private javax.swing.JTextField tfnome;
    private javax.swing.JFormattedTextField tftcnpj;
    // End of variables declaration//GEN-END:variables
}
