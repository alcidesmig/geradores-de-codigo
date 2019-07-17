package Main;

import Auxiliar.STRS;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import Auxiliar.*;        

/**
 *
 * @author radames
 */
public class GerarClasseEntidade1 {

    public GerarClasseEntidade1(String caminho) {

        List<String> descricaoEntidade = new ArrayList<>();
        List<String> textoGerado = new ArrayList<>();
        File file = new File(caminho);
        String nomeClasse = file.getName();

        String auxA[] = nomeClasse.split("\\.");
        nomeClasse = auxA[0];
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();

        descricaoEntidade = manipulaArquivo.abrirArquivo(caminho);

        if (descricaoEntidade == null) {
            System.out.println("Não achou o arquivo com o nome e atributos da classe....");
            System.exit(0); //aborta o programa
        }
        for (String s : descricaoEntidade) {
            System.out.println(s);
        }
        
        textoGerado.add("package classeGerada;");
        textoGerado.add("import java.awt.*;");
        textoGerado.add("import java.awt.event.*;");
        textoGerado.add("import java.util.*;");
        textoGerado.add("import java.util.List;");
        textoGerado.add("import javax.swing.*;");
        textoGerado.add("import Auxiliar.*;");
        textoGerado.add("import java.text.SimpleDateFormat;");

        textoGerado.add("");
        textoGerado.add("public class " + nomeClasse + " {");
        textoGerado.add("");
        textoGerado.add("private SimpleDateFormat sdf = new SimpleDateFormat(\"dd/MM/yyyy\");");
        textoGerado.add("");

        for (int i = 0; i < descricaoEntidade.size(); i++) {
            String[] aux = descricaoEntidade.get(i).split(";");
            if (aux[1].equals("Dependente")) {
                aux[1] = "String";
                String st = "";
                for (String x : aux) {
                    st += x + ";";
                }
                descricaoEntidade.set(i, st);
            }
            String ss = "    private " + aux[1] + " " + aux[0] + ";";
            textoGerado.add(ss);
        }
        textoGerado.add("");

        STRS strs = new STRS();

        textoGerado.add("    public " + nomeClasse + "(");
        for (int i = 0; i < descricaoEntidade.size(); i++) {
            String[] aux = descricaoEntidade.get(i).split(";");
            String codigo = "";
            if (i == descricaoEntidade.size() - 1) {
                codigo = aux[1] + " " + aux[0] + "){";
            } else {
                codigo = aux[1] + " " + aux[0] + ",";
            }
            textoGerado.add("           " + codigo);
        }

        for (int i = 0; i < descricaoEntidade.size(); i++) {
            String[] aux = descricaoEntidade.get(i).split(";");
            String codigo = "        this." + aux[0] + " = " + aux[0] + ";";
            textoGerado.add(codigo);
        }
        textoGerado.add("    }");
        textoGerado.add("");
        textoGerado.add("    public " + nomeClasse + "(){");
        textoGerado.add("    }");
        textoGerado.add("");
        //metodos set
        for (int i = 0; i < descricaoEntidade.size(); i++) {
            String[] aux = descricaoEntidade.get(i).split(";");

            String ss = "    public void set" + strs.primUp(aux[0]) + "(" + aux[1]
                    + " " + aux[0] + "){\n" + "        this." + aux[0] + "="
                    + aux[0] + ";\n    }\n";
            textoGerado.add(ss);
        }
        textoGerado.add("");

        //metodos get
        for (int i = 0; i < descricaoEntidade.size(); i++) {
            String[] aux = descricaoEntidade.get(i).split(";");

            String ss = "    public " + aux[1] + " get" + strs.primUp(aux[0]) + "(){\n"
                    + "        return this." + aux[0] + ";"
                    + "\n    }\n";
            textoGerado.add(ss);
        }

        //metodo toString
        String campos = "";
        for (int i = 0; i < descricaoEntidade.size(); i++) {
            String[] aux3 = ((String) descricaoEntidade.get(i)).split(";");
            if (aux3[1].equals("Date")) {
                campos = campos + "sdf.format(this." + aux3[0] + ")+\";\"+";
            } else {
                campos = campos + "this." + aux3[0] + "+\";\"+";
            }
        }
        textoGerado.add("   @Override\n"
                + "    public String toString() {");
        textoGerado.add("        return " + campos.substring(0, campos.length() - 5) + ";\n   }\n}");

        manipulaArquivo.salvarArquivo("src/classeGerada/" + nomeClasse + ".java", textoGerado);

    }

}
