package kr.co.main.providers.calendar

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kr.co.main.model.calendar.DiaryModel
import kr.co.main.model.calendar.type.WorkDescriptionModelType

internal class FakeWorkDescriptionModelListProvider :
    PreviewParameterProvider<List<DiaryModel.WorkDescriptionModel>> {
    override val values = sequenceOf(
        listOf(
            DiaryModel.WorkDescriptionModel(
                WorkDescriptionModelType.WEED,
                "감자밭 제초 작업"
            ),
            DiaryModel.WorkDescriptionModel(
                WorkDescriptionModelType.HARVEST,
                "봄 감자 수확"
            )
        )
    )
}