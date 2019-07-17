/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Auxiliar.ManipulaArquivo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class GerarDAOEspecifico {

    public void gerar(String caminho) {
        List<String> descricaoEntidade = new ArrayList<>();
        File file = new File(caminho);
        String nomeClasse = file.getName();

        String auxA[] = nomeClasse.split("\\.");
        nomeClasse = auxA[0];
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();

        descricaoEntidade = manipulaArquivo.abrirArquivo(caminho);

        String chave = descricaoEntidade.get(0).split(";")[0];
        String chaveMaiuscula = String.valueOf(chave.charAt(0)).toUpperCase() + chave.substring(1);
        String chave2 = descricaoEntidade.get(1).split(";")[0];
        String chave2Maiuscula = String.valueOf(chave2.charAt(0)).toUpperCase() + chave2.substring(1);

        if (descricaoEntidade == null) {
            System.out.println("Não achou o arquivo com o nome e atributos da classe....");
            System.exit(0); //aborta o programa
        }
        for (String s : descricaoEntidade) {
            System.out.println(s);
        }
        System.out.println("chegou aqui\n\n\n\n\n**");
        String x = "package DAOs;\n"
                + "\n"
                + "import Entidades." + nomeClasse + ";\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "import static DAOs.DAOGenerico.em;\n"
                + "import java.text.SimpleDateFormat;\n"
                + "\n"
                + "public class DAO" + nomeClasse + " extends DAOGenerico<" + nomeClasse + "> {\n"
                + "\n"
                + "    public DAO" + nomeClasse + "() {\n"
                + "        super(" + nomeClasse + ".class);\n"
                + "    }\n"
                + "\n"
                + "    public int autoId" + nomeClasse + "() {\n"
                + "        Integer a = (Integer) em.createQuery(\"SELECT MAX(e." + chave + ") FROM " + nomeClasse + " e \").getSingleResult();\n"
                + "        if (a != null) {\n"
                + "            return a + 1;\n"
                + "        } else {\n"
                + "            return 1;\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    public List<" + nomeClasse + "> listBy" + chaveMaiuscula + "(int "+chave+") {\n"
                + "        return em.createQuery(\"SELECT e FROM " + nomeClasse + " e WHERE e." + chave + " = :" + chave + "\").setParameter(\"" + chave + "\", " + chave + ").getResultList();\n"
                + "    }\n"
                + "\n"
                + "    public List<" + nomeClasse + "> listBy" + chave2Maiuscula + "(String " + chave2 + ") {\n"
                + "        return em.createQuery(\"SELECT e FROM " + nomeClasse + " e WHERE e." + chave2 + " LIKE :" + chave2 + "\").setParameter(\"" + chave2 + "\", \"%\" + " + chave2 + " + \"%\").getResultList();\n"
                + "    }\n"
                + "\n"
                + "    public List<" + nomeClasse + "> listInOrder" + chaveMaiuscula + "() {\n"
                + "        return em.createQuery(\"SELECT e FROM " + nomeClasse + " e ORDER BY e." + chave + "\").getResultList();\n"
                + "    }\n"
                + "\n"
                + "    public List<" + nomeClasse + "> listInOrder" + chave2Maiuscula + "() {\n"
                + "        return em.createQuery(\"SELECT e FROM " + nomeClasse + " e ORDER BY e." + chave2 + "\").getResultList();\n"
                + "    }\n"
                + "\n"
                + "    public List<String> listInOrderNomeStrings(String qualOrdem) {\n"
                + "        List<" + nomeClasse + "> lf;\n"
                + "        if (qualOrdem.equals(\"" + chave + "\")) {\n"
                + "            lf = listInOrder" + chaveMaiuscula + "();\n"
                + "        } else {\n"
                + "            lf = listInOrder" + chave2Maiuscula + "();\n"
                + "        }\n"
                + "\n"
                + "        List<String> ls = new ArrayList<>();\n"
                + "        SimpleDateFormat sdf = new SimpleDateFormat(\"dd/MM/yyyy\");\n"
                + "        for (int i = 0; i < lf.size(); i++) {\n"
                + "            " + gerarListagem(caminho)
                + "        }\n"
                + "        return ls;\n"
                + "    }\n"
                + "}\n"
                + "";
        List<String> codigo = new ArrayList<>();
        codigo.add(x);
        new ManipulaArquivo().salvarArquivo("src/classeGerada/DAO" + nomeClasse + ".java", codigo);
        System.out.println("gerado");
    }

    public static String gerarListagem(String caminho) {
        List<String> lista = new ManipulaArquivo().abrirArquivo(caminho);
        String a = "";
        for (int z = 0; z < lista.size(); z++) {
            String aux[] = lista.get(z).split(";");
            if (aux[1].equals("Date")) {
                a += "sdf.format(lf.get(i).get" + String.valueOf(aux[0].charAt(0)).toUpperCase() + aux[0].substring(1) + "()) + \";\" + ";

            } else {
                a += "lf.get(i).get" + String.valueOf(aux[0].charAt(0)).toUpperCase() + aux[0].substring(1) + "() + \";\" + ";

            }
        }
        String x = "ls.add(" + a.substring(0, (a.length() - 3)) + ");\n";
        return x;
    }
}
