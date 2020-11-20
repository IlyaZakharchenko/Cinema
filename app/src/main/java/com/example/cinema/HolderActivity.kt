package com.example.cinema

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class HolderActivity : AppCompatActivity() {

    private val router: Router by inject(named(CINEMA_QUALIFIER))
    private val navigatorHolder: NavigatorHolder by inject(named(CINEMA_QUALIFIER))
    private val navigator = SupportAppNavigator(this, R.id.fragmentContainer)

    companion object {
        private const val KEY_IS_RECREATED = "KEY_IS_RECREATED"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            router.newRootScreen(MovieListScreen())
        }
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onBackPressed() {
        router.exit()
    }
}