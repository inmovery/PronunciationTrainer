package ru.contesta.pronunciationtrainer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.compose.navArgument
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_card_content.view.*
import ru.contesta.pronunciationtrainer.cardslider.CardSliderAdapter
import ru.contesta.pronunciationtrainer.databinding.ActivityMainBinding
import ru.contesta.pronunciationtrainer.databinding.ActivityTestCardSliderBinding

class MovieAdapter(private val movies: List<Movie>) : CardSliderAdapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var binding: ActivityTestCardSliderBinding

    override fun bindVH(holder: MovieViewHolder, position: Int) {

        val movie = movies[position]

        holder.itemView.run {
            movie_poster.setImageResource(movie.poster)
            movie_title.text = movie.title
            movie_overview.text = movie.overview
        }
    }

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_card_content, container, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size


    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)
}