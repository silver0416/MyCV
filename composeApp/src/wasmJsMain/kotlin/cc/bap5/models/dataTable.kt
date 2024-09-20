package cc.bap5.models

data class CVContent(
    val name: String,
    val personalInfo: List<CVSection>,
    val skills: List<CVSection>,
    val experience: List<CVSection>,
    val education: List<CVSection>,
    val projects: List<CVSection>,
    val languages: List<CVSection>,
)

data class CVSection(
    val title: String,
    val details: String? = null,
)