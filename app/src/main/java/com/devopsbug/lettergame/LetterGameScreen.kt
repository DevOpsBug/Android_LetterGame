package com.devopsbug.lettergame

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.devopsbug.lettergame.ui.ExploreLettersScreen
import com.devopsbug.lettergame.ui.LetterGameViewModel
import com.devopsbug.lettergame.ui.theme.LetterGameTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devopsbug.lettergame.model.Letter
import com.devopsbug.lettergame.ui.LanguageSelectionScreen
import com.devopsbug.lettergame.ui.LetterGameStartScreen

enum class LetterGameScreen(@StringRes val title: Int) {
    START(title = R.string.app_name),
    EXPLORE(title = R.string.explore_screen),
    LANGUAGE(title = R.string.language_screen)
}


@Composable
fun LetterGame() {

    // Initialize navController
    val navController: NavHostController = rememberNavController()

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get the name of the current screen
    val currentScreen = LetterGameScreen.valueOf(
        backStackEntry?.destination?.route ?: LetterGameScreen.START.name
    )

    // Create ViewModel
    val viewModel: LetterGameViewModel = viewModel()

    LetterGameTheme {
        Scaffold(
            topBar = {
                LetterGameTopAppBar(
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                    currentScreenTitle = currentScreen.title,
                    modifier = Modifier
                )
            },
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        ) { innerPadding ->
            val uiState by viewModel.uiState.collectAsState()

            NavHost(
                navController = navController,
                startDestination = LetterGameScreen.START.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = LetterGameScreen.START.name) {
                    Log.d(TAG, "navHost Calling route = ${LetterGameScreen.START.name}")
                    LetterGameStartScreen(
                        onClickExplore = { navController.navigate(LetterGameScreen.EXPLORE.name) },
                        onClickLanguage = { navController.navigate(LetterGameScreen.LANGUAGE.name) }
                    )
                }
                composable(route = LetterGameScreen.EXPLORE.name) {
                    Log.d(TAG, "navHost: Calling route = ${LetterGameScreen.EXPLORE.name}")
                    ExploreLettersScreen(
                        currentLanguage = uiState.language,
                        updateLanguage = { viewModel.updateLanguage(it) },
                    )
                }
                composable(route = LetterGameScreen.LANGUAGE.name) {
                    Log.d(TAG, "navHost: Calling route = ${LetterGameScreen.LANGUAGE.name}")
                    LanguageSelectionScreen()
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LetterGameTopAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    @StringRes currentScreenTitle: Int,
    modifier: Modifier = Modifier
) {
        TopAppBar(
            title = { Text(stringResource(currentScreenTitle), color = MaterialTheme.colorScheme.onPrimary) },
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
            ),
//            colors = TopAppBarDefaults.mediumTopAppBarColors(
//                containerColor = MaterialTheme.colorScheme.primaryContainer
//            ),
            modifier = modifier

        )
}