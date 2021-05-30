# はじめに
Android Studioのテンプレートプロジェクトを作成すると、仮に`minSDKVersion`が高いものに設定したとしても、<br>
必ずLegacy Iconもデフォルトで追加される。Legacy Icon(pngファイル)を格納しているフォルダはresフォルダ配下の以下のディレクトリ<br>
- `mipmap-hdpi` , `mipmap-mdpi`、`mipmap-hdpi`, `mipmap-xdpi`, `mipmap-xxdpi`, `mipmap-xxxdpi`<br>
テンプレートからの作成だと、解像度別に作成された不要なアイコンが多すぎるので<br>
不必要なアイコンを探す目的で本アプリを作成した。<br>

# 結論
- `minSDKVersion`がAPI Level 26以上であれば、どの解像度の端末でも ランチャーアイコンは、`res/mipmap-anydpi-v26/` 配下の`Adaptive Icon`を使う
- OS ver 8.0未満であれば、`Legacy Icon`を使う
- `Adaptive Icon`のアニメーションは各端末のホームアプリに依存する。なので、OSver 8.0以上であれば必ずアニメーションが適用されるとは限らない
    - Pixcelシリーズに入ってるホームアプリの`Pixel Launcher`のVersion `8.1.0-4429924`以上でアニメーションが聞くことを確認済み
    - Ver OMR1-4113793 では、アニメーションされなかった
- [アプリのランチャーアイコンは必ずmipmap配下に格納する](https://developer.android.com/training/multiscreen/screendensities?hl=ja#mipmap)
    - **ランチャーアイコンは、ホームアプリによっては、デバイスの解像度より25%大きく表示される**。
    - drawable配下の画像は使う解像度以外のファイルはAPKに含まない
    - mipmap配下の画像は全ての解像度のファイルををAPKに含む
    - 画像は拡大するよりも、縮小する方が画質劣化が少ないので、mipmap配下にランチャーアイコンを配置すべき

# Capture

## 480 x 800 hdpi  Nexus S API 28

| drawable | mipmap | home capture | app capture
|:---|:---:|:---:|:---:|
| `drawable-hdpi` | `mipmap-anydpi-v26` |<img src= "" width=320 /> |<img src= "" width=320 /> |

## 1080 x 1920 xxhdpi  Nexus 5 API 24

| drawable | mipmap | home capture | app capture
|:---|:---:|:---:|:---:|
| `drawable-xxhdpi` | `mipmap-xxxhdpi` |<img src= "" width=320 /> |<img src= "" width=320 /> |

# Capture (Adaptive IconのAnimationが効いてる場合)

<img src= "" width=320 />

# Pixcelシリーズに入ってるホームアプリの`Pixel Launcher`のVersionの確認方法

ホーム長押し > 「ホームの設定」をクリック > 「概要」をクリック > 「バージョン」を確認する

# Links
[各種のピクセル密度をサポートする](https://developer.android.com/training/multiscreen/screendensities?hl=ja#kotlin)
[解像度の早見表](https://developer.android.com/training/multiscreen/screendensities?hl=ja#TaskProvideAltBmp)
[Adaptive Icon](https://developer.android.com/guide/practices/ui_guidelines/icon_design_adaptive?hl=ja)
