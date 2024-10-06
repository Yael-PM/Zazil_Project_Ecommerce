package com.ypm.zazil_project_ecommerce.view.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ypm.zazil_project_ecommerce.R
import com.ypm.zazil_project_ecommerce.view.Modifiers.customBackgrond
import com.ypm.zazil_project_ecommerce.viewmodel.RutasNav


@Composable
fun BottomBar(navController: NavController){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destinoActual = navBackStackEntry?.destination

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier
                .width(370.dp)
                .background(color = Color(0xFFD22973), shape = RoundedCornerShape(12.dp))
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .width(370.dp)
                    .height(50.dp)
                    .customBackgrond(idColor = R.color.primary_500),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonBottomBar(
                    painter_selected = painterResource(id = R.drawable.list_icon_fill),
                    painter_unselected = painterResource(id = R.drawable.list_icon_outline),
                    titulo = "Historial",
                    destinoActual = destinoActual,
                    ruta = RutasNav.HISTORIAL,
                    navController = navController
                )

                ButtonBottomBar(
                    painter_selected = painterResource(id = R.drawable.person_icon_fill),
                    painter_unselected = painterResource(id = R.drawable.person_icon_outline),
                    titulo = "Cuenta",
                    destinoActual = destinoActual,
                    ruta = RutasNav.PERFIL,
                    navController = navController
                )

                ButtonBottomBar(
                    painter_selected = painterResource(id = R.drawable.store_icon_fill),
                    painter_unselected = painterResource(id = R.drawable.store_icon_outline),
                    titulo = "Tienda",
                    destinoActual = destinoActual,
                    ruta = RutasNav.TIENDA,
                    navController = navController
                )

                ButtonBottomBar(
                    painter_selected = painterResource(id = R.drawable.groups_icon_fill),
                    painter_unselected = painterResource(id = R.drawable.groups_icon_outline),
                    titulo = "Comunidad",
                    destinoActual = destinoActual,
                    ruta = RutasNav.COMUNIDAD,
                    navController = navController
                )

                ButtonBottomBar(
                    painter_selected = painterResource(id = R.drawable.car_icon_fill),
                    painter_unselected = painterResource(id = R.drawable.car_icon_outline),
                    titulo = "Carrito",
                    destinoActual = destinoActual,
                    ruta = RutasNav.HOME,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun ButtonBottomBar(
    painter_selected: Painter,
    painter_unselected: Painter,
    titulo: String,
    destinoActual: NavDestination?,
    ruta: String,
    navController: NavController
){

    val seleccionado = destinoActual?.hierarchy?.any { it.route == ruta } == true
    
    val icono_actual = if (seleccionado) painter_selected else painter_unselected
    
    Column(
        modifier = Modifier
            .size(50.dp)
            .customBackgrond(idColor = R.color.primary_500)
            .clickable(onClick = {
                navController.navigate(ruta)
            }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            //El painter tiene que ser una imagen en el drawable int
            painter = icono_actual,
            contentDescription = titulo,
            tint = Color.White,
            modifier = Modifier
                .size(30.dp)
        )
    }
}

@Preview
@Composable
fun PreviewBar(){
    val navController = rememberNavController()
    BottomBar(navController)
}
