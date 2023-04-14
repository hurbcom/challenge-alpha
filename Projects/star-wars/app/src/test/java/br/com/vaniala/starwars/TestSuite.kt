package br.com.vaniala.starwars

import br.com.vaniala.starwars.data.remote.RemoteDataSourceImplTest
import br.com.vaniala.starwars.data.remote.service.ApiServiceTest
import br.com.vaniala.starwars.domain.model.CategoryTest
import br.com.vaniala.starwars.domain.model.FilmTest
import br.com.vaniala.starwars.domain.usecase.GetCategoriesUseCaseTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
@Suite.SuiteClasses(
    GetCategoriesUseCaseTest::class,
    RemoteDataSourceImplTest::class,
    CategoryTest::class,
    ApiServiceTest::class,
    FilmTest::class,
)
@RunWith(Suite::class)
class TestSuite
