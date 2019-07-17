/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import Auxiliar.*;

/**
 *
 * @author Alcides
 */
public class GUI extends JFrame {

    private Container cp;

    JTextField fieldCaminho = new JTextField(20);

    JLabel labelTitulo = new JLabel("Gerador de código");
    JLabel labelCaminho = new JLabel("Caminho: ");

    JLabel labelNomeVar = new JLabel("Nome váriavel");
    JLabel labelTipo = new JLabel("Tipo");
    JLabel labelTamanho = new JLabel("Tamanho");
    JLabel labelOpcao = new JLabel("Opções (;)");
    JLabel labelNomeUs = new JLabel("Nome para usuário");
    JLabel labelTabela = new JLabel("Listagem de atributos");
    JLabel labelBuscar = new JLabel("Nome do atributo");
    JLabel labelCaminhoFK = new JLabel("Caminho (FK)");
    JLabel labelGerar = new JLabel("Gerar");

    JLabel labelRemover = new JLabel("Nome do atributo");
    JComboBox remov = new JComboBox();

    JTextField fdNomeVar = new JTextField();
    JTextField fdTipo = new JTextField();
    JTextField fdTamanho = new JTextField();
    JTextField fdOpcao = new JTextField();
    JTextField fdNomeUs = new JTextField();
    JTextField fdCaminho = new JTextField();
    JTextField fdBuscar = new JTextField(10);

    String[] cb = {"int", "double", "long", "String", "boolean", "Date", "Dependente"};
    JComboBox type = new JComboBox(cb);
    String[] op = {"JCheckBox", "JRadioButton"};
    JComboBox opcCbRd = new JComboBox(op);

    JButton botaoCaminho = new JButton("...");
    JButton botaoGerarTodos = new JButton("Todos");
    JButton botaoAddAtrib = new JButton("Adicionar atributo");
    JButton botaoAttAtrib = new JButton("Atualizar atributo");
    JButton botaoRemovAtrib = new JButton("Remover atributo");
    JButton botaoConfirmar = new JButton(new ImageIcon(getClass().getResource("/icones/confirmar.png")));
    JButton botaoCancelar = new JButton(new ImageIcon(getClass().getResource("/icones/cancelar.png")));
    JButton botaoBuscar = new JButton("Buscar atributo");
    JButton botaoGerarGUI = new JButton("GUI");
    JButton botaoGerarEntidade = new JButton("Entidade");
    JButton botaoGerarControle = new JButton("Controle");
    JButton botaoGerarListagem = new JButton("Listagem");

    JCheckBox checkImg = new JCheckBox("Imagem");

    JPanel painelTitulo = new JPanel();
    JPanel painelCaminho = new JPanel();
    JPanel painelCRUD = new JPanel();
    JPanel painelCC = new JPanel();
    JPanel painelTabela = new JPanel();
    JPanel painelGerar = new JPanel();
    JPanel painelEntidade = new JPanel();
    JPanel painelGerarIn = new JPanel();
    JPanel painelGerarLab = new JPanel();

    String extensao;
    File arquivo;

    JFileChooser file = new JFileChooser();
    Boolean opc;

    Boolean opc2 = false;

    int cont;

    static String caminho = "";

    static DefaultTableModel modelo = new DefaultTableModel(new String[]{"Nome", "Tipo", "Nome usuário", "Tam/Objeto", "Opção", "Caminho IMG/FK"}, 10);
    static JTable tabela = new JTable(modelo);

    static JScrollPane scrollPaneTabela = new JScrollPane(tabela);

    static List<String> lista = new ArrayList<>();
    static List<String> listaAtributos = new ArrayList<>();
    static List<String> listaBuscar = new ArrayList<>();

    Font fonte = new Font("Courier New", Font.PLAIN, 14);
    Font fonteL = new Font("Courier New", Font.BOLD, 18);
    // menu com dois geradores, um para independente e outro para dependente, formato da data

    JPanel painelMenu = new JPanel();
    JPanel painelMenuLb = new JPanel();
    JPanel painelCaminhos = new JPanel();
    JPanel painelGerarMenu = new JPanel();
    JPanel painelMenuCentro = new JPanel();

    JButton botaoCam1 = new JButton("Classe 1");
    JButton botaoCam2 = new JButton("Classe 2 (FK)");
    JButton botaoGerarMenu = new JButton("Gerar");

    JLabel labelMenu = new JLabel("Gerar menu");

