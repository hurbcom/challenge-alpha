package br.com.vaniala.starwars

import br.com.vaniala.starwars.data.remote.RemoteDataSourceImplTest
import br.com.vaniala.starwars.domain.usecase.GetCategoriesUseCaseTest
import br.com.vaniala.starwars.ui.home.adapter.CategoryViewHolderTest
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
    CategoryViewHolderTest::class,
)
@RunWith(Suite::class)
class TestSuite
