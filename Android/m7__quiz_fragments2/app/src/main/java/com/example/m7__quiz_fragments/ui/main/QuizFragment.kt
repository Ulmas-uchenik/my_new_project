package com.example.m7__quiz_fragments.ui.main

import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.m7__quiz_fragments.R
import com.example.m7__quiz_fragments.databinding.FragmentQuizBinding
import com.example.skillbox_hw_quiz.quiz.Quiz
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import com.google.android.material.snackbar.Snackbar
import kotlinx.parcelize.Parcelize


class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuizBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Выставление текста вопроса
        binding.questionFirst.text = getQuestion(1)
        binding.questionSecond.text = getQuestion(2)
        binding.questionThird.text = getQuestion(3)

        // Выставление ответов
        binding.radioFirstBtn1.text = getAnswer(1, 1)
        binding.radioFirstBtn2.text = getAnswer(1, 2)
        binding.radioFirstBtn3.text = getAnswer(1, 3)
        binding.radioFirstBtn4.text = getAnswer(1, 4)
        binding.radioSecondBtn1.text = getAnswer(2, 1)
        binding.radioSecondBtn2.text = getAnswer(2, 2)
        binding.radioSecondBtn3.text = getAnswer(2, 3)
        binding.radioSecondBtn4.text = getAnswer(2, 4)
        binding.radioThirdBtn1.text = getAnswer(3, 1)
        binding.radioThirdBtn2.text = getAnswer(3, 2)
        binding.radioThirdBtn3.text = getAnswer(3, 3)
        binding.radioThirdBtn4.text = getAnswer(3, 4)

        val listAnswer = mutableListOf<Int>(-1, -1, -1)

        // Слушатели нажатий на RadioGroup
        binding.radioGroupFirst.setOnCheckedChangeListener { radioGroup, i ->
            listAnswer[0] = when (i) {
                R.id.radioFirst_btn1 -> 0
                R.id.radioFirst_btn2 -> 1
                R.id.radioFirst_btn3 -> 2
                R.id.radioFirst_btn4 -> 3
                else -> -1
            }
        }
        binding.radioGroupSecond.setOnCheckedChangeListener { radioGroup, i ->
            listAnswer[1] = when (i) {
                R.id.radioSecond_btn1 -> 0
                R.id.radioSecond_btn2 -> 1
                R.id.radioSecond_btn3 -> 2
                R.id.radioSecond_btn4 -> 3
                else -> -1
            }
        }
        binding.radioGroupThird.setOnCheckedChangeListener { radioGroup, i ->
            listAnswer[2] = when (i) {
                R.id.radioThird_btn1 -> 0
                R.id.radioThird_btn2 -> 1
                R.id.radioThird_btn3 -> 2
                R.id.radioThird_btn4 -> 3
                else -> -1
            }
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_startFragment)
        }

        binding.buttonFinish.setOnClickListener {it ->
            if (listAnswer.filter { it < 0 }.sumOf { it } < 0) {
                Snackbar.make(it, "Чтобы продолжить, ответить пожалуйста на все вопросы", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(Color.RED).show()
            } else {
                val bundle = Bundle()
                bundle.putParcelable(ANSWER_RESULT, AnswerResult(listAnswer))
                findNavController().navigate(R.id.action_quizFragment_to_finishFragment)
            }
        }
    }

    @Parcelize
    class AnswerResult(val answerList: List<Int>) : Parcelable

    private fun getAnswer(
        onQuestionNumber: Int,
        answerNumber: Int,
        local: QuizStorage.Locale = RU
    ): String {
        return QuizStorage.getQuiz(local).questions[onQuestionNumber - 1]
            .answers[answerNumber - 1]
    }

    private fun getQuestion(questionNumber: Int, local: QuizStorage.Locale = RU): String {
        return QuizStorage.getQuiz(local).questions[questionNumber - 1].question
    }

    companion object {
        val RU = QuizStorage.Locale.Ru
        val EN = QuizStorage.Locale.En

        const val ANSWER_RESULT = "ANSWER_RESULT"
        @JvmStatic
        fun newInstance() = QuizFragment()
    }
}