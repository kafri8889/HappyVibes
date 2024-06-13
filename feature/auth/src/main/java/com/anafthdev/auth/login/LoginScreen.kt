package com.anafthdev.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptionsBuilder
import com.anafthdev.auth.component.AuthTextField
import com.anafthdev.data.Destination
import com.anafthdev.data.R
import com.anafthdev.ui.component.HvButton
import com.anafthdev.ui.extension.accent
import com.anafthdev.ui.extension.textGray
import com.anafthdev.ui.theme.HappyVibesTheme

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    HappyVibesTheme {
        var state by remember { mutableStateOf(LoginState()) }

        LoginScreenContent(
            state = state,
            onEmailChanged = {
                state = state.copy(email = it)
            },
            onPasswordChanged = {
                state = state.copy(password = it)
            },
            onPasswordVisibilityChanged = {
                state = state.copy(showPassword = it)
            },
            onRememberMeChanged = {
                state = state.copy(rememberMe = it)
            },
            onRegisterClicked = {},
            onSignInClicked = {},
            onForgotPasswordClicked = {},
            onLoginWithGoogleClicked = {},
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier,
    navigateTo: (Destination, builder: (NavOptionsBuilder.() -> Unit)?) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreenContent(
        state = state,
        onEmailChanged = viewModel::setEmail,
        onPasswordChanged = viewModel::setPassword,
        onPasswordVisibilityChanged = viewModel::setShowPassword,
        onRememberMeChanged = viewModel::setRememberMe,
        onSignInClicked = {},
        onLoginWithGoogleClicked = {},
        onForgotPasswordClicked = {
            navigateTo(Destination.ForgotPassword, null)
        },
        onRegisterClicked = {
            navigateTo(Destination.Register) {
                popUpTo(Destination.Login) {
                    inclusive = true
                }

                launchSingleTop = true
            }
        },
        modifier = modifier
            .systemBarsPadding()
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    )
}

@Composable
private fun LoginScreenContent(
    state: LoginState,
    modifier: Modifier = Modifier,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordVisibilityChanged: (Boolean) -> Unit,
    onRememberMeChanged: (Boolean) -> Unit,
    onRegisterClicked: () -> Unit,
    onSignInClicked: () -> Unit,
    onForgotPasswordClicked: () -> Unit,
    onLoginWithGoogleClicked: () -> Unit
) {

    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        LoginWelcomeText()

        Spacer(modifier = Modifier.height(32.dp))

        LoginTextFieldGroup(
            email = state.email,
            password = state.password,
            showPassword = state.showPassword,
            rememberMe = state.rememberMe,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            onPasswordVisibilityChanged = onPasswordVisibilityChanged,
            onRememberMeChanged = onRememberMeChanged,
            onForgotPasswordClicked = onForgotPasswordClicked,
        )

        Spacer(modifier = Modifier.height(16.dp))

        HvButton(
            onClick = onSignInClicked,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.sign_in))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color.Black)
            )

            Text(
                text = stringResource(id = R.string.or_login_with),
                style = MaterialTheme.typography.bodyMedium
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color.Black)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HvButton(
            containerColor = Color.Transparent,
            onClick = onLoginWithGoogleClicked,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.login_with_google))

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_google_filled),
                contentDescription = null,
                modifier = Modifier
                    .size(ButtonDefaults.IconSize)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = stringResource(id = R.string.dont_have_an_account),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.textGray
                )
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(id = R.string.register_now),
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Light
                ),
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onRegisterClicked()
                    }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun LoginWelcomeText(
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current

    val measurer = rememberTextMeasurer()
    val textWidth = measurer.measure(
        text = stringResource(R.string.lets_sign_you_in),
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.ExtraBold
        )
    )

    Column(modifier) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .width(with(density) { textWidth.size.width.toDp() })
                    .height(12.dp)
                    .offset(y = 2.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.accent)
            )

            Text(
                text = stringResource(R.string.lets_sign_you_in),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.lets_sign_you_in_sub),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun LoginTextFieldGroup(
    email: String,
    password: String,
    showPassword: Boolean,
    rememberMe: Boolean,
    modifier: Modifier = Modifier,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordVisibilityChanged: (Boolean) -> Unit,
    onRememberMeChanged: (Boolean) -> Unit,
    onForgotPasswordClicked: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(modifier) {
        AuthTextField(
            title = stringResource(id = R.string.email),
            value = email,
            onValueChange = onEmailChanged,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text("ranran@gmail.com")
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(
            title = stringResource(id = R.string.password),
            value = password,
            onValueChange = onPasswordChanged,
            singleLine = true,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            trailingIcon = {
                IconButton(
                    onClick = {
                        onPasswordVisibilityChanged(!showPassword)
                    }
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (showPassword) R.drawable.ic_eye else R.drawable.ic_eye_slash
                        ),
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = onRememberMeChanged,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.accent
                )
            )

            Text(
                text = stringResource(id = R.string.remember_me),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.textGray
                ),
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onRememberMeChanged(!rememberMe)
                    }
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.forgot_password_ask),
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.textGray
                ),
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onForgotPasswordClicked()
                    }
            )
        }
    }
}