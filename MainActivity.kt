package com.technicaleducation.privatecenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF1557A6)
private val Blue = Color(0xFF2376D2)
private val Pale = Color(0xFFEAF3FF)
private val Ink = Color(0xFF18324F)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppRoot() }
    }
}

@Composable
fun AppRoot() {
    var page by remember { mutableStateOf("home") }
    var role by remember { mutableStateOf("শিক্ষার্থী") }
    var registered by remember { mutableStateOf(false) }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Navy,
            secondary = Blue,
            background = Color(0xFFF6F9FE),
            surface = Color.White
        )
    ) {
        Surface(Modifier.fillMaxSize(), color = Color(0xFFF6F9FE)) {
            when (page) {
                "home" -> HomeScreen(
                    onLogin = { role = it; page = "login" },
                    onRegister = { page = "register" }
                )
                "login" -> LoginScreen(role, onBack = { page = "home" }) {
                    page = "dashboard"
                }
                "register" -> RegisterScreen(
                    onBack = { page = "home" },
                    onSubmit = { registered = true; page = "submitted" }
                )
                "submitted" -> MessageScreen(
                    "নিবন্ধন জমা হয়েছে",
                    "অ্যাডমিন অনুমোদন করলে আপনার অ্যাকাউন্ট চালু হবে।",
                    "হোমে ফিরুন"
                ) { page = "home" }
                "dashboard" -> DashboardScreen(role, onBack = { page = "home" })
            }
        }
    }
}

@Composable
fun HomeScreen(onLogin: (String) -> Unit, onRegister: () -> Unit) {
    Column(
        Modifier.fillMaxSize().background(Pale).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(26.dp),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Column(
                Modifier.fillMaxWidth().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    Modifier.size(82.dp).background(Navy, RoundedCornerShape(24.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("TE", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    "টেকনিক্যাল এডুকেশন\nপ্রাইভেট সেন্টার",
                    color = Navy, fontSize = 23.sp, fontWeight = FontWeight.Bold,
                    lineHeight = 31.sp
                )
                Spacer(Modifier.height(8.dp))
                Text("শিক্ষা • দক্ষতা • সাফল্য", color = Ink)
                Spacer(Modifier.height(24.dp))
                Text("লগইন করুন", color = Ink, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(10.dp))
                PrimaryButton("অ্যাডমিন লগইন") { onLogin("অ্যাডমিন") }
                PrimaryButton("শিক্ষক লগইন") { onLogin("শিক্ষক") }
                PrimaryButton("শিক্ষার্থী লগইন") { onLogin("শিক্ষার্থী") }
                OutlinedButton(
                    onClick = onRegister,
                    modifier = Modifier.fillMaxWidth().padding(top = 6.dp)
                ) { Text("নতুন শিক্ষার্থী নিবন্ধন") }
            }
        }
        Spacer(Modifier.height(18.dp))
        Text("সহজ ও সুন্দর শিক্ষাব্যবস্থাপনা", color = Navy, fontSize = 13.sp)
    }
}

@Composable
fun PrimaryButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) { Text(label, modifier = Modifier.padding(3.dp)) }
}

@Composable
fun LoginScreen(role: String, onBack: () -> Unit, onLogin: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().padding(22.dp), verticalArrangement = Arrangement.Center) {
        Text("$role লগইন", color = Navy, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(username, { username = it }, label = { Text("মোবাইল নম্বর / ইউজার আইডি") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            password, { password = it }, label = { Text("পাসওয়ার্ড") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Text(
            "এটি প্রাথমিক ডিজাইন। প্রকৃত লগইন চালুর জন্য নিরাপদ সার্ভার ও ডেটাবেজ সংযোগ প্রয়োজন।",
            color = Color.Gray, fontSize = 13.sp
        )
        PrimaryButton("ডেমো ড্যাশবোর্ড দেখুন") { onLogin() }
        TextButton(onClick = onBack) { Text("ফিরে যান") }
    }
}

@Composable
fun RegisterScreen(onBack: () -> Unit, onSubmit: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var group by remember { mutableStateOf("সাধারণ") }
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("শিক্ষার্থী নিবন্ধন", color = Navy, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(14.dp))
        OutlinedTextField(name, { name = it }, label = { Text("শিক্ষার্থীর নাম") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(phone, { phone = it }, label = { Text("মোবাইল নম্বর") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(group, { group = it }, label = { Text("শ্রেণি / বিভাগ") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(18.dp))
        Text("নিবন্ধনের পর অ্যাডমিন অনুমোদন প্রয়োজন হবে।", color = Color.Gray)
        PrimaryButton("নিবন্ধন জমা দিন") { if (name.isNotBlank() && phone.isNotBlank()) onSubmit() }
        TextButton(onClick = onBack) { Text("ফিরে যান") }
    }
}

@Composable
fun DashboardScreen(role: String, onBack: () -> Unit) {
    val items = when (role) {
        "অ্যাডমিন" -> listOf("শিক্ষার্থী অনুমোদন", "শিক্ষক ও শিক্ষার্থী তালিকা", "হাজিরা ব্যবস্থাপনা", "পরীক্ষা ও ফলাফল", "ফি ব্যবস্থাপনা", "নোটিশ প্রকাশ", "PDF / পড়ার উপকরণ", "সাপ্তাহিক অগ্রগতি রিপোর্ট")
        "শিক্ষক" -> listOf("আজকের হাজিরা", "পরীক্ষার নম্বর", "শিক্ষার্থীর অগ্রগতি", "নোটিশ", "পড়ার উপকরণ")
        else -> listOf("আমার হাজিরা", "আমার পরীক্ষার ফলাফল", "নোটিশ", "PDF / পড়ার উপকরণ", "ফি সংক্রান্ত তথ্য")
    }
    Column(Modifier.fillMaxSize().padding(18.dp)) {
        Text("$role ড্যাশবোর্ড", color = Navy, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text("টেকনিক্যাল এডুকেশন প্রাইভেট সেন্টার", color = Color.Gray, fontSize = 13.sp)
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(items.size) { index ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { },
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(items[index], color = Ink, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                        Text("›", color = Blue, fontSize = 25.sp)
                    }
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        Text(
            "এই স্ক্রিনটি প্রাথমিক নমুনা। অনলাইন তথ্য সংরক্ষণ ও প্রতিটি ফিচার চালুর কাজ বাকি।",
            color = Color.Gray, fontSize = 12.sp
        )
        TextButton(onClick = onBack) { Text("লগআউট") }
    }
}

@Composable
fun MessageScreen(title: String, message: String, button: String, onClick: () -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(title, color = Navy, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        Text(message, color = Ink)
        Spacer(Modifier.height(20.dp))
        PrimaryButton(button, onClick)
    }
}
