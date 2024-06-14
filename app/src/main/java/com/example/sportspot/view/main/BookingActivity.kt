package com.example.sportspot.view.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sportspot.databinding.ActivityBookingBinding

import com.example.sportspot.view.utils.DatePickerFragment
import com.example.sportspot.view.utils.TimePickerFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BookingActivity : AppCompatActivity(), DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

    private var _binding: ActivityBookingBinding? = null
    private val binding get() = _binding!!
    private var arrivalDateMillis: Long = System.currentTimeMillis()
    private var arrivalTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.bookingButton.setOnClickListener {
            processBooking()
        }
    }

    private fun processBooking() {
        val arrivalDate = binding.arrivalDate.text.toString()
        val arrivalTime = binding.arrivalClock.text.toString()

        if (arrivalDate.isNotEmpty() && arrivalTime.isNotEmpty()) {
            val bookingMessage = "Pemesanan berhasil untuk tanggal: $arrivalDate dan waktu: $arrivalTime"
            Toast.makeText(this, bookingMessage, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Silakan pilih tanggal dan waktu kedatangan.", Toast.LENGTH_SHORT).show()
        }
    }

    fun showDatePicker(view: View) {
        val dialogFragment = DatePickerFragment()
        dialogFragment.show(supportFragmentManager, "datePicker")
    }

    fun showTimePicker(view: View) {
        val timePickerFragment = TimePickerFragment()
        timePickerFragment.show(supportFragmentManager, "timePicker")
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.format(calendar.time).also { binding.arrivalDate.text = it }
        arrivalDateMillis = calendar.timeInMillis
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        timeFormat.format(calendar.time).also { binding.arrivalClock.text = it }
        arrivalTime = timeFormat.format(calendar.time)
    }
}
