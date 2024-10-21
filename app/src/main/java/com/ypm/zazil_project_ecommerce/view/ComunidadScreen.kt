import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ypm.zazil_project_ecommerce.view.components.BottomBar
import com.ypm.zazil_project_ecommerce.view.components.CardPregunta
import com.ypm.zazil_project_ecommerce.viewmodel.ComunidadVM


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ComunidadUI(usuario: String?, navController: NavController, comunidadVM: ComunidadVM = viewModel()) {
    var showDialog by remember { mutableStateOf(false) }
    var pregunta by remember { mutableStateOf("") }

    val listaRespuestas by comunidadVM.listaRespuestas.collectAsState()

    LaunchedEffect(Unit) {
        comunidadVM.obtenerListaRespuestas()
    }

    Scaffold(
        bottomBar = { BottomBar(usuario, navController) }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Rectángulo rosa con botones
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD22973))
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Botón Foro con línea abajo
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            onClick = { navController.navigate("foro/${usuario}") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD22973))
                        ) {
                            Text("Foro", color = Color.White, fontSize = 20.sp)
                        }
                        // Línea de resaltado
                        Box(
                            modifier = Modifier
                                .width(55.dp)
                                .height(2.dp)
                                .fillMaxWidth()
                                .background(Color.White)
                        )
                    }
                    Button(
                        onClick = { navController.navigate("conocenos/${usuario}") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD22973))
                    ) {
                        Text("Conócenos", color = Color.White, fontSize = 20.sp)
                    }
                }
            }

            // Título
            Text(
                text = "Foro",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp),
                color = Color.Black
            )

            // Tarjeta de la pregunta
            LazyColumn(
                modifier = Modifier
                    .height(450.dp)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listaRespuestas) { respuesta ->
                    // Recordar el estado individual de cada pregunta
                    var showAnswer by remember { mutableStateOf(false) }

                    if(respuesta != null){
                        CardPregunta(
                            id_pregunta = respuesta.id_pregunta,
                            id_usuario = respuesta.id_usuario,
                            firstName = respuesta.firstName ?: "Desconocido",
                            lastName = respuesta.lastName ?: "Desconocido",
                            titulo = respuesta.titulo,
                            id_usuario_respuesta = respuesta.id_usuario_respuesta ?: "",
                            respuesta = respuesta.respuesta ?: "",
                            fecha_respuesta = respuesta.fecha_respuesta ?: "s.f.",
                        )
                    }
                }
            }

            // Botón "Hacer Pregunta"
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .padding(16.dp)
                    .height(50.dp)
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5885C6))
            ) {
                Text("Hacer Pregunta", color = Color.White)
            }

            // Diálogo para hacer preguntas
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Hacer una pregunta") },
                    text = {
                        // Envolver el contenido en un Box para aplicar el fondo blanco
                        Box(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(16.dp)
                        ) {
                            Column {
                                Text(text = "Escribe tu pregunta: ")
                                Spacer(modifier = Modifier.height(8.dp))
                                BasicTextField(
                                    value = pregunta,
                                    onValueChange = { pregunta = it },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .border(1.dp, Color.Black)
                                        .padding(8.dp),
                                )
                            }
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog = false
                                comunidadVM.addPregunta(usuario.toString(), pregunta, navController)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5885C6))
                        ) {
                            Text("Hacer Pregunta", color = Color.White)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Cancelar")
                        }
                    },
                    containerColor = Color.White  // Fondo blanco para  el AlertDialog
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ForoScreenPreview() {
    val navController = rememberNavController()
    ComunidadUI(usuario = "Juan Pérez", navController)
}