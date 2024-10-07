package com.semonemo.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semonemo.presentation.R
import com.semonemo.presentation.theme.GunMetal
import com.semonemo.presentation.theme.ProgressGreen
import com.semonemo.presentation.theme.ProgressRed
import com.semonemo.presentation.theme.SemonemoTheme
import com.semonemo.presentation.theme.Typography
import com.semonemo.presentation.theme.White

/*
@Param  icon : 버튼에 들어갈 아이콘. 없다면 null
        text : 버튼에 들어갈 텍스트
 */

@Composable
fun AuctionBidMessage(
    modifier: Modifier = Modifier,
    icon: Int?,
    bidPrice: Long = 0L,
    anonym: Int = 0,
    user: Boolean = true,
) {
    val borderColor = if(user) ProgressGreen else ProgressRed
    val postfixMessage = if(user) " 입찰 성공" else " 입찰(${String.format("%03d", anonym)}번)"
    Surface(
        shape = RoundedCornerShape(10.dp), // 모서리 반경 10dp
        color = White,
        border = BorderStroke(width = 3.dp, color = borderColor),
        modifier =
            modifier
                .fillMaxWidth(0.88f)
                .height(50.dp),
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                icon?.let {
                    Icon(
                        modifier = Modifier.wrapContentSize(),
                        painter = painterResource(id = it),
                        contentDescription = "",
                        tint = Color.Unspecified,
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                }
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text =
                        String.format(
                            "%,d ${stringResource(id = R.string.coin_price_unit)}",
                            bidPrice,
                        ) + postfixMessage,
                    style =
                        Typography.bodyMedium.copy(
                            fontFeatureSettings = "tnum",
                            color = GunMetal,
                            fontSize = 16.sp,
                        ),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewAuctionBidMessageUser() {
    SemonemoTheme {
        AuctionBidMessage(icon = R.drawable.ic_color_sene_coin, bidPrice = 1000L)
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewAuctionBidMessageOther() {
    SemonemoTheme {
        AuctionBidMessage(icon = R.drawable.ic_color_sene_coin, bidPrice = 3000L, anonym = 17, user = false)
    }
}
