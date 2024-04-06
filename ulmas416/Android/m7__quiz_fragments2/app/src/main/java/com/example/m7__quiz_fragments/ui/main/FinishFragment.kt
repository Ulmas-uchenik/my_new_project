package com.example.m7__quiz_fragments.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.m7__quiz_fragments.R
import com.example.skillbox_hw_quiz.quiz.QuizStorage

class FinishFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val classAnswer =
//            arguments?.getParcelable<QuizFragment.AnswerResult>(QuizFragment.ANSWER_RESULT)
//
//        val listAnswer = classAnswer?.answerList
//
//        val quiz = QuizStorage.getQuiz(QuizFragment.RU)
//        val resultAnswer = listAnswer
//        Log.d("Д36", listAnswer.toString())

        view.findViewById<Button>(R.id.btn_restart).setOnClickListener {
            findNavController().navigate(R.id.action_finishFragment_to_quizFragment)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = FinishFragment()
    }
}