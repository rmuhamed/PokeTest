package com.rmuhamed.sample.poketest.data;

import com.rmuhamed.sample.poketest.config.RestApiDefinition;
import com.rmuhamed.sample.poketest.data.dto.PokemonDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class RestApiRepositoryTest {

    @Mock
    private RestApiDefinition apiDefinition;
    private ExecutorService executorService = new SameThreadExecutorService();

    private RestApiRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new RestApiRepository(apiDefinition, executorService);
    }

    @Test(timeout = 1000)
    public void test_FindBy() throws Exception {
        PokemonDTO dto = new PokemonDTO();
        dto.setId("1");
        dto.setName("Poke");
        dto.setBaseExperience("120");
        dto.setWeight("120");
        dto.setTypes(new ArrayList<>());

        Response<PokemonDTO> response = Response.success(dto);

        Call<PokemonDTO> mockedCall = mock(Call.class);
        Mockito.when(mockedCall.execute()).thenReturn(response);

        Mockito.when(apiDefinition.fetchBy(any())).thenReturn(mockedCall);
        repository.findBy(any());
        Mockito.verify(apiDefinition).fetchBy(any());
        Assert.assertNotNull(repository.findBy(any()));
    }

    @After
    public void tearDown() throws Exception {
        executorService.shutdown();
    }


}