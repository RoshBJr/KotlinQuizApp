package com.ror0.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {
    // properties
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mUserName: String? = null
    private var correctAnswers: Int = 0

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        // assigning the variables with their respective views
        tvProgress = findViewById(R.id.tvProgress)
        progressBar = findViewById(R.id.progressBar)
        tvQuestion = findViewById(R.id.tvQuestion)
        ivImage = findViewById(R.id.ivImage)
        tvOptionOne = findViewById(R.id.tvOptionOne)
        tvOptionTwo = findViewById(R.id.tvOptionTwo)
        tvOptionThree = findViewById(R.id.tvOptionThree)
        tvOptionFour = findViewById(R.id.tvOptionFour)
        btnSubmit = findViewById(R.id.btnSubmit)
        mUserName = intent.getStringExtra(Constants.USER_NAME)

        // initializing the on click listener on all options
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionsList = Constants.getQuestions()
        setQuestion()
        defaultOptionsView()
    }

    private fun setQuestion() {

        // every time we set the new question we put the options to their default state
        defaultOptionsView()

        // here we fetch all the info of a single question and assign them to all our views in
        // order to see them on our phone (question, flag, progress bar, progress 1/10, option 1-4)
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        ivImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition / ${progressBar?.max}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour
        btnSubmit?.text = "SUBMIT"
    }

    // this method is first called on the OnCreate() then called everytime the user selects an
    // option and what it does is return every option to its default state
    private fun defaultOptionsView() {

        // variables to add every option as an array
        val options = ArrayList<TextView>()
        // adding all the options to said array
        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }

        // for loop te return every option to its default state
        for(option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    // this method is called to change the style of selected option
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        // set every button to its normal state
        defaultOptionsView()

        // this variable is there to verify if the user has selected the right answer
        mSelectedOptionPosition = selectedOptionNum

        // here we change the colored text and style of the selected option with its background
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    // this method is called when one of the options was selected
    // it calls another method (selectedOptionView) to change the option style
    // (textColor, background, etc..)
    override fun onClick(view: View?) {
        when(view?.id) {

            R.id.tvOptionOne -> {
                tvOptionOne?.let {
                    selectedOptionView(it, 1)
                }
            }

            R.id.tvOptionTwo -> {
                tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }

            R.id.tvOptionThree -> {
                tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }
            }

            R.id.tvOptionFour -> {
                tvOptionFour?.let {
                    selectedOptionView(it, 4)
                }
            }

            R.id.btnSubmit -> {
                // if our selected option is equal to 0 which means if we already answered the question
                // and the submit button says "GO TO NEXT QUESTION" we enter this scope
                if(mSelectedOptionPosition == 0) {

                    // current position is the position of which question we are situated
                    // example question 1 would be 0 and so forth
                    mCurrentPosition++

                    when { // while we are not at the last question we set the next question
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        // when there is no other question we finish the test
                        else -> {
                            // when we select the finish button at the end of the quiz we redirect
                            // the user to the result activity
                            val intent = Intent(this, ResultQuiz::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName) // we send the user's username prompted
                            intent.putExtra(Constants.CORRECT_ANSWERS,correctAnswers) // we send all his/hers correct answers
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size) // we send the number of questions total
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    if(question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else { // correct answer so correctAnswers plus 1
                        correctAnswers++
                    }
                    // always show to right answer when the  user submits its answer
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    // when we are at the last question we change the button text to finish
                    if(mCurrentPosition == mQuestionsList!!.size) {
                        btnSubmit?.text = "FINISH"
                    } else { // else we set the button text to go to next question
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    // we reassign this variable to go to the next question
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    // method to get the drawable of the selected and submitted option
    private fun answerView(answer: Int, drawableView: Int) {
        when(answer) {
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}