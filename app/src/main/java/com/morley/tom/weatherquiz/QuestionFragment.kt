package com.morley.tom.weatherquiz

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morley.tom.weatherquiz.R
import com.morley.tom.weatherquiz.models.QuestionModel
import kotlinx.android.synthetic.main.fragment_question.*
import java.util.*



class QuestionFragment : Fragment() {

    lateinit var currentQuestion : QuestionModel
    lateinit var quizNavigator : QuizNavigator

    fun setNavigator(activity : Activity){
        quizNavigator = activity as QuizNavigator
    }


    interface QuizNavigator {
        fun correctAnswerClicked()
        fun incorrectAnswerClicked()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews(){
        val btnCorrectBackground = context?.getDrawable(R.drawable.answer_button_right)
        val btnIncorrectBackground = context?.getDrawable(R.drawable.answer_button_wrong)
        weatherDescriptionTv.text = currentQuestion.weatherDescription.toUpperCase()
        temperatureTv.text = "${currentQuestion.currentTemp} \u2103"

        Collections.shuffle(currentQuestion.answers)
        answer1Btn.text = currentQuestion.answers[0].answer
        answer2Btn.text = currentQuestion.answers[1].answer
        answer3Btn.text = currentQuestion.answers[2].answer

        answer1Btn.setOnClickListener {
            disableAnswerButtons()
            val isCorrect = isCorrectAnswer(answer1Btn.text.toString())
            if (isCorrect) answer1Btn.background = btnCorrectBackground else answer1Btn.background = btnIncorrectBackground
            //Handler().postDelayed(this::initViews, 1000)
            if(isCorrect) quizNavigator.correctAnswerClicked() else quizNavigator.incorrectAnswerClicked()
        }
        answer2Btn.setOnClickListener {
            val isCorrect = isCorrectAnswer(answer2Btn.text.toString())
            if (isCorrect) answer2Btn.background = btnCorrectBackground else answer2Btn.background = btnIncorrectBackground
            if(isCorrect) quizNavigator.correctAnswerClicked() else quizNavigator.incorrectAnswerClicked()
        }
        answer3Btn.setOnClickListener {
            val isCorrect = isCorrectAnswer(answer3Btn.text.toString())
            if (isCorrect) answer3Btn.background = btnCorrectBackground else answer3Btn.background = btnIncorrectBackground
            if(isCorrect) quizNavigator.correctAnswerClicked() else quizNavigator.incorrectAnswerClicked()
        }
    }

    private fun disableAnswerButtons(){
        answer1Btn.isClickable = false
        answer2Btn.isClickable = false
        answer3Btn.isClickable = false
    }

    private fun isCorrectAnswer(clickedAnswer : String) : Boolean{
        return clickedAnswer == currentQuestion.correctLocation
    }

    fun setQuestion(question: QuestionModel){
        this.currentQuestion = question
    }

}
