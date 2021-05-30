package jp.ac.jec.cm0199.screendensitiestest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.jec.cm0199.screendensitiestest.databinding.ActivityMainBinding
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        // 画面のピクセル密度のスケールを取得する
        val scale: Float = resources.displayMetrics.density
        binding.textDensity.text = getDisplayScaleText(scale)

        binding.imageView.setImageResource(R.drawable.ic_launcher)
    }

    private fun getDisplayScaleText(scale: Float): String {
        return when {
            scale <= Density.LDPI.scale -> {
                "$scale : " + Density.LDPI.message
            }
            scale <= Density.MDPI.scale -> {
                "$scale : " + Density.MDPI.message
            }
            scale <= Density.HDPI.scale -> {
                "$scale : " + Density.HDPI.message
            }
            scale <= Density.XHDPI.scale -> {
                "$scale : " + Density.XHDPI.message
            }
            scale <= Density.XXHDPI.scale -> {
                "$scale : " + Density.XXHDPI.message
            }
            scale <= Density.XXXHDPI.scale -> {
                "$scale : " + Density.XXXHDPI.message
            }
            else -> {
                throw IllegalArgumentException("unknown...")
            }
        }
    }

    // 解像度の種類.
    enum class Density(val scale: Float, val message: String) {
        LDPI(0.75F, "低密度(ldpi) 0.75倍"),
        MDPI(1.0F, "中密度(mdpi) 1.0倍、基準"),
        HDPI(1.5F, "高密度(hdpi) 1.5倍"),
        XHDPI(2.0F, "超高密度(xhdpi) 2.0倍"),
        XXHDPI(3.0F, "超超高密度(xxhdpi) 3.0倍"),
        XXXHDPI(4.0F, "超超超高密度(xxxhdpi) 4.0倍"),
    }
}