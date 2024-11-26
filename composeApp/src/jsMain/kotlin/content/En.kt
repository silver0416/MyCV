package cc.bap5.content

import androidx.compose.foundation.Image
import cc.bap5.models.CVContent
import cc.bap5.models.CVSection
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.aicare1
import kotlinproject.composeapp.generated.resources.aicare2
import kotlinproject.composeapp.generated.resources.iotapp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

// 英語資料
@OptIn(ExperimentalResourceApi::class)
val englishContent = CVContent(
    name = "Hsiao Li-Yang",
    personalInfo = listOf(
        CVSection("Email: silver041692@gmail.com","silver041692@gmail.com"),
        CVSection("From: Taipei, Taiwan"),
    ),
    skills = listOf(
        CVSection("Kotlin"),
        CVSection("Java"),
        CVSection("Python"),
        CVSection("Android Application Development"),
        CVSection("Compose Multiplatform"),
        CVSection("SQL"),
        CVSection("Linux and docker deployment"),
    ),
    experience = listOf(
        CVSection("September 2021 - June 2023: Tamkang University Computer Support Service Team Vice Leader"),
        CVSection("July 2023 - December 2024: University Social Responsibility (USR) Framework Assistant"),
    ),
    education = listOf(
        CVSection("Tamkang University Bachelor of Information Management"),
    ),
    projects = listOf(
        CVSection(
            "Graduation Project: IoT Application",
            "IoT application and backend server development",
            listOf(
                Res.drawable.iotapp
            )
        ),
        CVSection(
            "University Social Responsibility (USR) Framework: Happy-Care&Happy-Life (Application Development)",
            "Application for the elderly to find companionship and care services",
            listOf(
                Res.drawable.aicare1,
                Res.drawable.aicare2
            )
        )
    ),
    languages = listOf(
        CVSection("Chinese"),
        CVSection("English (2024.10 | TOEIC 770 points)"),
        CVSection("Japanese (N2, N1 scheduled for December)")
    ),
)