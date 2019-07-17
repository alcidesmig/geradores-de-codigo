/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Auxiliar.ManipulaArquivo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class GerarDAOGenerico {

    public static void gerar() {
        String x = "package classeGerada;\n"
                + "\n"
                + "import java.util.List;\n"
                + "import javax.persistence.EntityManager;\n"
                + "import javax.persistence.Persistence;\n"
                + "\n"
                + "public class DAOGenerico<T> {\n"
                + "\n"
                + "    public static EntityManager em = Persistence.createEntityManagerFactory(\"UP\").createEntityManager();\n"
                + "    private Class clazz;\n"
                + "\n"
                + "    public DAOGenerico(Class clazz) {\n"
                + "        this.clazz = clazz;\n"
                + "    }\n"
                + "\n"
                + "    public void inserir(T e) {\n"
                + "        em.getTransaction().begin();\n"
                + "        em.persist(e);\n"
                + "        em.getTransaction().commit();\n"
                + "    }\n"
                + "\n"
                + "    public void atualizar(T e) {\n"
                + "        em.getTransaction().begin();\n"
                + "        em.merge(e);\n"
                + "        em.getTransaction().commit();\n"
                + "    }\n"
                + "\n"
                + "    public void remover(T e) {\n"
                + "        em.getTransaction().begin();\n"
                + "        em.remove(e);\n"
                + "        em.getTransaction().commit();\n"
                + "    }\n"
                + "\n"
                + "    public T obter(Long id) {\n"
                + "        return (T) em.find(clazz, id);\n"
                + "    }\n"
                + "\n"
                + "    public T obter(Integer id) {\n"
                + "        return (T) em.find(clazz, id);\n"
                + "    }\n"
                + "\n"
                + "    public List<T> list() {\n"
                + "        return em.createQuery(\"SELECT e FROM \" + clazz.getSimpleName() + \" e\").getResultList();\n"
                + "    }\n"
                + "}\n"
                + "";
        List<String> codigo = new ArrayList<>();
        codigo.add(x);
        new ManipulaArquivo().salvarArquivo("src/classeGerada/DAOGenerico.java", codigo);

    }
}
