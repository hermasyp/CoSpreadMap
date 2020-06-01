package com.catnip.cospreadmap.utils.ext

import java.text.NumberFormat

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

fun String.fromFormattedToNumber() : Int?{
    return NumberFormat.getInstance().parse(this)?.toInt()
}