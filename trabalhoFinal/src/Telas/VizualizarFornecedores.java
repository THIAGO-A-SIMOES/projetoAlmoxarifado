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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VizualizarFornecedores extends javax.swing.JFrame {

    public List<String> getListSigla() {
        return listSigla;
    }

    public void setListSigla(List<String> listSigla) {
        this.listSigla = listSigla;
    }

    public List<Cidade> getListCidade() {
        return listCidade;
    }

    public void setListCidade(List<Cidade> listCidade) {
        this.listCidade = listCidade;
    }

    public List<Estado> getListEstado() {
        return listEstado;
    }

    public void setListEstado(List<Estado> listEstado) {
        this.listEstado = listEstado;
    }

    public List<Fornecedor> getListFornecedor() {
        return listFornecedor;
    }

    public void setListFornecedor(List<Fornecedor> listFornecedor) {
        this.listFornecedor = listFornecedor;
    }

    public VizualizarFornecedores() {
        initComponents();

        listFornecedor = new ArrayList<>();
        listCidade = new ArrayList<>();
        listEstado = new ArrayList<>();
        listSigla = new ArrayList<>();
        preencherdados();
    }

    private List<Fornecedor> listFornecedor;
    private List<Estado> listEstado;
    private List<Cidade> listCidade;
    private List<String> listSigla;

    private void preencherdados() {
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql3 = "select * from fornecedor";
            PreparedStatement ps3 = conexao.prepareStatement(sql3);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                int codF = rs3.getInt("codFornecedor");
                int codC = rs3.getInt("codCidade");
                String cnpj = rs3.getString("cnpj");
                String nome = rs3.getString("nome");
                String contato = rs3.getString("contato");
                String endereco = rs3.getString("endereco");
                int sit = rs3.getInt("situacao");
                Fornecedor fornecedor = new Fornecedor(codF, codC, cnpj, nome, contato, endereco, sit);
                getListFornecedor().add(fornecedor);

                String sql4 = "select * from cidade where codCidade = " + codC;
                PreparedStatement ps4 = conexao.prepareStatement(sql4);
                ResultSet rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    int codCidade = rs4.getInt("codCidade");
                    String descricao = rs4.getString("descricao");
                    int codEstado = rs4.getInt("codEstado");
                    int sit1 = rs4.getInt("situacao");
                    Cidade cidade = new Cidade(codCidade, descricao, codEstado, sit1);
                    getListCidade().add(cidade);

                    String sql5 = "select * from estado where codEstado = " + codEstado;
                    PreparedStatement ps5 = conexao.prepareStatement(sql5);
                    ResultSet rs5 = ps5.executeQuery();
                    while (rs5.next()) {
                        int codEsta = rs5.getInt("codEstado");
                        String des = rs5.getString("descricao");
                        String sigla1 = rs5.getString("sigla");
                        Estado estado = new Estado(codEsta, des, sigla1);
                        getListEstado().add(estado);
                    }
                }

                String sql6 = "select e.sigla from estado e inner join cidade c on (e.codEstado = c.codEstado )  inner join fornecedor f on (f.codCidade = c.codCidade)";
                PreparedStatement ps6 = conexao.prepareStatement(sql6);
                ResultSet rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    String sig = rs6.getString("sigla");
                    getListSigla().add(sig);
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) TBFornecedor.getModel();

        int cont = 0;

        for (Fornecedor listFornecedor : listFornecedor) {
           
                    if (listFornecedor.getSituacao() == 1) {
                        model.addRow(new Object[]{listFornecedor.getCodFornecedor(), listFornecedor.getNome(), getListCidade().get(cont).getDescricao(), getListEstado().get(cont).getSigla(), listFornecedor.getCnpj(), listFornecedor.getContato(), listFornecedor.getEndereco(), "Ativado"});
                        cont++;
                    } else {
                        model.addRow(new Object[]{listFornecedor.getCodFornecedor(), listFornecedor.getNome(), getListCidade().get(cont).getDescricao(), getListEstado().get(cont).getSigla(), listFornecedor.getCnpj(), listFornecedor.getContato(), listFornecedor.getEndereco(), "Desativado"});
                        cont++;
                    }
                }
            
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TB_pane = new javax.swing.JPanel();
        TB_spane = new javax.swing.JScrollPane();
        TBFornecedor = new javax.swing.JTable();
        Buttons_pane = new javax.swing.JPanel();
        alterarButton = new javax.swing.JButton();
        cadastrarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualizar Fornecedores");

        TBFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo do Fornecedor", "Nome", "Cidade", "Estado", "CNPJ", "Contato", "Endereço", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TBFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBFornecedorMouseClicked(evt);
            }
        });
        TB_spane.setViewportView(TBFornecedor);
        if (TBFornecedor.getColumnModel().getColumnCount() > 0) {
            TBFornecedor.getColumnModel().getColumn(0).setResizable(false);
            TBFornecedor.getColumnModel().getColumn(1).setResizable(false);
            TBFornecedor.getColumnModel().getColumn(2).setResizable(false);
            TBFornecedor.getColumnModel().getColumn(3).setResizable(false);
            TBFornecedor.getColumnModel().getColumn(4).setResizable(false);
            TBFornecedor.getColumnModel().getColumn(4).setPreferredWidth(100);
            TBFornecedor.getColumnModel().getColumn(5).setResizable(false);
            TBFornecedor.getColumnModel().getColumn(6).setResizable(false);
            TBFornecedor.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout TB_paneLayout = new javax.swing.GroupLayout(TB_pane);
        TB_pane.setLayout(TB_paneLayout);
        TB_paneLayout.setHorizontalGroup(
            TB_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TB_spane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        TB_paneLayout.setVerticalGroup(
            TB_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TB_paneLayout.createSequentialGroup()
                .addComponent(TB_spane, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        alterarButton.setText("Alterar");
        alterarButton.setActionCommand("");
        alterarButton.setEnabled(false);
        alterarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarButtonActionPerformed(evt);
            }
        });

        cadastrarButton.setText("Cadastrar");
        cadastrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Buttons_paneLayout = new javax.swing.GroupLayout(Buttons_pane);
        Buttons_pane.setLayout(Buttons_paneLayout);
        Buttons_paneLayout.setHorizontalGroup(
            Buttons_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Buttons_paneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cadastrarButton)
                .addGap(18, 18, 18)
                .addComponent(alterarButton)
                .addContainerGap())
        );
        Buttons_paneLayout.setVerticalGroup(
            Buttons_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Buttons_paneLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(Buttons_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alterarButton)
                    .addComponent(cadastrarButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TB_pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Buttons_pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TB_pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(Buttons_pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void alterarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarButtonActionPerformed

        int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo alterar este Fronecedor ?", "Confirmação", JOptionPane.OK_CANCEL_OPTION);

        if (option == 0) {
            Fornecedor axl = new Fornecedor();
            DefaultTableModel model = (DefaultTableModel) TBFornecedor.getModel();
            String desAX = (String) model.getValueAt(TBFornecedor.getSelectedRow(), 2);
            int codC = 0;
            for (Cidade cidade : listCidade) {
                if (desAX == cidade.getDescricao()) {
                    codC = cidade.getCodCidade();
                }
            }
            axl.setCodCidade(codC);
            axl.setCnpj((String) model.getValueAt(TBFornecedor.getSelectedRow(), 4));
            axl.setCodFornecedor((int) model.getValueAt(TBFornecedor.getSelectedRow(), 0));
            axl.setContato((String) model.getValueAt(TBFornecedor.getSelectedRow(), 5));
            axl.setEndereco((String) model.getValueAt(TBFornecedor.getSelectedRow(), 6));
            axl.setNome((String) model.getValueAt(TBFornecedor.getSelectedRow(), 1));
            if ((String) model.getValueAt(TBFornecedor.getSelectedRow(), 7) == "Ativado") {
                axl.setSituacao(1);
            } else {
                axl.setSituacao(0);
            }
            Alterar_Fornecedor af = new Alterar_Fornecedor();
            dispose();
            af.setVisible(true);
            af.ax(axl);
        }
    }//GEN-LAST:event_alterarButtonActionPerformed

    private void cadastrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarButtonActionPerformed
        Cadastros_Fornecedor cf = new Cadastros_Fornecedor();
        cf.setVisible(true);
        dispose();
    }//GEN-LAST:event_cadastrarButtonActionPerformed

    private void TBFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBFornecedorMouseClicked
        alterarButton.setEnabled(true);
    }//GEN-LAST:event_TBFornecedorMouseClicked

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
            java.util.logging.Logger.getLogger(VizualizarFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VizualizarFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VizualizarFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VizualizarFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VizualizarFornecedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Buttons_pane;
    private javax.swing.JTable TBFornecedor;
    private javax.swing.JPanel TB_pane;
    private javax.swing.JScrollPane TB_spane;
    private javax.swing.JButton alterarButton;
    private javax.swing.JButton cadastrarButton;
    // End of variables declaration//GEN-END:variables
}
