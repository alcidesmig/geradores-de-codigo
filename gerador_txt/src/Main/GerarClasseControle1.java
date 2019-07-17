/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import Auxiliar.*;       
/**
 *
 * @author a1603248
 */
public class GerarClasseControle1 {

    String nomeClasse;
    String objeto;
    String atributoChave;
    String controle;

    public GerarClasseControle1(String caminho) {
        System.out.println("Caminho: " + caminho);

        File file = new File(caminho);
        if (file.exists()) {
            nomeClasse = file.getName();
            String[] aux = nomeClasse.split("\\.");
            nomeClasse = aux[0];
        } else {
            System.out.println("Erro!");
            System.exit(0);
        }

        List<String> de = new ManipulaArquivo().abrirArquivo(caminho);

        objeto = nomeClasse.toLowerCase();
        atributoChave = String.valueOf(de.get(0).split(";")[0].charAt(0)).toUpperCase() + de.get(0).split(";")[0].substring(1, de.get(0).split(";")[0].length());

        List<String> codigo = new ArrayList<>();

        codigo.add("package classeGerada;");
        codigo.add(" ");
        codigo.add("import java.util.ArrayList;");
        codigo.add("import java.util.List;");
        codigo.add("import Auxiliar.*;");
        codigo.add(" ");
        codigo.add("public class Controle" + nomeClasse + " {");
        codigo.add(" ");
        codigo.add("private List<" + nomeClasse + "> lista = new ArrayList<>();");
        codigo.add(" ");
        codigo.add("    public void inserir(" + nomeClasse + " " + objeto + ") {");
        codigo.add("        lista.add(" + objeto + ");");
        codigo.add("    }");
        codigo.add(" ");
        codigo.add("    public " + nomeClasse + " buscar(" + nomeClasse + " " + objeto + ") {");
        codigo.add("        for (int i = 0; i < lista.size(); i++) {");
        codigo.add("            if (String.valueOf(" + objeto + ".get" + atributoChave + "()).equals(String.valueOf(lista.get(i).get" + atributoChave + "()))) {");
        codigo.add("                return lista.get(i);");
        codigo.add("            }");
        codigo.add("        }");
        codigo.add("        return null;");
        codigo.add("    }");
        codigo.add(" ");
        codigo.add("    public void alterar(" + nomeClasse + " " + objeto + "Original, " + nomeClasse + " " + objeto + "Modificado) {");
        codigo.add("        lista.set(lista.indexOf(" + objeto + "Original), " + objeto + "Modificado);");
        codigo.add("    }");
        codigo.add(" ");
        codigo.add("    public void remover(" + nomeClasse + " " + objeto + ") {");
        codigo.add("        lista.remove(" + objeto + ");");
        codigo.add("    }");
        codigo.add(" ");
        codigo.add("    public void listar() {");
        codigo.add("        for (" + nomeClasse + " " + objeto + " : lista) {");
        codigo.add("            System.out.println(" + objeto + ".toString());");
        codigo.add("        }");
        codigo.add("    }");
        codigo.add(" ");
        codigo.add("    public List<" + nomeClasse + "> retornaLista() {");
        codigo.add("        return lista;");
        codigo.add("    }");
        codigo.add("}");

        new ManipulaArquivo().salvarArquivo("src/classeGerada/Controle" + nomeClasse + ".java", codigo);

    }
}
