package id.widianapw.android_utils.validator

import com.google.android.material.textfield.TextInputLayout
import id.widianapw.android_utils.common.Const
import id.widianapw.android_utils.extensions.value

/**
 * Created by Widiana Putra on 05/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */

data class ValidatorResult(val isSuccess: Boolean = true, val message: String = "")

sealed class ValidatorType {
    class MinimumLength(val min: Int) : ValidatorType()
    class MaximumLength(val max: Int) : ValidatorType()
    object Required : ValidatorType()
    object Alphanumeric : ValidatorType()
    object Email: ValidatorType()
    class MinimumValue(val min: Int): ValidatorType()
    class MaximumValue(val max: Int) : ValidatorType()
    object Numeric : ValidatorType()

}

interface ValidatorParser {
    fun validate(text: String, type: ValidatorType): ValidatorResult
}

object ValidatorFactory : ValidatorParser {
    override fun validate(text: String, type: ValidatorType): ValidatorResult {
        return when (type) {
            is ValidatorType.Required -> RequiredValidator.validate(text, type)
            is ValidatorType.MinimumLength -> MinimumLengthValidator.validate(text, type)
            is ValidatorType.MaximumLength -> MaximumLengthValidator.validate(text, type)
            is ValidatorType.Alphanumeric -> AlphanumericValidator.validate(text, type)
            is ValidatorType.Email -> EmailValidator.validate(text, type)
            is ValidatorType.MinimumValue -> MinimumValueValidator.validate(text, type)
            is ValidatorType.MaximumValue -> MaximumValueValidator.validate(text, type)
            is ValidatorType.Numeric -> NumericValidator.validate(text, type)
        }
    }
}


object RequiredValidator : ValidatorParser {
    override fun validate(text: String, type: ValidatorType): ValidatorResult {
        val isSuccess = text.isNotEmpty()
        val message = "Wajib diisi"
        return ValidatorResult(isSuccess, message)
    }
}

object MaximumLengthValidator : ValidatorParser {
    override fun validate(text: String, type: ValidatorType): ValidatorResult {
        type as ValidatorType.MaximumLength
        val isSuccess = text.length < type.max
        return ValidatorResult(isSuccess, "Panjang maksimum ${type.max} karakter")
    }
}

object MinimumLengthValidator : ValidatorParser {
    override fun validate(text: String, type: ValidatorType): ValidatorResult {
        type as ValidatorType.MinimumLength
        val isSuccess = text.length > type.min
        return ValidatorResult(isSuccess, "Panjang minimum ${type.min} karakter")
    }
}

object AlphanumericValidator : ValidatorParser {
    override fun validate(text: String, type: ValidatorType): ValidatorResult {
        val isSuccess = Const.Validation.ALPHANUMERIC_PATTERN.matcher(text).find()
        return ValidatorResult(isSuccess, "Hanya boleh huruf dan angka")
    }
}

object EmailValidator : ValidatorParser {
    override fun validate(text: String, type: ValidatorType): ValidatorResult {
        val isSuccess = Const.Validation.EMAIL_PATTERN.matcher(text).find()
        return ValidatorResult(isSuccess, "Format salah")
    }
}

object MaximumValueValidator : ValidatorParser {
    override fun validate(text: String, type: ValidatorType): ValidatorResult {
        type as ValidatorType.MaximumValue
        val isSuccess = text.toInt() < type.max
        val message = "Nilai maksimum adalah ${type.max}"
        return ValidatorResult(isSuccess, message)
    }
}

object MinimumValueValidator : ValidatorParser {
    override fun validate(text: String, type: ValidatorType): ValidatorResult {
        type as ValidatorType.MinimumValue
        val isSuccess = text.toInt() > type.min
        return ValidatorResult(isSuccess, "Nilai minimum adalah ${type.min}")
    }
}

object NumericValidator: ValidatorParser{
    override fun validate(text: String, type: ValidatorType): ValidatorResult {
        val isSuccess = Const.Validation.NUMBER_PATTERN.matcher(text).find()
        return ValidatorResult(isSuccess, "Hanya boleh angka")
    }
}



//
//enum class ValidatorType{
//    REQUIRED,
//    EMAIL,
//    MINIMUM_LENGTH(val min: Int),
//    MAXIMUM_LENGTH,
//    ALPHANUMERIC
//}