package phu.nguyen.dateme.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_viewpager_image_border.view.*
import phu.nguyen.dateme.R

class ImageProfileAdapter(
    private val listImage: List<String>,
    private val type: Int,
    private val onItemActionListener: (Int) -> Unit
) :
    RecyclerView.Adapter<ImageProfileAdapter.ImageProfileVH>() {
    constructor( listImage: List<String>,
                onItemActionListener: (Int) -> Unit) : this(
        listImage, BORDER, onItemActionListener
    ) {

    }
    companion object {
        const val NON_BORDER = 0
        const val BORDER = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageProfileVH {
        val view: View
        if (type == BORDER) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_viewpager_image_border, parent, false)
        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_viewpager_image_non_border, parent, false)
        }

        return ImageProfileVH(view)
    }

    override fun getItemCount(): Int = listImage.size

    override fun onBindViewHolder(holder: ImageProfileVH, position: Int) {
//        Log.d("testBind", "bind $position")
        holder.bind(listImage[position], onItemActionListener, position, listImage.size)
    }


    class ImageProfileVH(item: View) : RecyclerView.ViewHolder(item) {
        fun bind(image: String, onItemActionListener: (Int) -> Unit, position: Int, size: Int) {

            itemView.view_left.setOnClickListener {
                var previousPosition = 0
                if (position > 1) {
                    previousPosition = position - 1
                }
                if (position == 0) {
                    previousPosition = size + 1
                }
                onItemActionListener(previousPosition)
            }
            itemView.view_right.setOnClickListener {
                var nextPosition = size - 1
                if (position < size - 2) {
                    nextPosition = position + 1
                }
                if (position == size - 1) {
                    nextPosition = 0
                }
                onItemActionListener(nextPosition)
            }

            Glide
                .with(itemView)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(itemView.img_profile_dashboard)
        }
    }

}