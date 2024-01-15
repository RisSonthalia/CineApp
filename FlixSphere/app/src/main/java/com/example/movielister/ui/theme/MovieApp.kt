package com.example.movielister.ui.theme

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MovieApp() {
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
//    Scaffold(
//        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//        topBar = { MovieTopAppBar(scrollBehavior = scrollBehavior) }
//    ) {
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(it)
//        ) {
//            val movieViewModel: MovieViewModel = viewModel()
//           HomeScreen(
//                PopularmovieUiState = movieViewModel.popularmovie_UiState,
//            TopRatedmovieUiState = movieViewModel.topratedmovie_UiState,
//                NowPlayingmovieUiState = movieViewModel.nowplayingmovie_UiState)
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MovieTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
//    CenterAlignedTopAppBar(
//        scrollBehavior = scrollBehavior,
//        title = {
//            Text(
//                text = stringResource(R.string.app_name),
//                style = MaterialTheme.typography.headlineSmall,
//            )
//        },
//        modifier = modifier
//    )
//}