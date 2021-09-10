package Telas;

import Classes.Cidade;
import Classes.Estado;
import DatabaseConnection.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class VizualizarCidades extends javax.swing.JFrame {


    public List<Estado> getListEstado() {
        return listEstado;
    }


    public void setListEstado(List<Estado> listEstado) {
        this.listEstado = listEstado;
    }

    public List<Cidade> getListCidade() {
        return listCidade;
    }

    public void setListCidade(List<Cidade> listCidade) {
        this.listCidade = listCidade;
    }


    public VizualizarCidades() {
        initComponents();
        
        listCidade = new ArrayList<>();
        listEstado = new ArrayList<>();
        preencherdados();
        
    }
    
    private List<Cidade> listCidade;
    private List<Estado> listEstado;
    
    private void preencherdados() {

        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select * from cidade";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codE = rs.getInt("codEstado");
                String desc = rs.getString("descricao");
                int codC = rs.getInt("codCidade");
                int sit = rs.getInt("situacao");
                Cidade cidade = new Cidade(codC, desc, codE, sit);
                getListCidade().add(cidade);

            }
            
            String sql2 = "select * from estado";
            PreparedStatement ps2 = conexao.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                int cod = rs2.getInt("codEstado");
                String desc = rs2.getString("descricao");
                String sigla = rs2.getString("sigla");
                Estado estado = new Estado(cod, desc, sigla);
                getListEstado().add(estado);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) getTbCidade().getModel();
        for (Cidade listCidade : listCidade) {
            for(Estado Estado : getListEstado()){
                if(listCidade.getCodEstado() == Estado.getCodEstado()){
                    String sigla = Estado.getSigla();
                    if(listCidade.getSituacao() == 1){
                        model.addRow(new Object[]{listCidade.getCodCidade(), listCidade.getDescricao(), sigla, "Ativada"});
                    }else{
                        model.addRow(new Object[]{listCidade.getCodCidade(), listCidade.getDescricao(), sigla, "Desativada"});
                    }
                }
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TbCidadesPanel = new javax.swing.JPanel();
        ScCidade = new javax.swing.JScrollPane();
        TbCidade = new javax.swing.JTable();
        pButtons = new javax.swing.JPanel();
        alterarButton = new javax.swing.JButton();
        cadastrarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualizar Cidades");

        TbCidade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo da Cidade", "Descrição", "Estado", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TbCidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbCidadeMouseClicked(evt);
            }
        });
        ScCidade.setViewportView(TbCidade);
        if (TbCidade.getColumnModel().getColumnCount() > 0) {
            TbCidade.getColumnModel().getColumn(0).setResizable(false);
            TbCidade.getColumnModel().getColumn(1).setResizable(false);
            TbCidade.getColumnModel().getColumn(2).setResizable(false);
            TbCidade.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout TbCidadesPanelLayout = new javax.swing.GroupLayout(TbCidadesPanel);
        TbCidadesPanel.setLayout(TbCidadesPanelLayout);
        TbCidadesPanelLayout.setHorizontalGroup(
            TbCidadesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        TbCidadesPanelLayout.setVerticalGroup(
            TbCidadesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
        );

        alterarButton.setText("Alterar");
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

        javax.swing.GroupLayout pButtonsLayout = new javax.swing.GroupLayout(pButtons);
        pButtons.setLayout(pButtonsLayout);
        pButtonsLayout.setHorizontalGroup(
            pButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pButtonsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cadastrarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alterarButton)
                .addContainerGap())
        );
        pButtonsLayout.setVerticalGroup(
            pButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pButtonsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alterarButton)
                    .addComponent(cadastrarButton))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TbCidadesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TbCidadesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cadastrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarButtonActionPerformed
        Cadastro_Cidade cc = new Cadastro_Cidade();
        hide();
        cc.setVisible(true);
    }//GEN-LAST:event_cadastrarButtonActionPerformed

    private void alterarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarButtonActionPerformed
       
       int option  = JOptionPane.showConfirmDialog(this, "Deseja mesmo alterar esta Cidade ?", "Confirmação", JOptionPane.OK_CANCEL_OPTION);
       
       if (option == 0){
            Cidade ax1 = new Cidade();
            DefaultTableModel model = (DefaultTableModel) getTbCidade().getModel();
            ax1.setCodCidade((int) model.getValueAt(getTbCidade().getSelectedRow(), 0));
            ax1.setDescricao((String) model.getValueAt(getTbCidade().getSelectedRow(), 1));
            
            String sigla = (String) model.getValueAt(getTbCidade().getSelectedRow(), 2);
            int status = 0;
            if(model.getValueAt(getTbCidade().getSelectedRow(), 3) == "Ativada"){
                status = 1;
            }else{
                status = 0;
            }
            int codigo = 0;
            
            for (Estado estado : listEstado) {
               if(estado.getSigla() == sigla){
                   codigo = estado.getCodEstado();
               }
           }
            
           ax1.setCodEstado(codigo);
           ax1.setSituacao(status);
           
           Alterar_Cidade ac = new Alterar_Cidade();
           hide();
           ac.ax(ax1);
           ac.setVisible(true);
       }
    }//GEN-LAST:event_alterarButtonActionPerformed

    private void TbCidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbCidadeMouseClicked
         if(getTbCidade().getSelectedRow() > -1){
             getAlterarButton().setEnabled(true);           
           }
    }//GEN-LAST:event_TbCidadeMouseClicked


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
            java.util.logging.Logger.getLogger(VizualizarCidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VizualizarCidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VizualizarCidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VizualizarCidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VizualizarCidades().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScCidade;
    private javax.swing.JTable TbCidade;
    private javax.swing.JPanel TbCidadesPanel;
    private javax.swing.JButton alterarButton;
    private javax.swing.JButton cadastrarButton;
    private javax.swing.JPanel pButtons;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the ScCidade
     */
    public javax.swing.JScrollPane getScCidade() {
        return ScCidade;
    }

    /**
     * @param ScCidade the ScCidade to set
     */
    public void setScCidade(javax.swing.JScrollPane ScCidade) {
        this.ScCidade = ScCidade;
    }

    /**
     * @return the TbCidade
     */
    public javax.swing.JTable getTbCidade() {
        return TbCidade;
    }

    /**
     * @param TbCidade the TbCidade to set
     */
    public void setTbCidade(javax.swing.JTable TbCidade) {
        this.TbCidade = TbCidade;
    }

    /**
     * @return the TbCidadesPanel
     */
    public javax.swing.JPanel getTbCidadesPanel() {
        return TbCidadesPanel;
    }

    /**
     * @param TbCidadesPanel the TbCidadesPanel to set
     */
    public void setTbCidadesPanel(javax.swing.JPanel TbCidadesPanel) {
        this.TbCidadesPanel = TbCidadesPanel;
    }

    /**
     * @return the alterarButton
     */
    public javax.swing.JButton getAlterarButton() {
        return alterarButton;
    }

    /**
     * @param alterarButton the alterarButton to set
     */
    public void setAlterarButton(javax.swing.JButton alterarButton) {
        this.alterarButton = alterarButton;
    }

    /**
     * @return the cadastrarButton
     */
    public javax.swing.JButton getCadastrarButton() {
        return cadastrarButton;
    }

    /**
     * @param cadastrarButton the cadastrarButton to set
     */
    public void setCadastrarButton(javax.swing.JButton cadastrarButton) {
        this.cadastrarButton = cadastrarButton;
    }

    /**
     * @return the pButtons
     */
    public javax.swing.JPanel getpButtons() {
        return pButtons;
    }

    /**
     * @param pButtons the pButtons to set
     */
    public void setpButtons(javax.swing.JPanel pButtons) {
        this.pButtons = pButtons;
    }
}
