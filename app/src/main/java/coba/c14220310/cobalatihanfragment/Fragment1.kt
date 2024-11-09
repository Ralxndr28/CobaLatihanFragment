package coba.c14220310.cobalatihanfragment



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import coba.paba.latihanfragment.MainActivity


class Fragment1 : Fragment() {

    private var score: Int = 50
    private lateinit var scoreTextView: TextView
    private lateinit var giveUpButton: Button
    private val buttonIds = listOf(
        R.id.button1, R.id.button2, R.id.button3,
        R.id.button4, R.id.button5, R.id.button6,
        R.id.button7, R.id.button8, R.id.button9
    )
    private var selectedButtons: MutableList<Button> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        scoreTextView = view.findViewById(R.id.score_text_view)
        giveUpButton = view.findViewById(R.id.give_up_button)
        updateScore()

        setupGame(view)

        giveUpButton.setOnClickListener {
            (activity as MainActivity).finalScore = score
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Fragment2())
                .commit()
        }

        return view
    }

    private fun setupGame(view: View) {
        val randomNumbers = generateRandomNumbers()
        for (i in buttonIds.indices) {
            val button = view.findViewById<Button>(buttonIds[i])
            button.text = randomNumbers[i].toString()
            button.isEnabled = true
            button.setOnClickListener { checkGuess(button) }
        }
    }

    private fun generateRandomNumbers(): List<Int> {
        val maxNumber = (activity as MainActivity).maxRandomNumber
        val numbers = mutableListOf<Int>()

        // Generate pairs of numbers within the max range
        repeat(5) { num -> repeat(2) { numbers.add((num % maxNumber) + 1) } }
        numbers.shuffle() // Shuffle to randomize positions
        return numbers
    }

    private fun checkGuess(button: Button) {
        selectedButtons.add(button)
        if (selectedButtons.size == 2) {
            val (first, second) = selectedButtons.map { it.text.toString().toInt() }
            if (first == second) {
                score += 10
                selectedButtons.forEach { it.isEnabled = false }
            } else {
                score -= 5
            }
            selectedButtons.clear()
            updateScore()

            // Check if all buttons are disabled (game complete), reset if needed
            if (buttonIds.all { id -> !requireView().findViewById<Button>(id).isEnabled }) {
                setupGame(requireView()) // Reset game with new numbers
            }
        }
    }

    private fun updateScore() {
        scoreTextView.text = "Skor: $score"
    }
}