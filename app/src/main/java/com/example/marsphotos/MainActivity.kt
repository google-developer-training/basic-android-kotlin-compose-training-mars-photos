/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.marsphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.marsphotos.network.MarsPhoto
import com.example.marsphotos.overview.MarsApiStatus
import com.example.marsphotos.overview.OverviewViewModel
import com.example.marsphotos.ui.theme.MarsphotosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarsphotosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MarsPhotosApp()
                }
            }
        }
    }
}

@Composable
fun MarsPhotosApp(overviewModel: OverviewViewModel = viewModel()) {

    if ((overviewModel.status.value == MarsApiStatus.LOADING)) {
        LoadingScreen()
    } else if ((overviewModel.status.value == MarsApiStatus.ERROR)) {
        ErrorScreen { overviewModel.getMarsPhotos() }
    } else {
        PhotosGridScreen()
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Loading...")
    }
}

@Composable
fun ErrorScreen(retry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Failed to load")
        Button(onClick = { retry() }) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun PhotosGridScreen(overviewModel: OverviewViewModel = viewModel()) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(12.dp),
        content = {

            items(
                count = overviewModel.photos.size,
                key = { index -> overviewModel.photos[index].id },
                itemContent = { index ->
                    MarsPhotoCard(photo = overviewModel.photos[index])
                })
        }
    )
}

@Composable
fun MarsPhotoCard(photo: MarsPhoto) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = 8.dp,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.imgSrcUrl)
                .crossfade(true)
                .build(),
            fallback= painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarsphotosTheme {
        LoadingScreen()
    }
}
