package cc.bap5.content

import androidx.compose.foundation.Image
import cc.bap5.models.CVContent
import cc.bap5.models.CVSection
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.aicare1
import kotlinproject.composeapp.generated.resources.aicare2
import org.jetbrains.compose.resources.painterResource


// 日語資料
val japaneseContent = CVContent(
    name = "ショウ　リーヤーン",
    personalInfo = listOf(
        CVSection("メール: silver041692@gmail.com"),
        CVSection("出身地: 台北市, 台湾"),
    ),
    skills = listOf(
        CVSection("Kotlin"),
        CVSection("Java"),
        CVSection("Python"),
        CVSection("Androidアプリケーション開発"),
        CVSection("Compose Multiplatform"),
        CVSection("SQL"),
        CVSection("Linuxとdockerのデプロイメント"),
    ),
    experience = listOf(
        CVSection("2021年9月 - 2023年6月: 淡江大学コンピュータサポートサービスチーム 副リーダー"),
        CVSection("2023年7月 - 2024年1月: 大学社会的責任(USR)フレームワーク アシスタント"),
    ),
    education = listOf(
        CVSection("淡江大学 情報管理科"),
    ),
    projects = listOf(
        CVSection(
            "卒業プロジェクト: IoTアプリケーション",
            "IoTアプリケーションとバックエンドサーバーの開発"
        ),
        CVSection(
            "大学社会的責任(USR)フレームワーク: Happy-Care&Happy-Life (アプリケーション開発)",
            "高齢者が仲間や介護サービスを見つけるためのアプリケーション",
            listOf(
                Res.drawable.aicare1,
                Res.drawable.aicare2
            )
        )
    ),
    languages = listOf(
        CVSection("中国語"),
        CVSection("英語 (TOEIC予定: 10月)"),
        CVSection("日本語 (N2, N1予定: 12月)")
    )
)