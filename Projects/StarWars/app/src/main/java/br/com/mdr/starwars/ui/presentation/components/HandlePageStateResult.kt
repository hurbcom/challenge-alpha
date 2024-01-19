package br.com.mdr.starwars.ui.presentation.components

import androidx.compose.runtime.Composable
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.ui.presentation.base.BaseViewModel

@Composable
fun HandlePageStateResult(
    pageState: PageState<Any>,
    viewModel: BaseViewModel,
    successBlock: @Composable (PageState.Success<Any>) -> Unit
) {
    when (pageState) {
        is PageState.Empty -> { EmptyScreen(viewModel = viewModel) }
        is PageState.Error -> { EmptyScreen(viewModel = viewModel, error = pageState) }
        is PageState.Loading -> { ShimmerEffect() }
        is PageState.Success -> {
            successBlock(pageState)
        }
    }
}