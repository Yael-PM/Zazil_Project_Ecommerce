package mx.adm.proyectodos.ui.theme

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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

data class ForoPregunta(
    val userName: String,
    val userImage: String,
    val question: String
)

@Composable
fun ForoScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var pregunta by remember { mutableStateOf("") }
    var showAnswer by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    // Lista de preguntas iniciales
    val preguntas = remember {
        mutableStateListOf(
            ForoPregunta("Ximena Méndez Vázquez", "file:///android_res/drawable/usuario.png", "Quisiera saber cómo regresar al inicio."),
        )
    }

    // Filtrar preguntas basado en el texto de búsqueda
    val preguntasFiltradas = preguntas.filter {
        it.question.contains(searchText, ignoreCase = true) || it.userName.contains(searchText, ignoreCase = true)
    }

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
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = { /* Acción para Foro */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD22973))
                    ) {
                        Text("Foro", color = Color.White, fontSize = 20.sp)
                    }
                    Box(
                        modifier = Modifier
                            .width(55.dp)
                            .height(2.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                    )
                }
                Button(
                    onClick = { /* Acción para Quienes somos */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD22973))
                ) {
                    Text("¿Quiénes somos?", color = Color.White, fontSize = 20.sp)
                }
            }
        }

        Text(
            text = "Foro",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(14.dp),
        )

        Spacer(modifier = Modifier.height(2.dp))
        // Campo de búsqueda con la imagen de lupa
        Box(
            modifier = Modifier
                .height(40.dp)
                .width(360.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberAsyncImagePainter(model = "file:///android_res/drawable/lupa.png"), // Aquí carga la imagen de la lupa
                    contentDescription = "Lupa",
                    modifier = Modifier.size(24.dp) .border(2.dp, Color.Black, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))

                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    decorationBox = { innerTextField ->
                        if (searchText.isEmpty()) {
                            Text(
                                text = "Buscar...",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        innerTextField() // Renderizar el campo de texto
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        // Mostrar preguntas filtradas
        preguntasFiltradas.forEach { pregunta ->
            // Tarjeta de la pregunta
            ForoPost(
                userName = "Ximena Méndez Vázquez",
                userImage = "file:///android_res/drawable/usuario.png",
                question = "Quisiera saber cómo regresar al inicio.",
                backgroundColor = Color(0xFF5885C6), // Azul
                isQuestion = true,
                showAnswer = showAnswer,
                onShowAnswerToggle = { showAnswer = !showAnswer }
            )

        }
        // Tarjeta de la respuesta
        if (showAnswer) {
            ForoPost(
                userName = "Juan Pérez López",
                userImage = "file:///android_res/drawable/usuario2.png",
                question = "No te preocupes, solo debes dar clic en la pantalla de inicio.",
                backgroundColor = Color(0xFFE0E0E0),
                isQuestion = false
            )
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

        Spacer(modifier = Modifier.height(390.dp))

        Box(
            modifier = Modifier
                .background(color = Color(0xFFD22973), shape = RoundedCornerShape(12.dp))
                .border(2.dp, Color(0xFFD22973), shape = RoundedCornerShape(12.dp))
                .width(360.dp)
                .height(50.dp)
                .padding(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(5) { index ->
                    Image(
                        painter = rememberAsyncImagePainter(model = "file:///android_res/drawable/icon$index.png"),
                        contentDescription = "Icon $index",
                        modifier = Modifier
                            .size(48.dp)
                            .border(2.dp, Color.Black, CircleShape)
                    )
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Hacer una pregunta") },
                text = {
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
                },
                confirmButton = {
                    Button(
                        onClick = {
                            preguntas.add(
                                ForoPregunta(
                                    userName = "Usuario Anónimo", // Aquí puedes usar el nombre de usuario real
                                    userImage = "file:///android_res/drawable/usuario.png",
                                    question = pregunta
                                )
                            )
                            showDialog = false
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
                }
            )
        }
    }
}

@Composable
fun ForoPost(
    userName: String,
    userImage: String,
    question: String,
    backgroundColor: Color,
    isQuestion: Boolean,
    showAnswer: Boolean = false,
    onShowAnswerToggle: (() -> Unit)? = null) {

    Box(
        modifier = Modifier
            .width(350.dp)
            .height(90.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(model = userImage),
                contentDescription = "User Image",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape)
            )
            Spacer(modifier = Modifier.size(12.dp))

            Column(modifier = Modifier
                .weight(1f) // ancho
                .padding(end = 40.dp)
            ) {
                Text(
                    text = userName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (isQuestion) Color.White else Color.Black
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = question,
                    fontSize = 14.sp,
                    color = if (isQuestion) Color.White else Color.Black
                )
            }
        }

        if (isQuestion && onShowAnswerToggle != null) {
            FloatingActionButton(
                onClick = onShowAnswerToggle,
                containerColor = Color(0xFF5885C6),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(40.dp)
            ) {
                Text("+", fontSize = 24.sp, color = Color.White)
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun ForoScreenPreview() {
    ForoScreen()
}