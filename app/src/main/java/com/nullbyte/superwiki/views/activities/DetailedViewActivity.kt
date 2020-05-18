package com.nullbyte.superwiki.views.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.nullbyte.superwiki.R
import com.nullbyte.superwiki.base.BaseActivity
import com.nullbyte.superwiki.databinding.ActivityDetailedViewBinding
import com.nullbyte.superwiki.models.Profile
import com.nullbyte.superwiki.utilities.DisplayUtils
import com.nullbyte.superwiki.viewModels.DetailedViewViewModel
import kotlinx.android.synthetic.main.item_appearance.*
import kotlinx.android.synthetic.main.item_biography.*
import kotlinx.android.synthetic.main.item_connections.*
import kotlinx.android.synthetic.main.item_powerstat.*
import kotlinx.android.synthetic.main.item_work.*


class DetailedViewActivity : BaseActivity() {

    private lateinit var viewModel: DetailedViewViewModel
    private lateinit var detailedViewBinding: ActivityDetailedViewBinding
    private lateinit var mAlertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailedViewBinding = ActivityDetailedViewBinding.inflate(layoutInflater)
        setContentView(detailedViewBinding.root)
        viewModel = ViewModelProvider(this).get(DetailedViewViewModel::class.java)
        mAlertDialog = DisplayUtils.createLoaderDialog(this)
        mAlertDialog.show()

        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        detailedViewBinding.toolbar.title = name
        observeData()
        viewModel.getSuperheroDetailsById(id!!)

        setSupportActionBar(detailedViewBinding.toolbar)
        detailedViewBinding.toolbar.navigationIcon = resources.getDrawable(
            R.drawable.ic_arrow_back_white_24dp,
            null
        )
        detailedViewBinding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        detailedViewBinding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Favourites, coming soon.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun observeData() {
        viewModel.superHeroDetails.observe(this, Observer { details ->
            setImage(details)
            setBiography(details)
            setAppearance(details)
            setWork(details)
            setConnections(details)
            setPowerstat(details)
            mAlertDialog.dismiss()
        })
    }

    private fun setPowerstat(details: Profile?) {
        setText(tv_intelligence, details!!.powerstats.intelligence)
        setText(tv_intelligence, details.powerstats.intelligence)
        setText(tv_strength, details.powerstats.strength)
        setText(tv_durability, details.powerstats.durability)
        setText(tv_speed, details.powerstats.speed)
        setText(tv_power, details.powerstats.power)
        setText(tv_combat, details.powerstats.combat)
    }

    private fun setConnections(details: Profile?) {
        setText(tv_group_affiliation, details!!.connections.groupAffiliation)
        setText(tv_relatives, details.connections.relatives)
    }

    private fun setWork(details: Profile?) {
        setText(tv_occupation, details!!.work.occupation)
        setText(tv_base, details.work.base)
    }

    private fun setAppearance(details: Profile?) {
        setText(tv_gender, details!!.appearance.gender)
        setText(tv_race, details.appearance.race)
        setText(tv_height, details.appearance.height.toString().trimStart('[').trimEnd(']'))
        setText(tv_weight, details.appearance.weight.toString().trimStart('[').trimEnd(']'))
        setText(tv_eye_color, details.appearance.eyeColor)
        setText(tv_hair_color, details.appearance.hairColor)
    }

    private fun setBiography(details: Profile?) {
        if (details!!.biography.fullName.isBlank())
            setText(tv_real_name, details.name)
        else
            setText(tv_real_name, details.biography.fullName)
        setText(tv_publisher, details.biography.publisher)
        setText(tv_first_appearance, details.biography.firstAppearance)
        setText(tv_alter_egos, details.biography.alterEgos)
        setText(tv_place_of_birth, details.biography.placeOfBirth)
        setText(tv_aliases, details.biography.aliases.toString().trimStart('[').trimEnd(']'))
        setText(tv_alignment, details.biography.alignment)
    }

    private fun setImage(details: Profile?) {
        Glide.with(this)
            .load(details!!.image.url)
            .centerCrop()
            .placeholder(R.drawable.ic_image_black_24dp)
            .fallback(R.drawable.ic_image_black_24dp)
            .error(R.drawable.ic_broken_image_black_24dp)
            .into(detailedViewBinding.ivDisplayImage)
    }

    override fun onActivityStart() {
    }

    override fun onActivityResume() {
    }

    override fun onActivityStop() {
    }

    override fun onActivityPause() {
    }

    override fun onActivityDestroy() {
    }

    private fun setText(textView: TextView?, string: String) {
        if (string == "-")
            textView!!.text = getString(R.string.na)
        else
            textView!!.text = string
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
