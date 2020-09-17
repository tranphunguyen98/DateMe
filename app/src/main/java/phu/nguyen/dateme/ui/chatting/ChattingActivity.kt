package phu.nguyen.dateme.ui.chatting

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import phu.nguyen.dateme.R
import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.databinding.ActivityChattingBinding
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ChattingActivity : AppCompatActivity() {

    companion object {
        const val CHAT_KEY_INTENT = "chat"
    }

    @Inject
    lateinit var factory: ChattingViewModelFactory

    private lateinit var binding:  ActivityChattingBinding
    private lateinit var viewModel: ChattingViewModel

    private lateinit var chat: Chat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)

       chat = intent.getParcelableExtra<Chat>(CHAT_KEY_INTENT)

        viewModel = ViewModelProvider(this,factory).get(ChattingViewModel::class.java)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_chatting)
        binding.lifecycleOwner = this

        viewModel.setChat(chat)
        binding.viewModel = viewModel

        setSupportActionBar(binding.toolbarChatting)

        setUpUI()
    }

    private fun setUpUI(){
        binding.btnSend.setOnClickListener {
            viewModel.saveMessage()
            Timber.d("SaveMessage1!1")
        }
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