package cz.mendelu.pef.va1.xmichl.meminiapp.utils
//
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.graphics.drawscope.ContentDrawScope
//import androidx.compose.ui.graphics.ImageFilter
//
//class ImageUtils(
//    private val desiredColor: Color,
//    private val targetColor: Color
//) : androidx.compose.ui.draw.drawWithContent {
//
//    override fun ContentDrawScope.draw() {
//        drawContent()
//
//        drawRect(
//            color = desiredColor,
//            blendMode = androidx.compose.ui.graphics.BlendMode.ColorBurn
//        )
//
//        drawContent(
//            colorFilter = ColorFilter.tint(targetColor)
//        )
//    }
//}