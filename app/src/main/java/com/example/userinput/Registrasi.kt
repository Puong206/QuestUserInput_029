package com.example.userinput

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
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
    var tgl by remember { mutableStateOf("") }
    var rt by remember { mutableStateOf("") }
    var rw by remember { mutableStateOf("") }
    var usia by remember { mutableStateOf("") }
    val gender:List<String> = listOf("Laki-laki", "Perempuan")
    val datePickerState = rememberDatePickerState()
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }

    fun clearData() {
        textNama = ""
        textAsal = ""
        textTgl = ""
        textRt = ""
        textRw = ""
        textUsia = ""
        textGender = ""
        checked = false
    }

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

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            icon = {Icon(Icons.Default.Check, contentDescription = null)},
            title = { Text(text = "Berhasil")},
            text = {
                Column {
                    Text(text = "Nama: $nama")
                    Text(text = "Asal: $asal")
                    Text(text = "Tanggal Lahir: $tgl")
                    Text(text = "RT: $rt")
                    Text(text = "RW: $rw")
                    Text(text = "Usia: $usia")
                    Text(text = "Jenis Kelamin: $textGender")
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    showSuccessDialog = false
                    clearData()
                })
                {
                    Text("OK")
                }
            }
        )
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
            Text(text = "Form Registrasi",
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
                        label = { Text(text = "Nama",
                            fontSize = 14.sp,
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Medium) },
                        onValueChange = { textNama = it },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier=Modifier.height(16.dp))

                    TextField(
                        value = textAsal,
                        singleLine = true,
                        label = { Text(text = "Kota Asal",
                            fontSize = 14.sp,
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.Medium) },
                        onValueChange = { textAsal = it },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier=Modifier.height(16.dp))

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TextField(
                            value = textTgl,
                            onValueChange = {},
                            label = {Text("Tanggal Lahir",
                                fontSize = 14.sp,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Medium)},
                            readOnly = true,
                            shape = RoundedCornerShape(12.dp),
                            trailingIcon = {
                                IconButton(onClick = {showDatePicker = true}) {
                                    Icon(
                                        Icons.Default.DateRange, contentDescription = "Pilih Tanggal"
                                    )}
                            },
                            modifier = Modifier.width(184.dp)
                        )

                        TextField(
                            value = textRt,
                            singleLine = true,
                            label = { Text(text = "RT",
                                fontSize = 14.sp,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.SemiBold) },
                            onValueChange = { textRt = it },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.width(60.dp)
                        )

                        TextField(
                            value = textRw,
                            singleLine = true,
                            label = {Text(text = "RW",
                                fontSize = 14.sp,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.SemiBold)},
                            onValueChange = {textRw = it},
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.width(60.dp)
                        )
                    }
                    Spacer(modifier=Modifier.height(16.dp))
                    Column (
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            value = textUsia,
                            singleLine = true,
                            label = { Text(text = "Usia",
                                fontSize = 14.sp,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Medium) },
                            onValueChange = { textUsia = it },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier=Modifier.height(16.dp))

                        Text(text = "Jenis Kelamin",
                            textAlign = TextAlign.Left,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontFamily = PlusJakartaSans,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(start = 12.dp, bottom = 4.dp)
                                .fillMaxWidth()
                        )

                        Row {
                            gender.forEach { item ->
                                Row(modifier = Modifier.selectable(
                                    selected = textGender == item,
                                    onClick = { textGender = item}
                                ), verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = textGender == item,
                                        onClick = { textGender = item},
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = Color.White,
                                            unselectedColor = Color.White
                                        )
                                    )
                                    Text(item,
                                        fontSize = 16.sp,
                                        color = Color.White,
                                        fontFamily = PlusJakartaSans,
                                        fontWeight = FontWeight.SemiBold)
                                }
                            }
                        }
                        Spacer(modifier=Modifier.height(210.dp))
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Checkbox(
                                checked = checked,
                                onCheckedChange = {checked = it},
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color.White,
                                    uncheckedColor = Color.White,
                                    checkmarkColor = Color.Black
                                )
                            )
                            Text(text = "Saya setuju dengan syarat dan ketentuan yang berlaku",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Normal
                            )
                        }

                        Spacer(modifier=Modifier.height(20.dp))

                        ElevatedButton(
                            enabled = checked && textNama.isNotEmpty() &&
                                    textAsal.isNotEmpty() && textRt.isNotEmpty() &&
                                    textRw.isNotEmpty() && textTgl.isNotEmpty(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0A3981),
                                contentColor = Color.White
                            ),
                            modifier = Modifier.width(240.dp),
                            onClick = {
                                nama = textNama
                                asal = textAsal
                                rt = textRt
                                rw = textRw
                                usia = textUsia
                                tgl = textTgl
                                showSuccessDialog = true
                            }

                        ) {
                            Text(text = "Submit",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontFamily = PlusJakartaSans,
                                fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}