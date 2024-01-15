package com.example.movielister.ui.theme.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.example.movielister.navigation.Movie_enum
import com.example.movielister.navigation.SharedViewModel
import com.example.movielister.components.MovieTopAppBar
import com.example.movielister.viewmodel.BottomNavigationViewModel
import com.example.movielister.viewmodel.NowPlaying_UiState
import com.example.movielister.viewmodel.PopularMovie_UiState
import com.example.movielister.viewmodel.TopRatedMovie_UiState

data class BottomNavigationItem(
    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val hasNews:Boolean,
    val badgeCount:Int?=null,
    val OnClick:()->Unit
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    PopularmovieUiState: PopularMovie_UiState,
    TopRatedmovieUiState: TopRatedMovie_UiState,
    NowPlayingmovieUiState: NowPlaying_UiState,
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    bottomNavigationViewModel: BottomNavigationViewModel
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
//    var selecteditemIndex by rememberSaveable {
//        mutableStateOf(0)
//    }
    val items= listOf(
        BottomNavigationItem(
            title="Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false

        ){

            bottomNavigationViewModel.setSelecteditemindexed(0)
            navController.navigate(Movie_enum.HomeScreen.name )
        },
//        BottomNavigationItem(
//            title="Search",
//            selectedIcon = Icons.Filled.Search,
//            unselectedIcon = Icons.Outlined.Search,
//            hasNews = false
//
//        ){
//            bottomNavigationViewModel.setSelecteditemindexed(1)
//            navController.navigate(Movie_enum.SearchScreen.name )
//        },
        BottomNavigationItem(
            title="Watchlist",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            hasNews = false

        ){
            bottomNavigationViewModel.setSelecteditemindexed(2)
            navController.navigate(Movie_enum.WatchListScreen.name )
        },
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MovieTopAppBar(scrollBehavior = scrollBehavior,name="FlixSphere") },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = bottomNavigationViewModel.getSelecteditemindexed() == index,
                        onClick = item.OnClick,
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    }
                                }) {
                                Icon(
                                    imageVector = if (index == bottomNavigationViewModel.getSelecteditemindexed()) {
                                        item.selectedIcon

                                    } else {item.unselectedIcon},
                                    contentDescription = item.title
                                )
                            }
                        })

                }
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn(modifier = modifier.fillMaxSize()) {
                item {
                    MovieCategory( navController = navController,"Popular", PopularmovieUiState,sharedViewModel=sharedViewModel)
                }

                item {
                    MovieCategory( navController = navController,"Top Rated", TopRatedmovieUiState,sharedViewModel=sharedViewModel)
                }

                item {
                    MovieCategory( navController = navController,"Now Playing", NowPlayingmovieUiState,sharedViewModel=sharedViewModel)
                }
            }
        }
    }

}



fun convertToImageUrl(relativePath: String): String {
    val baseUrl = "https://image.tmdb.org/t/p/w500" // Replace with your actual base image URL
    return baseUrl + relativePath
}


//@Composable
//fun MovieCard(movie:Result,modifier: Modifier=Modifier) {
//    var isExpanded by remember {
//        mutableStateOf(false)
//    }
//
//
//
//    Card(modifier = Modifier
//        .padding(16.dp)
//        .fillMaxWidth()
////        .clickable {
////            onItemClick(movie.title)
////        },
//            ,
//        shape = RoundedCornerShape(corner = CornerSize(12.dp),),
//        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
//
//    ) {
//        Column() {
//            Row(modifier = Modifier.padding(8.dp)) {
//                Surface(
//                    modifier = Modifier
//                        .padding(12.dp)
//                        .size(100.dp), shape = RoundedCornerShape(corner = CornerSize(10.dp))
//                ) {
////                    AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(movie.images[0])
////                        .crossfade(true)
////                        .build(),
////                        contentScale = ContentScale.FillBounds,
////                        contentDescription = "Poster")
//
//                    SubcomposeAsyncImage(
//                        model = ImageRequest.Builder(context = LocalContext.current)
//                            .data(convertToImageUrl(movie.backdrop_path))
//                            .crossfade(true)
//                            .build(),
//                        contentDescription = stringResource(R.string.MoviePhoto),
//
//                        contentScale = ContentScale.FillBounds,
//                        modifier = Modifier.height(200.dp),
//                        error = {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .padding(16.dp),
//                                contentAlignment = Alignment.Center
//                            ){
//                                Image(painter = painterResource(id = R.drawable.ic_broken_image), contentDescription = "errormessage",
//                                    modifier= Modifier
//                                        .size(100.dp)
//                                        .scale(0.8f))
//                            }
//
//                        },
//                        loading={
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .padding(16.dp),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                CircularProgressIndicator(
//                                    modifier = Modifier
//                                        .size(50.dp)
////                        .clip(RoundedCornerShape(8.dp))
////                        .background(MaterialTheme.colorScheme.primary)
//                                        .scale(0.4f),// Scale down the CircularProgressIndicator
//
//
//
//                                )
//                            }
//                        }
//                    )
//                }
//                Spacer(modifier = Modifier.width(5.dp))
//                Column(modifier = Modifier.align(Alignment.Top).padding(top = 12.dp)) {
//                    Text(text = movie.title, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
//                    Spacer(modifier = Modifier.height(10.dp))
//                    Text(text = buildAnnotatedString {
//                        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
//                            append("Released Year: ")
//                        }
//                        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
//                            append(movie.release_date)
//                        }
//                    })
//                    Text(text = buildAnnotatedString {
//                        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
//                            append("Language: ")
//                        }
//                        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
//                            append(movie.original_language)
//                        }
//                    })
//                }
//            }
//            AnimatedVisibility(visible = isExpanded,modifier = Modifier.padding(start = 16.dp, end = 8.dp)) {
//                Text(text = buildAnnotatedString {
//                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
//                        append("Plot: ")
//                    }
//                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
//                        append(movie.overview)
//                    }
//                })
//            }
//            Spacer(modifier = Modifier.height(5.dp))
//
//            Icon(imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
//                contentDescription = "Arrow Down",
//                modifier = Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .clickable {
//                        isExpanded = !isExpanded
//                    }
//                , tint = Color.DarkGray)
//        }
//    }
//}