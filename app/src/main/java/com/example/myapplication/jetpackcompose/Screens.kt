package com.example.myapplication.jetpackcompose

sealed class Screens (val screens: String){
    data object Home:Screens("home")
    data object Profile:Screens("profile")
    data object Settings:Screens("settings")
    data object Search:Screens("Search")
    data object Notification:Screens("Notification")
    data object Post:Screens("Post")
}