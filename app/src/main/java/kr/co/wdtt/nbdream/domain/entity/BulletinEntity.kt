package kr.co.wdtt.nbdream.domain.entity

data class BulletinEntity(
    val id: String,  // 게시글 id가 필요할지 안할지? 필요하다면 어떤 식으로 만들지?
    val content: String,
    val pictures: List<String>,
    val plant: Plant,
    val bulletinCategory: BulletinCategory,
    val createdTime: String,
    val deletedTime: String,
    val modifiedTime: String,
    val userId: String,
    val bookmarkedUsers: List<String>,
    val comments: List<String>,  // 댓글 id만 들고 통신할지, 데이터 들고있을지?
    val bulletinSortType: BulletinSortType,  // 댓글 id만 들고 통신할지, 데이터 들고있을지?
)

enum class Plant(
    val value: String,  // value가 필요 없을지도
) {
    RedPepper("고추"),
    Rice("벼"),
    Potato("감자"),
    SweetPotato("고구마"),
}

enum class BulletinCategory(
    val value: String,  // value가 필요 없을지도
) {
    Free("자유주제"),
    Qna("질문"),
    Disease("병해충"),
}

enum class BulletinSortType {
    Recent,
    Order,
}

// 나중에 파일 나눌거. 일단 여기에 작성
data class CommentEntity(
    val id: String,  // 댓글 id?
    val content: String,
    val createdTime: String,
    val deletedTime: String,
    val modifiedTime: String,  // 댓글 수정은 가능하게 할지?
    val userId: String,
)