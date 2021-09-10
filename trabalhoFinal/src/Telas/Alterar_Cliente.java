package Telas;

import Classes.Categoria;
import Classes.Cidade;
import Classes.Cliente;
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
import javax.sound.midi.SysexMessage;
import javax.swing.JOptionPane;

public class Alterar_Cliente extends javax.swing.JFrame {

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the verify
     */
    public int getVerify() {
        return verify;
    }

    /**
     * @param verify the verify to set
     */
    public void setVerify(int verify) {
        this.verify = verify;
    }

    /**
     * @return the listordemcompra
     */
    public List<Integer> getListordemcompra() {
        return listordemcompra;
    }

    /**
     * @param listordemcompra the listordemcompra to set
     */
    public void setListordemcompra(List<Integer> listordemcompra) {
        this.listordemcompra = listordemcompra;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<Cidade> getListcidade() {
        return listcidade;
    }

    public void setListcidade(List<Cidade> listcidade) {
        this.listcidade = listcidade;
    }

    public Alterar_Cliente() {
        initComponents();
        listcidade = new ArrayList<>();
        listordemcompra = new ArrayList<>();
        preenchercidade();
    }
    private int verify;
    private int codigo;
    private int status;
    private List<Cidade> listcidade;

    private List<Integer> listordemcompra;

    public void ax(Cliente cliente) {
        setStatus(cliente.getSituacao());
        if (cliente.getSituacao() == 1) {

            cbsituacao.setSelectedItem("Ativado");
        } else {
            cbsituacao.setSelectedItem("Desativado");

        }

        tfnome.setText(cliente.getNome());
        tfendereco.setText(cliente.getEndereco());
        ftfcontato.setText(cliente.getContato());
        tftcnpj.setText(cliente.getCnpj());
        for (Cidade cidade : listcidade) {
            if (cliente.getCodCidade() == cidade.getCodCidade()) {
                cbcidade.setSelectedItem(cidade.getDescricao());
            }
        }
        setCodigo(cliente.getCodCliente());
        String sql = "select o.situacao from cliente c join ordemCompra o on (c.codCliente = o.codCliente) where c.codCliente = " + cliente.getCodCliente();
        try {
            Connection conn = ConexaoBanco.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs2 = ps.executeQuery(sql);
            while (rs2.next()) {

                getListordemcompra().add(rs2.getInt("situacao"));

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Alterar_Cliente.class.getName()).log(Level.SEVERE, null, ex);

        }

        boolean verifica = false;
        for (Integer integer : listordemcompra) {
            if (integer == 1) {

                verifica = true;
            }
        }

        if (verifica == true) {
            cbsituacao.setEnabled(false);
            setVerify(1);

        } else {
            setVerify(0);

        }
    }

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

        cbsituacao.addItem("Ativado");
        cbsituacao.addItem("Desativado");

    }

