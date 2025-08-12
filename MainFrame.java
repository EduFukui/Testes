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
        List<Pokemon> listapokemonAlola = new ArrayList<>();
        listapokemonAlola.add(new Pokemon("Rattata (Alola)", "Noturno", "Normal", 5, 15));
        listapokemonAlola.add(new Pokemon("Raticate (Alola)", "Noturno", "Normal", 20, 60));
        listapokemonAlola.add(new Pokemon("Raichu (Alola)", "Elétrico", "Psíquico", 25, 75));
        listapokemonAlola.add(new Pokemon("Sandshrew (Alola)", "Gelo", "Aço", 10, 30));
        listapokemonAlola.add(new Pokemon("Sandslash (Alola)", "Gelo", "Aço", 22, 66));
        listapokemonAlola.add(new Pokemon("Vulpix (Alola)", "Gelo", null, 10, 30));
        listapokemonAlola.add(new Pokemon("Ninetales (Alola)", "Gelo", "Fada", 28, 84));
        listapokemonAlola.add(new Pokemon("Diglett (Alola)", "Terrestre", "Aço", 6, 18));
        listapokemonAlola.add(new Pokemon("Dugtrio (Alola)", "Terrestre", "Aço", 24, 72));
        listapokemonAlola.add(new Pokemon("Meowth (Alola)", "Noturno", null, 7, 21));
        listapokemonAlola.add(new Pokemon("Persian (Alola)", "Noturno", null, 26, 78));
        listapokemonAlola.add(new Pokemon("Geodude (Alola)", "Pedra", "Elétrico", 7, 21));
        listapokemonAlola.add(new Pokemon("Graveler (Alola)", "Pedra", "Elétrico", 26, 78));
        listapokemonAlola.add(new Pokemon("Golem (Alola)", "Pedra", "Elétrico", 36, 108));
        listapokemonAlola.add(new Pokemon("Grimer (Alola)", "Veneno", "Sombrio", 8, 24));
        listapokemonAlola.add(new Pokemon("Muk (Alola)", "Veneno", "Sombrio", 30, 90));
        listapokemonAlola.add(new Pokemon("Exeggutor (Alola)", "Planta", "Dragão", 30, 90));
        listapokemonAlola.add(new Pokemon("Marowak (Alola)", "Fantasma", "Fogo", 30, 90));

        //Pokemon pikachu = new Pokemon("Magnemite","Eletrico",null ,10,10);
        //Pokemon eevee = new Pokemon("ratatta","Normal",null ,10,10);

        //listaPokemon.add(pikachu);
        //listaPokemon.add(eevee);
        //listaPokemon.add(new Pokemon("Squirtle","Agua",null,100,100));

        listaPokemon.add(new Pokemon("Bulbasaur", "Planta", "Veneno", 5, 15));
        listaPokemon.add(new Pokemon("Ivysaur", "Planta", "Veneno", 16, 48));
        listaPokemon.add(new Pokemon("Venusaur", "Planta", "Veneno", 36, 108));
        listaPokemon.add(new Pokemon("Charmander", "Fogo", null, 5, 15));
        listaPokemon.add(new Pokemon("Charmeleon", "Fogo", null, 16, 48));
        listaPokemon.add(new Pokemon("Charizard", "Fogo", "Voador", 36, 108));
        listaPokemon.add(new Pokemon("Squirtle", "Água", null, 5, 15));
        listaPokemon.add(new Pokemon("Wartortle", "Água", null, 16, 48));
        listaPokemon.add(new Pokemon("Blastoise", "Água", null, 36, 108));
        listaPokemon.add(new Pokemon("Caterpie", "Inseto", null, 4, 12));
        listaPokemon.add(new Pokemon("Metapod", "Inseto", null, 7, 21));
        listaPokemon.add(new Pokemon("Butterfree", "Inseto", "Voador", 10, 30));
        listaPokemon.add(new Pokemon("Weedle", "Inseto", "Veneno", 4, 12));
        listaPokemon.add(new Pokemon("Kakuna", "Inseto", "Veneno", 7, 21));
        listaPokemon.add(new Pokemon("Beedrill", "Inseto", "Veneno", 10, 30));
        listaPokemon.add(new Pokemon("Pidgey", "Normal", "Voador", 5, 15));
        listaPokemon.add(new Pokemon("Pidgeotto", "Normal", "Voador", 18, 54));
        listaPokemon.add(new Pokemon("Pidgeot", "Normal", "Voador", 36, 108));
        listaPokemon.add(new Pokemon("Rattata", "Normal", null, 5, 15));
        listaPokemon.add(new Pokemon("Raticate", "Normal", null, 20, 60));
        listaPokemon.add(new Pokemon("Spearow", "Normal", "Voador", 5, 15));
        listaPokemon.add(new Pokemon("Fearow", "Normal", "Voador", 20, 60));
        listaPokemon.add(new Pokemon("Ekans", "Veneno", null, 7, 21));
        listaPokemon.add(new Pokemon("Arbok", "Veneno", null, 22, 66));
        listaPokemon.add(new Pokemon("Pikachu", "Elétrico", null, 8, 24));
        listaPokemon.add(new Pokemon("Raichu", "Elétrico", null, 25, 75));
        listaPokemon.add(new Pokemon("Sandshrew", "Terrestre", null, 10, 30));
        listaPokemon.add(new Pokemon("Sandslash", "Terrestre", null, 22, 66));
        listaPokemon.add(new Pokemon("Nidoran♀", "Veneno", null, 6, 18));
        listaPokemon.add(new Pokemon("Nidorina", "Veneno", null, 16, 48));
        listaPokemon.add(new Pokemon("Nidoqueen", "Veneno", "Terrestre", 36, 108));
        listaPokemon.add(new Pokemon("Nidoran♂", "Veneno", null, 6, 18));
        listaPokemon.add(new Pokemon("Nidorino", "Veneno", null, 16, 48));
        listaPokemon.add(new Pokemon("Nidoking", "Veneno", "Terrestre", 36, 108));
        listaPokemon.add(new Pokemon("Clefairy", "Fada", null, 12, 36));
        listaPokemon.add(new Pokemon("Clefable", "Fada", null, 30, 90));
        listaPokemon.add(new Pokemon("Vulpix", "Fogo", null, 10, 30));
        listaPokemon.add(new Pokemon("Ninetales", "Fogo", null, 28, 84));
        listaPokemon.add(new Pokemon("Jigglypuff", "Normal", "Fada", 8, 24));
        listaPokemon.add(new Pokemon("Wigglytuff", "Normal", "Fada", 26, 78));
        listaPokemon.add(new Pokemon("Zubat", "Veneno", "Voador", 7, 21));
        listaPokemon.add(new Pokemon("Golbat", "Veneno", "Voador", 22, 66));
        listaPokemon.add(new Pokemon("Oddish", "Planta", "Veneno", 6, 18));
        listaPokemon.add(new Pokemon("Gloom", "Planta", "Veneno", 18, 54));
        listaPokemon.add(new Pokemon("Vileplume", "Planta", "Veneno", 32, 96));
        listaPokemon.add(new Pokemon("Paras", "Inseto", "Planta", 8, 24));
        listaPokemon.add(new Pokemon("Parasect", "Inseto", "Planta", 24, 72));
        listaPokemon.add(new Pokemon("Venonat", "Inseto", "Psíquico", 8, 24));
        listaPokemon.add(new Pokemon("Venomoth", "Inseto", "Voador", 24, 72));
        listaPokemon.add(new Pokemon("Diglett", "Terrestre", null, 6, 18));
        listaPokemon.add(new Pokemon("Dugtrio", "Terrestre", null, 24, 72));
        listaPokemon.add(new Pokemon("Meowth", "Normal", null, 7, 21));
        listaPokemon.add(new Pokemon("Persian", "Normal", null, 26, 78));
        listaPokemon.add(new Pokemon("Psyduck", "Água", null, 10, 30));
        listaPokemon.add(new Pokemon("Golduck", "Água", null, 30, 90));
        listaPokemon.add(new Pokemon("Mankey", "Lutador", null, 8, 24));
        listaPokemon.add(new Pokemon("Primeape", "Lutador", null, 28, 84));
        listaPokemon.add(new Pokemon("Growlithe", "Fogo", null, 10, 30));
        listaPokemon.add(new Pokemon("Arcanine", "Fogo", null, 36, 108));
        listaPokemon.add(new Pokemon("Poliwag", "Água", null, 7, 21));
        listaPokemon.add(new Pokemon("Poliwhirl", "Água", null, 18, 54));
        listaPokemon.add(new Pokemon("Poliwrath", "Água", "Lutador", 36, 108));
        listaPokemon.add(new Pokemon("Abra", "Psíquico", null, 9, 27));
        listaPokemon.add(new Pokemon("Kadabra", "Psíquico", null, 27, 81));
        listaPokemon.add(new Pokemon("Alakazam", "Psíquico", null, 36, 108));
        listaPokemon.add(new Pokemon("Machop", "Lutador", null, 10, 30));
        listaPokemon.add(new Pokemon("Machoke", "Lutador", null, 28, 84));
        listaPokemon.add(new Pokemon("Machamp", "Lutador", null, 36, 108));
        listaPokemon.add(new Pokemon("Bellsprout", "Planta", "Veneno", 7, 21));
        listaPokemon.add(new Pokemon("Weepinbell", "Planta", "Veneno", 20, 60));
        listaPokemon.add(new Pokemon("Victreebel", "Planta", "Veneno", 36, 108));
        listaPokemon.add(new Pokemon("Tentacool", "Água", "Veneno", 8, 24));
        listaPokemon.add(new Pokemon("Tentacruel", "Água", "Veneno", 30, 90));
        listaPokemon.add(new Pokemon("Geodude", "Pedra", "Terrestre", 7, 21));
        listaPokemon.add(new Pokemon("Graveler", "Pedra", "Terrestre", 26, 78));
        listaPokemon.add(new Pokemon("Golem", "Pedra", "Terrestre", 36, 108));
        listaPokemon.add(new Pokemon("Ponyta", "Fogo", null, 10, 30));
        listaPokemon.add(new Pokemon("Rapidash", "Fogo", null, 28, 84));
        listaPokemon.add(new Pokemon("Slowpoke", "Água", "Psíquico", 10, 30));
        listaPokemon.add(new Pokemon("Slowbro", "Água", "Psíquico", 30, 90));
        listaPokemon.add(new Pokemon("Magnemite", "Elétrico", "Aço", 8, 24));
        listaPokemon.add(new Pokemon("Magneton", "Elétrico", "Aço", 28, 84));
        listaPokemon.add(new Pokemon("Farfetch'd", "Normal", "Voador", 20, 60));
        listaPokemon.add(new Pokemon("Doduo", "Normal", "Voador", 10, 30));
        listaPokemon.add(new Pokemon("Dodrio", "Normal", "Voador", 28, 84));
        listaPokemon.add(new Pokemon("Seel", "Água", null, 10, 30));
        listaPokemon.add(new Pokemon("Dewgong", "Água", "Gelo", 28, 84));
        listaPokemon.add(new Pokemon("Grimer", "Veneno", null, 8, 24));
        listaPokemon.add(new Pokemon("Muk", "Veneno", null, 30, 90));
        listaPokemon.add(new Pokemon("Shellder", "Água", null, 7, 21));
        listaPokemon.add(new Pokemon("Cloyster", "Água", "Gelo", 30, 90));
        listaPokemon.add(new Pokemon("Gastly", "Fantasma", "Veneno", 8, 24));
        listaPokemon.add(new Pokemon("Haunter", "Fantasma", "Veneno", 26, 78));
        listaPokemon.add(new Pokemon("Gengar", "Fantasma", "Veneno", 36, 108));
        listaPokemon.add(new Pokemon("Onix", "Pedra", "Terrestre", 15, 45));
        listaPokemon.add(new Pokemon("Drowzee", "Psíquico", null, 10, 30));
        listaPokemon.add(new Pokemon("Hypno", "Psíquico", null, 30, 90));
        listaPokemon.add(new Pokemon("Krabby", "Água", null, 8, 24));
        listaPokemon.add(new Pokemon("Kingler", "Água", null, 28, 84));
        listaPokemon.add(new Pokemon("Voltorb", "Elétrico", null, 10, 30));
        listaPokemon.add(new Pokemon("Electrode", "Elétrico", null, 28, 84));
        listaPokemon.add(new Pokemon("Exeggcute", "Planta", "Psíquico", 8, 24));
        listaPokemon.add(new Pokemon("Exeggutor", "Planta", "Psíquico", 30, 90));
        listaPokemon.add(new Pokemon("Cubone", "Terrestre", null, 10, 30));
        listaPokemon.add(new Pokemon("Marowak", "Terrestre", null, 30, 90));
        listaPokemon.add(new Pokemon("Hitmonlee", "Lutador", null, 35, 105));
        listaPokemon.add(new Pokemon("Hitmonchan", "Lutador", null, 35, 105));
        listaPokemon.add(new Pokemon("Lickitung", "Normal", null, 28, 84));
        listaPokemon.add(new Pokemon("Koffing", "Veneno", null, 10, 30));
        listaPokemon.add(new Pokemon("Weezing", "Veneno", null, 30, 90));
        listaPokemon.add(new Pokemon("Rhyhorn", "Terrestre", "Pedra", 12, 36));
        listaPokemon.add(new Pokemon("Rhydon", "Terrestre", "Pedra", 36, 108));
        listaPokemon.add(new Pokemon("Chansey", "Normal", null, 30, 90));
        listaPokemon.add(new Pokemon("Tangela", "Planta", null, 15, 45));
        listaPokemon.add(new Pokemon("Kangaskhan", "Normal", null, 30, 90));
        listaPokemon.add(new Pokemon("Horsea", "Água", null, 8, 24));
        listaPokemon.add(new Pokemon("Seadra", "Água", null, 30, 90));
        listaPokemon.add(new Pokemon("Goldeen", "Água", null, 10, 30));
        listaPokemon.add(new Pokemon("Seaking", "Água", null, 30, 90));
        listaPokemon.add(new Pokemon("Staryu", "Água", null, 8, 24));
        listaPokemon.add(new Pokemon("Starmie", "Água", "Psíquico", 30, 90));
        listaPokemon.add(new Pokemon("Mr. Mime", "Psíquico", "Fada", 30, 90));
        listaPokemon.add(new Pokemon("Scyther", "Inseto", "Voador", 30, 90));
        listaPokemon.add(new Pokemon("Jynx", "Gelo", "Psíquico", 30, 90));
        listaPokemon.add(new Pokemon("Electabuzz", "Elétrico", null, 30, 90));
        listaPokemon.add(new Pokemon("Magmar", "Fogo", null, 30, 90));
        listaPokemon.add(new Pokemon("Pinsir", "Inseto", null, 30, 90));
        listaPokemon.add(new Pokemon("Tauros", "Normal", null, 30, 90));
        listaPokemon.add(new Pokemon("Magikarp", "Água", null, 5, 15));
        listaPokemon.add(new Pokemon("Gyarados", "Água", "Voador", 35, 105));
        listaPokemon.add(new Pokemon("Lapras", "Água", "Gelo", 30, 90));
        listaPokemon.add(new Pokemon("Ditto", "Normal", null, 30, 90));
        listaPokemon.add(new Pokemon("Eevee", "Normal", null, 10, 30));
        listaPokemon.add(new Pokemon("Vaporeon", "Água", null, 30, 90));
        listaPokemon.add(new Pokemon("Jolteon", "Elétrico", null, 30, 90));
        listaPokemon.add(new Pokemon("Flareon", "Fogo", null, 30, 90));
        listaPokemon.add(new Pokemon("Porygon", "Normal", null, 30, 90));
        listaPokemon.add(new Pokemon("Omanyte", "Pedra", "Água", 10, 30));
        listaPokemon.add(new Pokemon("Omastar", "Pedra", "Água", 30, 90));
        listaPokemon.add(new Pokemon("Kabuto", "Pedra", "Água", 10, 30));
        listaPokemon.add(new Pokemon("Kabutops", "Pedra", "Água", 30, 90));
        listaPokemon.add(new Pokemon("Aerodactyl", "Pedra", "Voador", 30, 90));
        listaPokemon.add(new Pokemon("Snorlax", "Normal", null, 35, 105));
        listaPokemon.add(new Pokemon("Articuno", "Gelo", "Voador", 50, 150));
        listaPokemon.add(new Pokemon("Zapdos", "Elétrico", "Voador", 50, 150));
        listaPokemon.add(new Pokemon("Moltres", "Fogo", "Voador", 50, 150));
        listaPokemon.add(new Pokemon("Dratini", "Dragão", null, 10, 30));
        listaPokemon.add(new Pokemon("Dragonair", "Dragão", null, 30, 90));
        listaPokemon.add(new Pokemon("Dragonite", "Dragão", "Voador", 55, 165));
        listaPokemon.add(new Pokemon("Mewtwo", "Psíquico", null, 70, 210));
        listaPokemon.add(new Pokemon("Mew", "Psíquico", null, 50, 150));


        //pokemonController.insereListaPokemons(listaPokemon);
        System.out.println("Total de Pokémon: " + listaPokemon.size());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}