package com.ypm.zazil_project_ecommerce.view

import android.annotation.SuppressLint
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ypm.zazil_project_ecommerce.view.components.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CuentaUI(navController: NavController) {
    
    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD22973))
                .padding(),
            contentAlignment = Alignment.BottomStart // Centrar
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = rememberAsyncImagePainter(model = "file:///android_res/drawable/perfil.png"),
                    contentDescription = "Perfil",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                        .padding(top = 136.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Caja blanca con bordes redondeados
                Box(
                    modifier = Modifier
                        .fillMaxWidth() // Ancho del rectángulo
                        .height(800.dp) // Ajusta la altura
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(24.dp)
                        ) // Fondo blanco con bordes redondeados
                        .padding(top = 34.dp), // Espacio superior para la imagen de perfil
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                            .background(Color(0xFFFFFFFF)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        // Nombre de perfil
                        Text(
                            text = "Jorge López Pérez",
                            color = Color.Black,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(vertical = 1.dp)
                        )
                        // Campo de texto para nombre con fondo blanco
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(top = 30.dp)
                                .background(Color(0xFFFFFFFF)) // Fondo blanco
                        ) {
                            OutlinedTextField(
                                value = "Jorge",
                                onValueChange = {},
                                label = { Text("Nombre") },
                                modifier = Modifier.fillMaxWidth(),

                                )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        //texto para apellido con fondo blanco
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .background(Color.White) // Fondo blanco
                        ) {
                            OutlinedTextField(
                                value = "Pérez",
                                onValueChange = {},
                                label = { Text("Apellido") },
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // texto para email con fondo blanco
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .background(Color.White) // Fondo blanco
                        ) {
                            OutlinedTextField(
                                value = "jorgito@gmail.com",
                                onValueChange = {},
                                label = { Text("Email") },
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // texto para contraseña con fondo blanco
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .background(Color.White)
                        ) {
                            OutlinedTextField(
                                value = "••••••••",
                                onValueChange = {},
                                label = { Text("Contraseña") },
                                modifier = Modifier.fillMaxWidth(),
                                visualTransformation = PasswordVisualTransformation()
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        // Botones
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { /* Acción de Editar */ },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .width(160.dp)
                                    .height(42.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5885C6))
                            ) {
                                Text("Editar", color = Color.White)
                            }

                            Button(
                                onClick = { /* Acción de Guardar cambios */ },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .width(160.dp)
                                    .height(42.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5885C6))
                            ) {
                                Text("Guardar Cambios", color = Color.White)
                            }
                        }
                        Spacer(modifier = Modifier.height(45.dp))
                    }
                }
            }
        }
    }
    

}
