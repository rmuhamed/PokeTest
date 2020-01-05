package com.rmuhamed.sample.poketest.data;

import com.rmuhamed.sample.poketest.data.dao.PokemonDAO;
import com.rmuhamed.sample.poketest.data.dao.PokemonEntity;
import com.rmuhamed.sample.poketest.model.Pokemon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseRepositoryTest {

    @Mock
    private PokemonDAO mockDao;
    private ExecutorService executorService = new SameThreadExecutorService();
    private DatabaseRepository repository;

    @Before
    public void setUp() {
        this.repository = new DatabaseRepository(mockDao, executorService);
    }

    @Test
    public void test_GetAll_empty() throws ExecutionException, InterruptedException {
        Mockito.when(mockDao.findAll()).thenReturn(new ArrayList<>());

        List<Pokemon> result = repository.getAll().get();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void test_GetAll_With_Elements() throws ExecutionException, InterruptedException {
        PokemonEntity entity = new PokemonEntity();
        entity.setId("1");

        PokemonEntity entity2 = new PokemonEntity();
        entity2.setId("2");

        List<PokemonEntity> entities = new ArrayList<>();
        entities.add(entity);
        entities.add(entity2);

        Mockito.when(mockDao.findAll()).thenReturn(entities);

        List<Pokemon> result = repository.getAll().get();
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("1", result.get(0).getId());
        Assert.assertEquals("2", result.get(1).getId());
    }

    @Test
    public void test_Save() throws ExecutionException, InterruptedException {
        Mockito.doNothing().when(mockDao).save(any());

        Pokemon toSave = new Pokemon.Builder()
                .setId("1")
                .setName("A")
                .setBaseExperience("200")
                .setType("Type")
                .setHeight("1")
                .setWeight("1")
                .setPicture("")
                .build();

        toSave.setCapturedAt(new Date());

        Boolean result = repository.save(toSave).get();

        Assert.assertNotNull(result);
        Assert.assertTrue(result);
    }

    @Test
    public void test_FindBy() throws ExecutionException, InterruptedException {
        PokemonEntity entity = new PokemonEntity();
        entity.setId("1");

        Mockito.when(mockDao.findBy(Mockito.any())).thenReturn(entity);

        Pokemon result = repository.findBy("1").get();
        Mockito.verify(mockDao, Mockito.times(1)).findBy(any());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), entity.getId());
    }

    @Test
    public void test_not_exists() throws ExecutionException, InterruptedException {
        Mockito.when(mockDao.countBy(Mockito.any())).thenReturn(0);

        Boolean result = repository.exists("1").get();
        Mockito.verify(mockDao, Mockito.times(1)).countBy(any());
        Assert.assertNotNull(result);
        Assert.assertFalse(result);
    }

    @Test
    public void test_exists() throws ExecutionException, InterruptedException {
        Mockito.when(mockDao.countBy(Mockito.any())).thenReturn(20);

        Boolean result = repository.exists("1").get();
        Mockito.verify(mockDao, Mockito.times(1)).countBy(any());
        Assert.assertNotNull(result);
        Assert.assertTrue(result);
    }
}