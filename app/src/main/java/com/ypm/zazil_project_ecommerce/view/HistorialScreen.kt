package com.ypm.zazil_project_ecommerce.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ypm.zazil_project_ecommerce.view.components.BottomBar
import com.ypm.zazil_project_ecommerce.view.components.HistorialCard
import com.ypm.zazil_project_ecommerce.viewmodel.HistorialVM


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HistorialUI(usuario: String?, navController: NavController, historialVM: HistorialVM = viewModel()) {

    val listaHistorial by historialVM.listaHistorial.collectAsState()

    LaunchedEffect(Unit) {
        historialVM.obtenerListaHistorial(usuario.toString())
    }

    Scaffold(
        bottomBar = { BottomBar(usuario, navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6)), // Fondo de la pantalla
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Título en negrita
            Text(
                text = "Historial",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tabla
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Encabezados de la tabla
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "ID", fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(text = "Total", fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(text = "Fecha", fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(text = "Estatus", fontWeight = FontWeight.Bold, color = Color.Black)
                }

                LazyColumn(
                    modifier = Modifier
                        .height(450.dp)
                        .padding(5.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(listaHistorial){ historial ->
                        HistorialCard(
                            id = historial.id_carrito,
                            estado = historial.estado,
                            total = historial.total.toString(),
                            fecha = historial.f_actualizacion
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewHistorialScreen() {
    val navController = rememberNavController()
    HistorialUI(usuario = "1", navController)
}