import Controller.PokemonController;
import Model.Pokemon;

import View.ListaPokemonsPanel;
import View.PokemonForm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;

public class main extends JFrame {

    private JDesktopPane desktopPane;
    private PokemonController pokemonController;


























    public main() {
        super("Sistema de Gerenciamento de Pokémons");
        this.pokemonController = new PokemonController();

        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        createMenuBar();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu Pokémons
        JMenu menuPokemons = new JMenu("Pokémons");
        JMenuItem itemCadastrarPokemon = new JMenuItem("Cadastrar Pokémon");
        JMenuItem itemListarPokemons = new JMenuItem("Listar Pokémons");

        itemCadastrarPokemon.addActionListener(e -> openPokemonForm(null));
        itemListarPokemons.addActionListener(e -> openListaPokemonsPanel());

        menuPokemons.add(itemCadastrarPokemon);
        menuPokemons.add(itemListarPokemons);

        menuBar.add(menuPokemons);

        // Menu Sair
        JMenu menuSair = new JMenu("Sair");
        JMenuItem itemSair = new JMenuItem("Sair do Sistema");
        itemSair.addActionListener(e -> System.exit(0));

        menuSair.add(itemSair);
        menuBar.add(menuSair);

        setJMenuBar(menuBar);
    }

    private void openPokemonForm(Integer idPokemon) {
        PokemonForm pokemonForm = new PokemonForm(pokemonController, idPokemon);
        desktopPane.add(pokemonForm);
        pokemonForm.setVisible(true);
        pokemonForm.toFront();
    }

    private void openListaPokemonsPanel() {
        ListaPokemonsPanel listaPokemons = new ListaPokemonsPanel(pokemonController);
        desktopPane.add(listaPokemons);
        listaPokemons.setVisible(true);
        listaPokemons.toFront();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new main().setVisible(true);






            Pokemon pokemon = new Pokemon();
            pokemon.setNome("Minccino");
            pokemon.setTipoPrimario("Normal");
            pokemon.setTipoSecundario("");
            pokemon.setNivel(1);
            pokemon.setHpMaximo(25);

            Pokemon pokemon2 = new Pokemon();
            pokemon2.setNome("Litleo");
            pokemon2.setTipoPrimario("Fogo");
            pokemon2.setTipoSecundario("Normal");
            pokemon2.setNivel(1);
            pokemon2.setHpMaximo(25);


            PokemonController po = new PokemonController();

            try {
                po.cadastrarPokemon(pokemon);
                po.cadastrarPokemon(pokemon2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        });
    }
}

/*
public static void mainM(String[]args) throws Exception {
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

        session.close();
        sessionFactory.close();
    }
}
*/



