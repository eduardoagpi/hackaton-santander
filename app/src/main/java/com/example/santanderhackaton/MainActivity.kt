package com.example.santanderhackaton

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.santanderhackaton.data.ApiService
import com.example.santanderhackaton.data.ServiceEnum
import com.example.santanderhackaton.data.ServiceRepository
import com.example.santanderhackaton.data.ServiceRepositoryImpl
import com.example.santanderhackaton.ui.theme.SantanderHackatonTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Date
import java.util.concurrent.Flow

class MainActivity : ComponentActivity() {

    lateinit var repository: ServiceRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = ServiceRepositoryImpl()
        setContent {
            SantanderHackatonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            repository.getServiceStatusFlow(ServiceEnum.Characters).collect{
                Log.v("Hackaton","Received response event " + it.response)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        repository.stopListening()
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SantanderHackatonTheme {
        Greeting("Android")
    }
}
