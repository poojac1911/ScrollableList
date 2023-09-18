@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.scrollablelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scrollablelist.Data.DataSource
import com.example.scrollablelist.Model.Affirmation
import com.example.scrollablelist.ui.theme.ScrollableListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationApp()
                }
            }
        }
    }
}

@Composable
fun AffirmationApp() {
    AffirmationList(
        affirmationList = DataSource().loadAffirmations()
    )
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier) {
        items(affirmationList) {affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(16.dp)) {
        Column() {
            Image(
                painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = affirmation.stringResourceId),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun AffirmationCardPreview() {
    ScrollableListTheme {
        AffirmationCard(Affirmation(R.string.affirmation1,R.drawable.image1))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScrollableListTheme {
        AffirmationApp()
    }
}