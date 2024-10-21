package com.ypm.zazil_project_ecommerce.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.ypm.zazil_project_ecommerce.R
import com.ypm.zazil_project_ecommerce.view.Modifiers.customBackgrond
import com.ypm.zazil_project_ecommerce.view.components.BottomBar
import com.ypm.zazil_project_ecommerce.view.components.CardCart
import com.ypm.zazil_project_ecommerce.viewmodel.CarritoVM

@SuppressLint("ResourceAsColor", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CarritoUI(usuarioID: String?, navController: NavController, carritoVM: CarritoVM = viewModel()) {

    val carritos by carritoVM.listaCarrito.collectAsState()
    val total by carritoVM.total.collectAsState()

    LaunchedEffect(Unit) {
        carritoVM.obtenerListaCarrito(usuarioID)
        carritoVM.obtenerTotal(carritos)
    }

    Scaffold(
        bottomBar =  { BottomBar(usuarioID, navController = navController) }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .customBackgrond(idColor = R.color.fondo_default)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            // Título del carrito
            Text(
                text = "Carrito",
                modifier = Modifier
                    .padding(top = 16.dp) // Ajustar el padding superior
                    .align(Alignment.CenterHorizontally), // Alinear al centro
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            // Espacio entre el título y la lista de productos
            Spacer(modifier = Modifier.height(16.dp))

            // Lista de productos en el carrito
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .customBackgrond(idColor = R.color.fondo_default),
                verticalArrangement = Arrangement.Top // Alinear al inicio
            ) {
                carritos.forEach { carrito ->
                    item {
                        CardCart(
                            id_producto = carrito.id_producto,
                            id_usuario = usuarioID,
                            imagen = "http://189.139.200.234:4000/api/imagenes/" + carrito.ruta_img,
                            nombre = carrito.nombre_producto,
                            precio = carrito.precio.toString(),
                            stock = carrito.stock,
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }

            // Espacio entre la lista y la sección de total/comprar
            Spacer(modifier = Modifier.height(16.dp))

            // Fila con el total y el botón de "Comprar"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.White)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${total}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de "Comprar"
            Button(
                onClick = {
                    carritoVM.cambiarEstadoCarrito(usuarioID.toString(), navController)
                },
                modifier = Modifier
                    .width(370.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Comprar",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Espacio debajo del botón
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCarrito() {
    val navController = rememberNavController()
    CarritoUI(usuarioID = "1", navController)
}
