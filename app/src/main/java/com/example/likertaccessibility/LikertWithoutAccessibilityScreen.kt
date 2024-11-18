package com.example.likertaccessibility

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

private const val NO_SELECT = -1

@Composable
fun LikertWithoutAccessibilityScreen(onDismiss: () -> Unit) {
    val snackBarHostState = remember { SnackbarHostState() }
    var selectedRating by remember { mutableIntStateOf(NO_SELECT) }
    var selectedLabel by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val snackBarMessageSuccess = stringResource(id = R.string.message_success_snackbar)
    val snackBarMessageError = stringResource(id = R.string.message_error_snackbar)

    Box(modifier = Modifier.fillMaxSize()) {
        SnackbarHost(hostState = snackBarHostState, modifier = Modifier.clearAndSetSemantics { })
        Icon(
            Icons.Default.Close,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable(onClick = onDismiss)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(R.string.title),
                style = MaterialTheme.typography.displaySmall,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(
                text = stringResource(R.string.subtitle),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 36.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf(
                    "😡" to stringResource(R.string.lickert_terrible),
                    "😞" to stringResource(R.string.lickert_bad),
                    "😐" to stringResource(R.string.lickert_regular),
                    "😊" to stringResource(R.string.lickert_good),
                    "😍" to stringResource(R.string.lickert_excellent),
                ).forEachIndexed { index, (emoji, label) ->
                    IconToggleButton(
                        checked = selectedRating == index,
                        onCheckedChange = {
                            if (selectedRating != index) {
                                selectedRating = index
                                selectedLabel = label
                            }
                        },
                        modifier = Modifier
                            .size(53.dp)
                            .background(
                                if (selectedRating == index) colorResource(R.color.light_gray) else Color.Transparent,
                                shape = MaterialTheme.shapes.small,
                            )
                    ) {
                        Text(
                            text = emoji,
                            fontSize = 30.sp,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 7.dp)
                    .clearAndSetSemantics { }
            ) {
                Text(
                    text = selectedLabel,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Box {
                Button(
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .semantics { contentDescription = "Sem marcador" }
                        .fillMaxWidth()
                        .height(48.dp)
                        .align(Alignment.BottomCenter),
                    onClick = {
                        coroutineScope.launch {
                            val snackBarMessage =
                                if (selectedRating == NO_SELECT) snackBarMessageError else snackBarMessageSuccess
                            snackBarHostState.showSnackbar(
                                message = snackBarMessage,
                                duration = SnackbarDuration.Short,
                            )
                        }
                    }
                ) {
                    Text(
                        text = stringResource(R.string.text_button),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clearAndSetSemantics { }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LinkerWithoutAccessibilityDefaultPreview() {
    LikertWithoutAccessibilityScreen {}
}
