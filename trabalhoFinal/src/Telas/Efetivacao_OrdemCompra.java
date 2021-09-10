package Telas;

import Classes.OrdemCompra;
import Classes.Receptaculo;
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

public class Efetivacao_OrdemCompra extends javax.swing.JFrame {

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the listCODIGODOCORREDOR
     */
    public List<Integer> getListCODIGODOCORREDOR() {
        return listCODIGODOCORREDOR;
    }

    /**
     * @param listCODIGODOCORREDOR the listCODIGODOCORREDOR to set
     */
    public void setListCODIGODOCORREDOR(List<Integer> listCODIGODOCORREDOR) {
        this.listCODIGODOCORREDOR = listCODIGODOCORREDOR;
    }

    /**
     * @return the codf
     */
    public int getCodf() {
        return codf;
    }

    /**
     * @param codf the codf to set
     */
    public void setCodf(int codf) {
        this.codf = codf;
    }

    /**
     * @return the codp
     */
    public int getCodp() {
        return codp;
    }

    /**
     * @param codp the codp to set
     */
    public void setCodp(int codp) {
        this.codp = codp;
    }

    /**
     * @return the listCodAlojamento
     */
    public List<Integer> getListCodAlojamento() {
        return listCodAlojamento;
    }

    /**
     * @param listCodAlojamento the listCodAlojamento to set
     */
    public void setListCodAlojamento(List<Integer> listCodAlojamento) {
        this.listCodAlojamento = listCodAlojamento;
    }

    /**
     * @return the listRECEPTACULO
     */
    public List<Receptaculo> getListRECEPTACULO() {
        return listRECEPTACULO;
    }

    /**
     * @param listRECEPTACULO the listRECEPTACULO to set
     */
    public void setListRECEPTACULO(List<Receptaculo> listRECEPTACULO) {
        this.listRECEPTACULO = listRECEPTACULO;
    }

    /**
     * @return the quantidadeMaxTipoProduto
     */
    public int getQuantidadeMaxTipoProduto() {
        return quantidadeMaxTipoProduto;
    }

    /**
     * @param quantidadeMaxTipoProduto the quantidadeMaxTipoProduto to set
     */
    public void setQuantidadeMaxTipoProduto(int quantidadeMaxTipoProduto) {
        this.quantidadeMaxTipoProduto = quantidadeMaxTipoProduto;
    }

    /**
     * @return the listQuantidadeArmazenada
     */
    public List<Integer> getListQuantidadeArmazenada() {
        return listQuantidadeArmazenada;
    }

    /**
     * @param listQuantidadeArmazenada the listQuantidadeArmazenada to set
     */
    public void setListQuantidadeArmazenada(List<Integer> listQuantidadeArmazenada) {
        this.listQuantidadeArmazenada = listQuantidadeArmazenada;
    }

    /**
     * @return the listCodRec
     */
    public List<Integer> getListCodRec() {
        return listCodRec;
    }

    /**
     * @param listCodRec the listCodRec to set
     */
    public void setListCodRec(List<Integer> listCodRec) {
        this.listCodRec = listCodRec;
    }

    public List<Integer> getListCorredores2() {
        return listCorredores2;
    }

