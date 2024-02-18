package com.example.myapplication.jetpackcompose

import android.graphics.drawable.Icon
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.jetpackcompose.ui.theme.GreenGC
import com.example.myapplication.jetpackcompose.ui.theme.JetpackComposeTheme

class BottomBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyBottomAppBar()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomAppBar() {
    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(Icons.Default.Home)

    }
    val sheetstate = rememberModalBottomSheetState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = GreenGC
            ) {
                IconButton(onClick = {
                    selected.value = Icons.Default.Home
                    navController.navigate(Screens.Home.screens) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Home) Color.White else Color.Gray
                    )

                }
                IconButton(onClick = {
                    selected.value = Icons.Default.Search
                    navController.navigate(Screens.Search.screens) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Search) Color.White else Color.Gray
                    )

                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    FloatingActionButton(onClick = {
                        showBottomSheet = true
                    }) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = GreenGC)
                    }

                }
                IconButton(onClick = {
                    selected.value = Icons.Default.Notifications
                    navController.navigate(Screens.Notification.screens) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Notifications) Color.White else Color.Gray
                    )

                }

                IconButton(onClick = {
                    selected.value = Icons.Default.Person
                    navController.navigate(Screens.Profile.screens) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Person) Color.White else Color.Gray
                    )

                }

            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.screens,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.screens) { Home() }
            composable(Screens.Search.screens) { Search() }
            composable(Screens.Notification.screens) { Notification() }
            composable(Screens.Profile.screens) { Profile() }
            composable(Screens.Post.screens) {  Post()  }

        }

    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetstate
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                BottomSheetItem(icon = Icons.Default.ThumbUp, title = "Create a Post") {
                    showBottomSheet = false
                    navController.navigate(Screens.Post.screens) {
                        popUpTo(0)
                    }
                }

                BottomSheetItem(icon = Icons.Default.Star, title = "Add a Story") {
                    Toast.makeText(context, "Story", Toast.LENGTH_SHORT).show()

                }
                BottomSheetItem(icon = Icons.Default.PlayArrow, title = "Create a Reel") {
                    Toast.makeText(context, "Reel", Toast.LENGTH_SHORT).show()

                }
                BottomSheetItem(icon = Icons.Default.Favorite, title = "Go Live") {
                    Toast.makeText(context, "Live", Toast.LENGTH_SHORT).show()

                }

            }


        }
    }

}

@Composable
fun BottomSheetItem(icon: ImageVector, title: String, onclick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.clickable { onclick() }
    ) {
        Icon(icon, contentDescription = null, tint = GreenGC)
        Text(text = title, color = GreenGC, fontSize = 22.sp)

    }
}

@Composable
@Preview
fun MybottomPrevoiew() {
    MyBottomAppBar()
}
