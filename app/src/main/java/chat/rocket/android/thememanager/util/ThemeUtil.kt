package chat.rocket.android.thememanager.util

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.AttrRes
import chat.rocket.android.R
import java.lang.Boolean.getBoolean

class ThemeUtil{

    companion object {
        @JvmStatic
        private lateinit var theme: Resources.Theme
        private val typedValue: TypedValue = TypedValue()
        private const val resolveRefs: Boolean = true

        fun setTheme(theme: Resources.Theme) {
            this.theme = theme
        }

        fun getThemeColor(@AttrRes attrColor: Int): Int {
            theme.resolveAttribute(attrColor, typedValue, resolveRefs)
            return typedValue.data
        }

        fun getThemeColorResource(@AttrRes attrColor: Int): Int {
            theme.resolveAttribute(attrColor, typedValue, resolveRefs)
            return typedValue.resourceId
        }

        fun getIsDark(context: Context): Boolean{
            theme.resolveAttribute(R.attr.colorBackgroundIsDark, typedValue, resolveRefs)
            return context.resources.getBoolean(typedValue.resourceId)
        }
    }
}
