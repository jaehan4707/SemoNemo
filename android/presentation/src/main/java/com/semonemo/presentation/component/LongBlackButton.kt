package com.semonemo.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semonemo.presentation.R
import com.semonemo.presentation.theme.SemonemoTheme
import com.semonemo.presentation.theme.Typography
import com.semonemo.presentation.ui.theme.Main02
import com.semonemo.presentation.ui.theme.White

/*
@Param  icon : 버튼에 들어갈 아이콘. 없다면 null
        text : 버튼에 들어갈 텍스트
 */

@Composable
fun LongBlackButton(
    modifier: Modifier = Modifier,
    icon: Int?,
    text: String,
) {
    Surface(
        onClick = {},
        shape = RoundedCornerShape(14.dp),
        modifier =
            modifier
                .height(53.dp),
    ) {
        Box(
            modifier = modifier.background(brush = Main02).padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                icon?.let {
                    Image(
                        modifier = Modifier.wrapContentSize(),
                        painter = painterResource(id = it),
                        contentDescription = "",
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                }
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = text,
                    style = Typography.bodySmall.copy(color = White),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewLongBlackButton() {
    LongBlackButton(icon = R.drawable.img_fire, text = "검정 버튼입니다.")
}
