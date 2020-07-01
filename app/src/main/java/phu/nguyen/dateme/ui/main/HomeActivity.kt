package phu.nguyen.dateme.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import phu.nguyen.dateme.R
import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.databinding.ActivityHomeBinding
import phu.nguyen.dateme.ui.loadData.LoadDataActivity
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
//    @Inject
//    lateinit var factory: HomeViewModelFactory
//
//    private lateinit var viewModel: HomeViewModel
    lateinit var navController: NavController
    lateinit var user: User
    lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        user = intent.extras?.get(LoadDataActivity.USER_KEY) as User
        Timber.d(user.userBasicInfo.name)
//        setUpViewModel()
        setUpBottomNavigation(user)

    }

//    private fun setUpViewModel() {
//        viewModel =
//            ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        //viewModel.getData("oMTAcviWFZWEIuMv0HIbJyyIKIB2")
//    }

    private fun setUpBottomNavigation(user: User?) {
        navController = findNavController(R.id.nav_host_fragment)

        navController.setGraph(
            R.navigation.mobile_navigation)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_profile,
                R.id.navigation_explore,
                R.id.navigation_dashboard,
                R.id.navigation_likes,
                R.id.navigation_chat
            )
        )

        binding.navView.apply {
            setOnNavigationItemSelectedListener(this@HomeActivity)
            menu.findItem(R.id.navigation_dashboard).isChecked = true
            setupWithNavController(navController)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}