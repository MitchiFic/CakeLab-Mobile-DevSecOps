package com.innovacode.cakelab.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CakeLabViewModel : ViewModel() {

    var sabor by mutableStateOf("")
        private set

    var relleno by mutableStateOf("")
        private set

    var tamano by mutableStateOf("")
        private set

    var decoracion by mutableStateOf("")
        private set

    var mensaje by mutableStateOf("")
        private set

    var indicaciones by mutableStateOf("")
        private set

    var total by mutableStateOf(450)
        private set

    fun guardarPersonalizacion(
        nuevoSabor: String,
        nuevoRelleno: String,
        nuevoTamano: String,
        nuevaDecoracion: String,
        nuevoMensaje: String,
        nuevasIndicaciones: String
    ) {
        sabor = nuevoSabor
        relleno = nuevoRelleno
        tamano = nuevoTamano
        decoracion = nuevaDecoracion
        mensaje = nuevoMensaje
        indicaciones = nuevasIndicaciones

        total = when (nuevoTamano.lowercase()) {
            "chico" -> 300
            "mediano" -> 450
            "grande" -> 600
            else -> 450
        }
    }

    fun limpiarPedido() {
        sabor = ""
        relleno = ""
        tamano = ""
        decoracion = ""
        mensaje = ""
        indicaciones = ""
        total = 450
    }
}

