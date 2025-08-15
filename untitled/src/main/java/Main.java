import Controller.PokemonController;
import Model.Pokemon;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[]args) throws Exception {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(Pokemon.class);

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Pokemon pokemon = new Pokemon();
        pokemon.setNome("Palkia");
        pokemon.setTipoPrimario("Dragão");
        pokemon.setTipoSecundario("");
        pokemon.setNivel(22);
        pokemon.setHpMaximo(25);


        PokemonController po = new PokemonController();

        po.cadastrarPokemon(pokemon);

        /*
        session.save(pokemon);
        session.getTransaction().commit();
*/
        session.close();
        sessionFactory.close();
    }
}
