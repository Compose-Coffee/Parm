package kr.co.onboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.co.onboard.ui.OnBoardRoute
import kr.co.onboard.ui.login.AddressSelectionListener
import kr.co.onboard.ui.login.LocationSearchScreen
import kr.co.onboard.ui.login.LocationSearchWebViewScreen

const val ONBOARD_ROUTE = "onboardRoute"
internal const val ADDRESS_ROUTE = "addressRoute"
internal const val ADDRESS_FIND_ROUTE = "addressFindRoute"
internal const val CROP_ROUTE = "cropRoute"
internal const val WELCOME_ROUTE = "welcomeRoute"

fun NavGraphBuilder.onboardNavGraph(
    navController: NavController
) {
    composable(
        route = ONBOARD_ROUTE
    ) {
        OnBoardRoute()
    }

    composable(
        route = ADDRESS_ROUTE
    ) {

    }

    composable(
        route = ADDRESS_FIND_ROUTE
    ) {

    }

    composable(
        route = CROP_ROUTE
    ) {

    }

    composable(
        route = WELCOME_ROUTE
    ) {

    }

    composable("location_search") {
        LocationSearchScreen(
            navController = navController,
            initialFullRoadAddr = "",
            initialJibunAddr = "",
            onAddressSelected = { roadAddr, jibun ->

            }
        )
    }

    composable("webview") {
        LocationSearchWebViewScreen(navController = navController, addressSelectionListener = object : AddressSelectionListener {
            override fun onAddressSelected(fullRoadAddr: String, jibunAddr: String) {
                navController.previousBackStackEntry?.savedStateHandle?.set("fullRoadAddr", fullRoadAddr)
                navController.previousBackStackEntry?.savedStateHandle?.set("jibunAddr", jibunAddr)
                navController.popBackStack()
            }
        })
    }
}