import Controller.PokemonController;
import Model.Pokemon;
import View.ListaPokemonsPanel;
import View.PokemonForm;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private JDesktopPane desktopPane;
    private PokemonController pokemonController;

    public MainFrame() {
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
        JMenuItem itemInserirListaPokemon = new JMenuItem("Insere Lista de Pokemons");

        itemCadastrarPokemon.addActionListener(e -> openPokemonForm(null));
        itemListarPokemons.addActionListener(e -> openListaPokemonsPanel());
        itemInserirListaPokemon.addActionListener(e -> {
            try {
                insereListaPokemons();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        menuPokemons.add(itemCadastrarPokemon);
        menuPokemons.add(itemListarPokemons);
        menuBar.add(itemInserirListaPokemon);

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

    private void insereListaPokemons() throws SQLException {
        List<Pokemon> listaPokemon = new ArrayList<Pokemon>();
        Pokemon pikachu = new Pokemon("Magnemite","Eletrico",null ,10,10);
        Pokemon eevee = new Pokemon("ratatta","Normal",null ,10,10);

        listaPokemon.add(pikachu);
        listaPokemon.add(eevee);
        listaPokemon.add(new Pokemon("Squirtle","Agua",null,100,100));
        pokemonController.insereListaPokemons(listaPokemon);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}