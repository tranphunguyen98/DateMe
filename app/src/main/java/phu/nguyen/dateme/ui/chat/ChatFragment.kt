package phu.nguyen.dateme.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import phu.nguyen.dateme.common.Result
import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.databinding.FragmentChatBinding
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var viewModel: ChatViewModel
    private lateinit var binding: FragmentChatBinding

    @Inject
    lateinit var factory: ChatViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this,factory).get(ChatViewModel::class.java)

        binding = FragmentChatBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setUpObserver()

        return binding.root
    }


    private fun setUpUI(chats: List<Chat>) {
        binding.rcNewMatch.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = NewMatchAdapter(chats)
        }

        binding.rcMessage.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MessageAdapter(chats)
            isNestedScrollingEnabled = false
        }
    }

    private fun setUpObserver() {
        viewModel.resultChat.observe(viewLifecycleOwner,Observer {result ->
            when(result) {
                is Result.Waiting -> {
                    Timber.d("Waiting")
                    binding.prgChat.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    Timber.d("Success - ${result.data.size}")
                    setUpUI(result.data)
                    binding.prgChat.visibility = View.GONE
                }

                is Result.Error -> {
                    binding.prgChat.visibility = View.GONE
                    Timber.d("Error = ${result.exception.message}")
                }
            }
        })
    }
}