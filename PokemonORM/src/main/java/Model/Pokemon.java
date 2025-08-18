package Model;
import jakarta.persistence.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity
@Table(name="pokemons")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para id com serial (autoIncrement)
    private int id_pokemon;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="tipo_primario", nullable = false)
    private String tipoPrimario;

    @Column(name="tipo_secundario")
    private String tipoSecundario; // Pode ser null

    @Column(name="nivel", nullable = false)
    private int nivel;

    @Column(name="hp_maximo", nullable = false)
    private int hpMaximo;

    public Pokemon(int id, String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo) {
        this.id_pokemon = id;
        this.nome = nome;
        this.tipoPrimario = tipoPrimario;
        this.tipoSecundario = tipoSecundario;
        this.nivel = nivel;
        this.hpMaximo = hpMaximo;
    }

    public Pokemon(String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo) {
        this.nome = nome;
        this.tipoPrimario = tipoPrimario;
        this.tipoSecundario = tipoSecundario;
        this.nivel = nivel;
        this.hpMaximo = hpMaximo;
    }

    public Pokemon() {
    }


        // Getters
    public int getId() { return id_pokemon; }
    public String getNome() { return nome; }
    public String getTipoPrimario() { return tipoPrimario; }
    public String getTipoSecundario() { return tipoSecundario; }
    public int getNivel() { return nivel; }
    public int getHpMaximo() { return hpMaximo; }

    // Setters com validação (implementadas aqui!)
    public void setId(int id) { this.id_pokemon = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setTipoPrimario(String tipoPrimario) { this.tipoPrimario = tipoPrimario; }
    public void setTipoSecundario(String tipoSecundario) { this.tipoSecundario = tipoSecundario; }

    public void setNivel(int nivel) {
        if (nivel < 1 || nivel > 100) {
            throw new IllegalArgumentException("O Nível do Pokémon deve estar entre 1 e 100.");
        }
        this.nivel = nivel;
    }

    public void setHpMaximo(int hpMaximo) {
        if (hpMaximo <= 0) {
            throw new IllegalArgumentException("O HP Máximo do Pokémon deve ser maior que 0.");
        }
        this.hpMaximo = hpMaximo;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id_pokemon +
                ", nome='" + nome + '\'' +
                ", tipoPrimario='" + tipoPrimario + '\'' +
                ", tipoSecundario='" + tipoSecundario + '\'' +
                ", nivel=" + nivel +
                ", hpMaximo=" + hpMaximo +
                '}';
    }


}
