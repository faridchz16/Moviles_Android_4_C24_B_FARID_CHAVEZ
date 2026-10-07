package com.tecsup.mibodega.ui.cliente.screens.inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material.icons.outlined.DeliveryDining
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.mibodega.ui.cliente.modelo.Producto
import com.tecsup.mibodega.ui.cliente.modelo.RepositorioPedidos
import com.tecsup.mibodega.ui.cliente.modelo.RepositorioUsuarios
import com.tecsup.mibodega.ui.cliente.modelo.listaCategorias
import com.tecsup.mibodega.ui.cliente.modelo.listaProductosFake
import com.tecsup.mibodega.ui.componentes.ProductoCard
import com.tecsup.mibodega.ui.theme.RojoPrecio
import com.tecsup.mibodega.ui.theme.VerdeBodega

enum class OrdenPrecio {
    DEFECTO,
    MENOR_A_MAYOR,
    MAYOR_A_MENOR
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    productos: List<Producto> = listaProductosFake,
    cantidadCarrito: Int,
    pestanaInicial: Int = 0,
    tienePedidoRealizado: Boolean = false,
    esModoOscuro: Boolean = false,
    onCambiarModoOscuro: (Boolean) -> Unit = {},
    onCambiarPestana: (Int) -> Unit = {},
    onVerCarrito: () -> Unit,
    onProductoClick: (Producto) -> Unit,
    onAgregarProducto: (Producto) -> Unit,
    onCerrarSesion: () -> Unit = {}
) {
    var textoBusqueda by remember { mutableStateOf("") }
    var categoriaSeleccionada by remember { mutableStateOf(listaCategorias.first()) }
    var indicePestana by remember { mutableIntStateOf(pestanaInicial) }

    var favoritosIds by remember { mutableStateOf(setOf<Int>()) }
    var mostrarSoloFavoritos by remember { mutableStateOf(false) }
    var ordenSeleccionado by remember { mutableStateOf(OrdenPrecio.DEFECTO) }

    LaunchedEffect(pestanaInicial) {
        indicePestana = pestanaInicial
    }

    val productosFiltradosYOrdenados = remember(
        productos,
        categoriaSeleccionada,
        textoBusqueda,
        mostrarSoloFavoritos,
        favoritosIds,
        ordenSeleccionado
    ) {
        val listaFiltrada = productos.filter { prod ->
            val coincideCategoria = if (categoriaSeleccionada == "Todos") true else prod.categoria.equals(categoriaSeleccionada, ignoreCase = true)
            val coincideTexto = if (textoBusqueda.isBlank()) true else {
                prod.nombre.contains(textoBusqueda.trim(), ignoreCase = true) ||
                        prod.descripcion.contains(textoBusqueda.trim(), ignoreCase = true)
            }
            val coincideFavorito = if (mostrarSoloFavoritos) favoritosIds.contains(prod.id) else true

            coincideCategoria && coincideTexto && coincideFavorito
        }

        when (ordenSeleccionado) {
            OrdenPrecio.MENOR_A_MAYOR -> listaFiltrada.sortedBy { it.precio }
            OrdenPrecio.MAYOR_A_MENOR -> listaFiltrada.sortedByDescending { it.precio }
            OrdenPrecio.DEFECTO -> listaFiltrada
        }
    }

    val productosEnPares = remember(productosFiltradosYOrdenados) {
        productosFiltradosYOrdenados.chunked(2)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when (indicePestana) {
                            0 -> if (mostrarSoloFavoritos) "Mis Favoritos" else "Mi Bodega"
                            1 -> "Categorías"
                            2 -> "Mis Pedidos"
                            else -> "Mi Perfil"
                        },
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = onVerCarrito) {
                        BadgedBox(
                            badge = {
                                if (cantidadCarrito > 0) {
                                    Badge { Text("$cantidadCarrito") }
                                }
                            }
                        ) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                        }
                    }
                }
            )
        },
        bottomBar = {
            BarraInferior(
                indiceSeleccionado = indicePestana,
                onSeleccionarIndice = { nuevoIndice ->
                    indicePestana = nuevoIndice
                    onCambiarPestana(nuevoIndice)
                }
            )
        }
    ) { paddingInterno ->
        when (indicePestana) {
            0 -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingInterno)
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    item {
                        OutlinedTextField(
                            value = textoBusqueda,
                            onValueChange = { textoBusqueda = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            placeholder = { Text("Buscar productos...") },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                            trailingIcon = {
                                if (textoBusqueda.isNotEmpty()) {
                                    IconButton(onClick = { textoBusqueda = "" }) {
                                        Icon(Icons.Default.Clear, contentDescription = "Limpiar búsqueda")
                                    }
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = VerdeBodega
                            )
                        )

                        Text(
                            text = "Categorías",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                        )

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(vertical = 4.dp)
                        ) {
                            items(listaCategorias) { categoria ->
                                ChipCategoria(
                                    texto = categoria,
                                    seleccionado = categoria == categoriaSeleccionada,
                                    onClick = { categoriaSeleccionada = categoria }
                                )
                            }
                        }

                        Spacer(Modifier.height(8.dp))

                        // CHIPS: FAVORITOS Y ORDENAR POR PRECIO
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FilterChip(
                                selected = mostrarSoloFavoritos,
                                onClick = { mostrarSoloFavoritos = !mostrarSoloFavoritos },
                                label = {
                                    Text(
                                        text = if (mostrarSoloFavoritos) "Favoritos (${favoritosIds.size})" else "Favoritos",
                                        fontWeight = if (mostrarSoloFavoritos) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = if (mostrarSoloFavoritos) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                        contentDescription = null,
                                        tint = if (mostrarSoloFavoritos) Color(0xFFE53935) else Color.Gray,
                                        modifier = Modifier.size(16.dp)
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Color(0xFFFFEBEE),
                                    selectedLabelColor = Color(0xFFC62828)
                                )
                            )

                            FilterChip(
                                selected = ordenSeleccionado != OrdenPrecio.DEFECTO,
                                onClick = {
                                    ordenSeleccionado = when (ordenSeleccionado) {
                                        OrdenPrecio.DEFECTO -> OrdenPrecio.MENOR_A_MAYOR
                                        OrdenPrecio.MENOR_A_MAYOR -> OrdenPrecio.MAYOR_A_MENOR
                                        OrdenPrecio.MAYOR_A_MENOR -> OrdenPrecio.DEFECTO
                                    }
                                },
                                label = {
                                    Text(
                                        when (ordenSeleccionado) {
                                            OrdenPrecio.MENOR_A_MAYOR -> "Precio: Menor a mayor"
                                            OrdenPrecio.MAYOR_A_MENOR -> "Precio: Mayor a menor"
                                            OrdenPrecio.DEFECTO -> "Ordenar por precio"
                                        }
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.SwapVert,
                                        contentDescription = null,
                                        tint = if (ordenSeleccionado != OrdenPrecio.DEFECTO) VerdeBodega else Color.Gray,
                                        modifier = Modifier.size(18.dp)
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = VerdeBodega.copy(alpha = 0.15f),
                                    selectedLabelColor = VerdeBodega
                                )
                            )
                        }

                        Spacer(Modifier.height(4.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (mostrarSoloFavoritos) "Mis Productos Favoritos"
                                else if (textoBusqueda.isNotBlank()) "Resultados de búsqueda"
                                else "Productos destacados",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${productosFiltradosYOrdenados.size} productos",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Spacer(Modifier.height(6.dp))
                    }

                    if (productosEnPares.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 40.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = if (mostrarSoloFavoritos) Icons.Default.FavoriteBorder else Icons.Default.SearchOff,
                                        contentDescription = null,
                                        tint = Color.Gray,
                                        modifier = Modifier.size(56.dp)
                                    )
                                    Spacer(Modifier.height(12.dp))
                                    Text(
                                        text = if (mostrarSoloFavoritos) "Aún no tienes favoritos" else "No se encontraron productos",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = if (mostrarSoloFavoritos) "Toca el corazón en cualquier producto para guardarlo aquí."
                                        else "Prueba con otra palabra o categoría.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    } else {
                        items(productosEnPares) { parDeProductos ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                for (producto in parDeProductos) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        ProductoCard(
                                            producto = producto,
                                            esFavorito = favoritosIds.contains(producto.id),
                                            onToggleFavorito = {
                                                favoritosIds = if (favoritosIds.contains(producto.id)) {
                                                    favoritosIds - producto.id
                                                } else {
                                                    favoritosIds + producto.id
                                                }
                                            },
                                            onClick = { onProductoClick(producto) },
                                            onAgregar = { onAgregarProducto(producto) }
                                        )
                                    }
                                }
                                if (parDeProductos.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }
            1 -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingInterno)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            text = "Todas las Categorías",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "Selecciona una categoría para explorar sus productos",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.height(8.dp))
                    }

                    val categoriasSinTodos = listaCategorias.filter { it != "Todos" }
                    items(categoriasSinTodos) { cat ->
                        val cantidad = productos.count { it.categoria.equals(cat, ignoreCase = true) }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    categoriaSeleccionada = cat
                                    mostrarSoloFavoritos = false
                                    indicePestana = 0
                                    onCambiarPestana(0)
                                },
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(46.dp)
                                            .background(VerdeBodega.copy(alpha = 0.12f), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = if (cat == "Bebidas") Icons.Default.LocalDrink else Icons.Default.Category,
                                            contentDescription = null,
                                            tint = VerdeBodega,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Spacer(Modifier.width(16.dp))
                                    Column {
                                        Text(
                                            text = cat,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "$cantidad productos disponibles",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
            2 -> {
                val historialPedidos = RepositorioPedidos.historial

                if (historialPedidos.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingInterno)
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        item {
                            Text(
                                text = "Historial de Pedidos",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "Revisa el estado de tus compras",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(Modifier.height(8.dp))
                        }

                        items(historialPedidos) { pedidoItem ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = pedidoItem.id,
                                            fontWeight = FontWeight.Bold,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Box(
                                            modifier = Modifier
                                                .background(Color(0xFFFEF3C7), RoundedCornerShape(8.dp))
                                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Icon(
                                                    imageVector = Icons.Outlined.DeliveryDining,
                                                    contentDescription = null,
                                                    tint = Color(0xFFD97706),
                                                    modifier = Modifier.size(16.dp)
                                                )
                                                Spacer(Modifier.width(4.dp))
                                                Text(
                                                    text = "En camino",
                                                    color = Color(0xFFB45309),
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 12.sp
                                                )
                                            }
                                        }
                                    }

                                    Spacer(Modifier.height(8.dp))
                                    Text(
                                        text = "${pedidoItem.fecha} • Entrega: ${pedidoItem.direccion}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )

                                    Spacer(Modifier.height(10.dp))
                                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                                    Spacer(Modifier.height(10.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "${pedidoItem.items.sumOf { it.cantidad }} productos (${pedidoItem.tipoEntrega})",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Text(
                                            text = "S/ ${String.format("%.2f", pedidoItem.total)}",
                                            fontWeight = FontWeight.Bold,
                                            color = RojoPrecio
                                        )
                                    }
                                }
                            }
                        }
                    }
                } else {
                    VistaPestanaSecundaria(
                        paddingValues = paddingInterno,
                        icono = Icons.Default.Receipt,
                        titulo = "Historial de Pedidos",
                        descripcion = "Aquí podrás revisar el seguimiento de tus pedidos confirmados."
                    )
                }
            }
            3 -> VistaPerfilMejorada(
                paddingValues = paddingInterno,
                esModoOscuro = esModoOscuro,
                onCambiarModoOscuro = onCambiarModoOscuro,
                onCerrarSesion = {
                    RepositorioUsuarios.cerrarSesion()
                    onCerrarSesion()
                }
            )
        }
    }
}

