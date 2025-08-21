package Model;
import jakarta.persistence.*;

@Entity
@Table(name="Treinadores")
public class Treinador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para id com serial (autoIncrement)
    private int id_treinador;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="cidade", nullable = false)
    private String cidade;

    //Constructor com ID
    public Treinador(int id_treinador, String nome, String cidade) {
        this.id_treinador = id_treinador;
        this.nome = nome;
        this.cidade = cidade;
    }

    //Constructor sem ID
    public Treinador(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }

    //Constructor Vazio
    public Treinador() {
    }

    //Getters
    public int getId_treinador() {return id_treinador;}
    public String getNome() {return nome;}
    public String getCidade() {return cidade;}

    //Setters
    public void setId_treinador(int id_treinador) {this.id_treinador = id_treinador;}
    public void setNome(String nome) {this.nome = nome;}
    public void setCidade(String cidade) {this.cidade = cidade;}

    @Override
    public String toString() {
        return "Treinador{" +
                "Id =" + id_treinador + '\n'+
                "Nome =" + nome + '\n'+
                "Cidade =" + cidade + '\n'+
                '}';
    }
}













