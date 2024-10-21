package com.ypm.zazil_project_ecommerce

import ComunidadUI
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ypm.zazil_project_ecommerce.ui.theme.Zazil_Project_EcommerceTheme
import com.ypm.zazil_project_ecommerce.view.AvisoPrivacidadYTerminosScreen
import com.ypm.zazil_project_ecommerce.view.CarritoUI
import com.ypm.zazil_project_ecommerce.view.ConocenosUI
import com.ypm.zazil_project_ecommerce.view.CuentaUI
import com.ypm.zazil_project_ecommerce.view.DetalleUI
import com.ypm.zazil_project_ecommerce.view.HistorialUI
import com.ypm.zazil_project_ecommerce.view.HomeUI
import com.ypm.zazil_project_ecommerce.view.RegistroUI
import com.ypm.zazil_project_ecommerce.view.TiendaUI
import com.ypm.zazil_project_ecommerce.viewmodel.CuentaVM
import com.ypm.zazil_project_ecommerce.viewmodel.LoginVM
import com.ypm.zazil_project_ecommerce.viewmodel.RegistroVM
import com.ypm.zazil_project_ecommerce.viewmodel.RutasNav

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen() //Función que llama a la Splash Screen y es la que se ejecuta al inicio de la app
        enableEdgeToEdge()
        setContent {
            Zazil_Project_EcommerceTheme {

                val navController = rememberNavController() //Se crea el navController para la navegación de la app

                NavegacionRutasLogin(navController = navController)
            }
        }
    }
}

@Composable
fun NavegacionRutasLogin(
    navController: NavController
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = RutasNav.LOGIN
    ) {
        composable(RutasNav.LOGIN){
            IniciarSesionScreen(navController, LoginVM())
        }
        composable(RutasNav.HOME){
            val usuario = it.arguments?.getString("id")
            HomeUI(usuario, navController)
        }
        composable(RutasNav.REGISTRO){
            RegistroUI(navController, RegistroVM())
        }
        composable(RutasNav.AVISO){
            AvisoPrivacidadYTerminosScreen(navController)
        }
        composable(RutasNav.HISTORIAL){
            val usuario = it.arguments?.getString("id")
            HistorialUI(usuario, navController)
        }
        composable(RutasNav.PERFIL){
            val usuario = it.arguments?.getString("id")
            CuentaUI(usuario, navController)
        }
        composable(RutasNav.COMUNIDAD){
            val usuario = it.arguments?.getString("id")
            ComunidadUI(usuario, navController)
        }
        composable(RutasNav.TIENDA){
            val usuario = it.arguments?.getString("id")
            TiendaUI(usuario, navController)
        }
        composable(RutasNav.CARRITO){
            val usuario = it.arguments?.getString("id")
            CarritoUI(usuario, navController)
        }
        composable(RutasNav.CONOCENOS){
            val usuario = it.arguments?.getString("id")
            ConocenosUI(usuario, navController)
        }
        composable(RutasNav.DETALLES){
            val producto = it.arguments?.getString("id")
            DetalleUI(producto, navController)
        }
    }
}

@Composable
fun IniciarSesionScreen(navController: NavController = rememberNavController(), viewModel: LoginVM) {

    val correo: String by viewModel.correo.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val correoError: String? by viewModel.correoError.observeAsState(null)
    val passwordError: String? by viewModel.passwordError.observeAsState(null)
    val loginEnable: Boolean by viewModel.loginEnabled.observeAsState(false)

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

                // Título "Inicia Sesión"
                Text(
                    text = "Inicia Sesión",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Campos de entrada
                OutlinedTextField(
                    value = correo,
                    onValueChange = { viewModel.cambioLogin(it, password) },
                    label = { Text("Correo Electrónico") },
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
                    onValueChange = { viewModel.cambioLogin(correo, it) },
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

                // Enlaces de recuperación de contraseña y registro
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Aviso de Privacidad",
                        color = Color(0xFF2196F3),
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable(onClick = {
                                navController.navigate(RutasNav.AVISO)
                            })
                    )
                    Text(
                        text = "Registrarme",
                        color = Color(0xFF2196F3),
                        modifier = Modifier
                            .clickable(onClick = {
                                navController.navigate(RutasNav.REGISTRO)
                            })
                    )
                }

                // Botón "Iniciar sesión"
                Button(
                    onClick = {
                        viewModel.validarLogin(correo, password, navController)
                        //navController.navigate(RutasNav.HOME)
                    },
                    enabled = loginEnable,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5885C6)), // Azul para el botón
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Iniciar sesión", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IniciarSesionScreenPreview() {
    IniciarSesionScreen(viewModel = LoginVM())
}