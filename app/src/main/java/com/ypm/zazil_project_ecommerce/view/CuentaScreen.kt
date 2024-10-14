package com.ypm.zazil_project_ecommerce.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ypm.zazil_project_ecommerce.view.components.BottomBar
import com.ypm.zazil_project_ecommerce.viewmodel.CuentaVM
import java.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage

@Composable
fun DatePickerField() {
    var birthDate by remember { mutableStateOf("Fecha de nacimiento") }

    // Variables para inicializar el DatePicker con la fecha
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Mostrar el DatePickerDialog al hacer clic
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            birthDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        }, year, month, day
    )

    // El campo de texto que muestra el DatePickerDialog
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .background(Color.White)
            .padding(16.dp)
            .clickable { datePickerDialog.show() }
    ) {
        Text(text = birthDate, color = Color.Gray)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CuentaUI(usuario: String?, navController: NavController) {

    val usuarioVM: CuentaVM = viewModel()
    val infoUsuario = usuarioVM.usuario.collectAsState().value

    usuario?.let {
        usuarioVM.obtenerUsuario(it)
    }

    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ) {
        infoUsuario.let {
            // Estado para habilitar/deshabilitar los campos
            var isEditable by remember { mutableStateOf(false) }
            val datePickerState = remember { mutableStateOf("") }

            val context = LocalContext.current

            val datePickerDialog = DatePickerDialog( context,
                { _, year, month, dayOfMonth ->
                    datePickerState.value = "$dayOfMonth/${month + 1}/$year" // se actualiza la fecha selecccionada
                }, 2024, 10, 1 // ejemplo
            )

            //Fondo
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFD22973))
                    .padding()
                    .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    //Foto de perfil circular
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .border(2.dp, Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ){
                        AsyncImage(
                            model = "http://187.145.186.58:4000/api/imagenes_usuario/" + it.ruta_img,
                            contentDescription = "Foto de perfil",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Botón para editar la foto de perfil
                    Button(
                        onClick = { /* Acción para editar la foto */ },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .width(160.dp)
                            .height(35.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5885C6))
                    ) {
                        Text("Editar foto", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    // Caja blanca con bordes redondeados
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(800.dp)
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(24.dp)
                            )
                            .padding(top = 14.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "${it.nombre} ${it.apellido_paterno} ${it.apellido_materno}",
                                color = Color.Black,
                                fontSize = 30.sp,
                                modifier = Modifier.padding(vertical = 1.dp)
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            // Campos de texto con isEditable para habilitar/deshabilitar
                            OutlinedTextField(
                                value = it.nombre,
                                onValueChange = {},
                                label = { Text("Nombre") },
                                modifier = Modifier.fillMaxWidth(0.9f),
                                enabled = isEditable,
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            OutlinedTextField(
                                value = it.apellido_paterno,
                                onValueChange = {},
                                label = { Text("Apellido") },
                                modifier = Modifier.fillMaxWidth(0.9f),
                                enabled = isEditable
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            OutlinedTextField(
                                value = it.apellido_materno,
                                onValueChange = {},
                                label = { Text("Apellido") },
                                modifier = Modifier.fillMaxWidth(0.9f),
                                enabled = isEditable
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            OutlinedTextField(
                                value = it.email,
                                onValueChange = {},
                                label = { Text("Email") },
                                modifier = Modifier.fillMaxWidth(0.9f),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                enabled = isEditable
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            OutlinedTextField(
                                value = it.password,
                                onValueChange = {},
                                label = { Text("Contraseña") },
                                modifier = Modifier.fillMaxWidth(0.9f),
                                visualTransformation = PasswordVisualTransformation(),
                                enabled = isEditable
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            // Campo de fecha de nacimiento
                            OutlinedTextField(
                                value = datePickerState.value.ifEmpty { "dd/mm/aaaa" },  // Show placeholder if no date is selected
                                onValueChange = {},
                                label = { Text("Fecha de Nacimiento") },
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .clickable {
                                        if (isEditable) {
                                            datePickerDialog.show() // Show date picker dialog when editable
                                        }
                                    },
                                enabled = isEditable,
                                readOnly = true // Prevent typing, only allow date picking via dialog
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            // Botones de Editar y Guardar Cambios con FloatingActionButton
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                // FloatingActionButton para Editar
                                FloatingActionButton(
                                    onClick = { isEditable = true }, // Habilita los campos
                                    containerColor = Color(0xFF5885C6), // Color del FAB
                                    modifier = Modifier
                                        .width(120.dp)
                                        .height(35.dp),                        ) {
                                    Text("Editar", color = Color.White)

                                }

                                // FloatingActionButton para Guardar Cambios que puede deshabilitarse
                                Box(
                                    modifier = Modifier.size(120.dp), // Tamaño del boton
                                ) {
                                    if (isEditable) {
                                        FloatingActionButton(
                                            onClick = { /* Acción de Guardar cambios */ },
                                            containerColor = Color(0xFF5885C6), // Color 0xFF5885C6 cuando está habilitado
                                            modifier = Modifier
                                                .width(120.dp)
                                                .height(45.dp),
                                        ) {
                                            Text("Guardar cambios", color = Color.White)

                                        }
                                    } else {
                                        // Mostrar FAB deshabilitado cuando isEditable es false
                                        FloatingActionButton(
                                            onClick = { /* deshabilitado */ },
                                            containerColor = Color.Gray, // Color gris cuando está deshabilitado
                                            modifier = Modifier
                                                .width(120.dp)
                                                .height(45.dp),
                                        ) {
                                            Text("Guardar cambios", color = Color.White)
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(45.dp))
                        }
                    }
                }
            }
        }
    }
}
