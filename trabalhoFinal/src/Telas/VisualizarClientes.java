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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VisualizarClientes extends javax.swing.JFrame {

    public List<Cidade> getListcidade() {
        return listcidade;
    }

    public void setListcidade(List<Cidade> listcidade) {
        this.listcidade = listcidade;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public List<Cliente> getListclientes() {
        return listclientes;
    }

    public void setListclientes(List<Cliente> listclientes) {
        this.listclientes = listclientes;
    }

    public VisualizarClientes() {
        initComponents();
        listclientes = new ArrayList<>();
        descriptions = new ArrayList<>();
        listcidade = new ArrayList<>();
        preencherdados();
    }

    private List<Cliente> listclientes;
    private List<String> descriptions;
    private List<Cidade> listcidade;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pprincipal = new javax.swing.JPanel();
        sptabela = new javax.swing.JScrollPane();
        tbcliente = new javax.swing.JTable();
        pbotoes = new javax.swing.JPanel();
        jbcadastrar = new javax.swing.JButton();
        jbalterar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualizar Clientes");

        tbcliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Cidade", "CNPJ", "Nome", "Contato", "Endereco", "Situacao"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbclienteMouseClicked(evt);
            }
        });
        sptabela.setViewportView(tbcliente);
        if (tbcliente.getColumnModel().getColumnCount() > 0) {
            tbcliente.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbcliente.getColumnModel().getColumn(1).setPreferredWidth(130);
            tbcliente.getColumnModel().getColumn(2).setPreferredWidth(180);
            tbcliente.getColumnModel().getColumn(3).setPreferredWidth(250);
            tbcliente.getColumnModel().getColumn(4).setPreferredWidth(140);
            tbcliente.getColumnModel().getColumn(5).setPreferredWidth(280);
            tbcliente.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        javax.swing.GroupLayout pprincipalLayout = new javax.swing.GroupLayout(pprincipal);
        pprincipal.setLayout(pprincipalLayout);
        pprincipalLayout.setHorizontalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sptabela, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
        );
        pprincipalLayout.setVerticalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pprincipalLayout.createSequentialGroup()
                .addComponent(sptabela, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jbcadastrar.setText("Cadastrar");
        jbcadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcadastrarActionPerformed(evt);
            }
        });

        jbalterar.setText("Alterar");
        jbalterar.setEnabled(false);
        jbalterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbalterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pbotoesLayout = new javax.swing.GroupLayout(pbotoes);
        pbotoes.setLayout(pbotoesLayout);
        pbotoesLayout.setHorizontalGroup(
            pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pbotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbcadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbalterar)
                .addContainerGap())
        );
        pbotoesLayout.setVerticalGroup(
            pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pbotoesLayout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbcadastrar)
                    .addComponent(jbalterar))
                .addGap(36, 36, 36))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pbotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void preencherdados() {
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select * from cliente";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codc = rs.getInt("codCliente");
                int codcid = rs.getInt("codCidade");
                String cnpj = rs.getString("cnpj");
                String nome = rs.getString("nome");
                String contato = rs.getString("contato");
                String endereco = rs.getString("endereco");
                int sit = rs.getInt("situacao");
                Cliente cliente = new Cliente(codc, codcid, cnpj, nome, contato, endereco, sit);
                getListclientes().add(cliente);

            }

            String sql1 = "select * from cidade ";
            PreparedStatement ps1 = conexao.prepareStatement(sql1);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                int codcid = rs1.getInt("codCidade");
                String desc = rs1.getString("descricao");
                int code = rs1.getInt("codEstado");
                int sit = rs1.getInt("situacao");
                Cidade cid = new Cidade(codcid, desc, code, sit);
                getListcidade().add(cid);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) tbcliente.getModel();
        for (Cliente listcliente : listclientes) {
            for (Cidade cidade : listcidade) {
                if (listcliente.getCodCidade() == cidade.getCodCidade()) {
                    if (listcliente.getSituacao() == 1) {

                        model.addRow(new Object[]{listcliente.getCodCliente(), cidade.getDescricao(), listcliente.getCnpj(), listcliente.getNome(), listcliente.getContato(), listcliente.getEndereco(), "Ativado"});

                    } else {
                        model.addRow(new Object[]{listcliente.getCodCliente(), cidade.getDescricao(), listcliente.getCnpj(), listcliente.getNome(), listcliente.getContato(), listcliente.getEndereco(), "Desativado"});

                    }
                }
            }

        }

    }


    private void tbclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbclienteMouseClicked
        if (tbcliente.getSelectedRow() > -1) {
            jbalterar.setEnabled(true);

        }
    }//GEN-LAST:event_tbclienteMouseClicked

    private void jbcadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcadastrarActionPerformed
        Cadastro_Cliente cc = new Cadastro_Cliente();
        cc.setVisible(true);
        dispose();
    }//GEN-LAST:event_jbcadastrarActionPerformed

    private void jbalterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbalterarActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo alterar o item selecionado?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option  == 0){
            
            Cliente ax = new Cliente ();
          
            DefaultTableModel model = (DefaultTableModel) tbcliente.getModel();
            ax.setCodCliente((int) model.getValueAt(tbcliente.getSelectedRow(), 0));
            String description = (String) model.getValueAt(tbcliente.getSelectedRow(), 1);
            int codigo = 0;
            for (Cidade cidade : listcidade) {
                if (description  == cidade.getDescricao()){
                    codigo = cidade.getCodCidade();
                }
            }
            ax.setCodCidade(codigo);
            ax.setCnpj((String) model.getValueAt(tbcliente.getSelectedRow(), 2));
            ax.setNome((String) model.getValueAt(tbcliente.getSelectedRow(), 3));
            ax.setContato((String) model.getValueAt(tbcliente.getSelectedRow(), 4));
            ax.setEndereco((String) model.getValueAt(tbcliente.getSelectedRow(), 5));
            if ((String) model.getValueAt(tbcliente.getSelectedRow(), 6) == "Ativado") {
                ax.setSituacao(1);

            } else {

                ax.setSituacao(0);
            }
            
            Alterar_Cliente vc = new Alterar_Cliente();
            vc.ax(ax);
            vc.setVisible(true);
            dispose();
            
        }
    }//GEN-LAST:event_jbalterarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizarClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbalterar;
    private javax.swing.JButton jbcadastrar;
    private javax.swing.JPanel pbotoes;
    private javax.swing.JPanel pprincipal;
    private javax.swing.JScrollPane sptabela;
    private javax.swing.JTable tbcliente;
    // End of variables declaration//GEN-END:variables
}
