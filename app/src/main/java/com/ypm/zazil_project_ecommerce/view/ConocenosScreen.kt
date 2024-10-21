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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.ypm.zazil_project_ecommerce.view.components.BottomBar
import com.ypm.zazil_project_ecommerce.viewmodel.RutasNav

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConocenosUI(usuario: String?, navController: NavController) {
    Scaffold(
        bottomBar = { BottomBar(usuario, navController) }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
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
                            onClick = { navController.navigate("comunidad/${usuario}") },
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
                            Text("Conócenos", color = Color.White, fontSize = 20.sp)
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
                    title = "Misión\n\n" +
                            "En Zazil, no solo estamos redefiniendo la\n" +
                            "menstruación, sino también el impacto que tiene en\n" +
                            "la economía y el medio ambiente. Nuestra misión es\n" +
                            "empoderar a las mujeres a tomar decisiones\n" +
                            "informadas sobre su salud menstrual mientras\n" +
                            "generan un impacto positivo en su bienestar\n" +
                            "financiero y en el planeta.",
                    backgroundColor = Color(0xFFE0E0E0),
                    isTitle = false
                )
                MisionPost(
                    title = "Visión\n\n" +
                            "Imaginamos un mundo donde la menstruación no\n" +
                            "solo es sostenible para el planeta, sino también\n" +
                            "empoderadora para todas las mujeres. Queremos\n" +
                            "que cada elección consciente de Zazil contribuya a la\n" +
                            "creación de comunidades fuertes, mujeres\n" +
                            "empoderadas económicamente y un entorno más\n" +
                            "saludable y equitativo. Nuestra visión es que Zazil no\n" +
                            "sea solo un producto, sino una fuerza positiva que\n" +
                            "transforma la forma en que vivimos la menstruación\n" +
                            "promoviendo el bienestar personal y global\n",
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
                data = "\"La producción y eliminación de toallas sanitarias contribuye significativamente a la huella de carbono. Por ejemplo, una toalla sanitaria puede emitir alrededor de 397.8 kg de CO2eq durante su ciclo de vida.\"",
                backgroundColor = Color(0xFFE0E0E0),
                isData = false
            )

            Spacer(modifier = Modifier.height(2.dp))
            DatoPost(
                title = "Dato",
                backgroundColor = Color(0xFFD22973),
                isTitle = true
            )

            // Tarjeta de Dato
            InfoPost(
                data = "Un estudio publicado en la revista Nature reveló que los residuos de toallas sanitarias son una fuente significativa de contaminación. Estos residuos contribuyen a la contaminación del suelo y el agua, y a la formación de la gran isla en el Pacífico",
                backgroundColor = Color(0xFFE0E0E0),
                isData = false
            )
            Spacer(modifier = Modifier.height(80.dp))

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
            .width(160.dp)
            .height(250.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .border(5.dp, Color(0xFF5885C6), shape = RoundedCornerShape(12.dp))
            .padding(10.dp),
    ) {
        Spacer(modifier = Modifier.size(12.dp))

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Text(
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
            .height(60.dp)
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
fun ConocenosScreenPreview() {
    val navController = rememberNavController()
    ConocenosUI(usuario = "Juan Pérez", navController)
}
