package cz.mendelu.pef.va1.xmichl.homework2.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun LetterAvatar(letter: Char) {
    val colors: List<Color> = listOf(
        Color.Blue,
        Color.Red,
        Color.Gray,
        Color.Green,
        Color.Magenta,
        Color.Yellow
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(50.dp)
            .background(colors.get(Random.nextInt(0, 5)), shape = CircleShape)
    ) {
        Text(
            text = letter.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp

        )
    }
}