package phu.nguyen.dateme.ui.editProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import phu.nguyen.dateme.databinding.ItemImageEditProfileBinding
import timber.log.Timber


class ImageEditProfileAdapter(private var listImage: MutableList<String>, private val heightPixel: Int) :
    RecyclerView.Adapter<ImageEditProfileAdapter.ImageEditVH>() {

    init {
        Timber.d("init ImageEditProfileAdapter")
    }

    private var isLoading = false

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading = isLoading
        notifyItemChanged(listImage.size)
    }

    fun addImage(image: String) {
        listImage.add(image)
        notifyItemChanged(listImage.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageEditVH {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemImageEditProfileBinding.inflate(layoutInflater, parent, false)
        val lp = binding.root.layoutParams
        lp.height = heightPixel / 4

        binding.root.layoutParams = lp
        return ImageEditVH(binding)
    }

    override fun getItemCount(): Int = 9

    override fun onBindViewHolder(holder: ImageEditVH, position: Int) {
        Timber.d("onBindViewHolder $position")
        if (position == listImage.size) {
            if (isLoading) {
                Timber.d("onBindViewHolder1 $position ")
                holder.bind("", true)
            } else {
                holder.bind("", false)
            }
        } else if (position > listImage.size) {
            holder.bind("", false)
        } else {
            holder.bind(listImage[position], false)
        }

    }

    class ImageEditVH(private val binding: ItemImageEditProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String, isLoading: Boolean) {
            binding.image = image
            binding.isLoading = isLoading
        }
    }
}