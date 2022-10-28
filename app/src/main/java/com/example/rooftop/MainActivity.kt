package com.example.rooftop

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private val textBox: TextView by lazy { findViewById(R.id.tv_number_box) }
    private var firstTerm = 0.0
    private var operatorRepresentation = 1
    private var isOperationCompleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupButtons()
    }

    private fun setupButtons() {

        val btnNegative: Button = findViewById(R.id.btn_negate)
        val btnZero: Button = findViewById(R.id.btn_zero)
        val btnComma: Button = findViewById(R.id.btn_comma)
        val btnEquals: Button = findViewById(R.id.btn_equals)
        val btnOne: Button = findViewById(R.id.btn_one)
        val btnTwo: Button = findViewById(R.id.btn_two)
        val btnThree: Button = findViewById(R.id.btn_three)
        val btnPlus: Button = findViewById(R.id.btn_plus)
        val btnFour: Button = findViewById(R.id.btn_four)
        val btnFive: Button = findViewById(R.id.btn_five)
        val btnSix: Button = findViewById(R.id.btn_six)
        val btnMinus: Button = findViewById(R.id.btn_minus)
        val btnSeven: Button = findViewById(R.id.btn_seven)
        val btnEight: Button = findViewById(R.id.btn_eight)
        val btnNine: Button = findViewById(R.id.btn_nine)
        val btnProduct: Button = findViewById(R.id.btn_product)
        val btnDivideOneByNumber: Button = findViewById(R.id.btn_divide_one_by_number)
        val btnPow: Button = findViewById(R.id.btn_pow)
        val btnRoot: Button = findViewById(R.id.btn_root)
        val btnDivision: Button = findViewById(R.id.btn_division)
        val btnPercentage: Button = findViewById(R.id.btn_percentage)
        val btnDeleteLocal: Button = findViewById(R.id.btn_delete_this)
        val btnDeleteAll: Button = findViewById(R.id.btn_delete_all)
        val btnDelete: ImageButton = findViewById(R.id.btn_delete)


        btnZero.setOnClickListener { checkIfAddZero() }
        btnOne.setOnClickListener { setChar("1") }
        btnTwo.setOnClickListener { setChar("2") }
        btnThree.setOnClickListener { setChar("3") }
        btnFour.setOnClickListener { setChar("4") }
        btnFive.setOnClickListener { setChar("5") }
        btnSix.setOnClickListener { setChar("6") }
        btnSeven.setOnClickListener { setChar("7") }
        btnEight.setOnClickListener { setChar("8") }
        btnNine.setOnClickListener { setChar("9") }
        btnComma.setOnClickListener { setChar(".") }

        btnDelete.setOnClickListener {
            deleteChar()
            isOperationCompleted = false
        }
        btnDeleteAll.setOnClickListener {
            deleteAll()
            restoreDefaultZero()
        }
        btnDeleteLocal.setOnClickListener { deleteAll() }


        btnPlus.setOnClickListener {
            saveFirstValue()
            setOperator(1)
            deleteAll()
        }

        btnMinus.setOnClickListener {
            saveFirstValue()
            setOperator(2)
            deleteAll()
        }

        btnProduct.setOnClickListener {
            saveFirstValue()
            setOperator(3)
            deleteAll()
        }

        btnDivision.setOnClickListener {
            saveFirstValue()
            setOperator(4)
            deleteAll()
        }

        btnRoot.setOnClickListener {
            saveFirstValue()
            setOperator(5)
            deleteAll()
        }

        btnPow.setOnClickListener {
            saveFirstValue()
            setOperator(6)
            deleteAll()
        }

        btnDivideOneByNumber.setOnClickListener {
            saveFirstValue()
            setOperator(7)
            deleteAll()
        }

        btnPercentage.setOnClickListener {
            saveFirstValue()
            setOperator(8)
            deleteAll()
        }

        btnNegative.setOnClickListener { invertNumber() }

        btnEquals.setOnClickListener { makeOperation() }

    }

    private fun restoreDefaultZero() {
        firstTerm = 0.0
    }

    private fun invertNumber() {
        val actualNumber = textBox.text.toString()
        if (textBox.text.toString().first() != '-' && textBox.text.toString() != "0"){
            "-$actualNumber".also { negativeNumber -> textBox.text = negativeNumber }
        }else if (textBox.text.toString() == "0") {
            return
        }else {
            textBox.text = actualNumber.drop(1)
        }
    }

    private fun makeOperation() {
        val secondTerm = textBox.text.toString().toDouble()
        when(operatorRepresentation){
            1 -> { drawResult(firstTerm.plus(secondTerm)) }
            2 -> { drawResult(firstTerm.minus(secondTerm)) }
            3 -> { drawResult(firstTerm.times(secondTerm)) }
            4 -> { drawResult(firstTerm.div(secondTerm)) }
            5 -> { drawResult(firstTerm makeRootOf secondTerm) }
            6 -> { drawResult(firstTerm.pow(secondTerm)) }
            7 -> { drawResult(1/firstTerm) }
            8 -> { drawResult((firstTerm.div(secondTerm)).times(100)) }
        }
        isOperationCompleted = true
    }

    private fun drawResult(result: Double) {
        if (result % 1 == 0.0) textBox.text = result.toInt().toString()
        else textBox.text = result.toString()
    }

    private fun setOperator(operatorReference: Int) {
        operatorRepresentation = operatorReference
    }

    private fun saveFirstValue() {
        firstTerm = textBox.text.toString().toDouble()
    }

    private fun deleteAll(){
        textBox.text = "0"
    }


    private fun deleteChar(){
        if (textBox.text.toString().length > 1) {
            val text = textBox.text.toString().dropLast(1)
            textBox.text = text
        }else {
            textBox.text = "0"
        }
    }

    private fun checkIfAddZero() {
        if (textBox.text.toString() != "0") {
            textBox.append("0")
        }
    }


    private fun setChar(char: String){
        if (textBox.text.toString() == "0") {
            textBox.text = char
        }else {
            checkIfOperationCompleted(char)
        }
    }

    private fun checkIfOperationCompleted(char: String) {
        if (isOperationCompleted) {
            textBox.text = char
            isOperationCompleted = false
        }else {
            textBox.append(char)
        }
    }

    private infix fun Double.makeRootOf(index:Double):Double {
        return this.pow(1/index)
    }
}