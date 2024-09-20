package cc.bap5

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import org.jetbrains.skiko.loadBytesFromPath

@Composable
fun App() {
    val fontFamilyResolver = LocalFontFamilyResolver.current
    var fontFamily by remember { mutableStateOf<FontFamily?>(null) }
    val fontsLoaded = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        fontFamily = FontFamily(
            androidx.compose.ui.text.platform.Font(
                "NotoSansJP",
                loadBytesFromPath("NotoSansJP-Medium.ttf")
            )
        )
        fontFamilyResolver.preload(fontFamily!!)
        fontsLoaded.value = true
    }
    MaterialTheme(
        typography = Typography().run {
            copy(
                displayLarge = displayLarge.copy(fontFamily = fontFamily),
                displayMedium = displayMedium.copy(fontFamily = fontFamily),
                displaySmall = displaySmall.copy(fontFamily = fontFamily),
                headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
                headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
                headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
                bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
                bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
                bodySmall = bodySmall.copy(fontFamily = fontFamily),
                labelLarge = labelLarge.copy(fontFamily = fontFamily),
                labelMedium = labelMedium.copy(fontFamily = fontFamily),
                labelSmall = labelSmall.copy(fontFamily = fontFamily)
            )
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            if (!fontsLoaded.value) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
            AnimatedVisibility(fontsLoaded.value) {
                CVPage()
            }
        }
    }
}
