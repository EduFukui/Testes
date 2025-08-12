package Controller;

import Model.DAO.PokemonDAO;
import Model.Pokemon;
import java.sql.SQLException;
import java.util.List;

public class PokemonController {
    private PokemonDAO pokemonDAO;

    public PokemonController() {
        this.pokemonDAO = new PokemonDAO();
    }

    public void cadastrarPokemon(String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo) throws Exception {
        // --- EXERCÍCIO: Adicionar validações aqui! ---
        // Exemplo de chamada do Model (já validado):


        if (!nome.matches("[a-zA-Z\\s]+")) {
            throw new Exception("So pode letras e espaços no nome");
        }

        if (tipoPrimario != null){
            for(char c : tipoPrimario.toCharArray()) {
                if (!Character.isLetter(c)) {
                    throw new Exception("So pode Letras no tipo 1");
                }
            }
        }

        if (tipoSecundario != null){
            for(char c : tipoSecundario.toCharArray()) {
                if (!Character.isLetter(c)) {
                    throw new Exception("So pode Letras no tipo 2");
                }
            }
        }

        PokemonController po = new PokemonController();
        List<Pokemon> listPo = po.listarTodosPokemons();
        for (Pokemon p : listPo) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                throw new Exception("O nome do Bicho já tens ai, ja existe. Mude, Aprimore, Melhore, Evolua, Cresça, Progrida, Desenvolva, Torne-se, Amplie");
            }
        }
        if(pokemonDAO.pokemonJaExiste(nome)){
            throw new Exception("Outro aviso, ja existe este nome!!!");
        }









        if (tipoPrimario.equalsIgnoreCase(tipoSecundario)) {
            throw new Exception("O tipo do Bicho é igual.");
        }


        if (nome == null || nome.trim().isEmpty()  ) {
            throw new Exception("O nome do Bicho é obrigatório.");
        }
        if (String.valueOf(nivel).trim().isEmpty()) {
            throw new Exception("A nivel é obrigatória.");
        }
        if (tipoPrimario == null || tipoPrimario.trim().isEmpty()) {
            throw new Exception("tipo 1 é obrigatória");
        }
        if (nivel < 0 || nivel > 101) {
            throw new Exception("A nivel não é validado.");
        }

        if (hpMaximo <= 0) {
            throw new IllegalArgumentException("O HP Máximo deve ser maior que 0.");
        }

        Pokemon pokemon = new Pokemon(nome, tipoPrimario, tipoSecundario, nivel, hpMaximo);
        try {
            pokemonDAO.inserir(pokemon);
        } catch (SQLException e) {
            throw new Exception("Erro ao cadastrar Pokémon no banco de dados: " + e.getMessage());
        }
    }

    public void atualizarPokemon(int id, String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo) throws Exception {
        // --- EXERCÍCIO: Adicionar validações aqui! ---
        // Exemplo de chamada do Model (já validado):
        Pokemon pokemon = new Pokemon(id, nome, tipoPrimario, tipoSecundario, nivel, hpMaximo);
        try {
            pokemonDAO.atualizar(pokemon);
        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar Pokémon no banco de dados: " + e.getMessage());
        }
    }

    public List<Pokemon> listarTodosPokemons() {
        return pokemonDAO.listarTodos();
    }

    public Pokemon buscarPokemonPorId(int id) {
        return pokemonDAO.buscarPorId(id);
    }

    public void removerPokemon(int id) throws Exception {
        Pokemon p = pokemonDAO.buscarPorId(id);
        if (p == null) {
            throw new Exception("O Pokémon com o ID especificado não existe.");
        }

        // --- EXERCÍCIO: Adicionar validações aqui! ---
        try {
            pokemonDAO.remover(id);
        } catch (SQLException e) {
            throw new Exception("Erro ao remover Pokémon: " + e.getMessage());
        }
    }

    public List<Pokemon> buscarPokemonPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome para busca não pode ser vazio.");
        }

        // --- EXERCÍCIO: Adicionar validações aqui! ---
        return pokemonDAO.buscarPorNome(nome);
    }

    public void insereListaPokemons(List<Pokemon> listaPokemons) throws SQLException {
        pokemonDAO.inserirListaPokemons(listaPokemons);
    }

}