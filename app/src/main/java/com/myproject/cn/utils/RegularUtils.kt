package com.myproject.cn.utils

import java.util.regex.Pattern

object RegularUtils {
    //判断字符串中是否有数字
    fun isNumeric(str: String): Boolean {
        val pattern = Pattern.compile("[0-9]*")
        val isNum = pattern.matcher(str)
        return if (!isNum.matches()) {
            false
        } else true
    }


    /**
     * 车牌号判断
     *
     * @param plate
     * @return
     */
    fun LicensePlate(plate: String): Boolean {
        var regex: String
        if (plate.length == 7) {
            regex = "^[\\u4e00-\\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z0-9]{5}$"
            if (Pattern.matches(regex, plate)) {
                return true
            } else {
                regex = "^[\\u4e00-\\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z0-9]{4}[学警挂]{1}$"//特种车判断
                if (Pattern.matches(regex, plate)) {
                    return true
                } else {
                    regex = "^[a-zA-Z]{2}[a-zA-Z0-9]{5}$"
                    return if (Pattern.matches(regex, plate)) {
                        true
                    } else {
                        false
                    }
                }
            }

        } else if (plate.length == 9) {
            regex = "^[WJ]{2}[a-zA-Z0-9]{7}$"//其他特种车判断
            return if (Pattern.matches(regex, plate)) {
                true
            } else {
                false
            }
        } else {

        }
        return false
    }

    /**
     * 车架号和车牌号判断
     *
     * @param plate
     * @return
     */
    fun LicensePlateVin(plate: String): Boolean {
        var regex: String
        if (plate.length == 7) {
            regex = "^[\\u4e00-\\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z0-9]{5}$"
            if (Pattern.matches(regex, plate)) {
                return true
            } else {
                regex = "^[\\u4e00-\\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z0-9]{4}[学警挂]{1}$"//特种车判断
                if (Pattern.matches(regex, plate)) {
                    return true
                } else {
                    regex = "^[a-zA-Z]{2}[a-zA-Z0-9]{5}$"
                    return if (Pattern.matches(regex, plate)) {
                        true
                    } else {
                        false
                    }
                }
            }

        } else if (plate.length == 9) {
            regex = "^[WJ]{2}[a-zA-Z0-9]{7}$"//其他特种车判断
            return if (Pattern.matches(regex, plate)) {
                true
            } else {
                false
            }
        } else {
            regex = "^[a-zA-Z0-9]{17}$"
            if (Pattern.matches(regex, plate)) return true
        }
        return false
    }

    /**
     * 车架号判断
     *
     * @param Vin
     * @return
     */
    fun Vin(Vin: String): Boolean {
        val regex = "^[a-zA-Z0-9]{17}$"
        return if (Pattern.matches(regex, Vin)) true else false
    }

    /**
     * 手机号判断
     *
     * @param phone
     * @return
     */
    fun Phone(phone: String): Boolean {

        if (phone.length == 11) {
            val regex_cm = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$"
            val regex_cu = "^((13[0-2])|(145)|(15[5-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$"
            val regex_ct = "^((133)|(153)|(170)|(177)|(18[0,1,9]))\\d{8}$"
            if (Pattern.matches(regex_cm, phone) || Pattern.matches(regex_cu, phone) || Pattern.matches(regex_ct, phone)) {
                return true
            }
        } else {
            return false
        }
        return false
    }

    /**
     * 发送机号判断
     *
     * @param EngineNo
     * @return
     */
    fun EngineNo(EngineNo: String): Boolean {
        val regex = "[^\\u4e00-\\u9fa5]+$"
        return if (Pattern.matches(regex, EngineNo)) true else false
    }

    /**
     * 邮箱判断
     *
     * @param E_Main
     * @return
     */
    fun E_Mail(E_Main: String): Boolean {
        val regex = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,5})+$"
        return if (Pattern.matches(regex, E_Main)) true else false
    }


    /**
     * 驾驶证判断
     *
     * @param License
     * @return
     */
    fun DriversLicense(License: String): Boolean {
        val regex = "^(\\d{12})$"
        return if (Pattern.matches(regex, License)) true else false
    }

    /**
     * 护照判断
     *
     * @param passport
     * @return
     */
    fun Passport(passport: String): Boolean {
        val regex = "^(S\\d{7})$|(D\\d{7})$|(P\\d{7})$|(G\\d{8})$"
        return if (Pattern.matches(regex, passport)) true else false
    }

    /**
     * 港澳通行证判断
     *
     * @param gatepass
     * @return
     */
    fun GatePass(gatepass: String): Boolean {
        val regex = "^[HMhm]{1}([0-9]{10}|[0-9]{8})$"
        return if (Pattern.matches(regex, gatepass)) true else false
    }

    /**
     * 台湾通行证判断
     *
     * @param GatePass
     * @return
     */
    fun TaiwanGatePass(GatePass: String): Boolean {
        val regex = "^(T\\d{8})$"
        return if (Pattern.matches(regex, GatePass)) true else false
    }

    /**
     * 身份证验证
     *
     * @param text
     * @return
     */
    fun IDCard(text: String): Boolean {
        val textone = "/^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$/"
        val texttow = "/^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$/"
        return if (text.length == 15) {
            if (Pattern.matches(textone, text))
                true
            else
                false
        } else if (text.length == 18) {
            if (Pattern.matches(texttow, text))
                true
            else
                false
        } else {
            false
        }
    }

    //去掉字符串中中文
    fun disposeChainese(text: String): String {
        val reg = "[\u4e00-\u9fa5]"
        val pat = Pattern.compile(reg)
        val mat = pat.matcher(text)
        return mat.replaceAll("")

    }

    fun getURl(text: String): String {
        val reg = "/http:[\\/]{2}[a-z]+[.]{1}[a-z\\d\\-]+[.]{1}[a-z\\d]*[\\/]*[A-Za-z\\d]*[\\/]*[A-Za-z\\d]*/"
        val pattern = Pattern.compile(reg)
        val matcher = pattern.matcher(text)
        return matcher.replaceAll("")
    }
}