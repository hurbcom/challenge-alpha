package br.com.mdr.starwars.presentation.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.mdr.starwars.R
import br.com.mdr.starwars.domain.model.Category
import br.com.mdr.starwars.domain.model.PageState
import br.com.mdr.starwars.presentation.common.EmptyScreen
import br.com.mdr.starwars.presentation.components.ShimmerEffect
import br.com.mdr.starwars.ui.theme.Dimens
import br.com.mdr.starwars.ui.theme.Dimens.MEDIUM_PADDING
import br.com.mdr.starwars.utils.upperCaseFirstChar
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategoriesScreen(navController: NavHostController) {
    val viewModel: CategoriesViewModel = koinViewModel<CategoriesViewModel>()
    val pageState = viewModel.categories.collectAsState()

    LaunchedEffect(key1 = null, block = {
        viewModel.getCategories()
    })

    HandlePagingResult(
        pageState = pageState.value,
        navController = navController,
        viewModel = viewModel
    )
}

@Composable
fun HandlePagingResult(
    pageState: PageState<List<Category>>,
    navController: NavHostController,
    viewModel: CategoriesViewModel
) {
    when (pageState) {
        is PageState.Empty -> { EmptyScreen(viewModel = viewModel) }
        is PageState.Error -> { EmptyScreen(viewModel = viewModel, error = pageState) }
        is PageState.Loading -> { ShimmerEffect() }
        is PageState.Success -> {
            ListContent(categories = pageState.result, navController = navController)
        }
    }
}


@Composable
fun ListContent(categories: List<Category>, navController: NavHostController) {
    LazyColumn(
        contentPadding = PaddingValues(all = MEDIUM_PADDING),
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
    ) {
        items(count = categories.size) { index ->
            CategoryItem(category = categories[index], navController = navController)
        }
    }
}

@Composable
fun CategoryItem(category: Category, navController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.CATEGORY_ITEM_HEIGHT),
        shape = RoundedCornerShape(size = MEDIUM_PADDING),
        onClick = {
            //TODO: Adicionar tela de detalhe de categoria
        }
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(category.categoryUrl)
                .placeholder(R.drawable.app_logo)
                .error(R.drawable.ic_error)
                .size(Size.ORIGINAL)
                .build(),
            contentDescription = category.name,
            contentScale = ContentScale.Crop
        )
        ConstraintLayout {
            val (surface, label) = createRefs()
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.CATEGORY_ITEM_SHADOW_HEIGHT)
                    .constrainAs(surface) {
                        bottom.linkTo(parent.bottom)
                    }
                    .alpha(0.2f),
                color = Color.Black,
                shape = RoundedCornerShape(
                    bottomStart = MEDIUM_PADDING,
                    bottomEnd = MEDIUM_PADDING
                ),
            ){}
            Text(
                text = category.name.upperCaseFirstChar(),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(label) {
                        bottom.linkTo(surface.bottom)
                        start.linkTo(surface.start, margin = MEDIUM_PADDING)
                        top.linkTo(surface.top)
                    },
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        category = Category("films", "https://swapi.dev/api/films/"),
        navController = rememberNavController()
    )
}