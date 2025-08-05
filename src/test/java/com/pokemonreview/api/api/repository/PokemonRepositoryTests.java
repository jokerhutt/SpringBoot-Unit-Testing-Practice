package com.pokemonreview.api.api.repository;

import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PokemonRepositoryTests {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Test
    public void PokemonRepository_SaveAll_ReturnSavedPokemon() {

        //Arrange
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        //Act
        Pokemon savePokemon = pokemonRepository.save(pokemon);

        //Assert
        Assertions.assertThat(savePokemon).isNotNull();
        Assertions.assertThat(savePokemon.getId()).isGreaterThan(0);

    }

    @Test
    public void PokemonRepository_GetAll_ReturnsMoreThanOnePokemon() {

        //Arrange
        Pokemon pokemon = Pokemon.builder().name("pikachu").type("electric").build();
        Pokemon pokemon2 = Pokemon.builder().name("pikachu").type("electric").build();

        //Act
        pokemonRepository.save(pokemon);
        pokemonRepository.save(pokemon2);

        //Assert
        List<Pokemon> pokemonList = pokemonRepository.findAll();
        Assertions.assertThat(pokemonList).isNotEmpty();
        Assertions.assertThat(pokemonList.size()).isGreaterThan(1);
        Assertions.assertThat(pokemonList.size()).isEqualTo(2);

    }

    @Test
    public void PokemonRepository_FindById_ReturnPokemon() {

        //Arrange
        Pokemon pokemon = Pokemon.builder().name("pikachu").type("electric").id(1).build();

        //Act
        pokemonRepository.save(pokemon);

        //Assert
        Pokemon returnedPokemon = pokemonRepository.findById(1).get();
        Assertions.assertThat(returnedPokemon).isNotNull();

    }


}
