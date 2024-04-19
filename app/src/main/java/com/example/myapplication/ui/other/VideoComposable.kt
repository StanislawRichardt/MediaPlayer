package com.example.myapplication.ui.other

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.model.UIModel
import com.example.myapplication.model.VideoModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.net.URLEncoder

@Composable
fun VideoComposable(video: UIModel, navController: NavController){

    val thumbnail = video.image?.asImageBitmap()

    val imageModifier = if(video.videoUrl != null){
        Modifier
            .clickable(
                enabled = true,
                onClick = { navController.navigate("playback/${URLEncoder.encode(video.videoUrl, "UTF-8")}")})
    }else{
        Modifier
    }

    MyApplicationTheme {
        Row(Modifier.padding(10.dp))  {
            if(thumbnail != null){
                Image(
                    bitmap = thumbnail,
                    contentDescription = "Video picture",
                    modifier = imageModifier.size(120.dp).padding(0.dp,0.dp,10.dp,0.dp),
                    alignment = Alignment.TopStart
                )
            }else{
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Video picture",
                    modifier = imageModifier
                )
            }

            Column {
                if(video.title != null){
                    Text(
                        text = video.title.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.body1
                    )
                }
                if(video.subtitle != null) {
                    Text(
                        text = video.subtitle.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.body2
                    )
                }
                if(video.date != null) {
                    Text(
                        text = video.date.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}
