/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;
import Auxiliar.*;

/**
 *
 * @author alcid
 */
public class GerarMenu {

    ManipulaArquivo manip = new ManipulaArquivo();
    List<String> codigo = new ArrayList<>();

    public void gerarClasseMenu(String nomeClasse_1, String nomeClasse_2) {
        codigo.add(
                "package Menu;\n"
                + "\n"
                + "import "+nomeClasse_1 + ".GUI" + nomeClasse_1+";\n"
                + "import "+nomeClasse_2 + ".GUI" + nomeClasse_2+";\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.Dimension;\n"
                + "import java.awt.Font;\n"
                + "import java.awt.Image;\n"
                + "import java.awt.Point;\n"
                + "import java.awt.event.ActionEvent;\n"
                + "import java.awt.event.ActionListener;\n"
                + "import java.awt.event.WindowAdapter;\n"
                + "import java.awt.event.WindowEvent;\n"
                + "import javax.swing.ImageIcon;\n"
                + "import javax.swing.JFrame;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JMenu;\n"
                + "import javax.swing.JMenuBar;\n"
                + "import javax.swing.JMenuItem;\n"
                + "import javax.swing.JPanel;\n"
                + "import Auxiliar.*;\n"
                + "import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;\n"
                + "public class Menu" + nomeClasse_1 + nomeClasse_2 + " extends JFrame {\n"
                + "\n"
                + "    Container cp;\n"
                + "    JPanel pnNorte = new JPanel();\n"
                + "    JPanel pnCentro = new JPanel();\n"
                + "    JLabel lbTitulo = new JLabel(\"Menu - Produtos com relacionamento 1:n\");\n"
                + "\n"
                + "    Font fonte = new Font(\"Monotype Corsiva\", Font.BOLD, 30);\n"
                + "\n"
                + "    JLabel labelComImagemDeTamanhoDiferente = new JLabel();\n"
                + "\n"
                + "    JMenuBar menuBar = new JMenuBar();\n"
                + "    JMenu menuCadastros = new JMenu(\"Cadastros\");\n"
                + "    JMenuItem cad" + nomeClasse_1 + " = new JMenuItem(\"" + nomeClasse_1 + "\");\n"
                + "    JMenuItem cad" + nomeClasse_2 + " = new JMenuItem(\"" + nomeClasse_2 + "\");\n"
                + "\n"
                + "    Point p;\n"
                + "\n"
                + "    public Menu" + nomeClasse_1 + nomeClasse_2 + "(Dimension dimensao) {\n"
                + "        setDefaultCloseOperation(DISPOSE_ON_CLOSE);\n"
                + "        setSize(dimensao);\n"
                + "        setTitle(lbTitulo.getText());\n"
                + "\n"
                + "        cp = getContentPane();\n"
                + "        cp.setLayout(new BorderLayout());\n"
                + "        pnNorte.add(lbTitulo);\n"
                + "        lbTitulo.setFont(fonte);\n"
                + "        pnNorte.setBackground(Color.LIGHT_GRAY);\n"
                + "\n"
                + "        cad" + nomeClasse_1 + ".addActionListener(new ActionListener() {\n"
                + "\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                new GUI" + nomeClasse_1 + "();\n"
                + "            }\n"
                + "        });\n"
                + "         cad" + nomeClasse_2 + ".addActionListener(new ActionListener() {\n"
                + "\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent e) {\n"
                + "                new GUI" + nomeClasse_2 + "();\n"
                + "            }\n"
                + "        });\n"
                + "\n"
                + "        pnCentro.add(labelComImagemDeTamanhoDiferente);\n"
                + "        pnCentro.setBackground(Color.BLACK);\n"
                + "\n"
                + "        cp.add(pnNorte, BorderLayout.NORTH);\n"
                + "        cp.add(pnCentro, BorderLayout.CENTER);\n"
                + "\n"
                + "        setJMenuBar(menuBar);\n"
                + "        menuBar.add(menuCadastros);\n"
                + "\n"
                + "        menuCadastros.add(cad"+nomeClasse_1+");\n"
                + "        menuCadastros.add(cad"+nomeClasse_2+");\n"
                + "\n"
                + "        addWindowListener(new WindowAdapter() {\n"
                + "            @Override\n"
                + "            public void windowClosing(WindowEvent e) {\n"
                + "                // Sai do sistema  \n"
                + "                System.exit(0);\n"
                + "            }\n"
                + "        });\n"
                + "\n"
                + "        \n"
                + "        pack();\n"
                + "        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);\n"
                + "        setLocation(p);\n"
                + "        setVisible(true);\n"
                + "    }\n"
                + "}\n"
                + "");
        new ManipulaArquivo().salvarArquivo("src/Menu/Menu" + nomeClasse_1 + nomeClasse_2 + ".java", codigo);

    }
}
