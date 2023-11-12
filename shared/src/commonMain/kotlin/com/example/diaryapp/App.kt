import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.diaryapp.common.theme.AppTheme
import com.moriatsushi.insetsx.rememberWindowInsetsController
import moe.tlaster.precompose.PreComposeApp

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    val windowInsets = rememberWindowInsetsController()

    LaunchedEffect(Unit) {
        windowInsets?.setIsNavigationBarsVisible(false)
        windowInsets?.setIsStatusBarsVisible(true)
        windowInsets?.setSystemBarsBehavior(com.moriatsushi.insetsx.SystemBarsBehavior.Immersive)
    }

    PreComposeApp {
        AppTheme(
            darkTheme = darkTheme,
            dynamicColor = dynamicColor
        ) {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize()
            ) {
                Navigation()
            }
        }
    }
}