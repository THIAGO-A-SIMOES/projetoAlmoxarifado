package trabalho.pkgfinal;

import Classes.Corredor;
import DatabaseConnection.ConexaoBanco;
import Telas.TelaPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrabalhoFinal {

    public static void main(String[] args) {

       /*  String sql = "insert into alojamento (codCliente) values (null)";
    
     
            for (int i = 0; i < 5; i++) {
                try {

                    Connection conn1 = ConexaoBanco.getConnection();
                    PreparedStatement ps1 = conn1.prepareStatement(sql);
                    ps1.execute();

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(TrabalhoFinal.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        String sql1 = "insert into corredor (codAlojamento) values (?)";

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                try {

                    Connection conn1 = ConexaoBanco.getConnection();
                    PreparedStatement ps1 = conn1.prepareStatement(sql1);
                    ps1.setInt(1, (i+1));
                    ps1.execute();

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(TrabalhoFinal.class.getName()).log(Level.SEVERE, null, ex);

                }

            }

        }

         
        
        List<Corredor> corredores = new ArrayList<>();
        


        
        String sql3 = "select * from corredor";

        try {
            Connection conn = ConexaoBanco.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql3);
            ResultSet rs2 = ps.executeQuery(sql3);
            while (rs2.next()) {

                int codcat = rs2.getInt("codCorredor");
                int situacao = rs2.getInt("codAlojamento");
                Corredor corredor = new Corredor(codcat, situacao);
                corredores.add(corredor);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TrabalhoFinal.class.getName()).log(Level.SEVERE, null, ex);

        }
        String sql2 = "insert into receptaculo (codCorredor, codTipoProduto, quantidade) values (?, null, 0)";
    
        for (Corredor corredore : corredores) {
            for (int i = 0; i < 8; i++) {
                try {

                    Connection conn1 = ConexaoBanco.getConnection();
                    PreparedStatement ps1 = conn1.prepareStatement(sql2);
                    ps1.setInt(1, (corredore.getCodCorredor()));

                    ps1.execute();

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(TrabalhoFinal.class.getName()).log(Level.SEVERE, null, ex);

                }
            }

        }*/
        TelaPrincipal tp = new TelaPrincipal();
        tp.setVisible(true);
    }

}
