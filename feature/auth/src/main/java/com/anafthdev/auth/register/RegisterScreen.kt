package com.anafthdev.auth.register

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

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun RegisterScreenPreview() {
    var state by remember {
        mutableStateOf(RegisterState())
    }

    HappyVibesTheme {
        RegisterScreenContent(
            state = state,
            onNameChanged = {
                state = state.copy(name = it)
            },
            onUsernameChanged = {
                state = state.copy(username = it)
            },
            onEmailChanged = {
                state = state.copy(email = it)
            },
            onPasswordChanged = {
                state = state.copy(password = it)
            },
            onConfirmPasswordChanged = {
                state = state.copy(confirmationPassword = it)
            },
            onPasswordVisibilityChanged = {
                state = state.copy(showPassword = it)
            },
            onConfirmPasswordVisibilityChanged = {
                state = state.copy(showConfirmationPassword = it)
            },
            onRegisterWithGoogleClicked = {},
            onRegisterClicked = {},
            onLoginClicked = {},
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    modifier: Modifier = Modifier,
    navigateTo: (Destination, builder: (NavOptionsBuilder.() -> Unit)?) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    RegisterScreenContent(
        state = state,
        onNameChanged = viewModel::setName,
        onUsernameChanged = viewModel::setUsername,
        onEmailChanged = viewModel::setEmail,
        onPasswordChanged = viewModel::setPassword,
        onConfirmPasswordChanged = viewModel::setConfirmationPassword,
        onPasswordVisibilityChanged = viewModel::setPasswordVisibility,
        onConfirmPasswordVisibilityChanged = viewModel::setConfirmationPasswordVisibility,
        onRegisterWithGoogleClicked = {},
        onRegisterClicked = {},
        onLoginClicked = {
            navigateTo(Destination.Login) {
                popUpTo(Destination.Register) {
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
private fun RegisterScreenContent(
    state: RegisterState,
    modifier: Modifier = Modifier,
    onNameChanged: (String) -> Unit,
    onUsernameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onPasswordVisibilityChanged: (Boolean) -> Unit,
    onConfirmPasswordVisibilityChanged: (Boolean) -> Unit,
    onRegisterWithGoogleClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    onLoginClicked: () -> Unit
) {

    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        RegisterWelcomeText()

        Spacer(modifier = Modifier.height(32.dp))

        RegisterTextFieldGroup(
            name = state.name,
            username = state.username,
            email = state.email,
            password = state.password,
            confirmPassword = state.confirmationPassword,
            showPassword = state.showPassword,
            showConfirmPassword = state.showConfirmationPassword,
            onNameChanged = onNameChanged,
            onUsernameChanged = onUsernameChanged,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            onConfirmPasswordChanged = onConfirmPasswordChanged,
            onPasswordVisibilityChanged = onPasswordVisibilityChanged,
            onConfirmPasswordVisibilityChanged = onConfirmPasswordVisibilityChanged
        )

        Spacer(modifier = Modifier.height(16.dp))

        HvButton(
            onClick = onRegisterClicked,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.sign_up))
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
                text = stringResource(id = R.string.or_register_with),
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
            onClick = onRegisterWithGoogleClicked,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.register_with_google))

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
                text = stringResource(id = R.string.have_an_account),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.textGray
                )
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(id = R.string.sign_in),
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Light
                ),
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onLoginClicked()
                    }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun RegisterWelcomeText(
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current

    val measurer = rememberTextMeasurer()
    val textWidth = measurer.measure(
        text = stringResource(R.string.happy_vibes),
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.ExtraBold
        )
    )

    Column(modifier) {
        Text(
            text = stringResource(R.string.lets_register_you_to),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )

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
                text = stringResource(R.string.happy_vibes),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}

@Composable
private fun RegisterTextFieldGroup(
    name: String,
    username: String,
    email: String,
    password: String,
    confirmPassword: String,
    modifier: Modifier = Modifier,
    showPassword: Boolean = false,
    showConfirmPassword: Boolean = false,
    onNameChanged: (String) -> Unit,
    onUsernameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onPasswordVisibilityChanged: (Boolean) -> Unit,
    onConfirmPasswordVisibilityChanged: (Boolean) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AuthTextField(
                title = stringResource(id = R.string.name),
                value = name,
                onValueChange = onNameChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text("Song Xinran")
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
            )

            AuthTextField(
                title = stringResource(id = R.string.username),
                value = username,
                onValueChange = onUsernameChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text("ranran")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        AuthTextField(
            title = stringResource(id = R.string.email),
            value = email,
            onValueChange = onEmailChanged,
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

        AuthTextField(
            title = stringResource(id = R.string.password),
            value = password,
            onValueChange = onPasswordChanged,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
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

        AuthTextField(
            title = stringResource(id = R.string.confirm_password),
            value = confirmPassword,
            onValueChange = onConfirmPasswordChanged,
            visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
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
                        onConfirmPasswordVisibilityChanged(!showConfirmPassword)
                    }
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (showConfirmPassword) R.drawable.ic_eye else R.drawable.ic_eye_slash
                        ),
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}