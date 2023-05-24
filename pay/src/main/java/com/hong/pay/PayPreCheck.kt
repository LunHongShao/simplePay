package com.hong.pay


/**
 *
 * @Description:
 * @Author:         slh
 * @CreateDate:     2021/9/1 15:06
 * @UpdateUser:     更新者：
 * @UpdateDate:     2021/9/1 15:06
 * @UpdateRemark:   更新说明：
 * @Version:        2.2.4
 */
data class PayPreCheck(val states: Int, val errorMsg: String) {
    fun argumentIsNoProblem() = states == 200
}