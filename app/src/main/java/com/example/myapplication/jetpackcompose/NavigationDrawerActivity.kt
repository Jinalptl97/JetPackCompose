package com.example.myapplication.jetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.jetpackcompose.ui.theme.GreenGC
import com.example.myapplication.jetpackcompose.ui.theme.JetpackComposeTheme
import kotlinx.coroutines.launch

class NavigationDrawerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationDrwaer()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrwaer() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerstate = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current.applicationContext

    ModalNavigationDrawer(
        drawerState = drawerstate,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .background(GreenGC)
                        .fillMaxWidth()
                        .height(150.dp)
                ) {
                    Text(text = "")
                }
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Home", color = GreenGC) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            tint = GreenGC
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerstate.close()
                        }
                        navController.navigate(Screens.Home.screens) {
                            popUpTo(0)
                        }
                    })
                NavigationDrawerItem(
                    label = { Text(text = "Profile", color = GreenGC) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = GreenGC
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerstate.close()
                        }
                        navController.navigate(Screens.Profile.screens) {
                            popUpTo(0)
                        }
                    })
                NavigationDrawerItem(
                    label = { Text(text = "Settings", color = GreenGC) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = GreenGC
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerstate.close()
                        }
                        navController.navigate(Screens.Settings.screens) {
                            popUpTo(0)
                        }
                    })
                NavigationDrawerItem(
                    label = { Text(text = "Logout", color = GreenGC) },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Logout",
                            tint = GreenGC
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerstate.close()
                        }
                        Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                    })
            }
        },
    ) {
        Scaffold(
            topBar = {
                val coroutineScope = rememberCoroutineScope()
                TopAppBar(title = { Text(text = "WhatsApp") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = GreenGC,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerstate.open()
                            }
                        }) {
                           Icon(Icons.Rounded.Menu,contentDescription = "MenuButton")


                        }
                    })
            }
        ) {
            NavHost(navController = navController,
                startDestination =Screens.Home.screens ){
                composable(Screens.Home.screens){ Home()}
                composable(Screens.Profile.screens){ Profile() }
                composable(Screens.Settings.screens){ Settings() }
            }

        }

    }
}


