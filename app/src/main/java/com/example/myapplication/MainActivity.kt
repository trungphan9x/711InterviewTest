package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "numberListScreen") {
        composable("numberListScreen") {
            ListIntegersScreen(navController)
        }
        composable("detailScreen/{number}") { backStackEntry ->
            DetailScreen(backStackEntry.arguments?.getString("number"))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListIntegersScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "List Integer App")
                }
            })
        }
    ) {
        ListInteger(modifier = Modifier.padding(it), navController)
    }
}

@Composable
fun ListInteger(modifier: Modifier, navController: NavHostController) {
    val viewModel : MainViewModel = viewModel()
    val numbers = viewModel.numbers.collectAsState()

    LazyColumn(modifier = modifier) {
        items(count = numbers.value.size) { index ->
            ItemInteger(numbers.value[index], navController, viewModel)
        }
    }
}

@Composable
fun ItemInteger(number: Int, navController: NavHostController, viewModel: MainViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                viewModel.removeNumber(number)
            },
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Number: $number",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}


@Composable
fun DetailScreen(number: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Number : $number",
                style = MaterialTheme.typography.titleLarge
            )
        }

    }
}
