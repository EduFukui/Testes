package Model;
import jakarta.persistence.*;

@Entity
@Table(name="Pokemons")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para id com serial (autoIncrement)
    private int id_pokemon;

    @Column(name="fk_id_treinador")
    private String fk_id_treinador;

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

    //Constructor com ID
    public Pokemon(int id_pokemon, String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo) {
        this.id_pokemon = id_pokemon;
        this.nome = nome;
        this.tipoPrimario = tipoPrimario;
        this.tipoSecundario = tipoSecundario;
        this.nivel = nivel;
        this.hpMaximo = hpMaximo;
    }

    //Constructor sem ID
    public Pokemon(String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo) {
        this.nome = nome;
        this.tipoPrimario = tipoPrimario;
        this.tipoSecundario = tipoSecundario;
        this.nivel = nivel;
        this.hpMaximo = hpMaximo;
    }

    public Pokemon() {
    }

    //Getters
    public int getId_pokemon() {return id_pokemon;}
    public String getNome() {return nome;}
    public String getTipoPrimario() {return tipoPrimario;}
    public String getTipoSecundario() {return tipoSecundario;}
    public int getNivel() {return nivel;}
    public int getHpMaximo() {return hpMaximo;}

    //Setters
    public void setId_pokemon(int id_pokemon) {this.id_pokemon = id_pokemon;}
    public void setNome(String nome) {this.nome = nome;}
    public void setTipoPrimario(String tipoPrimario) {this.tipoPrimario = tipoPrimario;}
    public void setTipoSecundario(String tipoSecundario) {this.tipoSecundario = tipoSecundario;}
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
                "Id =" + id_pokemon + '\n'+
                "Nome =" + nome + '\n'+
                "TipoPrimario =" + tipoPrimario + '\n'+
                "TipoSecundario =" + tipoSecundario + '\n'+
                "Nivel =" + nivel + '\n'+
                "HpMaximo =" + hpMaximo + '\n'+
                '}';
    }
}
