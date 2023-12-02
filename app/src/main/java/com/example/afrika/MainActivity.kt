package com.example.afrika

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afrika.ui.theme.AfrikaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AfrikaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AfrikaApp()

                }
            }
        }
    }
}
@Composable
fun AfrikaApp() {
    var currentPic by remember { mutableStateOf(1) }
    var clickCount by remember { mutableStateOf(0) }
    when (currentPic) {
        1 -> {
            AfrikaTextAndImage(
                textLabelResourceId = R.string.art1,
                drawableResourceId = R.drawable.intro,
                contentDescriptionResourceId = R.string.art1_content_description,
                onImageClick = {
                    currentPic = 2
                    clickCount = (2..4).random()
                })
        }
        2 -> {
            AfrikaTextAndImage(
                textLabelResourceId = R.string.art2,
                drawableResourceId = R.drawable.dance,
                contentDescriptionResourceId = R.string.art2_content_description,
                onImageClick = {
                    clickCount--
                    if(clickCount == 0){
                        currentPic = 3
                    }
                })
        }
        3 -> {
            AfrikaTextAndImage(
                textLabelResourceId = R.string.art3,
                drawableResourceId = R.drawable.resource_return,
                contentDescriptionResourceId = R.string.art3_content_description,
                onImageClick = {
                    currentPic = 1

                })
        }

    }
}


@Composable
fun AfrikaTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    Box (
        modifier = modifier
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(contentDescriptionResourceId)
                )

            }
            Spacer(
                modifier = modifier.height(16.dp) )
            Text(
                text = stringResource(textLabelResourceId),
                fontSize = 16.sp
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun AfrikaPreview() {
    AfrikaTheme {
        AfrikaApp()
    }
}