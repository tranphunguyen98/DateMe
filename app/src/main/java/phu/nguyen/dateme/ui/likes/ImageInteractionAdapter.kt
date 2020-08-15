package phu.nguyen.dateme.ui.likes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.databinding.ItemImageInteractionBinding
import timber.log.Timber

class ImageInteractionAdapter(
    private val profiles: List<Profile>,
    private val onItemActionListener: (Int) -> Unit
) :
    RecyclerView.Adapter<ImageInteractionAdapter.ImageInteractionVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageInteractionVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding :  ViewDataBinding
          binding =  ItemImageInteractionBinding.inflate(layoutInflater,parent,false)

        return (ImageInteractionVH(binding))
    }

    override fun getItemCount(): Int = profiles.size

    override fun onBindViewHolder(holder: ImageInteractionVH, position: Int) {
        holder.bind(profiles[position], onItemActionListener, position, profiles.size)
    }

    class ImageInteractionVH(private val binding : ItemImageInteractionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile, onItemActionListener: (Int) -> Unit, position: Int, size: Int) {
            Timber.d(profile.name)
            binding.profile = profile
            binding.cvImageEdit.setOnClickListener {
                onItemActionListener(position)
            }
        }
    }
}