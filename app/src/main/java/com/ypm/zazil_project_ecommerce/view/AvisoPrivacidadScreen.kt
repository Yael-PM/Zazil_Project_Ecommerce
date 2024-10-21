package com.ypm.zazil_project_ecommerce.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.ypm.zazil_project_ecommerce.R
import com.ypm.zazil_project_ecommerce.view.Modifiers.customBackgrond
import com.ypm.zazil_project_ecommerce.viewmodel.RutasNav

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AvisoPrivacidadYTerminosScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Aviso de Privacidad y Términos") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título del aviso
            Text(
                text = "Aviso de Privacidad",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Contenido del aviso de privacidad
            Text(
                text = """
                Tu privacidad es importante para nosotros. Esta política de privacidad describe cómo recopilamos, utilizamos y protegemos tu información personal. Nos comprometemos a garantizar que tu privacidad esté protegida.
                
                Si te pedimos que proporciones cierta información personal, como nombre, dirección de correo electrónico o número de teléfono, puedes estar seguro de que solo se utilizará de acuerdo con esta política.
                """.trimIndent(),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Título de términos y condiciones
            Text(
                text = "Términos y Condiciones",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Contenido de términos y condiciones
            Text(
                text = """
                1. **Uso de la Aplicación**: El uso de nuestra aplicación está sujeto a estos términos y condiciones. Al utilizar la aplicación, aceptas cumplir con los términos especificados.
                
                2. **Propiedad Intelectual**: Todos los contenidos presentes en la aplicación, como texto, imágenes, logotipos, y gráficos, son propiedad de la empresa. No se permite la reproducción total o parcial sin el consentimiento expreso.
                
                3. **Limitación de Responsabilidad**: No seremos responsables por cualquier daño o perjuicio que surja del uso de nuestra aplicación. El uso de la aplicación es bajo tu propio riesgo.
                
                4. **Modificaciones a los Términos**: Nos reservamos el derecho de modificar estos términos en cualquier momento. Es tu responsabilidad revisar los términos periódicamente.
                
                5. **Suspensión del Servicio**: Podemos suspender o cancelar tu acceso a la aplicación en cualquier momento sin previo aviso si consideramos que has violado estos términos.
                """.trimIndent(),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Botón de aceptar
            Button(
                onClick = { navController.navigate(RutasNav.LOGIN) },
            ){
                Text(text = "Aceptar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AvisoPrivacidadYTerminosPreview() {
    val navController = rememberNavController()
    AvisoPrivacidadYTerminosScreen(navController)
}