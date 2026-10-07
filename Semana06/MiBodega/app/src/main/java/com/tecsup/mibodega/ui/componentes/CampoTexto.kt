package com.tecsup.mibodega.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.tecsup.mibodega.ui.theme.VerdeBodega

@Composable
fun CampoTexto(
    etiqueta: String,
    valor: String,
    onValorCambia: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    teclado: KeyboardType = KeyboardType.Text,
    esError: Boolean = false,
    mensajeError: String? = null
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = etiqueta,
            style = MaterialTheme.typography.bodySmall,
            color = if (esError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
        )

        OutlinedTextField(
            value = valor,
            onValueChange = onValorCambia,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            placeholder = {
                if (placeholder.isNotEmpty()) {
                    Text(placeholder, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f))
                }
            },
            singleLine = true,
            isError = esError,
            visualTransformation = if (teclado == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = teclado),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = VerdeBodega,
                errorBorderColor = MaterialTheme.colorScheme.error,
                errorContainerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            supportingText = {
                if (esError && !mensajeError.isNullOrBlank()) {
                    Text(mensajeError, color = MaterialTheme.colorScheme.error)
                }
            }
        )
    }
}