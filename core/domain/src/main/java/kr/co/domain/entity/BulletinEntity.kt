package kr.co.domain.entity

import kr.co.domain.entity.type.CropType

data class BulletinEntity(
    val authorId: Long,
    val bulletinId: Long,
    val nickname: String,
    val profileImageUrl: String,
    val content: String,
    val crop: CropEntity,
    val imageUrls: List<String>,
    val bulletinCategory: BulletinCategory,
    val createdTime: String,
    val comments: List<CommentEntity>,
    val bookmarkedCount: Int,
) {
    companion object {
        fun dummy(idx: Int = 0) = BulletinEntity(
            authorId = 22222220 + (idx).toLong(),
            bulletinId = 33333330 + (idx).toLong(),
            nickname = "nickname${idx}",
            profileImageUrl = "https://profileImageUrl.com/${idx}",
            content = "content${idx}",
            crop = CropEntity(CropType.entries[(idx) % CropType.entries.size]),
            imageUrls = emptyList(),
            bulletinCategory = BulletinCategory.entries[(idx) % BulletinCategory.entries.size],
            createdTime = "createdTime${idx}",
            comments = emptyList(),
            bookmarkedCount = 220 + idx,
        )
    }

    enum class BulletinCategory(
        val queryName: String,
    ) {
        Free("free"),
        Qna("qna"),
        Disease("bug"),
    }

}
