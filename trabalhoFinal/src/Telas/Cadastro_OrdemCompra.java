package Telas;

import Classes.Fornecedor;
import Classes.OrdemCompra;
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

public class Cadastro_OrdemCompra extends javax.swing.JFrame {

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

    public List<String> getListFornecedores() {
        return listFornecedores;
    }

    public void setListFornecedores(List<String> listFornecedores) {
        this.listFornecedores = listFornecedores;
    }

    public Cadastro_OrdemCompra() {
        initComponents();

        listFornecedores = new ArrayList<>();
        listCliente = new ArrayList<>();
        listProduto = new ArrayList<>();
        preencherCampos();
    }

    private List<String> listFornecedores;
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

            String sql2 = "select nome from fornecedor where situacao = 1";
            PreparedStatement ps2 = conexao.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                String desc2 = rs2.getString("nome");
                getListFornecedores().add(desc2);
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
        for (String listFornecedore : listFornecedores) {
            fornecedor_combo_box.addItem(listFornecedores.get(cont));
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
        } else if (fornecedor_combo_box.getSelectedIndex() < 0) {

            validar = false;
        } else if (cliente_combo_box.getSelectedIndex() < 0) {

            validar = false;

        } else if (quant_box.getText().isEmpty()) {
            validar = false;
        } else if (valUnitario_box.getText().isEmpty()) {

            validar = false;
        }

        if (!validar) {

            JOptionPane.showConfirmDialog(this, "Favor preencher todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return validar;

    }

    private void registrarOrdem() {
        OrdemCompra ordem = new OrdemCompra();

        int codP = 0;
        int codF = 0;
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

            index = fornecedor_combo_box.getSelectedIndex();
            nome = "'";
            nome += fornecedor_combo_box.getItemAt(index);
            nome += "'";

            String sql2 = "select codFornecedor from fornecedor where nome = " + String.valueOf(nome);
            PreparedStatement ps2 = conexao.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                int cod = rs2.getInt("codFornecedor");
                codF = cod;
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

        ordem.setCodTipoProduto(codP);
        ordem.setCodFornecedor(codF);
        ordem.setCodCliente(codC);
        ordem.setQuantidade(Integer.valueOf(quant_box.getText()));
        ordem.setValUnitario(Float.valueOf(valUnitario_box.getText()));
        ordem.setSituacao(1);

        String sql = "insert into ordemcompra (valUnitario, quantidade, codCliente, codFornecedor, codTipoProduto, quantEstocada, situacao)";
        sql += "values (?, ?, ?, ?, ?,?,?)";

        try {
            Connection conn = ConexaoBanco.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setFloat(1, ordem.getValUnitario());
            ps.setInt(2, ordem.getQuantidade());
            ps.setInt(3, ordem.getCodCliente());
            ps.setInt(4, ordem.getCodFornecedor());
            ps.setInt(5, ordem.getCodTipoProduto());
            ps.setInt(6, 0);
            ps.setInt(7, ordem.getSituacao());
            ps.execute();

            JOptionPane.showMessageDialog(this, "Ordem de Compra cadastrada com sucesso!", "Confirmação de cadastro", JOptionPane.INFORMATION_MESSAGE);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Cadastros_Fornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registro_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        produto_combo_box = new javax.swing.JComboBox<>();
        valUnitario_box = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        quant_box = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fornecedor_combo_box = new javax.swing.JComboBox<>();
        cliente_combo_box = new javax.swing.JComboBox<>();
        buttons_panel = new javax.swing.JPanel();
        cancellButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro Ordem de Compra");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        registro_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de Ordem de Compra"));

        jLabel1.setText("Produto:");

        jLabel2.setText("Valor Unitario:");

        valUnitario_box.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        jLabel3.setText("Quantidade:");

        jLabel4.setText("Fornecedor:");

        jLabel5.setText("Cliente:");

        javax.swing.GroupLayout registro_panelLayout = new javax.swing.GroupLayout(registro_panel);
        registro_panel.setLayout(registro_panelLayout);
        registro_panelLayout.setHorizontalGroup(
            registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registro_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registro_panelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(produto_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registro_panelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valUnitario_box, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registro_panelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(quant_box, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registro_panelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fornecedor_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registro_panelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cliente_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(385, Short.MAX_VALUE))
        );
        registro_panelLayout.setVerticalGroup(
            registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registro_panelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(produto_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(quant_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(valUnitario_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fornecedor_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cliente_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        cancellButton.setText("Cencelar");
        cancellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellButtonActionPerformed(evt);
            }
        });

        registerButton.setText("Registrar");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttons_panelLayout = new javax.swing.GroupLayout(buttons_panel);
        buttons_panel.setLayout(buttons_panelLayout);
        buttons_panelLayout.setHorizontalGroup(
            buttons_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttons_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registerButton)
                .addGap(18, 18, 18)
                .addComponent(cancellButton)
                .addContainerGap())
        );
        buttons_panelLayout.setVerticalGroup(
            buttons_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttons_panelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(buttons_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancellButton)
                    .addComponent(registerButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registro_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttons_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(registro_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttons_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed

        if (validardados()) {

            registrarOrdem();
            dispose();
        }


    }//GEN-LAST:event_registerButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        VisualizarOrdemDeCompra vo = new VisualizarOrdemDeCompra();
        vo.setVisible(true);

    }//GEN-LAST:event_formWindowClosed

    private void cancellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellButtonActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo  sair da tela de registro?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            dispose();

        }    }//GEN-LAST:event_cancellButtonActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cadastro_OrdemCompra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttons_panel;
    private javax.swing.JButton cancellButton;
    private javax.swing.JComboBox<String> cliente_combo_box;
    private javax.swing.JComboBox<String> fornecedor_combo_box;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox<String> produto_combo_box;
    private javax.swing.JFormattedTextField quant_box;
    private javax.swing.JButton registerButton;
    private javax.swing.JPanel registro_panel;
    private javax.swing.JFormattedTextField valUnitario_box;
    // End of variables declaration//GEN-END:variables
}
