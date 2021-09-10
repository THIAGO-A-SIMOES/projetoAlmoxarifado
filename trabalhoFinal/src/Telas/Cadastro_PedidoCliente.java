package Telas;

import Classes.PedidoCliente;
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

public class Cadastro_PedidoCliente extends javax.swing.JFrame {

    /**
     * @return the listProduto
     */
    public List<String> getListProduto() {
        return listProduto;
    }

    /**
     * @param listProduto the listProduto to set
     */
    public void setListProduto(List<String> listProduto) {
        this.listProduto = listProduto;
    }

    /**
     * @return the listCliente
     */
    public List<String> getListCliente() {
        return listCliente;
    }

    /**
     * @param listCliente the listCliente to set
     */
    public void setListCliente(List<String> listCliente) {
        this.listCliente = listCliente;
    }

    public Cadastro_PedidoCliente() {
        initComponents();

        listCliente = new ArrayList<>();
        listProduto = new ArrayList<>();
        preencherCampos();
    }

    private List<String> listCliente;
    private List<String> listProduto;

    private void preencherCampos() {
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select descricao from tipoproduto where situacao = 1";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String desc = rs.getString("descricao");
                getListProduto().add(desc);
            }

            String sql3 = "select nome from cliente where situacao = 1";
            PreparedStatement ps3 = conexao.prepareStatement(sql3);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                String desc3 = rs3.getString("nome");
                getListCliente().add(desc3);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int cont = 0;
        for (String listProd : getListProduto()) {
            produto_combo_box.addItem(listProduto.get(cont));
            cont++;
        }

        cont = 0;
        for (String listCli : listCliente) {
            cliente_combo_box.addItem(listCliente.get(cont));
            cont++;
        }
    }

    private boolean validardados() {

        boolean validar = true;

        if (produto_combo_box.getSelectedIndex() < 0) {
            validar = false;

        } else if (cliente_combo_box.getSelectedIndex() < 0) {
            validar = false;
        } else if (quant_box.getText().isEmpty()) {
            validar = false;

        }

        if (!validar) {
            JOptionPane.showConfirmDialog(this, "Favor preencher todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);

        }

        return validar;

    }

    private void registroPedido() {
        PedidoCliente pedido = new PedidoCliente();

        int codP = 0;
        int codC = 0;

        try {
            Connection conexao = ConexaoBanco.getConnection();
            int index = produto_combo_box.getSelectedIndex();
            String nome = "'";
            nome += produto_combo_box.getItemAt(index);
            nome += "'";

            String sql1 = "select codTipoProduto from tipoproduto where descricao = " + String.valueOf(nome);
            PreparedStatement ps1 = conexao.prepareStatement(sql1);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                int cod = rs1.getInt("codTipoProduto");
                codP = cod;
            }

            index = cliente_combo_box.getSelectedIndex();
            nome = "'";
            nome += cliente_combo_box.getItemAt(index);
            nome += "'";

            String sql3 = "select codCliente from cliente where nome = " + String.valueOf(nome);
            PreparedStatement ps3 = conexao.prepareStatement(sql3);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                int cod = rs3.getInt("codCliente");
                codC = cod;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        pedido.setCodCliente(codC);
        pedido.setCodTipoProduto(codP);
        pedido.setQuantidade(Integer.valueOf(quant_box.getText()));
        pedido.setSituacao(1);

        String sql = "insert into pedidocliente (codTipoProduto, codCliente, quantidade, situacao)";
        sql += "values (?, ?, ?, ?)";

        try {
            Connection conn = ConexaoBanco.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pedido.getCodTipoProduto());
            ps.setInt(2, pedido.getCodCliente());
            ps.setInt(3, pedido.getQuantidade());
            ps.setInt(4, pedido.getSituacao());
            ps.execute();

            JOptionPane.showMessageDialog(this, "Pedido de Cliente registrado com sucesso!", "Confirmação de cadastro", JOptionPane.INFORMATION_MESSAGE);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Cadastros_Fornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pedidoCliente_pane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cliente_combo_box = new javax.swing.JComboBox<>();
        produto_combo_box = new javax.swing.JComboBox<>();
        quant_box = new javax.swing.JFormattedTextField();
        buttons_pane = new javax.swing.JPanel();
        cancellButton = new javax.swing.JButton();
        regButtton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro Pedido de Cliente");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pedidoCliente_pane.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar Pedido de Cliente"));

        jLabel1.setText("Produto:");

        jLabel2.setText("Quantidade:");

        jLabel3.setText("Cliente:");

        cliente_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cliente_combo_boxActionPerformed(evt);
            }
        });

        quant_box.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        javax.swing.GroupLayout pedidoCliente_paneLayout = new javax.swing.GroupLayout(pedidoCliente_pane);
        pedidoCliente_pane.setLayout(pedidoCliente_paneLayout);
        pedidoCliente_paneLayout.setHorizontalGroup(
            pedidoCliente_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pedidoCliente_paneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pedidoCliente_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pedidoCliente_paneLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quant_box, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pedidoCliente_paneLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(produto_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pedidoCliente_paneLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cliente_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pedidoCliente_paneLayout.setVerticalGroup(
            pedidoCliente_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pedidoCliente_paneLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pedidoCliente_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cliente_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pedidoCliente_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(produto_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pedidoCliente_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(quant_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        cancellButton.setText("Cancelar");
        cancellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellButtonActionPerformed(evt);
            }
        });

        regButtton.setText("Registrar");
        regButtton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regButttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttons_paneLayout = new javax.swing.GroupLayout(buttons_pane);
        buttons_pane.setLayout(buttons_paneLayout);
        buttons_paneLayout.setHorizontalGroup(
            buttons_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttons_paneLayout.createSequentialGroup()
                .addContainerGap(420, Short.MAX_VALUE)
                .addComponent(regButtton)
                .addGap(18, 18, 18)
                .addComponent(cancellButton)
                .addContainerGap())
        );
        buttons_paneLayout.setVerticalGroup(
            buttons_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttons_paneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(buttons_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regButtton)
                    .addComponent(cancellButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pedidoCliente_pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttons_pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pedidoCliente_pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttons_pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cliente_combo_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cliente_combo_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cliente_combo_boxActionPerformed

    private void regButttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regButttonActionPerformed

        if (validardados()) {
            registroPedido();
            dispose();

        }


    }//GEN-LAST:event_regButttonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        VisualizarPedidoCliente vp = new VisualizarPedidoCliente();
        vp.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void cancellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellButtonActionPerformed
        int option  = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair da tela de registo?","Confirmação", JOptionPane.INFORMATION_MESSAGE);
        if (option == 0)
            dispose();
            
    }//GEN-LAST:event_cancellButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Cadastro_PedidoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadastro_PedidoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadastro_PedidoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadastro_PedidoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cadastro_PedidoCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttons_pane;
    private javax.swing.JButton cancellButton;
    private javax.swing.JComboBox<String> cliente_combo_box;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel pedidoCliente_pane;
    private javax.swing.JComboBox<String> produto_combo_box;
    private javax.swing.JFormattedTextField quant_box;
    private javax.swing.JButton regButtton;
    // End of variables declaration//GEN-END:variables
}
