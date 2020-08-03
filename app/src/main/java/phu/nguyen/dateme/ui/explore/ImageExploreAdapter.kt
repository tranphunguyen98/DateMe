package phu.nguyen.dateme.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import phu.nguyen.dateme.data.model.SwipeProfile
import phu.nguyen.dateme.databinding.ItemImageExploreBinding
import timber.log.Timber

class ImageExploreAdapter(
    private val listSwipeProfile: List<SwipeProfile>,
    private val onItemActionListener: (Int) -> Unit
) :
    RecyclerView.Adapter<ImageExploreAdapter.ImageExploreVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageExploreVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding :  ViewDataBinding
          binding =  ItemImageExploreBinding.inflate(layoutInflater,parent,false)

        return (ImageExploreVH(binding))
    }

    override fun getItemCount(): Int = listSwipeProfile.size

    override fun onBindViewHolder(holder: ImageExploreVH, position: Int) {
        holder.bind(listSwipeProfile[position], onItemActionListener, position, listSwipeProfile.size)
    }


    class ImageExploreVH(private val binding : ItemImageExploreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(swipeProfile: SwipeProfile, onItemActionListener: (Int) -> Unit, position: Int, size: Int) {
            Timber.d(swipeProfile.name)
            binding.profile = swipeProfile
            binding.cvImageEdit.setOnClickListener {
                onItemActionListener(position)
            }
        }
    }
}