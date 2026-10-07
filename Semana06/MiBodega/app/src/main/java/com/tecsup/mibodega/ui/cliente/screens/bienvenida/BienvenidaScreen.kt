package com.tecsup.mibodega.ui.cliente.screens.bienvenida

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.mibodega.R
import com.tecsup.mibodega.ui.cliente.modelo.RepositorioUsuarios
import com.tecsup.mibodega.ui.cliente.modelo.Usuario
import com.tecsup.mibodega.ui.theme.VerdeBodega

private const val USUARIO_FIJO = "987654321"
private const val PASSWORD_FIJO = "123456"

@Composable
fun BienvenidaScreen(
    onRegistrarse: () -> Unit,
    onIniciarSesion: () -> Unit,
    onTerminos: () -> Unit
) {
    var mostrarDialogoLogin by remember { mutableStateOf(false) }
    var usuarioLogin by remember { mutableStateOf("") }
    var passwordLogin by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.ilustracion_bodega),
            contentDescription = "Logo Supermarket",
            modifier = Modifier.size(140.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Mi Bodega",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = VerdeBodega
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Tus productos de siempre\nen la puerta de tu casa",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.weight(1f))

        Button(
            onClick = onRegistrarse,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VerdeBodega),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Chat,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "Registrarme con mi teléfono",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        OutlinedButton(
            onClick = {
                errorMensaje = null
                mostrarDialogoLogin = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Iniciar sesión",
                color = VerdeBodega,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
        }

        Spacer(Modifier.height(16.dp))

        TextButton(onClick = onTerminos) {
            Text(
                text = "Al continuar aceptas nuestros Términos y Condiciones",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(12.dp))
    }

    if (mostrarDialogoLogin) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogoLogin = false
                errorMensaje = null
            },
            title = {
                Text(
                    text = "Iniciar sesión",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Column {
                    Text(
                        text = "Ingresa tu número de teléfono y contraseña.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = usuarioLogin,
                        onValueChange = {
                            usuarioLogin = it
                            errorMensaje = null
                        },
                        label = { Text("Teléfono o usuario") },
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                        singleLine = true,
                        isError = errorMensaje != null,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            focusedBorderColor = VerdeBodega,
                            errorBorderColor = MaterialTheme.colorScheme.error
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = passwordLogin,
                        onValueChange = {
                            passwordLogin = it
                            errorMensaje = null
                        },
                        label = { Text("Contraseña") },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        singleLine = true,
                        isError = errorMensaje != null,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            focusedBorderColor = VerdeBodega,
                            errorBorderColor = MaterialTheme.colorScheme.error
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (errorMensaje != null) {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = errorMensaje.orEmpty(),
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val telefonoIngresado = usuarioLogin.trim()
                        val contrasenaIngresada = passwordLogin.trim()

                        if (telefonoIngresado.isBlank() || contrasenaIngresada.isBlank()) {
                            errorMensaje = "Por favor completa ambos campos"
                        } else {
                            val coincideFijo = (telefonoIngresado == USUARIO_FIJO && contrasenaIngresada == PASSWORD_FIJO)
                            val coincideRegistrado = RepositorioUsuarios.autenticar(telefonoIngresado, contrasenaIngresada)

                            if (coincideFijo || coincideRegistrado) {
                                if (coincideFijo && RepositorioUsuarios.usuarioActivo == null) {
                                    RepositorioUsuarios.usuarioActivo = Usuario(
                                        nombre = "Cliente Demo",
                                        telefono = USUARIO_FIJO,
                                        contrasena = PASSWORD_FIJO,
                                        direccion = "Av. Los Olivos 123",
                                        referencia = "Frente al parque"
                                    )
                                }
                                mostrarDialogoLogin = false
                                errorMensaje = null
                                onIniciarSesion()
                            } else {
                                errorMensaje = "Teléfono o contraseña incorrectos"
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = VerdeBodega),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Ingresar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarDialogoLogin = false
                        errorMensaje = null
                    }
                ) {
                    Text("Cancelar", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        )
    }
}