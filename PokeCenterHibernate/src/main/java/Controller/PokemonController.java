package Controller;

import Model.Pokemon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.sql.*;
import java.util.List;

public class PokemonController {
    private Pokemon pokemon;

    public PokemonController() {
        this.pokemon = new Pokemon();
    }

    public void cadastrarPokemon(Pokemon pokemon) throws Exception {
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

    public List<Long> buscarPokemonPorTipo(String tipo){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Query<Long> query=session.createQuery("SELECT COUNT (*) FROM Pokemon WHERE tipoPrimario = :tipo", Long.class);
            query.setParameter("tipo",tipo);
            return query.getResultList();
        }
    }

    public void atualizarPokemon(Pokemon pokemon) {
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

    public void removerPokemon(int id) {
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

    public void inserirListaPokemons(List<Pokemon> listaPokemon) throws SQLException {
        String sql = "INSERT INTO pokemons (nome, tipo_primario, tipo_secundario, nivel, hp_maximo) VALUES (?, ?, ?, ?, ?)";
        Connection conn = ConexaoPostgresDB.conectar();
        conn.setAutoCommit(false);
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        final int Batch_size = 1000;
        for (int i = 0; i < listaPokemon.size();i++) {
            Pokemon meuPokemon = listaPokemon.get(i);
            stmt.setString(1, meuPokemon.getNome());
            stmt.setString(2, meuPokemon.getTipoPrimario());
            stmt.setString(3, meuPokemon.getTipoSecundario());
            stmt.setInt(4, meuPokemon.getNivel());
            stmt.setInt(5, meuPokemon.getHpMaximo());
            stmt.addBatch(); //Adiciona a intrucao ao lote
            if(i+1==listaPokemon.size() ||(i+1) % Batch_size == 0 ) {
                stmt.executeBatch();
                stmt.clearBatch();
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    meuPokemon.setId(rs.getInt(1));
                }
            }
        }
        conn.commit();
        conn.setAutoCommit(true);
    }

    public void atualizarEmLoteHP(List<Pokemon> listaPokemon) throws SQLException {
        String sql = "UPDATE pokemons SET nivel = ? WHERE id_pokemon = ?";
        Connection conn = ConexaoPostgresDB.conectar();
        conn.setAutoCommit(false);
        PreparedStatement stmt = null;
        final int Batch_size = 1000;

        try {
            conn = ConexaoPostgresDB.conectar();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < listaPokemon.size();i++) {
                Pokemon meuPokemon = listaPokemon.get(i);
                stmt.setInt(1, meuPokemon.getNivel());
                stmt.setInt(2, meuPokemon.getId());
                stmt.addBatch(); //Adiciona a intrucao ao lote

                if(i+1==listaPokemon.size() ||(i+1) % Batch_size == 0 ) {
                    stmt.executeBatch();
                    stmt.clearBatch();
                }

            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar em lote" + e.getMessage());
        }
        conn.commit();
        conn.setAutoCommit(true);
    }

    public void atualizarEmLoteLVL(List<Pokemon> listaPokemon) throws SQLException {
        String sql = "UPDATE pokemons SET hp_maximo = ? WHERE id_pokemon = ?";
        Connection conn = ConexaoPostgresDB.conectar();
        conn.setAutoCommit(false);
        PreparedStatement stmt = null;
        final int Batch_size = 1000;

        try {
            conn = ConexaoPostgresDB.conectar();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < listaPokemon.size();i++) {
                Pokemon meuPokemon = listaPokemon.get(i);
                stmt.setInt(1, meuPokemon.getHpMaximo());
                stmt.setInt(2, meuPokemon.getId());
                stmt.addBatch(); //Adiciona a intrucao ao lote

                if(i+1==listaPokemon.size() ||(i+1) % Batch_size == 0 ) {
                    stmt.executeBatch();
                    stmt.clearBatch();
                }

            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar em lote" + e.getMessage());
        }
        conn.commit();
        conn.setAutoCommit(true);
    }

    public void inserirListaPokemonsJSON(List<Pokemon> listaPokemon) throws SQLException {
        String sql = "INSERT INTO pokemons (nome, tipo_primario, tipo_secundario, nivel, hp_maximo) VALUES (?, ?, ?, ?, ?)";
        Connection conn = ConexaoPostgresDB.conectar();
        conn.setAutoCommit(false);
        PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        final int Batch_size = 1000;
        for (int i = 0; i < listaPokemon.size();i++) {
            Pokemon meuPokemon = listaPokemon.get(i);
            stmt.setString(1, meuPokemon.getNome());
            stmt.setString(2, meuPokemon.getTipoPrimario());
            stmt.setString(3, meuPokemon.getTipoSecundario());
            stmt.setInt(4, meuPokemon.getNivel());
            stmt.setInt(5, meuPokemon.getHpMaximo());
            stmt.addBatch(); //Adiciona a intrucao ao lote
            if(i+1==listaPokemon.size() ||(i+1) % Batch_size == 0 ) {
                stmt.executeBatch();
                stmt.clearBatch();
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    meuPokemon.setId(rs.getInt(1));
                }
            }
        }
        conn.commit();
        conn.setAutoCommit(true);
    }

}