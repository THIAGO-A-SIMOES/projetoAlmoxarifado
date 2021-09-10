package Telas;

import Classes.Categoria;
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

public class Cadastro_TipoProd extends javax.swing.JFrame {

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    private List<Categoria> categorias;

    public Cadastro_TipoProd() {
        initComponents();
        categorias = new ArrayList<>();
        preenchercategorias();
    }

    private void preenchercategorias() {
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select * from categoria";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int status = rs.getInt("situacao");
                int codcat = rs.getInt("codCategoria");
                String desc = rs.getString("descricao");
                Categoria ax =  new Categoria (codcat, desc,  status);
                categorias.add(ax);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Categoria categoria : categorias) {

            if (categoria.getSituacao() == 1){
                cbcategoria.addItem(categoria.getDescricao());
                
            }

        }

    }

    private boolean validardados() {

        boolean validar = true;

        if (cbcategoria.getSelectedIndex() < 0) {

            validar = false;
        } else if (tfdescricao.getText().isEmpty()) {
            validar = false;

        } else if (tfqtde.getText().isEmpty()) {
            validar = false;

        } else if (ftfupc.getText().equals("            ")) {
            validar = false;
            
        }

        if (!validar) {
            JOptionPane.showMessageDialog(this, "Favor preencher todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);

        }
        return validar;

    }

    private void cadastro() {

       

            TipoProduto tipoprod = new TipoProduto();
            tipoprod.setDescricao(tfdescricao.getText());
            tipoprod.setSituacao(1);
            tipoprod.setQtdeMaxRecept(Integer.valueOf(tfqtde.getText()));
            int codigo  = 0;
            for (Categoria categoria : categorias) {
                
                if (categoria.getDescricao() == cbcategoria.getSelectedItem()){
                    
                    codigo  = categoria.getCodCategoria();
                }
                
            }
            tipoprod.setCodCategoria(codigo);
            tipoprod.setUPC(ftfupc.getText());
            String sql = "insert into tipoproduto (descricao,qtdeMaxRecpt, UPC,codCategoria, situacao)";
            sql += "values (?,? ,? ,?, ?)";

            try {
                Connection conn = ConexaoBanco.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, tipoprod.getDescricao());
                ps.setInt(2, tipoprod.getQtdeMaxRecept());
                ps.setString(3, tipoprod.getUPC());
                ps.setInt(4, tipoprod.getCodCategoria());
                ps.setInt(5, tipoprod.getSituacao());
                ps.execute();

                JOptionPane.showMessageDialog(this, "Tipo de produto cadastrado com sucesso!", "Confirmação de cadastro", JOptionPane.INFORMATION_MESSAGE);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Cadastro_TipoProd.class.getName()).log(Level.SEVERE, null, ex);
            }
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pprincipal = new javax.swing.JPanel();
        ldescricao = new javax.swing.JLabel();
        tfdescricao = new javax.swing.JTextField();
        lqtde = new javax.swing.JLabel();
        tfqtde = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        ftfupc = new javax.swing.JFormattedTextField();
        lcategoria = new javax.swing.JLabel();
        cbcategoria = new javax.swing.JComboBox<>();
        pbotoes = new javax.swing.JPanel();
        jbconfirmar = new javax.swing.JButton();
        jbcancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro  Tipo de Produto");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        pprincipal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro Tpo  de Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        ldescricao.setText("Descrição: ");
        ldescricao.setToolTipText("");

        lqtde.setText("Quantidade em um receptáculo: ");

        jLabel1.setText("UPC: ");

        try {
            ftfupc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("############")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lcategoria.setText("Categoria:  ");

        javax.swing.GroupLayout pprincipalLayout = new javax.swing.GroupLayout(pprincipal);
        pprincipal.setLayout(pprincipalLayout);
        pprincipalLayout.setHorizontalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pprincipalLayout.createSequentialGroup()
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pprincipalLayout.createSequentialGroup()
                        .addComponent(ldescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pprincipalLayout.createSequentialGroup()
                        .addComponent(lqtde)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfqtde))
                    .addGroup(pprincipalLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfupc))
                    .addGroup(pprincipalLayout.createSequentialGroup()
                        .addComponent(lcategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 331, Short.MAX_VALUE))
        );
        pprincipalLayout.setVerticalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pprincipalLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ldescricao)
                    .addComponent(tfdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lqtde)
                    .addComponent(tfqtde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ftfupc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lcategoria)
                    .addComponent(cbcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbconfirmar.setText("Confirmar");
        jbconfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbconfirmarActionPerformed(evt);
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
                .addComponent(jbconfirmar)
                .addGap(18, 18, 18)
                .addComponent(jbcancelar)
                .addGap(20, 20, 20))
        );
        pbotoesLayout.setVerticalGroup(
            pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pbotoesLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbconfirmar)
                    .addComponent(jbcancelar))
                .addContainerGap())
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
                .addComponent(pbotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        VisualizarTipoProd vtp = new VisualizarTipoProd();
        vtp.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void jbcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcancelarActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair da tela de cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option == 0) {

            dispose();
        }


    }//GEN-LAST:event_jbcancelarActionPerformed

    private void jbconfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbconfirmarActionPerformed
        
        if (validardados()){
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
            java.util.logging.Logger.getLogger(Cadastro_TipoProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadastro_TipoProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadastro_TipoProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadastro_TipoProd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cadastro_TipoProd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbcategoria;
    private javax.swing.JFormattedTextField ftfupc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbcancelar;
    private javax.swing.JButton jbconfirmar;
    private javax.swing.JLabel lcategoria;
    private javax.swing.JLabel ldescricao;
    private javax.swing.JLabel lqtde;
    private javax.swing.JPanel pbotoes;
    private javax.swing.JPanel pprincipal;
    private javax.swing.JTextField tfdescricao;
    private javax.swing.JTextField tfqtde;
    // End of variables declaration//GEN-END:variables
}
