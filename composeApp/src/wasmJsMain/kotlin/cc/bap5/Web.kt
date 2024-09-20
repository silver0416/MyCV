package cc.bap5

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cc.bap5.content.englishContent
import cc.bap5.content.japaneseContent
import cc.bap5.models.CVContent
import cc.bap5.models.CVSection
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_github
import kotlinx.browser.window
import org.jetbrains.compose.resources.painterResource

@Composable
fun LanguageSwitch(isJapanese: Boolean, onLanguageChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(text = if (isJapanese) "英語" else "日本語", fontSize = 16.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Switch(checked = isJapanese, onCheckedChange = onLanguageChange)
    }
}

@Composable
fun CVHeader(content: CVContent) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = content.name,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun CVSection(title: String, content: List<CVSection>) {
    val show = if (title == "Projects") {
        "Show"
    } else {
        "表示"
    }
    val hide = if (title == "Projects") {
        "Hide"
    } else {
        "非表示"
    }
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            if (
                title == "Projects" || title == "プロジェクト"
            ) {
                GitHubLink()
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                content.forEach { line ->
                    val detailsVisible = remember { mutableStateOf(false) }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Text(text = line.title, modifier = Modifier.weight(0.8f))
                        if (line.details != null) {
                            TextButton(
                                onClick = {
                                    // 向下展開詳細內容
                                    detailsVisible.value = !detailsVisible.value
                                },
                                modifier = Modifier.weight(0.2f)
                            ) {
                                Text(text = if (detailsVisible.value) hide else show)
                            }
                        }
                    }
                    AnimatedVisibility(visible = detailsVisible.value) {
                        Text(text = line.details ?: "", modifier = Modifier.padding(start = 16.dp))
                    }
                    if (line != content.last()) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                            thickness = 2.dp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CVPersonalInfo(content: CVContent) {
    CVSection(if (content == japaneseContent) "個人情報" else "Personal Information", content.personalInfo)
}

@Composable
fun CVSkills(content: CVContent) {
    CVSection(if (content == japaneseContent) "スキル" else "Skills", content.skills)
}

@Composable
fun CVExperience(content: CVContent) {
    CVSection(if (content == japaneseContent) "経験" else "Experience", content.experience)
}

@Composable
fun CVEducation(content: CVContent) {
    CVSection(if (content == japaneseContent) "学歴" else "Education", content.education)
}

@Composable
fun CVProjects(content: CVContent) {
    CVSection(if (content == japaneseContent) "プロジェクト" else "Projects", content.projects)
}

@Composable
fun CVLanguages(content: CVContent) {
    CVSection(if (content == japaneseContent) "言語" else "Languages", content.languages)
}


@Composable
fun GitHubLink() {
    IconButton(
        onClick = {
            // 打開我的GitHub頁面
            window.open("https://github.com/silver0416", "_blank")
        }
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_github),
            contentDescription = "GitHub",
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}


@Composable
fun CVPage() {
    // 定義一個狀態來儲存當前語言是否為日語
    var isJapanese by remember { mutableStateOf(false) }

    // 根據語言選擇適當的內容
    val content = if (isJapanese) japaneseContent else englishContent
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        state = scrollState,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(modifier = Modifier.widthIn(max = 600.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth().weight(0.75f),
                        contentAlignment = Alignment.Center
                    ) { CVHeader(content = content) }
                    Box(
                        modifier = Modifier.fillMaxWidth().weight(0.25f),
                        contentAlignment = Alignment.Center
                    ) {// 語言切換開關
                        LanguageSwitch(isJapanese = isJapanese, onLanguageChange = { isJapanese = it })
                    }
                }
                // 顯示簡歷內容
                Spacer(modifier = Modifier.height(16.dp))
                CVPersonalInfo(content = content)
                Spacer(modifier = Modifier.height(16.dp))
                CVLanguages(content = content)
                Spacer(modifier = Modifier.height(16.dp))
                CVSkills(content = content)
                Spacer(modifier = Modifier.height(16.dp))
                CVExperience(content = content)
                Spacer(modifier = Modifier.height(16.dp))
                CVEducation(content = content)
                Spacer(modifier = Modifier.height(16.dp))
                CVProjects(content = content)
            }
        }
    }
}