    public void setListCorredores2(List<Integer> listCorredores2) {
        this.listCorredores2 = listCorredores2;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

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

    public Efetivacao_OrdemCompra() {
        initComponents();

        listFornecedores = new ArrayList<>();
        listCliente = new ArrayList<>();
        listProduto = new ArrayList<>();
        listCorredores2 = new ArrayList<>();
        listCodRec = new ArrayList<>();
        listQuantidadeArmazenada = new ArrayList<>();
        listRECEPTACULO = new ArrayList<>();
        listCodAlojamento = new ArrayList<>();
        listCODIGODOCORREDOR = new ArrayList<>();
        preencherCampos();
    }

    private List<String> listFornecedores;
    private List<String> listCliente;
    private List<String> listProduto;
    private List<Integer> listCorredores2;
    private List<Integer> listCodRec;
    private List<Integer> listQuantidadeArmazenada;
    private List<Receptaculo> listRECEPTACULO;
    private int codigo;
    private List<Integer> listCodAlojamento;
    private int quantidadeMaxTipoProduto;
    private List<Integer> listCODIGODOCORREDOR;

    private int codf;
    private int codp;
    private int count;

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

    private void EfetivarOrdem() {

        int codP = 0;
        int codF = 0;
        int codC = 0;

        if (getCount() == 0) {
            JOptionPane.showMessageDialog(this, "Não há alojamentos vinculado a este cliente", "Erro", JOptionPane.ERROR_MESSAGE);

        } else {
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

            int quantidade_para_armazenar = Integer.valueOf(rebebido_box.getText());
            int cont = 0;

            for (Integer integer : listCodRec) {
                if (listQuantidadeArmazenada.get(cont) < getQuantidadeMaxTipoProduto()) {
                    int ax = getQuantidadeMaxTipoProduto() - listQuantidadeArmazenada.get(cont);
                    if (ax < quantidade_para_armazenar) {
                        try {
                            Connection conn = ConexaoBanco.getConnection();
                            String sql = "update receptaculo set quantidade = ? where codReceptaculo = " + integer;
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.setInt(1, listQuantidadeArmazenada.get(cont) + ax);
                            JOptionPane.showMessageDialog(this, ax + " produtos foram estocados no receptaculo " + integer + " - " + getListCODIGODOCORREDOR().get(cont), "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                            ps.execute();

                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(Efetivacao_OrdemCompra.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        quantidade_para_armazenar = quantidade_para_armazenar - ax;
                    } else if (ax >= quantidade_para_armazenar) {
                        try {
                            Connection conn = ConexaoBanco.getConnection();
                            String sql = "update receptaculo set quantidade = ? where codReceptaculo = " + integer;
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.setInt(1, listQuantidadeArmazenada.get(cont) + quantidade_para_armazenar);
                            JOptionPane.showMessageDialog(this, quantidade_para_armazenar + " produtos foram estocados no receptaculo " + integer + " - " + getListCODIGODOCORREDOR().get(cont), "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                            ps.execute();

                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(Efetivacao_OrdemCompra.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        quantidade_para_armazenar = 0;
                    }
                }
                cont++;
            }

            if (quantidade_para_armazenar != 0) {
                for (Receptaculo receptaculo : listRECEPTACULO) {
                    if (quantidade_para_armazenar != 0) {
                        if (receptaculo.getQuantidade() == 0) {
                            int ax = receptaculo.getQuantidade();
                            ax = getQuantidadeMaxTipoProduto() - ax;
                            if (ax < quantidade_para_armazenar) {
                                try {
                                    Connection conn = ConexaoBanco.getConnection();
                                    String sql = "update receptaculo set quantidade = ?, codTipoProduto = " + codP + " where codReceptaculo = " + String.valueOf(receptaculo.getCodReceptaculo());
                                    PreparedStatement ps = conn.prepareStatement(sql);
                                    ps.setInt(1, receptaculo.getQuantidade() + ax);
                                    JOptionPane.showMessageDialog(this, ax + " produtos foram estocados no receptaculo " + receptaculo.getCodReceptaculo() + " - " + receptaculo.getCodCorredor(), "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                                    ps.execute();
                                } catch (ClassNotFoundException | SQLException ex) {
                                    Logger.getLogger(Efetivacao_OrdemCompra.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                quantidade_para_armazenar = quantidade_para_armazenar - ax;
                            } else if (ax >= quantidade_para_armazenar) {
                                try {
                                    Connection conn = ConexaoBanco.getConnection();
                                    String sql = "update receptaculo set quantidade = ?, codTipoProduto = " + codP + " where codReceptaculo = " + String.valueOf(receptaculo.getCodReceptaculo());
                                    PreparedStatement ps = conn.prepareStatement(sql);
                                    ps.setInt(1, receptaculo.getQuantidade() + quantidade_para_armazenar);
                                    JOptionPane.showMessageDialog(this, quantidade_para_armazenar + " produtos foram estocados no receptaculo " + receptaculo.getCodReceptaculo() + " - " + receptaculo.getCodCorredor(), "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                                    ps.execute();
                                } catch (ClassNotFoundException | SQLException ex) {
                                    Logger.getLogger(Efetivacao_OrdemCompra.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                quantidade_para_armazenar = 0;
                            }
                        }
                    } else {

                    }
                }
            }

            try {
                String sql = "update ordemcompra set valUnitario = ?, quantidade = ?, codCliente = ?, codFornecedor = ?, codTipoProduto = ?, quantEstocada = ?, situacao =? where codOrdemCompra = " + getCodigo();
                Connection conn = ConexaoBanco.getConnection();
                PreparedStatement ps1 = conn.prepareStatement(sql);
                ps1.setFloat(1, Float.valueOf(valUnitario_box.getText()));
                ps1.setInt(2, Integer.valueOf(quant_box.getText()));
                ps1.setInt(3, codC);
                ps1.setInt(4, codF);
                ps1.setInt(5, codP);

                if (Integer.valueOf(total_armazenado_box.getText()) + Integer.valueOf(rebebido_box.getText()) <= Integer.valueOf(quant_box.getText())) {

                    int quantEstocada = Integer.valueOf(total_armazenado_box.getText()) + Integer.valueOf(rebebido_box.getText());

                    ps1.setInt(6, quantEstocada);

                    if (Integer.valueOf(total_armazenado_box.getText()) + Integer.valueOf(rebebido_box.getText()) == Integer.valueOf(quant_box.getText())) {
                        ps1.setInt(7, 0);
                        String sql1 = "insert into notaCompra (codFornecedor, codTipoProduto, codOrdemCompra) values (?, ?, ?)";
                        PreparedStatement ps2 = conn.prepareStatement(sql1);
                        ps2.setInt(1, getCodf());
                        ps2.setInt(2, getCodp());
                        ps2.setInt(3, getCodigo());
                        ps2.execute();

                    } else {
                        ps1.setInt(7, 1);
                    }

                    ps1.execute();

                    JOptionPane.showMessageDialog(this, "Ordem de Compra efetivada com sucesso!", "Confirmação de cadastro", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao obter dados", "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Efetivacao_OrdemCompra.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void auxiliar(OrdemCompra ordem) {

        quant_box.setText(String.valueOf(ordem.getQuantidade()));
        valUnitario_box.setText(String.valueOf(ordem.getValUnitario()));

        String Fornecedor = "";
        String Cliente = "";
        String Produto = "";
        int codAL = 0;
        int codCO = 0;

        setCodf(ordem.getCodFornecedor());
        setCodp(ordem.getCodTipoProduto());

        try {
            Connection conn = ConexaoBanco.getConnection();

            String sql20 = "select count(*) as  quantidade from alojamento a join cliente c on (a.codCliente = c.codCliente) where c.codCliente = " + ordem.getCodCliente();
            PreparedStatement ps20 = conn.prepareStatement(sql20);
            ResultSet rs20 = ps20.executeQuery();
            while (rs20.next()) {
                setCount(rs20.getInt("quantidade"));

            }

            String sql = "select nome from fornecedor where codFornecedor = " + String.valueOf(ordem.getCodFornecedor());
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()) {
                String nome = rs1.getString("nome");
                Fornecedor = nome;
            }

            String sql2 = "select nome from cliente where codCliente = " + String.valueOf(ordem.getCodCliente());
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                String nome2 = rs2.getString("nome");
                Cliente = nome2;
            }

            String sql3 = "select descricao from tipoproduto where codTipoProduto = " + String.valueOf(ordem.getCodTipoProduto());
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                String desc = rs3.getString("descricao");
                Produto = desc;
            }

            String sql4 = "select codAlojamento from alojamento where codCliente = " + String.valueOf(ordem.getCodCliente());
            PreparedStatement ps4 = conn.prepareStatement(sql4);
            ResultSet rs4 = ps4.executeQuery();
            while (rs4.next()) {
                int codA = rs4.getInt("codAlojamento");
                codAL = codA;
                listCodAlojamento.add(codA);

                String sql8 = "select r.codReceptaculo, r.codCorredor from receptaculo r join tipoProduto t  on  (t.codTipoProduto = r.codTipoProduto) where r.codCorredor in (select cd.codCorredor from corredor cd join alojamento a on (a.codAlojamento = cd.codAlojamento) where a.codAlojamento = " + codA + ") and r.codTipoProduto = " + ordem.getCodTipoProduto();
                PreparedStatement ps8 = conn.prepareStatement(sql8);
                ResultSet rs8 = ps8.executeQuery();
                while (rs8.next()) {
                    int codRec = rs8.getInt("codReceptaculo");
                    int codCORR = rs8.getInt("codCorredor");
                    listCodRec.add(codRec);
                    getListCODIGODOCORREDOR().add(codCORR);
                }
                String sql10 = "select quantidade from receptaculo where codTipoProduto = " + ordem.getCodTipoProduto();
                PreparedStatement ps10 = conn.prepareStatement(sql10);
                ResultSet rs10 = ps10.executeQuery();
                while (rs10.next()) {
                    int quantA = rs10.getInt("quantidade");
                    listQuantidadeArmazenada.add(quantA);
                }

                String sql5 = "select codCorredor from corredor where codAlojamento = " + String.valueOf(codAL);
                PreparedStatement ps5 = conn.prepareStatement(sql5);
                ResultSet rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    int codCo = rs5.getInt("codCorredor");
                    listCorredores2.add(codCo);

                    String sql12 = "select * from receptaculo where codCorredor = " + String.valueOf(codCo);
                    PreparedStatement ps12 = conn.prepareStatement(sql12);
                    ResultSet rs12 = ps12.executeQuery();
                    while (rs12.next()) {
                        int codRECEPTACULO = rs12.getInt("codReceptaculo");
                        int codTIPOPRODUTO = rs12.getInt("codTipoProduto");
                        int QUANTIDADE = rs12.getInt("quantidade");
                        Receptaculo rece = new Receptaculo();
                        rece.setCodReceptaculo(codRECEPTACULO);
                        rece.setCodCorredor(codCo);
                        rece.setCodTipoProduto(codTIPOPRODUTO);
                        rece.setQuantidade(QUANTIDADE);
                        listRECEPTACULO.add(rece);
                    }
                }
            }

            String sql11 = "select qtdeMaxRecpt from tipoproduto where codTipoProduto = " + String.valueOf(ordem.getCodTipoProduto());
            PreparedStatement ps11 = conn.prepareStatement(sql11);
            ResultSet rs11 = ps11.executeQuery();
            while (rs11.next()) {
                int x = rs11.getInt("qtdeMaxRecpt");
                setQuantidadeMaxTipoProduto(x);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Efetivacao_OrdemCompra.class.getName()).log(Level.SEVERE, null, ex);
        }

        fornecedor_combo_box.setSelectedItem(Fornecedor);
        cliente_combo_box.setSelectedItem(Cliente);
        produto_combo_box.setSelectedItem(Produto);
        total_armazenado_box.setText(String.valueOf(ordem.getQuantestocada()));
        setCodigo(ordem.getCodOrdemCompra());
    }

    private boolean validarDados() {
        boolean validar = true;
        boolean verifica = true;

        if (rebebido_box.getText().isEmpty()) {
            validar = false;
        } else if (Integer.valueOf(total_armazenado_box.getText()) + Integer.valueOf(rebebido_box.getText()) > Integer.valueOf(quant_box.getText())) {
            verifica = false;

        }

        if (!validar) {
            JOptionPane.showMessageDialog(this, "Por favor preencher todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            return validar;
        } else if (!verifica) {
            JOptionPane.showMessageDialog(this, "A quantidade a ser depositada e maior do que a quantidade requisitado pela Ordem de Compra", "Erro", JOptionPane.ERROR_MESSAGE);
            return verifica;
        }

        return true;
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
        jLabel6 = new javax.swing.JLabel();
        rebebido_box = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        total_armazenado_box = new javax.swing.JTextField();
        buttons_panel = new javax.swing.JPanel();
        cancellButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Efetivação Ordem de Compra");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        registro_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Efetivar Ordem de Compra"));

        jLabel1.setText("Produto:");

        jLabel2.setText("Valor Unitario:");

        produto_combo_box.setEditable(true);
        produto_combo_box.setEnabled(false);

        valUnitario_box.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        valUnitario_box.setEnabled(false);

        jLabel3.setText("Quantidade:");

        quant_box.setEditable(false);

        jLabel4.setText("Fornecedor:");

        jLabel5.setText("Cliente:");

        fornecedor_combo_box.setEditable(true);
        fornecedor_combo_box.setEnabled(false);

        cliente_combo_box.setEditable(true);
        cliente_combo_box.setEnabled(false);

        jLabel6.setText("Quantidade a ser Armazenada:");

        rebebido_box.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        jLabel7.setText("Armazenado:");

        total_armazenado_box.setEditable(false);

        javax.swing.GroupLayout registro_panelLayout = new javax.swing.GroupLayout(registro_panel);
        registro_panel.setLayout(registro_panelLayout);
        registro_panelLayout.setHorizontalGroup(
            registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registro_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registro_panelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valUnitario_box, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registro_panelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fornecedor_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registro_panelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cliente_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registro_panelLayout.createSequentialGroup()
                        .addGroup(registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registro_panelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(quant_box, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registro_panelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(produto_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(137, 137, 137)
                        .addGroup(registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registro_panelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(total_armazenado_box, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registro_panelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rebebido_box, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        registro_panelLayout.setVerticalGroup(
            registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registro_panelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(produto_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(total_armazenado_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(quant_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registro_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(rebebido_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        if (validarDados()) {
            EfetivarOrdem();
            dispose();
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        EfetivarOrdemDeCompra eoc = new EfetivarOrdemDeCompra();
        eoc.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void cancellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellButtonActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Deseja mesmo sair da tela de efetivação?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            dispose();

        }
    }//GEN-LAST:event_cancellButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Efetivacao_OrdemCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Efetivacao_OrdemCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Efetivacao_OrdemCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Efetivacao_OrdemCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Efetivacao_OrdemCompra().setVisible(true);
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox<String> produto_combo_box;
    private javax.swing.JFormattedTextField quant_box;
    private javax.swing.JFormattedTextField rebebido_box;
    private javax.swing.JButton registerButton;
    private javax.swing.JPanel registro_panel;
    private javax.swing.JTextField total_armazenado_box;
    private javax.swing.JFormattedTextField valUnitario_box;
    // End of variables declaration//GEN-END:variables
}
