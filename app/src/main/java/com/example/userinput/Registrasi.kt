package com.example.userinput

import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val PlusJakartaSans = FontFamily(
    Font(R.font.plusjakartasans_extralight, FontWeight.ExtraLight),
    Font(R.font.plusjakartasans_light, FontWeight.Light),
    Font(R.font.plusjakartasans_regular, FontWeight.Normal),
    Font(R.font.plusjakartasans_medium, FontWeight.Medium),
    Font(R.font.plusjakartasans_semibold, FontWeight.SemiBold),
    Font(R.font.plusjakartasans_bold, FontWeight.Bold),
    Font(R.font.plusjakartasans_extrabold, FontWeight.ExtraBold)
)

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val glassBrush = Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.25f),
            Color.White.copy(alpha = 0.1f)
        )
    )

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(glassBrush)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.25f),
                        Color.White.copy(alpha = 0.1f)
                    )
                ),
                shape = RoundedCornerShape(24.dp)
            ),
                colors = CardDefaults.cardColors(
                    containerColor =  Color.Transparent
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                )
            ) {
        Column(
            modifier = Modifier.padding(24.dp),
            content = content
        )
    }
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registrasi(modifier: Modifier = Modifier)
{
    var textNama by remember { mutableStateOf("") }
    var textAsal by remember { mutableStateOf("") }
    var textTgl by remember { mutableStateOf("") }
    var textRt by remember { mutableStateOf("") }
    var textRw by remember { mutableStateOf("") }
    var textUsia by remember { mutableStateOf("") }
    var textGender by remember { mutableStateOf("") }

    var nama by remember { mutableStateOf("") }
    var asal by remember { mutableStateOf("") }
    val tgl by remember { mutableStateOf("") }
    var isDatePickerOpen by remember { mutableStateOf(false) }
    var rt by remember { mutableStateOf("") }
    var rw by remember { mutableStateOf("") }
    var usia by remember { mutableStateOf("") }
    val gender by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    val state = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
    //var selectedDate by remember { mutableStateOf<Long?>(null) }
    var showModal by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    val selectedDate = datePickerState.selectedDateMillis?.let {convertMillisToDate(it) } ?: ""

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                        datePickerState.selectedDateMillis?.let {
                            textTgl = convertMillisToDate(it)
                        }
                    }
                ) {
                    Text(text = "Pilih")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false }
                ) {
                    Text(text = "Batal")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0A3981).copy(alpha = 0.8f),
                        Color(0xFF191414).copy(alpha = 0.8f),
                        Color(0xFF000000)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 68.dp, start = 20.dp, end = 20.dp, bottom = 40.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Register Form",
                color = Color.White,
                fontSize = 28.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            GlassCard(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = textNama,
                        singleLine = true,
                        label = { Text(text = "Nama") },
                        onValueChange = { textNama = it },
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.colors(

                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier=Modifier.height(16.dp))
                    TextField(
                        value = textAsal,
                        singleLine = true,
                        label = { Text(text = "Kota Asal") },
                        onValueChange = { textAsal = it },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier=Modifier.height(16.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        TextField(
                            value = textTgl,
                            onValueChange = {},
                            label = {Text("Tanggal Lahir")},
                            readOnly = true,
                            shape = RoundedCornerShape(12.dp),
                            trailingIcon = {
                                IconButton(onClick = {showDatePicker = true}) {
                                    Icon(
                                        Icons.Default.DateRange, contentDescription = "Pilih Tanggal"
                                    )}
                            },
                            //modifier = Modifier
                              //  .width(200.dp)
                                //.height(28.dp)
                        )

                        if (showDatePicker) {
                            Popup(
                                onDismissRequest = { showDatePicker = false },
                                alignment = Alignment.TopStart
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(top = 68.dp, start = 20.dp, end = 20.dp, bottom = 40.dp)
                                ) {
                                    DatePicker(
                                        state = datePickerState,
                                        showModeToggle = false,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .shadow(elevation = 4.dp)
                                    )
                                }
                            }
                        }

                        TextField(
                            value = textRt,
                            singleLine = true,
                            label = { Text(text = "RT") },
                            onValueChange = { textRt = it },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Column (
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val items = List(20) {"@$it"}
                        LazyColumn {
                            items(items, key = { item -> item}) {
                                item ->
                                Text(text = item,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable{}
                                        .padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}