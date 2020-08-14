package com.memo.component.entity

import android.os.Parcelable
import com.memo.component.R
import kotlinx.android.parcel.Parcelize

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-07-30 16:43
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Parcelize
data class ShareEntity(
	val image: Int = R.mipmap.bg_share_test,
	val title: String = "渔舟唱晚",
	val content: String = "《渔舟唱晚》是一首颇具古典风格的筝曲。乐曲描绘了夕阳映照万顷碧波，渔民悠然自得，渔船随波渐远的优美景象。这首乐曲是20世纪30年代以来，在中国流传最广、影响最大的一首筝独奏曲。\n" +
			"娄树华的《渔舟唱晚》，是中国古筝艺术史上划时代的作品，它是用古曲《归去来兮》为素材，发展编创而成者。创作于1938年——1939年之际。一经问世就开创了筝曲的新纪元。经过50多年表演证明，是一首受到中外音乐界公认的名筝曲。\n" +
			"由山东省临清市金灼南大师将传统筝曲《双板》《三环套日》《流水激石》编创成一曲，取名《渔舟唱晚》，成为一首著名的筝曲，广为流传。建国后，著名音乐家黎国荃先生根据同名筝曲曾改编创作为小提琴曲，得以在全世界范围内广泛流传，吕思清、盛中国、俞丽拿都曾倾情演绎。1984年，著名电子琴演奏大师浦琪璋将其改编并用电子琴完美演奏，中央电视台选取其1分36秒至2分43秒作为天气预报的背景音乐，一直沿用至今。三十多年不变的背景音乐，成为十四亿人最熟悉和喜爱的音乐。"
) : Parcelable