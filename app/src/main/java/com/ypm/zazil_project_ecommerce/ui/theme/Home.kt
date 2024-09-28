package mx.adm.proyectodos.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD22973))
            .padding(),
        contentAlignment = Alignment.BottomStart
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        )  {
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(700.dp)
                    .background(Color.White, shape = RoundedCornerShape(24.dp))
                    .padding(top = 54.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Nombre del usuario
                    Text(
                        text = "Jorge López Pérez",
                        color = Color.Black,
                        fontSize = 29.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 50.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        HomeButton(
                            painter = rememberAsyncImagePainter(model = "file:///android_res/drawable/tienda.png"),
                            text = "Tienda"
                        )
                        HomeButton(
                            painter = rememberAsyncImagePainter(model = "file:///android_res/drawable/historial.png"),
                            text = "Historial"
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        HomeButton(
                            painter = rememberAsyncImagePainter(model = "file:///android_res/drawable/cuenta.png"),
                            text = "Cuenta"
                        )
                        HomeButton(
                            painter = rememberAsyncImagePainter(model = "file:///android_res/drawable/foro.png"),
                            text = "Foro"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeButton(
    painter: Painter,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .size(130.dp)
            .background(color = Color(0xFF5885C6), shape = RoundedCornerShape(12.dp))
            .border(12.dp, Color(0xFF5885C6), shape = RoundedCornerShape(12.dp))
            .clickable { /* Acción al hacer clic */ },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painter,
            contentDescription = text,
            modifier = Modifier
                .size(55.dp)
                .border(2.dp, Color.Black, CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = text, fontSize = 20.sp, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

