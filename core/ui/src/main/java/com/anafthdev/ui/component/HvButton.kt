package com.anafthdev.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anafthdev.ui.extension.accent
import com.anafthdev.ui.extension.onAccent

@Preview(showBackground = true)
@Composable
private fun HvButtonPreview() {
    HvButton(
        onClick = {}
    ) {
        Text(text = "Sign In")
    }
}

@Composable
fun HvButton(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    containerColor: Color = MaterialTheme.colorScheme.accent,
    contentColor: Color = MaterialTheme.colorScheme.onAccent,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        contentPadding = contentPadding,
        shape = RoundedCornerShape(2.dp),
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onAccent
        )
    ) {
        ProvideTextStyle(
            MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.SemiBold
            )
        ) {
            content()
        }
    }
}