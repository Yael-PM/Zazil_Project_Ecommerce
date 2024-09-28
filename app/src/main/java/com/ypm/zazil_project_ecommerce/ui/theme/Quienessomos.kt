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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun QuienessomosScreen() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Rectángulo rosa de foro y quienes somos
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD22973)) // Fondo rosa
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

                }
                Button(
                    onClick = { /* Acción para Quienes somos */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD22973) )
                ) {
                    // Botón Quienes somos con línea abajo
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("¿Quiénes somos?", color = Color.White, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(4.dp)) // Espacio entre texto línea
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(2.dp)
                                .fillMaxWidth()
                                .background(Color.White)
                        )
                    }
                }

            }
        }


        Spacer(modifier = Modifier.height(20.dp))

        QuienesTPost(
            title = "¿Quiénes somos?",
            backgroundColor = Color(0xFF5885C6),
            isTitle = true
        )

        // Tarjeta de quienes somos
        DescripcionPost(
            data = "Zazil es una marca comprometida con el bienestar de las mujeres y el cuidado del medio ambiente. Su misión es proporcionar soluciones innovadoras y sostenibles para el período menstrual. ¿Cómo lo hacen? A través de la creación de toallas femeninas reutilizables.\n" +
                    "“Cambiando el Mundo, un Ciclo a la Vez.”",
            backgroundColor = Color(0xFFE0E0E0),
            isData = false
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Misión y visión",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MisionPost(
                title = "* Misión",
                backgroundColor = Color(0xFFE0E0E0),
                isTitle = false
            )
            MisionPost(
                title = "* Visión",
                backgroundColor = Color(0xFFE0E0E0),
                isTitle = false
            )
        }


        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = "Datos curiosos",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
        )

        Spacer(modifier = Modifier.height(2.dp))
        DatoPost(
            title = "Dato",
            backgroundColor = Color(0xFFD22973),
            isTitle = true
        )

        // Tarjeta de Dato
        InfoPost(
            data = "Info del dato",
            backgroundColor = Color(0xFFE0E0E0),
            isData = false
        )
        Spacer(modifier = Modifier.height(20.dp))

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
                // Botones representados con imágenes
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

    }
}

@Composable
fun QuienesTPost(title: String, backgroundColor: Color, isTitle: Boolean) {
    Column(
        modifier = Modifier
            .width(350.dp)
            .height(70.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(1.dp))

        Column {
            Text(
                fontWeight = FontWeight.Bold,
                text = title,
                fontSize = 25.sp,
                color = if (isTitle) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun DescripcionPost(data: String, backgroundColor: Color, isData: Boolean) {
    Column(
        modifier = Modifier
            .width(350.dp)
            .height(150.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(10.dp),
    ) {
        Spacer(modifier = Modifier.size(12.dp))

        Column {
            Text(
                fontWeight = FontWeight.Bold,
                text = data,
                fontSize = 14.sp,
                color = if (isData) Color.White else Color.Black
            )
        }
    }
}


@Composable
fun MisionPost(title: String, backgroundColor: Color, isTitle: Boolean) {
    Row(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .border(5.dp, Color(0xFF5885C6), shape = RoundedCornerShape(12.dp))
            .padding(10.dp),
    ) {
        Spacer(modifier = Modifier.size(12.dp))

        Column {
            Text(
                fontWeight = FontWeight.Bold,
                text = title,
                fontSize = 14.sp,
                color = if (isTitle) Color.White else Color.Black
            )
        }
    }
}
@Composable
fun DatoPost(title: String, backgroundColor: Color, isTitle: Boolean) {
    Column(
        modifier = Modifier
            .width(350.dp)
            .height(50.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(1.dp))

        Column {
            Text(
                fontWeight = FontWeight.Bold,
                text = title,
                fontSize = 25.sp,
                color = if (isTitle) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun InfoPost(data: String, backgroundColor: Color, isData: Boolean) {
    Column(
        modifier = Modifier
            .width(350.dp)
            .height(90.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.size(12.dp))

        Column {
            Text(
                fontWeight = FontWeight.Bold,
                text = data,
                fontSize = 14.sp,
                color = if (isData) Color.White else Color.Black
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuienessomosScreenPreview() {
    QuienessomosScreen()
}
