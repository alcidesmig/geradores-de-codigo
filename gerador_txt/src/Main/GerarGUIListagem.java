package Main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import Auxiliar.*;

/**
 *
 * @author radames
 */
public class GerarGUIListagem {

    public GerarGUIListagem(String caminho, String[] listaDados) {
        List<String> lista = new ArrayList<>();
        List<String> c = new ArrayList<>();
        File file = new File(caminho);
        String nomeClasse = file.getName();
        String auxA[] = nomeClasse.split("\\.");
        nomeClasse = auxA[0];
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();

        lista = manipulaArquivo.abrirArquivo(caminho);

        c.add("package classeGerada;\n"
                + "\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.Desktop;\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "import javax.swing.JDialog;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JScrollPane;\n"
                + "import javax.swing.JTable;\n"
                + "import javax.swing.JToolBar;\n"
                + "import javax.swing.table.DefaultTableModel;\n"
                + "import Auxiliar.*;\n"
                + ""
                + "// @author Radames\n"
                + "public class " + nomeClasse + "GUIListagem extends JDialog {\n"
                + "\n"
                + "    JPanel painelTa = new JPanel();\n"
                + "    static DefaultTableModel modelo = new DefaultTableModel(" + toString2(listaDados) + ", 0);\n"
                + "    static JTable tabela = new JTable(modelo);\n"
                + "    JScrollPane scroll = new JScrollPane(tabela);\n"
                + "    List<" + nomeClasse + "> lista = new ArrayList<>();"
                + "\n"
                + "    public " + nomeClasse + "GUIListagem(List<" + nomeClasse + "> texto, int posX, int posY) {\n"
                + "        setTitle(\"Listagem de " + nomeClasse + "\");\n"
                + "        setSize(840, 315);//tamanho da janela\n"
                + "        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memória a classe\n"
                + "        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado\n"
                + "        setBackground(Color.CYAN);//cor do fundo da janela\n"
                + "        setModal(true);\n"
                + "        lista = texto;"
                + "        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes\n"
                + "\n"
                + "        painelTa.add(scroll);\n"
                + "        atualizaTabela();\n"
                + "\n"
                + "        cp.add(scroll, BorderLayout.CENTER);\n"
                + "\n"
                + "        setLocation(posX + 20, posY + 20);\n"
                + "        setVisible(true);       \n"
                + "    }\n"
                + "\n"
                + "    public void atualizaTabela() {\n"
                + "        DefaultTableModel modelo = new DefaultTableModel(" + toString2(listaDados) + ", 0);\n"
                + "        int aux = 0;\n"
                + "\n"
                + "        for (int i = 0; i < lista.size(); i++) {\n"
                + "            String[] linha = lista.get(i).toString().split(\";\");\n"
                + "            modelo.addRow(linha);\n"
                + "            aux++;\n"
                + "        }\n"
                + "        while (aux < 10) {\n"
                + "            aux++;\n"
                + "            modelo.addRow(new Object[]{\"\", \"\", \"\", \"\", \"\"});\n"
                + "        }\n"
                + "\n"
                + "        tabela.setModel(modelo);\n"
                + "    }"
                + "}\n"
                + ""
        );

        manipulaArquivo.salvarArquivo("src/classeGerada/" + nomeClasse + "GUIListagem.java", c);

    }

    public static String toString2(String[] s) {
        String ret = "{";
        for (int x = 0; x < s.length; x++) {
            if (x == s.length - 1) {
                ret += "\"" + s[x] + "\"}";
                continue;
            }
            ret += "\"" + s[x] + "\",";
        }
        return "new String[]" + ret;
    }

}
