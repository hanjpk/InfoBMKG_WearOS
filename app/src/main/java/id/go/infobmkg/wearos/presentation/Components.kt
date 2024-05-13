package id.go.infobmkg.wearos.presentation

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Text

@Composable
fun DialogChip(
    text: String,
    onClick: (() -> Unit)? = null,
    icon: @Composable (BoxScope.() -> Unit)?
) {
    Chip(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            onClick?.invoke()
        },
        icon = icon,
        colors = ChipDefaults.gradientBackgroundChipColors(
            startBackgroundColor = Color(0xff2c2c2d),
            endBackgroundColor = Color(0xff2c2c2d)
        ),
        label = {
            Text(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
    )
}

