package phu.nguyen.dateme.ui.chatting

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import phu.nguyen.dateme.R
import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.databinding.ActivityChattingBinding
import timber.log.Timber

class ChattingActivity : AppCompatActivity() {

    companion object {
        const val CHAT_KEY_INTENT = "chat"
    }

    private lateinit var binding:  ActivityChattingBinding
    private var chat: Chat? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)

        chat = intent.getParcelableExtra<Chat>(CHAT_KEY_INTENT)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_chatting)
        binding.lifecycleOwner = this
        binding.chat = chat
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