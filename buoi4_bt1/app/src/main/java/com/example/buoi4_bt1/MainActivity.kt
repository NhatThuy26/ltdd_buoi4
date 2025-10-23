package com.example.buoi4_bt1

import androidx.compose.material.icons.filled.MenuBook
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buoi4_bt1.ui.theme.Buoi4_bt1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Buoi4_bt1Theme {
                AppScaffold()
            }
        }
    }
}

@Composable
fun AppScaffold() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Quản lý") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.MenuBook, contentDescription = null) },
                    label = { Text("DS Sách") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Sinh viên") }
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                0 -> LibraryManagementScreen()
                1 -> BookListScreen()
                2 -> StudentScreen()
            }
        }
    }
}

@Composable
fun LibraryManagementScreen() {
    var screenStep by remember { mutableStateOf(1) }
    var studentName by remember { mutableStateOf("Nguyen Van A") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tiêu đề
        Text(
            text = "Hệ thống\nQuản lý Thư viện",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        // Ô nhập sinh viên + nút thay đổi
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = studentName,
                onValueChange = { studentName = it },
                label = { Text("Sinh viên") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { /* Thay đổi sinh viên */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A4DBE)),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("Thay đổi", color = Color.White)
            }
        }

        // Danh sách sách
        Text(
            text = "Danh sách sách",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(vertical = 8.dp)
        )

        // Các màn nhỏ
        when (screenStep) {
            1 -> BookSelectionScreen()   // Nguyen Van A
            2 -> BorrowedBooksScreen()   // Nguyen Thi B
            3 -> EmptyBorrowScreen()     // Nguyen Van C
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút thêm
        Button(
            onClick = {
                screenStep = when (screenStep) {
                    1 -> 2
                    2 -> 3
                    else -> 1
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A4DBE)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(48.dp)
        ) {
            Text("Thêm", fontSize = 16.sp, color = Color.White)
        }
    }
}

/* ------------------ MÀN NHỎ 1 ------------------ */
@Composable
fun BookSelectionScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF2F2F2), RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        BookCheckboxItem("Sách 01", true)
        BookCheckboxItem("Sách 02", true)
    }
}

/* ------------------ MÀN NHỎ 2 ------------------ */
@Composable
fun BorrowedBooksScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF2F2F2), RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        BookCheckboxItem("Sách 01", true)
    }
}

/* ------------------ MÀN NHỎ 3 ------------------ */
@Composable
fun EmptyBorrowScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF2F2F2), RoundedCornerShape(12.dp))
            .padding(vertical = 28.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bạn chưa mượn quyển sách nào",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Nhấn ‘Thêm’ để bắt đầu hành trình đọc sách!",
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            color = Color.Black
        )
    }
}

/* ------------------ ITEM SÁCH ------------------ */
@Composable
fun BookCheckboxItem(name: String, isChecked: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color.White, RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {},
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF9B0036),
                uncheckedColor = Color(0xFF9B0036)
            )
        )
        Text(
            text = name,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 6.dp)
        )
    }
}

/* ------------------ MÀN DS SÁCH ------------------ */
data class Book(val name: String, val isChecked: Boolean)

@Composable
fun BookListScreen() {
    val books = remember {
        mutableStateListOf(
            Book("Lập trình Android cơ bản", true),
            Book("Hệ điều hành", false),
            Book("Lập trình hướng đối tượng", true),
            Book("Mạng máy tính", false),
            Book("Cơ sở dữ liệu", true)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Danh sách Sách",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFF2F2F2), RoundedCornerShape(12.dp))
                .padding(8.dp)
        ) {
            items(books) { book ->
                BookRowItem(book = book) { checked ->
                    val index = books.indexOf(book)
                    books[index] = book.copy(isChecked = checked)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Thêm sách mới */ }) {
            Text("Thêm sách mới")
        }
    }
}

@Composable
fun BookRowItem(book: Book, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Checkbox(
            checked = book.isChecked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(checkedColor = Color(0xFFB00020))
        )
        Text(book.name, fontSize = 16.sp)
    }
}

/* ------------------ MÀN SINH VIÊN ------------------ */
@Composable
fun StudentScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Màn 3: Sinh viên", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}
