package Controller;

import Model.Pokemon;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class PokemonController {
    private Pokemon pokemon;

    public PokemonController() {
        this.pokemon = new Pokemon();
    }

    public void cadastrarPokemon(Pokemon pokemon) throws Exception {
        // --- EXERCÍCIO: Adicionar validações aqui! ---
        // Exemplo de chamada do Model (já validado):

        System.out.println(pokemon.getHpMaximo());
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            if (!pokemon.getNome().matches("[a-zA-Z\\s]+")) {
                throw new Exception("So pode letras e espaços no nome");
            }

            if (pokemon.getTipoPrimario() != null) {
                for (char c : pokemon.getTipoPrimario().toCharArray()) {
                    if (!Character.isLetter(c)) {
                        throw new Exception("So pode Letras no tipo 1");
                    }
                }
            }

            if (pokemon.getTipoSecundario() != null) {
                for (char c : pokemon.getTipoSecundario().toCharArray()) {
                    if (!Character.isLetter(c)) {
                        throw new Exception("So pode Letras no tipo 2");
                    }
                }
            }

         //   List<Pokemon>existentes = session.createQuery("SELECT COUNT(*) FROM pokemons WHERE LOWER(nome) = LOWER(?)", Pokemon.class).setParameter("nome",pokemon.getNome().toLowerCase()).list();
//
           //if (existentes.isEmpty()) {
           //    throw new Exception("Outro aviso, ja existe este nome!!!");
           // }


            if (pokemon.getTipoPrimario().equalsIgnoreCase(pokemon.getTipoSecundario())) {
                throw new Exception("O tipo do Bicho é igual.");
            }


            if (pokemon.getNome() == null || pokemon.getNome().trim().isEmpty()) {
                throw new Exception("O nome do Bicho é obrigatório.");
            }
            if (String.valueOf(pokemon.getNivel()).trim().isEmpty()) {
                throw new Exception("A nivel é obrigatória.");
            }
            if (pokemon.getTipoPrimario() == null || pokemon.getTipoPrimario().trim().isEmpty()) {
                throw new Exception("tipo 1 é obrigatória");
            }
            if (pokemon.getNivel() < 0 || pokemon.getNivel() > 101) {
                throw new Exception("A nivel não é validado.");
            }

            if (pokemon.getHpMaximo() <= 0) {
                throw new IllegalArgumentException("O HP Máximo deve ser maior que 0.");
            }

            session.persist(pokemon);
            transaction.commit();
        } catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao cadastrar Pokemon", e);
        }
    }

    public List<Pokemon> listarTodosPokemon(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            //HQL (Hibernate Query language) - similar ao sql
            Query<Pokemon> query = session.createQuery("FROM Pokemon", Pokemon.class);
            return query.getResultList();
        }
    }

    public Pokemon buscarPorID(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Pokemon.class,id); // Retorna o objeto ou null
        }
    }

    public List<Pokemon> buscarPokemonPorNome(String nome){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Query<Pokemon> query=session.createQuery("From Pokemon as p Where p.nome= :para1", Pokemon.class);
            query.setParameter("para1",nome);
            return query.getResultList();
        }

    }

    public  void atualizarPokemon(Pokemon pokemon) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.merge(pokemon);
                transaction.commit();

        } catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao cadastrar Pokemon", e);
        }
    }

    public  void removerPokemon(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                Pokemon pokemon = session.get(Pokemon.class,id);
                if(pokemon!=null){
                    session.remove(pokemon);
                }
                transaction.commit();

        } catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao cadastrar Pokemon", e);
        }
    }
}


























































/*
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
    }*/

