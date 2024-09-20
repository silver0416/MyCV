package cc.bap5.content

import cc.bap5.models.CVContent
import cc.bap5.models.CVSection

// 英語資料
val englishContent = CVContent(
    name = "Hsiao Li-Yang",
    personalInfo = listOf(
        CVSection("Email: silver041692@gmail.com"),
        CVSection("Phone: 090-4071-7763"),
        CVSection("Address: Nippori Sunrise Mansion 204, 5-34-8 Higashi-Nippori, Arakawa-ku, Tokyo"),
        CVSection("From: Taipei, Taiwan"),
    ),
    skills = listOf(
        CVSection("Kotlin"),
        CVSection("Java"),
        CVSection("Python"),
        CVSection("Android Application Development"),
        CVSection("Compose Multiplatform"),
        CVSection("SQL"),
        CVSection("Linux"),
    ),
    experience = listOf(
        CVSection("September 2021 - June 2023: Tamkang University Computer Support Service Team Vice Leader"),
        CVSection("July 2023 - December 2024: University Social Responsibility (USR) Framework Assistant"),
    ),
    education = listOf(
        CVSection("Tamkang University Bachelor of Information Management"),
    ),
    projects = listOf(
        CVSection("Graduation Project: IoT Application",
            "IoT application and backend server development"
        ),
        CVSection(
            "University Social Responsibility (USR) Framework: Happy-Care&Happy-Life (Application Development)",
            "Application for the elderly to find companionship and care services"
        )
    ),
    languages = listOf(
        CVSection("Chinese"),
        CVSection("English (TOEIC scheduled for October)"),
        CVSection("Japanese (N2, N1 scheduled for December)")
    )
)