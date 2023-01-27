package com.ror0.quizapp

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Argentina",
            "Australia",
            "Denmark",
            "Austria",
            3)
        questionsList.add(que1)

        val que2 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina",
            "Australia",
            "Kuwait",
            "Austria",
            1)
        questionsList.add(que2)

        val que3 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Germany",
            "India",
            "Denmark",
            "New Zealand",
            2)
        questionsList.add(que3)

        val que4 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Germany",
            "Australia",
            "Fiji",
            "Kuwait",
            4)
        questionsList.add(que4)

        val que5 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Brazil",
            "Australia",
            "Belgium",
            "Austria",
            3)
        questionsList.add(que5)

        val que6 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "New Zealand",
            "Australia",
            "Denmark",
            "Austria",
            2)
        questionsList.add(que6)

        val que7 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Brazil",
            "Australia",
            "Denmark",
            "Venezuela",
            1)
        questionsList.add(que7)

        val que8 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Argentina",
            "Fiji",
            "Monaco",
            "Nova Scotia",
            2)
        questionsList.add(que8)

        val que9 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "Belgium",
            "Australia",
            "New Zealand",
            "Austria",
            3)
        questionsList.add(que9)

        val que10 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Belgium",
            "India",
            "Denmark",
            "Germany",
            4)
        questionsList.add(que10)

        return questionsList
    }
}