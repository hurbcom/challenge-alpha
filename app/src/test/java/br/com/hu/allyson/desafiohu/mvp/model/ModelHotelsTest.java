package br.com.hu.allyson.desafiohu.mvp.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.hu.allyson.desafiohu.domain.Result;
import br.com.hu.allyson.desafiohu.mvp.presenter.PresenterHotels;
import br.com.hu.allyson.desafiohu.network.NetworkHotels;
import retrofit2.Callback;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ModelHotelsTest {

    @Captor
    private ArgumentCaptor<Callback<Result>> captor;

    @Mock
    private NetworkHotels.HotelsRepositoryImpl repository;

    @Mock
    private PresenterHotels presenterHotels;

    private ModelHotels model;

    @Before
    public void setUp() {
        initMocks(this);
        model = new ModelHotels(repository, presenterHotels);
    }


    @Test
    public void checkNull() {
        assertNotNull(model.getRepository());
        assertNotNull(model.getPresenter());
    }

    @Test
    public void shouldRequestListOfHotelsWithSucess() {

        final Result result = mock(Result.class);

        doAnswer(new Answer<Result>() {
            @Override
            public Result answer(InvocationOnMock invocation) {
                presenterHotels.requestHotelsSucess(result);
                return result;
            }
        }).when(repository).request(captor.capture());

        model.requestHotels();

        verify(repository, times(1)).request(captor.capture());
        verify(presenterHotels, times(1)).requestHotelsSucess(result);

    }

    @Test
    public void shouldRequestListOfHotelsWithError() {

        final Result result = mock(Result.class);

        doAnswer(new Answer<Result>() {
            @Override
            public Result answer(InvocationOnMock invocation) {
                presenterHotels.requestHotelsError();
                return result;
            }
        }).when(repository).request(captor.capture());

        model.requestHotels();

        verify(repository, times(1)).request(captor.capture());
        verify(presenterHotels, times(1)).requestHotelsError();

    }
}
