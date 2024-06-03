package com.example.azariasartspace

import android.os.Bundle
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.azariasartspace.ui.theme.AzariasArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AzariasArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color =  colorResource(id = R.color.Lighter_Screenbackground),

                    ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
private fun ArtSpace(modifier: Modifier = Modifier) {
    var filmIndex by remember { mutableStateOf(0)}
    if(filmIndex == 4) filmIndex = 0
    if(filmIndex == -1) filmIndex =3

    val film: Int
    val filmName: Int
    val filmDirector: Int
    val filmYear: Int

    when (filmIndex) {
        0 -> {
            film = R.drawable.metropolis2
            filmName = R.string.Metropolis
            filmDirector = R.string.Metropolis_Director
            filmYear = R.string.Metropolis_Year
        }

        1 -> {
            film = R.drawable.the_thing
            filmName = R.string.The_Thing
            filmDirector = R.string.The_Thing_Director
            filmYear = R.string.The_Thing_Year
        }

        2 -> {
            film = R.drawable.throne_of_blood
            filmName = R.string.Throne_of_Blood
            filmDirector = R.string.Throne_of_Blood_Director
            filmYear = R.string.Throne_of_Blood_Year
        }

        else -> {
            film = R.drawable.back_to_the_future
            filmName = R.string.Back_To_The_Future
            filmDirector = R.string.Back_To_The_Future_Director
            filmYear = R.string.Back_To_The_Future_Year
        }
    }
        Column(
            modifier = Modifier
                .padding(30.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.black
                )),
                modifier = Modifier.padding(5.dp),
                shape = RectangleShape,
            ) {

                Image(
                    painter = painterResource(id = film),
                    contentDescription = stringResource(id = filmName),
                    modifier = Modifier.padding(2.dp),
                )
            }

            BoxWithConstraints {
                if (maxWidth < 413.dp) {
                    Spacer(Modifier.height(100.dp))
                } else if (maxWidth < 1281.dp) {
                    Spacer(Modifier.height(20.dp))
                }
            }

            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            colorResource(id = R.color.black),
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp

                        )
                    )
                    {
                        append(stringResource(id = filmName))
                        appendLine()
                    }

                    withStyle(
                        style = SpanStyle(
                            colorResource(id = R.color.black),
                            fontWeight = FontWeight.Light,
                            fontSize = 30.sp
                        )
                    ) {
                        append(stringResource(id = filmDirector))
                        appendLine()

                    }

                    withStyle(
                        style = SpanStyle(
                            colorResource(id = R.color.black),
                            fontWeight = FontWeight.Light,
                            fontSize = 25.sp

                        )
                    ) {
                        append(stringResource(id = filmYear))
                    }
                },

                modifier = Modifier
                    .background(colorResource(id = R.color.Lighter_Screenbackground))
                    .padding(6.dp),
            )

            Spacer(Modifier.height(48.dp))

            Row(
                Modifier.fillMaxWidth() .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                EditButtons(
                    onClick = { filmIndex-- },
                    buttonColor = R.color.ButtonColor,
                    buttonText = R.string.previous_button,
                )

                EditButtons(
                    onClick = { filmIndex++ },
                    buttonColor = R.color.ButtonColor,
                    buttonText = R.string.next_button,
                )
            }
        }

    }

@Composable
fun EditButtons(onClick: () -> Unit, @StringRes buttonText: Int, @ColorRes buttonColor: Int, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(colorResource(id = buttonColor)),
        modifier = modifier.width(140.dp),
    ) {
        Text(text = stringResource(id = buttonText))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AzariasArtSpaceTheme {

        ArtSpace()

    }
}