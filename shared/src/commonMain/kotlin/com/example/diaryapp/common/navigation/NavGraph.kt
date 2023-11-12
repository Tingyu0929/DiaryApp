import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import com.example.diaryapp.common.navigation.Screen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    navigator: Navigator = rememberNavigator()
) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    NavHost(
        navigator = navigator,
        initialRoute = Screen.MainScreen.route, // 一開始要顯示的畫面。
    ) {
        scene(route = Screen.MainScreen.route) {
            MainScreen(
                bottomSheetState = bottomSheetState,
                addDiaryOnClicked = {
                    navigator.navigate(Screen.DetailScreen.route.plus("/$it"))
                }
            )
        }
        scene(route = Screen.DetailScreen.route.plus(Screen.DetailScreen.objectPath)) { backStackEntry ->
            Screen.DetailScreen.objectName?.let { objectName ->
                val diaryId: Long? = backStackEntry.path<Long>(objectName)
                DetailScreen(
                    diaryId = diaryId ?: 0,
                    onBackClicked = { navigator.popBackStack() }
                )
            }
        }
    }
}