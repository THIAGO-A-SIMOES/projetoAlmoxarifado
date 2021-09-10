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

public class Efetivacao_PedidoCliente extends javax.swing.JFrame {

    /**
     * @return the listcodc
     */
    public List<Integer> getListcodc() {
        return listcodc;
    }

    /**
     * @param listcodc the listcodc to set
     */
    public void setListcodc(List<Integer> listcodc) {
        this.listcodc = listcodc;
    }

    public int getCodigoped() {
        return codigoped;
    }

    public void setCodigoped(int codigoped) {
        this.codigoped = codigoped;
    }

    public int getQuantped() {
        return quantped;
    }

    public void setQuantped(int quantped) {
        this.quantped = quantped;
    }

    public List<Integer> getListcodrec() {
        return listcodrec;
    }

    public void setListcodrec(List<Integer> listcodrec) {
        this.listcodrec = listcodrec;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getListProduto() {
        return listProduto;
    }

    public void setListProduto(List<String> listProduto) {
        this.listProduto = listProduto;
    }

    public List<String> getListCliente() {
        return listCliente;
    }

    public void setListCliente(List<String> listCliente) {
        this.listCliente = listCliente;
    }

    public Efetivacao_PedidoCliente() {
        initComponents();

        listCliente = new ArrayList<>();
        listProduto = new ArrayList<>();
        listcodrec = new ArrayList<>();
        quantemcadarec = new ArrayList<>();
        listcodc = new ArrayList<>();

    }

    private List<String> listCliente;
    private List<String> listProduto;
    private List<Integer> listcodc;
    private List<Integer> listcodrec;
    private List<Integer> quantemcadarec;

    private int count;
    private int quantped;
    private int codigoped;

    public void ax(PedidoCliente pedcliente) {
        String nomecliente = null;
        String descricaoprod = null;
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select nome from cliente where codCliente = " + pedcliente.getCodCliente();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nomecliente = rs.getString("nome");

            }

            String sql3 = "select descricao from tipoProduto where codTipoProduto = " + pedcliente.getCodTipoProduto();
            PreparedStatement ps3 = conexao.prepareStatement(sql3);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                descricaoprod = rs3.getString("descricao");

            }

            String sql4 = "select count(*) as  quantidade from alojamento a join cliente c on (a.codCliente = c.codCliente) where c.codCliente = " + pedcliente.getCodCliente();
            PreparedStatement ps4 = conexao.prepareStatement(sql4);
            ResultSet rs4 = ps4.executeQuery();
            while (rs4.next()) {
                setCount(rs4.getInt("quantidade"));

            }

            String sql5 = "select r.codReceptaculo, r.codCorredor from cliente c join alojamento a on (c.codCliente = a.codCliente)  join corredor cl  on  (cl.codAlojamento =  a.codAlojamento)  join receptaculo r on (r.codCorredor = cl.codCorredor)  where c.codCliente = " + pedcliente.getCodCliente() + " and r.codTipoProduto  = " + pedcliente.getCodTipoProduto();
            PreparedStatement ps5 = conexao.prepareStatement(sql5);
            ResultSet rs5 = ps5.executeQuery();
            while (rs5.next()) {

                int codr = rs5.getInt("codReceptaculo");
                getListcodrec().add(codr);
                getListcodc().add(rs5.getInt("codCorredor"));
                String sql6 = "select quantidade from receptaculo where codReceptaculo = " + codr;
                PreparedStatement ps6 = conexao.prepareStatement(sql6);
                ResultSet rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    getQuantemcadarec().add(rs6.getInt("quantidade"));

                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        cliente_combo_box.addItem(nomecliente);
        produto_combo_box.addItem(descricaoprod);

        quant_box.setText(String.valueOf(pedcliente.getQuantidade()));

        cliente_combo_box.setSelectedIndex(0);
        produto_combo_box.setSelectedIndex(0);

        setQuantped(pedcliente.getQuantidade());
        setCodigoped(pedcliente.getCodPedidoCliente());
    }

