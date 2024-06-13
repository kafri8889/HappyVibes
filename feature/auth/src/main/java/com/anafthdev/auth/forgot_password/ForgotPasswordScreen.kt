package com.anafthdev.auth.forgot_password

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptionsBuilder
import com.anafthdev.auth.component.AuthTextField
import com.anafthdev.data.Destination
import com.anafthdev.data.R
import com.anafthdev.ui.component.HvButton
import com.anafthdev.ui.theme.HappyVibesTheme

@Preview(showSystemUi = false, showBackground = true)
@Composable
private fun ForgotPasswordScreenPreview() {
    HappyVibesTheme {
        var state by remember { mutableStateOf(ForgotPasswordState()) }

        ForgotPasswordScreenContent(
            state = state,
            onSubmitClicked = {},
            onNavigationIconClicked = {},
            onEmailChanged = { email ->
                state = state.copy(email = email)
            },
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel,
    modifier: Modifier = Modifier,
    navigateTo: (Destination, builder: (NavOptionsBuilder.() -> Unit)?) -> Unit,
    onNavigationIconClicked: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ForgotPasswordScreenContent(
        state = state,
        onEmailChanged = viewModel::setEmail,
        onNavigationIconClicked = onNavigationIconClicked,
        onSubmitClicked = {},
        modifier = modifier
            .fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ForgotPasswordScreenContent(
    state: ForgotPasswordState,
    modifier: Modifier = Modifier,
    onNavigationIconClicked: () -> Unit,
    onEmailChanged: (String) -> Unit,
    onSubmitClicked: () -> Unit
) {
    Box(modifier) {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = onNavigationIconClicked) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = null
                    )
                }
            },
            actions = {
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier
                        .padding(end = 8.dp)
                )
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.enter_the_email),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )

            Text(
                text = stringResource(id = R.string.enter_your_registered_email_with),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Normal
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = state.email,
                onValueChange = onEmailChanged,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                placeholder = {
                    Text("ranran@gmail.com")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            
            HvButton(
                onClick = onSubmitClicked,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.submit))
            }
        }
    }
}
