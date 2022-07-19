package com.garibyan.armen.tbc_task_11

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.garibyan.armen.tbc_task_11.Constants.IS_EDITING
import com.garibyan.armen.tbc_task_11.Constants.ITEM_INDEX
import com.garibyan.armen.tbc_task_11.databinding.ActivityAddEditGameBinding

class AddEditGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditGameBinding
    private var id: Int = 0
    private var isEditing: Boolean = false
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditGameBinding.inflate(layoutInflater)
        index = intent.getIntExtra(ITEM_INDEX, 0)
        setContentView(binding.root)

        getGameTypeFromRadioGroup()
        init()
        onClickListeners()

    }

    private fun onClickListeners() {
        binding.btnAddEdit.setOnClickListener {
            checkEditing()
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun init() {
        isEditing = intent.getBooleanExtra(IS_EDITING, false)
        if (isEditing) {
            binding.btnAddEdit.text = getString(R.string.edit)
            getGameInfo()
        }
    }

    private fun getGameTypeFromRadioGroup(): Boolean {
        binding.radioGroup.setOnCheckedChangeListener { _, checked ->
            id = when (checked) {
                R.id.rdBtnFootball -> ViewTypes.FOOTBALL
                R.id.rdBtnHockey -> ViewTypes.HOCKEY
                else -> 0
            }
        }
        return id != 0
    }

    private fun checkEditing() {
        when (isEditing) {
            false -> addUser()
            true -> editGame()
        }
    }

    private fun editGame() {
        val game = list[index]

        val updatedGame = Game(
            binding.edtFirstTeam.insertCorrectText(game.firstTeam),
            binding.edtSecondTeam.insertCorrectText(game.secondTeam),
            binding.edtFirstTeamScore.insertCorrectText(game.firstTeamScore.toString()).toInt(),
            binding.edtSecondTeamScore.insertCorrectText(game.secondTeamScore.toString()).toInt(),
            id
        )

        list[index] = updatedGame
        finish()
    }

    private fun addUser() {
        if(isValidInput()){
        binding.apply {
            val game = Game(
                edtFirstTeam.text.toString(),
                edtSecondTeam.text.toString(),
                edtFirstTeamScore.text.toString().toInt(),
                edtSecondTeamScore.text.toString().toInt(),
                id
            )
            list.add(game)
            finish()}
        }else
            Toast.makeText(this, R.string.invalid_input, Toast.LENGTH_SHORT).show()
    }

    private fun getGameInfo() {
        binding.apply {
            edtFirstTeam.hint = list[index].firstTeam
            edtSecondTeam.hint = list[index].secondTeam
            edtFirstTeamScore.hint = list[index].firstTeamScore.toString()
            edtSecondTeamScore.hint = list[index].secondTeamScore.toString()
            if (list[index].gameType == ViewTypes.FOOTBALL) {
                radioGroup.check(R.id.rdBtnFootball)
            } else {
                radioGroup.check(R.id.rdBtnHockey)
            }
        }
    }

    private fun isValidInput(): Boolean {
        binding.apply {
            return !(edtFirstTeam.text!!.isBlank() ||
                    edtSecondTeam.text!!.isBlank() ||
                    edtFirstTeamScore.text!!.isBlank() ||
                    edtSecondTeamScore.text!!.isBlank() ||
                    !getGameTypeFromRadioGroup())
        }
    }

}

