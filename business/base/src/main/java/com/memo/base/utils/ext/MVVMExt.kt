package com.memo.base.utils.ext

import java.lang.reflect.ParameterizedType

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-04-29 16:26
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getViewModelClass(clazz: Any): VM {
	return (clazz.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}
