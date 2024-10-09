package com.ypm.zazil_project_ecommerce.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ypm.zazil_project_ecommerce.viewmodel.RegistroVM
import com.ypm.zazil_project_ecommerce.viewmodel.RutasNav

@Composable
fun RegistroUI(navController: NavController, viewModel: RegistroVM) {

    val nombre by viewModel.nombre.observeAsState("")
    val apellidoPaterno by viewModel.apellidoPaterno.observeAsState("")
    val apellidoMaterno by viewModel.apellidoMaterno.observeAsState("")
    val correo by viewModel.correo.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val registroEnabled: Boolean by viewModel.registroEnabled.observeAsState(false)
    val registerResponse by viewModel.registerResponse.observeAsState(null)
    val registerError by viewModel.registerError.observeAsState(null)
    val campoError by viewModel.campoError.observeAsState(null)
    val correoError by viewModel.correoError.observeAsState(null)
    val passwordError by viewModel.passwordError.observeAsState(null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD22973)),  // Color rosa de fondo
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Parte superior rosa con el nombre "Zazil"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),  // Altura para la barra rosa
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Zazil",
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Caja blanca con curvatura en la parte superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                ), // Curvatura en la parte superior de la sección blanca
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // Título "Crear Cuenta"
                Text(
                    text = "Crear Cuenta",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Campos de entrada
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { viewModel.validRegister(it, apellidoPaterno, apellidoMaterno, correo, password) },
                    label = { Text("Nombre(s)") },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp),  // Bordes redondeados
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black)
                )
                campoError?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        color = Color.Red
                    )
                }

                OutlinedTextField(
                    value = apellidoPaterno,
                    onValueChange = { viewModel.validRegister(nombre, it, apellidoMaterno, correo, password) },
                    label = { Text("Apellido Paterno") },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black)
                )
                campoError?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        color = Color.Red
                    )
                }

                OutlinedTextField(
                    value = apellidoMaterno,
                    onValueChange = { viewModel.validRegister(nombre, apellidoPaterno, it, correo, password) },
                    label = { Text("Apellido Materno") },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black)
                )
                campoError?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        color = Color.Red
                    )
                }

                OutlinedTextField(
                    value = correo,
                    onValueChange = { viewModel.validRegister(nombre, apellidoPaterno, apellidoMaterno, it, password) },
                    label = { Text("Correo electrónico") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black)
                )
                correoError?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        color = Color.Red
                    )
                }

                OutlinedTextField(
                    value = password,
                    onValueChange = { viewModel.validRegister(nombre, apellidoPaterno, apellidoMaterno, correo, it) },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black)
                )
                passwordError?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        color = Color.Red
                    )
                }

                // Enlace de sesión
                Text(
                    text = "¿Ya tienes cuenta? Inicia sesión",
                    color = Color(0xFF2196F3),
                    modifier = Modifier
                        .padding(bottom = 40.dp)
                        .clickable(onClick = {
                            navController.navigate(RutasNav.LOGIN)
                        }),

                )

                // Botón "Crear Cuenta"
                Button(
                    onClick = {
                        viewModel.registroValido(nombre, apellidoPaterno, apellidoMaterno, correo, password, navController)
                    },
                    enabled = registroEnabled,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5885C6)), // Azul para el botón
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Crear Cuenta", color = Color.White, fontSize = 18.sp)
                }

                when {
                    registerResponse != null -> {
                        LaunchedEffect(Unit) {
                            navController.navigate(RutasNav.LOGIN)
                        }
                    }

                    registerError != null ->
                        Text(
                            text = registerError ?: "Error",
                            color = Color.Red,
                        )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewRegister(){
    val navController = rememberNavController()
    RegistroUI(navController = navController, viewModel = RegistroVM())
}