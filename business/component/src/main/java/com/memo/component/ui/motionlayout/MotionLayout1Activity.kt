package com.memo.component.ui.motionlayout

import com.memo.base.ui.mvvm.BaseActivity
import com.memo.component.R
import com.memo.core.tool.helper.ImageLoadHelper
import kotlinx.android.synthetic.main.activity_motion_layout_1.*

class MotionLayout1Activity : BaseActivity() {

	val imageUrl = "http://b-ssl.duitang.com/uploads/item/201607/11/20160711113003_MicYN.jpeg"
	val introduction =
		"        张艺兴（LAY），1991年10月7日出生于湖南省长沙市，中国内地流行乐男歌手、影视演员，韩国男子演唱组合EXO/EXO-M成员。\n        2005年参加《明星学院》比赛并获得总决赛季军。2008年成为韩国SM公司旗下练习生。2012年4月以EXO团体和其子队EXO-M成员身份正式出道。"
	val life =
		"        张艺兴出生于湖南省长沙市，6岁时参演了当代农村电视剧《咱老百姓》，中学就读于湖南师范大学附属中学。2000年参加《音乐不断》林志颖歌友会并演唱歌曲，首次接触电视荧屏。2005年参加湖南经视《明星学院》比赛并获得总决赛季军，比赛结束后，张艺兴回到学校继续学业，在校期间，他都会参加校文化艺术节的汇演。\n        2008年， 张艺兴通过S.M. Casting System在中国的选拔成为韩国SM公司旗下练习生。张艺兴精通钢琴、吉他、葫芦丝等多种乐器，13岁初试作曲便沉浸其中，此后不断有作品问世。\n        2014年，张艺兴加入韩国音乐著作权协会成为注册作曲家。2015年4月，张艺兴在中国成立个人工作室，随后工作重心将适当转向国内，但其仍将作为EXO成员参加组合的集体活动，不会影响EXO整体的发展计划。同时，SM公司给予张艺兴在中国独立开展其个人影视、音乐、商务合作等一切经纪事务的权利，不干涉其在中国的工作安排，且对他在中国的发展给予充分支持，而张艺兴亦成为SM公司成立以来首位获得在中国开放自主发展权的中国艺人。"

	override fun bindLayoutRes(): Int = R.layout.activity_motion_layout_1

	override fun initialize() {
		ImageLoadHelper.loadRoundImage(mContext, imageUrl, 10, mIvCover)
		mTvIntroduction.text = introduction
		mTvLife.text = life
	}

	override fun onBackPressed() {
		if (mRoot.currentState != mRoot.startState) {
			mRoot.transitionToStart()
		} else {
			super.onBackPressed()
		}
	}
}