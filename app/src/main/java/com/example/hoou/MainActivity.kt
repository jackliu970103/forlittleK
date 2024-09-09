package com.example.hoou

import android.media.effect.Effect
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hoou.ui.theme.HoouTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HoouTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                Mainscreenq()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mainscreenq(){
    val scope= rememberCoroutineScope()
    val scaffoldState= rememberScaffoldState()
    val navController= rememberNavController()
    Scaffold (
        scaffoldState=scaffoldState,
        topBar = {
            TopAppBar(title = { Text(text = "an example")},
                navigationIcon = {
                    IconButton(onClick = { scope.launch {
                        scaffoldState.drawerState.open()
                    }}) {
                        Icon(Icons.Default.Menu, contentDescription = null)
                    }
                })

        },
        drawerContent = {
            drawercontext(onclic = {router ->scope.launch {
                scaffoldState.drawerState.close()
                navController.navigate(router)
            }  })
        }
    ){
            paddingValues ->
        NavHost(navController = navController, startDestination = drawerScreen.Home.router, modifier = Modifier.padding(paddingValues) ){
            composable(drawerScreen.Home.router) {
                homescreen()
            }
            composable(drawerScreen.login.router) {
                loginscreen(navController)
            }
            composable(drawerScreen.signup.router) {
                signupscreen(navController)
            }
            composable(drawerScreen.splash.router){
                splashscreen(navController)
            }

        }
    }
}

@Composable
fun splashscreen(navController: NavController){
    LaunchedEffect(Unit ){
        delay(3000)
        navController.navigate(drawerScreen.Home.router)
    }
    Box(
        modifier = Modifier.fillMaxSize(), // 占据整个屏幕
        contentAlignment = Alignment.Center // 将内容居中对齐
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "splash screen", Modifier.fillMaxSize())
        }
    }
}
@Composable
fun homescreen(){
    Box(
        modifier = Modifier.fillMaxSize(), // 占据整个屏幕
        contentAlignment = Alignment.Center // 将内容居中对齐
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "HomeScreen use drawer to other page", fontSize = 50.sp)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginscreen(navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize(), // 占据整个屏幕
        contentAlignment = Alignment.Center // 将内容居中对齐
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var username by remember {
                mutableStateOf("")
            }
            var password by remember {
                mutableStateOf("")
            }
            TextField(value = username, onValueChange = {
                username = it
            })
            Spacer(modifier = Modifier.height(50.dp))
            TextField(value = password, onValueChange = {
                password = it
            })
            Spacer(modifier = Modifier.height(50.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = " login")
            }
            TextButton(onClick = { navController.navigate(drawerScreen.signup.router) }) {
                Text(text = "I dont have account")
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun signupscreen(navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize(), // 占据整个屏幕
        contentAlignment = Alignment.Center // 将内容居中对齐
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var username by remember {
                mutableStateOf("")
            }
            var password by remember {
                mutableStateOf("")
            }
            TextField(value = username, onValueChange = {
                username = it
            })
            Spacer(modifier = Modifier.height(50.dp))
            TextField(value = password, onValueChange = {
                password = it
            })
            Spacer(modifier = Modifier.height(50.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = " login")
            }
            TextButton(onClick = { navController.navigate(drawerScreen.login.router) }) {
                Text(text = "I  have account")
            }
        }
    }
}
@Composable
fun drawercontext(
    onclic:(router:String) -> Unit
){
    Text(text = "Header")
    Spacer(modifier = Modifier.height(50.dp))
    drawerScreen.values().forEach {
        screen ->
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "${screen.title}", Modifier.clickable {
            onclic(screen.router)
        })
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HoouTheme {

    }
}
enum class drawerScreen(
    val title :String,
    val router:String
){
    Home(title = "first","first"),
    login("loginscreen","login"),
    signup("signupScreen","signup"),
    splash("splashscreen","spl")
}