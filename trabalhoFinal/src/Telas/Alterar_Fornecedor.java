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


public class Alterar_Fornecedor extends javax.swing.JFrame {

   
    public List <Integer> getListosit() {
        return listosit;
    }


    public void setListosit(List <Integer> listosit) {
        this.listosit = listosit;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }


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
    private int codigo;

    public Alterar_Fornecedor() {
        initComponents();
        
        listEstado = new ArrayList<>();
        listCodEstado = new ArrayList<>();
        listCidade = new ArrayList<>();
        listosit = new ArrayList<>();
        preencherEstados();
        
    }
    
    
    private List <Integer> listosit;
    
    

    
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
            
        }else if(endereco_box.getText().isEmpty()){
            validar = false;
            
        }
        
        if (!validar){
            
            JOptionPane.showMessageDialog(this, "Favor preencher todos os campos", "Erro ao cadastrar tipo produto", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return validar;
    }
    
    private void alterar(){
       
       try {
            String sql = "update fornecedor set codCidade = ?, cnpj = ?, nome = ?, contato = ?, endereco = ?, situacao = ? where codFornecedor = " + String.valueOf(getCodigo());
            Connection conn = ConexaoBanco.getConnection();
            PreparedStatement  ps = conn.prepareStatement(sql);
            
            int codC = 0;
            int index = cidade_combo_box.getSelectedIndex();
            String nome = "'";
            nome += cidade_combo_box.getItemAt(index);
            nome += "'";
            String sql2 = "select codCidade from cidade where descricao = " + String.valueOf(nome);
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ResultSet rs1 = ps2.executeQuery();
            while (rs1.next()) {
                int cod = rs1.getInt("codCidade");
                codC = cod;
            }
            
            ps.setInt(1, codC);                
            ps.setString(2, cnpj_box.getText());
            ps.setString(3, nome_box.getText());
            ps.setString(4, contato_box.getText());
            ps.setString(5, endereco_box.getText());
            
            if(status_combo_box.getSelectedItem() == "Ativado"){
                ps.setInt(6, 1);
            }else{
                ps.setInt(6, 0);
            }
            ps.execute();
            
            } 
            catch (ClassNotFoundException  | SQLException ex) { 
                Logger.getLogger(Alterar_Cidade.class.getName()).log(Level.SEVERE, null, ex);            
            }
       
            JOptionPane.showMessageDialog(this, "Alteração realizada com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
       
             
    }
    
    public void ax(Fornecedor fornecedor){
              
        nome_box.setText(fornecedor.getNome());
        cnpj_box.setText(fornecedor.getCnpj());
        contato_box.setText(fornecedor.getContato());
       
        String cidade = "";
        String estado = "";
        try {
            String sql = "select descricao from cidade where codCidade = " + String.valueOf(fornecedor.getCodCidade());
            Connection conn = ConexaoBanco.getConnection();
            PreparedStatement  ps = conn.prepareStatement(sql);
            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()) {
                String desc = rs1.getString("descricao");
                cidade = desc;
            }
            
            String sql2 = "select e.descricao from estado e join cidade c on c.codEstado = e.codEstado where c.codCidade = " + String.valueOf(fornecedor.getCodCidade());
            PreparedStatement  ps2 = conn.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                String desc1 = rs2.getString("descricao");
                estado = desc1;
            }
            
        }
        catch (ClassNotFoundException  | SQLException ex) { 
                Logger.getLogger(Alterar_Cidade.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
        
        try {
            String sql = "select o.situacao from fornecedor f join ordemCompra o on (f.codFornecedor = o.codFornecedor) where f.codFornecedor = " + fornecedor.getCodFornecedor();
            Connection conn = ConexaoBanco.getConnection();
            PreparedStatement  ps = conn.prepareStatement(sql);
            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()) {
                getListosit().add (rs1.getInt("situacao"));
            }
            
          
            
        }
        catch (ClassNotFoundException  | SQLException ex) { 
                Logger.getLogger(Alterar_Fornecedor.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
        estado_combo_box.setSelectedItem(estado);
        cidade_combo_box.setSelectedItem(cidade);
        endereco_box.setText(fornecedor.getEndereco());
        setCodigo(fornecedor.getCodFornecedor());
        
        if(fornecedor.getSituacao() == 1){
            status_combo_box.setSelectedItem("Ativada");
        }else{
            status_combo_box.setSelectedItem("Desativada");
        }
        
        
        boolean verifica = false;
        
        for (Integer integer : listosit) {
            
            if (integer  == 1){
                
                
                verifica = true;
            }
            
        }
        if (verifica == true)
            status_combo_box.setEnabled(false);
        
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
        endereco_label = new javax.swing.JLabel();
        endereco_box = new javax.swing.JTextField();
        cancelarButton = new javax.swing.JButton();
        cadastroButton = new javax.swing.JButton();
        cnpj_box = new javax.swing.JFormattedTextField();
        contato_box = new javax.swing.JFormattedTextField();
        status_label = new javax.swing.JLabel();
        status_combo_box = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterar  Fornecedor");
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

        cidade_combo_box.setEditable(true);

        estado_combo_box.setEditable(true);
        estado_combo_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estado_combo_boxActionPerformed(evt);
            }
        });

        endereco_label.setText("Endereço:");

        cancelarButton.setText("Cencelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        cadastroButton.setText("Alterar");
        cadastroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroButtonActionPerformed(evt);
            }
        });

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

        status_label.setText("Status:");

        status_combo_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativado", "Desativado" }));

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
                            .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                        .addComponent(estado_label)
                                        .addComponent(endereco_label))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(cadastro_panelLayout.createSequentialGroup()
                                            .addComponent(estado_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(121, 121, 121)
                                            .addComponent(cidade_label)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cidade_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(contato_box, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(endereco_box))))
                            .addGroup(cadastro_panelLayout.createSequentialGroup()
                                .addComponent(status_label)
                                .addGap(18, 18, 18)
                                .addComponent(status_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 149, Short.MAX_VALUE)))
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
                    .addComponent(endereco_label)
                    .addComponent(endereco_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cadastro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status_label)
                    .addComponent(status_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            alterar();
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
            java.util.logging.Logger.getLogger(Alterar_Fornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Alterar_Fornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Alterar_Fornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Alterar_Fornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Alterar_Fornecedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cadastroButton;
    private javax.swing.JPanel cadastro_panel;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JComboBox<String> cidade_combo_box;
    private javax.swing.JLabel cidade_label;
    private javax.swing.JFormattedTextField cnpj_box;
    private javax.swing.JLabel cnpj_label;
    private javax.swing.JFormattedTextField contato_box;
    private javax.swing.JLabel contato_label;
    private javax.swing.JTextField endereco_box;
    private javax.swing.JLabel endereco_label;
    private javax.swing.JComboBox<String> estado_combo_box;
    private javax.swing.JLabel estado_label;
    private javax.swing.JTextField nome_box;
    private javax.swing.JLabel nome_label;
    private javax.swing.JComboBox<String> status_combo_box;
    private javax.swing.JLabel status_label;
    // End of variables declaration//GEN-END:variables
}
