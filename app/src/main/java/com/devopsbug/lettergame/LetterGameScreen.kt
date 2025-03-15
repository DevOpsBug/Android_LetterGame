package com.devopsbug.lettergame

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.devopsbug.lettergame.ui.ExploreLettersScreen
import com.devopsbug.lettergame.ui.LetterGameViewModel
import com.devopsbug.lettergame.ui.theme.LetterGameTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devopsbug.lettergame.ui.RandomLetterScreen
import com.devopsbug.lettergame.ui.LetterGameStartScreen

enum class LetterGameScreen(@StringRes val title: Int) {
    start(title = R.string.app_name),
    exploreLetters(title = R.string.explore_screen),
    randomLetter(title = R.string.play_letter_game_screen)
}

@Preview
@Composable
fun LetterGame() {

    // Initialize navController
    val navController: NavHostController = rememberNavController()

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get the name of the current screen
    val currentScreen = LetterGameScreen.valueOf(
        backStackEntry?.destination?.route ?: LetterGameScreen.start.name
    )

    // Create ViewModel
    val viewModel: LetterGameViewModel = viewModel()

    LetterGameTheme {
        Scaffold(
            topBar = {
                LetterGameTopAppBar(
                    navigateHome = { navController.navigate(LetterGameScreen.start.name) },
                    currentScreenTitle = currentScreen.title,
                )
            },
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        ) { innerPadding ->
            val uiState by viewModel.uiState.collectAsState()

            NavHost(
                navController = navController,
                startDestination = LetterGameScreen.start.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = LetterGameScreen.start.name) {
                    Log.d(TAG, "navHost Calling route = ${LetterGameScreen.start.name}")
                    LetterGameStartScreen(
                        onClickExplore = { navController.navigate(LetterGameScreen.exploreLetters.name) },
                        //onClickLanguage = { navController.navigate(LetterGameScreen.randomLetter.name) },
                        updateLanguage = { viewModel.updateLanguage(it) },
                        currentLevel = uiState.level,
                        updateLevel = { viewModel.updateLevel(it) },
                        currentLanguage = uiState.language,
                    )
                }
                composable(route = LetterGameScreen.exploreLetters.name) {
                    Log.d(
                        TAG,
                        "navHost: Calling route = ${LetterGameScreen.exploreLetters.name}"
                    )
                    ExploreLettersScreen(
                        currentLanguage = uiState.language,
                        updateLanguage = { viewModel.updateLanguage(it) },
                        currentLevel = uiState.level,
                        currentLetterSet = uiState.currentLetterSet,
                        continueToRandomLetterScreen = { navController.navigate(LetterGameScreen.randomLetter.name) }
                    )
                }
                composable(route = LetterGameScreen.randomLetter.name) {
                    Log.d(TAG, "navHost: Calling route = ${LetterGameScreen.randomLetter.name}")
                    RandomLetterScreen(
                        currentLanguage = uiState.language,
                        currentLetter = uiState.currentLetter,
                        currentLevel = uiState.level,
                        newRandomLetter = { viewModel.newRandomLetter() },
                    )
                }
            }

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LetterGameTopAppBar(
    navigateHome: () -> Unit,
    @StringRes currentScreenTitle: Int,
) {
        TopAppBar(
            title = {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    IconButton(onClick = navigateHome) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = stringResource(R.string.back_button),
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                    //Spacer(modifier = Modifier.weight(1f))
                    Text(
                        stringResource(currentScreenTitle),
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                    )
                    Image(
                        painter = painterResource(R.drawable.letter_game_launcher_foreground),
                        contentDescription = "Letter Game Icon",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.7f)
                            .padding(6.dp)
                    )
                }
                    },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
            ),

        )
}