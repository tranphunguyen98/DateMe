package phu.nguyen.dateme.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import phu.nguyen.dateme.R
import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.ui.dashboard.ImageProfileAdapter.Companion.NON_BORDER

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImageSource(view: ImageView, url: String?) {
            url?.let {
                Picasso.get()
                    .load(it)
                    .noFade()
                    .placeholder(R.drawable.placeholder)
                    .into(view, object : Callback {
                        override fun onSuccess() {
                            view.alpha = 0f
                            view.animate().setDuration(500).alpha(1f).start()
                        }

                        override fun onError(e: Exception?) {
                        }

                    })
            }
        }

        @JvmStatic
        @BindingAdapter("loadImageWithoutAnimation")
        fun loadImageSourceWithoutAnimation(view: ImageView, url: String?) {
            url?.let {
                Picasso.get()
                    .load(it)
                    .noFade()
                    .placeholder(R.drawable.placeholder)
                    .into(view,object : Callback {
                        override fun onSuccess() {
                            view.alpha = 0f
                            view.animate().setDuration(200).alpha(1f).start()
                        }

                        override fun onError(e: Exception?) {
                        }

                    })
            }
        }

//        @JvmStatic
//        @BindingAdapter("loadImagesExplore")
//        fun setupRecyclerViewImageExplore(recyclerView: RecyclerView, profile: Profile?) {
//            profile?.let {
//                recyclerView.adapter = ImageExploreAdapter(profile.images)
//            }
//        }

        @JvmStatic
        @BindingAdapter(value = ["loadImagesProfile", "withTabLayout", "withNonBorder"], requireAll = false)
        fun setViewPagerProperties(viewPager2: ViewPager2, profile: Profile?, tabLayout: TabLayout?, nonBorder: Boolean?) {
            profile?.let {
                with(viewPager2) {
                    offscreenPageLimit = 2
                    adapter = if(nonBorder != null && nonBorder == true) {
                        phu.nguyen.dateme.ui.dashboard.ImageProfileAdapter(profile.images,NON_BORDER) { it ->
                            setCurrentItem(it, true)
                        }
                    } else {
                        phu.nguyen.dateme.ui.dashboard.ImageProfileAdapter(profile.images) { it ->
                            setCurrentItem(it, true)
                        }
                    }
                    transitionName = "profile${profile.id}"
                    if(tabLayout != null) {
                        TabLayoutMediator(tabLayout, this,
                            TabLayoutMediator.TabConfigurationStrategy { _, _ ->
                            }).attach()
                    }
                }
            }
        }

//
//        @JvmStatic
//        @BindingAdapter("loadMovie")
//        fun setRecyclerViewData(recyclerView: RecyclerView, movies : List<Movie>?) {
//            movies?.let {movies ->
//                when(recyclerView.adapter) {
//                    is PopularMoviesAdapter -> {
//                        (recyclerView.adapter as PopularMoviesAdapter).addData(movies)
//                    }
//                    is FavoriteMoviesAdapter -> {
//                        (recyclerView.adapter as FavoriteMoviesAdapter).addData(movies)
//                    }
//                    is SearchMoviesAdapter -> {
//                        (recyclerView.adapter as SearchMoviesAdapter).addData(movies)
//                    }
//                }
//            }
//        }
    }
}