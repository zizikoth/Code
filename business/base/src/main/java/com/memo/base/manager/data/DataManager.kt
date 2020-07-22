package com.memo.base.manager.data


/**
 * title:信息存储管理
 * describe:这里使用SP进行数据存储，建议只进行基本数据的存储，最多添加一个个人信息的json
 *
 * @author memo
 * @date 2020-04-28 14:13
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class DataManager {

	private object Holder {
		val instance = DataManager()
	}

	companion object {
		fun get() = Holder.instance
	}

}