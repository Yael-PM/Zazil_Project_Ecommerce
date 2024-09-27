package com.ypm.zazil_project_ecommerce.view
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

object Modifiers {

    @Composable
    fun Modifier.customBackgrond(idColor: Int): Modifier{
        return this.then(
            Modifier.background(
                color = Color(
                    ContextCompat.getColor(
                        LocalContext.current,
                        idColor
                    )
                )
            )
        )
    }
}