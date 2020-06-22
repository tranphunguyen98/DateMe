package phu.nguyen.dateme.ui.explore

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.databinding.ItemImageExploreBinding

class ImageExploreAdapter(
    private val listProfile: List<Profile>,
    private val onItemActionListener: (Int) -> Unit
) :
    RecyclerView.Adapter<ImageExploreAdapter.ImageExploreVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageExploreVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding :  ViewDataBinding
          binding =  ItemImageExploreBinding.inflate(layoutInflater,parent,false)

        return (ImageExploreVH(binding))
    }

    override fun getItemCount(): Int = listProfile.size

    override fun onBindViewHolder(holder: ImageExploreVH, position: Int) {
        holder.bind(listProfile[position], onItemActionListener, position, listProfile.size)
    }


    class ImageExploreVH(private val binding : ItemImageExploreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile, onItemActionListener: (Int) -> Unit, position: Int, size: Int) {
            Log.d("textObserver", profile.name)
            binding.profile = profile
        }
    }
}