    String[] classes = new String[2];

    JTabbedPane tabbedPane = new JTabbedPane();

    public GUI() {
        setSize(740, 575);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new GridLayout(1, 2));
        setTitle("Gerador de código");

        painelMenu.setLayout(new BorderLayout());
        painelMenuCentro.setLayout(new GridLayout(2, 1));
        painelMenuCentro.add(painelCaminhos);
        painelMenuCentro.add(painelGerarMenu);
        painelMenu.add(painelMenuLb, BorderLayout.NORTH);
        painelMenu.add(painelMenuCentro, BorderLayout.CENTER);

        painelMenuLb.add(labelMenu);
        painelCaminhos.add(botaoCam1);
        painelCaminhos.add(botaoCam2);
        painelGerarMenu.add(botaoGerarMenu);

        botaoCam1.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                arquivo = null;
                file.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int i = file.showSaveDialog(null);
                if (i == 1) {
                    botaoCam1.setText("Classe 1");
                } else {
                    arquivo = file.getSelectedFile();
                    botaoCam1.setText(arquivo.getName().substring(0, arquivo.getName().length() - 4));
                    classes[0] = arquivo.getName().substring(0, arquivo.getName().length() - 4);
                }
            }
        }
        );

        botaoCam2.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                arquivo = null;
                file.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int i = file.showSaveDialog(null);
                if (i == 1) {
                    botaoCam2.setText("Classe 2 (FK)");
                } else {
                    arquivo = file.getSelectedFile();
                    botaoCam2.setText(arquivo.getName().substring(0, arquivo.getName().length() - 4));
                    classes[1] = arquivo.getName().substring(0, arquivo.getName().length() - 4);
                }
            }
        }
        );

        botaoGerarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GerarMenu().gerarClasseMenu(classes[0], classes[1]);
                JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso!");
            }
        });

        botaoCam1.setBackground(Color.WHITE);
        botaoCam2.setBackground(Color.WHITE);
        botaoGerarMenu.setBackground(Color.WHITE);
        painelCaminhos.setBackground(Color.WHITE);
        painelMenuCentro.setBackground(Color.WHITE);
        painelMenuLb.setBackground(Color.WHITE);
        painelMenu.setBackground(Color.WHITE);
        painelGerarMenu.setBackground(Color.WHITE);
        painelGerarLab.setBackground(Color.WHITE);
        painelTitulo.setBackground(Color.WHITE);
        painelCaminho.setBackground(Color.WHITE);
        painelCRUD.setBackground(Color.WHITE);
        painelCC.setBackground(Color.WHITE);
        painelTabela.setBackground(Color.WHITE);
        scrollPaneTabela.setBackground(Color.WHITE);
        painelEntidade.setBackground(Color.WHITE);
        painelGerar.setBackground(Color.WHITE);
        painelGerarIn.setBackground(Color.WHITE);
        painelGerarLab.setBackground(Color.WHITE);
        botaoCaminho.setBackground(Color.WHITE);
        botaoGerarTodos.setBackground(Color.WHITE);
        botaoAddAtrib.setBackground(Color.WHITE);
        botaoRemovAtrib.setBackground(Color.WHITE);
        botaoConfirmar.setBackground(Color.WHITE);
        botaoCancelar.setBackground(Color.WHITE);
        botaoAttAtrib.setBackground(Color.WHITE);
        botaoBuscar.setBackground(Color.WHITE);
        botaoGerarGUI.setBackground(Color.WHITE);
        botaoGerarControle.setBackground(Color.WHITE);
        botaoGerarEntidade.setBackground(Color.WHITE);
        botaoGerarListagem.setBackground(Color.WHITE);
        checkImg.setBackground(Color.WHITE);

        labelMenu.setFont(fonteL);
        labelTitulo.setFont(fonteL);
        labelGerar.setFont(fonteL);
        labelTabela.setFont(fonteL);

        botaoCam1.setFont(fonte);
        botaoCam2.setFont(fonte);
        botaoGerarMenu.setFont(fonteL);
        botaoAddAtrib.setFont(fonte);
        botaoAttAtrib.setFont(fonte);
        botaoRemovAtrib.setFont(fonte);
        botaoBuscar.setFont(fonte);
        botaoCaminho.setFont(fonte);
        botaoConfirmar.setFont(fonte);
        botaoCancelar.setFont(fonte);
        botaoGerarControle.setFont(fonte);
        botaoGerarEntidade.setFont(fonte);
        botaoGerarGUI.setFont(fonte);
        botaoGerarListagem.setFont(fonte);
        botaoGerarTodos.setFont(fonte);

        labelBuscar.setFont(fonte);
        labelCaminho.setFont(new Font("Courier New", Font.BOLD, 14));
        labelNomeUs.setFont(fonte);
        labelNomeVar.setFont(fonte);
        labelOpcao.setFont(fonte);
        labelRemover.setFont(fonte);
        labelTamanho.setFont(fonte);
        labelCaminhoFK.setFont(fonte);
        labelTipo.setFont(fonte);
        type.setFont(fonte);
        remov.setFont(fonte);
        opcCbRd.setFont(fonte);
        fdCaminho.setFont(new Font("Courier New", Font.BOLD, 5));
        opcCbRd.setFont(fonte);
        checkImg.setFont(new Font("Courier New", Font.BOLD, 14));

        painelTitulo.setLayout(new FlowLayout());
        painelTitulo.add(labelTitulo);

        painelCaminho.setLayout(new FlowLayout());
        painelCaminho.add(labelCaminho);
        painelCaminho.add(fieldCaminho);
        painelCaminho.add(botaoCaminho);
        painelCaminho.add(checkImg);

        painelCRUD.add(botaoAddAtrib);
        painelCRUD.add(botaoRemovAtrib);
        painelCRUD.add(botaoAttAtrib);

        painelCRUD.setLayout(new FlowLayout());

        painelCC.setLayout(new FlowLayout());
        painelCC.add(botaoConfirmar);
        painelCC.add(botaoCancelar);
        botaoConfirmar.setVisible(true);
        botaoCancelar.setVisible(true);

        painelTabela.setLayout(new GridLayout(1, 1));
        painelTabela.add(scrollPaneTabela);

        painelGerar.setLayout(new BorderLayout());
        painelGerarIn.setLayout(new GridLayout(1, 5));
        painelGerarIn.add(botaoGerarTodos);
        painelGerarIn.add(botaoGerarGUI);
        painelGerarIn.add(botaoGerarEntidade);
        painelGerarIn.add(botaoGerarControle);
        painelGerarIn.add(botaoGerarListagem);
        painelGerarLab.setLayout(new FlowLayout());
        painelGerarLab.add(labelGerar);
        painelGerar.add(painelGerarLab, BorderLayout.NORTH);
        painelGerar.add(painelGerarIn, BorderLayout.SOUTH);

        painelEntidade.setLayout(new GridLayout(6, 1));
        painelEntidade.add(painelTitulo);
        painelEntidade.add(painelCaminho);
        painelEntidade.add(painelCRUD);
        painelEntidade.add(painelCC);
        painelEntidade.add(painelTabela);
        painelCC.setVisible(false);
        painelEntidade.add(painelGerar);
        cp.add(painelEntidade);

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (opc2) {
                    lista.remove(remov.getSelectedIndex());
                    new ManipulaArquivo().salvarArquivo(caminho, lista);
                    atualizaTabela();
                    painelCRUD.removeAll();
                    painelCC.setVisible(false);
                    painelCRUD.add(botaoAddAtrib);
                    painelCRUD.add(botaoRemovAtrib);
                    painelCRUD.add(botaoAttAtrib);
                    opc2 = false;
                } else if (opc) {
                    try {
                        if (fdTamanho.getText().equals("")) {
                            fdTamanho.setText("0");
                        }
                        int i = 0;
                        for (String a : listaAtributos) {
                            System.out.println(a + " " + listaAtributos.get(i));
                            if (fdNomeVar.getText().equals(listaAtributos.get(i))) {
                                int ax = 1 / 0;
                            }
                            i++;
                        }
                        String tipo = type.getSelectedItem().toString();
                        if (tipo.equals("int") || tipo.equals("double") || tipo.equals("long") || tipo.equals("String")) {
                            if (isInt(fdNomeVar.getText().substring(0, 1)) || (isInt(fdTamanho.getText()) == false)) {
                                int a = 1 / 0;
                            }
                            lista.add(fdNomeVar.getText() + ";" + tipo + ";" + fdTamanho.getText() + ";" + fdNomeUs.getText());
                        } else if (tipo.equals("boolean")) {
                            if (isInt(fdNomeVar.getText().substring(0, 1))) {
                                int a = 1 / 0;
                            }
                            lista.add(fdNomeVar.getText() + ";" + tipo + ";" + opcCbRd.getSelectedItem() + ";" + fdOpcao.getText() + ";" + fdNomeUs.getText());
                        } else if (tipo.equals("Date")) {
                            lista.add(fdNomeVar.getText() + ";" + tipo + ";" + fdNomeUs.getText());
                        } else if (tipo.equals("Dependente")) {
                            lista.add(fdNomeVar.getText() + ";" + tipo + ";" + fdNomeUs.getText() + ";" + fdCaminho.getText());
                        }
                        new ManipulaArquivo().salvarArquivo(caminho, lista);
                        atualizaTabela();

                        painelCRUD.setLayout(new FlowLayout());
                        fdNomeUs.setText("");
                        fdNomeVar.setText("");
                        fdOpcao.setText("");
                        fdTamanho.setText("");
                        type.setSelectedIndex(0);
                        painelCRUD.removeAll();
                        painelCRUD.add(botaoAddAtrib);
                        painelCRUD.add(botaoRemovAtrib);
                        painelCRUD.add(botaoAttAtrib);

                        painelCRUD.setLayout(new FlowLayout());
                        painelCC.setVisible(false);
                        setSize(100, 100);
                        setSize(740, 575);
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!");
                    }
                } else {
                    try {
                        painelCRUD.removeAll();
                        int i = 0;
                        Boolean achou = null;
                        String linha = "";
                        int cont = 0;
                        for (String x : lista) {
                            if (x.split(";")[0].equals(remov.getSelectedItem())) {
                                achou = true;
                                linha = x;
                                cont++;
                            }
                        }
                        String[] li = linha.split(";");
                        listaBuscar = new ArrayList<>();
                        for (String x : li) {
                            listaBuscar.add(x);
                        }
                        for (String a : listaAtributos) {
                            if (fdNomeVar.getText().equals(a) && !listaBuscar.get(0).equals(fdNomeVar.getText())) {
                                int ax = 1 / 0;
                            }
                            i++;
                        }
                        String tipo = listaBuscar.get(1);
                        String tamanho = listaBuscar.get(2);
                        String subst = "";
                        if (tipo.equals("int") || tipo.equals("double") || tipo.equals("long") || tipo.equals("String")) {
                            if (isInt(fdNomeVar.getText().substring(0, 1))) {
                                int a = 1 / 0;
                            }
                            subst = (fdNomeVar.getText() + ";" + tipo + ";" + tamanho + ";" + fdNomeUs.getText());
                        } else if (tipo.equals("boolean")) {
                            if (isInt(fdNomeVar.getText().substring(0, 1))) {
                                int a = 1 / 0;
                            }
                            subst = (fdNomeVar.getText() + ";" + tipo + ";" + opcCbRd.getSelectedItem() + ";" + fdOpcao.getText() + ";" + fdNomeUs.getText());
                        } else if (tipo.equals("Date")) {
                            subst = (fdNomeVar.getText() + ";" + tipo + ";" + fdNomeUs.getText());
                        } else if (tipo.equals("Dependente")) {
                            subst = (fdNomeVar.getText() + ";" + tipo + ";" + fdNomeUs.getText() + ";" + fdCaminho.getText());
                        }

                        lista.set(remov.getSelectedIndex(), subst);
                        new ManipulaArquivo().salvarArquivo(caminho, lista);
                        atualizaTabela();

                        painelCRUD.setLayout(new FlowLayout());
                        fdNomeUs.setText("");
                        fdNomeVar.setText("");
                        fdOpcao.setText("");
                        fdTamanho.setText("");
                        type.setSelectedIndex(0);
                        painelCRUD.removeAll();
                        painelCRUD.add(botaoAddAtrib);
                        painelCRUD.add(botaoAttAtrib);
                        painelCRUD.add(botaoRemovAtrib);

                        painelCRUD.setLayout(new FlowLayout());
                        painelCC.setVisible(false);
                        setSize(100, 100);
                        setSize(740, 575);
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!");
                    }
                }
            }
        });

        botaoAttAtrib.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (caminho.equals("")) {
                    JOptionPane.showMessageDialog(null, "Escolha um arquivo para realizar as operações!");
                } else {
                    painelCRUD.setLayout(new FlowLayout());
                    painelCRUD.removeAll();
                    painelCRUD.add(labelBuscar);
                    String auxi = "";
                    for (String x : lista) {
                        auxi += x.split(";")[0] + ",";
                    }
                    String[] comboB = auxi.split(",");
                    remov = new JComboBox(comboB);
                    remov.setFont(fonte);
                    painelCRUD.add(remov);
                    painelCRUD.add(botaoBuscar);
                    opc = false;
                    setSize(100, 100);
                    setSize(740, 575);
                }
            }
        });

        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean achou = false;
                    String linha = "";
                    cont = 0;
                    for (String x : lista) {
                        if (x.split(";")[0].equals(remov.getSelectedItem())) {
                            achou = true;
                            linha = x;
                            cont++;
                        }
                    }
                    if (achou == false) {
                        int a = 1 / 0;
                    }
                    atualizaTabelaPorNome(remov.getSelectedItem() + "");
                    //nome;tipo;tamanho;nomeUsuario
                    //nome;tipo;JCheckBox/JRadioButton;opcao;nomeUsuario
                    //nome;tipo;nomeUsuario
                    //nome;tipo;nomeUsuario;caminhoFK
                    String[] li = linha.split(";");
                    String tipo = li[1];
                    painelCRUD.removeAll();
                    if (tipo.equals("int")
                            || tipo.equals("double")
                            || tipo.equals("long")
                            || tipo.equals("String")) {
                        painelCRUD.add(labelNomeVar);
                        painelCRUD.add(labelTamanho);
                        painelCRUD.add(labelNomeUs);

                        painelCRUD.add(fdNomeVar);
                        painelCRUD.add(fdTamanho);
                        painelCRUD.add(fdNomeUs);
                        fdNomeVar.setText(li[0]);
                        fdTamanho.setText(li[2]);
                        fdNomeUs.setText(li[3]);

                        labelNomeVar.setVisible(true);
                        labelTamanho.setVisible(true);
                        labelTipo.setVisible(true);
                        labelNomeUs.setVisible(true);
                        fdNomeVar.setVisible(true);
                        fdTamanho.setVisible(true);
                        type.setVisible(true);
                        fdNomeUs.setVisible(true);

                        labelTamanho.setText("Tamanho");

                        painelCRUD.setLayout(new GridLayout(2, 3));
                    } else if (tipo.equals("boolean")) {
                        painelCRUD.add(labelNomeVar);
                        painelCRUD.add(labelTamanho);
                        painelCRUD.add(labelOpcao);
                        painelCRUD.add(labelNomeUs);

                        painelCRUD.add(fdNomeVar);
                        painelCRUD.add(opcCbRd);
                        painelCRUD.add(fdOpcao);
                        painelCRUD.add(fdNomeUs);

                        labelTamanho.setText("JCheckBox/JRadioButton");

                        fdNomeVar.setText(li[0]);
                        if (li[2].equals("JCheckBox")) {
                            opcCbRd.setSelectedIndex(0);
                        } else {
                            opcCbRd.setSelectedIndex(1);
                        }
                        fdOpcao.setText(li[3]);
                        fdNomeUs.setText(li[4]);

                        painelCRUD.setLayout(new GridLayout(2, 4));
                    } else if (tipo.equals("Date")) {
                        painelCRUD.setLayout(new GridLayout(2, 2));
                        painelCRUD.add(labelNomeVar);
                        painelCRUD.add(labelNomeUs);
                        painelCRUD.add(fdNomeVar);
                        painelCRUD.add(fdNomeUs);

                        fdNomeVar.setText(li[0]);
                        fdNomeUs.setText(li[2]);

                    } else if (tipo.equals("Dependente")) {
                        painelCRUD.setLayout(new GridLayout(2, 3));
                        painelCRUD.add(labelNomeVar);
                        painelCRUD.add(labelCaminhoFK);
                        painelCRUD.add(labelNomeUs);
                        painelCRUD.add(fdNomeVar);
                        painelCRUD.add(fdCaminho);
                        painelCRUD.add(fdNomeUs);
                        fdNomeVar.setText(li[0]);
                        fdNomeUs.setText(li[2]);
                        fdCaminho.setText(li[3]);
                    }
                    setSize(100, 100);
                    setSize(740, 575);
                    painelCC.setVisible(true);
                    opc = false;
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Preencha tudo corretamente!");
                }
            }
        });

        botaoAddAtrib.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (caminho.equals("")) {
                    JOptionPane.showMessageDialog(null, "Escolha um arquivo para realizar as operações!");
                } else {
                    painelCRUD.removeAll();
                    painelCRUD.add(labelNomeVar);
                    painelCRUD.add(labelTamanho);
                    painelCRUD.add(labelTipo);
                    painelCRUD.add(labelNomeUs);

                    painelCRUD.add(fdNomeVar);
                    painelCRUD.add(fdTamanho);
                    painelCRUD.add(type);
                    painelCRUD.add(fdNomeUs);

                    labelNomeVar.setVisible(true);
                    labelTamanho.setVisible(true);
                    labelTipo.setVisible(true);
                    labelNomeUs.setVisible(true);
                    fdNomeVar.setVisible(true);
                    fdTamanho.setVisible(true);
                    type.setVisible(true);
                    fdNomeUs.setVisible(true);

                    labelTamanho.setText("Tamanho");

                    painelCRUD.setLayout(new GridLayout(2, 4));
                    //painelCp.setLayout(new GridLayout(5, 1));
                    painelCC.setVisible(true);

                    opc = true;
                }
            }
        });

        botaoRemovAtrib.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (caminho.equals("")) {
                    JOptionPane.showMessageDialog(null, "Escolha um arquivo para realizar as operações!");
                } else {
                    String auxi = "";
                    for (String x : lista) {
                        auxi += x.split(";")[0] + ",";
                    }
                    String[] comboB = auxi.split(",");
                    remov = new JComboBox(comboB); //********* NOMES ATRIBUTOS
                    painelCRUD.setLayout(new GridLayout(1, 2));
                    painelCRUD.removeAll();
                    painelCRUD.add(labelRemover);
                    painelCRUD.add(remov);

                    painelCRUD.setLayout(new FlowLayout());
                    painelCC.setVisible(true);

                    opc2 = true;
                }
            }
        }
        );

        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                atualizaTabela();
                painelCRUD.setLayout(new FlowLayout());
                painelCRUD.removeAll();
                painelCRUD.add(botaoAddAtrib);
                painelCRUD.add(botaoRemovAtrib);
                painelCRUD.add(botaoAttAtrib);

                painelCC.setVisible(false);
                setSize(100, 100);
                setSize(740, 575);
            }
        }
        );

        type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                painelCRUD.removeAll();
                if (type.getSelectedItem().equals("int")
                        || type.getSelectedItem().equals("double")
                        || type.getSelectedItem().equals("long")
                        || type.getSelectedItem().equals("String")) {
                    painelCRUD.add(labelNomeVar);
                    painelCRUD.add(labelTamanho);
                    painelCRUD.add(labelTipo);
                    painelCRUD.add(labelNomeUs);

                    painelCRUD.add(fdNomeVar);
                    painelCRUD.add(fdTamanho);
                    painelCRUD.add(type);
                    painelCRUD.add(fdNomeUs);

                    labelNomeVar.setVisible(true);
                    labelTamanho.setVisible(true);
                    labelTipo.setVisible(true);
                    labelNomeUs.setVisible(true);
                    fdNomeVar.setVisible(true);
                    fdTamanho.setVisible(true);
                    type.setVisible(true);
                    fdNomeUs.setVisible(true);

                    painelCC.setVisible(true);
                    labelTamanho.setText("Tamanho");

                    painelCRUD.setLayout(new GridLayout(2, 4));
                } else if (type.getSelectedItem().equals("boolean")) {
                    painelCRUD.add(labelNomeVar);
                    painelCRUD.add(labelTamanho);
                    painelCRUD.add(labelTipo);
                    painelCRUD.add(labelOpcao);
                    painelCRUD.add(labelNomeUs);

                    painelCRUD.add(fdNomeVar);
                    painelCRUD.add(opcCbRd);
                    painelCRUD.add(type);
                    painelCRUD.add(fdOpcao);
                    painelCRUD.add(fdNomeUs);

                    labelTamanho.setText("JCheckBox/JRadioButton");

                    painelCC.setVisible(true);

                    painelCRUD.setLayout(new GridLayout(2, 5));
                } else if (type.getSelectedItem().equals("Date")) {
                    painelCRUD.setLayout(new GridLayout(2, 3));
                    painelCRUD.add(labelNomeVar);
                    painelCRUD.add(labelTipo);
                    painelCRUD.add(labelNomeUs);

                    painelCRUD.add(fdNomeVar);
                    painelCRUD.add(type);
                    painelCRUD.add(fdNomeUs);

                    painelCC.setVisible(true);
                } else if (type.getSelectedItem().equals("Dependente")) {
                    painelCRUD.setLayout(new GridLayout(2, 4));
                    painelCRUD.add(labelNomeVar);
                    painelCRUD.add(labelTipo);
                    painelCRUD.add(labelNomeUs);
                    painelCRUD.add(labelCaminhoFK);

                    painelCRUD.add(fdNomeVar);
                    painelCRUD.add(type);
                    painelCRUD.add(fdNomeUs);
                    painelCRUD.add(fdCaminho);
                }
            }
        }
        );

        botaoCaminho.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                arquivo = null;
                file.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int i = file.showSaveDialog(null);
                if (i == 1) {
                    fieldCaminho.setText("");
                } else {
                    arquivo = file.getSelectedFile();
                    fieldCaminho.setText(arquivo.getPath());
                    caminho = arquivo.getPath();
                    lista = new ManipulaArquivo().abrirArquivo(arquivo.getPath());
                    atualizaTabela();
                }
            }
        }
        );

        botaoGerarTodos.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                File file = new File(fieldCaminho.getText());
                try {
                    if (file.exists()) {
                        extensao = file.getName();
                        String[] aux = extensao.split("\\.");
                        extensao = aux[1];
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro!");
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um arquivo .txt!");
                }
                if (!extensao.equals("txt")) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um arquivo .txt!");
                } else {
                    try {
                        String formatoDate = JOptionPane.showInputDialog("Insira o formato para as váriaveis do tipo <DATE>", "dd/MM/yyyy");
                        String caminho = file.getPath();
                        new GerarClasseEntidade1(caminho);
                        new GerarClasseControle1(caminho);
                        new GerarClasseGUI1(caminho, formatoDate, checkImg.isSelected());
                        List<String> lista = new ArrayList<>();
                        lista = new ManipulaArquivo().abrirArquivo(fieldCaminho.getText());
                        String a = "";
                        for (int z = 0; z < lista.size(); z++) {
                            String aux[] = lista.get(z).split(";");
                            a += aux[aux.length - 1] + ",";
                        }
                        String[] listaDados = a.split(",");
                        new GerarGUIListagem(caminho, listaDados);
                        JOptionPane.showMessageDialog(null, "Arquivos gerados com sucesso!");
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Erro ao gerar os arquivos!" + erro.toString());
                    }
                }
            }
        }
        );

        botaoGerarControle.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                File file = new File(fieldCaminho.getText());
                try {
                    if (file.exists()) {
                        extensao = file.getName();
                        String[] aux = extensao.split("\\.");
                        extensao = aux[1];
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro!");
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um arquivo .txt!");
                }
                if (!extensao.equals("txt")) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um arquivo .txt!");
                } else {
                    try {
                        String caminho = file.getPath();
                        new GerarClasseControle1(caminho);
                        JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso!");
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Erro ao gerar o arquivo!");
                    }
                }
            }
        }
        );

        botaoGerarEntidade.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                File file = new File(fieldCaminho.getText());
                try {
                    if (file.exists()) {
                        extensao = file.getName();
                        String[] aux = extensao.split("\\.");
                        extensao = aux[1];
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro!");
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um arquivo .txt!");
                }
                if (!extensao.equals("txt")) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um arquivo .txt!");
                } else {
                    try {
                        String caminho = file.getPath();
                        new GerarClasseEntidade1(caminho);
                        JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso!");
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Erro ao gerar o arquivo!");
                    }
                }
            }
        }
        );

        botaoGerarListagem.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                File file = new File(fieldCaminho.getText());
                try {
                    if (file.exists()) {
                        extensao = file.getName();
                        String[] aux = extensao.split("\\.");
                        extensao = aux[1];
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro!");
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um arquivo .txt!");
                }
                if (!extensao.equals("txt")) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um arquivo .txt!");
                } else {
                    try {
                        String caminho = file.getPath();
                        List<String> lista = new ArrayList<>();
                        lista = new ManipulaArquivo().abrirArquivo(fieldCaminho.getText());
                        String a = "";
                        for (int z = 0; z < lista.size(); z++) {
                            String aux[] = lista.get(z).split(";");
                            a += aux[aux.length - 1] + ",";
                        }
                        String[] listaDados = a.split(",");
                        new GerarGUIListagem(caminho, listaDados);
                        JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso!");
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Erro ao gerar o arquivo!");
                    }
                }
            }
        }
        );

        botaoGerarGUI.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                File file = new File(fieldCaminho.getText());
                try {
                    if (file.exists()) {
                        extensao = file.getName();
                        String[] aux = extensao.split("\\.");
                        extensao = aux[1];
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro!");
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um arquivo .txt!");
                }
                if (!extensao.equals("txt")) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um arquivo .txt!");
                } else {
                    try {
                        String formatoDate = JOptionPane.showInputDialog("Insira o formato para as váriaveis do tipo <DATE>", "dd/MM/yyyy");
                        String caminho = file.getPath();
                        new GerarClasseGUI1(caminho, formatoDate, checkImg.isEnabled());
                        JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso!");
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Erro ao gerar o arquivo!");
                    }
                }
            }
        }
        );

        tabbedPane.addTab("Entidade", painelEntidade);
        tabbedPane.addTab("Menu", painelMenu);
        cp.add(tabbedPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void atualizaTabela() {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Nome", "Tipo", "Nome usuário", "Tam/Objeto", "Opção", "Caminho FK"}, 0);
        int aux = 0;

        lista = new ManipulaArquivo().abrirArquivo(caminho);

        for (int i = 0; i < lista.size(); i++) {
            String[] linha = lista.get(i).split(";");
            String tipo = lista.get(i).split(";")[1];
            System.out.println(tipo);
            listaAtributos.add(linha[0]);
            if (tipo.equals("int") || tipo.equals("double") || tipo.equals("long") || tipo.equals("String")) {
                modelo.addRow(new Object[]{linha[0], linha[1], linha[3], linha[2], "", ""});
            } else if (tipo.equals("boolean")) {
                modelo.addRow(new Object[]{linha[0], linha[1], linha[5], linha[2], linha[3] + ";" + linha[4], ""});
            } else if (tipo.equals("Date")) {
                modelo.addRow(new Object[]{linha[0], linha[1], linha[2], "", "", ""});
            } else if (tipo.equals("Dependente")) {
                modelo.addRow(new Object[]{linha[0], linha[1], linha[2], "", "", linha[3]});
            }
            aux++;
        }
        while (aux < 10) {
            aux++;
            modelo.addRow(new Object[]{"", "", "", "", "", ""});
            //nome;tipo;tamanho;nomeUsuario
            //nome;tipo;JCheckBox/JRadioButton;opcao;nomeUsuario
            //nome;tipo;nomeUsuario
        }

        tabela.setModel(modelo);
    }

    public static void atualizaTabelaPorNome(String nome) {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Nome", "Tipo", "Nome usuário", "Tam/Objeto", "Opção", "Caminho FK"}, 0);
        int aux = 0;
        List<String> lista2 = new ArrayList<>();
        lista = new ManipulaArquivo().abrirArquivo(caminho);
        for (String x : lista) {
            if (x.split(";")[0].equals(nome)) {
                lista2.add(x);
            }
        }

        for (int i = 0; i < lista2.size(); i++) {
            String[] linha = lista2.get(i).split(";");
            String tipo = lista2.get(i).split(";")[1];
            System.out.println(tipo);
            listaAtributos.add(linha[0]);
            if (tipo.equals("int") || tipo.equals("double") || tipo.equals("long") || tipo.equals("String")) {
                modelo.addRow(new Object[]{linha[0], linha[1], linha[3], linha[2], "", ""});
            } else if (tipo.equals("boolean")) {
                modelo.addRow(new Object[]{linha[0], linha[1], linha[5], linha[2], linha[3] + ";" + linha[4], ""});
            } else if (tipo.equals("Date")) {
                modelo.addRow(new Object[]{linha[0], linha[1], linha[2], "", "", ""});
            } else if (tipo.equals("Dependente")) {
                modelo.addRow(new Object[]{linha[0], linha[1], linha[2], "", "", ""});
            }
            aux++;
        }
        while (aux < 10) {
            aux++;
            modelo.addRow(new Object[]{"", "", "", "", "", ""});
            //nome;tipo;tamanho;nomeUsuario
            //nome;tipo;JCheckBox/JRadioButton;opcao;nomeUsuario
            //nome;tipo;nomeUsuario
        }

        tabela.setModel(modelo);
    }

    public boolean isInt(String a) {
        try {
            int b = Integer.valueOf(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
