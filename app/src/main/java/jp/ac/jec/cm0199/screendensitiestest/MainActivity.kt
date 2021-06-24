package jp.ac.jec.cm0199.screendensitiestest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.jec.cm0199.screendensitiestest.databinding.ActivityMainBinding
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            // 画面のピクセル密度のスケールを取得する
            val scale: Float = resources.displayMetrics.density
            textDensity.text = getDisplayScaleText(scale)
            imageView.setImageResource(R.drawable.ic_launcher)
        }
    }

    private fun getDisplayScaleText(scale: Float): String {
        return when {
            scale <= Density.LDPI.scale -> {
                getString(R.string.ldpi_message, scale)
            }
            scale <= Density.MDPI.scale -> {
                getString(R.string.mdpi_message)
            }
            scale <= Density.HDPI.scale -> {
                getString(R.string.hdpi_message)
            }
            scale <= Density.XHDPI.scale -> {
                getString(R.string.xhdpi_message)
            }
            scale <= Density.XXHDPI.scale -> {
                getString(R.string.xxhdpi_message)
            }
            scale <= Density.XXXHDPI.scale -> {
                getString(R.string.xxxhdpi_message)
            }
            else -> {
                throw IllegalArgumentException("unknown...")
            }
        }
    }

    // 解像度の種類.
    enum class Density(val scale: Float) {
        LDPI(0.75F),
        MDPI(1.0F),
        HDPI(1.5F),
        XHDPI(2.0F),
        XXHDPI(3.0F),
        XXXHDPI(4.0F),
    }
}