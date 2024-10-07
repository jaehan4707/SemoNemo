package com.semonemo.presentation.screen.wallet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.semonemo.domain.model.Coin
import com.semonemo.domain.model.CoinHistory
import com.semonemo.presentation.BuildConfig
import com.semonemo.presentation.R
import com.semonemo.presentation.component.LoadingDialog
import com.semonemo.presentation.screen.nft.NftViewModel
import com.semonemo.presentation.screen.wallet.subscreen.TransactionHistoryBox
import com.semonemo.presentation.screen.wallet.subscreen.WalletCardBox
import com.semonemo.presentation.screen.wallet.subscreen.WalletCoinBox
import com.semonemo.presentation.theme.SemonemoTheme
import com.semonemo.presentation.util.toPrice
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.generated.Uint256
import java.time.LocalDateTime

data class ProductInfo(
    val message: String,
    val imageRes: Int,
    val color: Color,
)

@Composable
fun WalletRoute(
    modifier: Modifier,
    navigateToCoinDetail: () -> Unit,
    viewModel: WalletViewModel = hiltViewModel(),
    nftViewModel: NftViewModel = hiltViewModel(),
    onShowSnackBar: (String) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    WalletContent(
        modifier = modifier,
        navigateToCoinDetail = navigateToCoinDetail,
        uiState = uiState.value,
        uiEvent = viewModel.uiEvent,
        onShowSnackBar = onShowSnackBar,
        sendExchangePayableTransaction = { amount ->
            nftViewModel.sendTransaction(
                function =
                    Function(
                        "deposit",
                        listOf(
                            Uint256(amount.toBigInteger().toPrice()),
                        ),
                        listOf<TypeReference<*>>(object : TypeReference<Uint256>() {}),
                    ),
                onSuccess = { txHash ->
                    viewModel.exchangeCoinPayable(amount = amount.toLong(), txHash = txHash)
                },
                onError = {
                    onShowSnackBar(it)
                },
                contractAddress = BuildConfig.SYSTEM_CONTRACT_ADDRESS,
            )
        },
        sendExchangeCoinTransaction = { amount ->
            nftViewModel.sendTransaction(
                function =
                    Function(
                        "withdraw",
                        listOf(
                            Uint256(amount.toBigInteger().toPrice()),
                        ),
                        listOf<TypeReference<*>>(object : TypeReference<Uint256>() {}),
                    ),
                onSuccess = { txHash ->
                    viewModel.exchangePayableCoin(amount = amount.toLong(), txHash = txHash)
                },
                onError = {
                    onShowSnackBar(it)
                },
                contractAddress = BuildConfig.SYSTEM_CONTRACT_ADDRESS,
            )
        },
    )
}

@Composable
fun WalletContent(
    modifier: Modifier,
    navigateToCoinDetail: () -> Unit,
    uiState: WalletUiState,
    uiEvent: SharedFlow<WalletUiEvent>,
    onShowSnackBar: (String) -> Unit,
    sendExchangePayableTransaction: (String) -> Unit,
    sendExchangeCoinTransaction: (String) -> Unit,
) {
    LaunchedEffect(uiEvent) {
        uiEvent.collectLatest { event ->
            when (event) {
                is WalletUiEvent.Error -> onShowSnackBar(event.errorMessage)
            }
        }
    }
    WalletScreen(
        modifier = modifier,
        userName = uiState.nickname,
        coinPrice = uiState.coinPrice.toDouble(),
        changePercent = uiState.coinChanged,
        coinHistory = uiState.coinHistory,
        userCoin = uiState.userCoin,
        userId = uiState.userId,
        navigateToCoinDetail = navigateToCoinDetail,
        sendExchangePayableTransaction = sendExchangePayableTransaction,
        onShowSnackBar = onShowSnackBar,
        sendExchangeCoinTransaction = sendExchangeCoinTransaction,
    )

    if (uiState.isLoading) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .clickable(enabled = false) {},
        )
        LoadingDialog(
            lottieRes = R.raw.normal_load,
            loadingMessage = stringResource(R.string.wallet_loading_message),
            subMessage = stringResource(R.string.loading_sub_message),
        )
    }
}

@Composable
fun WalletScreen(
    modifier: Modifier = Modifier,
    navigateToCoinDetail: () -> Unit = {},
    userName: String = "짜이한",
    userCoin: Coin = Coin(),
    coinPrice: Double = 100000.0,
    changePercent: Double = 8.7,
    changePrice: Double = coinPrice * changePercent * 0.01,
    coinHistory: List<CoinHistory> = listOf(),
    userId: Long = 0L,
    sendExchangePayableTransaction: (String) -> Unit = {},
    sendExchangeCoinTransaction: (String) -> Unit = {},
    onShowSnackBar: (String) -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Column(
        modifier =
            modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(state = scrollState)
                .statusBarsPadding()
                .navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        WalletCardBox(
            modifier = modifier,
            userName = userName,
            userCoin = userCoin,
            navigateToCoinDetail = navigateToCoinDetail,
            sendExchangePayableTransaction = sendExchangePayableTransaction,
            sendExchangeCoinTransaction = sendExchangeCoinTransaction,
            onShowSnackBar = onShowSnackBar,
        )
        Spacer(modifier = Modifier.height(10.dp))
        WalletCoinBox(
            modifier = modifier,
            coinPrice = coinPrice,
            changePercent = changePercent,
            changePrice = changePrice,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = stringResource(R.string.recent_transaction_history))
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            repeat(coinHistory.size) { index ->
                val item = coinHistory[index]
                val isSell =
                    item.toUser?.let {
                        it.userId == userId
                    } ?: false
                val product = item.tradeType.split(" ").first()
                val originalDateTime = LocalDateTime.parse(item.createdAt)
                val dateOnly = originalDateTime.toLocalDate()
                TransactionHistoryBox(
                    modifier = modifier,
                    date = dateOnly.toString(),
                    isSell = isSell,
                    product = product,
                    price = item.amount.toDouble(),
                )
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun WalletScreenPreview() {
    SemonemoTheme {
        Scaffold { innerPadding ->
            WalletScreen(
                modifier = Modifier.padding(innerPadding),
                coinHistory = listOf(CoinHistory()),
            )
        }
    }
}
