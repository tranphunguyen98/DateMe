package phu.nguyen.dateme.ui.editProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import phu.nguyen.dateme.databinding.ItemImageEditProfileBinding


class ImageEditProfileAdapter(private val listImage: List<String>, private val heightPixel: Int) : RecyclerView.Adapter<ImageEditProfileAdapter.ImageEditVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageEditVH {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemImageEditProfileBinding.inflate(layoutInflater,parent, false)
        val lp = binding.root.layoutParams
        lp.height = heightPixel / 4

        binding.root.layoutParams = lp
        return ImageEditVH(binding)
    }

    override fun getItemCount(): Int = 9

    override fun onBindViewHolder(holder: ImageEditVH, position: Int) {
        if(position >= listImage.size) {
            holder.bind("")
        } else {
            holder.bind(listImage[position])
        }

    }

    class ImageEditVH(private val binding: ItemImageEditProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String) {
            binding.image = image
        }
    }
}