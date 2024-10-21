package com.ypm.zazil_project_ecommerce.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.net.Uri
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
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CuentaUI(usuarioID: String?, navController: NavController, cuentaVM: CuentaVM = viewModel()) {
    val usuario by cuentaVM.usuario.collectAsState(initial = null)
    var isEditable by remember { mutableStateOf(false) }

    usuarioID?.let {
        if (usuario == null) cuentaVM.obtenerUsuario(it)
    }

    // Image picker launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                cuentaVM.actualizarRutaImg(it.toString()) // Actualizar la ruta de imagen en ViewModel
            }
        }
    )

    Scaffold(
        bottomBar = { BottomBar(usuarioID, navController = navController) }
    ) {
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


                Spacer(modifier = Modifier.height(5.dp))

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
                            text = "${usuario?.nombre ?: ""} ${usuario?.apellido_paterno ?: ""} ${usuario?.apellido_materno ?: ""}",
                            color = Color.Black,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(vertical = 1.dp)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        OutlinedTextField(
                            value = cuentaVM.nombre.collectAsState().value,
                            onValueChange = {
                                if (isEditable) cuentaVM.actualizarNombre(it)
                            },
                            label = { Text("Nombre") },
                            modifier = Modifier.fillMaxWidth(0.9f),
                            enabled = isEditable,
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        OutlinedTextField(
                            value = cuentaVM.apellidoPaterno.collectAsState().value,
                            onValueChange = {
                                if (isEditable) cuentaVM.actualizarApellidoPaterno(it)
                            },
                            label = { Text("Apellido Paterno") },
                            modifier = Modifier.fillMaxWidth(0.9f),
                            enabled = isEditable
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        OutlinedTextField(
                            value = cuentaVM.apellidoMaterno.collectAsState().value,
                            onValueChange = {
                                if (isEditable) cuentaVM.actualizarApellidoMaterno(it)
                            },
                            label = { Text("Apellido Materno") },
                            modifier = Modifier.fillMaxWidth(0.9f),
                            enabled = isEditable
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        OutlinedTextField(
                            value = cuentaVM.email.collectAsState().value,
                            onValueChange = {
                                if (isEditable) cuentaVM.actualizarEmail(it)
                            },
                            label = { Text("Email") },
                            modifier = Modifier.fillMaxWidth(0.9f),
                            enabled = isEditable
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        OutlinedTextField(
                            value = if ((cuentaVM.password.collectAsState().value).length > 6) "${cuentaVM.password.collectAsState().value.take(6)}..." else cuentaVM.password.collectAsState().value,
                            onValueChange = {},
                            label = { Text("Contraseña") },
                            modifier = Modifier.fillMaxWidth(0.9f),
                            visualTransformation = PasswordVisualTransformation(),
                            enabled = false
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            FloatingActionButton(
                                onClick = { isEditable = true },
                                containerColor = Color(0xFF5885C6),
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(35.dp)
                            ) {
                                Text("Editar", color = Color.White)
                            }

                            FloatingActionButton(
                                onClick = {
                                    if (isEditable) {
                                        cuentaVM.actualizarUsuario(usuarioID.toString())
                                        isEditable = false
                                    }
                                },
                                containerColor = if (isEditable) Color(0xFF5885C6) else Color.Gray,
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(35.dp)
                            ) {
                                Text("Guardar", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}