    private void efetivarpedido() {
        int somatotal = 0;

        for (Integer integer : quantemcadarec) {

            somatotal = somatotal + integer;

        }

        if (getCount() == 0) {
            JOptionPane.showMessageDialog(this, "Não há alojamentos vinculado a este cliente", "Erro", JOptionPane.ERROR_MESSAGE);

        } else if (somatotal < getQuantped()) {
            JOptionPane.showMessageDialog(this, "Você não possui esta quantidade em estoque!", "Erro", JOptionPane.ERROR_MESSAGE);

        } else {
            int cont = 0;
            for (Integer rece : listcodrec) {

                if (getQuantped() == quantemcadarec.get(cont)) {
                    try {
                        String sql = "update receptaculo set quantidade = 0, codTipoProduto = null where codReceptaculo = " + rece;
                        Connection conn = ConexaoBanco.getConnection();
                        PreparedStatement ps = conn.prepareStatement(sql);
                        JOptionPane.showMessageDialog(this, getQuantped() + " produtos foram retirados do receptaculo " + rece + " - " + getListcodc().get(cont), "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                        ps.execute();

                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Efetivacao_PedidoCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                } else if (getQuantped() > quantemcadarec.get(cont)) {
                    try {
                        String sql = "update receptaculo set quantidade = 0, codTipoProduto = null where codReceptaculo = " + rece;
                        Connection conn = ConexaoBanco.getConnection();
                        PreparedStatement ps = conn.prepareStatement(sql);
                        JOptionPane.showMessageDialog(this, quantemcadarec.get(cont) + " produtos foram retirados do receptaculo " + rece + " - " + getListcodc().get(cont), "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                        ps.execute();
                        setQuantped(getQuantped() - quantemcadarec.get(cont));

                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Efetivacao_PedidoCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    try {
                        int ax = quantemcadarec.get(cont) - getQuantped();
                        String sql = "update receptaculo set quantidade = " + ax + " where codReceptaculo = " + rece;
                        Connection conn = ConexaoBanco.getConnection();
                        PreparedStatement ps = conn.prepareStatement(sql);
                        JOptionPane.showMessageDialog(this, getQuantped() + " produtos foram retirados do receptaculo " + rece + " - " + getListcodc().get(cont), "Confirmação", JOptionPane.INFORMATION_MESSAGE);

                        ps.execute();
                        setQuantped(getQuantped() - quantemcadarec.get(cont));

                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Efetivacao_PedidoCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                cont++;
            }

            try {
                int ax = quantemcadarec.get(cont) - getQuantped();
                String sql = "update pedidoCliente set situacao = 0  where codPedidoCliente = " + getCodigoped();
                Connection conn = ConexaoBanco.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.execute();
                setQuantped(getQuantped() - quantemcadarec.get(cont));
                JOptionPane.showMessageDialog(this, "Efetivação realizada com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Efetivacao_PedidoCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

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
        setTitle("Efitivação Pedido de Cliente");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pedidoCliente_pane.setBorder(javax.swing.BorderFactory.createTitledBorder("Efetivação Pedido de Cliente"));

        jLabel1.setText("Produto:");

        jLabel2.setText("Quantidade:");

        jLabel3.setText("Cliente:");

        cliente_combo_box.setEnabled(false);
        cliente_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cliente_combo_boxActionPerformed(evt);
            }
        });

        produto_combo_box.setEnabled(false);

        quant_box.setEditable(false);
        quant_box.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        quant_box.setEnabled(false);

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

        regButtton.setText("Efetivar");
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
                .addContainerGap(426, Short.MAX_VALUE)
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

        efetivarpedido();
        dispose();


    }//GEN-LAST:event_regButttonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        EfetivarPedidoCliente vp = new EfetivarPedidoCliente();
        vp.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void cancellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellButtonActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair da tela de registo?", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
        if (option == 0) {
            dispose();
        }

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
            java.util.logging.Logger.getLogger(Efetivacao_PedidoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Efetivacao_PedidoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Efetivacao_PedidoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Efetivacao_PedidoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Efetivacao_PedidoCliente().setVisible(true);
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

    public List<Integer> getQuantemcadarec() {
        return quantemcadarec;
    }

    public void setQuantemcadarec(List<Integer> quantemcadarec) {
        this.quantemcadarec = quantemcadarec;
    }
}
