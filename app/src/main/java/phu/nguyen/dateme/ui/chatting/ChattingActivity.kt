package phu.nguyen.dateme.ui.chatting

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import phu.nguyen.dateme.R
import phu.nguyen.dateme.databinding.ActivityChattingBinding
import timber.log.Timber

class ChattingActivity : AppCompatActivity() {

    private lateinit var binding:  ActivityChattingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chatting)
        setSupportActionBar(binding.toolbarChatting)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chatting_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Timber.d("menu: $item")
        return super.onOptionsItemSelected(item)
    }
}