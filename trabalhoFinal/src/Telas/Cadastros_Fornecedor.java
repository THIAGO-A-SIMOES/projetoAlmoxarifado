package Telas;

import Classes.Cidade;
import Classes.Estado;
import Classes.Fornecedor;
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


public class Cadastros_Fornecedor extends javax.swing.JFrame {


    public List<Integer> getListCodEstado() {
        return listCodEstado;
    }

    public List<String> getListCidade() {
        return listCidade;
    }

    public void setListCidade(List<String> listCidade) {
        this.listCidade = listCidade;
    }

    public void setListCodEstado(List<Integer> listCodEstado) {
        this.listCodEstado = listCodEstado;
    }

    public List<String> getListEstado() {
        return listEstado;
    }

    public void setListEstado(List<String> listEstado) {
        this.listEstado = listEstado;
    }

    private List<String> listEstado;
    private List<Integer> listCodEstado;
    private List<String> listCidade;

    public Cadastros_Fornecedor() {
        initComponents();
        
        listEstado = new ArrayList<>();
        listCodEstado = new ArrayList<>();
        listCidade = new ArrayList<>();
        preencherEstados();
    }

    
    private void preencherEstados(){
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select descricao, codEstado from estado";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String desc = rs.getString("descricao");
                int codE = rs.getInt("codEstado");
                getListEstado().add(desc);
                getListCodEstado().add(codE);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int cont = 0;
        for (String listEstado1 : getListEstado()) {
            estado_combo_box.addItem(listEstado.get(cont));
            cont ++;
        }
        
        
    }
    
    private void preencherCidades(){
        
        cidade_combo_box.removeAllItems();
        listCidade.clear();
        
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String ax = "'";
            ax += String.valueOf(estado_combo_box.getSelectedItem());
            ax += "'";
            String sql1 = "select codEstado from estado where descricao = " + ax;
            PreparedStatement ps1 = conexao.prepareStatement(sql1);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                int codE = rs1.getInt("codEstado");
                getListCodEstado().add(codE);
            }
            
            String sql = "select descricao from cidade where codEstado = " + String.valueOf(listCodEstado.get(estado_combo_box.getSelectedIndex()));
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String desc = rs.getString("descricao");
                getListCidade().add(desc);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int cont = 0;
        
        cidade_combo_box.removeAllItems();
        
        for (String listString : getListCidade()) {
            cidade_combo_box.addItem(listCidade.get(cont));
            cont ++;
        }
        
