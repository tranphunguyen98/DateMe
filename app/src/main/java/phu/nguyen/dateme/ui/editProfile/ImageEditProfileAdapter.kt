package phu.nguyen.dateme.ui.editProfile

import android.app.Activity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import phu.nguyen.dateme.databinding.ItemImageEditProfileBinding


class ImageEditProfileAdapter(private val listImage: List<String>) : RecyclerView.Adapter<ImageEditProfileAdapter.ImageEditVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageEditVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemImageEditProfileBinding.inflate(layoutInflater,parent, false)
        val lp = binding.root.layoutParams

        val displayMetrics = DisplayMetrics()
        (parent.context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)

        lp.height = (displayMetrics.heightPixels) / 4

        binding.root.layoutParams = lp
        return ImageEditVH(binding)
    }

    override fun getItemCount(): Int = listImage.size

    override fun onBindViewHolder(holder: ImageEditVH, position: Int) {
        holder.bind(listImage[position])
    }

    class ImageEditVH(private val binding: ItemImageEditProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String) {
            binding.image = image
        }
    }
}