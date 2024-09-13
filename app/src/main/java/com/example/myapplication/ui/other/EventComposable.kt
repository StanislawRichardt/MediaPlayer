package com.example.myapplication.ui.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.model.UIModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.net.URLEncoder

@Composable
fun EventComposable(event: UIModel, navController: NavController){

    val imageModifier = if(event.videoUrl != null){
        Modifier
            .clickable(
                enabled = true,
                onClick = { navController.navigate("playback/${URLEncoder.encode(event.videoUrl, "UTF-8")}")})
    }else{
        Modifier
    }

    MyApplicationTheme {
        Row(Modifier.padding(10.dp))  {
            if(event.image != null){
                AsyncImage(
                    model = event.image,
                    contentDescription = "Video picture",
                    modifier = imageModifier.size(120.dp).padding(0.dp,0.dp,10.dp,0.dp),
                    alignment = Alignment.TopStart
                )
            }else{
                AsyncImage(
                    model = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Video picture",
                    modifier = imageModifier
                )
            }

            Column {
                if(event.title != null){
                    Text(
                        text = event.title.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.body1
                    )
                }
                if(event.subtitle != null) {
                    Text(
                        text = event.subtitle.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.body2
                    )
                }
                if(event.date != null) {
                    Text(
                        text = event.date.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}
