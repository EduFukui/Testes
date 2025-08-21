import Controller.PokemonController;
import Model.Pokemon;
import com.fasterxml.jackson.databind.ObjectMapper;
import Json.LerJson;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        LerJson jsonReader = new LerJson();
        PokemonController po = new PokemonController();

        try {
            // Lê os 150 Pokémons do arquivo JSON
            List<Pokemon> pokemonsParaInserir = jsonReader.lerPokemonsDoJson();
            System.out.println("Lidos " + pokemonsParaInserir.size() + " Pokémons do arquivo JSON.");
            // Exemplo de uso com um método de carga massiva
            po.insereListaPokemons(pokemonsParaInserir);
                    System.out.println("Carga de Pokémons concluída com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo JSON: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
/*
        List<Pokemon> pokemons = new ArrayList<>();

        pokemons.add(new Pokemon("Charmander", "Fogo", null, 1, 100,10));
        pokemons.add(new Pokemon("Squirtle", "Água", null, 1, 100,100));
        ObjectMapper mapper = new ObjectMapper();
        try {
            // O Jackson vai converter a lista em JSON e salvar no arquivo
            mapper.writeValue(new
                    File("./src/main/resources/pokemons_salvos.json"), pokemons);
            System.out.println("Arquivo 'pokemons_salvos.json' foi criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Houve um erro ao salvar o arquivo.");
            e.printStackTrace();
        }*/
    }
}
