package com.ypm.zazil_project_ecommerce.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }  // Estado para manejar el texto ingresado

    OutlinedTextField(
        value = searchText,
        onValueChange = { newText -> searchText = newText },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        placeholder = { Text(text = "Buscar...") },  // Texto que se muestra cuando no hay input
        leadingIcon = {  // Icono de búsqueda al inicio
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Icono de búsqueda"
            )
        },
        shape = RoundedCornerShape(12.dp),  // Bordes redondeados
        maxLines = 1,  // Limitar a una línea
        singleLine = true
    )
}