    private void alterar() {
        
        
        if (getVerify() == 1) {
            String sql = "update cliente set codCidade = ?, cnpj = ?, nome = ?, contato = ?, endereco = ?, situacao = ? where codCliente = " + String.valueOf(getCodigo());
            try {
                Connection conn = ConexaoBanco.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                for (Cidade cidade : listcidade) {
                    if (cidade.getDescricao() == cbcidade.getSelectedItem()) {
                        codigo = cidade.getCodCidade();
                    }
                }
                ps.setInt(1, codigo);
                ps.setString(2, tftcnpj.getText());
                ps.setString(3, tfnome.getText());
                ps.setString(4, ftfcontato.getText());
                ps.setString(5, tfendereco.getText());
                if (cbsituacao.getSelectedIndex() == 0) {
                    ps.setInt(6, 1);

                } else {
                    ps.setInt(6, 0);

                }

                ps.execute();

                JOptionPane.showMessageDialog(this, "Alteração realizada com sucesso!", "Confirmação da alteração", JOptionPane.INFORMATION_MESSAGE);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Alterar_Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
          
            if (getStatus() == 1 && cbsituacao.getSelectedIndex() == 1) {
                try {
                    Connection conn = ConexaoBanco.getConnection();
                    String sql2 = "update receptaculo set quantidade = 0, codTipoProduto = null where codCorredor in (select codCorredor from corredor where codAlojamento in (select a.codAlojamento from alojamento a join cliente cl on (a.codCliente  = cl.codCliente ) where cl.codCliente = " + getCodigo() + " ))";
                    Connection conn2 = ConexaoBanco.getConnection();
                    PreparedStatement ps1 = conn2.prepareStatement(sql2);
                    ps1.execute();
                    
                    
                    String sql1 = "update alojamento set codCliente = null where codCliente = " + getCodigo();
                    PreparedStatement ps2 = conn.prepareStatement(sql1);
                    ps2.execute();
                    
                    
                    String sql = "update cliente set codCidade = ?, cnpj = ?, nome = ?, contato = ?, endereco = ?, situacao = ? where codCliente = " + String.valueOf(getCodigo());

                    Connection conn1 = ConexaoBanco.getConnection();
                    PreparedStatement ps = conn1.prepareStatement(sql);
                    for (Cidade cidade : listcidade) {
                        if (cidade.getDescricao() == cbcidade.getSelectedItem()) {
                            codigo = cidade.getCodCidade();
                        }
                    }
                    ps.setInt(1, codigo);
                    ps.setString(2, tftcnpj.getText());
                    ps.setString(3, tfnome.getText());
                    ps.setString(4, ftfcontato.getText());
                    ps.setString(5, tfendereco.getText());
                    ps.setInt(6, 0);
                    ps.execute();
                    
                    
                    
                  
                    

                    JOptionPane.showMessageDialog(this, "Alteração realizada com sucesso!", "Confirmação da alteração", JOptionPane.INFORMATION_MESSAGE);

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Alterar_Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }

            

            } else {
                String sql = "update cliente set codCidade = ?, cnpj = ?, nome = ?, contato = ?, endereco = ?, situacao = ? where codCliente = " + String.valueOf(getCodigo());
                try {
                    Connection conn = ConexaoBanco.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    for (Cidade cidade : listcidade) {
                        if (cidade.getDescricao() == cbcidade.getSelectedItem()) {
                            codigo = cidade.getCodCidade();
                        }
                    }
                    ps.setInt(1, codigo);
                    ps.setString(2, tftcnpj.getText());
                    ps.setString(3, tfnome.getText());
                    ps.setString(4, ftfcontato.getText());
                    ps.setString(5, tfendereco.getText());
                    if (cbsituacao.getSelectedIndex() == 0) {
                        ps.setInt(6, 1);

                    } else {
                        ps.setInt(6, 0);

                    }

                    ps.execute();

                    JOptionPane.showMessageDialog(this, "Alteração realizada com sucesso!", "Confirmação da alteração", JOptionPane.INFORMATION_MESSAGE);

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Alterar_Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

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
        lsituacao = new javax.swing.JLabel();
        cbsituacao = new javax.swing.JComboBox<>();
        pbotoes = new javax.swing.JPanel();
        jbconfirmar = new javax.swing.JButton();
        jbcancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alteração de Cliente");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pprincipal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alteração de Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

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

        lsituacao.setText("Situação: ");

        javax.swing.GroupLayout pprincipalLayout = new javax.swing.GroupLayout(pprincipal);
        pprincipal.setLayout(pprincipalLayout);
        pprincipalLayout.setHorizontalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pprincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pprincipalLayout.createSequentialGroup()
                        .addComponent(lnome)
                        .addGap(30, 30, 30)
                        .addComponent(tfnome))
                    .addGroup(pprincipalLayout.createSequentialGroup()
                        .addComponent(lendereco)
                        .addGap(12, 12, 12)
                        .addComponent(tfendereco))
                    .addGroup(pprincipalLayout.createSequentialGroup()
                        .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lcontato)
                            .addComponent(lcnpj)
                            .addComponent(lcidade))
                        .addGap(18, 18, 18)
                        .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pprincipalLayout.createSequentialGroup()
                                .addComponent(cbcidade, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lsituacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbsituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tftcnpj, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                .addComponent(ftfcontato, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addGap(216, 216, 216))
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
                    .addComponent(cbcidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lsituacao)
                    .addComponent(cbsituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jbconfirmar.setText("Alterar");
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

        if (validardados()) {

            alterar();
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
            java.util.logging.Logger.getLogger(Alterar_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Alterar_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Alterar_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Alterar_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Alterar_Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbcidade;
    private javax.swing.JComboBox<String> cbsituacao;
    private javax.swing.JFormattedTextField ftfcontato;
    private javax.swing.JButton jbcancelar;
    private javax.swing.JButton jbconfirmar;
    private javax.swing.JLabel lcidade;
    private javax.swing.JLabel lcnpj;
    private javax.swing.JLabel lcontato;
    private javax.swing.JLabel lendereco;
    private javax.swing.JLabel lnome;
    private javax.swing.JLabel lsituacao;
    private javax.swing.JPanel pbotoes;
    private javax.swing.JPanel pprincipal;
    private javax.swing.JTextField tfendereco;
    private javax.swing.JTextField tfnome;
    private javax.swing.JFormattedTextField tftcnpj;
    // End of variables declaration//GEN-END:variables
}
