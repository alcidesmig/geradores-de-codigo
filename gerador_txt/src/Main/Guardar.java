///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Main;
//
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author Aluno
// */
//public class GerarClasseGUI1 {
//
//    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
//    String nomeClasse;
//    List<String> codigo = new ArrayList<>();
//    List<String> descricaoEntidade;
//    STRS st = new STRS();
//    List<String> listaRd = new ArrayList<>();
//    List<String> listaCb = new ArrayList<>();
//    ArrayList<String> Textos = new ArrayList<String>();
//    ArrayList<String> Campos = new ArrayList<String>();
//    ArrayList<String> CamposModif = new ArrayList<String>();
//    ArrayList<String> camposCompare = new ArrayList<String>();
//    ArrayList<String> CamposSp = new ArrayList<String>();
//
//    public GerarClasseGUI1(String caminho) {
//        File file = new File(caminho);
//        if (file.exists()) {
//            nomeClasse = file.getName();
//            String[] aux = nomeClasse.split("\\.");
//            nomeClasse = aux[0].toLowerCase();
//        } else {
//            System.out.println("Erro!");
//            System.exit(0);
//        }
//        descricaoEntidade = manipulaArquivo.abrirArquivo(caminho);
//
//        String pKey = descricaoEntidade.get(0).split(";")[0];
//        String pKeyUp = st.primUp(descricaoEntidade.get(0).split(";")[0]);
//        String pKeyType = descricaoEntidade.get(0).split(";")[1];
//        String nomeClasseUp = st.primUp(nomeClasse);
//
//        codigo.add("package classeGerada;");
//        codigo.add("");
//        codigo.add("import java.awt.*;");
//        codigo.add("import java.awt.event.*;");
//        codigo.add("import java.util.*;");
//        codigo.add("import java.text.SimpleDateFormat;");
//        codigo.add("import java.util.List;");
//        codigo.add("import javax.swing.*;");
//        codigo.add("");
//        codigo.add("public class GUI extends JFrame {");
//        codigo.add("    private Container cp;");
//        List<String> compLab = new ArrayList<>();
//        List<String> compLabF = new ArrayList<>();
//        List<String> compField = new ArrayList<>();
//        List<String> pnBg = new ArrayList<>();
//        codigo.add("    private JLabel labelAviso = new JLabel(\"Avisos\");");
//        codigo.add("    private JLabel labelTitulo = new JLabel(\"" + descricaoEntidade.get(0).split(";")[3] + ": \");");
//        for (int i = 0; i < descricaoEntidade.size(); i++) {
//            String[] aux = descricaoEntidade.get(i).split(";");
//            try {
//                Integer.valueOf(aux[2]);
//                String linha = "    private JLabel lb" + st.primUp(aux[0]) + " = new JLabel(\"" + aux[3] + "\");";
//                String linha2 = "    private JTextField fd" + st.primUp(aux[0]) + " = new JTextField(" + aux[2] + ");";
//                compLab.add("lb" + st.primUp(aux[0]));
//                compLabF.add("lb" + st.primUp(aux[0]));
//                compField.add("fd" + st.primUp(aux[0]));
//                CamposModif.add("fd" + st.primUp(aux[0]));
//                camposCompare.add("fd" + st.primUp(aux[0]));
//                codigo.add(linha);
//                codigo.add(linha2);
//                codigo.add("");
//            } catch (Exception e) {
//                if (aux[2].equals("JRadioButton")) {
//                    String pn = "pn" + st.primUp(aux[0]);
//                    String add = "lb" + aux[0] + "," + pn + ",";
//                    compLabF.add("lb" + (aux[0]));
//                    String group = "rdGroup" + st.primUp(aux[0]);
//                    String[] x = aux[3].split(",");
//                    pnBg.add(pn);
//                    for (int j = 0; j < x.length; j++) {
//                        add += "rd" + x[j] + ",";
//                        codigo.add("    JRadioButton rd" + x[j] + " = new JRadioButton(\"" + x[j] + "\");");
//                        CamposModif.add("rd" + x[j]);
//                        pnBg.add("rd" + x[j]);
//                    }
//                    camposCompare.add(add);
//                    listaRd.add(add);
//                    codigo.add("    JLabel lb" + aux[0] + " = new JLabel(\"" + aux[aux.length - 1] + "\");");
//                    codigo.add("    JPanel " + pn + " = new JPanel();");
//                    codigo.add("    ButtonGroup " + group + " = new ButtonGroup();");
//
////                } else if (aux[2].equals("JComboBox")) { //TIRAR COMBOBOX
////                    String[] itens = aux[3].split(",");
////                    String y = "";
////                    for (int j = 0; j < itens.length; j++) {
////                        y += itens[j] + ",";
////                    }
////                    codigo.add("JLabel lb" + aux[0] + " = new JLabel(\"" + aux[aux.length - 1] + "\");");
////                    codigo.add("String auxiliar = " + "\"" + y + "\"");
////                    codigo.add("JComboBox combo" + aux[0] + " = new JComboBox(auxiliar.split(\",\");");
////                    Textos.add("lb" + (aux[0]));
////                    Campos.add("combo" + aux[0]);
////                    camposCompare.add("combo" + aux[0]);
//                } else if (aux[1].equals("Date")) {
//                    codigo.add("    private JSpinner spinner" + aux[0] + " = new JSpinner(new SpinnerDateModel());");
//                    codigo.add("    private final JSpinner.DateEditor spinnerEditor" + aux[0] + " = new JSpinner.DateEditor(spinner" + aux[0] + ", \"dd/MM/yyyy\");");
//                    codigo.add("    private JLabel lb" + st.primUp(aux[0]) + " = new JLabel(\"" + aux[2] + "\");");
//                    String x = "lb" + st.primUp(aux[0]) + "," + "spinner" + aux[0] + "," + "spinnerEditor" + aux[0];
//                    CamposModif.add("spinner" + aux[0]);
//                    camposCompare.add("spinner" + aux[0]);
//                    CamposSp.add(x);
//                    compLabF.add("lb" + st.primUp(aux[0]));
//                } else if (aux[2].equals("JCheckBox")) {
//                    String pn = "pn" + st.primUp(aux[0]);
//                    pnBg.add(pn);
//                    compLabF.add("lb" + (aux[0]));
//                    codigo.add("    JLabel lb" + aux[0] + " = new JLabel(\"" + aux[aux.length - 1] + "\");");
//                    codigo.add("    JPanel " + pn + " = new JPanel();");
//                    String add = "lb" + aux[0] + "," + pn + ",";
//                    String[] x = aux[3].split(",");
//                    for (int j = 0; j < x.length; j++) {
//                        add += "check" + x[j] + ",";
//                        codigo.add("    JCheckBox check" + x[j] + " = new JCheckBox(\"" + x[j] + "\");");
//                        CamposModif.add("check" + x[j]);
//                        pnBg.add("check" + x[j]);
//                    }
//                    camposCompare.add(add);
//                    listaCb.add(add);
//                }
//            }
//        }
//        codigo.add("");
//        codigo.add("    private JPanel painelNortes = new JPanel(new GridLayout(2, 1));");
//        codigo.add("    private JPanel painelNorteCima = new JPanel();");
//        codigo.add("    private JPanel painelNorteBaixo = new JPanel();");
//        codigo.add("    private JPanel painelCentralFora = new JPanel(new BorderLayout());");
//        codigo.add("    private JPanel painelCentral = new JPanel();");
//        codigo.add("    private JPanel painelSul = new JPanel();");
//        codigo.add("    private JLabel labelBranco = new JLabel();");
//        codigo.add("");
//        codigo.add("    JButton btInserir = new JButton(new ImageIcon(getClass().getResource(\"/icones/add.png\")));");
//        codigo.add("    JButton btSalvar = new JButton(new ImageIcon(getClass().getResource(\"/icones/confirmar.png\")));");
//        codigo.add("    JButton btRemover = new JButton(new ImageIcon(getClass().getResource(\"/icones/deletar.png\")));");
//        codigo.add("    JButton btAtualizar = new JButton(new ImageIcon(getClass().getResource(\"/icones/att.png\")));");
//        codigo.add("    JButton btBuscar = new JButton(new ImageIcon(getClass().getResource(\"/icones/search.png\")));");
//        codigo.add("    JButton btCancelar = new JButton(new ImageIcon(getClass().getResource(\"/icones/cancelar.png\")));");
//        codigo.add("    JButton btListar = new JButton(new ImageIcon(getClass().getResource(\"/icones/listar.png\")));");
//        codigo.add("");
//        codigo.add("    Controle controle = new Controle();");
//        codigo.add("    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();");
//        codigo.add("    List<String> dados = new ArrayList<>();");
//        codigo.add("    Boolean acao;");
//        codigo.add("    Font fonte = new Font(\"Courier New\", Font.BOLD, 20);");
//        codigo.add("    Font fonteL = new Font(\"Courier New\", Font.PLAIN, 14);");
//        codigo.add("");
//        codigo.add("    private SimpleDateFormat sdf = new SimpleDateFormat(\"dd/MM/yyyy\");");
//        codigo.add("");
//        String novoElClasse = "    " + nomeClasseUp + " " + nomeClasse + " = new " + nomeClasseUp + "();";
//        codigo.add(novoElClasse);
//        codigo.add("");
//        codigo.add("    public GUI(){");
//        codigo.add("        dados = manipulaArquivo.abrirArquivo(\"dados.txt\");");
//        codigo.add("        String aux[];");
//        codigo.add("        try {");
//        codigo.add("            for (String linha : dados) {");
//        codigo.add("              aux = linha.split(\";\");");
//        codigo.add("             controle.inserir(new " + nomeClasseUp + "(");
//        for (int i = 0; i < descricaoEntidade.size(); i++) {
//            String[] aux = descricaoEntidade.get(i).split(";");
//            if (i == descricaoEntidade.size() - 1) {
//                if (aux[1].equals("int")) {
//                    codigo.add("            Integer.valueOf(aux[" + i + "])));");
//                } else if (aux[1].equals("double")) {
//                    codigo.add("            Double.valueOf(aux[" + i + "])));");
//                } else if (aux[1].equals("String")) {
//                    codigo.add("            aux[" + i + "]));");
//                } else if (aux[1].equals("long")) {
//                    codigo.add("            Long.valueOf(aux[" + i + "])));");
//                } else if (aux[1].equals("boolean")) {
//                    codigo.add("            Boolean.valueOf(aux[" + i + "])));");
//                } else if (aux[1].equals("Date")) {
//                    codigo.add("            sdf.parse(aux[" + i + "])));");
//                }
//            } else if (aux[1].equals("int")) {
//                codigo.add("              Integer.valueOf(aux[" + i + "]),");
//            } else if (aux[1].equals("double")) {
//                codigo.add("              Double.valueOf(aux[" + i + "]),");
//            } else if (aux[1].equals("String")) {
//                codigo.add("              aux[" + i + "],");
//            } else if (aux[1].equals("long")) {
//                codigo.add("              Long.valueOf(aux[" + i + "]),");
//            } else if (aux[1].equals("boolean")) {
//                codigo.add("              Boolean.valueOf(aux[" + i + "]),");
//            } else if (aux[1].equals("Date")) {
//                codigo.add("              sdf.parse(aux[" + i + "]),");
//            }
//        }
//        codigo.add("            }");
//        codigo.add("        } catch (Exception erro) {");
//        codigo.add("        }");
//        codigo.add("");
//        int xS = 725, yS = 340;
//        if (descricaoEntidade.size() - 1 > 2) { //************
//            yS = yS + (((descricaoEntidade.size() - 1) - 2) * 40);
//        }
//        codigo.add("        setSize(" + xS + ", " + yS + ");");
//        codigo.add("        setDefaultCloseOperation(DISPOSE_ON_CLOSE);");
//        codigo.add("        cp = getContentPane();");
//        codigo.add("        cp.setLayout(new BorderLayout());");
//        codigo.add("        setTitle(\"Cadastro de " + nomeClasseUp + "s\");");
//        codigo.add("");
//        codigo.add("        painelCentral.setLayout(new GridLayout(" + descricaoEntidade.size() + ", 2));");
//
//        for (int x = 1; x < compLab.size(); x++) {
//            codigo.add("        painelCentral.add(" + compLab.get(x) + ");");
//            codigo.add("        painelCentral.add(" + compField.get(x) + ");");
//        }
//        codigo.add("");
//        for (String componente : compField) {
//            if (!componente.equals("fd" + pKeyUp)) {
//                codigo.add("        " + componente + ".setEnabled(false);");
//            }
//        }
//
//        for (String x : listaRd) {
//            String[] k = x.split(",");
//            codigo.add("        painelCentral.add(" + k[0] + ");");
//            for (int j = 2; j < k.length; j++) {
//                codigo.add("        " + k[1] + ".add(" + k[j] + ");");
//                codigo.add("        " + k[j] + ".setEnabled(false);");
//            }
//            codigo.add("        painelCentral.add(" + k[1] + ");");
//        }
//        for (String x : CamposSp) {
//            String[] k = x.split(",");
//            codigo.add("        painelCentral.add(" + k[0] + ");");
//            codigo.add("        painelCentral.add(" + k[1] + ");");
//            codigo.add("        " + k[1] + ".setEditor(" + k[2] + ");");
//            codigo.add("        " + k[1] + ".setEnabled(false);");
//        }
//        for (String x : listaCb) {
//            String[] k = x.split(",");
//            codigo.add("        painelCentral.add(" + k[0] + ");");
//            for (int j = 2; j < k.length; j++) {
//                codigo.add("        " + k[1] + ".add(" + k[j] + ");");
//                codigo.add("        " + k[j] + ".setEnabled(false);");
//            }
//            codigo.add("        painelCentral.add(" + k[1] + ");");
//        }
//        codigo.add("");
//        codigo.add("        cp.add(painelNortes, BorderLayout.NORTH);");
//        codigo.add("        cp.add(painelCentralFora, BorderLayout.CENTER);");
//        codigo.add("        cp.add(painelSul, BorderLayout.SOUTH);");
//        codigo.add("");
//        codigo.add("        painelCentralFora.add(labelBranco, BorderLayout.NORTH);");
//        codigo.add("        painelCentralFora.add(painelCentral, BorderLayout.SOUTH);");
//        codigo.add("        painelNortes.add(painelNorteCima);");
//        codigo.add("        painelNortes.add(painelNorteBaixo);");
//        codigo.add("        painelNorteCima.add(labelTitulo);");
//        codigo.add("        painelNorteCima.add(fd" + pKeyUp + ");");
//        codigo.add("        painelNorteBaixo.add(btBuscar);");
//        codigo.add("        painelNorteBaixo.add(btInserir);");
//        codigo.add("        painelNorteBaixo.add(btAtualizar);");
//        codigo.add("        painelNorteBaixo.add(btRemover);");
//        codigo.add("        painelNorteBaixo.add(btSalvar);");
//        codigo.add("        painelNorteBaixo.add(btCancelar);");
//        codigo.add("        painelNorteBaixo.add(btListar);");
//        codigo.add("        painelNorteCima.setBackground(Color.white);");
//        codigo.add("        painelNorteBaixo.setBackground(Color.white);");
//        codigo.add("        painelCentralFora.setBackground(Color.white);");
//        codigo.add("        painelCentral.setBackground(Color.white);");
//        codigo.add("        painelSul.setBackground(Color.white);");
//        codigo.add("        btInserir.setBackground(Color.WHITE);");
//        codigo.add("        btSalvar.setBackground(Color.WHITE);");
//        codigo.add("        btRemover.setBackground(Color.WHITE);");
//        codigo.add("        btAtualizar.setBackground(Color.WHITE);");
//        codigo.add("        btBuscar.setBackground(Color.WHITE);");
//        codigo.add("        btCancelar.setBackground(Color.WHITE);");
//        codigo.add("        btListar.setBackground(Color.WHITE);");
//        for (String x : pnBg) {
//            codigo.add("        " + x + ".setBackground(Color.WHITE);");
//        }
//        codigo.add("");
//        codigo.add("        labelTitulo.setFont(new Font(\"Courier New\", Font.BOLD, 20));");
//        codigo.add("        fd" + pKeyUp + ".setFont(new Font(\"Courier New\", Font.PLAIN, 20));"); // ***** ARRUMAR FONTES
//        for (String x : compLabF) {
//            codigo.add("        " + x + ".setFont(new Font(\"Courier New\", Font.BOLD, 17));");
//        }
//        for (String x : CamposModif) {
//            codigo.add("        " + x + ".setFont(new Font(\"Courier New\", Font.PLAIN, 17));");
//        }
//        codigo.add("        labelAviso.setFont(new Font(\"Courier New\", Font.BOLD, 20));");
//        codigo.add("        btInserir.setVisible(false);");
//        codigo.add("        btAtualizar.setVisible(false);");
//        codigo.add("        btRemover.setVisible(false);");
//        codigo.add("        btSalvar.setVisible(false);");
//        codigo.add("        btCancelar.setVisible(false);");
//        codigo.add("");
//        codigo.add("        painelSul.add(labelAviso);");
//        codigo.add("");
//        codigo.add("        btBuscar.addActionListener(new ActionListener() {");
//        codigo.add("            @Override");
//        codigo.add("            public void actionPerformed(ActionEvent e) {");
//        codigo.add("                try{");
//        codigo.add("                    " + nomeClasse + " = new " + nomeClasseUp + "();");
//        if (pKeyType.equals("int")) {
//            codigo.add("                    int " + pKey + " = Integer.valueOf(fd" + pKeyUp + ".getText());");
//        } else if (pKeyType.equals("double")) {
//            codigo.add("                    Double " + pKey + " = Double.valueOf(fd" + pKeyUp + ".getText());");
//        } else if (pKeyType.equals("String")) {
//            codigo.add("                    String " + pKey + " = fd" + pKeyUp + ".getText();");
//        } else if (pKeyType.equals("long")) {
//            codigo.add("                    long " + pKey + " = Long.valueOf(fd" + pKeyUp + ".getText());");
//        }
////        } else if (TipoC.equals("boolean")) {
////            for (String x : listaRd) {
////                if (x.split(",")[1].equals("pn" + Chave_Maiusculo)) {
////                    codigo.add("boolean chaveX = null");
////                    codigo.add("if(" + x.split(",")[3] + ".isSelected(){");
////                    codigo.add("chaveX = true;} else {chaveX = false;}");
////                }
////            }
////            for (String x : listaCb) {
////                if (x.split(",")[1].equals("pn" + Chave_Maiusculo)) {
////                    codigo.add("boolean chaveX = null");
////                    codigo.add("if(" + x.split(",")[3] + ".isSelected(){");
////                    codigo.add("chaveX = true;} else {chaveX = false;}");
////                }
////            }
////            codigo.add("boolean " + Chave + " = chaveX;");
//        if (pKeyType.equals("int")) {
//            codigo.add("                    " + nomeClasse + ".set" + pKeyUp + "(Integer.valueOf(fd" + pKeyUp + ".getText()));");
//        } else if (pKeyType.equals("Double")) {
//            codigo.add("                    " + nomeClasse + ".set" + pKeyUp + "(Double.valueOf(fd" + pKeyUp + ".getText()));");
//        } else if (pKeyType.equals("String")) {
//            codigo.add("                    " + nomeClasse + ".set" + pKeyUp + "(fd" + pKeyUp + ".getText());");
//        } else if (pKeyType.equals("long")) {
//            codigo.add("                    " + nomeClasse + ".set" + pKeyUp + "(Long.valueOf(fd" + pKeyUp + ".getText()));");
//        }
////        } else if (TipoC.equals("boolean")) {
////            codigo.add("" + Classe + ".set" + Chave_Maiusculo + "(" + Chave + ");");
//        codigo.add("                    " + nomeClasse + " = controle.buscar(" + nomeClasse + ");");
//        codigo.add("                    labelAviso.setBackground(Color.green);");
//        codigo.add("                    if (" + nomeClasse + " != null) {");
//        for (int i2 = 0; i2 < camposCompare.size(); i2++) {
//            String type = descricaoEntidade.get(i2).split(";")[1];
//            if (type.equals("boolean")) {
//                codigo.add("                        if (" + nomeClasse + ".get" + st.primUp(descricaoEntidade.get(i2).split(";")[0]) + "()) {");
//                codigo.add("                            " + camposCompare.get(i2).split(",")[2] + ".setSelected(true);");
//                codigo.add("                        } else {");
//                codigo.add("                            " + camposCompare.get(i2).split(",")[2] + ".setSelected(false);");
//                codigo.add("                        }");
//                //TRYCATCH PARA VERIFICAR SE HÁ SEGUNDO
//            } else if (type.equals("Date")) {
//                codigo.add("                        " + (String) camposCompare.get(i2) + ".setValue(" + nomeClasse + ".get" + st.primUp(descricaoEntidade.get(i2).split(";")[0]) + "());");
//            } else {
//                codigo.add("                        " + (String) camposCompare.get(i2) + ".setText(" + nomeClasse + ".get" + st.primUp(descricaoEntidade.get(i2).split(";")[0]) + "() + \"\");");
//            }
//        }
//        codigo.add("                        btAtualizar.setVisible(true);");
//        codigo.add("                        btRemover.setVisible(true);");
//        codigo.add("                        btInserir.setVisible(false);");
//        codigo.add("                        btListar.setVisible(false);");
//        codigo.add("                        labelAviso.setText(\"Encontrado na lista!\");");
//        codigo.add("                        labelAviso.setBackground(Color.green);");
//        codigo.add("                    } else {");
//
//        for (int i = 0; i < compField.size(); ++i) {
//            if (!compField.get(i).equals("fd" + pKeyUp)) {
//                codigo.add("                        " + compField.get(i) + ".setEnabled(false);");
//                codigo.add("                        " + compField.get(i) + ".setText(null);");
//            }
//        }
//        for (int i = 0; i < listaRd.size(); ++i) {
//            String x = listaRd.get(i).split(",")[2];
//            codigo.add("                        " + x + ".setEnabled(false);");
//            codigo.add("                        " + x + ".setSelected(false);");
//        }
//        for (int i = 0; i < listaCb.size(); ++i) {
//            String x = listaCb.get(i).split(",")[2];
//            codigo.add("                        " + x + ".setEnabled(false);");
//            codigo.add("                        " + x + ".setSelected(false);");
//        }
//        for (int i = 0; i < CamposSp.size(); ++i) {
//            String x = CamposSp.get(i).split(",")[1];
//            codigo.add("                        " + x + ".setEnabled(false);");
//            codigo.add("                        " + x + ".setValue(new Date());");
//        }
//
//        codigo.add("                        labelAviso.setText(\"Não encontrado!\");");
//        codigo.add("                        labelAviso.setBackground(Color.red);");
//        codigo.add("                        btInserir.setVisible(true);");
//        codigo.add("                        btAtualizar.setVisible(false);");
//        codigo.add("                        btRemover.setVisible(false);");
//        codigo.add("                        btListar.setVisible(false);");
//        codigo.add("                    }");
//        codigo.add("                } catch (Exception erro) {");
//        codigo.add("                    labelAviso.setText(\"Preencha os campos corretamente!\");");
//        codigo.add("                    labelAviso.setBackground(Color.red);");
//        codigo.add("                }");
//        codigo.add("            }");
//        codigo.add("        }");
//        codigo.add("        );");
//        codigo.add("");
//        codigo.add("        btInserir.addActionListener(new ActionListener() {");
//        codigo.add("            @Override");
//        codigo.add("            public void actionPerformed(ActionEvent e) {");
//        codigo.add("                acao = true;");
//        codigo.add("                fd" + pKeyUp + ".setEnabled(false);");
//        for (String componente : CamposModif) {
//            if (!componente.equals("fd" + pKeyUp)) {
//                codigo.add("                " + componente + ".setEnabled(true);");
//            }
//        }
//        codigo.add("                btSalvar.setVisible(true);");
//        codigo.add("                btCancelar.setVisible(true);");
//        codigo.add("                btBuscar.setVisible(false);");
//        codigo.add("                btInserir.setVisible(false);");
//        codigo.add("                btListar.setVisible(false);");
//        codigo.add("                fd" + st.primUp(descricaoEntidade.get(1).split(";")[0]) + ".requestFocus();");
//        codigo.add("            }");
//        codigo.add("        }");
//        codigo.add("        );");
//        codigo.add("");
//        codigo.add("        btSalvar.addActionListener(new ActionListener() {");
//        codigo.add("            @Override");
//        codigo.add("            public void actionPerformed(ActionEvent e) {");
//        codigo.add("                if(acao){ //btInserir");
//        codigo.add("                    try {");
//        codigo.add("                    " + novoElClasse);
//        int cont = 0;
//        for (String linha : descricaoEntidade) {
//            String nome = st.primUp(linha.split(";")[0]);
//            String type = linha.split(";")[1];
//            if (type.equals("int")) {
//                codigo.add("                        " + nomeClasse + ".set" + nome + "(Integer.valueOf(" + camposCompare.get(cont) + ".getText()));");
//            } else if (type.equals("double")) {
//                codigo.add("                        " + nomeClasse + ".set" + nome + "(Double.valueOf(" + camposCompare.get(cont) + ".getText()));");
//            } else if (type.equals("String")) {
//                codigo.add("                        " + nomeClasse + ".set" + nome + "(" + camposCompare.get(cont) + ".getText());");
//            } else if (type.equals("long")) {
//                codigo.add(nomeClasse + ".set" + nome + "(Long.valueOf(" + (String) camposCompare.get(cont) + ".getText()));");
//            } else if (type.equals("boolean")) { // ***************
//                String campo = camposCompare.get(cont).split(",")[2];
//                if (linha.split(";")[2].equals("JCheckBox") || linha.split(";")[2].equals("JRadioButton")) {
//                    codigo.add("                        Boolean resp" + nome + " = null;");
//                    codigo.add("                        if (" + campo + ".isSelected()) {");
//                    codigo.add("                            resp" + nome + " = true;");
//                    if (camposCompare.get(cont).split(",").length > 3) {
//                        codigo.add("                        } else if (" + camposCompare.get(cont).split(",")[3] + ".isSelected()) {");
//                        codigo.add("                            resp" + nome + " = false;");
//                        codigo.add("                        } else {");
//                        codigo.add("                            int a = 1 / 0;");
//                        codigo.add("                        }");
//                    } else {
//                        codigo.add("                        } else {");
//                        codigo.add("                            resp" + nome + " = false;");
//                        codigo.add("                        }");
//                    }
//
//                    codigo.add("                        " + nomeClasse + ".set" + nome + "(resp" + nome + ");");
//
//                }
//            } else if (type.equals("Date")) {
//                codigo.add("                        " + nomeClasse + ".set" + nome + "((Date) " + camposCompare.get(cont) + ".getValue());");
//            }
//
//            cont++;
//        }
//        codigo.add("                        controle.inserir(" + nomeClasse + ");");
//        codigo.add("                        labelAviso.setText(\"Registro inserido com sucesso!\");");
//        codigo.add("                        fd" + pKeyUp + ".setEnabled(true);");
//        codigo.add("                        fd" + pKeyUp + ".requestFocus();");
//        for (String componente : compField) {
//            if (!componente.equals("fd" + pKeyUp)) {
//                codigo.add("                        " + componente + ".setEnabled(false);");
//            }
//        }
//        for (int i = 0; i < CamposModif.size(); ++i) {
//            if (!CamposModif.get(i).equals("fd" + pKeyUp)) {
//                codigo.add("                        " + CamposModif.get(i) + ".setEnabled(false);");
//            }
//        }
//        codigo.add("                        btSalvar.setVisible(false);");
//        codigo.add("                        btCancelar.setVisible(false);");
//        codigo.add("                        btBuscar.setVisible(true);");
//        codigo.add("                        btListar.setVisible(true);");
//        codigo.add("                    } catch (Exception erro) {");
//        codigo.add("                        labelAviso.setText(\"Erro nos dados!\");");
//        codigo.add("                    }");
//        codigo.add("                } else { //btAlterar");
//        codigo.add("                    try {");
//        codigo.add("                        " + nomeClasseUp + " " + nomeClasse + "Original = " + nomeClasse + ";");
//        codigo.add("                        " + nomeClasseUp + " " + nomeClasse + "Modificado = new " + nomeClasseUp + "();");
//        cont = 0;
//        for (String linha : descricaoEntidade) {
//            String nome = st.primUp(linha.split(";")[0]);
//            String type = linha.split(";")[1];
//            if (type.equals("int")) {
//                codigo.add("                        " + nomeClasse + "Modificado.set" + nome + "(Integer.valueOf(" + camposCompare.get(cont) + ".getText()));");
//            } else if (type.equals("double")) {
//                codigo.add("                        " + nomeClasse + "Modificado.set" + nome + "(Double.valueOf(" + camposCompare.get(cont) + ".getText()));");
//            } else if (type.equals("String")) {
//                codigo.add("                        " + nomeClasse + "Modificado.set" + nome + "(" + camposCompare.get(cont) + ".getText());");
//            } else if (type.equals("long")) {
//                codigo.add("                        " + nomeClasse + "Modificado.set" + nome + "(Long.valueOf(" + (String) camposCompare.get(cont) + ".getText()));");
//            } else if (type.equals("boolean")) { // ***************
//                String campo = camposCompare.get(cont).split(",")[2];
//                if (linha.split(";")[2].equals("JCheckBox")) {
//                    codigo.add("                        Boolean resp" + nome + " = null;");
//                    codigo.add("                        if (" + campo + ".isSelected()) {");
//                    codigo.add("                            resp" + nome + " = true; ");
//                    codigo.add("                        } else {");
//                    codigo.add("                            resp" + nome + " = false;");
//                    codigo.add("                        }");
//                    codigo.add("                        " + nomeClasse + "Modificado.set" + nome + "(resp" + nome + ");");
//                } else if (linha.split(";")[2].equals("JRadioButton")) {
//                    codigo.add("                        Boolean resp" + nome + " = null;");
//                    codigo.add("                        if(" + campo + ".isSelected()){ resp" + nome + " = true; } else {resp" + nome + " = false;} ");
//                    codigo.add("                        " + nomeClasse + "Modificado.set" + nome + "(resp" + nome + ");");
//                }
//            } else if (type.equals("Date")) {
//                codigo.add("                        " + nomeClasse + "Modificado.set" + nome + "((Date) " + camposCompare.get(cont) + ".getValue());");
//            }
//
//            ++cont;
//        }
//        codigo.add("                        controle.alterar(" + nomeClasse + "Original" + ", " + nomeClasse + "Modificado);");
//        codigo.add("                        labelAviso.setText(\"Registro alterado com sucesso!\");");
//        for (String componente : compField) {
//            if (!componente.equals("fd" + pKeyUp)) {
//                codigo.add("                        " + componente + ".setEnabled(false);");
//            }
//        }
//        codigo.add("                        fd" + pKeyUp + ".setEnabled(true);");
//        codigo.add("                        fd" + pKeyUp + ".requestFocus();");
//        for (int i = 0; i < CamposModif.size(); ++i) {
//            if (!CamposModif.get(i).equals("fd" + pKeyUp)) {
//                codigo.add("                        " + CamposModif.get(i) + ".setEnabled(false);");
//            }
//        }
//        codigo.add("                        btSalvar.setVisible(false);");
//        codigo.add("                        btCancelar.setVisible(false);");
//        codigo.add("                        btBuscar.setVisible(true);");
//        codigo.add("                        btListar.setVisible(true);");
//        codigo.add("                    } catch (Exception erro) {");
//        codigo.add("                        labelAviso.setText(\"Erro nos dados!\");");
//        codigo.add("                    }");
//        codigo.add("                }");
//        codigo.add("            }");
//        codigo.add("        }");
//        codigo.add("    );");
//        codigo.add("");
//        codigo.add("        btCancelar.addActionListener(new ActionListener() {");
//        codigo.add("            @Override");
//        codigo.add("            public void actionPerformed(ActionEvent e) {");
//        codigo.add("                labelAviso.setText(\"Cancelado!\");");
//        
//        for (String componente : CamposModif) {
//            codigo.add("                " + componente + ".setEnabled(false);");
//            if (componente.substring(0, 2).equals("rd") || componente.substring(0, 2).equals("ch")) {
//                codigo.add("                " + componente + ".setSelected(false);");
//            } else if (componente.substring(0, 2).equals("fd")) {
//                codigo.add("                " + componente + ".setText(\"\");");
//            } else if(componente.substring(0, 2).equals("sp")){
//                codigo.add("                " + componente + ".setValue(new Date());");
//            }
//        }
//        
//        codigo.add("                fd" + pKeyUp + ".setEnabled(true);");
//        codigo.add("                fd" + pKeyUp + ".requestFocus();");
//        codigo.add("                btSalvar.setVisible(false);");
//        codigo.add("                btCancelar.setVisible(false);");
//        codigo.add("                btBuscar.setVisible(true);");
//        codigo.add("                btListar.setVisible(true);");
//        codigo.add("            }");
//        codigo.add("        }");
//        codigo.add("        );");
//        codigo.add("");
//        codigo.add("        btAtualizar.addActionListener(new ActionListener() {");
//        codigo.add("            @Override");
//        codigo.add("            public void actionPerformed(ActionEvent e) {");
//        codigo.add("                acao = false;");
//        for (String componente : CamposModif) {
//            if (!componente.equals("fd" + pKeyUp)) {
//                codigo.add("                " + componente + ".setEnabled(true);");
//            }
//        }
//        codigo.add("                " + compField.get(1) + ".requestFocus();");
//        codigo.add("                fd" + pKeyUp + ".setEnabled(false);");
//        codigo.add("                btSalvar.setVisible(true);");
//        codigo.add("                btCancelar.setVisible(true);");
//        codigo.add("                btBuscar.setVisible(false);");
//        codigo.add("                btRemover.setVisible(false);");
//        codigo.add("                btAtualizar.setVisible(false);");
//        codigo.add("                btListar.setVisible(false);");
//        codigo.add("            }");
//        codigo.add("        }");
//        codigo.add("        );");
//        codigo.add("");
//        codigo.add("        btRemover.addActionListener(new ActionListener() {");
//        codigo.add("            @Override");
//        codigo.add("            public void actionPerformed(ActionEvent e) {");
//        codigo.add("                btRemover.setVisible(false);");
//        codigo.add("                btListar.setVisible(false);");
//        codigo.add("                btAtualizar.setVisible(false);");
//        codigo.add("                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, \"Confirma a exclusão do registro <Chave = \" + " + nomeClasse + ".get" + pKeyUp + "() + \" >?\", \"Confirm\", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {");
//        codigo.add("                    controle.remover(" + nomeClasse + ");");
//        codigo.add("                    labelAviso.setText(\"Removido!\");");
//        for (String componente : compField) {
//            codigo.add("                    " + componente + ".setText(\"\");");
//        }
//        codigo.add("                    btListar.setVisible(true);");
//        codigo.add("                } else {");
//        codigo.add("                    labelAviso.setText(\"Remoção cancelada!\");");
//        codigo.add("                    btAtualizar.setVisible(true);");
//        codigo.add("                    btRemover.setVisible(true);");
//        codigo.add("                }");
//        codigo.add("            }");
//        codigo.add("        }");
//        codigo.add("        );");
//        codigo.add("");
//        codigo.add("        btListar.addActionListener(new ActionListener() {");
//        codigo.add("            @Override");
//        codigo.add("            public void actionPerformed(ActionEvent e) {");
//        codigo.add("                new " + nomeClasseUp + "GUIListagem(controle.retornaLista(), 768, 350);");
//        codigo.add("            }");
//        codigo.add("        }");
//        codigo.add("        );");
//        codigo.add("");
//        codigo.add("        addWindowListener(new WindowAdapter() {");
//        codigo.add("            @Override");
//        codigo.add("            public void windowClosing(WindowEvent e) {");
//        codigo.add("                dados.clear();");
//        codigo.add("                List<" + nomeClasseUp + "> lista = controle.retornaLista();");
//        codigo.add("                for (" + nomeClasseUp + " entidade : lista) {");
//        codigo.add("                    dados.add(entidade.toString());");
//        codigo.add("                }");
//        codigo.add("                manipulaArquivo.salvarArquivo(\"dados.txt\", dados);");
//        codigo.add("                System.exit(0);");
//        codigo.add("            }");
//        codigo.add("        }");
//        codigo.add("        );");
//        codigo.add("        setLocationRelativeTo(null);");
//        codigo.add("        setVisible(true);");
//        codigo.add("    }");
//        codigo.add("}");
//
//        new ManipulaArquivo().salvarArquivo("src/classeGerada/GUI.java", codigo);
//    }
//}
