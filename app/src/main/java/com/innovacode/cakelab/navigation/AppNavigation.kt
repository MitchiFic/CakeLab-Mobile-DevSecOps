package com.innovacode.cakelab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.innovacode.cakelab.ui.screens.CatalogScreen
import com.innovacode.cakelab.ui.screens.HomeScreen
import com.innovacode.cakelab.ui.screens.LoginScreen
import com.innovacode.cakelab.ui.screens.RegisterScreen
import com.innovacode.cakelab.ui.screens.CustomizeCakeScreen
import com.innovacode.cakelab.ui.screens.CartScreen
import com.innovacode.cakelab.ui.screens.PaymentScreen
import com.innovacode.cakelab.ui.screens.OrdersScreen
import com.innovacode.cakelab.ui.screens.ProfileScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.innovacode.cakelab.viewmodel.CakeLabViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val cakeLabViewModel: CakeLabViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(Routes.HOME)
                },
                onRegisterClick = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegisterClick = {
                    navController.navigate(Routes.HOME)
                },
                onBackLoginClick = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onCatalogClick = {
                    navController.navigate(Routes.CATALOG)
                },
                onProfileClick = {
                    navController.navigate(Routes.PROFILE)
                },
                onOrdersClick = {
                    navController.navigate(Routes.ORDERS)
                },
                onLogoutClick = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.HOME) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Routes.CATALOG) {
            CatalogScreen(
                onCustomizeClick = {
                    navController.navigate(Routes.CUSTOMIZE)
                }
            )
        }

        composable(Routes.CUSTOMIZE) {
            CustomizeCakeScreen(
                viewModel = cakeLabViewModel,
                onAddCartClick = {
                    navController.navigate(Routes.CART)
                },
                onBackCatalogClick = {
                    navController.navigate(Routes.CATALOG)
                }
            )
        }

        composable(Routes.CART) {
            CartScreen(
                viewModel = cakeLabViewModel,
                onPaymentClick = {
                    navController.navigate(Routes.PAYMENT)
                },
                onBackCatalogClick = {
                    navController.navigate(Routes.CATALOG)
                }
            )
        }

        composable(Routes.PAYMENT) {
            PaymentScreen(
                viewModel = cakeLabViewModel,
                onConfirmPaymentClick = {
                    navController.navigate(Routes.ORDERS)
                },
                onBackCartClick = {
                    navController.navigate(Routes.CART)
                }
            )
        }

        composable(Routes.PROFILE) {
            ProfileScreen(
                onSaveProfileClick = {
                    navController.navigate(Routes.HOME)
                },
                onBackHomeClick = {
                    navController.navigate(Routes.HOME)
                }
            )
        }

        composable(Routes.ORDERS) {
            OrdersScreen(
                viewModel = cakeLabViewModel,
                onBackHomeClick = {
                    navController.navigate(Routes.HOME)
                },
                onNewOrderClick = {
                    cakeLabViewModel.limpiarPedido()
                    navController.navigate(Routes.CATALOG)
                }
            )
        }
    }
}

