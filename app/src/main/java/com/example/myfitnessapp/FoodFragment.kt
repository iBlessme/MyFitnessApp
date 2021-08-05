package com.example.myfitnessapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_food.*

class FoodFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onStart() {
        super.onStart()

        reset.setOnClickListener {
            valueCal.setText("0")
        }

        var list = listOf("Рис - 200 ккал", "Салат - 50 ккал", "Лапша - 140 ккал", "Бургер - 200 ккал", "Пицца - 250 ккал","Холодный напиток - 140 ккал","Яблоки - 114 ккал","Хлеб - 65 ккал (4 кусочка)","Пирог - 132 ккал (1 кусочек)","Морковь - 30 ккал","Курица - 220 ккал","Шоколад - 200 ккал","Пончик - 110 ккал","Картовель фри - 110 ккал","Авельсин - 52 ккал","Хот-дог - 95 ккал","Чипсы - 155 ккал" ,"Мороженное - 100 ккал" ,"Мясо - 300 ккал (250гр)")

        listView.adapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_list_item_1, list)
        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            if (position == 0) {
                valueCal.setText((valueCal.text.toString().toInt() + 200).toString())
            }
            if (position == 1) {
                valueCal.setText((valueCal.text.toString().toInt() + 50).toString())
            }
            if (position == 2) {
                valueCal.setText((valueCal.text.toString().toInt() + 140).toString())
            }
            if (position == 3) {
                valueCal.setText((valueCal.text.toString().toInt() + 200).toString())
            }
            if (position == 4) {
                valueCal.setText((valueCal.text.toString().toInt() + 250).toString())
            }
            if (position == 5) {
                valueCal.setText((valueCal.text.toString().toInt() + 140).toString())
            }
            if (position == 6) {
                valueCal.setText((valueCal.text.toString().toInt() + 114).toString())
            }
            if (position == 7) {
                valueCal.setText((valueCal.text.toString().toInt() + 65).toString())
            }
            if (position == 8) {
                valueCal.setText((valueCal.text.toString().toInt() + 132).toString())
            }
            if (position == 9) {
                valueCal.setText((valueCal.text.toString().toInt() + 30).toString())
            }
            if (position == 10) {
                valueCal.setText((valueCal.text.toString().toInt() + 220).toString())
            }
            if (position == 11) {
                valueCal.setText((valueCal.text.toString().toInt() + 200).toString())
            }
            if (position == 12) {
                valueCal.setText((valueCal.text.toString().toInt() + 110).toString())
            }
            if (position == 13) {
                valueCal.setText((valueCal.text.toString().toInt() + 110).toString())
            }
            if (position == 14) {
                valueCal.setText((valueCal.text.toString().toInt() + 52).toString())
            }
            if (position == 15) {
                valueCal.setText((valueCal.text.toString().toInt() + 95).toString())
            }
            if (position == 16) {
                valueCal.setText((valueCal.text.toString().toInt() + 155).toString())
            }
            if (position == 17) {
                valueCal.setText((valueCal.text.toString().toInt() + 100).toString())
            }
            if (position == 18) {
                valueCal.setText((valueCal.text.toString().toInt() + 300).toString())
            }



        }
    }
}