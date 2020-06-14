package phu.nguyen.dateme.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import phu.nguyen.dateme.databinding.ItemViewpagerImageBorderBinding
import phu.nguyen.dateme.databinding.ItemViewpagerImageNonBorderBinding

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
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding :  ViewDataBinding
        val view: View

        binding = if (type == BORDER) {
            ItemViewpagerImageBorderBinding.inflate(layoutInflater,parent,false)
        } else {
            ItemViewpagerImageNonBorderBinding.inflate(layoutInflater,parent,false)
        }

        return ImageProfileVH(binding)
    }

    override fun getItemCount(): Int = listImage.size

    override fun onBindViewHolder(holder: ImageProfileVH, position: Int) {
//        Log.d("testBind", "bind $position")
        holder.bind(listImage[position], onItemActionListener, position, listImage.size)
    }


    class ImageProfileVH(private val binding : ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String, onItemActionListener: (Int) -> Unit, position: Int, size: Int) {

            if(binding is ItemViewpagerImageBorderBinding) {
                binding.image = image
                binding.viewLeft.setOnClickListener {
                    onLeftClicked(onItemActionListener,position,size)
                }
                binding.viewRight.setOnClickListener {
                    onRightClicked(onItemActionListener,position,size)
                }
            } else if (binding is ItemViewpagerImageNonBorderBinding){
                binding.image = image
                binding.viewLeft.setOnClickListener {
                    onLeftClicked(onItemActionListener,position,size)
                }
                binding.viewRight.setOnClickListener {
                    onRightClicked(onItemActionListener,position,size)
                }
            }
        }

        private fun onLeftClicked( onItemActionListener: (Int) -> Unit, position: Int, size: Int) {
            var previousPosition = 0
            if (position > 1) {
                previousPosition = position - 1
            }
            if (position == 0) {
                previousPosition = size + 1
            }
            onItemActionListener(previousPosition)
        }

        private fun onRightClicked( onItemActionListener: (Int) -> Unit, position: Int, size: Int) {
            var nextPosition = size - 1
            if (position < size - 2) {
                nextPosition = position + 1
            }
            if (position == size - 1) {
                nextPosition = 0
            }
            onItemActionListener(nextPosition)
        }
    }

}