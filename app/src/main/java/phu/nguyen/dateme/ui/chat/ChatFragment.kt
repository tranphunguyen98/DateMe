package phu.nguyen.dateme.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import phu.nguyen.dateme.databinding.FragmentChatBinding
import phu.nguyen.dateme.ui.profile.ChatViewModel

class ChatFragment : Fragment() {

    private lateinit var homeViewModel: ChatViewModel
    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(ChatViewModel::class.java)
        binding = FragmentChatBinding.inflate(inflater,container,false)

        setUpUI()

        return binding.root
    }

    private fun setUpUI() {
        binding.rcNewMatch.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = NewMatchAdapter()

        }

        binding.rcMessage.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MessageAdapter()
            isNestedScrollingEnabled = false
        }
    }
}