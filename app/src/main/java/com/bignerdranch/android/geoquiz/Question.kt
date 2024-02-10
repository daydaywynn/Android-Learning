import androidx.annotation.StringRes
/*
* This data class holds two pieces of data
* 1. Resource ID for the question text
* 2. The question Answer
* */
data class Question(@StringRes val textResId: Int, val answer : Boolean) {
    public fun checkAnswer(passedAnswer: Boolean): Boolean {
        return this.answer.equals(passedAnswer)
    }
}