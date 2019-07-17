package Auxiliar;

import Main.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ManipulaArquivo {

    public ManipulaArquivo() {
    }

    public List<String> abrirArquivo(String caminho) {
        List<String> texto = new ArrayList<>();
        File arq = new File(caminho);
        if (arq.exists()) {
            try {
                FileReader arquivo = new FileReader(caminho);
                BufferedReader conteudoDoArquivo = new BufferedReader(arquivo);
                String linha = conteudoDoArquivo.readLine();
                while (linha != null) {
                    texto.add(linha);
                    linha = conteudoDoArquivo.readLine();
                }
                conteudoDoArquivo.close();
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            texto.add("");
        }
        return texto;
    }

    public int salvarArquivo(String caminho, List<String> texto) {
        try {
            FileWriter arquivo = new FileWriter(caminho);
            BufferedWriter conteudoDoArquivo = new BufferedWriter(arquivo);
            for (int i = 0; i < texto.size(); i++) {
                conteudoDoArquivo.write(texto.get(i) + "\n");//+ System.getProperty("line.separator")); // 
            }
            conteudoDoArquivo.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return 1;
        }
        return 0;
    }
}
