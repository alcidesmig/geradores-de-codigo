/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Auxiliar.STRS;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import Auxiliar.*;
import java.awt.Color;

/**
 *
 * @author Aluno
 */
public class GerarClasseGUI1 {

    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    String nomeClasse;
    List<String> codigo = new ArrayList<>();
    List<String> descricaoEntidade;
    STRS st = new STRS();
    List<String> listaRd = new ArrayList<>();
    List<String> listaCb = new ArrayList<>();
    ArrayList<String> Textos = new ArrayList<String>();
    ArrayList<String> Campos = new ArrayList<String>();
    ArrayList<String> componentesAll = new ArrayList<String>();
    ArrayList<String> camposCompare = new ArrayList<String>();
    ArrayList<String> listaSp = new ArrayList<String>();

    public GerarClasseGUI1(String caminho, String formatoDate, Boolean temImg) {
        File file = new File(caminho);
        String nomeClasseUp = "";
        if (file.exists()) {
            nomeClasse = file.getName();
            String[] aux = nomeClasse.split("\\.");
            nomeClasseUp = aux[0];
            nomeClasse = aux[0].toLowerCase();
        } else {
            System.out.println("Erro!");
            System.exit(0);
        }
        descricaoEntidade = manipulaArquivo.abrirArquivo(caminho);

        String pKey = descricaoEntidade.get(0).split(";")[0];
        String pKeyUp = st.primUp(descricaoEntidade.get(0).split(";")[0]);
        String pKeyType = descricaoEntidade.get(0).split(";")[1];

        System.out.println(nomeClasse);

        codigo.add("package classeGerada;");
        codigo.add("");
        codigo.add("import java.awt.*;");
        codigo.add("import java.awt.event.*;");
        codigo.add("import java.util.*;");
        codigo.add("import java.text.SimpleDateFormat;");
        codigo.add("import java.util.List;");
        codigo.add("import javax.swing.*;");
        codigo.add("import java.io.File;");
        codigo.add("import javax.swing.text.JTextComponent;");
        codigo.add("import Auxiliar.*;");
        codigo.add("import DAOs.*;");
        codigo.add("import Entidades.*;");
        codigo.add("");
        codigo.add("public class GUI" + nomeClasseUp + " extends JFrame {");
        codigo.add("    public static void main(String[] args) {\n"
                + "        new GUI" + nomeClasseUp + "();\n"
                + "    }");
        codigo.add("    private Container cp;");
        List<String> compLab = new ArrayList<>();
        List<String> compLabF = new ArrayList<>();
        List<String> listaField = new ArrayList<>();
        List<String> listaCombo = new ArrayList<>();
        List<String> rdGroup = new ArrayList<>();
        List<String> pnBg = new ArrayList<>();
        codigo.add("    private JLabel labelAviso = new JLabel(\"Avisos\");");
        codigo.add("    private JLabel labelTitulo = new JLabel(\"" + descricaoEntidade.get(0).split(";")[3] + ": \");");
        for (int i = 0; i < descricaoEntidade.size(); i++) {
            String[] aux = descricaoEntidade.get(i).split(";");
            System.out.println(">>>>>>" + descricaoEntidade.get(i));
            try {
                Integer.valueOf(aux[2]);
                String linha = "    private JLabel lb" + st.primUp(aux[0]) + " = new JLabel(\"" + aux[3] + "\");";
                String linha2 = "    private JTextField fd" + st.primUp(aux[0]) + " = new JTextField(" + aux[2] + ");";
                compLab.add("lb" + st.primUp(aux[0]));
                compLabF.add("lb" + st.primUp(aux[0]));
                listaField.add("fd" + st.primUp(aux[0]));
                componentesAll.add("fd" + st.primUp(aux[0]));
                camposCompare.add("fd" + st.primUp(aux[0]));
                codigo.add(linha);
                codigo.add(linha2);
                codigo.add("");
            } catch (Exception e) {
                if (aux[2].equals("JRadioButton")) {
                    String pn = "pn" + st.primUp(aux[0]);
                    String add = "lb" + aux[0] + "," + pn + ",";
                    compLabF.add("lb" + (aux[0]));
                    String[] x = {aux[3], aux[4]};
                    pnBg.add(pn);
                    String group = "rdGroup" + st.primUp(aux[0]) + ",";
                    for (int j = 0; j < x.length; j++) {
                        add += "rd" + x[j] + ",";
                        group += ("rd" + x[j] + ",");
                        codigo.add("    JRadioButton rd" + x[j] + " = new JRadioButton(\"" + x[j] + "\");");
                        componentesAll.add("rd" + x[j]);
                        pnBg.add("rd" + x[j]);
                    }
                    rdGroup.add(group);
                    camposCompare.add(add);
                    listaRd.add(add);
                    codigo.add("    JLabel lb" + aux[0] + " = new JLabel(\"" + aux[aux.length - 1] + "\");");
                    codigo.add("    JPanel " + pn + " = new JPanel();");
                    codigo.add("    ButtonGroup " + group.split(",")[0] + " = new ButtonGroup();");
                } else if (aux[1].equals("Date")) {
                    codigo.add("    private JSpinner spinner" + aux[0] + " = new JSpinner(new SpinnerDateModel());");
                    codigo.add("    private final JSpinner.DateEditor spinnerEditor" + aux[0] + " = new JSpinner.DateEditor(spinner" + aux[0] + ", \"dd/MM/yyyy\");");
                    codigo.add("    private JLabel lb" + st.primUp(aux[0]) + " = new JLabel(\"" + aux[2] + "\");");
                    String x = "lb" + st.primUp(aux[0]) + "," + "spinner" + aux[0] + "," + "spinnerEditor" + aux[0];
                    componentesAll.add("spinner" + aux[0]);
                    camposCompare.add("spinner" + aux[0]);
                    listaSp.add(x);
                    compLabF.add("lb" + st.primUp(aux[0]));
                } else if (aux[2].equals("JCheckBox")) {
                    String pn = "pn" + st.primUp(aux[0]);
                    pnBg.add(pn);
                    compLabF.add("lb" + (aux[0]));
                    codigo.add("    JLabel lb" + aux[0] + " = new JLabel(\"" + aux[aux.length - 1] + "\");");
                    codigo.add("    JPanel " + pn + " = new JPanel();");
                    String add = "lb" + aux[0] + "," + pn + ",";
                    String[] x = {aux[3], aux[4]};
                    for (int j = 0; j < x.length; j++) {
                        add += "check" + x[j] + ",";
                        codigo.add("    JCheckBox check" + x[j] + " = new JCheckBox(\"" + x[j] + "\");");
                        componentesAll.add("check" + x[j]);
                        pnBg.add("check" + x[j]);
                    }
                    camposCompare.add(add);
                    listaCb.add(add);
                } else if (aux[1].equals("Dependente")) {
                    String linha = "    private JLabel lb" + st.primUp(aux[0]) + " = new JLabel(\"" + aux[2] + "\");";
                    String linha2 = "    private List<String> string" + aux[0] + " = new ArrayList<>();";
                    String linha3 = "    private JComboBox combo" + st.primUp(aux[0]) + " = new JComboBox();";
                    listaCombo.add("lb" + st.primUp(aux[0]) + ",combo" + st.primUp(aux[0]) + "," + aux[3] + "," + aux[0]);
                    camposCompare.add("combo" + st.primUp(aux[0]));
                    System.out.println("ADICIONOU   " + ("combo" + st.primUp(aux[0])));
                    componentesAll.add("combo" + st.primUp(aux[0]));
                    compLabF.add("lb" + st.primUp(aux[0]));
                    codigo.add(linha);
                    codigo.add(linha2);
                    codigo.add(linha3);
                }
            }
        }
        if (temImg) {
            codigo.add("    JPanel painelImagem = new JPanel(new GridLayout(1, 1));");
            codigo.add("    Image img;");
            codigo.add("    Image imagemAux;");
            codigo.add("    String origem;");
            codigo.add("    String destino = \"src/fotos/\";");
            codigo.add("    String semImagem = \"src/fotos/0.png\";");
            codigo.add("    String escolherImagem = \"src/fotos/0a.png\";");
            codigo.add("    JLabel labelFoto = new JLabel(\"\");");
            codigo.add("    Boolean uploadFoto = false;");
            temImg = true;
        }
        codigo.add("");
        codigo.add("    private JPanel painelNortes = new JPanel(new GridLayout(2, 1));");
        codigo.add("    private JPanel painelNorteCima = new JPanel();");
        codigo.add("    private JPanel painelNorteBaixo = new JPanel();");
        codigo.add("    private JPanel painelCentralFora = new JPanel(new BorderLayout());");
        codigo.add("    private JPanel painelCentral = new JPanel();");
        codigo.add("    private JPanel painelSul = new JPanel();");
        codigo.add("    private JLabel labelBranco = new JLabel();");
        codigo.add("");
        codigo.add("    JButton btInserir = new JButton(new ImageIcon(getClass().getResource(\"/icones/add.png\")));");
        codigo.add("    JButton btSalvar = new JButton(new ImageIcon(getClass().getResource(\"/icones/confirmar.png\")));");
        codigo.add("    JButton btRemover = new JButton(new ImageIcon(getClass().getResource(\"/icones/deletar.png\")));");
        codigo.add("    JButton btAtualizar = new JButton(new ImageIcon(getClass().getResource(\"/icones/att.png\")));");
        codigo.add("    JButton btBuscar = new JButton(new ImageIcon(getClass().getResource(\"/icones/search.png\")));");
        codigo.add("    JButton btCancelar = new JButton(new ImageIcon(getClass().getResource(\"/icones/cancelar.png\")));");
        codigo.add("    JButton btListar = new JButton(new ImageIcon(getClass().getResource(\"/icones/listar.png\")));");
        codigo.add("");
        codigo.add("    DAO" + nomeClasseUp + " controle = new DAO" + nomeClasseUp + "();");
        codigo.add("    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();");
        codigo.add("    Boolean acao;");
        codigo.add("    Font fonte = new Font(\"Courier New\", Font.BOLD, 20);");
        codigo.add("    Font fonteL = new Font(\"Courier New\", Font.PLAIN, 14);");
        codigo.add("");
        codigo.add("    private SimpleDateFormat sdf = new SimpleDateFormat(\"" + formatoDate + "\");");
        if (listaCombo.size() > 0) {
            codigo.add("    JTextComponent editor = (JTextComponent) " + listaCombo.get(0).split(",")[1] + ".getEditor().getEditorComponent();");
        }
        codigo.add("");
        String novoElClasse = "    " + nomeClasseUp + " " + nomeClasse + " = new " + nomeClasseUp + "();";
        codigo.add(novoElClasse);
        codigo.add("");
        codigo.add("    public GUI" + nomeClasseUp + "(){");
        int xS = 725, yS = 340;
        if (descricaoEntidade.size() - 1 > 2) { //************
            yS = yS + (((descricaoEntidade.size() - 1) - 2) * 40);
        }
        codigo.add("        setSize(" + xS + ", " + yS + ");");
        codigo.add("        setDefaultCloseOperation(DISPOSE_ON_CLOSE);");
        codigo.add("        cp = getContentPane();");
        codigo.add("        cp.setLayout(new BorderLayout());");
        codigo.add("        setTitle(\"Cadastro de " + nomeClasseUp + "s\");");
        codigo.add("");
        if (temImg) {
            codigo.add("        try {\n"
                    + "            origem = \"/fotos/0.png\";\n"
                    + "            ImageIcon icone = new ImageIcon(getClass().getResource(origem));\n"
                    + "            imagemAux = icone.getImage();\n"
                    + "            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));\n"
                    + "            labelFoto.setIcon(icone);\n"
                    + "\n"
                    + "        } catch (Exception erro) {\n"
                    + "            System.out.println(\"erro ao carregar a imagem\");\n"
                    + "        }");
            codigo.add("");
        }
        codigo.add("        painelCentral.setLayout(new GridLayout(" + descricaoEntidade.size() + ", 2));");

        for (int x = 1; x < compLab.size(); x++) {
            codigo.add("        painelCentral.add(" + compLab.get(x) + ");");
            codigo.add("        painelCentral.add(" + listaField.get(x) + ");");
        }
        codigo.add("");
        for (String componente : listaField) {
            if (!componente.equals("fd" + pKeyUp)) {
                codigo.add("        " + componente + ".setEnabled(false);");
            }
        }
        codigo.add("");
        for (String x : listaRd) {
            String[] k = x.split(",");
            codigo.add("        painelCentral.add(" + k[0] + ");");
            for (int j = 2; j < k.length; j++) {
                codigo.add("        " + k[1] + ".add(" + k[j] + ");");
                codigo.add("        " + k[j] + ".setEnabled(false);");
            }
            codigo.add("        painelCentral.add(" + k[1] + ");");
        }
        for (String x : listaSp) {
            String[] k = x.split(",");
            codigo.add("        painelCentral.add(" + k[0] + ");");
            codigo.add("        painelCentral.add(" + k[1] + ");");
            codigo.add("        " + k[1] + ".setEditor(" + k[2] + ");");
            codigo.add("        " + k[1] + ".setEnabled(false);");
        }
        for (String x : listaCb) {
            String[] k = x.split(",");
            codigo.add("        painelCentral.add(" + k[0] + ");");
            for (int j = 2; j < k.length; j++) {
                codigo.add("        " + k[1] + ".add(" + k[j] + ");");
                codigo.add("        " + k[j] + ".setEnabled(false);");
            }
            codigo.add("        painelCentral.add(" + k[1] + ");");
        }
        codigo.add("        List<String> combo = new ArrayList<>();");
        for (String x : listaCombo) {
            String[] k = x.split(",");
            gerarAtualizaComboBox(k, "        ");
            codigo.add("        painelCentral.add(" + k[0] + ");");
            codigo.add("        painelCentral.add(" + k[1] + ");");
            codigo.add("        " + k[1] + ".setEnabled(false);");
            codigo.add("        editor.setDocument(new SearchableComboBox(" + k[1] + "));");
        }

        codigo.add("");
        /*        for (String x : rdGroup) { RETIRAR COMENTÁRIO -> UTILIZAÇÃO DE RADIOGROUPS PARA RADIOBUTTONS
            for (int i = 0; i < 2; i++) {
                String[] y = x.split(",");
                codigo.add("        " + y[0] + ".add(" + y[1] + ");");
                codigo.add("        " + y[0] + ".add(" + y[2] + ");");
            }
        }*/
        codigo.add("");
        codigo.add("cp.setBackground(Color.white);");
        if (temImg) {

            codigo.add("        painelImagem.setBackground(Color.white);");
            codigo.add("        painelImagem.add(labelFoto);");
            codigo.add("        cp.add(painelNortes, BorderLayout.NORTH);");
            codigo.add("        cp.add(painelCentralFora, BorderLayout.WEST);");
            codigo.add("        cp.add(painelImagem, BorderLayout.EAST);");
            codigo.add("        cp.add(painelSul, BorderLayout.SOUTH);");
        } else {
            codigo.add("        cp.add(painelNortes, BorderLayout.NORTH);");
            codigo.add("        cp.add(painelCentralFora, BorderLayout.CENTER);");
            codigo.add("        cp.add(painelSul, BorderLayout.SOUTH);");
        }

        codigo.add("");
        codigo.add("        painelCentralFora.add(labelBranco, BorderLayout.NORTH);");
        codigo.add("        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);");
        codigo.add("        painelNortes.add(painelNorteCima);");
        codigo.add("        painelNortes.add(painelNorteBaixo);");
        codigo.add("        painelNorteCima.add(labelTitulo);");
        codigo.add("        painelNorteCima.add(fd" + pKeyUp + ");");
        codigo.add("        painelNorteBaixo.add(btBuscar);");
        codigo.add("        painelNorteBaixo.add(btInserir);");
        codigo.add("        painelNorteBaixo.add(btAtualizar);");
        codigo.add("        painelNorteBaixo.add(btRemover);");
        codigo.add("        painelNorteBaixo.add(btSalvar);");
        codigo.add("        painelNorteBaixo.add(btCancelar);");
        codigo.add("        painelNorteBaixo.add(btListar);");
        codigo.add("        painelNorteCima.setBackground(Color.white);");
        codigo.add("        painelNorteBaixo.setBackground(Color.white);");
        codigo.add("        painelCentralFora.setBackground(Color.white);");
        codigo.add("        painelCentral.setBackground(Color.white);");
        codigo.add("        painelSul.setBackground(Color.white);");
        codigo.add("        btInserir.setBackground(Color.WHITE);");
        codigo.add("        btSalvar.setBackground(Color.WHITE);");
        codigo.add("        btRemover.setBackground(Color.WHITE);");
        codigo.add("        btAtualizar.setBackground(Color.WHITE);");
        codigo.add("        btBuscar.setBackground(Color.WHITE);");
        codigo.add("        btCancelar.setBackground(Color.WHITE);");
        codigo.add("        btListar.setBackground(Color.WHITE);");
        for (String x : pnBg) {
            codigo.add("        " + x + ".setBackground(Color.WHITE);");
        }
        codigo.add("");
        codigo.add("        labelTitulo.setFont(new Font(\"Courier New\", Font.BOLD, 20));");
        codigo.add("        fd" + pKeyUp + ".setFont(new Font(\"Courier New\", Font.PLAIN, 20));"); // ***** ARRUMAR FONTES
        for (String x : compLabF) {
            codigo.add("        " + x + ".setFont(new Font(\"Courier New\", Font.BOLD, 17));");
        }
        for (String x : componentesAll) {
            codigo.add("        " + x + ".setFont(new Font(\"Courier New\", Font.PLAIN, 17));");
        }
        codigo.add("        labelAviso.setFont(new Font(\"Courier New\", Font.BOLD, 20));");
        codigo.add("        btInserir.setVisible(false);");
        codigo.add("        btAtualizar.setVisible(false);");
        codigo.add("        btRemover.setVisible(false);");
        codigo.add("        btSalvar.setVisible(false);");
        codigo.add("        btCancelar.setVisible(false);");
        codigo.add("");
        codigo.add("        painelSul.add(labelAviso);");
        codigo.add("");
        codigo.add("        btBuscar.addActionListener(new ActionListener() {");
        codigo.add("            @Override");
        codigo.add("            public void actionPerformed(ActionEvent e) {");
        if (temImg) {
            codigo.add("            uploadFoto = false;");
        }
        codigo.add("                try{");
        codigo.add("                    " + nomeClasse + " = new " + nomeClasseUp + "();");
        if (pKeyType.equals("int")) {
            codigo.add("                    int " + pKey + " = Integer.valueOf(fd" + pKeyUp + ".getText());");
        } else if (pKeyType.equals("double")) {
            codigo.add("                    Double " + pKey + " = Double.valueOf(fd" + pKeyUp + ".getText());");
        } else if (pKeyType.equals("String")) {
            codigo.add("                    String " + pKey + " = fd" + pKeyUp + ".getText();");
        } else if (pKeyType.equals("long")) {
            codigo.add("                    long " + pKey + " = Long.valueOf(fd" + pKeyUp + ".getText());");
        }
        if (pKeyType.equals("int")) {
            codigo.add("                    " + nomeClasse + ".set" + pKeyUp + "(Integer.valueOf(fd" + pKeyUp + ".getText()));");
        } else if (pKeyType.equals("Double")) {
            codigo.add("                    " + nomeClasse + ".set" + pKeyUp + "(Double.valueOf(fd" + pKeyUp + ".getText()));");
        } else if (pKeyType.equals("String")) {
            codigo.add("                    " + nomeClasse + ".set" + pKeyUp + "(fd" + pKeyUp + ".getText());");
        } else if (pKeyType.equals("long")) {
            codigo.add("                    " + nomeClasse + ".set" + pKeyUp + "(Long.valueOf(fd" + pKeyUp + ".getText()));");
        }

        codigo.add("                    " + nomeClasse + " = controle.obter(" + nomeClasse + ".get" + pKeyUp + "());");
        codigo.add("                    labelAviso.setBackground(Color.green);");
        codigo.add("                    if (" + nomeClasse + " != null) {");
        for (int i2 = 0; i2 < camposCompare.size(); i2++) {
            String type = descricaoEntidade.get(i2).split(";")[1];
            if (type.equals("boolean")) {
                codigo.add("                        if (" + nomeClasse + ".get" + st.primUp(descricaoEntidade.get(i2).split(";")[0]) + "()) {");
                codigo.add("                            " + camposCompare.get(i2).split(",")[2] + ".setSelected(true);");
                codigo.add("                            " + camposCompare.get(i2).split(",")[3] + ".setSelected(false);");
                codigo.add("                        } else {");
                System.out.println(camposCompare.get(i2).split(",")[3]);
                codigo.add("                            " + camposCompare.get(i2).split(",")[3] + ".setSelected(true);");
                codigo.add("                            " + camposCompare.get(i2).split(",")[2] + ".setSelected(false);");
                codigo.add("                        }");
                //TRYCATCH PARA VERIFICAR SE HÁ SEGUNDO
            } else if (type.equals("Date")) {
                codigo.add("                        " + (String) camposCompare.get(i2) + ".setValue(" + nomeClasse + ".get" + st.primUp(descricaoEntidade.get(i2).split(";")[0]) + "());");
            } else if (type.equals("Dependente")) {
                codigo.add("                        " + camposCompare.get(i2) + ".setSelectedItem(" + nomeClasse + ".get" + st.primUp(descricaoEntidade.get(i2).split(";")[0]) + "().toString());");
            } else {
                codigo.add("                        " + (String) camposCompare.get(i2) + ".setText(" + nomeClasse + ".get" + st.primUp(descricaoEntidade.get(i2).split(";")[0]) + "() + \"\");");
            }
        }
        codigo.add("                        btAtualizar.setVisible(true);");
        codigo.add("                        btRemover.setVisible(true);");
        codigo.add("                        btInserir.setVisible(false);");
        codigo.add("                        btListar.setVisible(false);");
        codigo.add("                        labelAviso.setText(\"Encontrado na lista!\");");
        codigo.add("                        labelAviso.setBackground(Color.green);");
        if (temImg) {
            codigo.add("                        try {\n"
                    + "                            String aux = String.valueOf(" + nomeClasse + ".get" + pKeyUp + "()).trim();\n"
                    + "                            origem = \"/fotos/\" + aux + \".png\";\n"
                    + "                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));\n"
                    + "                            imagemAux = icone.getImage();\n"
                    + "                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));\n"
                    + "\n"
                    + "                            labelFoto.setIcon(icone);\n"
                    + "\n"
                    + "                        } catch (Exception erro) {\n"
                    + "                            origem = \"/fotos/0.png\";\n"
                    + "                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));\n"
                    + "                            imagemAux = icone.getImage();\n"
                    + "                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));\n"
                    + "                            labelFoto.setIcon(icone);\n"
                    + "                        }");
        }
        codigo.add("                    } else {");
        if (temImg) {
            codigo.add("        try {\n"
                    + "            origem = \"/fotos/0.png\";\n"
                    + "            ImageIcon icone = new ImageIcon(getClass().getResource(origem));\n"
                    + "            imagemAux = icone.getImage();\n"
                    + "            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));\n"
                    + "            labelFoto.setIcon(icone);\n"
                    + "\n"
                    + "        } catch (Exception erro) {\n"
                    + "            System.out.println(\"erro ao carregar a imagem\");\n"
                    + "        }");
        }
        for (int i = 0; i < listaField.size(); ++i) {
            if (!listaField.get(i).equals("fd" + pKeyUp)) {
                codigo.add("                        " + listaField.get(i) + ".setEnabled(false);");
                codigo.add("                        " + listaField.get(i) + ".setText(null);");
            }
        }
        for (int i = 0; i < listaRd.size(); ++i) {
            String x = listaRd.get(i).split(",")[2];
            String y = listaRd.get(i).split(",")[3];
            codigo.add("                        " + x + ".setEnabled(false);");
            codigo.add("                        " + y + ".setEnabled(false);");
            codigo.add("                        " + x + ".setSelected(false);");
            codigo.add("                        " + y + ".setSelected(false);");
        }
        for (int i = 0; i < listaCb.size(); ++i) {
            String x = listaCb.get(i).split(",")[2];
            String y = listaCb.get(i).split(",")[3];
            codigo.add("                        " + x + ".setEnabled(false);");
            codigo.add("                        " + y + ".setEnabled(false);");
            codigo.add("                        " + x + ".setSelected(false);");
            codigo.add("                        " + y + ".setSelected(false);");
        }
        for (int i = 0; i < listaSp.size(); ++i) {
            String x = listaSp.get(i).split(",")[1];
            codigo.add("                        " + x + ".setEnabled(false);");
            codigo.add("                        " + x + ".setValue(new Date());");
        } //organizar código com base em funções -> gerador v3
        for (int i = 0; i < listaCombo.size(); i++) {
            String x = listaCombo.get(i).split(",")[1];
            codigo.add("                        " + x + ".setEnabled(false);");
            codigo.add("                        " + x + ".setSelectedIndex(0);");
        }
        codigo.add("                        labelAviso.setText(\"Não encontrado!\");");
        codigo.add("                        labelAviso.setBackground(Color.red);");
        codigo.add("                        btInserir.setVisible(true);");
        codigo.add("                        btAtualizar.setVisible(false);");
        codigo.add("                        btRemover.setVisible(false);");
        codigo.add("                        btListar.setVisible(false);");
        if (temImg) {
            codigo.add("        try {\n"
                    + "            origem = \"/fotos/0.png\";\n"
                    + "            ImageIcon icone = new ImageIcon(getClass().getResource(origem));\n"
                    + "            imagemAux = icone.getImage();\n"
                    + "            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));\n"
                    + "            labelFoto.setIcon(icone);\n"
                    + "\n"
                    + "        } catch (Exception erro) {\n"
                    + "            System.out.println(\"erro ao carregar a imagem\");\n"
                    + "        }");
        }
        codigo.add("                    }");
        codigo.add("                } catch (Exception erro) {");
        codigo.add("                    labelAviso.setText(\"Preencha os campos corretamente!\");");
        codigo.add("                    labelAviso.setBackground(Color.red);");
        codigo.add("                }");
        codigo.add("            }");
        codigo.add("        }");
        codigo.add("        );");
        codigo.add("");
        codigo.add("        btInserir.addActionListener(new ActionListener() {");
        codigo.add("            @Override");
        codigo.add("            public void actionPerformed(ActionEvent e) {");
        codigo.add("                acao = true;");
        if (temImg) {
            codigo.add("        try {\n"
                    + "            origem = \"/fotos/0a.png\";\n"
                    + "            ImageIcon icone = new ImageIcon(getClass().getResource(origem));\n"
                    + "            imagemAux = icone.getImage();\n"
                    + "            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));\n"
                    + "            labelFoto.setIcon(icone);\n"
                    + "\n"
                    + "        } catch (Exception erro) {\n"
                    + "            System.out.println(\"erro ao carregar a imagem\");\n"
                    + "        }");
        }
        codigo.add("                fd" + pKeyUp + ".setEnabled(false);");
        for (String componente : componentesAll) {
            if (!componente.equals("fd" + pKeyUp)) {
                codigo.add("                " + componente + ".setEnabled(true);");
            }
        }
        if (temImg) {
            codigo.add("            uploadFoto = true;");
        }
        codigo.add("                btSalvar.setVisible(true);");
        codigo.add("                btCancelar.setVisible(true);");
        codigo.add("                btBuscar.setVisible(false);");
        codigo.add("                btInserir.setVisible(false);");
        codigo.add("                btListar.setVisible(false);");
        //codigo.add("                fd" + st.primUp(descricaoEntidade.get(1).split(";")[0]) + ".requestFocus();");
        codigo.add("            }");
        codigo.add("        }");
        codigo.add("        );");
        codigo.add("");
        codigo.add("        btSalvar.addActionListener(new ActionListener() {");
        codigo.add("            @Override");
        codigo.add("            public void actionPerformed(ActionEvent e) {");
        if (temImg) {
            codigo.add("            uploadFoto = false;");
        }
        codigo.add("                if(acao){ //btInserir");
        codigo.add("                    try {");
        codigo.add("                    " + nomeClasse + " = new " + nomeClasseUp + "();");
        int cont = 0;
        System.out.println(descricaoEntidade.size());
        System.out.println(camposCompare.size());
        for (String string : camposCompare) {
            System.out.println("Campos compare:" + string);
        }
        for (String linha : descricaoEntidade) {
            String nome = st.primUp(linha.split(";")[0]);
            String type = linha.split(";")[1];
            System.out.println(linha + " = " + type);
            if (type.equals("int")) {
                codigo.add("                        " + nomeClasse + ".set" + nome + "(Integer.valueOf(" + camposCompare.get(cont) + ".getText()));");
            } else if (type.equals("double")) {
                codigo.add("                        " + nomeClasse + ".set" + nome + "(Double.valueOf(" + camposCompare.get(cont) + ".getText()));");
            } else if (type.equals("String")) {
                codigo.add("                        " + nomeClasse + ".set" + nome + "(" + camposCompare.get(cont) + ".getText());");
            } else if (type.equals("long")) {
                codigo.add(nomeClasse + ".set" + nome + "(Long.valueOf(" + (String) camposCompare.get(cont) + ".getText()));");
            } else if (type.equals("boolean")) { // ***************
                String campo = camposCompare.get(cont).split(",")[2];
                if (linha.split(";")[2].equals("JCheckBox") || linha.split(";")[2].equals("JRadioButton")) {
                    codigo.add("                        Boolean resp" + nome + " = null;");
                    codigo.add("                        if (" + campo + ".isSelected()) {");
                    codigo.add("                            resp" + nome + " = true;");
                    if (camposCompare.get(cont).split(",").length > 3) {
                        codigo.add("                        } else if (" + camposCompare.get(cont).split(",")[3] + ".isSelected()) {");
                        codigo.add("                            resp" + nome + " = false;");
                        codigo.add("                        } else {");
                        codigo.add("                            int a = 1 / 0;");
                        codigo.add("                        }");
                    } else {
                        codigo.add("                        } else {");
                        codigo.add("                            resp" + nome + " = false;");
                        codigo.add("                        }");
                    }

                    codigo.add("                        " + nomeClasse + ".set" + nome + "(resp" + nome + ");");

                }
            } else if (type.equals("Date")) {
                codigo.add("                        " + nomeClasse + ".set" + nome + "((Date) " + camposCompare.get(cont) + ".getValue());");
            } else if (type.equals("Dependente")) {
                codigo.add("                        " + nomeClasse + ".set" + nome + "(" + camposCompare.get(cont) + ".getSelectedItem().toString());");
            }

            cont++;
        }
        codigo.add("                        controle.inserir(" + nomeClasse + ");");
        codigo.add("                        labelAviso.setText(\"Registro inserido com sucesso!\");");
        codigo.add("                        fd" + pKeyUp + ".setEnabled(true);");
        codigo.add("                        fd" + pKeyUp + ".requestFocus();");
//        for (String componente : listaField) {
//            if (!componente.equals("fd" + pKeyUp)) {
//                codigo.add("                        " + componente + ".setEnabled(false);");
//            }
//        }
        for (int i = 0; i < componentesAll.size(); ++i) {
            if (!componentesAll.get(i).equals("fd" + pKeyUp)) {
                codigo.add("                        " + componentesAll.get(i) + ".setEnabled(false);");
            }
        }
        codigo.add("                        btSalvar.setVisible(false);");
        codigo.add("                        btCancelar.setVisible(false);");
        codigo.add("                        btBuscar.setVisible(true);");
        codigo.add("                        btListar.setVisible(true);");
        codigo.add("                    } catch (Exception erro) {");
        codigo.add("                        labelAviso.setText(\"Erro nos dados!\");");
        codigo.add("                    }");
        codigo.add("                } else { //btAlterar");
        codigo.add("                    try {");
        codigo.add("                        " + nomeClasse + " = new " + nomeClasseUp + "();");
        cont = 0;
        for (String linha : descricaoEntidade) {
            String nome = st.primUp(linha.split(";")[0]);
            String type = linha.split(";")[1];
            if (type.equals("int")) {
                codigo.add("                        " + nomeClasse + ".set" + nome + "(Integer.valueOf(" + camposCompare.get(cont) + ".getText()));");
            } else if (type.equals("double")) {
                codigo.add("                        " + nomeClasse + ".set" + nome + "(Double.valueOf(" + camposCompare.get(cont) + ".getText()));");
            } else if (type.equals("String")) {
                codigo.add("                        " + nomeClasse + ".set" + nome + "(" + camposCompare.get(cont) + ".getText());");
            } else if (type.equals("long")) {
                codigo.add("                        " + nomeClasse + ".set" + nome + "(Long.valueOf(" + (String) camposCompare.get(cont) + ".getText()));");
            } else if (type.equals("boolean")) {
                String campo = camposCompare.get(cont).split(",")[2];
                if (linha.split(";")[2].equals("JCheckBox")) {
                    codigo.add("                        Boolean resp" + nome + " = null;");
                    codigo.add("                        if (" + campo + ".isSelected()) {");
                    codigo.add("                            resp" + nome + " = true; ");
                    codigo.add("                        } else {");
                    codigo.add("                            resp" + nome + " = false;");
                    codigo.add("                        }");
                    codigo.add("                        " + nomeClasse + ".set" + nome + "(resp" + nome + ");");
                } else if (linha.split(";")[2].equals("JRadioButton")) {
                    codigo.add("                        Boolean resp" + nome + " = null;");
                    codigo.add("                        if(" + campo + ".isSelected()) {"
                            + "    resp" + nome + " = true;"
                            + "} else {"
                            + "    resp" + nome + " = false;"
                            + "}");
                    codigo.add("                        " + nomeClasse + ".set" + nome + "(resp" + nome + ");");
                }
            } else if (type.equals("Date")) {
                codigo.add("                        " + nomeClasse + ".set" + nome + "((Date) " + camposCompare.get(cont) + ".getValue());");
            } else if (type.equals("Dependente")) {
                codigo.add("                        " + nomeClasse + ".set" + nome + "(" + camposCompare.get(cont) + ".getSelectedItem().toString());");
            }

            ++cont;
        }
        codigo.add("                        controle.atualizar(" + nomeClasse + ");");
        codigo.add("                        labelAviso.setText(\"Registro alterado com sucesso!\");");

        codigo.add("                        fd" + pKeyUp + ".setEnabled(true);");
        codigo.add("                        fd" + pKeyUp + ".requestFocus();");
        for (int i = 0; i < componentesAll.size(); ++i) {
            if (!componentesAll.get(i).equals("fd" + pKeyUp)) {
                codigo.add("                        " + componentesAll.get(i) + ".setEnabled(false);");
            }
        }
        codigo.add("                        btSalvar.setVisible(false);");
        codigo.add("                        btCancelar.setVisible(false);");
        codigo.add("                        btBuscar.setVisible(true);");
        codigo.add("                        btListar.setVisible(true);");
        codigo.add("                    } catch (Exception erro) {");
        codigo.add("                        labelAviso.setText(\"Erro nos dados!\");");
        codigo.add("                    }");
        codigo.add("                }");
        if (temImg) {
            codigo.add("                destino = destino + " + nomeClasse + ".get" + pKeyUp + "()" + " + \".png\";");
            codigo.add("                CopiaImagem.copiar(origem, destino);\n"
                    + "                destino = \"src/fotos/\";");
        }
        codigo.add("            }");
        codigo.add("        }");
        codigo.add("    );");
        codigo.add("");
        codigo.add("        btCancelar.addActionListener(new ActionListener() {");
        codigo.add("            @Override");
        codigo.add("            public void actionPerformed(ActionEvent e) {");
        if (temImg) {
            codigo.add("        try {\n"
                    + "            origem = \"/fotos/0.png\";\n"
                    + "            ImageIcon icone = new ImageIcon(getClass().getResource(origem));\n"
                    + "            imagemAux = icone.getImage();\n"
                    + "            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));\n"
                    + "            labelFoto.setIcon(icone);\n"
                    + "\n"
                    + "        } catch (Exception erro) {\n"
                    + "            System.out.println(\"erro ao carregar a imagem\");\n"
                    + "        }");
        }
        codigo.add("                labelAviso.setText(\"Cancelado!\");");

        for (String componente : componentesAll) {
            codigo.add("                " + componente + ".setEnabled(false);");
            if (componente.substring(0, 2).equals("rd") || componente.substring(0, 2).equals("ch")) {
                codigo.add("                " + componente + ".setSelected(false);");
            } else if (componente.substring(0, 2).equals("fd")) {
                codigo.add("                " + componente + ".setText(\"\");");
            } else if (componente.substring(0, 2).equals("sp")) {
                codigo.add("                " + componente + ".setValue(new Date());");
            } else if (componente.substring(0, 2).equals("co")) {
                codigo.add("                " + componente + ".setSelectedIndex(0);");
            }
        }

        codigo.add("                fd" + pKeyUp + ".setEnabled(true);");
        codigo.add("                fd" + pKeyUp + ".requestFocus();");
        codigo.add("                btSalvar.setVisible(false);");
        codigo.add("                btCancelar.setVisible(false);");
        codigo.add("                btBuscar.setVisible(true);");
        codigo.add("                btListar.setVisible(true);");
        codigo.add("            }");
        codigo.add("        }");
        codigo.add("        );");
        codigo.add("");
        codigo.add("        btAtualizar.addActionListener(new ActionListener() {");
        codigo.add("            @Override");
        codigo.add("            public void actionPerformed(ActionEvent e) {");
        if (temImg) {
            codigo.add("            uploadFoto = false;");
        }
        codigo.add("                acao = false;");
        for (String componente : componentesAll) {
            if (!componente.equals("fd" + pKeyUp)) {
                codigo.add("                " + componente + ".setEnabled(true);");
            }
        }
        if (listaField.size() > 1) {
            codigo.add("                " + listaField.get(1) + ".requestFocus();");
        }
        codigo.add("                fd" + pKeyUp + ".setEnabled(false);");
        codigo.add("                btSalvar.setVisible(true);");
        codigo.add("                btCancelar.setVisible(true);");
        codigo.add("                btBuscar.setVisible(false);");
        codigo.add("                btRemover.setVisible(false);");
        codigo.add("                btAtualizar.setVisible(false);");
        codigo.add("                btListar.setVisible(false);");
        codigo.add("            }");
        codigo.add("        }");
        codigo.add("        );");
        codigo.add("");
        codigo.add("        btRemover.addActionListener(new ActionListener() {");
        codigo.add("            @Override");
        codigo.add("            public void actionPerformed(ActionEvent e) {");
        codigo.add("                btRemover.setVisible(false);");
        codigo.add("                btListar.setVisible(false);");
        codigo.add("                btAtualizar.setVisible(false);");
        codigo.add("                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, \"Confirma a exclusão do registro <Chave = \" + " + nomeClasse + ".get" + pKeyUp + "() + \" >?\", \"Confirm\", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {");
        codigo.add("                    controle.remover(" + nomeClasse + ");");
        codigo.add("                    labelAviso.setText(\"Removido!\");");
        for (String componente : componentesAll) {
            if (componente.substring(0, 2).equals("rd") || componente.substring(0, 2).equals("ch")) {
                codigo.add("                    " + componente + ".setEnabled(false);");
                codigo.add("                    " + componente + ".setSelected(false);");
            } else if (componente.substring(0, 2).equals("fd")) {
                codigo.add("                    " + componente + ".setText(\"\");");
                if (!(componente.equals(("fd" + pKeyUp)))) {
                    codigo.add("                    " + componente + ".setEnabled(false);");
                }
            } else if (componente.substring(0, 2).equals("sp")) {
                codigo.add("                    " + componente + ".setEnabled(false);");
                codigo.add("                    " + componente + ".setValue(new Date());");
            } else if (componente.substring(0, 2).equals("co")) {
                codigo.add("                " + componente + ".setEnabled(false);");
                codigo.add("                " + componente + ".setSelectedIndex(0);");
            }
        }
        if (temImg) {
            codigo.add("String aux = String.valueOf(" + nomeClasse + ".get" + pKeyUp + "()).trim();\n"
                    + "                    origem = \"src/fotos/\" + aux + \".png\";\n"
                    + "                    System.out.println(origem);\n"
                    + "                    try {\n"
                    + "                        File f = new File(origem);\n"
                    + "                        if (f.exists()) {\n"
                    + "                            f.delete();\n"
                    + "                        }\n"
                    + "                    } catch (Exception erro) {\n"
                    + "                        System.out.println(\"Erro\");\n"
                    + "                    }");
        }
        codigo.add("                    btListar.setVisible(true);");
        codigo.add("                } else {");
        codigo.add("                    labelAviso.setText(\"Remoção cancelada!\");");
        codigo.add("                    btAtualizar.setVisible(true);");
        codigo.add("                    btRemover.setVisible(true);");
        codigo.add("                }");
        codigo.add("            }");
        codigo.add("        }");
        codigo.add("        );");
        codigo.add("");
        if (temImg) {
            codigo.add(" labelFoto.addMouseListener(new MouseAdapter() {\n"
                    + "            public void mouseReleased(MouseEvent e) {\n"
                    + "                if (uploadFoto) {\n"
                    + "                    JFileChooser fc = new JFileChooser();\n"
                    + "                    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);\n"
                    + "                    if (fc.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {\n"
                    + "                        File img = fc.getSelectedFile();\n"
                    + "                        origem = fc.getSelectedFile().getAbsolutePath();\n"
                    + "                        try {\n"
                    + "                            ImageIcon icone = new javax.swing.ImageIcon(img.getAbsolutePath());\n"
                    + "                            Image imagemAux;\n"
                    + "                            imagemAux = icone.getImage();\n"
                    + "                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));\n"
                    + "                            labelFoto.setIcon(icone);\n"
                    + "\n"
                    + "                        } catch (Exception ex) {\n"
                    + "                            System.out.println(\"Erro: \" + ex.getMessage());\n"
                    + "                        }\n"
                    + "                    }\n"
                    + "\n"
                    + "                }\n"
                    + "\n"
                    + "            }\n"
                    + "        });");
        }
        codigo.add("");
        codigo.add("        btListar.addActionListener(new ActionListener() {");
        codigo.add("            @Override");
        codigo.add("            public void actionPerformed(ActionEvent e) {");
        codigo.add("                new " + nomeClasseUp + "GUIListagem(controle.listInOrderNomeStrings(\"tanto faz\"), cp);");
        codigo.add("            }");
        codigo.add("        }");
        codigo.add("        );");
        codigo.add("");
        codigo.add("        addWindowListener(new WindowAdapter() {");
        codigo.add("            @Override");
        codigo.add("            public void windowClosing(WindowEvent e) {");
        codigo.add("                dispose();");
        codigo.add("            }");
        codigo.add("        }");
        codigo.add("        );");
        codigo.add("        setLocationRelativeTo(null);");
        codigo.add("        setVisible(true);");
        codigo.add("    }");
        codigo.add("}");

        new ManipulaArquivo().salvarArquivo("src/classeGerada/GUI" + nomeClasseUp + ".java", codigo);
    }

    private void gerarAtualizaComboBox(String[] k, String ident) {
        codigo.add(ident + "combo = new ManipulaArquivo().abrirArquivo(\"" + k[2] + "\");");
        codigo.add(ident + "for(int x = 0; x < combo.size(); x++) {");
        codigo.add(ident + "    string" + k[3] + ".add(combo.get(x).split(\";\")[0]);");
        codigo.add(ident + "}");
        codigo.add(ident + k[1] + " = new JComboBox(string" + k[3] + ".toArray());");
    }
}
