# はじめに
Android Studioのテンプレートプロジェクトを作成すると、仮に`minSDKVersion`が高いものに設定したとしても、<br>
必ずLegacy Iconもデフォルトで追加される。Legacy Icon(pngファイル)を格納しているフォルダはresフォルダ配下の以下のディレクトリ<br>
- `mipmap-hdpi` , `mipmap-mdpi`、`mipmap-hdpi`, `mipmap-xdpi`, `mipmap-xxdpi`, `mipmap-xxxdpi`<br>
テンプレートからの作成だと、解像度別に作成された不要なアイコンが多すぎるので<br>
不必要なアイコンを探す目的で本アプリを作成した。<br>

# 環境

本アプリを使った調査は、以下のAndroid Studioのバージョンで作成できるテンプレートプロジェクトをBaseに行っています。<br>
https://user-images.githubusercontent.com/16476224/120110164-6f8ad200-c1a7-11eb-8da0-cf8b4111a289.png

# 結論
- `minSDKVersion`がAPI Level 26以上であれば、どの解像度の端末でも ランチャーアイコンは、`res/mipmap-anydpi-v26/` 配下の`Adaptive Icon`を使う
- OS ver 8.0未満であれば、`Legacy Icon`を使う
- `Adaptive Icon`のアニメーションは各端末のホームアプリに依存する。なので、OSver 8.0以上であれば必ずアニメーションが適用されるとは限らない
    - Pixcelシリーズに入ってるホームアプリの`Pixel Launcher`のVersion `8.1.0-4429924`以上でアニメーションが効くことを確認済み
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
| `drawable-hdpi` | `mipmap-anydpi-v26` |<img src= "https://user-images.githubusercontent.com/16476224/120109902-66e5cc00-c1a6-11eb-9e00-4cd98cdeeea5.png" width=320 /> |<img src= "https://user-images.githubusercontent.com/16476224/120109905-68af8f80-c1a6-11eb-8919-1b926884d5f3.png" width=320 /> |

## 1080 x 1920 xxhdpi  Nexus 5 API 24

**mipmapは、`xxxhdpi`を使っているのに注目！ 誤植ではない。**
| drawable | mipmap | home capture | app capture
|:---|:---:|:---:|:---:|
| `drawable-xxhdpi` | `mipmap-xxxhdpi` |<img src= "https://user-images.githubusercontent.com/16476224/120109850-2423f400-c1a6-11eb-9cba-3313a601627a.png" width=320 /> |<img src= "https://user-images.githubusercontent.com/16476224/120109852-25edb780-c1a6-11eb-95a9-43651e047e38.png" width=320 /> |

# Capture (Adaptive IconのAnimationが効いてる場合)

**droid君が動いてることが確認できる！！**

<img src= "https://user-images.githubusercontent.com/16476224/120109978-b4623900-c1a6-11eb-942b-64e0cf42fe6f.gif" width=320 />

# Pixcelシリーズに入ってるホームアプリの`Pixel Launcher`のVersionの確認方法

ホーム長押し > 「ホームの設定」をクリック > 「概要」をクリック > 「バージョン」を確認する

# Links
[各種のピクセル密度をサポートする](https://developer.android.com/training/multiscreen/screendensities?hl=ja#kotlin)
[解像度の早見表](https://developer.android.com/training/multiscreen/screendensities?hl=ja#TaskProvideAltBmp)
[Adaptive Icon](https://developer.android.com/guide/practices/ui_guidelines/icon_design_adaptive?hl=ja)
