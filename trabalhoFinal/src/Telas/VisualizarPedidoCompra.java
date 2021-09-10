package Telas;

import Classes.Categoria;
import Classes.PedidoCompra;
import DatabaseConnection.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VisualizarPedidoCompra extends javax.swing.JFrame {

    public List<PedidoCompra> getListpedidocompra() {
        return listpedidocompra;
    }

    public void setListpedidocompra(List<PedidoCompra> listpedidocompra) {
        this.listpedidocompra = listpedidocompra;
    }

    public VisualizarPedidoCompra() {
        initComponents();
        listpedidocompra = new ArrayList<>();
        preencherdados();
    }

    private List<PedidoCompra> listpedidocompra;

    private void preencherdados() {
        try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql = "select * from pedidoCompra";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int codp = rs.getInt("codPedidoCompra");
                float val = rs.getFloat("valTotal");
                int sit = rs.getInt("situacao");
                PedidoCompra pc = new PedidoCompra(codp, val, sit);
                getListpedidocompra().add(pc);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tbpedidocompra.getModel();
        for (PedidoCompra pedidoCompra : listpedidocompra) {
            if (pedidoCompra.getSituacao() == 0) {

                model.addRow(new Object[]{pedidoCompra.getCodPedidoCompra(), pedidoCompra.getValTotal(), "Finalizado"});

            } else {
                model.addRow(new Object[]{pedidoCompra.getCodPedidoCompra(), pedidoCompra.getValTotal(), "Em andamento"});
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pprincipal = new javax.swing.JPanel();
        sptabela = new javax.swing.JScrollPane();
        tbpedidocompra = new javax.swing.JTable();
        pbotoes = new javax.swing.JPanel();
        jbregistrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualizar Pedido de Compra");

        tbpedidocompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Valor total", "Situacao"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbpedidocompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpedidocompraMouseClicked(evt);
            }
        });
        sptabela.setViewportView(tbpedidocompra);
        if (tbpedidocompra.getColumnModel().getColumnCount() > 0) {
            tbpedidocompra.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbpedidocompra.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbpedidocompra.getColumnModel().getColumn(2).setPreferredWidth(200);
        }

        javax.swing.GroupLayout pprincipalLayout = new javax.swing.GroupLayout(pprincipal);
        pprincipal.setLayout(pprincipalLayout);
        pprincipalLayout.setHorizontalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sptabela, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        pprincipalLayout.setVerticalGroup(
            pprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pprincipalLayout.createSequentialGroup()
                .addComponent(sptabela, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jbregistrar.setText("Registrar");
        jbregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbregistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pbotoesLayout = new javax.swing.GroupLayout(pbotoes);
        pbotoes.setLayout(pbotoesLayout);
        pbotoesLayout.setHorizontalGroup(
            pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pbotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbregistrar)
                .addContainerGap())
        );
        pbotoesLayout.setVerticalGroup(
            pbotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pbotoesLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(jbregistrar)
                .addGap(30, 30, 30))
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

    private void tbpedidocompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpedidocompraMouseClicked

    }//GEN-LAST:event_tbpedidocompraMouseClicked

    private void jbregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbregistrarActionPerformed
        Registrar_PedidoCompra rpc = new Registrar_PedidoCompra();
        rpc.setVisible(true);
        dispose();
    }//GEN-LAST:event_jbregistrarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizarPedidoCompra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbregistrar;
    private javax.swing.JPanel pbotoes;
    private javax.swing.JPanel pprincipal;
    private javax.swing.JScrollPane sptabela;
    private javax.swing.JTable tbpedidocompra;
    // End of variables declaration//GEN-END:variables
}
