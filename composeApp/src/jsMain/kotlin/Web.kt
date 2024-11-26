package cc.bap5

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.onClick
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import cc.bap5.content.englishContent
import cc.bap5.content.japaneseContent
import cc.bap5.models.CVContent
import cc.bap5.models.CVSection
import kotlinproject.composeapp.generated.resources.*
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_email
import kotlinproject.composeapp.generated.resources.ic_github
import kotlinproject.composeapp.generated.resources.ic_right_top
import kotlinx.browser.window
import org.jetbrains.compose.resources.DrawableResource
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
fun CVSection(
    title: String,
    content: List<CVSection>,
    showDialog: MutableState<Boolean> = remember { mutableStateOf(false) },
    dialogImage: MutableState<DrawableResource?> = remember { mutableStateOf(null) }
) {
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
    val windowWidth = remember { mutableStateOf(window.window.innerWidth) }
    val windowHeight = remember { mutableStateOf(window.window.innerHeight) }
    val isMobile = windowWidth.value < 600 || windowHeight.value < 600

    window.addEventListener("resize", {
        windowWidth.value = window.window.innerWidth
        windowHeight.value = window.window.innerHeight
    })

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
                Spacer(modifier = Modifier.weight(0.8f))
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
                    if (isMobile) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start,
                        ) {
                            Text(text = line.title)
                            if (line.title.contains("Email") || line.title.contains("メール")) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                                    IconButton(
                                        onClick = {
                                            // 打開郵件程式
                                            window.open("mailto:${line.details}", "_blank")
                                        },
                                    ) {
                                        Icon(
                                            painter = painterResource(Res.drawable.ic_email),
                                            contentDescription = "Email",
                                            tint = MaterialTheme.colorScheme.primary,
                                        )
                                    }
                                    IconButton(
                                        onClick = {
                                            // 複製郵件地址
                                            window.navigator.clipboard.writeText(line.details ?: "")
                                        },
                                    ) {
                                        Icon(
                                            painter = painterResource(Res.drawable.ic_copy),
                                            contentDescription = "copy to clipboard",
                                            tint = MaterialTheme.colorScheme.primary,
                                        )
                                    }
                                }
                            }
                            else if (line.details != null || line.image != null) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                    TextButton(
                                        onClick = {
                                            // 向下展開詳細內容
                                            detailsVisible.value = !detailsVisible.value
                                        },
                                    ) {
                                        Text(text = if (detailsVisible.value) hide else show)
                                    }
                                }
                            }
                        }
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Text(text = line.title, modifier = Modifier.weight(0.8f))
                            if (line.title.contains("Email") || line.title.contains("メール")) {
                                IconButton(
                                    onClick = {
                                        // 打開郵件程式
                                        window.open("mailto:${line.details}", "_blank")
                                    },
                                ) {
                                    Icon(
                                        painter = painterResource(Res.drawable.ic_email),
                                        contentDescription = "Email",
                                        tint = MaterialTheme.colorScheme.primary,
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        // 複製郵件地址
                                        window.navigator.clipboard.writeText(line.details ?: "")
                                    },
                                ) {
                                    Icon(
                                        painter = painterResource(Res.drawable.ic_copy),
                                        contentDescription = "copy to clipboard",
                                        tint = MaterialTheme.colorScheme.primary,
                                    )
                                }
                            }
                            else if (line.details != null || line.image != null) {
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
                    }
                    AnimatedVisibility(visible = detailsVisible.value) {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = line.details ?: "", modifier = Modifier.padding(start = 16.dp))
                            LazyRow {
                                line.image?.forEach { image ->
                                    item {
                                        Image(
                                            painter = painterResource(image),
                                            contentDescription = null,
                                            modifier = Modifier.size(200.dp).clickable {
                                                dialogImage.value = image
                                                showDialog.value = true
                                            }
                                        )
                                    }
                                }
                            }
                        }
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
fun CVProjects(content: CVContent, showDialog: MutableState<Boolean>, dialogImage: MutableState<DrawableResource?>) {
    CVSection(if (content == japaneseContent) "プロジェクト" else "Projects", content.projects, showDialog, dialogImage)
}

@Composable
fun CVLanguages(content: CVContent) {
    CVSection(if (content == japaneseContent) "言語" else "Languages", content.languages)
}


@Composable
fun GitHubLink() {
    Button(
        onClick = {
            // 打開我的GitHub頁面
            window.open("https://github.com/silver0416", "_blank")
        },
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_github),
                contentDescription = "GitHub",
                tint = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "GitHub",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 4.dp)
            )
            Icon(
                painter = painterResource(Res.drawable.ic_right_top),
                contentDescription = "right_top",
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CVPage() {
    // 定義一個狀態來儲存當前語言是否為日語
    var isJapanese by remember { mutableStateOf(false) }

    // 根據語言選擇適當的內容
    val content = if (isJapanese) japaneseContent else englishContent
    val scrollState = rememberLazyListState()
    val showDialog = remember { mutableStateOf(false) }
    val dialogImage = remember { mutableStateOf<DrawableResource?>(null) }

    if (showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = false },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Image(
                painter = painterResource(dialogImage.value!!),
                contentDescription = null,
                modifier = Modifier.size(400.dp).onClick {
                    showDialog.value = false
                }
            )
        }
    }

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
                CVEducation(content = content)
                Spacer(modifier = Modifier.height(16.dp))
                CVLanguages(content = content)
                Spacer(modifier = Modifier.height(16.dp))
                CVSkills(content = content)
                Spacer(modifier = Modifier.height(16.dp))
                CVExperience(content = content)
                Spacer(modifier = Modifier.height(16.dp))
                CVProjects(content = content, showDialog = showDialog, dialogImage = dialogImage)
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}