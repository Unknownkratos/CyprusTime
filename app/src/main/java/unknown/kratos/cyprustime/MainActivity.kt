package unknown.kratos.cyprustime

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.Duration
import java.time.LocalDate
import java.time.Period
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculateButton = findViewById<Button>(R.id.button2)
        calculateButton.setOnClickListener {
            // Get current date
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Create DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Calculate age based on selected date
                    val selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
                    val currentDate = LocalDate.of(year, month + 1, day)

                    val period = Period.between(selectedDate, currentDate)
                    val duration = Duration.between(selectedDate.atStartOfDay(), currentDate.atStartOfDay())

                    val ageInYears = period.years
                    val ageInMinutes = duration.toMinutes()
                    val ageInSeconds = duration.seconds

                    // Display age in TextView
                    val textView = findViewById<TextView>(R.id.textView3)
                    textView.text = "Your age is $ageInYears years, $ageInMinutes minutes, and $ageInSeconds seconds"
                },
                year,
                month,
                day
            )

            // Show DatePickerDialog
            datePickerDialog.show()
        }
    }
}