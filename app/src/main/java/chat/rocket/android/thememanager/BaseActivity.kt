package chat.rocket.android.thememanager

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import chat.rocket.android.R
import chat.rocket.android.thememanager.viewmodel.ThemesViewModel
import chat.rocket.android.thememanager.viewmodel.ThemesViewModelFactory
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ThemesViewModelFactory
    private lateinit var viewModel: ThemesViewModel
    private var currentTheme: String = "AppTheme"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(ThemesViewModel::class.java)
        currentTheme = viewModel.getCurrentTheme()!!
        applyTheme(currentTheme)
    }

    override fun onResume() {
        super.onResume()
        val selectedTheme = viewModel.getCurrentTheme()!!
        if(currentTheme != selectedTheme)
            recreate()
    }

    private fun applyTheme(currentTheme: String) {
        when(currentTheme){
            "AppTheme" -> setTheme(R.style.AppTheme)
            "DarkTheme" -> setTheme(R.style.DarkTheme)
            "BlackTheme" -> setTheme(R.style.BlackTheme)
        }
    }

    private fun getThemeAttribute( @AttrRes attrColor: Int, typedValue: TypedValue = TypedValue(), resolveRefs: Boolean = true) : Int{
        theme.resolveAttribute(attrColor, typedValue, resolveRefs)
        return typedValue.data
    }
}