@Composable
private fun VistaPerfilMejorada(
    paddingValues: PaddingValues,
    esModoOscuro: Boolean,
    onCambiarModoOscuro: (Boolean) -> Unit,
    onCerrarSesion: () -> Unit
) {
    val usuario = RepositorioUsuarios.usuarioActivo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .background(VerdeBodega.copy(alpha = 0.15f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = VerdeBodega,
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(Modifier.height(12.dp))

        Text(
            text = usuario?.nombre ?: "Farid Chavez",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Cliente frecuente",
            style = MaterialTheme.typography.bodyMedium,
            color = VerdeBodega,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Información Personal",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(Modifier.height(12.dp))

                FilaDatoPerfil(
                    icono = Icons.Default.Phone,
                    titulo = "Teléfono",
                    valor = usuario?.telefono ?: "No registrado"
                )
                Spacer(Modifier.height(10.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                Spacer(Modifier.height(10.dp))

                val direccionCompleta = if (!usuario?.referencia.isNullOrBlank()) {
                    "${usuario?.direccion} (${usuario?.referencia})"
                } else {
                    usuario?.direccion ?: "No registrada"
                }

                FilaDatoPerfil(
                    icono = Icons.Default.LocationOn,
                    titulo = "Dirección de entrega",
                    valor = direccionCompleta
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DarkMode,
                        contentDescription = "Modo Oscuro",
                        tint = VerdeBodega,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Modo Oscuro",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = if (esModoOscuro) "Activado" else "Desactivado",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Switch(
                    checked = esModoOscuro,
                    onCheckedChange = onCambiarModoOscuro,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = VerdeBodega
                    )
                )
            }
        }

        Spacer(Modifier.height(28.dp))

        Button(
            onClick = onCerrarSesion,
            colors = ButtonDefaults.buttonColors(containerColor = RojoPrecio),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Cerrar sesión",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
private fun FilaDatoPerfil(
    icono: ImageVector,
    titulo: String,
    valor: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icono,
            contentDescription = null,
            tint = VerdeBodega,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text = titulo,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = valor,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun VistaPestanaSecundaria(
    paddingValues: PaddingValues,
    icono: ImageVector,
    titulo: String,
    descripcion: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = VerdeBodega,
                modifier = Modifier.size(56.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = descripcion,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ChipCategoria(
    texto: String,
    seleccionado: Boolean,
    onClick: () -> Unit
) {
    val fondo = if (seleccionado) VerdeBodega else MaterialTheme.colorScheme.surfaceVariant
    val contenido = if (seleccionado) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Row(
        modifier = Modifier
            .background(fondo, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(text = texto, color = contenido, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun BarraInferior(
    indiceSeleccionado: Int,
    onSeleccionarIndice: (Int) -> Unit
) {
    val items = listOf(
        Triple("Inicio", Icons.Default.Home, 0),
        Triple("Categorías", Icons.Default.List, 1),
        Triple("Pedidos", Icons.Default.Receipt, 2),
        Triple("Perfil", Icons.Default.Person, 3)
    )
    NavigationBar {
        items.forEach { (etiqueta, icono, indice) ->
            NavigationBarItem(
                selected = indiceSeleccionado == indice,
                onClick = { onSeleccionarIndice(indice) },
                icon = { Icon(icono, contentDescription = etiqueta) },
                label = { Text(etiqueta) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = VerdeBodega,
                    selectedTextColor = VerdeBodega
                )
            )
        }
    }
}