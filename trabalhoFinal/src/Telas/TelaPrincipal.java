
package Telas;

import Classes.PedidoCompra;
import DatabaseConnection.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class TelaPrincipal extends javax.swing.JFrame {

   
    public TelaPrincipal() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmvinculacao = new javax.swing.JMenuBar();
        jmcadastrar = new javax.swing.JMenu();
        micidade = new javax.swing.JMenuItem();
        micategoria = new javax.swing.JMenuItem();
        miforcecedor = new javax.swing.JMenuItem();
        mitipoproduto = new javax.swing.JMenuItem();
        miclientes = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jmpedidos = new javax.swing.JMenu();
        mipedidocompra = new javax.swing.JMenuItem();
        miPedidocliente = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mialojamentocliente = new javax.swing.JMenuItem();
        jmefetivacao = new javax.swing.JMenu();
        jmepedidocompra = new javax.swing.JMenuItem();
        mieordemcompra = new javax.swing.JMenuItem();
        jmeepedidocliente = new javax.swing.JMenuItem();
        jmcontrole = new javax.swing.JMenu();
        miquantprodutos = new javax.swing.JMenuItem();
        miquantreceptaculos = new javax.swing.JMenuItem();
        jmrelatorio = new javax.swing.JMenu();
        mialojamentosporcliente = new javax.swing.JMenuItem();
        miprodportipo = new javax.swing.JMenuItem();
        mivgpc = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela  Principal");

        jmvinculacao.setToolTipText("");

        jmcadastrar.setText("Cadastrar");

        micidade.setText("Cidade");
        micidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                micidadeActionPerformed(evt);
            }
        });
        jmcadastrar.add(micidade);

        micategoria.setText("Categoria");
        micategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                micategoriaActionPerformed(evt);
            }
        });
        jmcadastrar.add(micategoria);

        miforcecedor.setText("Fornecedor");
        miforcecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miforcecedorActionPerformed(evt);
            }
        });
        jmcadastrar.add(miforcecedor);

        mitipoproduto.setText("Tipo de Produto");
        mitipoproduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitipoprodutoActionPerformed(evt);
            }
        });
        jmcadastrar.add(mitipoproduto);

        miclientes.setText("Cliente");
        miclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miclientesActionPerformed(evt);
            }
        });
        jmcadastrar.add(miclientes);

        jMenuItem1.setText("Ordem de Compra");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jmcadastrar.add(jMenuItem1);

        jmvinculacao.add(jmcadastrar);

        jmpedidos.setText("Pedidos");

        mipedidocompra.setText("Pedido de Compra");
        mipedidocompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mipedidocompraActionPerformed(evt);
            }
        });
        jmpedidos.add(mipedidocompra);

        miPedidocliente.setText("Pedido do Cliente");
        miPedidocliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPedidoclienteActionPerformed(evt);
            }
        });
        jmpedidos.add(miPedidocliente);

        jmvinculacao.add(jmpedidos);

        jMenu1.setText("Vinculação");

        mialojamentocliente.setText("Alojamento/Cliente");
        mialojamentocliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mialojamentoclienteActionPerformed(evt);
            }
        });
        jMenu1.add(mialojamentocliente);

        jmvinculacao.add(jMenu1);

        jmefetivacao.setText("Efetivação");

        jmepedidocompra.setText("Pedido de Compra");
        jmepedidocompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmepedidocompraActionPerformed(evt);
            }
        });
        jmefetivacao.add(jmepedidocompra);

        mieordemcompra.setText("Ordem de compra");
        mieordemcompra.setToolTipText("");
        mieordemcompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mieordemcompraActionPerformed(evt);
            }
        });
        jmefetivacao.add(mieordemcompra);

        jmeepedidocliente.setText("Pedido do Cliente");
        jmeepedidocliente.setToolTipText("");
        jmeepedidocliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmeepedidoclienteActionPerformed(evt);
            }
        });
        jmefetivacao.add(jmeepedidocliente);

        jmvinculacao.add(jmefetivacao);

        jmcontrole.setText("Controle");

        miquantprodutos.setText("Quantidade de Produtos");
        miquantprodutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miquantprodutosActionPerformed(evt);
            }
        });
        jmcontrole.add(miquantprodutos);

        miquantreceptaculos.setText("Quantidade de Receptaculos");
        miquantreceptaculos.setToolTipText("");
        miquantreceptaculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miquantreceptaculosActionPerformed(evt);
            }
        });
        jmcontrole.add(miquantreceptaculos);

        jmvinculacao.add(jmcontrole);

        jmrelatorio.setText("Relatorios");

        mialojamentosporcliente.setText("Alojmentos  por Cliente");
        mialojamentosporcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mialojamentosporclienteActionPerformed(evt);
            }
        });
        jmrelatorio.add(mialojamentosporcliente);

        miprodportipo.setText("Quantidade de produtos por tipo");
        miprodportipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miprodportipoActionPerformed(evt);
            }
        });
        jmrelatorio.add(miprodportipo);

        mivgpc.setText("Valor gasto por cliente");
        mivgpc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mivgpcActionPerformed(evt);
            }
        });
        jmrelatorio.add(mivgpc);

        jmvinculacao.add(jmrelatorio);

        setJMenuBar(jmvinculacao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 699, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void micidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_micidadeActionPerformed
        VizualizarCidades vc = new VizualizarCidades();
        vc.setVisible(true);
    }//GEN-LAST:event_micidadeActionPerformed

    private void micategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_micategoriaActionPerformed
        VizualizarCategorias  vc = new VizualizarCategorias();
        vc.setVisible(true);
    }//GEN-LAST:event_micategoriaActionPerformed

    private void miforcecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miforcecedorActionPerformed
        VizualizarFornecedores vf = new VizualizarFornecedores();
        vf.setVisible(true);
    }//GEN-LAST:event_miforcecedorActionPerformed

    private void mitipoprodutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitipoprodutoActionPerformed

        VisualizarTipoProd vtp = new VisualizarTipoProd();
        vtp.setVisible(true);

    }//GEN-LAST:event_mitipoprodutoActionPerformed

    private void miclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miclientesActionPerformed
        VisualizarClientes vc = new VisualizarClientes();
        vc.setVisible(true);
        
    }//GEN-LAST:event_miclientesActionPerformed

    private void miPedidoclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPedidoclienteActionPerformed
      VisualizarPedidoCliente cpc = new VisualizarPedidoCliente();
      cpc.setVisible(true);
    }//GEN-LAST:event_miPedidoclienteActionPerformed

    private void mipedidocompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mipedidocompraActionPerformed
          VisualizarPedidoCompra vpc = new VisualizarPedidoCompra();
          vpc.setVisible(true);
    }//GEN-LAST:event_mipedidocompraActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        VisualizarOrdemDeCompra voc = new VisualizarOrdemDeCompra();
        voc.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void mialojamentoclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mialojamentoclienteActionPerformed
        VisualizarAlojamento va = new VisualizarAlojamento();
        va.setVisible(true);
    }//GEN-LAST:event_mialojamentoclienteActionPerformed

    private void mieordemcompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mieordemcompraActionPerformed
        EfetivarOrdemDeCompra eoc  = new EfetivarOrdemDeCompra();
        eoc.setVisible(true);
    }//GEN-LAST:event_mieordemcompraActionPerformed

    private void jmepedidocompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmepedidocompraActionPerformed
       EfetivarPedidoCompra epc = new EfetivarPedidoCompra ();
       epc.setVisible(true);

    }//GEN-LAST:event_jmepedidocompraActionPerformed

    private void jmeepedidoclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmeepedidoclienteActionPerformed
        EfetivarPedidoCliente epc = new EfetivarPedidoCliente();
        epc.setVisible(true);
    }//GEN-LAST:event_jmeepedidoclienteActionPerformed

    private void miquantreceptaculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miquantreceptaculosActionPerformed
           try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql3 = "select count(*) as quantidade from receptaculo";
            PreparedStatement ps3 = conexao.prepareStatement(sql3);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
         
                JOptionPane.showMessageDialog(this, "O almoxarifado possui "+ (rs3.getInt("quantidade")) + " receptaculos", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            
            }

        } catch (ClassNotFoundException | SQLException ex) {
           JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
    }//GEN-LAST:event_miquantreceptaculosActionPerformed

    private void miquantprodutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miquantprodutosActionPerformed
 try {
            Connection conexao = ConexaoBanco.getConnection();
            String sql3 = "select sum(quantidade) as quantidade from receptaculo";
            PreparedStatement ps3 = conexao.prepareStatement(sql3);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
         
                JOptionPane.showMessageDialog(this, "O almoxarifado possui "+ (rs3.getInt("quantidade")) + " produtos", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            
            }

        } catch (ClassNotFoundException | SQLException ex) {
           JOptionPane.showMessageDialog(this, "Erro ao obter  os dados do banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }    }//GEN-LAST:event_miquantprodutosActionPerformed

    private void mialojamentosporclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mialojamentosporclienteActionPerformed
        Tela_Relatorios_Cliente trc = new Tela_Relatorios_Cliente();
        trc.setVisible(true);
    }//GEN-LAST:event_mialojamentosporclienteActionPerformed

    private void miprodportipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miprodportipoActionPerformed
        Tela_Quantidadedeprodutosportipo  tq = new Tela_Quantidadedeprodutosportipo();
        tq.setVisible(true);
    }//GEN-LAST:event_miprodportipoActionPerformed

    private void mivgpcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mivgpcActionPerformed

        Tela_Valor_Gasto tvg  = new Tela_Valor_Gasto();
        tvg.setVisible(true);

    }//GEN-LAST:event_mivgpcActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu jmcadastrar;
    private javax.swing.JMenu jmcontrole;
    private javax.swing.JMenuItem jmeepedidocliente;
    private javax.swing.JMenu jmefetivacao;
    private javax.swing.JMenuItem jmepedidocompra;
    private javax.swing.JMenu jmpedidos;
    private javax.swing.JMenu jmrelatorio;
    private javax.swing.JMenuBar jmvinculacao;
    private javax.swing.JMenuItem miPedidocliente;
    private javax.swing.JMenuItem mialojamentocliente;
    private javax.swing.JMenuItem mialojamentosporcliente;
    private javax.swing.JMenuItem micategoria;
    private javax.swing.JMenuItem micidade;
    private javax.swing.JMenuItem miclientes;
    private javax.swing.JMenuItem mieordemcompra;
    private javax.swing.JMenuItem miforcecedor;
    private javax.swing.JMenuItem mipedidocompra;
    private javax.swing.JMenuItem miprodportipo;
    private javax.swing.JMenuItem miquantprodutos;
    private javax.swing.JMenuItem miquantreceptaculos;
    private javax.swing.JMenuItem mitipoproduto;
    private javax.swing.JMenuItem mivgpc;
    // End of variables declaration//GEN-END:variables
}
