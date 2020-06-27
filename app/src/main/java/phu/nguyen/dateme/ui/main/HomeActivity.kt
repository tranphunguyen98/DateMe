package phu.nguyen.dateme.ui.main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import phu.nguyen.dateme.R
import phu.nguyen.dateme.ui.login.data.Result
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    @Inject
    lateinit var factory: HomeViewModelFactory

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//
//        with(window) {
//            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//
//            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//
//            statusBarColor = resources.getColor(R.color.white)
//        }

        viewModel =
            ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        viewModel.getData("oMTAcviWFZWEIuMv0HIbJyyIKIB2")

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        setUpObserver()

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_profile,
                R.id.navigation_explore,
                R.id.navigation_dashboard,
                R.id.navigation_likes,
                R.id.navigation_chat
            )
        )

        navView.setOnNavigationItemSelectedListener(this)
        navView.menu.findItem(R.id.navigation_dashboard).isChecked = true

        navView.setupWithNavController(navController)
    }

    private fun setUpObserver() {
        viewModel.result.observe(this, Observer {result ->
            when (result) {
                is Result.Success -> {
                    Log.d("testLogin", "Success ${result.data.userBasicInfo.name}")
                }
                is Result.Waiting -> {
                    Log.d("testLogin", "Waiting...")
                }
                is Result.Error -> {
                    Log.d("testLogin", "Error ${result.exception.message}")
                }
            }
        })
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}