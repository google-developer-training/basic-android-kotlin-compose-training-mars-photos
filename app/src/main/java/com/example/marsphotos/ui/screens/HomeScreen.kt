/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.marsphotos.R
import com.example.marsphotos.network.MarsPhoto

@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is MarsUiState.Success -> ResultScreen(
            photos = marsUiState.photos,
            modifier = Modifier.fillMaxWidth()
        )
        is MarsUiState.Error -> ErrorScreen( modifier = Modifier.fillMaxSize())
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(photos: List<MarsPhoto>, modifier: Modifier = Modifier) {
    LazyColumn (modifier=Modifier.padding(16.dp)
        ,verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(photos){
        MarsPhotoItem(it)
    }
    }
}

@Composable
fun MarsPhotoItem(photo:MarsPhoto) {
    Box(modifier = Modifier.fillMaxWidth().border(1.dp,color= Color.White)) {
        Text(text = photo.id)
        Image(
            painter = rememberAsyncImagePainter(photo.imgSrc),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
        )

    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoadingScreenPreview() {
//    MarsPhotosTheme {
//        LoadingScreen()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ErrorScreenPreview() {
//    MarsPhotosTheme {
//        ErrorScreen()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PhotosGridScreenPreview() {
//    MarsPhotosTheme {
//        ResultScreen(stringResource(R.string.placeholder_success))
//    }
//}