//package DAOs;
//
//import Entidades.Animal;
//import java.util.ArrayList;
//import java.util.List;
//import static DAOs.DAOGenerico.em;
//import java.text.SimpleDateFormat;
//
//public class DAOAnimal extends DAOGenerico<Animal> {
//
//    public DAOAnimal() {
//        super(Animal.class);
//    }
//
//    public int autoIdAnimal() {
//        Integer a = (Integer) em.createQuery("SELECT MAX(e.numero) FROM Animal e ").getSingleResult();
//        if (a != null) {
//            return a + 1;
//        } else {
//            return 1;
//        }
//    }
//
//    public List<Animal> listByCpf(String nome) {
//        return em.createQuery("SELECT e FROM Animal e WHERE e.numero LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
//    }
//
//    public List<Animal> listByRa(int id) {
//        return em.createQuery("SELECT e FROM Animal e WHERE e.descricao = :id").setParameter("id", id).getResultList();
//    }
//
//    public List<Animal> listInOrderCpf() {
//        return em.createQuery("SELECT e FROM Animal e ORDER BY e.numero").getResultList();
//    }
//
//    public List<Animal> listInOrderRa() {
//        return em.createQuery("SELECT e FROM Animal e ORDER BY e.descricao").getResultList();
//    }
//
//    public List<String> listInOrderNomeStrings(String qualOrdem) {
//        List<Animal> lf;
//        if (qualOrdem.equals("id")) {
//            lf = listInOrderRa();
//        } else {
//            lf = listInOrderCpf();
//        }
//
//        List<String> ls = new ArrayList<>();
//        "Número","Data","Preco da manutenção","Descrição do animal"
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        for (int i = 0; i < lf.size(); i++) {
//            ls.add(lf.get(i).getNumero() + ";" + sdf.format(lf.get(i).getDataNasc()) + ";" + lf.get(i).getPrecoManutencao() + ";" + lf.get(i).getDescricao());
//        }
//        return ls;
//    }
//}
