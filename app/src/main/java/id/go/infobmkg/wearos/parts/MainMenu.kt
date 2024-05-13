package id.go.infobmkg.wearos.parts

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import id.go.infobmkg.wearos.parts.rotary.rotaryWithScroll
import id.go.infobmkg.wearos.presentation.DialogChip
import id.go.infobmkg.wearos.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


data class MenuItem(
    val name: String,
    val icon: ImageVector,
//    val activityClass: KClass<*>
)

@OptIn(ExperimentalHorologistApi::class, ExperimentalWearFoundationApi::class)
@Composable
fun MainMenu(
    listState: ScalingLazyListState = rememberScalingLazyListState(),
    focusRequester: FocusRequester ) {
    val context = LocalContext.current
    ScalingLazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .rotaryWithScroll(
                scrollableState = listState,
                focusRequester = focusRequester
            )
            .focusRequester(focusRequester)
            .focusable(),
        autoCentering = AutoCenteringParams(itemIndex = 1),
        state = listState,
    ) {
        item {  ListHeader {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.bmkg),
                        contentDescription = "Logo BMKG",
                        modifier = Modifier
                            .size(30.dp)
                    )
                    Spacer(modifier = Modifier.size(3.dp))
                    Text(
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        text = "InfoBMKG",
                        style = MaterialTheme.typography.title2
                    )

                }
                Text(
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    text = "Multi-Hazard Early Warning System",
                    style = MaterialTheme.typography.caption3
                )
            }

            }

        }
        item {
            DialogChip(
                text = "Cuaca",
                icon = {
//                    Icon(painter = painterResource(id = R.drawable.cuaca), contentDescription ="BMKG", tint = Color.White )
                })
        }

        item {
            DialogChip(
                text = "Gempa Bumi",
                icon = {},
                onClick = {
                    Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
                })
        }

        item {
            DialogChip(
                text = "Kualitas Udara",
                icon = {})
        }

        item {
            DialogChip(
                text = "Radar",
                icon = {})
        }



    }
}

@Composable
fun ListItem(menuItem: MenuItem) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 0.dp)  // Ensure no external padding is reducing the width
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = {
                    Toast
                        .makeText(context, "${menuItem.name} clicked", Toast.LENGTH_SHORT)
                        .show()
//                    context.startActivity(Intent(context, menuItem.activityClass.java))
                }
            ),
        color = Color.Black,  // Set the background to black
        shape = RoundedCornerShape(50.dp)  // Adjust the corner radius to 50
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),  // Adjust internal padding if needed
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = menuItem.icon,
                contentDescription = menuItem.name,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = menuItem.name,
                color = Color.White,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}
