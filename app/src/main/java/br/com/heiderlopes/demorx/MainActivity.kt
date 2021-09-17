package br.com.heiderlopes.demorx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.heiderlopes.demorx.api.PokemonService
import br.com.heiderlopes.demorx.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var compositeDisposable = CompositeDisposable()

    lateinit var adapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val api = PokemonService()

        adapter = MainListAdapter(Picasso.get())
        binding.rvPokemons.adapter = adapter
        //binding.rvPokemons.layoutManager = LinearLayoutManager(this)
        binding.rvPokemons.layoutManager = GridLayoutManager(this, 3)

        val pokemonObservable = api.loadPokemons()

        compositeDisposable.add(
            pokemonObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { adapter.addItem(it) },
                    { e -> e.printStackTrace() },
                    { adapter.notifyDataSetChanged() }
                )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}