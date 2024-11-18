package com.example.likertaccessibility.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.likertaccessibility.R
import com.example.likertaccessibility.components.CustomCardButton

@Composable
fun MainScreen(
    onButtonClickWithoutAccessibility: () -> Unit,
    onButtonClickWithAccessibility: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomCardButton(
            icon = R.drawable.ic_error,
            iconTint = R.color.error,
            text = R.string.main_text_card_without_accessibility,
            onClick = onButtonClickWithoutAccessibility,
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomCardButton(
            icon = R.drawable.ic_check_circle,
            iconTint = R.color.success,
            text = R.string.main_text_card_with_accessibility,
            onClick = onButtonClickWithAccessibility,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    MainScreen({}, {})
}