        cidade_combo_box.setSelectedIndex(-1);
    }
    
    private boolean validardados(){
        boolean validar = true;
        
        if (nome_box.getText().isEmpty()){
            validar = false;
            
        }else if(cnpj_box.getText().equals("  .   .   /0001-  ")){
            validar = false;
            
        }else if(contato_box.getText().equals("(  )    -    ")){
            validar = false;
            
        }else if(estado_combo_box.getSelectedIndex() == -1){
            validar = false;
            
        }else if(cidade_combo_box.getSelectedIndex() == -1){
            validar = false;
            
        }else if(rua_box.getText().isEmpty()){
            validar = false;
            
        }else if(numero_box.getText().isEmpty()){
            validar = false;
            
        }else if(bairro_box.getText().isEmpty()){
            validar = false;
        }
        
        if (!validar){
            
            JOptionPane.showMessageDialog(this, "Favor preencher todos os campos", "Erro ao cadastrar tipo produto", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return validar;
    }
    
    private void cadastrarFornecedor(){
       
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(nome_box.getText());
            fornecedor.setCnpj(cnpj_box.getText());
            fornecedor.setContato(contato_box.getText());
            int index = cidade_combo_box.getSelectedIndex();
            String nome = "'";
            nome += cidade_combo_box.getItemAt(index);
            nome += "'";
            int codC = 0;
                    
            try {
                Connection conexao = ConexaoBanco.getConnection();
                String sql1 = "select codCidade from cidade where descricao = " + String.valueOf(nome);
                PreparedStatement ps1 = conexao.prepareStatement(sql1);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    int cod = rs1.getInt("codCidade");
                    codC = cod;
                }

            } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao obter os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
            }
                  
            fornecedor.setCodCidade(codC);
            String endereco = rua_box.getText();
            endereco += " ";
            endereco += "Nº: ";
            endereco += numero_box.getText();
            endereco += " ";
            endereco += "Bairro: ";
            endereco += bairro_box.getText();
            fornecedor.setEndereco(endereco);
            fornecedor.setSituacao(1);           
            
            String sql = "insert into fornecedor (codCidade, cnpj, nome, contato, endereco, situacao)";
            sql += "values (?, ?, ?, ?, ?, ?)";
            
            try {
                Connection conn = ConexaoBanco.getConnection();
                PreparedStatement  ps = conn.prepareStatement(sql);
                ps.setInt(1, fornecedor.getCodCidade());
                ps.setString(2, fornecedor.getCnpj());
                ps.setString(3, fornecedor.getNome());
                ps.setString(4, fornecedor.getContato());
                ps.setString(5, fornecedor.getEndereco());
                ps.setInt(6, fornecedor.getSituacao());
                ps.execute();
            
                JOptionPane.showMessageDialog(this, "Fornecedor cadastrado com sucesso!", "Confirmação de cadastro", JOptionPane.INFORMATION_MESSAGE);
               
            } 
            catch (ClassNotFoundException  | SQLException ex) { 
                Logger.getLogger(Cadastros_Fornecedor.class.getName()).log(Level.SEVERE, null, ex);            
            }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cadastro_panel = new javax.swing.JPanel();
        nome_label = new javax.swing.JLabel();
        cnpj_label = new javax.swing.JLabel();
        contato_label = new javax.swing.JLabel();
        cidade_label = new javax.swing.JLabel();
        estado_label = new javax.swing.JLabel();
        nome_box = new javax.swing.JTextField();
        cidade_combo_box = new javax.swing.JComboBox<>();
        estado_combo_box = new javax.swing.JComboBox<>();
        rua_label = new javax.swing.JLabel();
        rua_box = new javax.swing.JTextField();
        cancelarButton = new javax.swing.JButton();
        cadastroButton = new javax.swing.JButton();
        bairro_label = new javax.swing.JLabel();
        numero_label = new javax.swing.JLabel();
        bairro_box = new javax.swing.JTextField();
        cnpj_box = new javax.swing.JFormattedTextField();
        contato_box = new javax.swing.JFormattedTextField();
        numero_box = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro do Fornecedor");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        cadastro_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Fornecedor"));

        nome_label.setText("Nome:");

        cnpj_label.setText("CNPJ:");

        contato_label.setText("Contato:");

        cidade_label.setText("Cidade:");

        estado_label.setText("Estado:");

        estado_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estado_combo_boxActionPerformed(evt);
            }
        });

        rua_label.setText("Rua:");

        cancelarButton.setText("Cencelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        cadastroButton.setText("Cadastrar");
        cadastroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroButtonActionPerformed(evt);
            }
        });

        bairro_label.setText("Bairro:");

        numero_label.setText("N°:");

        try {
            cnpj_box.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cnpj_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnpj_boxActionPerformed(evt);
            }
        });

        try {
            contato_box.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        numero_box.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        javax.swing.GroupLayout cadastro_panelLayout = new javax.swing.GroupLayout(cadastro_panel);
        cadastro_panel.setLayout(cadastro_panelLayout);
        cadastro_panelLayout.setHorizontalGroup(
            cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cadastro_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cadastro_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cadastroButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelarButton))
                    .addGroup(cadastro_panelLayout.createSequentialGroup()
                        .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cadastro_panelLayout.createSequentialGroup()
                                .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nome_label)
                                    .addComponent(cnpj_label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nome_box, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cnpj_box, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(cadastro_panelLayout.createSequentialGroup()
                                .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(contato_label)
                                    .addComponent(estado_label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(cadastro_panelLayout.createSequentialGroup()
                                        .addComponent(estado_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(121, 121, 121)
                                        .addComponent(cidade_label)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cidade_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(contato_box, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(cadastro_panelLayout.createSequentialGroup()
                                .addComponent(bairro_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bairro_box, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(cadastro_panelLayout.createSequentialGroup()
                                .addComponent(rua_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rua_box, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(numero_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numero_box, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 117, Short.MAX_VALUE)))
                .addContainerGap())
        );
        cadastro_panelLayout.setVerticalGroup(
            cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cadastro_panelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome_label)
                    .addComponent(nome_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnpj_label)
                    .addComponent(cnpj_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contato_label)
                    .addComponent(contato_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estado_label)
                    .addComponent(estado_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cidade_label)
                    .addComponent(cidade_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rua_label)
                    .addComponent(rua_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numero_label)
                    .addComponent(numero_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bairro_label)
                    .addComponent(bairro_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton)
                    .addComponent(cadastroButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cadastro_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cadastro_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void estado_combo_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estado_combo_boxActionPerformed
        preencherCidades();
    }//GEN-LAST:event_estado_combo_boxActionPerformed

    private void cadastroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastroButtonActionPerformed
        if(validardados()){
            cadastrarFornecedor();
            dispose();
        }
    }//GEN-LAST:event_cadastroButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        VizualizarFornecedores vf = new VizualizarFornecedores();
        vf.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void cnpj_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnpj_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cnpj_boxActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair do cadastro de Fornecedor?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            dispose();
        }
    }//GEN-LAST:event_cancelarButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Cadastros_Fornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadastros_Fornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadastros_Fornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadastros_Fornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cadastros_Fornecedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bairro_box;
    private javax.swing.JLabel bairro_label;
    private javax.swing.JButton cadastroButton;
    private javax.swing.JPanel cadastro_panel;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JComboBox<String> cidade_combo_box;
    private javax.swing.JLabel cidade_label;
    private javax.swing.JFormattedTextField cnpj_box;
    private javax.swing.JLabel cnpj_label;
    private javax.swing.JFormattedTextField contato_box;
    private javax.swing.JLabel contato_label;
    private javax.swing.JComboBox<String> estado_combo_box;
    private javax.swing.JLabel estado_label;
    private javax.swing.JTextField nome_box;
    private javax.swing.JLabel nome_label;
    private javax.swing.JFormattedTextField numero_box;
    private javax.swing.JLabel numero_label;
    private javax.swing.JTextField rua_box;
    private javax.swing.JLabel rua_label;
    // End of variables declaration//GEN-END:variables
}
