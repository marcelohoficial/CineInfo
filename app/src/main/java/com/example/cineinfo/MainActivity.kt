package com.example.cineinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cineinfo.ui.theme.CineInfoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CineInfoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieDetailScreen()
                }
            }
        }
    }
}

@Composable
fun MovieDetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF262A35)) // fundo neutro
    ) {
        // Parte de cima (imagem com gradiente leve)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f) // metade da tela
        ) {
            // Poster
            Image(
                painter = painterResource(id = R.drawable.poster_exemplo),
                contentDescription = "Poster do filme",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            // Gradiente suave para o corte da imagem
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xFF262A35)),
                            startY = 200f
                        )
                    )
            )
        }

        // Parte de baixo (conteúdo)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Título
            Text(
                text = "O Auto da Compadecida",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )



            Spacer(modifier = Modifier.height(8.dp))

            // Infos curtas
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("2000", color = Color.LightGray, fontSize = 14.sp)
                Spacer(Modifier.width(8.dp))
                Text("HD", color = Color.White, fontSize = 12.sp,
                    modifier = Modifier
                        .background(Color.DarkGray, RoundedCornerShape(4.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp))
                Spacer(Modifier.width(8.dp))
                Text("1h 44m", color = Color.LightGray, fontSize = 14.sp)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "⭐ 9.5/10",
                    fontSize = 16.sp,
                    color = Color(0xFFFFD700),
                    fontWeight = FontWeight.SemiBold
                )

            }
        }
    }
}
