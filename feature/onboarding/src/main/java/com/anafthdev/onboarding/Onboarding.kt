package com.anafthdev.onboarding

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptionsBuilder
import com.anafthdev.data.Destination
import com.anafthdev.data.R
import com.anafthdev.ui.component.HvButton
import com.anafthdev.ui.extension.accent

@Preview
@Composable
private fun OnboardingScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        OnboardingScreen(
            navigateTo = { _, _ -> }
        )
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    navigateTo: (Destination, builder: (NavOptionsBuilder.() -> Unit)?) -> Unit
) {
    val context = LocalContext.current

    val image = remember(context) {
        context.assets.open("person.png").use {
            BitmapFactory.decodeStream(it)
        }.asImageBitmap()
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {

        Image(
            bitmap = image,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = "Lorem pisum",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 24.dp)
        )

        Text(
            text = "Commodo quis imperdiet massa tincidunt nunc pulvinar. Risus viverra adipiscing at in tellus integer. Aliquam ut porttitor leo a diam sollicitudin tempor id eu. Velit scelerisque in dictum non. ",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        )

        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .height(2.dp)
                .padding(horizontal = 24.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.accent)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Condimentum id venenatis a condimentum vitae sapien pellentesque habitant morbi. Morbi tempus iaculis urna id volutpat lacus laoreet non. Massa id neque aliquam vestibulum morbi blandit cursus.",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        HvButton(
            onClick = {
                navigateTo(Destination.Login, null)
            },
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.sign_in))
        }

        Spacer(modifier = Modifier.height(8.dp))

        HvButton(
            containerColor = Color.Transparent,
            onClick = {
                navigateTo(Destination.Register, null)
            },
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.sign_up))
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
